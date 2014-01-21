package com.rent.model;


import com.rent.dao.UserDAO;
import com.rent.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Named
@SessionScoped
public class UserMB implements Serializable {
    private static final Locale DEFAULT_LOCALE=Locale.forLanguageTag("ua");
    private static final Map<String,Locale> LOCALES =new HashMap<String, Locale>();
    //param
    private String userEmail;
    private String userPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }
    //fields
    private User user;
    private Locale locale;
    @EJB
    private UserDAO userDAO;

    static {
        LOCALES.put("EN", Locale.ENGLISH);
        LOCALES.put("UA", DEFAULT_LOCALE);
    }

    public UserMB() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale=locale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    @PostConstruct
    public void init(){
        user=new User();
        setLocale(DEFAULT_LOCALE);
    }

    public String doRegister() {
        userDAO.persist(user);
        user=null;
        return "login";
    }

    public String doLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.login(userEmail, userPassword);
            user=userDAO.findByEmail(userEmail);
        } catch (ServletException e) {
            String messageText= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("loginERR");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
            return "login";
        }
        return "home";
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.logout();
            user=null;

            return "login";
        } catch (ServletException e) {
            String messageText= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("logoutERR");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,messageText,messageText));
            return "home";
        }
    }

    public String doChangeLanguage(String languageCode){
        if (LOCALES.containsKey(languageCode))
            setLocale(LOCALES.get(languageCode));
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() + "?faces-redirect=true";
    }
}
