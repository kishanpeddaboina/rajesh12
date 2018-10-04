package gov.laca.amp.modules.mac.dc;

import gov.laca.amp.common.model.triliumaddressvalidation.data.AddressPojo;
import gov.laca.amp.ent.exception.AmpException;
import gov.laca.amp.fwk.extn.dc.AmpPojoDC;
import gov.laca.amp.fwk.util.ADFUtils;
import gov.laca.amp.modules.mac.facade.MacWorkUnitMgmtFacade;
import gov.laca.amp.modules.mac.pojo.AINPojo;
import gov.laca.amp.modules.mac.pojo.AINRecordPojo;
import gov.laca.amp.modules.mac.pojo.AddrPojo;
import gov.laca.amp.modules.mac.pojo.AssessePojo;
import gov.laca.amp.modules.mac.pojo.MacPojo;
import gov.laca.amp.modules.mac.pojo.MailingAddrPayloadDO;



import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.LinkedHashMap;

import javax.faces.event.ActionEvent;

public class MacDC extends AmpPojoDC{
    public MacDC() {
        super();
    }
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;
    
   // private List<MacPojo> macPojoList = new ArrayList<MacPojo>();
    private List<AssessePojo> assessePojoList = new ArrayList<AssessePojo>();
    private List<MailingAddrPayloadDO> mailingAddrPojoList = new ArrayList<MailingAddrPayloadDO>();
    private List<AddressPojo> addrValidnForm = new ArrayList<AddressPojo>();
   
    private List<AINRecordPojo> ainRecordPojoList = new ArrayList<AINRecordPojo>();
    private String role;
    private boolean loadmac = false;
    private int ainSize = 0;

    /**
     * @param workUnitId
     * @param ains
     * @param rolevalue
     * @return
     * @throws AmpException
     */
    public String loadMacData(String workUnitId,List<AINPojo> ains,String rolevalue )throws AmpException {
        
     //   logger.error(MacDC.class, "MacWorkTaskBean()", "Start loadMacdata", null);
        
       // System.out.println("workUnitId----"+workUnitId);
        role = rolevalue;
        if (workUnitId!=null)
        {	  
          getPriorAssessee(ains);
       //   System.out.println("getAddrPayloadData : ains size->"+ains.size());
            loadmac = true;
          getAddrPayloadData(workUnitId,ains,rolevalue,false);   
            loadmac =false;
          //retrieveMacList(ains);
          //populateAINStatus();
          //DEBUG
        }
       
        
       
                
//              
                
                
               
      //  logger.error(MacDC.class, "MacWorkTaskBean()", "END loadMacdata", null);
        return "loadMacData";
    }
  
    public void setAssessePojoList(List<AssessePojo> assessePojoList) {
        this.assessePojoList = assessePojoList;
    }


    public void fetchAssesseeList(String ain, String aoid)throws AmpException {
        // Add event code here...
        
      //  System.out.println("AIN==:"+ain+",aoid:"+aoid);
        
            MacWorkUnitMgmtFacade  facade = new MacWorkUnitMgmtFacade();
            assessePojoList = facade.getAssesseeList(ain,aoid);
          //  System.out.println("MacDC : getPriorAssessee :size of list-> "+assessePojoList.size());
                    
    }
    public List<AssessePojo> getAssessePojoList() {
        
        return assessePojoList;
    }


  public void setMailingAddrPojoList(List<MailingAddrPayloadDO> mailingAddrPojoList)
  {
    this.mailingAddrPojoList = mailingAddrPojoList;
  }

  public List<MailingAddrPayloadDO> getMailingAddrPojoList()
  {
    return mailingAddrPojoList;
  }

