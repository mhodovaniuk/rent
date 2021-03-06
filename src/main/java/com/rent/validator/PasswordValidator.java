package com.rent.validator;

import com.rent.utils.I18nBundleUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by mykhailo on 1/1/14.
 */
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String password=(String)o;
        UIInput uiPassword2 = (UIInput) uiComponent.getAttributes().get("password2");
        String password2=(String)uiPassword2.getSubmittedValue();
        String messageText;
        if (password==null || password.isEmpty() || password2==null || password2.isEmpty())
            return;
        if (password.length()<6 || password.length()>16){
            uiPassword2.setValid(false);
            messageText=I18nBundleUtil.get("passwordLengthERR",facesContext.getViewRoot().getLocale());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
        }
        if (!password.matches("[a-zA-Z0-9]*")){
            uiPassword2.setValid(false);
            messageText= I18nBundleUtil.get("passwordPatterERR",facesContext.getViewRoot().getLocale());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
        }

        if (!password.equals(password2)){
            uiPassword2.setValid(false);
            messageText=I18nBundleUtil.get("passwordsMatchERR",facesContext.getViewRoot().getLocale());
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
        }
    }
}