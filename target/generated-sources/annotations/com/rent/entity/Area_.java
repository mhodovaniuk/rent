package com.rent.entity;

import java.math.BigDecimal;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Area.class)
public abstract class Area_ {

	public static volatile SingularAttribute<Area, Long> id;
	public static volatile SingularAttribute<Area, Integer> floor;
	public static volatile SingularAttribute<Area, String> imagePath;
	public static volatile SingularAttribute<Area, BigDecimal> rent;
	public static volatile SingularAttribute<Area, String> number;
	public static volatile SingularAttribute<Area, Boolean> airCondition;
	public static volatile SingularAttribute<Area, Double> square;

}

