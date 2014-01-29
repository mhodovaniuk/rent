package com.rent.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Mykhailo_Hodovaniuk on 12/4/13.
 */
@Entity
@Table(name = "\"Order\"")
@NamedQueries({
    @NamedQuery(name="Order.findUserOrders",query = "select o from Order o where o.user.id=:userId"),
    @NamedQuery(name="Order.isExistsWithSuchID",query = "select count(o) from Order o where o.visibleID=:visibleID"),
    @NamedQuery(name= "Order.findAcceptedPaymentsSum",query = "select sum(value) from Payment p where p.order.id=:orderId and p.accepted=true"),
    @NamedQuery(name= "Order.findTotalCost",query = "select sum(totalCost) from OrderPart p where p.order.id=:orderId")
})
public class Order implements Serializable,VisibleID {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq_gen")
    @SequenceGenerator(name = "order_id_seq_gen", sequenceName = "order_id_seq")
    private Long id;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column
    private Calendar creatingDate;
    @Column
    private String details;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<OrderPart> orderParts=new ArrayList<OrderPart>();
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order")
    private List<Payment> payments=new ArrayList<Payment>();
    @Column(unique = true)
    private String visibleID;


    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Calendar creatingDate) {
        this.creatingDate = creatingDate;
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

    public List<OrderPart> getOrderParts() {
        return orderParts;
    }

    public void setOrderParts(List<OrderPart> orderParts) {
        this.orderParts = orderParts;
    }


    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public String getVisibleID() {
        return visibleID;
    }

    public void setVisibleID(String visibleID) {
        this.visibleID = visibleID;
    }
}
