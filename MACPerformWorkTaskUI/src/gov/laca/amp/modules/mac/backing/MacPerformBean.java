package gov.laca.amp.modules.mac.backing;

import gov.laca.amp.ent.exception.AmpException;
import gov.laca.amp.fwk.extn.view.AmpManagedBean;
import gov.laca.amp.fwk.util.ADFUtils;
import gov.laca.amp.fwk.util.JSFUtils;
import gov.laca.amp.fwk.util.StringUtils;
import gov.laca.amp.modules.mac.pojo.AINPojo;

import oracle.adf.share.security.SecurityContext;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.StringTokenizer;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;

import oracle.adf.view.rich.component.rich.nav.RichLink;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.*;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichIterator;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.component.UIXIterator;
import org.apache.myfaces.trinidad.event.ReturnEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;

import oracle.jbo.*;

import org.apache.myfaces.trinidad.model.RowKeySet;

public class MacPerformBean extends AmpManagedBean {
  //  private RichIterator ainIterator1;
   // private RichTable assesseetable;
  //  private RichPanelGroupLayout pglAssessee;
  //  private RichPopup selectAssesseePopUP;
    public int counter = 0;
  //  private RichPanelGroupLayout navigationPgl;
    boolean remarksMandatory = false;
    boolean notesMandatory = false;
  //  private RichPanelGroupLayout checkboxInvestigation;
  //  private RichSelectBooleanCheckbox itusAddrInvestigation;
  //  private RichSelectBooleanCheckbox mailingSitusAddr;
    String viewmode = null;
    boolean checkAinStatus = false;
    private Boolean ownershipInvestigator;
  //  private RichPanelGroupLayout addressValidnForm;
  //  private RichInputText ainNotes;
  //  private RichInputText ainRemarks;
  //  private RichPanelGroupLayout remarksLayout;
  //  private RichPanelGroupLayout notesLayout;
 //   private RichPanelFormLayout performForm;
  //  DCBindingContainer bindingContainer = null;
  //  private RichPanelGroupLayout performNavPgl;
  //  private RichGridRow assesseeForm1;
  //  private RichGridRow assesseeForm2;
  //  private RichLink nextButton;
    private String retrivedMailingAddr = ",,,,";
    private String fetchedincareof =null;
    private static transient ADFLogger _log = ADFLogger.createADFLogger(MacPerformBean.class);
    private String level ;
    private static final String L1 = "Level 1";
    private static final String L2 = "Level 2";

    public void setViewmode(String viewmode) {
        this.viewmode = viewmode;
    }

    public String getViewmode() {
        return viewmode;
    }


    public MacPerformBean() {
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
//
//    public void setAinIterator1(RichIterator ainIterator1) {
//        this.ainIterator1 = ainIterator1;
//    }
//
//    public RichIterator getAinIterator1() {
//        return ainIterator1;
//    }

//    public void setAinIterator(UIXIterator ainIterator) {
//        this.ainIterator = ainIterator;
//    }
//
//    public UIXIterator getAinIterator() {
//        return ainIterator;
//    }

//   / private UIXIterator ainIterator;
//    private int rowsPerPage = 1;

//    public void firstActionListener(ActionEvent actionEvent) {
//        this.getAinIterator().setFirst(0);
//    }
//
//    public void previousActionListener(ActionEvent actionEvent) {
//        this.getAinIterator().setFirst(this.getAinIterator().getFirst() - rowsPerPage);
//    }
//
//    public void nextActionListener(ActionEvent actionEvent) {
//        this.getAinIterator().setFirst(this.getAinIterator().getFirst() + rowsPerPage);
//    }
//
//    public void lastActionListener(ActionEvent actionEvent) {
//        int totalPages = this.getAinIterator().getRowCount() / rowsPerPage;
//        int rowCount = totalPages * rowsPerPage;
//        if (rowCount == this.getAinIterator().getRowCount()) {
//            rowCount = rowCount - rowsPerPage;
//        }
//        this.getAinIterator().setFirst(rowCount);
//
//    }
//
//    public void setainIterator(UIXIterator ainIterator) {
//        this.ainIterator = ainIterator;
//    }
//
//    public void setRowsPerPage(int rowsPerPage) {
//        this.rowsPerPage = rowsPerPage;
//    }
//
//    public int getRowsPerPage() {
//        return rowsPerPage;
//    }

    public String loadAssignedPT() {
        // Add event code here...

        // GET values from the payload
      //  System.out.println(" LOGGED IN USER ROLE :::" + ADFUtils.getPageFlowStringValue("rolevalue"));

        List<AINPojo> ainList = new ArrayList<AINPojo>();
        String workUnitId = ADFUtils.getPageFlowStringValue("workUnitId");
      //  System.out.println("WORK UNIT ID " + workUnitId);

        DCBindingContainer propIdentContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        populateLevel();

        
        RowSetIterator ainIterator =  propIdentContainer.findIteratorBinding("PropertyIdentificationIterator").getViewObject().createRowSetIterator(null);
        //For SERVER

        AINPojo a1 = null;
        String primaryAin  = null;
         while (ainIterator.hasNext()) {
                Row row = ainIterator.next();
                a1 = new AINPojo();
                a1.setAin((String) row.getAttribute("AIN"));
                if (row.getAttribute("AOID") != null) {
                    Integer aoidInt = (Integer) row.getAttribute("AOID");
                    BigInteger aoidBig = BigInteger.valueOf(aoidInt.intValue());
                    a1.setAoid(aoidBig);
                }
                if(Boolean.TRUE.equals(row.getAttribute("IsPrimary"))) {
                     primaryAin = (String) row.getAttribute("AIN");
                }
             
                ainList.add(a1);
            }
         
        ainIterator.closeRowSetIterator();
        

        ADFUtils.setPageFlowValue("ainList", ainList);
        ADFUtils.setPageFlowValue("wuPriAIN", primaryAin);
        if(primaryAin == null) {
             _log.severe("No Primary AIN In BPM payload");
        }

        String rolevalue = roleVal();
       

      //  System.out.println("WUID==" + workUnitId);
        if(rolevalue != null && rolevalue.trim().length() != 0) {
            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding op = (OperationBinding) bindings.getOperationBinding("loadMacData");
            op.getParamsMap().put("workUnitId", workUnitId);
            op.getParamsMap().put("ains", ainList);
            op.getParamsMap().put("rolevalue", rolevalue);
            op.execute();

            
            DCIteratorBinding mailingAddrPojoListItr =
                (DCIteratorBinding) propIdentContainer.findIteratorBinding("mailingAddrPojoListIterator");
            mailingAddrPojoListItr.executeQuery();
            
            Row mailAddRow = mailingAddrPojoListItr.getCurrentRow();
            
            fetchedincareof= (String)mailAddRow.getAttribute("origInCareOf");
            
            //   System.out.println("FETCHED ::"+fetchedincareof);
            
            
            DCIteratorBinding mailingAddrIterator =
                (DCIteratorBinding) propIdentContainer.findIteratorBinding("mailingAddrIterator");

            if (mailingAddrIterator!=null) {
                Row mAddrRow = mailingAddrIterator.getCurrentRow();
                if (mAddrRow != null) {
                    String street = (String) mAddrRow.getAttribute("street");
                    String city = (String) mAddrRow.getAttribute("city");
                    String state = (String) mAddrRow.getAttribute("state");
                    String zip = (String) mAddrRow.getAttribute("zip");
                    String Country = (String) mAddrRow.getAttribute("country");
                

                retrivedMailingAddr = appendAddr(street,city,state,zip,Country);
               
                }
            }
        }
        
      //  System.out.println(":: loadData MacPerformBean ENDS ::");

        return "assignedPT";


    }

    public void choosePriorAssessee() {

    }

    /**
     *
     * @param selectionEvent
     */

    public void selectAssesseeRow(SelectionEvent selectionEvent) {
        // Add event code here...
        RichTable _table = (RichTable) selectionEvent.getSource();
        CollectionModel model = (CollectionModel) _table.getValue();
        JUCtrlHierBinding _binding = (JUCtrlHierBinding) model.getWrappedData();
        DCIteratorBinding iteratorBinding = _binding.getDCIteratorBinding();
        Object _selectedRowData = _table.getSelectedRowData();
        JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) _selectedRowData;
        Key rwKey = node.getRowKey();
        iteratorBinding.setCurrentRowWithKey(rwKey.toStringFormat(true));
    }


//    public void setAssesseetable(RichTable assesseetable) {
//        this.assesseetable = assesseetable;
//    }

