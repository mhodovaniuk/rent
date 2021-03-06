package com.rent.model;


import com.rent.dao.AreaDAO;
import com.rent.dao.OrderDAO;
import com.rent.entity.Area;
import com.rent.entity.Order;
import com.rent.entity.OrderPart;
import com.rent.utils.I18nBundleUtil;
import com.rent.utils.MyDateUtil;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by mykhailo on 1/4/14.
 */
@Named
@SessionScoped
public class CartMB implements Serializable {
    //fields
    @EJB
    private OrderDAO orderDAO;
    @EJB
    private AreaDAO areaDAO;
    @Inject
    private UserMB userMB;

    private Order order;


    public Order getOrder() {
        return order;
    }

    @PostConstruct
    public void refresh() {
        createNewOrder();

    }

    private void createNewOrder() {
        order = new Order();
        order.setUser(userMB.getUser());
    }


    public boolean doVerifyAndAddOrderPart(OrderPart orderPart) {
        orderPart.setOrder(order);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean addedStatus = true;
        String areaInCartERR = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("areaInCartERR");
        String datesERR = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("datesERR");
        String added = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("addedArea");
        String error = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("error");
        String warning = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("warning");
        String orderPartLenERR = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("orderPartLenERR");
        String goToCart=I18nBundleUtil.get("goToCart",FacesContext.getCurrentInstance().getViewRoot().getLocale());
        if ( orderContainsArea(orderPart.getArea())) {
            addedStatus = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, areaInCartERR);
        }

        if (addedStatus && !MyDateUtil.checkDates(Calendar.getInstance(), orderPart.getStartDate(), orderPart.getEndDate())) {
            addedStatus = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, warning, datesERR);
        }

        if (addedStatus && MyDateUtil.monthsDiff(orderPart.getStartDate(), orderPart.getEndDate()) < 3) {
            addedStatus = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, warning, orderPartLenERR);
        }

        if (addedStatus) {
            order.getOrderParts().add(orderPart);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, added, goToCart);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        requestContext.addCallbackParam("addedStatus", addedStatus);
        return addedStatus;
    }

    private boolean orderContainsArea(Area area) {
        if (area == null)
            return false;
        for (OrderPart part : order.getOrderParts())
            if (part.getArea().getId().equals(area.getId()))
                return true;
        return false;
    }

    public void doRemoveOrderPartFromOrder(OrderPart part) {
        order.getOrderParts().remove(part);
    }

    public String doCompleteOrder() {
        FacesMessage msg = null;
        String msgStr = null;
        FacesMessage.Severity severity = null;
        String removeAreas=I18nBundleUtil.get("removeAreas",FacesContext.getCurrentInstance().getViewRoot().getLocale());
        String error = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("error");
        ArrayList<String> areasList=new ArrayList<String>();
        for (OrderPart p:order.getOrderParts())
            if (!areaDAO.isAreaFree(p.getArea().getId(),p.getStartDate(),p.getEndDate()))
                areasList.add(p.getArea().getNumber());
        if (areasList.size()!=0){
            removeAreas+=areasList.get(0);
            for (int i = 1; i < areasList.size(); i++) {
                removeAreas+=", "+areasList.get(i);
            }
            msg=new FacesMessage(FacesMessage.SEVERITY_WARN,error,removeAreas);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "cart";
        }
        if (order.getOrderParts().size() != 0) {
            msgStr= ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("orderAdded");
            severity=FacesMessage.SEVERITY_INFO;
            order.setCreatingDate(Calendar.getInstance());
            orderDAO.persist(order);
            refresh();
        } else {
            msgStr=ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("orderEmptyERR");
            severity=FacesMessage.SEVERITY_WARN;
        }
        msg=new FacesMessage(severity,"",msgStr);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        return "rent";
    }

    public BigDecimal getCost() {
        BigDecimal cost = BigDecimal.ZERO;
        for (OrderPart part : order.getOrderParts())
            cost = cost.add(part.getTotalCost());
        return cost;
    }



}
