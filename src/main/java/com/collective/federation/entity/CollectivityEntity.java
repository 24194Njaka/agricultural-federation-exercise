package com.collective.federation.entity;
import java.time.LocalDate;

public class CollectivityEntity {
    private Long id;
    private String name;
    private String location;
    private String specialty;
    private String city;
    private LocalDate creationDate;
    private TypeStatus status;
    private LocalDate authorizationDate;

    public CollectivityEntity() {}

    public CollectivityEntity(Long id, String name, String location, String specialty, String city, LocalDate creationDate, TypeStatus status, LocalDate authorizationDate) {
        this.id = id; this.name = name; this.location = location; this.specialty = specialty;
        this.city = city; this.creationDate = creationDate; this.status = status; this.authorizationDate = authorizationDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getSpecialty() { return specialty; }
    public String getCity() { return city; }
    public LocalDate getCreationDate() { return creationDate; }
    public TypeStatus getStatus() { return status; }
    public LocalDate getAuthorizationDate() { return authorizationDate; }
}