    public RichTable getAssesseetable() {
        return (RichTable) MacUiBean.getValueFrmExpression("#{macUiBean.assesseetable}");
    }

    /**
     *
     * @param actionEvent
     */
    public void selectAction(ActionEvent actionEvent) {
        // Add event code here...

        logger.error(MacPerformBean.class, "MacPerformBean()", "Start selectAction", null);
        try {
            RowKeySet selectedEmps = getAssesseetable().getSelectedRowKeys();
            //Create iterator from RowKeySet
            Iterator selectedEmpIter = selectedEmps.iterator();

            CollectionModel model = (CollectionModel) getAssesseetable().getValue();
            JUCtrlHierBinding _binding = (JUCtrlHierBinding) model.getWrappedData();
            DCIteratorBinding iteratorBinding = _binding.getDCIteratorBinding();
            Object _selectedRowData = getAssesseetable().getSelectedRowData();
            JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) _selectedRowData;
            Key rwKey = node.getRowKey();
           iteratorBinding.setCurrentRowWithKey(rwKey.toStringFormat(true));
            String selectedAssessName = (String) iteratorBinding.getCurrentRow().getAttribute("assesseeName");
            String selectedrecordingDate = null;
            if (iteratorBinding.getCurrentRow().getAttribute("recordingDate") != null) {
                selectedrecordingDate = iteratorBinding.getCurrentRow()
                                                       .getAttribute("recordingDate")
                                                       .toString();
            }
            String selecteddocId = (String) iteratorBinding.getCurrentRow().getAttribute("docId");
            String selectedSeqNumber = (String) iteratorBinding.getCurrentRow().getAttribute("seqNumber");

            String selectedstreet = (String) iteratorBinding.getCurrentRow().getAttribute("street");

            String selectedcity = (String) iteratorBinding.getCurrentRow().getAttribute("city");
            String selectedstate = (String) iteratorBinding.getCurrentRow().getAttribute("state");
            String selectedzip = (String) iteratorBinding.getCurrentRow().getAttribute("zipcode");
            String selectedcountry = (String) iteratorBinding.getCurrentRow().getAttribute("country");
            BigInteger ohid = (BigInteger) iteratorBinding.getCurrentRow().getAttribute("ohid");
            String inCareof = (String) iteratorBinding.getCurrentRow().getAttribute("inCareOf");
            //    String selectedupdatedDate = (String) iteratorBinding.getCurrentRow().getAttribute("updatedDate");


            DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
            Row rows[] = iter.getAllRowsInRange();
            //currentMailingAddrIterator
            Row row = iter.getCurrentRow();
                
               String PriorAssessMAddr = selectedstreet+", "+selectedcity+", "+selectedstate+", "+selectedzip+", "+selectedcountry;

            row.setAttribute("currentMailingAddress", PriorAssessMAddr);
            row.setAttribute("ohid", ohid);
            row.setAttribute("assesseeName", selectedAssessName);
            row.setAttribute("recordingDate", iteratorBinding.getCurrentRow().getAttribute("recordingDate"));
            row.setAttribute("seqNumber", selectedSeqNumber);
            row.setAttribute("docId", selecteddocId);
            row.setAttribute("priorAssesseeMailAddress", PriorAssessMAddr);// facade considers priorAssesseeMailAddress string for mailing addr if not null
            row.setAttribute("inCareOf", inCareof);
            AdfFacesContext.getCurrentInstance().addPartialTarget((UIComponent) MacUiBean.getValueFrmExpression("#{macUiBean.assesseeForm1}"));
            AdfFacesContext.getCurrentInstance().addPartialTarget((UIComponent) MacUiBean.getValueFrmExpression("#{macUiBean.assesseeForm2}"));
            AdfFacesContext.getCurrentInstance().addPartialTarget((UIComponent) MacUiBean.getValueFrmExpression("#{macUiBean.assesseeForm3}"));

            closePopup();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        logger.error(MacPerformBean.class, "MacPerformBean()", "END selectAction", null);

    }

    public void closePopup() {
        // Add event code here...
        UIComponent tmpComponent;
        tmpComponent = this.getSelectAssesseePopUP();
        while (!(tmpComponent instanceof RichPopup)) {
            tmpComponent = tmpComponent.getParent();
        }
        RichPopup popup = (RichPopup) tmpComponent;
        popup.hide();
    }

//    public void setPglAssessee(RichPanelGroupLayout pglAssessee) {
//        this.pglAssessee = pglAssessee;
//    }

    public RichPanelGroupLayout getPglAssessee() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.pglAssessee}");
    }

//    public void setSelectAssesseePopUP(RichPopup selectAssesseePopUP) {
//        this.selectAssesseePopUP = selectAssesseePopUP;
//    }

    public RichPopup getSelectAssesseePopUP() {
        return (RichPopup) MacUiBean.getValueFrmExpression("#{macUiBean.selectAssesseePopUP}");
    }

    public String cancelPopUp() {
        // Add event code here...
        closePopup();
        return null;
    }

    public void previousAction(ActionEvent actionEvent) {
        // Add event code here...
       // logger.error(MacPerformBean.class, "MacPerformBean()", "Starts previousAction", null);

        if (counter > 1) {
            counter = counter - 1;

            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding op = (OperationBinding) bindings.getOperationBinding("Previous");
            op.execute();
        }

        //AdfFacesContext.getCurrentInstance().addPartialTarget(navigationPgl);
      //  logger.error(MacPerformBean.class, "MacPerformBean()", "Ends previousAction", null);

    }

//    public void setNavigationPgl(RichPanelGroupLayout navigationPgl) {
//        this.navigationPgl = navigationPgl;
//    }

    public RichPanelGroupLayout getNavigationPgl() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.navigationPgl}");
    }

