package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderPart.class)
public abstract class OrderPart_ {

	public static volatile SingularAttribute<OrderPart, Long> id;
	public static volatile SingularAttribute<OrderPart, Date> startDate;
	public static volatile SingularAttribute<OrderPart, Area> area;
	public static volatile SingularAttribute<OrderPart, BigDecimal> totalCost;
	public static volatile SingularAttribute<OrderPart, Order> order;
	public static volatile SingularAttribute<OrderPart, Date> endDate;

}

