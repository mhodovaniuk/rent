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

    public User getRegDTOUser() {
        return regDTOUser;
    }

    public void setRegDTOUser(User regDTOUser) {
        this.regDTOUser = regDTOUser;
    }

    //param
    private User regDTOUser;
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
        regDTOUser=new User();
        setLocale(DEFAULT_LOCALE);
    }

    public String doRegister() {
        userDAO.persist(regDTOUser);
        regDTOUser=new User();
        String messageText= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("regMSG");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messageText));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "home";
    }

    public String doLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            request.login(userEmail, userPassword);
            user=userDAO.findByEmail(userEmail);
            userEmail=null;
            userPassword=null;
            return "home";
        } catch (ServletException e) {
            String messageText= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("loginERR");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",messageText));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            userPassword=null;
            return "login";
        }
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)
                context.getExternalContext().getRequest();
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            request.logout();
            user=null;
            return "login";
        } catch (ServletException e) {
            String messageText= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("logoutERR");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",messageText));
            return "home";
        }
    }

    public String doChangeLanguage(String languageCode){
        if (LOCALES.containsKey(languageCode))
            setLocale(LOCALES.get(languageCode));
        return FacesContext.getCurrentInstance().getViewRoot().getViewId() ;
    }
}