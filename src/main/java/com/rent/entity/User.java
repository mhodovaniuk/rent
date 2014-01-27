package com.rent.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * Created by Mykhailo_Hodovaniuk on 12/2/13.
 */
@Entity
@Table(name = "\"User\"")
@NamedQuery(name = "User.findByEmail",query = "select u from User u where u.email=:email")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_id_seq_gen" )
    @SequenceGenerator(name = "user_id_seq_gen", sequenceName = "user_id_seq",initialValue = 1000)
    private Long id;
    @Column(unique = true)

    private String email;
    @Column
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne(cascade = CascadeType.PERSIST,mappedBy = "user")
    private UserDescription userDescription;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders=new ArrayList<Order>();

    public User(){
        userDescription=new UserDescription();
        userDescription.setUser(this);
        role=Role.CLIENT;
    }

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDescription getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(UserDescription userDescription) {
        this.userDescription = userDescription;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
