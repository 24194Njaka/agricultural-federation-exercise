package com.argiculturre.entity;

import java.time.LocalDateTime;

public class CollectivityEntity {
    private Long id;
    private Long number;
    private String name;
    private String city;
    private String specialty;
    private LocalDateTime createdAt;
    private Boolean openingAuthorized;
    private java.math.BigDecimal currentBalance;
    private String currency;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getNumber() { return number; }
    public void setNumber(Long number) { this.number = number; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public Boolean getOpeningAuthorized() { return openingAuthorized; }
    public void setOpeningAuthorized(Boolean openingAuthorized) { this.openingAuthorized = openingAuthorized; }
    public java.math.BigDecimal getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(java.math.BigDecimal currentBalance) { this.currentBalance = currentBalance; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}