package com.rent.model;

import com.rent.dao.AreaDAO;
import com.rent.dto.SearchDTO;
import com.rent.entity.Area;
import com.rent.entity.OrderPart;
import com.rent.utils.I18nBundleUtil;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


@ManagedBean
@ViewScoped
public class RentMB implements Serializable {
    @EJB
    private AreaDAO areaDAO;
    @Inject
    private CartMB cartMB;
    private OrderPart orderPart;
    public SearchDTO getSearchDTO() {
        return searchDTO;
    }

    public OrderPart getOrderPart() {
        return orderPart;
    }

    public void setOrderPart(OrderPart orderPart) {
        this.orderPart = orderPart;
    }

    public void setSearchDTO(SearchDTO searchDTO) {
        this.searchDTO = searchDTO;
    }

    private SearchDTO searchDTO;
    private LazyDataModel<Area> dataModel;

    @PostConstruct
    public void init() {
        orderPart=new OrderPart();
        searchDTO = new SearchDTO();
        dataModel =new LazyDataModel<Area>() {
            @Override
            public List<Area> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                setRowCount(areaDAO.findAreasCount(searchDTO));
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
    public void checkDTO(){
        if (searchDTO.getRealFromFloor()>searchDTO.getRealToFloor() || searchDTO.getRealFromRent().compareTo(searchDTO.getRealToRent())>0 || searchDTO.getRealFromSquare()>searchDTO.getRealToSquare()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"", I18nBundleUtil.get("fromBiggerToERR",FacesContext.getCurrentInstance().getViewRoot().getLocale())));
        }
    }
    public void doChooseArea(Area area) {
        //System.out.println(area);
        orderPart.setArea(area);
    }

    public void doAddOrderPartToCart(){
        cartMB.doVerifyAndAddOrderPart(orderPart);
        orderPart=new OrderPart();
    }
}
