package com.RestauarantAPIs.Restaurant.controllers;

import com.RestauarantAPIs.Restaurant.BaseController;
import com.RestauarantAPIs.Restaurant.entities.Reservations;
import com.RestauarantAPIs.Restaurant.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * all reservation routes
 * mapped by /book
 * */

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/books")
public class ReservationsController extends BaseController {

    @Autowired
    private ReservationsService reservationsService;

    //book a table
    @PostMapping("/")
    public Reservations addReservation(@RequestBody Reservations reservation) {
        this.reservationsService.addReservation(reservation, getCurrentUser());
        return reservation;
    }

    //fetch all reservations
    @GetMapping("/all")
    public Iterable<Reservations> findAll() {
        return this.reservationsService.findAll();
    }

    //fetch current user reservation
    @GetMapping("/me")
    public Iterable<Reservations> findUserBooks() {
        Iterable<Reservations> books = reservationsService.findUserBooks(getCurrentUser().getId());
        return books;
    }
}