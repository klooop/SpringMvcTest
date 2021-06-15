package com.example.springmvc.repositories.specifications;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import org.springframework.data.jpa.domain.Specification;

public class CreditSpecs {
    public static Specification<Credit> phoneContains(Long phone) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("phone"), phone);
    }
    public static Specification<Credit> nameContains(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
    public static Specification<Credit> surnameContains(String surname) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("surname"), "%" + surname + "%");
    }
    public static Specification<Credit> middleNameContains(String middle_name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("middle_name"), "%" + middle_name + "%");
    }
    public static Specification<Credit> passportSeriesContains(int passport_series) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("passport_series"), passport_series);
    }
    public static Specification<Credit> passportNumberContains(int passport_number) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("passport_number"), passport_number);
    }


}
