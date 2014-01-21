package com.rent.converter;

import com.rent.utils.MyDateUtil;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mykhailo on 1/10/14.
 */
@FacesConverter("myDateConverter")
public class MyDateConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
            return MyDateUtil.stringToDate(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
            return MyDateUtil.dateToString(o);
    }
}
