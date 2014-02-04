package com.rent.entity;

import com.rent.utils.MyDateUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: mychajlo
 * Date: 12/4/13
 * Time: 10:14 PM
 */
@Entity
@Table(name = "\"OrderPart\"")
@NamedQuery(name="OrderPart.isExistsWithSuchID",query = "select count(p) from OrderPart p where p.visibleID=:visibleID")
public class OrderPart implements Serializable,VisibleID  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_part_id_seq_gen")
    @SequenceGenerator(name = "order_part_id_seq_gen",sequenceName = "order_part_id_seq")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column
    private BigDecimal totalCost;
    @ManyToOne
    @JoinColumn(name="area_id")
    private Area area;
    @Column(unique = true)
    private String visibleID;


    public OrderPart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = MyDateUtil.setToFirstDayOfMonth(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = MyDateUtil.setToLastDayOfMonth(endDate);
    }

    public BigDecimal getTotalCost() {
        if (totalCost!=null)
            return totalCost;
        totalCost= area.getRent().multiply(new BigDecimal(MyDateUtil.monthsDiff(startDate,endDate)));
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getVisibleID() {
        return visibleID;
    }

    public void setVisibleID(String visibleID) {
        this.visibleID = visibleID;
    }
}
