package com.rent.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mykhailo_Hodovaniuk on 12/2/13.
 */
@Entity
@Table(name = "\"UserDescription\"")
public class UserDescription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_description_id_seq_gen")
    @SequenceGenerator(name = "user_description_id_seq_gen", sequenceName = "user_description_id_seq")
    private Long id;
    @Column
    private String companyName;
    @Column
    private String phone;
    @Column
    private String contactPersonName;
    @Embedded
    private Address address;
    @Column
    private String details;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserDescription() {
        address=new Address();
    }

    public UserDescription(String companyName, String phone, String contactPersonName, Address address, String details, User user) {
        this.companyName = companyName;
        this.phone = phone;
        this.contactPersonName = contactPersonName;
        this.address = address;
        this.details = details;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
