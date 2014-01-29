package com.rent.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public  class IndentifierGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static String nextId() {
        return new BigInteger(66, random).toString(32);
    }
}