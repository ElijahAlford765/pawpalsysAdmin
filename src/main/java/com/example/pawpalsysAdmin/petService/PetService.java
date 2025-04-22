package com.example.pawpalsysAdmin.petService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PetService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
    private int providerId;
    private String description;
    private float price;
    private String availability;

    private boolean approved;

    public PetService() {
    }

    public PetService(int providerId, String description, float price, String availability, boolean approved) {
        this.providerId = providerId;
        this.description = description;
        this.price = price;
        this.availability = availability;

        this.approved = approved;
    }

    public boolean getApproved() {
        return approved;

    }
    public void setApproved(boolean approved){
        this.approved = approved;
    }





    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "PetService{" +
                "serviceId=" + serviceId +
                ", providerId=" + providerId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availability='" + availability + '\'' +
                '}';
    }
}
