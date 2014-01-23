package com.rent.validator;

import com.rent.dao.UserDAO;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Created by mykhailo on 1/2/14.
 */
@ManagedBean
@RequestScoped
public class EmailValidator implements Validator {
    @EJB
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String email=o.toString();
        if (email==null || email.isEmpty())
            return;
        if (!email.matches("[a-zA-Z][A-Za-z0-9._]*@[a-z]+([.][a-z]*)+")){
            String messageText= ResourceBundle.getBundle("i18n/texts", facesContext.getViewRoot().getLocale()).getString("emailRegexpERR");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"",messageText));
        }
        if (userDAO.isExistsUserWithEmail(email)) {
            String messageText= ResourceBundle.getBundle("i18n/texts", facesContext.getViewRoot().getLocale()).getString("emailInUseERR");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"",messageText));
        }
    }
}