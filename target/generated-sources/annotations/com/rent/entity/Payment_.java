package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Payment.class)
public abstract class Payment_ {

	public static volatile SingularAttribute<Payment, Long> id;
	public static volatile SingularAttribute<Payment, Order> order;
	public static volatile SingularAttribute<Payment, String> details;
	public static volatile SingularAttribute<Payment, Boolean> accepted;
	public static volatile SingularAttribute<Payment, BigDecimal> value;
	public static volatile SingularAttribute<Payment, Date> date;

}

