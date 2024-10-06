package com.ecommerce_backend.ecommerce.common.filters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.Type;

public class CriteriaFilter<E> {

    public Type<?> getFieldType(Class<?> entityClass, String fieldName, EntityManager entityManager) {

        EntityType<E> entityType = (EntityType<E>) entityManager.getMetamodel().entity(entityClass);

        if (fieldName.contains("<") || fieldName.contains(">")) {
            fieldName = fieldName.substring(0, fieldName.length() - 1);
        }
        SingularAttribute<? super E, ?> attribute = entityType.getSingularAttribute(fieldName);

        return attribute.getType();

    }

    public ListingResponseObject<E> applyFilterForSearching(Class<E> entityClass, Map<String, String> map,
            EntityManager entityManager,
            List<String> sortingColumns, int size, int page) throws ParseException {
        var filterMappingObject = getFilterMappingObject(entityClass, map, entityManager, sortingColumns);
        Long count = entityManager.createQuery(filterMappingObject.getCounCriteriaQuery()).getSingleResult();
        ListingResponseObject<E> listingResponseObject = new ListingResponseObject<>();
        listingResponseObject.setTotalResultCount(count);
        if ((long) page * size > count) {

            // Add your own sorting logic here
            listingResponseObject.setResponse(entityManager.createQuery(filterMappingObject.getCriteriaQuery())
                    .setFirstResult(0)
                    .setMaxResults(size).getResultList());
        } else {
            listingResponseObject.setResponse(entityManager.createQuery(filterMappingObject.getCriteriaQuery())
                    .setFirstResult(page * size)
                    .setMaxResults(size).getResultList());

        }
        return listingResponseObject;
    }

    public ListingResponseObject<E> applyFilter(Class<E> entityClass, Map<String, String> map,
            EntityManager entityManager) throws ParseException {
        var filterMappingObject = getFilterMappingObject(entityClass, map, entityManager, null);
        Long count = entityManager.createQuery(filterMappingObject.getCounCriteriaQuery()).getSingleResult();
        ListingResponseObject<E> listingResponseObject = new ListingResponseObject<>();
        listingResponseObject.setTotalResultCount(count);
        listingResponseObject
                .setResponse(entityManager.createQuery(filterMappingObject.getCriteriaQuery()).getResultList());
        return listingResponseObject;
    }

    private FilterMappingObject<E> getFilterMappingObject(Class<E> entityClass, Map<String, String> map,
            EntityManager entityManager,
            List<String> sortingColumns) throws ParseException {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = generateQuery(criteriaBuilder, entityClass, map, entityManager,
                sortingColumns);
        CriteriaQuery<Long> count = getCount(criteriaBuilder, entityClass, map, entityManager);

        FilterMappingObject<E> filterMappingObject = new FilterMappingObject<>();
        filterMappingObject.setCriteriaQuery(criteriaQuery);
        filterMappingObject.setCounCriteriaQuery(count);

        return filterMappingObject;
    }

    private CriteriaQuery<E> generateQuery(CriteriaBuilder criteriaBuilder, Class<E> entityClass,
            Map<String, String> map, EntityManager entityManager, List<String> sortingColumns) throws ParseException {
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> root = (Root<E>) criteriaQuery.from(entityClass);
        var predicate = generatePredicates(map, criteriaQuery, entityClass, criteriaBuilder, entityManager, root);
        criteriaQuery.where(predicate);
        // Check if sorting columns are present or not
        if (sortingColumns != null && !sortingColumns.isEmpty()) {
            List<Order> orders = new ArrayList<>();
            for (var column : sortingColumns) {
                orders.add(criteriaBuilder.asc(root.get(column)));
            }
            criteriaQuery.orderBy(orders);
        }
        return criteriaQuery;
    }

    private CriteriaQuery<Long> getCount(CriteriaBuilder criteriaBuilder, Class<E> entityClass,
            Map<String, String> map, EntityManager entityManager) throws ParseException {
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = (Root<E>) criteriaQuery.from(entityClass);
        var predicate = generatePredicates(map, criteriaQuery, entityClass, criteriaBuilder, entityManager, root);
        criteriaQuery.where(predicate);
        return criteriaQuery;
    }

    private Predicate generatePredicates(Map<String, String> map, CriteriaQuery criteriaQuery, Class<E> entityClass,
            CriteriaBuilder criteriaBuilder, EntityManager entityManager, Root<E> root) throws ParseException {

        List<Predicate> orPredicates = new ArrayList<>();
        List<Predicate> andPredicates = new ArrayList<>();
        for (var map1 : map.entrySet()) {
            Class<?> javaType = null;
            if (map1.getKey().contains(".")) {
                var keys = map1.getKey().split("\\.");
                Join<E, ?> join = root.join(keys[0], JoinType.INNER);
                orPredicates.add(criteriaBuilder.equal(join.get(keys[1]), map1.getValue()));
            } else {
                javaType = getFieldType(entityClass, map1.getKey(), entityManager).getJavaType();
                if (javaType == String.class) {
                    orPredicates.add(criteriaBuilder.like(root.get(map1.getKey()), "%" + map1.getValue() + "%"));
                } else if (javaType == Date.class || javaType == Timestamp.class) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    if (map1.getKey().contains("<")) {
                        var value = map1.getValue();
                        Date date = simpleDateFormat.parse(value);
                        andPredicates.add(criteriaBuilder.greaterThanOrEqualTo(
                                root.get(map1.getKey().substring(0, map1.getKey().length() - 1)), date));
                    } else if (map1.getKey().contains(">")) {
                        var value = map1.getValue();
                        Date date = simpleDateFormat.parse(value);
                        andPredicates.add(criteriaBuilder.lessThanOrEqualTo(
                                root.get(map1.getKey().substring(0, map1.getKey().length() - 1)), date));
                    } else {
                        Date date = simpleDateFormat.parse(map1.getValue());
                        orPredicates.add(criteriaBuilder.equal(root.get(map1.getKey()), date));
                    }
                }

            }

        }
        return combineTwoPredicatesIntoOne(orPredicates, andPredicates, criteriaBuilder);
    }

    private Predicate combineTwoPredicatesIntoOne(List<Predicate> orPredicates, List<Predicate> andPredicates,
            CriteriaBuilder criteriaBuilder) {
        Predicate combinedPredicate = null;
        if (!orPredicates.isEmpty() && !andPredicates.isEmpty()) {
            combinedPredicate = criteriaBuilder.and(criteriaBuilder.or(orPredicates.toArray(new Predicate[0])),
                    criteriaBuilder.and(andPredicates.toArray(new Predicate[0])));
        } else if (!orPredicates.isEmpty()) {
            combinedPredicate = criteriaBuilder.or(orPredicates.toArray(new Predicate[0]));
        } else if (!andPredicates.isEmpty()) {
            combinedPredicate = criteriaBuilder.and(andPredicates.toArray(new Predicate[0]));
        }
        return combinedPredicate;
    }
}