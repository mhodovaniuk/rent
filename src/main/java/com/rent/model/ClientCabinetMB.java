package com.rent.model;

import com.rent.dao.OrderDAO;
import com.rent.dao.PaymentDAO;
import com.rent.entity.Order;
import com.rent.entity.OrderPart;
import com.rent.entity.Payment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mykhailo on 1/6/14.
 */
@Named
@RequestScoped
public class ClientCabinetMB implements Serializable{
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

    public void setPaymentDAO(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public BigDecimal getAcceptedPaymentsSum(Order order){
        BigDecimal res = orderDAO.findAcceptedPaymentsSum(order.getId());
        return res==null?BigDecimal.ZERO:res;
    }

    public BigDecimal getTotalCost(Order order){
        BigDecimal res = orderDAO.findTotalCost(order.getId());
        return res==null?BigDecimal.ZERO:res;
    }

    public void addPayment(Order order) {
        newPayment.setOrder(order);
        paymentDAO.persist(newPayment);
    }
}
