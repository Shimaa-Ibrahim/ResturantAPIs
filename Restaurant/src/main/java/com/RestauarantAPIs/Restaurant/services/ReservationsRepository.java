package com.RestauarantAPIs.Restaurant.services;

import com.RestauarantAPIs.Restaurant.entities.Reservations;
import org.springframework.data.repository.CrudRepository;


interface ReservationsRepository extends CrudRepository<Reservations, Integer> {
    Iterable<Reservations> findByUserId(Long userId);

}