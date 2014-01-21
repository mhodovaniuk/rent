package com.rent.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * User: mychajlo
 * Date: 12/4/13
 * Time: 9:45 PM
 */
@Entity
@Table(name="Area")
@NamedQueries({
    @NamedQuery(name = "Area.findAllAvailable",query = "select a from Area a where not exists(select p from OrderPart p where p.area.id=a.id and CURRENT_DATE < p.endDate)"),
    @NamedQuery(name = "Area.search",query = "select a from Area a where a.floor>=:fromFloor and a.floor<=:toFloor and a.square>=:fromSquare and a.square<=:toSquare and a.rent>=:fromRent and a.rent<=:toRent and (a.airCondition=:aircondition or :aircondition is NULL) and not exists(select p from OrderPart p where p.area.id=a.id and CURRENT_DATE < p.endDate)"),
    @NamedQuery(name="Area.findAll",query = "select a from Area a"),
    @NamedQuery(name="Area.finByNumber",query="select a from Area a where a.number=:areaNumber"),
    @NamedQuery(name="Area.finLastRentDate",query="select max(p.endDate) from OrderPart p where p.area.id=:areaId and p.endDate>current_date"),
    @NamedQuery(name="Area.findAvailableAreasCount",query="select count(a) from Area a where not exists(select p from OrderPart p where p.area.id=a.id and CURRENT_DATE < p.endDate)"),
    @NamedQuery(name="Area.findAreasCount",query="select count(a) from Area a")
})

public class Area implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "area_id_seq_gen")
    @SequenceGenerator(name = "area_id_seq_gen",sequenceName = "area_id_seq")
    private Long id;
    @Column(nullable = false)
    private int floor;
    @Column (nullable = false)
    private double square;
    @Column(nullable = false)
    private boolean airCondition;
    @Column(nullable = false)
    private BigDecimal rent;
    @Column
    private String imagePath;
    @Column(unique = true, nullable = false)
    private String number;

    public Area() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imageName) {
        this.imagePath = imageName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
