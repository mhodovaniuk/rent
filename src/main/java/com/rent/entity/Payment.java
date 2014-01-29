package com.rent.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * User: mychajlo
 * Date: 12/8/13
 * Time: 2:09 PM
 */
@Entity
@Table(name = "\"Payment\"")

@NamedQueries({
        @NamedQuery(name = "Payment.findAllNotAcceptedPayments", query = "select p from Payment p where p.accepted=false"),
        @NamedQuery(name = "Payment.isExistsWithSuchID", query = "select count(p) from Payment p where p.visibleID=:visibleID")
})
public class Payment implements Serializable,VisibleID {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_seq_gen")
    @SequenceGenerator(name = "payment_id_seq_gen", sequenceName = "payment_id_seq")
    private Long id;
    @Column
    private BigDecimal value;
    @Column
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column
    private boolean accepted;
    @Column
    private String details;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(unique = true)
    private String visibleID;

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order orderPart) {
        this.order = orderPart;
    }

    public String getVisibleID() {
        return visibleID;
    }

    public void setVisibleID(String visibleID) {
        this.visibleID = visibleID;
    }
}

