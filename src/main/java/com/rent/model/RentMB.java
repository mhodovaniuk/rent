package com.rent.model;

import com.rent.dao.AreaDAO;
import com.rent.dto.SearchDTO;
import com.rent.entity.Area;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Named
@SessionScoped
public class RentMB implements Serializable {
    @EJB
    private AreaDAO areaDAO;

    public SearchDTO getSearchDTO() {
        return searchDTO;
    }

    public void setSearchDTO(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    private SearchDTO searchDTO;
    private LazyDataModel<Area> dataModel;

    @PostConstruct
    public void init() {
        searchDTO = new SearchDTO();
        dataModel =new LazyDataModel<Area>() {
            @Override
            public List<Area> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                return areaDAO.search(first,pageSize,searchDTO);
            }
        };
        dataModel.setRowCount((int) areaDAO.findAvailableAreasCount());
    }

    public RentMB() {
    }

    public LazyDataModel<Area> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<Area> dataModel) {
        this.dataModel = dataModel;
    }
}
