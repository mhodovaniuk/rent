package com.rent.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * Created by mykhailo on 12/19/13.
 */
public abstract class BaseDAO<T> {
    private Class<T> entityClass;

    protected BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T persist(T e){
        getEntityManager().persist(e);

        return e;
    }

    public T update(T e){
        getEntityManager().merge(e);
        return e;
    }

    public T remove(T e){
        getEntityManager().remove(e);
        return e;
    }

    public T find(Long id){
        return getEntityManager().find(entityClass,id);
    }
}
