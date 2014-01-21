package com.rent.dao;

import com.rent.entity.Payment;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mykhailo on 1/12/14.
 */
@Stateless
@LocalBean
public class PaymentDAO extends BaseDAO<Payment> {
    @PersistenceContext(unitName = "PostgreSQLPU")
    private EntityManager em;

    public PaymentDAO() {
        super(Payment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Payment> findAllNotAcceptedPayments(){
        return em.createNamedQuery("Payment.findAllNotAcceptedPayments",Payment.class).getResultList();
    }

}
