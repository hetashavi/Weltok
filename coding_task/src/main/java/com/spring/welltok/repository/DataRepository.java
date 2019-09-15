package com.spring.welltok.repository;

import com.spring.welltok.model.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface DataRepository extends CrudRepository<Data, Long> {
    }