//    public String nextAction() {
//        // Add event code here...
//        logger.error(MacPerformBean.class, "MacPerformBean()", "Starts nextAction", null);
//        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
//        //  iter.executeQuery();
//        //DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//
//        if (counter < iter.getEstimatedRowCount()) {
//            counter = counter + 1;
//            // Add event code here...
//            OperationBinding op = (OperationBinding) bindingContainer.getOperationBinding("Next");
//            op.execute();
//          
//            Row row = iter.getCurrentRow();
//            return null;
//        }
//        logger.error(MacPerformBean.class, "MacPerformBean()", "Ends nextAction", null);
//        if (counter == iter.getEstimatedRowCount()) {
//            //ainList
//            String workUnitId = ADFUtils.getPageFlowStringValue("workUnitId");
//            String roleValue = ADFUtils.getPageFlowStringValue("rolevalue");
//            List<AINPojo> ainList = (List<AINPojo>) ADFUtils.getPageFlowValue("ainList");
//            OperationBinding op = (OperationBinding) bindingContainer.getOperationBinding("getAddrPayloadData");
//            if (ainList != null)
//            //DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//            op.getParamsMap().put("workUnitId", ADFUtils.getPageFlowStringValue("workUnitId"));
//            op.getParamsMap().put("ains", ainList);
//            op.getParamsMap().put("rolevalue", roleValue);
//            op.getParamsMap().put("showallain", false);
//            op.execute();
//
//            DCIteratorBinding iter1 =
//                (DCIteratorBinding) bindingContainer.findIteratorBinding("mailingAddrPojoListIterator");
//            iter1.executeQuery();
//
//            DCIteratorBinding iter2 = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
//            iter2.executeQuery();
//            DCIteratorBinding iter3 =
//                (DCIteratorBinding) bindingContainer.findIteratorBinding("currentMailingAddrIterator");
//            iter3.executeQuery();
//            DCIteratorBinding iter4 =
//                (DCIteratorBinding) bindingContainer.findIteratorBinding("currentSitusAddrIterator");
//            iter4.executeQuery();
//
//        }
//        viewmode = "summaryview";
//        ADFUtils.setPageFlowValue("viewmode", viewmode);
//        return "summaryview";
//    }


    public boolean saveAddressWorkUnit(Boolean addrValChanged) {
      //  logger.error(MacPerformBean.class, "saveAddressWorkUnit()", "Starts saveAddressWorkUnit", null);

        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ainListIterator =
            (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        DCIteratorBinding mailingAddrPojoListIterator =
            (DCIteratorBinding) bindingContainer.findIteratorBinding("mailingAddrPojoListIterator");

        Row ainListIteratorRow = ainListIterator.getCurrentRow();
        Row row = mailingAddrPojoListIterator.getCurrentRow();
       
        Row aMPWorkTaskIteratorRow = null;
        DCBindingContainer aMPWorkTaskbindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding aMPWorkTaskIterator =
            (DCIteratorBinding) aMPWorkTaskbindingContainer.findIteratorBinding("AMPWorkTaskIterator");
       

         aMPWorkTaskIteratorRow = aMPWorkTaskIterator.getCurrentRow();
        if (aMPWorkTaskIteratorRow != null) {
            row.setAttribute("payloadType", "MAILING ADDRESS");          
                       
            row.setAttribute("workUnitId", aMPWorkTaskIteratorRow.getAttribute("WorkUnitId"));
            row.setAttribute("workUnitName", aMPWorkTaskIteratorRow.getAttribute("WorkUnitName"));
            row.setAttribute("aggregateId", aMPWorkTaskIteratorRow.getAttribute("AggregatedId"));
            row.setAttribute("category", aMPWorkTaskIteratorRow.getAttribute("Category"));

            row.setAttribute("subCategory", aMPWorkTaskIteratorRow.getAttribute("Subcategory"));
            row.setAttribute("eventDate", aMPWorkTaskIteratorRow.getAttribute("EventDate"));
            row.setAttribute("uiAction", "Submit");
            row.setAttribute("district", "");
            row.setAttribute("cluster", "");
            row.setAttribute("documentId", aMPWorkTaskIteratorRow.getAttribute("DocumentId"));
           
        }
        // TODO Need to see data type.
        else {
            //  row = mailingAddrPojoListIterator.getCurrentRow();
            row.setAttribute("payloadType", "MAILING ADDRESS");
            row.setAttribute("uiAction", "Submit");
            row.setAttribute("workUnitId", ADFUtils.getPageFlowStringValue("workUnitId"));
        }
        row.setAttribute("inputSource", "WorkTask");
        row.setAttribute("transactionId", "");
     
        //Recheck the value
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentMailingAddrIterator");
        Row rows[] = iter.getAllRowsInRange();

        Row currentMailAddrRow = iter.getCurrentRow();
//
//        System.out.println("Mailing Address Size->" + rows.length);
//        for (int i = 0; i < rows.length; i++) {
//            System.out.println("MAiling address data, should have been set " + rows[i].getAttribute("street"));
//            System.out.println("MAiling address data, should have been set " + rows[i].getAttribute("city"));
//            System.out.println("MAiling address data, should have been set " + rows[i].getAttribute("zip"));
//            System.out.println("MAiling address data, should have been set " + rows[i].getAttribute("state"));
//            System.out.println("MAiling address data, should have been set " + rows[i].getAttribute("country"));
//
//        }
       // System.out.println("END ADRESS DEBUG*******************************************");



// New code added .. 
        if (currentMailAddrRow != null) {
            String submittedvalue = appendAddr((String)currentMailAddrRow.getAttribute("street"), (String)currentMailAddrRow.getAttribute("city"),(String)currentMailAddrRow.getAttribute("state"), (String)currentMailAddrRow.getAttribute("zip"), (String)currentMailAddrRow.getAttribute("country"));
            row.setAttribute("mailingAddress", submittedvalue);
        } // new line Added as per requirment.


        boolean addrChange = checkmailingAddrChange(ainListIteratorRow);

        Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));
       if( estimateRowCount != null && estimateRowCount.intValue() ==  1 && isOwnershipReviewer()) {
           //setAddrFormDataToSitus();
       }
        
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op = (OperationBinding) bindings.getOperationBinding("saveAddressWorkUnitFacade");
        op.getParamsMap().put("addrValChanged", addrValChanged);
        op.execute();
        logger.error(MacPerformBean.class, "saveAddressWorkUnit()", "Starts saveAddressWorkUnit", null);
        return addrChange;

    }


    /** This is the action that is called on click of Approve Action required button
     * @param actionEvent
     */
    //public void ApproveAction(ActionEvent actionEvent) {
    public String ApproveAction() {
        // Add event code here...
        boolean status = true;
        String returnAction = null;
     //   logger.error(MacPerformBean.class, "MacPerformBean()", "Starts ApproveAction", null);


        if (validateApproveNotes()) {
            DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
            Row row = iter.getCurrentRow();
            long filterCount = iter.getEstimatedRowCount();
            Integer rowIndex = (Integer) row.getAttribute("indexId"); // will always have value
            row.setAttribute("isApproveAddrChange", true);
            row.setAttribute("isDenyAddrChange", false);
            row.setAttribute("isMailingAddrInvestigationReq", false);
            row.setAttribute("isVisited", true);
            remarksMandatory = false;
            notesMandatory = true;
       //     logger.error(MacPerformBean.class, "MacPerformBean()", "Ends ApproveAction", null);

            String mainAIN = (String) row.getAttribute("ain");
            Boolean situsInvestFlag = (Boolean) row.getAttribute("isSitusAddrInvestigationReq");

// if mailing address chnage 
            // showAllAin
            
            // curren is visted 
            boolean mailAddrChange = checkmailingAddrChange(iter.getCurrentRow());
            
            
            saveAddressWorkUnit(Boolean.valueOf(mailAddrChange));
            
         
            
           checkmailingAddrChange(iter.getCurrentRow());
//            
                
           

            OperationBinding op = null;
          Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));
            if (estimateRowCount == 1) {
                if(Boolean.TRUE.equals(row.getAttribute("isSitusAddrInvestigationReq"))) {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "YES");
                } else {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "NO");
                }
                returnAction = addEventParameters();
            }else {
                
                if(mailAddrChange && isOwnershipReviewer2() &&  (estimateRowCount.longValue() != filterCount  ||  counter != 0) ) {
                    return showAllAin(Boolean.TRUE, "INVESTIGATE", "Approve", rowIndex, estimateRowCount);
                   
                }
               
                
                
               return nextOperation();
//                if ((counter) >= iter.getEstimatedRowCount()) {
//                    viewmode = "summaryview";
//                    ADFUtils.setPageFlowValue("viewmode", viewmode);
//                    return "summaryview";
//                }
                
            }

        }
       
      //  System.out.println("Approve Status:" + status);
        
        return returnAction;
    }

    /**
     * Invoke an expression
     * @param expr
     * @param returnType
     * @param argType
     * @param argument
     * @return
     */
    public static Object invokeMethodExpression(String expr, Class returnType, Class argType, Object argument) {
        return invokeMethodExpression(expr, returnType, new Class[] { argType }, new Object[] { argument });

    }

    public static Object invokeMethodExpression(String expr, Class returnType, Class[] argTypes, Object[] args) {
        FacesContext fc = FacesContext.getCurrentInstance();
        ELContext elctx = fc.getELContext();
        ExpressionFactory elFactory = fc.getApplication().getExpressionFactory();
        MethodExpression methodExpr = elFactory.createMethodExpression(elctx, expr, returnType, argTypes);
        return methodExpr.invoke(elctx, args);
    }