  /**This method returns the mailing address payload
   *
   * @param workUnitId
   * @param ains
   * @throws AmpException
   */
  public void getAddrPayloadData(String workUnitId, List<AINPojo> ains,String rolevalue,boolean showallain)
    throws AmpException
  {
    //  System.out.println(" INSIDE getAddrPayloadData ");
        
//    logger.error(MacDC.class, "getAddrPayloadData()", "Start getAddrPayloadData", null);
//    //Adding for debugging
//    if(ains != null)
//    {
//      //  System.out.println("MacDC : getAddrPayloadData : Size of AINPOJO list :"+ains.size());
//      //  System.out.println("MacDC : getAddrPayloadData : Listing all AIN s passed to getPayLoad ");
////        for(int i=0;i<ains.size();i++)
////        {
////          //  System.out.println("MacDC : getAddrPayloadData : "+ains.get(i).getAin());
////        }
////        System.out.println("DC : getAddrPayloadData : End Debug");
//    }
    MacWorkUnitMgmtFacade  facade = new MacWorkUnitMgmtFacade();

    try {
    //  System.out.println("MacDC : getAddrPayloadData : workunitId "+workUnitId);
        
     //   System.out.println("rolevalue ::"+rolevalue);
      mailingAddrPojoList = facade.getAddrPayload(workUnitId, ains,rolevalue,showallain);
        
        ainSize = mailingAddrPojoList.get(0).getAinList().size();
       // System.out.println("156===="+ainSize);
        if(loadmac) {
            loadmac = false;
//            System.out.println("STREET==="+mailingAddrPojoList.get(0).getMailingAddr().getStreet());
//            System.out.println("CITY ==="+mailingAddrPojoList.get(0).getMailingAddr().getCity());
//            if(mailingAddrPojoList.get(0).getMailingAddr().getStreet() == null && mailingAddrPojoList.get(0).getMailingAddr().getCity() == null && mailingAddrPojoList.get(0).getMailingAddr().getState() == null && mailingAddrPojoList.get(0).getMailingAddr().getZip() == null && mailingAddrPojoList.get(0).getMailingAddr().getCountry() == null )
//            {    
//                    if (mailingAddrPojoList.get(0).getAinList() != null) {
//                        // get data from current ownership record
//                        if(mailingAddrPojoList.get(0).getAinList().size() == 1) { 
//                     if(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr() != null){
//                        mailingAddrPojoList.get(0).getMailingAddr().setStreet(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr().getStreet());
//                        mailingAddrPojoList.get(0).getMailingAddr().setCity(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr().getCity());
//                        mailingAddrPojoList.get(0).getMailingAddr().setState(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr().getState());
//                        mailingAddrPojoList.get(0).getMailingAddr().setZip(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr().getZip());
//                        mailingAddrPojoList.get(0).getMailingAddr().setCountry(mailingAddrPojoList.get(0).getAinList().get(0).getCurrentMailingAddr().getCountry());
//                    }
//                    }
//                 }
//            }
            
        }
    } catch (AmpException ae) {
        // TODO: Add catch code
        ae.printStackTrace();
        throw new AmpException();
    }
       
   
      populateAinData(rolevalue,showallain);
   // logger.error(MacDC.class, "getAddrPayloadData()", "End getAddrPayloadData", null);
    
  }
  
  /** This method returns the List of Prior Assessess for the ain
   * 
   * @param workUnitId
   * @throws AmpException
   */
  public void getPriorAssessee(List<AINPojo> ains)
    throws AmpException
  {
        
//    logger.error(MacDC.class, "getPriorAssessee()", "Start getPriorAssessee", null);
//    
//    MacWorkUnitMgmtFaçade  facade = new MacWorkUnitMgmtFaçade();
//     assessePojoList = facade.getAssesseeList(ains);
//      System.out.println("MacDC : getPriorAssessee :size of list-> "+assessePojoList.size());
//  
//    logger.error(MacDC.class, "getPriorAssessee()", "End getPriorAssessee", null);
    
  }


