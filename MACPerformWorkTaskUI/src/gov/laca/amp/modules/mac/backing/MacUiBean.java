package gov.laca.amp.modules.mac.backing;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import gov.laca.amp.common.view.backing.CommonAggregateBean;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.RegionNavigationEvent;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class MacUiBean {
    private RichPanelGroupLayout performNavPgl;
    private RichGridRow assesseeForm1;
    private RichGridRow assesseeForm2;
    private RichPanelGroupLayout addressValidnForm;
    private RichPanelGroupLayout checkboxInvestigation;
    private RichSelectBooleanCheckbox itusAddrInvestigation;
    private RichSelectBooleanCheckbox mailingSitusAddr;
    private RichPanelGroupLayout notesLayout;
    private RichInputText ainNotes;
    private RichPanelGroupLayout remarksLayout;
    private RichInputText ainRemarks;
    private RichPopup selectAssesseePopUP;
    private RichTable assesseetable;
    private RichPopup aggregatePopup;
    private RichPopup editAggregatePopUp;
    private RichPanelGroupLayout refPgl;
    private RichButton aggrSubmitBtn;
    private static ADFLogger _log = ADFLogger.createADFLogger(MacUiBean.class);
    private RichGridRow assesseeForm3;

    public MacUiBean() {
    }

    public void setPerformNavPgl(RichPanelGroupLayout performNavPgl) {
        this.performNavPgl = performNavPgl;
    }

    public RichPanelGroupLayout getPerformNavPgl() {
        return performNavPgl;
    }

    public void setAssesseeForm1(RichGridRow assesseeForm1) {
        this.assesseeForm1 = assesseeForm1;
    }

    public RichGridRow getAssesseeForm1() {
        return assesseeForm1;
    }

    public void setAssesseeForm2(RichGridRow assesseeForm2) {
        this.assesseeForm2 = assesseeForm2;
    }

    public RichGridRow getAssesseeForm2() {
        return assesseeForm2;
    }

    public void setAddressValidnForm(RichPanelGroupLayout addressValidnForm) {
        this.addressValidnForm = addressValidnForm;
    }

    public RichPanelGroupLayout getAddressValidnForm() {
        return addressValidnForm;
    }

    public void setCheckboxInvestigation(RichPanelGroupLayout checkboxInvestigation) {
        this.checkboxInvestigation = checkboxInvestigation;
    }

    public RichPanelGroupLayout getCheckboxInvestigation() {
        return checkboxInvestigation;
    }

    public void setItusAddrInvestigation(RichSelectBooleanCheckbox itusAddrInvestigation) {
        this.itusAddrInvestigation = itusAddrInvestigation;
    }

    public RichSelectBooleanCheckbox getItusAddrInvestigation() {
        return itusAddrInvestigation;
    }

    public void setMailingSitusAddr(RichSelectBooleanCheckbox mailingSitusAddr) {
        this.mailingSitusAddr = mailingSitusAddr;
    }

    public RichSelectBooleanCheckbox getMailingSitusAddr() {
        return mailingSitusAddr;
    }

    public void setNotesLayout(RichPanelGroupLayout notesLayout) {
        this.notesLayout = notesLayout;
    }

    public RichPanelGroupLayout getNotesLayout() {
        return notesLayout;
    }

    public void setAinNotes(RichInputText ainNotes) {
        this.ainNotes = ainNotes;
    }

    public RichInputText getAinNotes() {
        return ainNotes;
    }

    public void setRemarksLayout(RichPanelGroupLayout remarksLayout) {
        this.remarksLayout = remarksLayout;
    }

    public RichPanelGroupLayout getRemarksLayout() {
        return remarksLayout;
    }

    public void setAinRemarks(RichInputText ainRemarks) {
        this.ainRemarks = ainRemarks;
    }

    public RichInputText getAinRemarks() {
        return ainRemarks;
    }

    public void setSelectAssesseePopUP(RichPopup selectAssesseePopUP) {
        this.selectAssesseePopUP = selectAssesseePopUP;
    }

    public RichPopup getSelectAssesseePopUP() {
        return selectAssesseePopUP;
    }

    public void setAssesseetable(RichTable assesseetable) {
        this.assesseetable = assesseetable;
    }

    public RichTable getAssesseetable() {
        return assesseetable;
    }

    //MacUiBean.getValueFrmExpression("#{macUiBean.assesseetable}")
    public static Object getValueFrmExpression(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
        return valueExp.getValue(elContext);
    }

    public void setAggregatePopup(RichPopup aggregatePopup) {
        this.aggregatePopup = aggregatePopup;
    }

    public RichPopup getAggregatePopup() {
        return aggregatePopup;
    }

    public void showEditAggrPopUp(ActionEvent actionEvent) {
        // Add event code here...
        Map tfMap = AdfFacesContext.getCurrentInstance().getViewScope();
        CommonAggregateBean commAggBean = (CommonAggregateBean) tfMap.get("aggregate");
        if (commAggBean == null) {
            commAggBean = new CommonAggregateBean();
            commAggBean.setIsRefresh(Boolean.TRUE);

        }
        tfMap.put("aggregate", commAggBean);   

        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getEditAggregatePopUp().show(hints);

    }

    public void setEditAggregatePopUp(RichPopup editAggregatePopUp) {
        this.editAggregatePopUp = editAggregatePopUp;
    }

    public RichPopup getEditAggregatePopUp() {
        return editAggregatePopUp;
    }

    public void setRefPgl(RichPanelGroupLayout refPgl) {
        this.refPgl = refPgl;
    }

    public RichPanelGroupLayout getRefPgl() {
        return refPgl;
    }

    public void aggrCloseListener(RegionNavigationEvent regionNavigationEvent) {
        _log.fine("new view id.."+regionNavigationEvent.getNewViewId());
        if(regionNavigationEvent.getNewViewId() == null || "hidep".equalsIgnoreCase(regionNavigationEvent.getNewViewId())) {
            getAggregatePopup().hide();
            
            ExtendedRenderKitService service = Service.getRenderKitService(FacesContext.getCurrentInstance(), ExtendedRenderKitService.class);
            if(getAggrSubmitBtn() != null) {
                String clientCompId = getAggrSubmitBtn().getClientId(FacesContext.getCurrentInstance());
                _log.fine("button client id.. "+clientCompId);
                StringBuffer clickBtn = new StringBuffer();
                clickBtn.append("var prfBtn=AdfPage.PAGE.findComponentByAbsoluteId('" + clientCompId + "'); AdfActionEvent.queue(prfBtn,true);");
                service.addScript(FacesContext.getCurrentInstance(), clickBtn.toString());
            }
            
        }
    }

    public void setAggrSubmitBtn(RichButton aggrSubmitBtn) {
        this.aggrSubmitBtn = aggrSubmitBtn;
    }

    public RichButton getAggrSubmitBtn() {
        return aggrSubmitBtn;
    }

    public void setAssesseeForm3(RichGridRow assesseeForm3) {
        this.assesseeForm3 = assesseeForm3;
    }

    public RichGridRow getAssesseeForm3() {
        return assesseeForm3;
    }
}