/** This is the action that is called on click of Deny Action required button
     * @param actionEvent
     */
   // public void denyAction(ActionEvent actionEvent) {
   public String denyAction() {
        // Add event code here...
        String returnAction = null;
     //   logger.error(MacPerformBean.class, "MacPerformBean()", "Starts denyAction", null);

        if (validateDenyRemarks()) {


            DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
            long filterCount = iter.getEstimatedRowCount();
            Row row = iter.getCurrentRow();
            //set  Approve and mailing addre investigation to false
            Integer rowIndex = (Integer) row.getAttribute("indexId"); // will always have value
            row.setAttribute("isDenyAddrChange", true);
            row.setAttribute("isApproveAddrChange", false);
            row.setAttribute("isMailingAddrInvestigationReq", false);
            row.setAttribute("isVisited", true);
            remarksMandatory = true;
            notesMandatory = false;
            // visted = true ;
            
            // curren is visted 
            boolean mailAddrChange = checkmailingAddrChange(iter.getCurrentRow());
            
            saveAddressWorkUnit(Boolean.valueOf(mailAddrChange));

            
            
            checkmailingAddrChange(iter.getCurrentRow());
            
            OperationBinding op = null;
            Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));

            if (estimateRowCount ==1) {
                if(Boolean.TRUE.equals(row.getAttribute("isSitusAddrInvestigationReq"))) {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "YES");
                } else {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "NO");
                }
                returnAction = addEventParameters();
            }else {
                
                if(mailAddrChange && isOwnershipReviewer2() && (estimateRowCount.longValue() != filterCount  ||  counter != 0) ) {
                    return showAllAin(Boolean.TRUE,"INVESTIGATE", "Deny", rowIndex, estimateRowCount);
                   
                }
               
               
                return nextOperation();
               
                
            }
        }

      //  logger.error(MacPerformBean.class, "MacPerformBean()", "ENDS denyAction", null);
        return returnAction;
    }

 /** This is the action that is called on click of Mailind Address Investigation required button
     * @param actionEvent
     */
    //public void mailingAddressInvestigation(ActionEvent actionEvent) {
    public String mailingAddressInvestigation() {
        logger.error(MacPerformBean.class, "MacPerformBean()", "Start mailingAddressInvestigation", null);
        String returnAction = null;
        if (validateMailingAddress()) {
            DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
            Row row = iter.getCurrentRow();

            row.setAttribute("isApproveAddrChange", false);
            row.setAttribute("isDenyAddrChange", false);
            row.setAttribute("isMailingAddrInvestigationReq", true);
            row.setAttribute("isVisited", true);
            remarksMandatory = true;
            notesMandatory = false;
            // visted = true ;
            ADFUtils.setPageFlowValue("hasMailingAddrInvestgn", "YES");
            
            
            saveAddressWorkUnit(null);

            OperationBinding op = null;
            Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));

            if (estimateRowCount ==1) {
                if(Boolean.TRUE.equals(row.getAttribute("isSitusAddrInvestigationReq"))) {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "YES");
                } else {
                    ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "NO");
                }
              returnAction = addEventParameters();
                //            op = (OperationBinding) bindingContainer.getOperationBinding("SubmitForApproval");
                //            op.execute();
            }else {
               
               
                
             return   nextOperation();
//                if ((counter) >= iter.getEstimatedRowCount()) {
//                    viewmode = "summaryview";
//                    ADFUtils.setPageFlowValue("viewmode", viewmode);
//                    return "summaryview";
//                }
                
            }
           

        }
     //   logger.error(MacPerformBean.class, "MacPerformBean()", "ENDS mailingAddressInvestigation", null);
        return returnAction;
    }

    public void setRemarksMandatory(boolean remarksMandatory) {
        this.remarksMandatory = remarksMandatory;
    }

    public boolean isRemarksMandatory() {
        return remarksMandatory;
    }


    /**This valuechangelistener is called when the Set situs and mailing Address Investigation checkbox is checked
     * @param valueChangeEvent
     */
    public void SaveAddressVLC(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
      //  logger.error(MacPerformBean.class, "MacPerformBean()", "Start SaveAddressVLC", null);

        Boolean situsAddrInvestigation = (Boolean) valueChangeEvent.getNewValue();
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
                Row row = iter.getCurrentRow();
        if (situsAddrInvestigation) {
           // setAddrFormDataToSitus();
           // getItusAddrInvestigation().setSelected(false);
           row.setAttribute("isSitusAddrInvestigationReq", Boolean.FALSE);
        } 
        AdfFacesContext.getCurrentInstance().addPartialTarget(getItusAddrInvestigation());
     //   logger.error(MacPerformBean.class, "MacPerformBean()", "End SaveAddressVLC", null);
    }

//    public void setCheckboxInvestigation(RichPanelGroupLayout checkboxInvestigation) {
//        this.checkboxInvestigation = checkboxInvestigation;
//    }

    public RichPanelGroupLayout getCheckboxInvestigation() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.checkboxInvestigation}");
    }

    /**This valuechangelistener is called when the Situs Address Investigation checkbox is checked
     * @param valueChangeEvent
     */
    public void situsAddreInvestVCL(ValueChangeEvent valueChangeEvent) {

        Boolean situsAddrInvest = (Boolean) valueChangeEvent.getNewValue();
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        Row row = iter.getCurrentRow();
        if (situsAddrInvest) {
            
           
        //    row.setAttribute("isSitusAddrInvestigationReq", true);
            ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", "YES");
           // row.setAttribute("flagMailSitus", Boolean.FALSE);

          //  getMailingSitusAddr().setSelected(false);
        } 
     //   AdfFacesContext.getCurrentInstance().addPartialTarget(getMailingSitusAddr());
    }

//    public void setItusAddrInvestigation(RichSelectBooleanCheckbox itusAddrInvestigation) {
//        this.itusAddrInvestigation = itusAddrInvestigation;
//    }

    public RichSelectBooleanCheckbox getItusAddrInvestigation() {
        return (RichSelectBooleanCheckbox) MacUiBean.getValueFrmExpression("#{macUiBean.itusAddrInvestigation}");
    }

//    public void setMailingSitusAddr(RichSelectBooleanCheckbox mailingSitusAddr) {
//        this.mailingSitusAddr = mailingSitusAddr;
//    }

//    public RichSelectBooleanCheckbox getMailingSitusAddr() {
//        return (RichSelectBooleanCheckbox) MacUiBean.getValueFrmExpression("#{macUiBean.mailingSitusAddr}");
//    }

    private void updateMailingWithSitusAddr() {
      //  logger.error(MacPerformBean.class, "MacPerformBean()", "Start SaveAddressVLC", null);
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        Row row = iter.getCurrentRow();

        //Sapna - updating the new address for both situs and Mailing
        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentMailingAddrIterator");

        Row mailingAddrrow = iter.getCurrentRow();

        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentSitusAddrIterator");
        Row situsAddrRow = iter.getCurrentRow();

        mailingAddrrow.setAttribute("street", situsAddrRow.getAttribute("street"));
        mailingAddrrow.setAttribute("city", situsAddrRow.getAttribute("city"));
        mailingAddrrow.setAttribute("state", situsAddrRow.getAttribute("state"));
        mailingAddrrow.setAttribute("zip", situsAddrRow.getAttribute("zip"));
        mailingAddrrow.setAttribute("country", situsAddrRow.getAttribute("country"));
    }

    //This method is called inside the valuchangelistener, when the check box Set Situs and Mailing address is checked.
    private void setAddrFormDataToSitus() {
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        Row row = iter.getCurrentRow();
        if(Boolean.TRUE.equals(row.getAttribute("isSitusAddrInvestigationReq")) || !Boolean.TRUE.equals(row.getAttribute("flagMailSitus"))) {
            return;
        }

        //Sapna - updating the new address for both situs and Mailing
        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("mailingAddrIterator");
        Row mailingAddrrow = iter.getCurrentRow();
        //        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        //        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("addrValidnFormIterator");
        //        Row addrValidtnFormRow = iter.getCurrentRow();

        mailingAddrrow.setAttribute("street", this.getValueFrmExpression("#{bindings.street1.inputValue}"));
        mailingAddrrow.setAttribute("city", this.getValueFrmExpression("#{bindings.city1.inputValue}"));
        mailingAddrrow.setAttribute("state", this.getValueFrmExpression("#{bindings.state1.inputValue}"));
        mailingAddrrow.setAttribute("zip", this.getValueFrmExpression("#{bindings.zip1.inputValue}"));
        mailingAddrrow.setAttribute("country", this.getValueFrmExpression("#{bindings.country1.inputValue}"));
      //  iter.executeQuery();
        //Set same address for situs address
        bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentSitusAddrIterator");
        Row situsAddrRow = iter.getCurrentRow();
        System.out.println("Existing Current Mailing Street ==" + situsAddrRow.getAttribute("street"));

        situsAddrRow.setAttribute("street", this.getValueFrmExpression("#{bindings.street1.inputValue}"));
        situsAddrRow.setAttribute("city", this.getValueFrmExpression("#{bindings.city1.inputValue}"));
        situsAddrRow.setAttribute("state", this.getValueFrmExpression("#{bindings.state1.inputValue}"));
        situsAddrRow.setAttribute("zip", this.getValueFrmExpression("#{bindings.zip1.inputValue}"));
        situsAddrRow.setAttribute("country", this.getValueFrmExpression("#{bindings.country1.inputValue}"));
      //  iter.executeQuery();
    }

    /**Method to get value from Expression (EL)
     * @param data
     * @return
     */
    public String getValueFrmExpression(String data) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Application app = fc.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = fc.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, data, Object.class);
        String Message = null;
        Object obj = valueExp.getValue(elContext);
        if (obj != null) {
            Message = obj.toString();
        }
        return Message;
    }


    /**Method to set value in Expression (EL)
     * @param el
     * @param val
     */
    public void setvalueToExpression(String el, Object val) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);
        exp.setValue(elContext, val);
    }

    public boolean isOwnershipApprover() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipApprover") &&  isLOne()) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipInvestigator() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipInvestigator")) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipApprover2() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipApprover2") && isLTwo()) {
            return true;
        }

        return false;  

    }

    public boolean isOwnershipReviewer() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipReviewer") &&  isLOne()) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipReviewer2() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipReviewer2") && isLTwo()) {
            return true;
        }

        return false;  // local testing : return false;

    }


    /**
     * @return
     */
    public boolean checkAinStatus() {

        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");

        if (iter != null) {
            Row rows[] = iter.getAllRowsInRange();
            for (Row r : rows) {
                Boolean isVisited = (Boolean) r.getAttribute("isVisited");
                //Adding null chcek
                if (isVisited != null && !isVisited) {
                    return false;
                }
            }
        }
      //  System.out.println("inside checkAinStatus End");
        return true;
    }


    /**
     * @param mailAddChange
     * @param submittedMailAddress
     * @return
     */
    public boolean mailAddChanged(boolean mailAddChange, String submittedMailAddress) {

        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        Row currentRow = iter.getCurrentRow();
        currentRow.setAttribute("originalMailAddress", submittedMailAddress);
      //  System.out.println("iter*****" + iter);
        if (mailAddChange) {
            Row rows[] = iter.getAllRowsInRange();
            for (Row r : rows) {
                r.setAttribute("isVisited", false);
            }
            currentRow.setAttribute("isVisited", true);
        }

        return true;
    }

    public void setCheckAinStatus(boolean checkAinStatus) {
        this.checkAinStatus = checkAinStatus;
    }

    public boolean isCheckAinStatus() {
      //  checkAinStatus();

       // System.out.println("checkAinStatus--" + checkAinStatus());
        return checkAinStatus;
    }

    /** This action updates the current mailing address with current situs address on click of Insert Situs Address button
     * @return
     */
    public String onUseSitusAddr() {
       // System.out.println("MacPerformBean : onUseSitusAddress ->");
        String situsAddress = (String) AdfFacesContext.getCurrentInstance()
                                                      .getPageFlowScope()
                                                      .get("selectedAddr");

       // System.out.println("MacPerformBean Situs Address : " + situsAddress);
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding situsStreet = (AttributeBinding) bindingContainer.getControlBinding("street");
        AttributeBinding situsCity = (AttributeBinding) bindingContainer.getControlBinding("city");
        AttributeBinding situsState = (AttributeBinding) bindingContainer.getControlBinding("state");
        AttributeBinding situsZip = (AttributeBinding) bindingContainer.getControlBinding("zip");
        AttributeBinding situsCountry = (AttributeBinding) bindingContainer.getControlBinding("country");

//        System.out.println("situsStreet===" + situsStreet);
//        System.out.println("situsCity===" + situsCity);
//        System.out.println("situsZip===" + situsZip);
        //Update UI
        this.setvalueToExpression("#{bindings.country1.inputValue}", situsCountry);
        this.setvalueToExpression("#{bindings.city1.inputValue}", situsCity);
        this.setvalueToExpression("#{bindings.state1.inputValue}", situsState);
        this.setvalueToExpression("#{bindings.zip1.inputValue}", situsZip);
        this.setvalueToExpression("#{bindings.street1.inputValue}", situsStreet);

        AdfFacesContext.getCurrentInstance().addPartialTarget(getAddressValidnForm());
        //Update data
        updateMailingWithSitusAddr();
        return null;
    }