    public void saveAddressWorkUnitFacade(Boolean addrValChanged) throws AmpException {
     //   logger.error(MacDC.class, "saveAddressWorkUnitFacade()", "Start saveAddressWorkUnitFacade", null);
        MailingAddrPayloadDO addrPayloadDO = null;
        BigInteger workUnitIdFromResponse;
        MacWorkUnitMgmtFacade  facade = new MacWorkUnitMgmtFacade();
     
      if(mailingAddrPojoList.size() > 0)
          addrPayloadDO = mailingAddrPojoList.get(0);
      
       if (addrPayloadDO != null) {
            List<AINRecordPojo> orgList = addrPayloadDO.getOrgAinList();
            List<AINRecordPojo> moidifiedList = addrPayloadDO.getAinList();
          
           
            if ("OwnershipReviewer2".equals(role) && orgList != null && orgList.size() > 0  ) {
                LinkedHashMap<String, AINRecordPojo> filterAinMap = new LinkedHashMap<String, AINRecordPojo>();
                LinkedHashMap<String, AINRecordPojo> allAinMap = new LinkedHashMap<String, AINRecordPojo>();
                if (moidifiedList != null) {
                    for (AINRecordPojo ainrecord : moidifiedList) {
                        filterAinMap.put(ainrecord.getAin(), ainrecord);
                      
                    }
                }
                for (AINRecordPojo ainrecord : orgList) {
                    allAinMap.put(ainrecord.getAin(), ainrecord);
                }
                
              //  System.out.println("199===addrValChanged:"+addrValChanged);
                List<AINRecordPojo> list = new ArrayList<AINRecordPojo>();
                for (String ain : allAinMap.keySet()) {
                    AINRecordPojo ainRec;
                    if (filterAinMap.containsKey(ain)) {
                         ainRec = filterAinMap.get(ain);
                        if(Boolean.TRUE.equals(addrValChanged)) {
                         //   ainRec.setIsVisited(false); // already done on view and populate
                            ainRec.setIsOwnedByInvestigators(true);
                        }  
                    
                    } else {
                         ainRec = allAinMap.get(ain);
                        if(Boolean.TRUE.equals(addrValChanged)) {
                            ainRec.setIsVisited(false);
                            ainRec.setIsOwnedByInvestigators(true);
                        }
                        
                    }
                    
                    list.add(ainRec);

                }
                
                addrPayloadDO.setOrgAinList(list);
               


            } else {
                addrPayloadDO.setOrgAinList(addrPayloadDO.getAinList());
            }
        }
        try {
//            System.out.println("MacDC : saveAddressWorkUnitFacade : IN CARE OF :"+addrPayloadDO.getInCareOf());
//            System.out.println("MacDC : saveAddressWorkUnitFacade : getInputSource"+addrPayloadDO.getInputSource());
//            System.out.println("MacDC : saveAddressWorkUnitFacade PayloadType :"+addrPayloadDO.getPayloadType());
//            System.out.println("MacDC : saveAddressWorkUnitFacade : WorkUnit Id"+addrPayloadDO.getWorkUnitId());
//            System.out.println("addrPayloadDO Submitted value "+addrPayloadDO.getMailingAddress());
          workUnitIdFromResponse = facade.saveAddressWorkUnitFacade(addrPayloadDO);
          // System.out.println("MacDC : .saveAddressWorkUnitFacade() : workUnitIdFromResponse "+workUnitIdFromResponse);
        }catch (Exception e) {       
            e.printStackTrace();
            throw new AmpException();
        }
      //  logger.error(MacDC.class, "saveAddressWorkUnitFacade()", "End saveAddressWorkUnitFacade", null);
        
        
    }

    public void setAddrValidnForm(List<AddressPojo> addrValidnForm) {
        this.addrValidnForm = addrValidnForm;
    }

    public List<AddressPojo> getAddrValidnForm() {
        return addrValidnForm;
    }
    
    public void setAinRecordPojoList(List<AINRecordPojo> ainRecordPojoList) {
        this.ainRecordPojoList = ainRecordPojoList;
    }

    public List<AINRecordPojo> getAinRecordPojoList() {
        return ainRecordPojoList;
    }

    public void setAinSize(int ainSize) {
        this.ainSize = ainSize;
    }

    public int getAinSize() {
        return ainSize;
    }
    
    
    
