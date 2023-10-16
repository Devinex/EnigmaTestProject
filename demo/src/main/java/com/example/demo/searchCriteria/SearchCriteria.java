package com.example.demo.searchCriteria;

import com.example.demo.enums.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;
}
