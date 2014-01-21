package com.rent.dao;

import com.rent.entity.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

/**
 * Created by mykhailo on 12/19/13.
 */
@Stateless
@LocalBean
public class UserDAO extends BaseDAO<User> {

    @PersistenceContext(unitName = "PostgreSQLPU")
    private EntityManager em;

    public UserDAO() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public User findByEmail(String email){
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail",User.class);
        List<User> result=query.setParameter("email",email).getResultList();
        if (result.isEmpty())
            return null;
        else return result.get(0);
    }


    public boolean isExistsUserWithEmail(String email){
        return findByEmail(email)!=null;
    }
}