    private void populateAinData(String rolevalue, boolean showAll) {
      
        List<AINRecordPojo> originalAINList = new ArrayList<AINRecordPojo>();
        List<AINRecordPojo> ainList =  mailingAddrPojoList.get(0).getAinList();
        
        for(int i=0;i< ainList.size(); i++) {
            AINRecordPojo aINRecordPojo = ainList.get(i);
            originalAINList.add(aINRecordPojo);
        }
        
//        if(ainList.size() == 1) {
//            AINRecordPojo aINRecordPojo =  originalAINList.get(0);
//            if(aINRecordPojo.getIsSitusAddrInvestigationReq()!=null && !Boolean.TRUE.equals(aINRecordPojo.getIsSitusAddrInvestigationReq()) ) {
//                aINRecordPojo.setFlagMailSitus(Boolean.TRUE);
//            } else {
//                aINRecordPojo.setFlagMailSitus(Boolean.FALSE);
//            }
//        }
    //    System.out.println("292====="+originalAINList.size());
        mailingAddrPojoList.get(0).setOrgAinList(originalAINList);
        
        // if R2 , list should show only mail Add inv 
      //  System.out.println("OwnershipReviewer2 : CHECK " +rolevalue);
        if(rolevalue=="OwnershipReviewer2") {
            List<AINRecordPojo> ainRecInvestigationList = new ArrayList<AINRecordPojo>();
         

       
          
           int mailingAddressCount = 0;
           
           for(int i=0;i< ainList.size(); i++) {
               AINRecordPojo aINRecordPojo = ainList.get(i);
               
               if(Boolean.TRUE.equals(aINRecordPojo.getIsMailingAddrInvestigationReq()) && !Boolean.TRUE.equals(aINRecordPojo.getIsOwnedByInvestigators())) {
                   aINRecordPojo.setIsVisited(false);
                   aINRecordPojo.setIsOwnedByInvestigators(Boolean.TRUE);
               }
               
               if(showAll)
               
               {
                
//                   if(aINRecordPojo.getIsOwnedByInvestigators() == null) {
//                       aINRecordPojo.setIsOwnedByInvestigators(true);
//                    //   aINRecordPojo.setIsVisited(false);
//                   }
                   ainRecInvestigationList.add(aINRecordPojo);
               } else if(Boolean.TRUE.equals(aINRecordPojo.getIsOwnedByInvestigators())) {
                   
                   ainRecInvestigationList.add(aINRecordPojo);
               }
           }
           
//            for(int i=0;i< ainList.size(); i++) {
//                AINRecordPojo aINRecordPojo = ainList.get(i);
//                
//                if(showAll || Boolean.TRUE.equals(aINRecordPojo.getIsOwnedByInvestigators()))
//                
//                {
//                    mailingAddressCount ++;
//                   
//                    ainRecInvestigationList.add(aINRecordPojo);
//                }
//            }
           
           
          
//            if(ainSize >=1 &&  mailingAddressCount ==0 ) {
//                    
//                    for(int i=0;i< ainList.size(); i++) {
//                        AINRecordPojo aINRecordPojo = ainList.get(i);
//                        mailingAddressCount ++;
//                        ainRecInvestigationList.add(aINRecordPojo);
//                    }
//                }
            
           
         //   System.out.println("ainRecInvestigationList SIZE ::"+ainRecInvestigationList.size());
            mailingAddrPojoList.get(0).setAinList(ainRecInvestigationList);
           


     //   System.out.println("originalAINList::"+originalAINList.size());
        } else if ("OwnershipApprover2".equals(rolevalue)) {
            for(int i=0;i< ainList.size(); i++) {
                AINRecordPojo aINRecordPojo = ainList.get(i);
                
                if(Boolean.TRUE.equals(aINRecordPojo.getIsMailingAddrInvestigationReq()) && !Boolean.TRUE.equals(aINRecordPojo.getIsOwnedByInvestigators())) {
                    aINRecordPojo.setIsVisited(false);
                    aINRecordPojo.setIsOwnedByInvestigators(Boolean.TRUE);
                }
            }   
        }
    }
    
    public String filterAin(String filterType, String role) {
        List<AINRecordPojo> originalAINList = mailingAddrPojoList.get(0).getOrgAinList();
        List<AINRecordPojo> ainList = new ArrayList<AINRecordPojo>();
        if("NONE".equals(filterType)) {
            for(AINRecordPojo ainrec : originalAINList) {
                ainList.add(ainrec);
            }
        } else if("INVESTIGATE".equals(filterType)) {
            for(AINRecordPojo ainrec : originalAINList) {
                if(Boolean.TRUE.equals(ainrec.getIsOwnedByInvestigators())) {
                    ainList.add(ainrec);
                }                
            }
        }
        
        mailingAddrPojoList.get(0).setAinList(ainList);
        return null;
    }
    
