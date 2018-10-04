package gov.laca.amp.modules.mac.backing;

import gov.laca.amp.fwk.extn.view.AmpManagedBean;
import gov.laca.amp.fwk.util.ADFUtils;
import gov.laca.amp.fwk.util.JSFUtils;
import gov.laca.amp.modules.mac.pojo.AINPojo;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.StringTokenizer;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.model.AttributeBinding;
import oracle.adf.model.BindingContext;
import oracle.adf.model.OperationBinding;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.share.security.SecurityContext;

import oracle.adf.view.rich.render.ClientEvent;

import oracle.jbo.Row;

public class MacApproveBean extends AmpManagedBean {
    private String level ;
    private static final String L1 = "Level 1";
    private static final String L2 = "Level 2";
    private static transient ADFLogger _log = ADFLogger.createADFLogger(MacApproveBean.class);
    
    public MacApproveBean() {
    }

    public String loadData() {
        // Add event code here...

        System.out.println(":: loadData MacApproveBean  START ::");

        // GET values from the payload
        //        System.out.println(" LOGGED IN USER ROLE :::"+ADFUtils.getPageFlowStringValue("rolevalue"));

        List<AINPojo> ainList = new ArrayList<AINPojo>();

        DCBindingContainer aMPWorkTaskbindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding aMPWorkTaskIterator =
            (DCIteratorBinding) aMPWorkTaskbindingContainer.findIteratorBinding("AMPWorkTaskIterator");
        Row aMPWorkTaskIteratorRow = null;
        if(aMPWorkTaskIterator != null)
        {
         System.out.println("aMPWorkTaskIterator is not null");
         aMPWorkTaskIteratorRow = aMPWorkTaskIterator.getCurrentRow();
        }
        else
            System.out.println("aMPWorkTaskIteratorRow is null!!!");

//        DCBindingContainer ainIteratorbindingContainer =
//            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding ainIterator =
//            (DCIteratorBinding) ainIteratorbindingContainer.findIteratorBinding("AINIterator");
//        Row ainIteratorRow = null;
//        if(ainIterator != null)
//        {
//            System.out.println("AINIterator is not null");
//            ainIteratorRow = ainIterator.getCurrentRow();
//        }
//        else
//            System.out.println("AINIterator is null!!!");
//
//        String ain_text = (String) ainIteratorRow.getAttribute("AIN_text");
//        System.out.println("MacApprovebean : loadData : ain_text from AINIterator->"+ain_text);
        String workUnitId = null;
        if (aMPWorkTaskIteratorRow.getAttribute("WorkUnitId") != null) {
            workUnitId = aMPWorkTaskIteratorRow.getAttribute("WorkUnitId").toString();
        }
        System.out.println("workUnitId ::::" + workUnitId);

        ADFUtils.setPageFlowValue("workUnitId", workUnitId);
        
        
        DCBindingContainer propIdentContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding ainIterator = (DCIteratorBinding) propIdentContainer.findIteratorBinding("PropertyIdentificationIterator");
        Row propIdenRows[] = ainIterator.getAllRowsInRange();
        //For SERVER
        
        System.out.println("propIdenRows---"+propIdenRows);
        AINPojo a1 = null;
        if(propIdenRows!= null)
        {
            System.out.println("Check Property Identification from Payload, Size : "+propIdenRows.length);

            for(Row row:propIdenRows)
            {
                System.out.println("AIN : "+row.getAttribute("AIN"));
                System.out.println("aoid : "+row.getAttribute("AOID"));
                System.out.println("PropertyType : "+row.getAttribute("PropertyType"));
                a1 = new AINPojo();
                System.out.println(":1:");
                if (row.getAttribute("AIN") != null) {
                    a1.setAin(row.getAttribute("AIN").toString());
                }
                System.out.println(":2:");
                if(row.getAttribute("AOID") != null)
                {
                    Integer aoidInt = (Integer)row.getAttribute("AOID");
                    System.out.println("aoidInt->"+aoidInt);
                     BigInteger aoidBig = BigInteger.valueOf(aoidInt.intValue()); 
                     System.out.println("aoidBig->"+aoidBig);
                     a1.setAoid(aoidBig);
                }
                ainList.add(a1);
            }
        }
        else
        {
            System.out.println("Check Property Identification from Payload, is null");
            //For Local
            
        }
        
        ADFUtils.setPageFlowValue("ainList", ainList);
        
//        ADFUtils.setPageFlowValue("ain_text", ain_text);
//
//
//        StringTokenizer st = new StringTokenizer(ain_text, ",");
//
//        while (st.hasMoreTokens()) {
//            AINPojo a1 = new AINPojo();
//            a1.setAin(st.nextToken());
//            ainList.add(a1);
//        }

        populateLevel();
        String rolevalue = null;
        if (isOwnershipApprover()) {
            rolevalue = "OwnershipApprover";
            
        }
        if (isOwnershipApprover2()) {
            rolevalue = "OwnershipApprover2";
        }
        _log.info("Computed Role Value.."+rolevalue);
        ADFUtils.setPageFlowValue("rolevalue", rolevalue);
        
//        if (isOwnershipReviewer()) {
//            rolevalue = "OwnershipReviewer";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipReviewer");
//        }
//        if (isOwnershipReviewer2()) {
//            rolevalue = "OwnershipReviewer2";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipReviewer2");
//        }


      //  System.out.println("WUID==" + workUnitId);
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        if(rolevalue != null && rolevalue.trim().length() != 0) {
            OperationBinding op = (OperationBinding) bindings.getOperationBinding("loadMacData");
            op.getParamsMap().put("workUnitId", workUnitId);
            op.getParamsMap().put("ains", ainList);
            op.getParamsMap().put("rolevalue", rolevalue);
            op.execute();
        }
        
        

//        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
//        String[] userRoles = secCntx.getUserRoles();


//        System.out.println("userRoles====" + userRoles);
//
//        for (String userrole : secCntx.getUserRoles()) {
//            System.out.println("userrole---" + userrole);
//        }
        
        //displaying all parameters
            DCIteratorBinding paramIter = (DCIteratorBinding)bindings.findIteratorBinding("ParameterIterator");
             paramIter.executeQuery();
            Row rows[] = paramIter.getAllRowsInRange();
            System.out.println("ROWS SIZE ::"+paramIter.getEstimatedRowCount());
            for (Row row : rows) {
                String name = (String) row.getAttribute("Name");
                String value = (String) row.getAttribute("Value");
                System.out.println("APPROVE ON LOAD :  0000----name:"+name  +"----Value:"+value);
            }
            
        System.out.println(":: loadData MacApproveBean ENDS ::");

        return "approve";

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
        if (Arrays.asList(userRoles).contains("OwnershipApprover")  &&  isLOne() ) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipApprover2() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipApprover2")  &&  isLTwo()) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipReviewer() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipReviewer")) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipReviewer2() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipReviewer2")) {
            return true;
        }

        return false;

    }

    /**
     * @return
     */
    public String doAction()
    {
        System.out.println("doAction");
        Map map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        oracle.bpel.services.workflow.worklist.adf.InvokeActionBean invokeActionBean =
                                   (oracle.bpel.services.workflow.worklist.adf.InvokeActionBean)map.get("invokeActionBean");
        String result = invokeActionBean.invokeOperation();
        System.out.println("result frm the Action:"+result);

           return "closeTaskFlow";
        }



    
    /**
        * Invoke an expression
        * @param expr
        * @param returnType
        * @param argType
        * @param argument
        * @return
        */
       public static Object invokeMethodExpression(String expr, Class returnType,
                                                   Class argType,
                                                   Object argument) {
           return invokeMethodExpression(expr, returnType,
                                         new Class[] { argType },
                                         new Object[] { argument });

       }
    
    public static Object invokeMethodExpression(String expr, Class returnType,
                                                   Class[] argTypes,
                                                   Object[] args) {
           FacesContext fc = FacesContext.getCurrentInstance();
           ELContext elctx = fc.getELContext();
           ExpressionFactory elFactory =
               fc.getApplication().getExpressionFactory();
           MethodExpression methodExpr =
               elFactory.createMethodExpression(elctx, expr, returnType,
                                                argTypes);
           return methodExpr.invoke(elctx, args);
       }
    
    
    public String addEventParameters() {
            System.out.println(" APPROVE: 1090--ADD EVENT PARAMETRS ##########");
            
            
        DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding paramIter = (DCIteratorBinding)bindings.findIteratorBinding("ParameterIterator");
            
            
            DCIteratorBinding iter = (DCIteratorBinding) bindings.findIteratorBinding("ainListIterator");
//            Row iterRow = iter.getCurrentRow();
            Row iterRow[] = paramIter.getAllRowsInRange();
            
            for (Row row : iterRow) {
                boolean hasSitusAddrInvestgn= (Boolean)row.getAttribute("isSitusAddrInvestigationReq");
                boolean hasMailingAddrInvestgn = (Boolean)row.getAttribute("isMailingAddrInvestigationReq");
                System.out.println("hasSitusAddrInvestgn:"+hasSitusAddrInvestgn);
                    System.out.println("hasMailingAddrInvestgn:"+hasMailingAddrInvestgn);
                                    if((hasSitusAddrInvestgn)){
                                        System.out.println(" APPROVE: 1106 rw---:"+paramIter.getEstimatedRowCount());
                                        OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                                        op.execute();
                                        Row rw1 =paramIter.getCurrentRow();
                                        System.out.println("APPROVE:  1109 rw---:"+paramIter.getEstimatedRowCount());
                                        rw1.setAttribute("Name", "IsSitusInvestigationRequired");
                                        rw1.setAttribute("Value", "Yes");
                                    }else {
                                        OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                                        op.execute();
                                        Row rw2 =paramIter.getCurrentRow();
                                        rw2.setAttribute("Name", "IsSitusInvestigationRequired");
                                        rw2.setAttribute("Value", "No");
                                    }
                                    if(hasMailingAddrInvestgn){
                                            OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                                            op.execute();
                                            Row rw3 =paramIter.getCurrentRow();
                                        rw3.setAttribute("Name", "IsMailingInvestigationRequired");
                                        rw3.setAttribute("Value", "Yes");
                                        }else {
                                            OperationBinding op = (OperationBinding) bindings.getOperationBinding("CreateInsert");
                                            op.execute();
                                            Row rw4 =paramIter.getCurrentRow();
                                        rw4.setAttribute("Name", "IsMailingInvestigationRequired");
                                        rw4.setAttribute("Value", "No");
                                        }
            }
            
        
                            //displaying all parameters
                                 paramIter.executeQuery();
                                Row rows[] = paramIter.getAllRowsInRange();
                                System.out.println("ROWS SIZE ::"+paramIter.getEstimatedRowCount());
                                for (Row row : rows) {
                                    String name = (String) row.getAttribute("Name");
                                    String value = (String) row.getAttribute("Value");
                                    System.out.println("APPROVE:  1132----name:"+name  +"----Value:"+value);
                                }
            return null;
        }
    
    
    public String postComment() {
        _log.info("MacApproveBean : postComment");
        String comment = JSFUtils.resolveExpressionAsString("#{commentBean.value}");
        if(comment == null || comment.trim().isEmpty()) {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ctx.addMessage("Empty Comment", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter comment", "Please enter comment"));
            return null;
        } else {
            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            bindings.getOperationBinding("CreateInsert2").execute();
        }
          JSFUtils.resloveMethodExpression("#{commentBean.addComment}", Object.class, new Class[]{}, new Object[]{});
          ADFContext.getCurrent().getPageFlowScope().put("lstbpmaction", "addcomment");
          return null;
          
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
    
    private boolean isLOne() {
        return L1.equals(getLevel()); 
    }
        
    private boolean isLTwo() {
        return L2.equals(getLevel()); 
    }
    
    public void onUnload(ClientEvent clientEvent) {
            // Add event code here...
            
            try {
                String action = (String) ADFContext.getCurrent().getPageFlowScope().get("lstbpmaction");
                if("addcomment".equalsIgnoreCase(action)) {
                    updateBPMTask();   
                }
                  
            } catch(Exception e) {
                e.printStackTrace();
                _log.severe("onUnload", e);
            }
           
          //  closeTaskFlow();

        }
    
    private void updateBPMTask() {
            DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
            oracle.binding.OperationBinding op = bindings.getOperationBinding("update");
            op.execute();
            
        }
    
    
    
}
