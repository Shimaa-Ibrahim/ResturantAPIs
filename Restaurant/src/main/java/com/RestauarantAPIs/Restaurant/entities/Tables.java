package com.RestauarantAPIs.Restaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

/*
 * table table :)
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "table number cannot be null")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]+$")
    private String tableNumber;

    @NotNull(message = "accommodation number cannot be null")
    @Positive
    private Integer tableAccommodation;


    @JsonIgnore
    @OneToMany(mappedBy = "tables", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reservations> reservations;

    public Tables(String tableNumber, Integer tableAccommodation) {
        this.tableNumber = tableNumber;
        this.tableAccommodation = tableAccommodation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getTableAccommodation() {
        return tableAccommodation;
    }

    public void setTableAccommodation(Integer tableAccommodation) {
        this.tableAccommodation = tableAccommodation;
    }

    public Set<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservations> reservations) {
        this.reservations = reservations;
    }
}
