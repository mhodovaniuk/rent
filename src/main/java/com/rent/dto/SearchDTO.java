package com.rent.dto;

import java.math.BigDecimal;

public class SearchDTO {
    public static final int WITH=0,WITHOUT=1,DONT_CARE=3;
    private static final Integer MAX_FLOOR = Integer.MAX_VALUE;
    private static final Double MAX_SQUARE = (double) Integer.MAX_VALUE;
    private static final BigDecimal MAX_RENT = new BigDecimal(MAX_SQUARE);
    private Integer fromFloor;
    private Integer toFloor;
    private Double fromSquare;
    private Double toSquare;
    private BigDecimal fromRent;
    private BigDecimal toRent;
    private int aircondition=DONT_CARE;

    public SearchDTO() {
    }

    public Integer getFromFloor() {
        return null;
    }

    public void setFromFloor(Integer fromFloor) {

        this.fromFloor = fromFloor;
    }

    public Integer getRealToFloor(){
        if (toFloor == null || toFloor ==0)
            return MAX_FLOOR;
        return toFloor;
    }
    public Boolean getRealAircondition(){
        switch (aircondition){
            case WITH:
                return true;
            case WITHOUT:
                return false;
            default:
                return null;
        }
    }

    public Double getRealToSquare(){
        if (toSquare == null || Math.abs(toSquare) < 0.001)
            return MAX_SQUARE;
        return toSquare;
    }

    public Integer getToFloor() {

        return null;
    }

    public void setToFloor(Integer toFloor) {
        this.toFloor = toFloor;
    }

    public Double getFromSquare() {
        return null;
    }

    public void setFromSquare(Double fromSquare) {
        this.fromSquare = fromSquare;
    }

    public Double getToSquare() {
        return null;
    }

    public void setToSquare(Double toSquare) {
        this.toSquare = toSquare;
    }

    public BigDecimal getFromRent() {
        return null;
    }

    public void setFromRent(BigDecimal fromRent) {
        this.fromRent = fromRent;
    }



    public BigDecimal getToRent() {
        return null;
    }

    public BigDecimal getRealToRent() {
        if (toRent == null || toRent.intValue() == 0)
            return MAX_RENT;
        return toRent;
    }

    public void setToRent(BigDecimal toRent) {
        this.toRent = toRent;
    }

    public int getAircondition() {
        return aircondition;
    }

    public void setAircondition(int aircondition) {
        this.aircondition = aircondition;
    }

    public BigDecimal getRealFromRent() {
        return fromRent==null?BigDecimal.ZERO:fromRent;
    }

    public Double getRealFromSquare() {
        return fromSquare==null?0d:fromSquare;
    }

    public Integer getRealFromFloor() {
        return fromFloor==null?0:fromFloor;
    }
}
