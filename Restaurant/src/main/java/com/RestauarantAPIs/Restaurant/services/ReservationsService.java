package com.RestauarantAPIs.Restaurant.services;

import com.RestauarantAPIs.Restaurant.entities.Reservations;
import com.RestauarantAPIs.Restaurant.entities.Tables;
import com.RestauarantAPIs.Restaurant.entities.User;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ReservationsService {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<Tables> available = new ArrayList<Tables>();

    @Autowired
    private ReservationsRepository reservationsRepository;

    @Autowired
    private TablesService tablesService;

    //book reservation
    public void addReservation(@Valid Reservations reservation, User user) {
        reservation.setUser(user);
        Iterable<Tables> AllTables = this.tablesService.findAll();
        Date today = new Date();
        Date userDate = new Date(reservation.getReservationDate().getTime());
        Boolean isAvailable = true;

        for (Tables table : AllTables) {
            isAvailable = true;
            if (table.getReservations().isEmpty() && table.getTableAccommodation() >= reservation.getMembersNumber()) {
                this.available.add(table);
            } else {
                if (table.getTableAccommodation() >= reservation.getMembersNumber()) {
                    for (Reservations book : table.getReservations()) {
                        Date bookDate = new Date(book.getReservationDate().getTime());
                        if (formatter.format(today).equals(formatter.format(bookDate)) || formatter.format(userDate).equals(formatter.format(bookDate))) {
                            isAvailable = false;
                        }
                    }
                    if (isAvailable) {
                        this.available.add(table);
                    }
                }
            }
        }
        reservation.setTables(this.available.get(0));
        this.reservationsRepository.save(reservation);
        this.available.clear();

    }

    //    get all reservations
    public Iterable<Reservations> findAll() {
        return reservationsRepository.findAll();
    }

    public Reservations findSpecific(Integer id) {
        return reservationsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found " + id));
    }

    //    user's resevations
    public Iterable<Reservations> findUserBooks(Long userId) {
        return this.reservationsRepository.findByUserId(userId);
    }

}
