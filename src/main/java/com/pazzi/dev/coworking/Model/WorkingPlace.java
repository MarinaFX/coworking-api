package com.pazzi.dev.coworking.Model;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.TABLE;

@Entity
@Table(name = "WORKING_PLACE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "address"})
})
public class WorkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Basic
    @Column(unique = true)
    private String name;

    @Basic
    @Column(unique = true)
    private String address;

    @Basic
    private Double rating;

    @Basic
    private Double wifiRating;

    @Basic
    private Double latitude;

    @Basic
    private Double longitude;

    @Override
    public String toString() {
        return this.name + " - " + this.address + " (" + this.latitude + ", " + this.longitude + ")";
    }

    public WorkingPlace setName(String name) {
        this.name = name;
        return this;
    }

    public WorkingPlace setAddress(String address) {
        this.address = address;
        return this;
    }

    public WorkingPlace setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public WorkingPlace setWifiRating(Double wifiRating) {
        this.wifiRating = wifiRating;
        return this;
    }

    public WorkingPlace setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public WorkingPlace setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Double getRating() {
        return rating;
    }

    public Double getWifiRating() {
        return wifiRating;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