//    public void setAddressValidnForm(RichPanelGroupLayout addressValidnForm) {
//        this.addressValidnForm = addressValidnForm;
//    }

    public RichPanelGroupLayout getAddressValidnForm() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.addressValidnForm}");
    }

    //This hs to be set to udpate the mailing address with the address form data
    private void setFormDataToMailingAddrOnly() {
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding formStreet = (AttributeBinding) bindingContainer.getControlBinding("street1");
        AttributeBinding formCity = (AttributeBinding) bindingContainer.getControlBinding("city1");
        AttributeBinding formState = (AttributeBinding) bindingContainer.getControlBinding("state1");
        AttributeBinding formZip = (AttributeBinding) bindingContainer.getControlBinding("zip1");
        AttributeBinding formCountry = (AttributeBinding) bindingContainer.getControlBinding("country1");

      

        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentMailingAddrIterator");
        iter.executeQuery();
        Row mailingAddrrow = iter.getCurrentRow();
        mailingAddrrow.setAttribute("street", formStreet);
        mailingAddrrow.setAttribute("city", formCity);
        mailingAddrrow.setAttribute("state", formState);
        mailingAddrrow.setAttribute("zip", formZip);
        mailingAddrrow.setAttribute("country", formCountry);
        iter.executeQuery();
    }

    public void backAction(ActionEvent actionEvent) {
        // Add event code here...
    }

    /**This action is called on click of Back button in the summary list page
     * @return
     */
    public String backAction() {
        // Add event code here...
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter1 = (DCIteratorBinding) bindings.findIteratorBinding("ainListIterator");
        //        iter1.executeQuery();
     //   System.out.println("BACK BUTTON: total AINs -->" + iter1.getEstimatedRowCount());
       int n = (int) (iter1.getEstimatedRowCount() - 1);
        iter1.setCurrentRowIndexInRange(n);
        counter = n;
        return "back";
    }


    public void setNotesMandatory(boolean notesMandatory) {
        this.notesMandatory = notesMandatory;
    }

    public boolean isNotesMandatory() {
        return notesMandatory;
    }

//    public void setAinNotes(RichInputText ainNotes) {
//        this.ainNotes = ainNotes;
//    }

    public RichInputText getAinNotes() {
        return (RichInputText) MacUiBean.getValueFrmExpression("#{macUiBean.ainNotes}");
    }

