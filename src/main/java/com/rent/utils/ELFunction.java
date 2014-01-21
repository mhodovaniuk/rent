package com.rent.utils;

import com.rent.converter.MyDateConverter;

import java.util.Date;

/**
 * Created by mykhailo on 1/12/14.
 */
public final class ELFunction {
    private ELFunction(){}
    public static String formatDate(Object o){
        return MyDateUtil.dateToString(o);
    }
}