    public Boolean validateAinVisited() {
        List<AINRecordPojo> ainList = mailingAddrPojoList.get(0).getOrgAinList();
        if(ainList != null) {
            for(AINRecordPojo ain : ainList) {
                if(!Boolean.TRUE.equals(ain.getIsVisited()))
                    return Boolean.FALSE;
            }
        }
        
        return Boolean.TRUE;
    }

public Boolean CheckIfAAUExists(BigInteger aggregateId) throws AmpException {

            logger.error(MacDC.class, "CheckIfAAUExists()", "Start CheckIfAAUExists", null);   
            Boolean response=Boolean.FALSE;
            System.out.println("IN CheckIfAAUExists DCCCCC and aggregateId:"+aggregateId);
            MacWorkUnitMgmtFacade  facade = new MacWorkUnitMgmtFacade();
            try {
               response = facade.CheckIfAAUExists(aggregateId);
              System.out.println("response in DC:"+response);
            }catch (gov.laca.amp.proxy.soap.aggregateassessment.client.FaultMessage e) {           
                throw new AmpException();            
            }
         //   ADFContext.getCurrent().getPageFlowScope().put("CheckIfAAUExistsStatus",response);
           // System.out.println("FROM PAGE FLOEW SCOPEin DC"+  ADFContext.getCurrent().getPageFlowScope().get("CheckIfAAUExistsStatus"));
            logger.error(MacDC.class, "CheckIfAAUExists()", "End CheckIfAAUExists", null);
            return response;        
        }

    public String validateInvestReq() {
        List<AINRecordPojo> ainList = mailingAddrPojoList.get(0).getOrgAinList();
        if(ainList != null) {
            for(AINRecordPojo ain : ainList) {
                if(Boolean.TRUE.equals(ain.getIsMailingAddrInvestigationReq()))
                    return "YES";
            }
        }
        
        return "NO";
    }
    
    public String validateSitusReq() {
        List<AINRecordPojo> ainList = mailingAddrPojoList.get(0).getOrgAinList();
        if(ainList != null) {
            for(AINRecordPojo ain : ainList) {
                if(Boolean.TRUE.equals(ain.getIsSitusAddrInvestigationReq()))
                return "YES";
            }
        }
        
        return "NO";
    }
    
    
    public String updatePayload(List<String> aggrAinList, String role, Boolean showAll)  {
       
        try {
            if(aggrAinList ==  null || aggrAinList.size() == 0) {
                return "NC";
            }
            
            MailingAddrPayloadDO addrPayloadDO  = mailingAddrPojoList.get(0);
        
            List<AINRecordPojo> newAinList = new ArrayList<AINRecordPojo>(10);
            for(AINRecordPojo ainRec : addrPayloadDO.getOrgAinList()) {
                if(aggrAinList.contains(ainRec.getAin())) {
                    newAinList.add(ainRec);
                    aggrAinList.remove(ainRec.getAin());
                }
                  
                
            }
         
            if(aggrAinList.size() == 0 && newAinList.size() == addrPayloadDO.getOrgAinList().size()) {
                return "NC";
            }
            
            Boolean markInvestigate = "INVESTIGATE".equals(role);
            
            if(Boolean.TRUE.equals(markInvestigate)) {
                for(AINRecordPojo ainRec : newAinList) {
                    ainRec.setIsOwnedByInvestigators(Boolean.TRUE);
                }
            }
            
           
            MacWorkUnitMgmtFacade  facade = new MacWorkUnitMgmtFacade();
            if(aggrAinList.size() > 0)
              newAinList.addAll(facade.retrieveOwnershipAddress(aggrAinList,markInvestigate));
         
            
            addrPayloadDO.setOrgAinList(newAinList);
            facade.saveAddressWorkUnitFacade(addrPayloadDO);
            mailingAddrPojoList.clear();
            mailingAddrPojoList.add(addrPayloadDO);
            ainSize = addrPayloadDO.getOrgAinList().size();
            return "REFRESH";
           
        } catch(Exception e) {
            logger.error("Error in updatePayload", e);
            e.printStackTrace();
        }
       
        
        
        return null;
        
    }
}
