package com.rent.model;

import com.rent.dao.PaymentDAO;
import com.rent.entity.Payment;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mykhailo on 1/14/14.
 */
@Named
@SessionScoped
public class AdminCabinetMB implements Serializable {
    private List<Payment> payments;

    public List<Payment> getPayments() {
        return payments;
    }

    @EJB
    private PaymentDAO paymentDAO;
    public List<Payment> getNotAcceptedPayments(){
        payments=paymentDAO.findAllNotAcceptedPayments();
        return payments;
    }

    public void acceptPayments(){
        for (Payment payment:payments)
            paymentDAO.update(payment);
    }
}
