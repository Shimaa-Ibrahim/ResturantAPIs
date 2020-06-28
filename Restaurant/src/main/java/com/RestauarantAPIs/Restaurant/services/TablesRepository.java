package com.RestauarantAPIs.Restaurant.services;

import com.RestauarantAPIs.Restaurant.entities.Tables;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

interface TablesRepository extends CrudRepository<Tables, Long> {

    @Query(value = "SELECT `tables`.* \n" +
            "FROM `tables`, `reservations`\n" +
            "WHERE `tables`.`id` = `reservations`.`tables_id` AND `reservations`.`reservation_date` != ?1 \n" +
            "UNION\n" +
            "SELECT `tables`.* FROM `tables` WHERE `tables`.`id` NOT IN(\n" +
            "SELECT `reservations`.`tables_id` FROM `reservations`)",
            nativeQuery = true)
    Iterable<Tables> findAvailableTablesByDate(String date);

}