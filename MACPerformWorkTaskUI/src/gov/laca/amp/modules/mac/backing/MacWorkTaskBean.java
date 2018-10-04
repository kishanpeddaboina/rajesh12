package gov.laca.amp.modules.mac.backing;

import gov.laca.amp.fwk.util.ADFUtils;
import gov.laca.amp.fwk.util.JSFUtils;
import gov.laca.amp.modules.mac.pojo.AINPojo;

import java.io.Serializable;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.StringTokenizer;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

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
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

public class MacWorkTaskBean implements Serializable{
    private transient RichOutputText userIdText;
    public String userId = null;
   private static transient ADFLogger _log = ADFLogger.createADFLogger(MacWorkTaskBean.class);

    public MacWorkTaskBean() {
    }

    public boolean isOwnershipApprover() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipApprover")) {
            return true;
        }

        return false;

    }

    public boolean isOwnershipApprover2() {

        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String[] userRoles = secCntx.getUserRoles();
        if (Arrays.asList(userRoles).contains("OwnershipApprover2")) {
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

    public String loadData() {
        // Add event code here...

        System.out.println(":: MacWorkTaskBean loadData START ::");
        List<AINPojo> ainList = new ArrayList<AINPojo>();
//        String rolevalue = null;
//
//        if (isOwnershipApprover()) {
//            rolevalue = "OwnershipApprover";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipApprover");
//            ADFContext.getCurrent()
//                      .getPageFlowScope()
//                      .put("rolevalue", rolevalue);
//        }
//        if (isOwnershipApprover2()) {
//            rolevalue = "OwnershipApprover2";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipApprover2");
//            ADFContext.getCurrent()
//                      .getPageFlowScope()
//                      .put("rolevalue", rolevalue);
//        }
//        if (isOwnershipReviewer()) {
//            rolevalue = "OwnershipReviewer";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipReviewer");
//            ADFContext.getCurrent()
//                      .getPageFlowScope()
//                      .put("rolevalue", rolevalue);
//        }
//        if (isOwnershipReviewer2()) {
//            rolevalue = "OwnershipReviewer2";
//            ADFUtils.setPageFlowValue("rolevalue", "OwnershipReviewer2");
//            ADFContext.getCurrent()
//                      .getPageFlowScope()
//                      .put("rolevalue", rolevalue);
//        }
//
//        System.out.println(" LOGGED IN USER ROLE on LOAD :::" + ADFUtils.getPageFlowStringValue("rolevalue"));
        //        // GET values from the payload
        DCBindingContainer aMPWorkTaskbindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding aMPWorkTaskIterator =
            (DCIteratorBinding) aMPWorkTaskbindingContainer.findIteratorBinding("AMPWorkTaskIterator");
        Row aMPWorkTaskIteratorRow = aMPWorkTaskIterator.getCurrentRow();

        //Before Refactoring
        //        DCBindingContainer ainIteratorbindingContainer = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        //        DCIteratorBinding ainIterator = (DCIteratorBinding) ainIteratorbindingContainer.findIteratorBinding("AINIterator");
        //        Row ainIteratorRow = ainIterator.getCurrentRow();
        //
        //        String ain_text = (String) ainIteratorRow.getAttribute("AIN_text");
        //
        //        String workUnitId = null;
        //        if (aMPWorkTaskIteratorRow.getAttribute("WorkUnitId") != null) {
        //            workUnitId = aMPWorkTaskIteratorRow.getAttribute("WorkUnitId").toString();
        //        }
        //        System.out.println("workUnitId ::::" + workUnitId);
        //        System.out.println("ain_text===" + ain_text);
        //
        //        ADFUtils.setPageFlowValue("workUnitId", workUnitId);
        //        ADFUtils.setPageFlowValue("ain_text", ain_text);

        //After Refactoring - Sapna - 29 NOv
        String workUnitId = null;
        if (aMPWorkTaskIteratorRow.getAttribute("WorkUnitId") != null) {
            workUnitId = aMPWorkTaskIteratorRow.getAttribute("WorkUnitId").toString();
        }
        System.out.println("workUnitId ::::" + workUnitId);
//        DCBindingContainer propIdentContainer =
//            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
//        RowSetIterator ainIterator =  propIdentContainer.findIteratorBinding("PropertyIdentificationIterator").getViewObject().createRowSetIterator(null);
//      //  Row propIdenRows[] = ainIterator.getAllRowsInRange();
//        //For SERVER
//
//       // System.out.println("propIdenRows---" + propIdenRows);
//        AINPojo a1 = null;
//       String primaryAin  = null;
//            while (ainIterator.hasNext()) {
//                Row row = ainIterator.next();
//                System.out.println("AIN : " + row.getAttribute("AIN"));
//                System.out.println("aoid : " + row.getAttribute("AOID"));
//                System.out.println("PropertyType : " + row.getAttribute("PropertyType"));
//                a1 = new AINPojo();
//               
//                a1.setAin((String) row.getAttribute("AIN"));
//                if(row.getAttribute("AOID") != null)
//                {
//                    Integer aoidInt = (Integer)row.getAttribute("AOID");
//                    System.out.println("aoidInt->"+aoidInt);
//                     BigInteger aoidBig = BigInteger.valueOf(aoidInt.intValue()); 
//                     System.out.println("aoidBig->"+aoidBig);
//                     a1.setAoid(aoidBig);
//                }
//                
//                if(Boolean.TRUE.equals(row.getAttribute("IsPrimary"))) {
//                    primaryAin = (String) row.getAttribute("AIN");
//                }
//                ainList.add(a1);
//            }
//            
//        ainIterator.closeRowSetIterator();
//        ADFUtils.setPageFlowValue("wuPriAIN", primaryAin);
//        if(primaryAin == null) {
//            _log.severe("No Primary AIN In BPM payload");
//        }
//      
//
//        ADFUtils.setPageFlowValue("ainList", ainList);rolevalue
        String milestone =  (String)((AttributeBinding)aMPWorkTaskbindingContainer.getControlBinding("Milestone")).getInputValue();
        AdfFacesContext.getCurrentInstance().getPageFlowScope().put("milestone", milestone);
        ADFUtils.setPageFlowValue("workUnitId", workUnitId);
        MacPerformBean mpb = new MacPerformBean();
        mpb.populateLevel();
        mpb.roleVal();

        return "loadMacData";
    }

    public void setUserIdText(RichOutputText userIdText) {
        this.userIdText = userIdText;
    }

    public RichOutputText getUserIdText() {
        return userIdText;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return ADFContext.getCurrent()
                         .getSecurityContext()
                         .getUserProfile()
                         .getUserID();
    }

    public void ApproveOperation(ActionEvent actionEvent) {
        // Add event code here...

        System.out.println("INSIDE ApproveOperation");

        MacPerformBean performbean = new MacPerformBean();

        performbean.saveAddressWorkUnit(null);

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        //AttributeBinding rsnCode =
        // ((AttributeBinding)bindings.getControlBinding("reasonCode"));

        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");


        Row row = iter.getCurrentRow();

        row.setAttribute("isApproveAddrChange", true);
        row.setAttribute("isVisited", true);

        invokeMethodExpression("#{invokeActionBean.setOperation}", Object.class, ActionEvent.class, actionEvent);

        System.out.println("EXIT ApproveOperation");

    }


    public void denyOperation(ActionEvent actionEvent) {
        // Add event code here...

        System.out.println("INSIDE denyOperation");

        MacPerformBean performbean = new MacPerformBean();

        performbean.saveAddressWorkUnit(null);

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        //AttributeBinding rsnCode =
        // ((AttributeBinding)bindings.getControlBinding("reasonCode"));

        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");


        Row row = iter.getCurrentRow();

        row.setAttribute("isDenyAddrChange", true);
        row.setAttribute("isVisited", true);

        invokeMethodExpression("#{invokeActionBean.setOperation}", Object.class, ActionEvent.class, actionEvent);

        System.out.println("EXIT denyOperation");

    }


    public void mailingAddrInvestOperation(ActionEvent actionEvent) {
        // Add event code here...

        System.out.println("INSIDE mailingAddrInvestOperation");

        MacPerformBean performbean = new MacPerformBean();

        performbean.saveAddressWorkUnit(null);

        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        //AttributeBinding rsnCode =
        // ((AttributeBinding)bindings.getControlBinding("reasonCode"));

        DCBindingContainer bindingContainer =
            (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding iter = (DCIteratorBinding) bindingContainer.findIteratorBinding("ainListIterator");


        Row row = iter.getCurrentRow();

        row.setAttribute("isMailingAddrInvestigationReq", true);
        row.setAttribute("isVisited", true);

        invokeMethodExpression("#{invokeActionBean.setOperation}", Object.class, ActionEvent.class, actionEvent);

        System.out.println("EXIT mailingAddrInvestOperation");

    }

    //helper method to execute the QueryListener EL

    void invokeMethodExpression(String expr, QueryEvent queryEvent) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elContext = fctx.getELContext();
        ExpressionFactory eFactory = fctx.getApplication().getExpressionFactory();
        MethodExpression mexpr =
            eFactory.createMethodExpression(elContext, expr, Object.class, new Class[] { QueryEvent.class });
        mexpr.invoke(elContext, new Object[] { queryEvent });
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
    
    public String postComment() {
            _log.info("PropClaimPerformBean : postComment");
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
                      ADFContext.getCurrent().getSessionScope().put("lstbpmaction", "addcomment");
                      return null;

        }

}
