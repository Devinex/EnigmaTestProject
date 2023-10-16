package com.example.demo.searchCriteria;

import com.example.demo.enums.SearchOperation;
import com.example.demo.model.Person.Person;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PersonSpecification implements Specification<Person> {
    private List<SearchCriteria> searchCriteriaList;

    public PersonSpecification() {
        this.searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : searchCriteriaList) {
            if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(
                        criteriaBuilder.equal(root.get(
                                criteria.getKey()), criteria.getValue()));
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
