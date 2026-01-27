package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="vehicles")
public class Vehicle {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="type")
    private String type;

	@Column(name="license")
    private String license;

	@Column(name="mileage")
    private Integer mileage;

	public Vehicle() {
	}

	public Vehicle(String type, String license, Integer mileage) {
		this.type = type;
		this.license = license;
		this.mileage = mileage;
	}

	public Vehicle(Vehicle vehicle) {
		this.type = vehicle.getType();
		this.license = vehicle.getLicense();
		this.mileage = vehicle.getMileage();
	}

	@Override
    public String toString() {
        return String.format(
			"Vehicle[id=%d, type='%s', license='%s']",
			id, type, license);
    }

    // Getters and Setters
	public Long getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getLicense() {
		return license;
	}
	public Integer getMileage() {
		return mileage;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

}