//    public void setAinRemarks(RichInputText ainRemarks) {
//        this.ainRemarks = ainRemarks;
//    }

    public RichInputText getAinRemarks() {
        return (RichInputText) MacUiBean.getValueFrmExpression("#{macUiBean.ainRemarks}");
    }

  
    private boolean validateDenyRemarks() {
        
       
        String Country = this.getValueFrmExpression("#{bindings.country1.inputValue}");
        String zip = this.getValueFrmExpression("#{bindings.zip1.inputValue}");
        String state = this.getValueFrmExpression("#{bindings.state1.inputValue}");
        String city = this.getValueFrmExpression("#{bindings.city1.inputValue}");
        String street = this.getValueFrmExpression("#{bindings.street1.inputValue}");
        
        
        if(Country == null && zip== null && state == null && city == null && street ==null) {
            FacesMessage Message = new FacesMessage("Please provide mailing address for the request!");
            Message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, Message);
            return false;
        }
        
       
        if (getAinRemarks().getValue() == null || ((String) getAinRemarks().getValue()) == "") {
            FacesMessage Message = new FacesMessage("Please provide Remarks for deny request!");
            Message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, Message);
            return false;
        }

        return true;
    }

    private boolean validateMailingAddress() {
        
        
        if (getAinRemarks().getValue() == null || ((String) getAinRemarks().getValue()) == "") {
            FacesMessage Message = new FacesMessage("Please provide Remarks for mailing address investigation!");
            Message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, Message);
            return false;
        }

        return true;
    }
    
    private boolean validateApproveNotes() {
        
        String Country = this.getValueFrmExpression("#{bindings.country1.inputValue}");
        String zip = this.getValueFrmExpression("#{bindings.zip1.inputValue}");
        String state = this.getValueFrmExpression("#{bindings.state1.inputValue}");
        String city = this.getValueFrmExpression("#{bindings.city1.inputValue}");
        String street = this.getValueFrmExpression("#{bindings.street1.inputValue}");
        
        
        if(Country == null && zip== null && state == null && city == null && street ==null) {
            FacesMessage Message = new FacesMessage("Please provide mailing address for the request!");
            Message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, Message);
            return false;
        }
        
        boolean situsAddInvest = (Boolean)getItusAddrInvestigation().getValue();
        
        System.out.println("situsAddInvest---"+situsAddInvest);
        
        if ((getAinNotes().getValue() == null || ((String) getAinNotes().getValue()) == "") && situsAddInvest) {
            FacesMessage Message = new FacesMessage("Please provide Notes for situs address investigation!");
            Message.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, Message);
            return false;
        }

        return true;
    }

    /**This method adds the Event params in the payload when situs address or mailing address investigation is needed.
     * @return
     */
    public String addEventParameters() {
        System.out.println("1090--ADD EVENT PARAMETRS ##########");

        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding paramIter = (DCIteratorBinding) bindings.findIteratorBinding("ParameterIterator");
        String hasSitusAddrInvestgn = ADFUtils.getPageFlowStringValue("hasSitusAddrInvestgn");
        String hasMailingAddrInvestgn = ADFUtils.getPageFlowStringValue("hasMailingAddrInvestgn");
//        System.out.println("hasSitusAddrInvestgn:" + hasSitusAddrInvestgn);
//        System.out.println("hasMailingAddrInvestgn:" + hasMailingAddrInvestgn);
        paramIter.executeQuery();
        Boolean situsAddressParam = Boolean.FALSE;
        Boolean mailingAddressParam = Boolean.FALSE;
        Row rows[] = paramIter.getAllRowsInRange();
        for (Row row : rows) {
            String name = (String) row.getAttribute("Name");
            if ("IsSitusInvestigationRequired".equals(name)) {
                situsAddressParam = Boolean.TRUE;
                if("YES".equals(hasSitusAddrInvestgn)) {
                    row.setAttribute("Value", "Yes");
                } else {
                    row.setAttribute("Value", "No");
                }
            }
            if ("IsMailingInvestigationRequired".equals(name)) {
                mailingAddressParam = Boolean.TRUE;
                    if("YES".equals(hasMailingAddrInvestgn)) {
                        row.setAttribute("Value", "Yes");
                    } else {
                        row.setAttribute("Value", "No");
                    }
            }
        }
      //  System.out.println("Already Events Params are there................");
        if (!situsAddressParam) {
            System.out.println("newly creaitng Event Params");
            if ("YES".equals(hasSitusAddrInvestgn)) {
               // System.out.println("1106 rw---:" + paramIter.getEstimatedRowCount());
                OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                op.execute();
                Row rw1 = paramIter.getCurrentRow();
              //  System.out.println("1109 rw---:" + paramIter.getEstimatedRowCount());
                rw1.setAttribute("Name", "IsSitusInvestigationRequired");
                rw1.setAttribute("Value", "Yes");
            } else {
                OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                op.execute();
                Row rw2 = paramIter.getCurrentRow();
                rw2.setAttribute("Name", "IsSitusInvestigationRequired");
                rw2.setAttribute("Value", "No");
            }
        }
        if (!mailingAddressParam) {
            System.out.println("newly creaitng Event Params");
            if ("YES".equals(hasMailingAddrInvestgn)) {
                OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                op.execute();
                Row rw3 = paramIter.getCurrentRow();
                rw3.setAttribute("Name", "IsMailingInvestigationRequired");
                rw3.setAttribute("Value", "Yes");
            } else {
                OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                op.execute();
                Row rw4 = paramIter.getCurrentRow();
                rw4.setAttribute("Name", "IsMailingInvestigationRequired");
                rw4.setAttribute("Value", "No");
            }
        }
        //displaying all parameters
        paramIter.executeQuery();
        Row rows1[] = paramIter.getAllRowsInRange();
        for (Row row : rows1) {
            String name = (String) row.getAttribute("Name");
            String value = (String) row.getAttribute("Value");
          //  System.out.println("1132----name:" + name + "----Value:" + value);
        }

        OperationBinding op1 = (OperationBinding) bindings.getOperationBinding("SubmitForApproval");
        op1.execute();
        ADFContext.getCurrent().getSessionScope().put("lstbpmaction", "submit");
        return "closeTF";
    }


//    public void setRemarksLayout(RichPanelGroupLayout remarksLayout) {
//        this.remarksLayout = remarksLayout;
//    }

    public RichPanelGroupLayout getRemarksLayout() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.remarksLayout}");
    }

//    public void setNotesLayout(RichPanelGroupLayout notesLayout) {
//        this.notesLayout = notesLayout;
//    }

    public RichPanelGroupLayout getNotesLayout() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.notesLayout}");
    }

    public String nextOperation() {
        // Add event code here...
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op1 = (OperationBinding) bindings.getOperationBinding("Next1");
        op1.execute();
     //   System.out.println("IN side NEXT ::");
        counter++;
        //        loadAssignedPT();
        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter0 =
            (DCIteratorBinding) bindingContainer.findIteratorBinding("mailingAddrPojoListIterator");
        //
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        DCIteratorBinding iter1 =
            (DCIteratorBinding) bindingContainer.findIteratorBinding("currentMailingAddrIterator");
        DCIteratorBinding iter2 = (DCIteratorBinding) bindingContainer.findIteratorBinding("currentSitusAddrIterator");
        if ((counter) >= iter.getEstimatedRowCount()) {

            iter.executeQuery();
            iter1.executeQuery();
            iter2.executeQuery();
            ((DCIteratorBinding) bindingContainer.findIteratorBinding("orgAinListIterator")).executeQuery();
            System.out.println("1348==="+((DCIteratorBinding) bindingContainer.findIteratorBinding("orgAinListIterator")).getEstimatedRowCount());
            viewmode = "summaryview";
            ADFUtils.setPageFlowValue("viewmode", viewmode);
            return "summaryview";
        }
        return null;

    }

    public String previousOperation() {
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op = (OperationBinding) bindings.getOperationBinding("Previous");
        op.execute();
        counter--;
        return null;
    }

    public int getNavigationPage() {


        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        int i = iter.getCurrentRowIndexInRange() + 1;

        return i;

    }

    public void showAllAin(ActionEvent actionEvent) {
        // Add event code here...
        showAllAin(Boolean.TRUE, "NONE","SHOW_ALL", null, null);

    }

//    public void setPerformForm(RichPanelFormLayout performForm) {
//        this.performForm = performForm;
//    }

    public RichPanelFormLayout getPerformForm() {
        return (RichPanelFormLayout) MacUiBean.getValueFrmExpression("#{macUiBean.performForm}");
    }

    public void fetchAssesseeList(ActionEvent actionEvent) {
        // Add event code here...


    }

    public void fetchPriorAssessee(PopupFetchEvent popupFetchEvent) {
        // Add event code here...

        DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
        
        Row currentRow = iter.getCurrentRow();
        String aoid = null;
        if (currentRow.getAttribute("aoid") != null) {
            aoid = currentRow.getAttribute("aoid").toString();
        }
        String ain = (String) currentRow.getAttribute("ain");
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op = (OperationBinding) bindings.getOperationBinding("fetchAssesseeList");
        op.getParamsMap().put("ain", ain);
        op.getParamsMap().put("aoid", aoid);
        op.execute();
        iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("assessePojoListIterator");
        iter.executeQuery();
        AdfFacesContext.getCurrentInstance().addPartialTarget(getAssesseetable());
      
    }





    public RichPanelGroupLayout getPerformNavPgl() {
        return (RichPanelGroupLayout) MacUiBean.getValueFrmExpression("#{macUiBean.performNavPgl}");
    }

    public void closeTaskflow() {
        // Add event code here...
    }

      public String closeTF() {
        // Add event code here...
     //   System.out.println("testing CTF.........");
        return "closeTaskFlow";
    }
    //    public String reassignAction()
//    {
//        System.out.println("MacPerformBean : reassignAction");
//        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
//        oracle.bpel.services.workflow.worklist.adf.InvokeActionBean invokeActionBean =
//                                   (oracle.bpel.services.workflow.worklist.adf.InvokeActionBean)map.get("invokeActionBean");
//        String result = invokeActionBean.action();
//        System.out.println("result frm the Action:"+result);
//
//           return "closeTaskFlow";
//        }


//    public void setAssesseeForm1(RichGridRow assesseeForm1) {
//        this.assesseeForm1 = assesseeForm1;
//    }

    public RichGridRow getAssesseeForm1() {
        return (RichGridRow) MacUiBean.getValueFrmExpression("#{macUiBean.assesseeForm1}");
    }
    
//    public void setAssesseeForm2(RichGridRow assesseeForm2) {
//        this.assesseeForm2 = assesseeForm2;
//    }

    public RichGridRow getAssesseeForm2() {
        return (RichGridRow) MacUiBean.getValueFrmExpression("#{macUiBean.assesseeForm2}");
    }
