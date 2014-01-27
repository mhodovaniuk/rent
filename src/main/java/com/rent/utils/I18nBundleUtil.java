package com.rent.utils;

import javax.faces.context.FacesContext;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Mykhailo_Hodovaniuk on 1/27/14.
 */
public class I18nBundleUtil {
    private I18nBundleUtil(){}
    public static String get(String key, Locale locale){
        return  ResourceBundle.getBundle("i18n/texts", locale).getString(key);
    }
}
