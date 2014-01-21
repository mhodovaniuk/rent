package com.rent.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile SingularAttribute<User, UserDescription> userDescription;
	public static volatile SingularAttribute<User, String> password;

}

