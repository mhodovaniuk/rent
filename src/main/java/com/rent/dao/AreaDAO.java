package com.rent.dao;

import com.rent.dto.SearchDTO;
import com.rent.entity.Area;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Stateless
@LocalBean
public class AreaDAO extends BaseDAO<Area> {
    @PersistenceContext(unitName = "PostgreSQLPU")
    private EntityManager em;

    public AreaDAO() {
        super(Area.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Area> findAllAvailable() {
        TypedQuery<Area> query = em.createNamedQuery("Area.findAllAvailable", Area.class);

        return query.getResultList();
    }

    public List<Area> search(int first, int pageSize, SearchDTO dto) {
        TypedQuery<Area> query = em.createNamedQuery("Area.search", Area.class);
        return query.setParameter("fromFloor", dto.getRealFromFloor()).setParameter("toFloor", dto.getRealToFloor())
                .setParameter("fromSquare", dto.getRealFromSquare()).setParameter("toSquare", dto.getRealToSquare())
                .setParameter("fromRent", dto.getRealFromRent()).setParameter("toRent", dto.getRealToRent())
                .setParameter("aircondition", dto.getRealAircondition()).setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    public List<Area> findAll(int first, int pageSize) {
        TypedQuery<Area> query =em.createNamedQuery("Area.findAll", Area.class);
        return query.setFirstResult(first).setMaxResults(pageSize).getResultList();
    }

    public List<Area> search(String areaNumber) {
        TypedQuery<Area> query =em.createNamedQuery("Area.finByNumber", Area.class);
        return query.setParameter("areaNumber", areaNumber).getResultList();
    }

    public Date findLastRentDate(Long areaId) {
        TypedQuery<Date> query =em.createNamedQuery("Area.finLastRentDate", Date.class);
        return query.setParameter("areaId", areaId).getSingleResult();
    }

    public long findAvailableAreasCount() {
        return em.createNamedQuery("Area.findAvailableAreasCount",Long.class).getSingleResult();
    }

    public long findAreasCount(){
        return em.createNamedQuery("Area.findAreasCount",Long.class).getSingleResult();
    }

    public int findAreasCount(SearchDTO dto) {
        TypedQuery<Long> query = em.createNamedQuery("Area.findSearchedAreasCount", Long.class);
        return query.setParameter("fromFloor", dto.getRealFromFloor()).setParameter("toFloor", dto.getRealToFloor())
                .setParameter("fromSquare", dto.getRealFromSquare()).setParameter("toSquare", dto.getRealToSquare())
                .setParameter("fromRent", dto.getRealFromRent()).setParameter("toRent", dto.getRealToRent())
                .setParameter("aircondition", dto.getRealAircondition()).getSingleResult().intValue();
    }
}
