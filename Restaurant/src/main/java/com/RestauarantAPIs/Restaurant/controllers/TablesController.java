package com.RestauarantAPIs.Restaurant.controllers;

import com.RestauarantAPIs.Restaurant.entities.Reservations;
import com.RestauarantAPIs.Restaurant.entities.Tables;
import com.RestauarantAPIs.Restaurant.services.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * all tables routes
 * mapped by /tables
 * */


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tables")
public class TablesController {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    @Autowired
    private TablesService tablesService;

    //find all tables
    @GetMapping("/")
    public Iterable<Tables> findAllTables() {

        return tablesService.findAll();
    }

//    @GetMapping("/{id}")
//    public Tables findTable(@PathVariable(value = "id") Long id) {
//        return tablesService.findTable(id);
//    }

    //    add table
    @PostMapping("/")
    public Tables addTable(@RequestBody Tables table) {
        tablesService.addTable(table);
        return table;
    }

    //to get available tables "custom func : all except date reservations == user date "
    @GetMapping("/available/{date}")
    public Iterable<Tables> getAvailableTables(@PathVariable String date) {
        ArrayList<Tables> available = new ArrayList<Tables>();
        Iterable<Tables> AllTables = this.tablesService.findAll();
        Boolean isAvailable = true;

        for (Tables table : AllTables) {
            isAvailable = true;
            if (table.getReservations().isEmpty()) {
                available.add(table);
            } else {
                for (Reservations reservation : table.getReservations()) {
                    Date bookDate = new Date(reservation.getReservationDate().getTime());
                    if (date.equals(formatter.format(bookDate))) {
                        isAvailable = false;
                    }
                }
                if (isAvailable) {
                    available.add(table);
                }
            }
        }
        return available;
    }

    //find available tables by date using sql query
    @GetMapping("/availableDate/{date}")
    public Iterable<Tables> findAvailableTablesByDate(@PathVariable String date) {
        return this.tablesService.findAvailableTablesByDate(date);
    }
}