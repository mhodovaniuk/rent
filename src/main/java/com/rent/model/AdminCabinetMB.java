package com.rent.model;

import com.rent.dao.PaymentDAO;
import com.rent.entity.Payment;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mykhailo on 1/14/14.
 */
@ManagedBean
@ViewScoped
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
