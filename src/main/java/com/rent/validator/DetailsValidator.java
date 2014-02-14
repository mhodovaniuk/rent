package com.rent.validator;

import com.rent.utils.I18nBundleUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Mykhailo_Hodovaniuk on 2/10/14.
 */
@FacesValidator("detailsValidator")
public class DetailsValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String details=(String)value;
        int maxLength = Collections.max(Arrays.asList(details.split(" ")), new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }).length();
        if (maxLength>20){
            String messageText= I18nBundleUtil.get("detailsERR", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
        }
    }
}
