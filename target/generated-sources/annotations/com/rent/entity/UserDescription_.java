package com.rent.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDescription.class)
public abstract class UserDescription_ {

	public static volatile SingularAttribute<UserDescription, Long> id;
	public static volatile SingularAttribute<UserDescription, String> phone;
	public static volatile SingularAttribute<UserDescription, String> details;
	public static volatile SingularAttribute<UserDescription, Address> address;
	public static volatile SingularAttribute<UserDescription, String> contactPersonName;
	public static volatile SingularAttribute<UserDescription, String> companyName;
	public static volatile SingularAttribute<UserDescription, User> user;

}

