package com.rent.dao;

import com.rent.entity.Order;
import com.rent.entity.OrderPart;
import com.rent.utils.IndentifierGenerator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mykhailo on 1/6/14.
 */
@Stateless
@LocalBean
public class OrderDAO extends BaseDAO<Order> {
    @PersistenceContext(unitName = "PostgreSQLPU")
    private EntityManager em;

    public OrderDAO() {
        super(Order.class);
    }

    @Override
    public Order persist(Order e) {
        setVisibleID(e,"Order.isExistsWithSuchID");
        for (OrderPart part:e.getOrderParts())
            setVisibleID(part,"OrderPart.isExistsWithSuchID");
        return super.persist(e);
    }



    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Order> findUserOrders(Long userId){
        TypedQuery<Order> query=em.createNamedQuery("Order.findUserOrders",Order.class);
        List<Order> orders = query.setParameter("userId", userId).getResultList();
        for (Order o:orders){
            o.getOrderParts().size();
            o.getPayments().size();
        }
        return orders;
    }

    public BigDecimal findAcceptedPaymentsSum(Long orderId){
        TypedQuery<BigDecimal> query=em.createNamedQuery("Order.findAcceptedPaymentsSum",BigDecimal.class);
        return query.setParameter("orderId",orderId).getSingleResult();
    }

    public BigDecimal findTotalCost(Long orderId) {
        TypedQuery<BigDecimal> query=em.createNamedQuery("Order.findTotalCost",BigDecimal.class);
        return query.setParameter("orderId",orderId).getSingleResult();
    }
}
