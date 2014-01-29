package com.rent.model;

import com.rent.dao.OrderDAO;
import com.rent.dao.PaymentDAO;
import com.rent.entity.Order;
import com.rent.entity.Payment;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by mykhailo on 1/6/14.
 */
@ManagedBean
@ViewScoped
public class ClientCabinetMB implements Serializable{
    private Order selectedOrder;

    public Order getSelectedOrder() {
      //  System.out.println("get " + this + selectedOrder);
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
      //  System.out.println("    set " + this + selectedOrder);
        this.selectedOrder = selectedOrder;
    }

    @Inject
    private UserMB userMB;
    @EJB
    private OrderDAO orderDAO;
    @EJB
    private PaymentDAO paymentDAO;
    private Payment newPayment;

    public Payment getNewPayment() {
        return newPayment;
    }

    public void setNewPayment(Payment newPayment) {
        this.newPayment = newPayment;
    }
    @PostConstruct
    public void init(){
        createNewPayment();
    }
    private void createNewPayment(){
        newPayment=new Payment();
    }
    public List<Order> getUserOrders(){
        return orderDAO.findUserOrders(userMB.getUser().getId());
    }

    public BigDecimal getAcceptedPaymentsSum(Order order){
        BigDecimal res = orderDAO.findAcceptedPaymentsSum(order.getId());
        return res==null?BigDecimal.ZERO:res;
    }

    public BigDecimal getTotalCost(Order order){
        BigDecimal res = orderDAO.findTotalCost(order.getId());
        return res==null?BigDecimal.ZERO:res;
    }

    public void doVerifyAndAddPayment() {
        RequestContext requestContext = RequestContext.getCurrentInstance();

        String added = ResourceBundle.getBundle("i18n/texts", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("addedPayment");
        FacesMessage msg =new FacesMessage(FacesMessage.SEVERITY_INFO,"",added);
        newPayment.setOrder(selectedOrder);
        paymentDAO.persist(newPayment);
        newPayment=new Payment();

        FacesContext.getCurrentInstance().addMessage(null, msg);

        requestContext.addCallbackParam("addedStatus", true);

    }


}
