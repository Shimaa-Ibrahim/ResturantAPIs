package com.RestauarantAPIs.Restaurant.services;

import com.RestauarantAPIs.Restaurant.entities.Tables;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;


@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public void addTable(@Valid Tables table) {
        this.tablesRepository.save(table);
    }

    //return all tables
    public Iterable<Tables> findAll() {
        return tablesRepository.findAll();
    }

    public Tables findTable(Long id) {
        return tablesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found " + id));

    }

    public Iterable<Tables> findAvailableTablesByDate(String date) {
        return this.tablesRepository.findAvailableTablesByDate(date);
    }
}