//
//    public void setNextButton(RichLink nextButton) {
//        this.nextButton = nextButton;
//    }

    public RichLink getNextButton() {
        return (RichLink) MacUiBean.getValueFrmExpression("#{macUiBean.nextButton}");
    }

    public void setRetrivedMailingAddr(String retrivedMailingAddr) {
        this.retrivedMailingAddr = retrivedMailingAddr;
    }

    public String getRetrivedMailingAddr() {
        return retrivedMailingAddr;
    }

    private String showAllAin(Boolean showAll, String filterType, String action,Integer index, Integer totalCount) {
        String outcome = null;
        if(showAll == null)
            showAll = Boolean.TRUE;
      //  System.out.println("1502==="+action+"=="+index+"=="+counter);
        DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        
        try {
            
            if(isOwnershipReviewer2() && Boolean.TRUE.equals(showAll)) {
                OperationBinding op = (OperationBinding) dbc.getOperationBinding("filterAin");
                op.getParamsMap().put("filterType", filterType);
                op.execute();
            }  else {
//                String workUnitId = ADFUtils.getPageFlowStringValue("workUnitId");
//                String roleValue = ADFUtils.getPageFlowStringValue("rolevalue");
//                List<AINPojo> ainList = (List<AINPojo>) ADFUtils.getPageFlowValue("ainList");
//                OperationBinding op = (OperationBinding) dbc.getOperationBinding("getAddrPayloadData");
//                op.getParamsMap().put("workUnitId", ADFUtils.getPageFlowStringValue("workUnitId"));
//                op.getParamsMap().put("ains", ainList);
//                op.getParamsMap().put("rolevalue", roleValue);
//                op.getParamsMap().put("showallain", showAll.booleanValue());
//                op.execute();
            }
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
        
        DCIteratorBinding iter = dbc.findIteratorBinding("ainListIterator");
        DCIteratorBinding iter1 = dbc.findIteratorBinding("mailingAddrPojoListIterator");
       
        iter1.executeQuery();
        
        iter.executeQuery();
        
        if(index !=null && totalCount != null) {
           
                if("Next".equals(action) || "Approve".equals(action) || "Deny".equals(action)) {
                    int i =  index +1;
                    if(i < totalCount) {                        
                        counter = i;
                        iter.setCurrentRowIndexInRange(i); 
                        outcome="";
                    } else {
                        dbc.findIteratorBinding("orgAinListIterator").executeQuery();
                        outcome  = "summaryview";
                        viewmode = outcome;
                        ADFUtils.setPageFlowValue("viewmode", outcome); 
                    }
                                       
                } else if("Previous".equals(action)) {
                    int i =  index - 1;
                    counter = i;
                    iter.setCurrentRowIndexInRange(i); 
                    outcome="";
                }
        
            
            
        } else if(Boolean.TRUE.equals(showAll) && "SHOW_ALL".equals(action)) {
            counter = 0;
            iter.setCurrentRowIndexInRange(0);
        }
        
        //          AdfFacesContext.getCurrentInstance().addPartialTarget(performForm);
        AdfFacesContext.getCurrentInstance().addPartialTarget(getPerformNavPgl());
        return outcome;
    }

    private boolean checkmailingAddrChange(Row ainListIteratorRow) {
       // String originalMailAddress = (String) ainListIteratorRow.getAttribute("originalMailAddress");
boolean addrChange = false;

        String Country = this.getValueFrmExpression("#{bindings.country1.inputValue}");
        String zip = this.getValueFrmExpression("#{bindings.zip1.inputValue}");
        String state = this.getValueFrmExpression("#{bindings.state1.inputValue}");
        String city = this.getValueFrmExpression("#{bindings.city1.inputValue}");
        String street = this.getValueFrmExpression("#{bindings.street1.inputValue}");
        
        String updatedIncareOf = this.getValueFrmExpression("#{bindings.inCareOf.inputValue}");
        
//        System.out.println("updatedIncareOf---"+updatedIncareOf);
//            System.out.println("FETCHED IN CARE OF ::"+fetchedincareof);
            
            

        String submittedMailAddress = appendAddr(street,city,state,zip,Country);

//        System.out.println("originalMailAddress::>" + originalMailAddress);
//        System.out.println("retrivedMailingAddr :: -->" + retrivedMailingAddr);
//
//        System.out.println("SubmittedMailAddress::>" + submittedMailAddress);
        
//        if (fetchedincareof != null) {
//        if (!fetchedincareof.equalsIgnoreCase(updatedIncareOf)) {
//            mailAddChanged(true, submittedMailAddress);
//            
//            DCIteratorBinding mailingAddrPojoListItr =
//                (DCIteratorBinding) bindingContainer.findIteratorBinding("mailingAddrPojoListIterator");
//            mailingAddrPojoListItr.executeQuery();
//            
//            Row mailAddRow = mailingAddrPojoListItr.getCurrentRow();
//            mailAddRow.setAttribute("inCareOf", updatedIncareOf);
//            
//        }
//        }
        
       
            if (compare(submittedMailAddress,retrivedMailingAddr) &&  compare(fetchedincareof,updatedIncareOf)) {
                System.out.println("NO Change ");
                addrChange = false;
            } else {
                System.out.println("CHANGED :: ");
                mailAddChanged(true, submittedMailAddress);
                addrChange = true;
                DCBindingContainer bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
                //  iter.executeQuery();
                             
            }

     
        
       
        fetchedincareof = updatedIncareOf;
        retrivedMailingAddr = submittedMailAddress;
        
        return addrChange;
    }

    public void setFetchedincareof(String fetchedincareof) {
        this.fetchedincareof = fetchedincareof;
    }

    public String getFetchedincareof() {
        return fetchedincareof;
    }
    
    public static boolean compare(String addr1, String addr2) {
        return (addr1 == null ? addr2 == null : addr1.equalsIgnoreCase(addr2));
    }


    public void filterAin(ActionEvent actionEvent) {
        DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op = (OperationBinding) dbc.getOperationBinding("filterAin");
        op.getParamsMap().put("filterType", "INVESTIGATE");
        op.execute();
        
        DCIteratorBinding iter = (DCIteratorBinding) dbc.findIteratorBinding("ainListIterator");
        DCIteratorBinding iter1 = (DCIteratorBinding) dbc.findIteratorBinding("mailingAddrPojoListIterator");
        
        iter1.executeQuery();
        
        iter.executeQuery();
        counter = 0;
        iter.setCurrentRowIndexInRange(0);
      
    }
    
    
    public String onNext() {
//        boolean status = true;
//        String returnAction = null;
//        logger.error(MacPerformBean.class, "MacPerformBean()", "Starts ApproveAction", null);
//
//
//        if (true || validateApproveNotes()) {
//            bindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//            DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");
//            Row row = iter.getCurrentRow();
//            long filterCount = iter.getEstimatedRowCount();
//            Integer rowIndex = (Integer) row.getAttribute("indexId"); // will always have value
//            Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));
//            String assessSel = (String) row.getAttribute("priorAssesseeMailAddress");
//            remarksMandatory = false;
//            notesMandatory = true;
//            logger.error(MacPerformBean.class, "MacPerformBean()", "Ends ApproveAction", null);
//
//          
//
//        // if mailing address chnage
//            // showAllAin
//            
//            // curren is visted 
//            boolean mailAddrChange = checkmailingAddrChange(iter.getCurrentRow());
//            if(mailAddrChange) {
//                row.setAttribute("isVisited", false);
//            }
//            if(mailAddrChange || assessSel != null) {
//                saveAddressWorkUnit(Boolean.valueOf(mailAddrChange));
//                checkmailingAddrChange(iter.getCurrentRow());
//                return showAllAin(Boolean.valueOf(mailAddrChange), "INVESTIGATE","Next", rowIndex, estimateRowCount);
//            } else {
//                ;
//            }
//            
//         
//        }
        
        String returnAction = onNavigate("Next");
     //   System.out.println("1699====="+returnAction);
        if(returnAction == null) {
            return nextOperation();
        }
        
        return returnAction;
    }
    
    public String onPrevious() {
        String returnAction = onNavigate("Previous");
        if(returnAction == null) {
            return previousOperation();
        }
        
        return returnAction;
    }
    
    private String onNavigate(String navAction) {
       
            DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iter = (DCIteratorBinding) dbc.findIteratorBinding("ainListIterator");
            Row row = iter.getCurrentRow();
            long filterCount = iter.getEstimatedRowCount();
            Integer rowIndex = (Integer) row.getAttribute("indexId"); // will always have value
            Integer estimateRowCount = Integer.valueOf(this.getValueFrmExpression("#{bindings.ainSize.inputValue}"));
            String assessSel = (String) row.getAttribute("priorAssesseeMailAddress");
           // logger.error(MacPerformBean.class, "MacPerformBean()", "Ends ApproveAction", null);

          

        // if mailing address chnage
            // showAllAin
            
            // curren is visted 
            boolean mailAddrChange = checkmailingAddrChange(iter.getCurrentRow());
            if(mailAddrChange) {
                row.setAttribute("isVisited", false);
            }
            if(mailAddrChange || assessSel != null) {
                saveAddressWorkUnit(Boolean.valueOf(mailAddrChange));
                checkmailingAddrChange(iter.getCurrentRow());
                return showAllAin(Boolean.valueOf(mailAddrChange), "INVESTIGATE",navAction, rowIndex, estimateRowCount);
              
            } 
            
         return null;
     
    }
    
    public String checkAinVisited() {
        DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding op = (OperationBinding) dbc.getOperationBinding("validateAinVisited");
        Boolean result = (Boolean)op.execute();
        if(result == null) {
            setCheckAinStatus(false);
        } else {
            setCheckAinStatus(result.booleanValue());
        }
        
        ADFUtils.setPageFlowValue("hasSitusAddrInvestgn", dbc.getOperationBinding("validateSitusReq").execute());
        ADFUtils.setPageFlowValue("hasMailingAddrInvestgn", dbc.getOperationBinding("validateInvestReq").execute());
        
        return null;
    }

public String submitAction(){

                DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
              
               // DCIteratorBinding itorBinding = bindings.findIteratorBinding("AMPWorkTaskIterator");                    
                AttributeBinding aggregatedId = (AttributeBinding)bindings.getControlBinding("AggregatedId");
                System.out.println("aggregatedId in performBean:"+aggregatedId);
                
                OperationBinding op = (OperationBinding) bindings.getOperationBinding("CheckIfAAUExists");       
                op.getParamsMap().put("aggregateId", aggregatedId);        
                Boolean status=(Boolean)op.execute();                   
               // System.out.println("FROM PAGE FLOEW SCOPE"+  ADFContext.getCurrent().getPageFlowScope().get("CheckIfAAUExistsStatus"));
                //Boolean status= (Boolean) ADFContext.getCurrent().getPageFlowScope().get("CheckIfAAUExistsStatus");
                if(op.getErrors().isEmpty()){
                    //status = Boolean.FALSE;
                    if(status!=null){
                    if(status){   //
                        System.out.println("SUBMItting as the records are matching");
                        addEventParameters();              
                        return "closeTF";
                    }
                    else{
                        System.out.println("NOT Matching hence create a new one");
                        ADFContext.getCurrent().getViewScope().put("aggregatedId", aggregatedId.getInputValue());
                        RichPopup.PopupHints hints = new RichPopup.PopupHints();
                        ((RichPopup)MacUiBean.getValueFrmExpression("#{macUiBean.aggregatePopup}")).show(hints);
                        setRefresh();
                        
                    }
                }
                }
                return null;
            }

public void setRefresh() {
    Map vMap =  ADFContext.getCurrent().getViewScope();
    Boolean wuRefresh = (Boolean) vMap.get("wuRefresh");
    if(wuRefresh != null) {
        wuRefresh = !wuRefresh;
    } else {
        wuRefresh = Boolean.TRUE;
    }
    vMap.put("wuRefresh",wuRefresh);
}

public String appendAddr(String street, String city, String state, String zip, String Country) {
    StringBuffer sb = new StringBuffer();
    String sep = ", ";
    if(street != null) {
        sb.append(street);
    } else {
        sb.append("");
    }
    
    sb.append(sep);
    
   
    if(city != null) {
        sb.append(city);
    } else {
        sb.append("");
    }
    
    sb.append(sep);
    
   
    if(state != null) {
        sb.append(state);
    } else {
        sb.append("");
    }
    
    sb.append(sep);
    
   
   
   
    if(zip != null) {
        sb.append(zip);
    } else {
        sb.append("");
    }
    
    sb.append(sep);
    
 
    if(Country != null) {
        sb.append(Country);
    } else {
        sb.append("");
    }
    return sb.toString().toUpperCase();
}

    public void onEditAggrReturn(ReturnEvent returnEvent) {
      
        List<String> ainList = (List<String>) ADFContext.getCurrent().getSessionScope().remove("aggrAinList");
       
    
        DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
      
        oracle.binding.OperationBinding op = dbc.getOperationBinding("updatePayload");
 
        String filterType = "NONE";
        if(isOwnershipReviewer2()) {
            filterType = "INVESTIGATE";
        }
        op.getParamsMap().put("aggrAinList",ainList );
        op.getParamsMap().put("role",filterType );
        
        String res = (String) op.execute();
        if(!op.getErrors().isEmpty())
            logger .error("error in onEditAggrReturn.."+op.getErrors().toString());
      
        if("REFRESH".equals(res)) {
          
            DCIteratorBinding iter1 =  dbc.findIteratorBinding("mailingAddrPojoListIterator");
            
            
            iter1.executeQuery();
            DCIteratorBinding iter2=  dbc.findIteratorBinding("orgAinListIterator");
            iter2.executeQuery();
          
            ((AttributeBinding)dbc.getControlBinding("ainSize")).setInputValue(iter2.getEstimatedRowCount());
          
             op =  dbc.getOperationBinding("filterAin");
             
            op.getParamsMap().put("filterType", filterType);
            op.execute();
            
            DCIteratorBinding iter = dbc.findIteratorBinding("ainListIterator");
          
            
            iter.executeQuery();
            counter = 0;
            iter.setCurrentRowIndexInRange(0);
            AdfFacesContext.getCurrentInstance().getPageFlowScope().put("filterLinkType", null);
           AdfFacesContext.getCurrentInstance().addPartialTarget((UIComponent) MacUiBean.getValueFrmExpression("#{macUiBean.refPgl}"));
           AdfFacesContext.getCurrentInstance().addPartialTarget((UIComponent) MacUiBean.getValueFrmExpression("#{macUiBean.performNavPgl}"));
        }
      
    }
    
    
    public String showEditAggregate() {
        
        

                String Country = this.getValueFrmExpression("#{bindings.country1.inputValue}");
                String zip = this.getValueFrmExpression("#{bindings.zip1.inputValue}");
                String state = this.getValueFrmExpression("#{bindings.state1.inputValue}");
                String city = this.getValueFrmExpression("#{bindings.city1.inputValue}");
                String street = this.getValueFrmExpression("#{bindings.street1.inputValue}");
                
                String updatedIncareOf = this.getValueFrmExpression("#{bindings.inCareOf.inputValue}");

                    

                String submittedMailAddress = appendAddr(street,city,state,zip,Country);
                
               DCBindingContainer dbc = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();

                DCIteratorBinding iter2=  dbc.findIteratorBinding("orgAinListIterator");
               
                    if (iter2.getEstimatedRowCount() == 1 || ( compare(submittedMailAddress,retrivedMailingAddr) &&  compare(fetchedincareof,updatedIncareOf))) {
                        return "showEditAggregate";
                    } else {
                        JSFUtils.addFacesInformationMessage("Unable to Edit Work Unit Aggregate due to unsaved mailing address change.To Edit Work Unit Aggregate please save mailing address by taking decision or navigate to an other AIN details or undo the mailing address change.");
                                 
                        return null;
                    }
        
       
    }
    
    public String roleVal() {
        String rolevalue = "";
       
        String mileStone = (String) AdfFacesContext.getCurrentInstance().getPageFlowScope().get("milestone");
        logger.fine("roleVal milestone.."+mileStone+"=="+isOwnershipReviewer()+"=="+isOwnershipReviewer2()+"=="+isOwnershipApprover()+"=="+isOwnershipApprover2());
        if("unassigned".equalsIgnoreCase(mileStone)) {
            if (isOwnershipApprover()) {
                rolevalue = "OwnershipApprover";
               
            }
            if (isOwnershipApprover2()) {
                rolevalue = "OwnershipApprover2";
            }
        } else if (isOwnershipReviewer()) {
                rolevalue = "OwnershipReviewer";
        }  else if (isOwnershipReviewer2()) {
                rolevalue = "OwnershipReviewer2";
            
        }
        
        
        
        logger.fine("roleVal computed.."+rolevalue);
        ADFUtils.setPageFlowValue("rolevalue", rolevalue);
        return rolevalue;

    }
    
    
    private boolean isLOne() {
        return L1.equals(getLevel()); 
    }
        
    private boolean isLTwo() {
        return L2.equals(getLevel()); 
    }


    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
    
    
    public void populateLevel() {
        DCBindingContainer propIdentContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding ownerGroup = (AttributeBinding)propIdentContainer.getControlBinding("customAttributeString2");
        setLevel((String) ownerGroup.getInputValue());
        logger.fine("Task Level.."+getLevel());
    }

}
