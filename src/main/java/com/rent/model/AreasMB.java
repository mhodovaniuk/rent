package com.rent.model;

import com.rent.dao.AreaDAO;
import com.rent.entity.Area;
import com.rent.utils.I18nBundleUtil;
import com.rent.utils.MyDateUtil;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Named
@RequestScoped
public class AreasMB implements Serializable {

    @EJB
    private AreaDAO areaDAO;
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "{regexpERR}")
    private String areaNumber;

    public LazyDataModel<Area> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<Area> dataModel) {
        this.dataModel = dataModel;
    }

    private LazyDataModel<Area> dataModel;

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDataModel<Area>() {
            private List<Area> prev;
            @Override
            public List<Area> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                if (areaNumber == null)
                    areaNumber = "";
                areaNumber=areaNumber.replaceAll("[^a-zA-Z0-9]","");
                setRowCount(areaDAO.searchSize(areaNumber));
                return areaDAO.search(first, pageSize, areaNumber);
            }
        };
    }

    private boolean checkAreaNumber(String areaNumber) {
        return !areaNumber.matches("[a-zA-Z0-9]") || "".equals(areaNumber);

    }

    public AreasMB() {
    }

    public String getGetLastRentMonth(Area area) {
        Date lastRentDate = areaDAO.findLastRentDate(area.getId());
        if (lastRentDate == null)
            return "";
        else return MyDateUtil.dateToString(lastRentDate);

    }

    public void cleanAreaNumber() {
        areaNumber = null;
    }
}
