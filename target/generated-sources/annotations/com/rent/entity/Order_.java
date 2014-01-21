package com.rent.entity;

import java.util.Calendar;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, Calendar> creatingDate;
	public static volatile SingularAttribute<Order, String> details;
	public static volatile ListAttribute<Order, Payment> payments;
	public static volatile SingularAttribute<Order, User> user;
	public static volatile ListAttribute<Order, OrderPart> orderParts;

}

