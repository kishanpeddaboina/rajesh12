package gov.laca.amp.modules.mac.facade;

import gov.laca.amp.ent.exception.AmpException;
import gov.laca.amp.fwk.extn.facade.AmpProxyFacade;
import gov.laca.amp.modules.mac.pojo.AINPojo;
import gov.laca.amp.modules.mac.pojo.AINRecordPojo;
import gov.laca.amp.modules.mac.pojo.AddrPojo;
import gov.laca.amp.modules.mac.pojo.AssessePojo;

import gov.laca.amp.modules.mac.pojo.MacPojo;
import gov.laca.amp.modules.mac.pojo.MailingAddrPayloadDO;
import gov.laca.amp.proxy.soap.addressmanagement.client.FaultMessage;
import gov.laca.amp.proxy.soap.addressmanagement.client.gen.CurrentAddressType;

import gov.laca.amp.proxy.soap.addressmanagement.client.gen.OwnershipAddrRecordType;
import gov.laca.amp.proxy.soap.addressmanagement.client.gen.OwnershipInfoRequestType;
import gov.laca.amp.proxy.soap.addressmanagement.client.gen.OwnershipInfoResponseType;
import gov.laca.amp.proxy.soap.addressmanagement.client.gen.RetrieveCurrentOwnershipInfoRequest;
import gov.laca.amp.proxy.soap.addressmanagement.client.gen.RetrieveCurrentOwnershipInfoResponse;
import gov.laca.amp.proxy.soap.addressmanagement.local.MailingAddrAssocMgmtServiceWrapper;
;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.AddressType;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.MailingAddrPayload;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.ManageAddrWorkUnitRequest;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.PayloadListType;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.RetrieveAddrPayloadRequest;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.RetrieveAddrPayloadResponse;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.local.MailingAddrAssocWrapperServiceWrapper;
import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.ManageAddrWorkUnitRequest.UserDetails;

import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.ManageAddrWorkUnitResponse;

import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.PropertyIdentificationList;

import gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.gen.PropertyIdentificationType;

import gov.laca.amp.proxy.soap.aggregateassessment.local.AggregateAssessmentUnitServiceWrapper;
import gov.laca.amp.proxy.soap.tam.unlockedainsmgmtsvc.client.gen.WFUnlockedAINListType;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.StringTokenizer;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.adf.share.ADFContext;
import oracle.adf.share.security.SecurityContext;

public class MacWorkUnitMgmtFacade extends AmpProxyFacade {
    public MacWorkUnitMgmtFacade() {
        super();
    }


//    /**
//     * @param ain
//     * @param aoid
//     * @return
//     * @throws AmpException
//     */
//    public List<MacPojo> retrieveMacList(List<AINPojo> ains) throws AmpException {
//        logger.error(MacWorkUnitMgmtFaçade.class, "retrieveMacList()", "Start retrieveMacList", null);
//        RetrieveCurrentOwnershipInfoResponse response;
//        List<MacPojo> macPojoList = new ArrayList<MacPojo>();
//        MailingAddrAssocMgmtServiceWrapper wrapper = new MailingAddrAssocMgmtServiceWrapper();
//
//        List<RetrieveCurrentOwnershipInfoResponse.CurrentOwnershipInfoList.CurrentOwnershipInfo> currentOwnershipInfo = null;
//
//        RetrieveCurrentOwnershipInfoResponse.CurrentOwnershipInfoList currentOwnershipInfoList = null;
//
//        //        PsrMgmtServiceWrapper.sql.Date date = null;
//        if (ains != null) {
//            {
//                try {
//                    RetrieveCurrentOwnershipInfoRequest request = new RetrieveCurrentOwnershipInfoRequest();
//                    //RetrieveCurrentOwnershipInfoRequest.AINList ainList = request.getAINList();
//
//                    //Refactoring Change
//                    //RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord ainRecReq = null;
//                  //  List<RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord> ainRecReqList = ainList.getAINRecord();
//                  
//                    RetrieveCurrentOwnershipInfoRequest.AINList ainList = new RetrieveCurrentOwnershipInfoRequest.AINList();
//                    RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord ainRecReq = new RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord();
//                    //retrieveOwnerShipDetails
//                  
//                    System.out.println("ains ::: ----" + ains.size());
//                    for (int i = 0; i < ains.size(); i++) {
//                        ainRecReq = new RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord();
//                        ainRecReq.setAIN(ains.get(i).getAin());
//                        if (ains.get(i).getAoid() != null) {
//                            ainRecReq.setAOID(ains.get(i).getAoid());
//                        }
//                        ainList.getAINRecord().add(ainRecReq);
//                        //ainRecReqList.add(ainRecReq);
//                    }
//
//                    //End Refactoring Change
//                    request.setAINList(ainList);
//                    System.out.println("MacWorkUnitMgmtFaçade : retrieveMacList:AIN s to retrieve Ownership req" + request.getAINList()
//                                                                                                                          .getAINRecord()
//                                                                                                                          .size());
//
//                 
//                    response = wrapper.retrieveOwnerShipDetailsForMultipleAINs(request);
//
//                    //  RetrieveCurrentOwnershipInfoResponse retrieveOwnerShipDetailsForMultipleAINs(RetrieveCurrentOwnershipInfoRequest request)
//
//                    currentOwnershipInfoList = response.getCurrentOwnershipInfoList();
//                    System.out.println("currentOwnershipInfoList===" + currentOwnershipInfoList);
//                    List<RetrieveCurrentOwnershipInfoResponse.CurrentOwnershipInfoList.CurrentOwnershipInfo> list = currentOwnershipInfoList.getCurrentOwnershipInfo();
//
//                    if (list != null)
//                        for (int i = 0; i < list.size(); i++) {
//
//                            MacPojo pojo = new MacPojo();
//                            if (list.get(i) != null) {
//                                pojo.setAin(list.get(i).getAIN());
//                                pojo.setAoid((list.get(i).getAOID()));
//
//                                CurrentAddressType type = list.get(i).getCurrentSitusAddress();
//                                if (type != null) {
//                                    String currentSituAddress = type.getStreet() + ", " + type.getCity() + ", " + type.getState() + ", " + type.getZip() + ", " + type.getCountry();
//                                    pojo.setCurrentSitusAddress(currentSituAddress);
//                                }
//                                CurrentAddressType caType = list.get(i).getCurrentMailingAddress();
//                                if (caType != null) {
//
//                                    String currentMailingAddress = caType.getStreet() + ", " + caType.getCity() + ", " + caType.getState() + ", " + caType.getZip() + ", " + caType.getCountry();
//                                    pojo.setCurrentmailingAddress(currentMailingAddress);
//                                }
//                                pojo.setDocId(list.get(i).getDocumentID());
//                                pojo.setSeqNumber(list.get(i).getDocumentSeq());
//                                pojo.setRecordingDate((toSQLDate(list.get(i).getRecordingDate())));
//                                pojo.setAssesseeName(list.get(i).getAssesseeName());
//
//                            }
//
//                            macPojoList.add(pojo);
//
//                        }
//
//                } catch (Exception e) {
//                    // TODO: Add catch code
//                    e.printStackTrace();
//                    throw new AmpException();
//                }
//            }
//        }
//
//        logger.error(MacWorkUnitMgmtFaçade.class, "retrieveMacList()", "End retrieveMacList", null);
//        return macPojoList;
//    }

    /** This method calls the retrieveAddrPayload method in wrapper to retrieve the Address Payload
     * @param workUnitId
     * @param ains
     * @return
     */
    public List<MailingAddrPayloadDO> getAddrPayload(String workUnitId, List<AINPojo> ains, String rolevalue,boolean showallain) throws AmpException {
      //  logger.error(MacWorkUnitMgmtFaçade.class, "getAddrPayload()", "Start getAddrPayload", null);
        RetrieveAddrPayloadRequest request = new RetrieveAddrPayloadRequest();
        RetrieveAddrPayloadResponse response = null;
        MailingAddrPayload macPayLoad = null;
        List<MailingAddrPayloadDO> addrPayLoadList = new ArrayList<MailingAddrPayloadDO>();
        List<AINRecordPojo> ainRecList = new ArrayList<AINRecordPojo>();
        List<AINRecordPojo> ainRecInvestigationList = new ArrayList<AINRecordPojo>();
        List<MailingAddrPayload.AINList.AINRecord> ainRec = null;
        MailingAddrPayloadDO addrPayloadDO = null;
        AddrPojo situsAddrPojo = null;
        AddrPojo mailingAddrPojo = null;
        PropertyIdentificationList propertyIdentificationList = new PropertyIdentificationList();
        PropertyIdentificationType propIdentType = null;
        AINRecordPojo ainRecPojo = null;
        if (ains != null) {
            //Refactoring Change
            System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : WorkUnitId from param" + workUnitId);
          //  System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : looping and adding AINs from AINs Pojo" + ains.size());
            for (int i = 0; i < ains.size(); i++) {
                propIdentType = new PropertyIdentificationType();
                propIdentType.setAIN(ains.get(i).getAin());
                if ("Y".equals(ains.get(i).getIsPrimary()))
                    propIdentType.setIsPrimary(true);
                else
                    propIdentType.setIsPrimary(false);
                propertyIdentificationList.getPropertyIdentification().add(propIdentType);
            }

            //End Refactoring Change
            request.setPropertyIdentificationList(propertyIdentificationList);


            System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : AIN Records after addition->" + propertyIdentificationList.getPropertyIdentification().size());

            request.setWUID(new BigInteger(workUnitId));

            try {
                MailingAddrAssocWrapperServiceWrapper wrapper = new MailingAddrAssocWrapperServiceWrapper();
                response = wrapper.retrieveAddrPayload(request);
                if (response.getPayloadList() != null && response.getPayloadList().getPayloadRecord() != null)
//                    System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : response->" + response.getPayloadList()
//                                                                                                       .getPayloadRecord()
//                                                                                                       .size());
                for (int i = 0; i < response.getPayloadList()
                                            .getPayloadRecord()
                                            .size(); i++) {
                    addrPayloadDO = new MailingAddrPayloadDO();
                    addrPayloadDO.setWorkUnitId(workUnitId);
                    addrPayloadDO.setPayloadType("MAILING ADDRESS");
                    addrPayloadDO.setInputSource("WorkTask");
                    if (response.getPayloadList()
                                .getPayloadRecord()
                                .get(i) != null) {
                        macPayLoad = response.getPayloadList()
                                             .getPayloadRecord()
                                             .get(i)
                                             .getMailingAddrPayload();
                        if (macPayLoad != null) {
                           // System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : macPayload careOf->" + macPayLoad.getCareOf());

                            addrPayloadDO.setInCareOf(macPayLoad.getCareOf());
                            addrPayloadDO.setOrigInCareOf(macPayLoad.getCareOf());
                            StringBuffer addressBuff = new StringBuffer();
                            ;
                            if (macPayLoad.getMailingAddr() != null) {
                                if(macPayLoad.getMailingAddr().getStreet() != null)
                                    addressBuff = addressBuff.append(macPayLoad.getMailingAddr().getStreet());
                                if(macPayLoad.getMailingAddr().getCity() != null)
                                    addressBuff = addressBuff.append(",").append(macPayLoad.getMailingAddr().getCity());
                                if(macPayLoad.getMailingAddr().getState() != null)
                                    addressBuff = addressBuff.append(",").append(macPayLoad.getMailingAddr().getState());
                                if(macPayLoad.getMailingAddr().getZip() != null)
                                    addressBuff = addressBuff.append(",").append(macPayLoad.getMailingAddr().getZip());
                                if(macPayLoad.getMailingAddr().getCountry() != null)
                                    addressBuff = addressBuff.append(",").append(macPayLoad.getMailingAddr().getCountry());
                              //  System.out.println("MacWorkUnitMgmtFaçade : getAddrPayload : macPayload mailingAddr->" + addressBuff.toString());

                            }
                            // mail Addr start
                            AddrPojo mailingAddr = new AddrPojo();
                            mailingAddr.setStreet(macPayLoad.getMailingAddr().getStreet());
                            mailingAddr.setCity(macPayLoad.getMailingAddr().getCity());
                            mailingAddr.setState(macPayLoad.getMailingAddr().getState());
                            mailingAddr.setZip(macPayLoad.getMailingAddr().getZip());
                            mailingAddr.setCountry(macPayLoad.getMailingAddr().getCountry());
                            
                            addrPayloadDO.setMailingAddr(mailingAddr);
                            // mail Addr end
                            
                           
                            
                            
                            addrPayloadDO.setMailingAddress(addressBuff.toString());
                            addrPayloadDO.setAgentName(macPayLoad.getAgentName());
                            addrPayloadDO.setDate(toSQLDate(macPayLoad.getDate()));
                            addrPayloadDO.setEmail(macPayLoad.getEmail());
                            addrPayloadDO.setIsAuthorizedAgent(macPayLoad.isIsAuthorizedAgent());
                            addrPayloadDO.setIsSetasSitusAddr(macPayLoad.isIsSetAsSitusAddr());
                            addrPayloadDO.setPhone(macPayLoad.getPhone());
                            addrPayloadDO.setPropertyOwnerName(macPayLoad.getPropertyOwnerName());
                            addrPayloadDO.setSignature(macPayLoad.getSignature());
                            
                            AddrPojo assessCurMailingAddr = new AddrPojo();
                            if (macPayLoad.getMailingAddr() != null)
                            {
                            assessCurMailingAddr.setStreet(macPayLoad.getMailingAddr().getStreet());
                            assessCurMailingAddr.setCity(macPayLoad.getMailingAddr().getCity());
                            assessCurMailingAddr.setState(macPayLoad.getMailingAddr().getState());
                            assessCurMailingAddr.setZip(macPayLoad.getMailingAddr().getZip());
                            assessCurMailingAddr.setCountry(macPayLoad.getMailingAddr().getCountry());
                            }
                            addrPayloadDO.setAssessCurMailingAddr(assessCurMailingAddr);
                            ainRec = macPayLoad.getAINList().getAINRecord();
                            if (ainRec != null) {
                               // System.out.println("** MacWorkUnitMgmtFaçade : getAddrPayload :AINList size->" + ainRec.size());
                                for (int j = 0; j < ainRec.size(); j++) {
                                    ainRecPojo = new AINRecordPojo();
                                    ainRecPojo.setIndexId(Integer.valueOf(j));
                                    if (macPayLoad.getMailingAddr() != null) {
                                    ainRecPojo.setOriginalMailAddress(macPayLoad.getMailingAddr().getStreet()+", "+macPayLoad.getMailingAddr().getCity()+", "+macPayLoad.getMailingAddr().getState()+", "+macPayLoad.getMailingAddr().getZip()+", "+macPayLoad.getMailingAddr().getCountry());
                                    }
                                    
                                    ainRecPojo.setAin(ainRec.get(j).getAIN());
                                    String sain = ainRecPojo.getAin();
                                    if(sain != null && sain.length() == 10) {
                                         ainRecPojo.setAinDisplay(sain.substring(0, 4) + "-" + sain.substring(4, 7) + "-" + sain.substring(7, 10));
                                    }
                                    ainRecPojo.setAoid(ainRec.get(j).getAOID());
                                    ainRecPojo.setOhid(ainRec.get(j).getOHID());
                                    ainRecPojo.setAssesseeName(ainRec.get(j).getAssesseeName());
                                    ainRecPojo.setCluster(ainRec.get(j).getCluster());
                                    ainRecPojo.setCommunityName(ainRec.get(j).getCommunityName());
                                    ainRecPojo.setDistrict(ainRec.get(j).getDistrict());
                                    ainRecPojo.setDocId(ainRec.get(j).getDocumentID());
                                    ainRecPojo.setSeqNumber(ainRec.get(j).getDocumentSequence());
                                    ainRecPojo.setIsApproveAddrChange(ainRec.get(j).isIsApproveAddrChange());
                                    ainRecPojo.setIsDenyAddrChange(ainRec.get(j).isIsDenyAddrChange());
                                    ainRecPojo.setIsMailingAddrInvestigationReq(ainRec.get(j).isIsMailingAddrInvestigationReq());
                                   // System.out.println("IS INVESTIGATION ?" + ainRec.get(j).isIsMailingAddrInvestigationReq());
                                    boolean investigationRequired = false;
                                    if (Boolean.TRUE.equals(ainRec.get(j).isIsMailingAddrInvestigationReq())) {
                                        investigationRequired = true;
                                    }
                                    ainRecPojo.setIsSitusAddrInvestigationReq(ainRec.get(j).isIsSitusAddrInvestigationReq());
                                    ainRecPojo.setIsOwnedByInvestigators(ainRec.get(j).isIsOwnedByInvestigators());
                                 //   System.out.println("ainRec.get(j).isIsVisited()----"+ainRec.get(j).isIsVisited());
                                    ainRecPojo.setIsVisited(ainRec.get(j).isIsVisited());
                                    ainRecPojo.setParcelStatus(ainRec.get(j).getParcelStatus());
                                    ainRecPojo.setRecordingDate(toSQLDate(ainRec.get(j).getRecordingDate()));
                                    ainRecPojo.setRegion(ainRec.get(j).getRegion());
                                    ainRecPojo.setRemarks(ainRec.get(j).getRemarks());
                                    if (ainRec.get(j).getCurrentMailingAddress() != null) {
                                  //  ainRecPojo.setOriginalMailAddress(ainRec.get(j).getCurrentMailingAddress().getStreet()+", "+ainRec.get(j).getCurrentMailingAddress().getCity()+", "+ainRec.get(j).getCurrentMailingAddress().getState()+", "+ainRec.get(j).getCurrentMailingAddress().getZip()+", "+ainRec.get(j).getCurrentMailingAddress().getCountry());
                                    ainRecPojo.setCurrentMailingAddress(appendAddr(ainRec.get(j).getCurrentMailingAddress().getStreet(),ainRec.get(j).getCurrentMailingAddress().getCity(),ainRec.get(j).getCurrentMailingAddress().getState(),ainRec.get(j).getCurrentMailingAddress().getZip(),ainRec.get(j).getCurrentMailingAddress().getCountry()));
                                   }
                                ainRecPojo.setCurrentSitusAddress(appendAddr(ainRec.get(j).getCurrentSitusAddress().getStreet(),ainRec.get(j).getCurrentSitusAddress().getCity(),ainRec.get(j).getCurrentSitusAddress().getState(),ainRec.get(j).getCurrentSitusAddress().getZip(),ainRec.get(j).getCurrentSitusAddress().getCountry()));


                                    //Update Situs Address
                                    situsAddrPojo = new AddrPojo();
                                    if (ainRec.get(j).getCurrentSitusAddress() != null) {
                                        situsAddrPojo.setCity(ainRec.get(j).getCurrentSitusAddress().getCity());
                                        situsAddrPojo.setState(ainRec.get(j).getCurrentSitusAddress().getState());
                                        situsAddrPojo.setStreet(ainRec.get(j).getCurrentSitusAddress().getStreet());
                                        situsAddrPojo.setCountry(ainRec.get(j).getCurrentSitusAddress().getCountry());
                                        situsAddrPojo.setZip(ainRec.get(j).getCurrentSitusAddress().getZip());
                                    }
                                    ainRecPojo.setCurrentSitusAddr(situsAddrPojo);
                                    //Update mailing Address
                                    mailingAddrPojo = new AddrPojo();
                                   // System.out.println("DEBUG MAILING ADDRESS : GET PAYLOAD, Print street and city if not null");
                                    if (ainRec.get(j).getCurrentMailingAddress() != null) {                                        
                                        System.out.println("STREET "+ainRec.get(j).getCurrentMailingAddress().getStreet());
                                        System.out.println("CITY "+ainRec.get(j).getCurrentMailingAddress().getCity());                                       
                                        mailingAddrPojo.setCity(ainRec.get(j).getCurrentMailingAddress().getCity());
                                        mailingAddrPojo.setState(ainRec.get(j).getCurrentMailingAddress().getState());
                                        mailingAddrPojo.setStreet(ainRec.get(j).getCurrentMailingAddress().getStreet());
                                        mailingAddrPojo.setCountry(ainRec.get(j).getCurrentMailingAddress().getCountry());
                                        mailingAddrPojo.setZip(ainRec.get(j).getCurrentMailingAddress().getZip());
                                    }
                                   // System.out.println("Remarks while retrieveing : "+ainRec.get(j).getRemarks());
                                  //  System.out.println("Notes while retrieveing : "+ainRec.get(j).getSitusAddrInvestigationNotes());
                                    ainRecPojo.setRemarks(ainRec.get(j).getRemarks());
                                    ainRecPojo.setSitusAddrInvestigationNotes(ainRec.get(j).getSitusAddrInvestigationNotes());
                                    ainRecPojo.setCurrentMailingAddr(mailingAddrPojo);
                                    // if(rolevalue =="OwnershipApprover" && investigationRequired)
                                  //  System.out.println("rolevalue before populating the list--->" + rolevalue);
                                    if ("OwnershipReviewer2".equals(rolevalue) && investigationRequired )
                                        ainRecInvestigationList.add(ainRecPojo);
                                    
                                    ainRecPojo.setInCareOf(ainRec.get(j).getCareOfName());
                                    ainRecList.add(ainRecPojo);
                                }
                            }

                          //  System.out.println("AinRecList  SIZE :::: " + ainRecList.size());
                            //System.out.println("ainRecInvestigationList  SIZE :::: " + ainRecInvestigationList.size());

//and showallain== false 
                          //  System.out.println("rolevalue=="+rolevalue+"::"+"value---"+showallain);
//                            if ("OwnershipReviewer2".equals(rolevalue) && !showallain ) {
//                                System.out.println("IF");
//                                addrPayloadDO.setAinList(ainRecInvestigationList);
//                            } else {
//                                System.out.println("ELSE");
//                                addrPayloadDO.setAinList(ainRecList);
//                            }

                            addrPayloadDO.setAinList(ainRecList);
                            addrPayLoadList.add(addrPayloadDO);
                            //macPayLoad.
                           // System.out.println("AINLIST  SIZE ::"+addrPayLoadList.get(0).getAinList().size());
                        }
                    }
                }
            }

            catch (Exception e) {
                // TODO: Add catch code
                e.printStackTrace();
                throw new AmpException();
            }
        } //End AIN check
      //  System.out.println("EXIT :: " + addrPayLoadList.size());
        return addrPayLoadList;

    }

    /**
     * @param ain
     * @param aoid
     * @return
     * @throws AmpException
     */
    public List<AssessePojo> getAssesseeList(String ain, String aoid) throws AmpException {
        logger.error(MacWorkUnitMgmtFacade.class, "getAssesseeList()", "Start getAssesseeList", null);
        OwnershipInfoRequestType request = new OwnershipInfoRequestType();
        OwnershipInfoResponseType response = null;
        MailingAddrPayload macPayLoad = null;
        List<AssessePojo> assessePojoList = new ArrayList<AssessePojo>();
        AssessePojo assessPojo = null;

        List<OwnershipAddrRecordType> ownershipList = null;
        OwnershipAddrRecordType ownerShipRecType = null;
      //  System.out.println("MacWorkUnitMgmtFaçade : Start getAssesseeList : ");
        try {

            if (ain!= null) {
                MailingAddrAssocMgmtServiceWrapper wrapper = new MailingAddrAssocMgmtServiceWrapper();
                response = wrapper.retrieveAssesseDetails(ain, aoid);
                if (response != null) {
                    ownershipList = response.getOwnershipAddrList().getOwnershipAddrRecord();
                    if (ownershipList != null) {
                      //  System.out.println("**AIN:::" + ain + ",::AOID::" + aoid);

                       // System.out.println("**************ownershipList.size()====" + ownershipList.size());
                        for (int i = 0; i < ownershipList.size(); i++) {
                            if (ownershipList.get(i) != null) {
                                ownerShipRecType = ownershipList.get(i);
                                assessPojo = new AssessePojo();
                                assessPojo.setAssesseeName(ownerShipRecType.getOwnerName());
                                assessPojo.setRecordingDate(toSQLDate(ownerShipRecType.getRecordingDate()));
                                assessPojo.setDocId(ownerShipRecType.getDocumentID());
                                assessPojo.setSeqNumber(ownerShipRecType.getDocumentSeq());
                                assessPojo.setUpdatedDate(toSQLDate(ownerShipRecType.getUpdatedDate()));
                                assessPojo.setOhid(ownerShipRecType.getOHID());
                                if (ownerShipRecType.getAddress() != null) {
                                    assessPojo.setCity(ownerShipRecType.getAddress().getCity());
                                    assessPojo.setState(ownerShipRecType.getAddress().getState());
                                    assessPojo.setZipcode(ownerShipRecType.getAddress().getZip());
                                    assessPojo.setStreet(ownerShipRecType.getAddress().getStreet());
                                    assessPojo.setCountry(ownerShipRecType.getAddress().getCountry());
                                }
                                //Update Date is pending - after the SOA changes are done
                                assessPojo.setInCareOf(ownerShipRecType.getCareOfName());
                                assessePojoList.add(assessPojo);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            throw new AmpException();
        }
     //   System.out.println("MacWorkUnitMgmtFaçade : End getAssesseeList size: " + assessePojoList.size());
        return assessePojoList;

    }

    public static void main(String args[]) {
        List<AINPojo> ains = new ArrayList<AINPojo>();
        List<String> ainList = new ArrayList<String>();
        ainList.add("2004002022");
        ainList.add("2004001015");
        AINPojo ain = new AINPojo();
        ain.setAin("2004002022");
        ain.setIsPrimary("Y");
        ains.add(ain);

        ain = new AINPojo();
        ain.setAin("2004001015");
        ain.setIsPrimary("Y");
        ains.add(ain);
        MacWorkUnitMgmtFacade facade = new MacWorkUnitMgmtFacade();
        List<AINRecordPojo> ainsList = new ArrayList<>();
        try {
            ainsList = facade.retrieveOwnershipAddress(ainList, false);
            System.out.println("ainList.size:"+ainsList.size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public BigInteger saveAddressWorkUnitFacade(MailingAddrPayloadDO mailingAddrPayloadDO) throws AmpException, gov.laca.amp.proxy.soap.addressmgmtwrapperservice.client.FaultMessage {
        ManageAddrWorkUnitRequest request = new ManageAddrWorkUnitRequest();
        PayloadListType payloadList = new PayloadListType();
        UserDetails userDetails = new ManageAddrWorkUnitRequest.UserDetails();
        SecurityContext secCntx = ADFContext.getCurrent().getSecurityContext();
        String userName = secCntx.getUserName();
        userDetails.setUserName(userName);
        userDetails.setUserType("Internal");
        //userDetails.setUserId(userName);
        //userDetails.setUserLevel("");
        request.setUserDetails(userDetails);
        List<PayloadListType.PayloadRecord> payloadRecords = payloadList.getPayloadRecord();
        PayloadListType.PayloadRecord payloadRecord = new PayloadListType.PayloadRecord();
        
       
        MailingAddrPayload mailingAddresspayload = new MailingAddrPayload();
        payloadRecord.setPayloadType(mailingAddrPayloadDO.getPayloadType());
        
      //  System.out.println("IN Care OF ::"+mailingAddrPayloadDO.getInCareOf());
        mailingAddresspayload.setCareOf(toUpper(mailingAddrPayloadDO.getInCareOf()));
    //        mailingAddresspayload.setMailingAddr(arg0);
        mailingAddresspayload.setAgentName(mailingAddrPayloadDO.getAgentName());
        mailingAddresspayload.setIsAuthorizedAgent(mailingAddrPayloadDO.getIsAuthorizedAgent());
        mailingAddresspayload.setEmail(mailingAddrPayloadDO.getEmail());
        mailingAddresspayload.setPropertyOwnerName(mailingAddrPayloadDO.getPropertyOwnerName());
        mailingAddresspayload.setSignature(mailingAddrPayloadDO.getSignature());
        mailingAddresspayload.setPhone(mailingAddrPayloadDO.getPhone());
        //Refactoring
        AddressType mailingAddrType = new AddressType();
       
       // NEw Code Add for saving MAilAddr 
       
       
        PropertyIdentificationType propIdenType = null;
        
        // Need to discuss with Sapna , what is this for ?
    //          String mailingAddress = mailingAddrPayloadDO.getMailingAddress();
    //
    //        System.out.println("saveAddressWorkUnitFacade : Mailing Address from Pojo:" + mailingAddress);
    //        if(mailingAddress != null && !mailingAddress.equals(""))
    //                {
    //                    System.out.println("Mailing Address not null and not empty");
    //                    String[] mailingAddressParts = mailingAddress.split(",");
    //                    if (mailingAddressParts != null && mailingAddressParts.length >= 0)
    //                    {
    //                        int partsLength = mailingAddressParts.length;
    //                        System.out.println("mailingAddressParts. length part->"+mailingAddressParts.length);
    //                        if(partsLength>0)
    //                            mailingAddrType.setStreet(mailingAddressParts[0]);
    //                        if(partsLength>1)
    //                            mailingAddrType.setCity(mailingAddressParts[1]);
    //                        if(partsLength>2)
    //                         mailingAddrType.setState(mailingAddressParts[2]);
    //                        if(partsLength>3)
    //                            mailingAddrType.setZip(mailingAddressParts[3]);
    //                        if(partsLength>4)
    //                            mailingAddrType.setCountry(mailingAddressParts[4]);
    //                    }
    //                    mailingAddresspayload.setMailingAddr(mailingAddrType);
    //               }
        
            AddrPojo mailingAddr = mailingAddrPayloadDO.getMailingAddr(); 
            mailingAddrType.setStreet(toUpper(mailingAddr.getStreet()));
            mailingAddrType.setCity(toUpper(mailingAddr.getCity()));
            mailingAddrType.setState(toUpper(mailingAddr.getState()));
            mailingAddrType.setZip(toUpper(mailingAddr.getZip()));
            mailingAddrType.setCountry(toUpper(mailingAddr.getCountry()));
        mailingAddresspayload.setMailingAddr(mailingAddrType);

        mailingAddresspayload.setDate(asXMLGregorianCalendar(mailingAddrPayloadDO.getDate()));
     //   System.out.println("mailingAddresspayload.getDate():" + mailingAddresspayload.getDate());
        MailingAddrPayload.AINList ainList = new MailingAddrPayload.AINList();
        List<MailingAddrPayload.AINList.AINRecord> ainRecords = ainList.getAINRecord();

        AddressType currentAddress = null;
        MailingAddrPayload.AINList.AINRecord ainRecord = null;

   //     System.out.println("FACADE : Debugging SAVE->");
        
        //PropertyIdentificationList propIdentificationlist = new PropertyIdentificationList();
       // List<PropertyIdentificationType>  ainListTypeMain = propIdentificationlist.getPropertyIdentification();
        
    //        PropertyIdentificationList propertyIdentificationList = request.getPropertyIdentificationList();
               PropertyIdentificationList propIdentificationlist = new PropertyIdentificationList();
       List<PropertyIdentificationType>  propertyIdentificationList = propIdentificationlist.getPropertyIdentification();
       
        if (mailingAddrPayloadDO.getOrgAinList() != null) {
          //  System.out.println("No. of AIN s in DO " + mailingAddrPayloadDO.getOrgAinList().size());
            for (int i = 0; i < mailingAddrPayloadDO.getOrgAinList().size(); i++) {
                ainRecord = new MailingAddrPayload.AINList.AINRecord();

                if (mailingAddrPayloadDO.getOrgAinList().get(i) != null) {
                    propIdenType = new PropertyIdentificationType();
                    if (mailingAddrPayloadDO.getOrgAinList().get(i).getAin() != null)
                       propIdenType.setAIN(mailingAddrPayloadDO.getOrgAinList().get(i).getAin());                   
                     //   System.out.println("BigInteger AOID ->"+mailingAddrPayloadDO.getOrgAinList().get(i).getAoid());
                    if(mailingAddrPayloadDO.getOrgAinList().get(i).getAoid() !=null)                        
                        propIdenType.setAOID(new Integer(mailingAddrPayloadDO.getOrgAinList().get(i).getAoid().intValue()));
                        
                    if(propertyIdentificationList != null)
                                            propertyIdentificationList.add(propIdenType);
                                        else
                                            System.out.println("propertyIdentificationList is NULL");
                                       // propertyIdentificationList.getPropertyIdentification().add(propIdenType);                
                                        request.setPropertyIdentificationList(propIdentificationlist);
                                        
    //                    propertyIdentificationList.getPropertyIdentification().add(propIdenType);
    //                    request.setPropertyIdentificationList(propertyIdentificationList);

                    //mailingAddrPayloadDO.getOrgAinList().get(i).setRecordingDate(mailingAddrPayloadDO.)
                    ainRecord.setAIN(mailingAddrPayloadDO.getOrgAinList().get(i).getAin());
                    ainRecord.setAOID(mailingAddrPayloadDO.getOrgAinList().get(i).getAoid());
                    ainRecord.setOHID(mailingAddrPayloadDO.getOrgAinList().get(i).getOhid());
                    ainRecord.setAssesseeName(mailingAddrPayloadDO.getOrgAinList().get(i).getAssesseeName());
                    ainRecord.setCluster(mailingAddrPayloadDO.getOrgAinList().get(i).getCluster());
                    ainRecord.setCommunityName(mailingAddrPayloadDO.getOrgAinList().get(i).getCommunityName());                                                                   
                    ainRecord.setDistrict(mailingAddrPayloadDO.getOrgAinList().get(i).getDistrict());                                                              
                    ainRecord.setDocumentID(mailingAddrPayloadDO.getOrgAinList().get(i).getDocId());                                                              
                                                                
                    ainRecord.setDocumentSequence(mailingAddrPayloadDO.getOrgAinList().get(i).getSeqNumber());
                    ainRecord.setIsApproveAddrChange(mailingAddrPayloadDO.getOrgAinList().get(i).getIsApproveAddrChange());
                //    System.out.println("IsApproveAddrChange :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getIsApproveAddrChange());
                    ainRecord.setIsDenyAddrChange(mailingAddrPayloadDO.getOrgAinList().get(i).getIsDenyAddrChange());
                 //   System.out.println("IsDenyAddrChange :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getIsDenyAddrChange());
                    ainRecord.setIsMailingAddrInvestigationReq(mailingAddrPayloadDO.getOrgAinList().get(i).getIsMailingAddrInvestigationReq());
                 //   System.out.println("IsMailingAddrInvestigationReq :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getIsMailingAddrInvestigationReq());
                    ainRecord.setIsSitusAddrInvestigationReq(mailingAddrPayloadDO.getOrgAinList().get(i).getIsSitusAddrInvestigationReq());
                //    System.out.println("IsSitusAddrInvestigationReq :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getIsSitusAddrInvestigationReq());
                    ainRecord.setIsVisited(mailingAddrPayloadDO.getOrgAinList().get(i).getIsVisited());                  
                    ainRecord.setParcelStatus(mailingAddrPayloadDO.getOrgAinList().get(i).getParcelStatus());
                    ainRecord.setRecordingDate(asXMLGregorianCalendar(mailingAddrPayloadDO.getOrgAinList().get(i).getRecordingDate()));
                    ainRecord.setRegion(mailingAddrPayloadDO.getOrgAinList().get(i).getRegion());
                //    System.out.println("Setting Remarks from mailingAddrPayloadDO : "+mailingAddrPayloadDO.getOrgAinList().get(i).getRemarks());
                    ainRecord.setRemarks(mailingAddrPayloadDO.getOrgAinList().get(i).getRemarks());
              //      System.out.println("Situs Investigation Notes from mailingAddrPayloadDO : "+mailingAddrPayloadDO.getOrgAinList().get(i).getSitusAddrInvestigationNotes());
                    ainRecord.setSitusAddrInvestigationNotes(mailingAddrPayloadDO.getOrgAinList().get(i).getSitusAddrInvestigationNotes());
                    ainRecord.setIsOwnedByInvestigators(mailingAddrPayloadDO.getOrgAinList().get(i).getIsOwnedByInvestigators());
                    ainRecord.setCareOfName(mailingAddrPayloadDO.getOrgAinList().get(i).getInCareOf());

    // New code for checking Null Object
                    if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr() != null) {
                        currentAddress = new AddressType();
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getStreet() !=null || mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCity() !=null ||mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getState() !=null || mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getZip() !=null || mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCountry() !=null ) {
                           
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCity() != null) {
                             //   System.out.println("Mailing Addr City :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCity());
                                currentAddress.setCity(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCity());
                            }
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCountry() != null) {
                              //  System.out.println("Mailing Addr Country :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCountry());
                                currentAddress.setCountry(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getCountry());
                            }
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getState() != null) {
                             //   System.out.println("Mailing Addr State :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getState());
                                currentAddress.setState(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getState());
                            }
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getStreet() != null) {
                              //  System.out.println("Mailing Addr Street :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getStreet());
                                currentAddress.setStreet(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getStreet());
                            }
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getZip() != null) {
                                
                                currentAddress.setZip(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr().getZip());
                            }
                           
                        }else {
                            // validation added, will not come in this loop.
                            if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddress() != null) {
                                String[] tempcurrentmailgAddr = mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddress()
                                                                                    .split(",");

                                currentAddress.setStreet(tempcurrentmailgAddr[0]);
                                currentAddress.setCity(tempcurrentmailgAddr[1]);
                                currentAddress.setState(tempcurrentmailgAddr[2]);
                                currentAddress.setZip(tempcurrentmailgAddr[3]);
                                currentAddress.setCountry(tempcurrentmailgAddr[4]);
                            }
                                         
                        }
                        
                    ainRecord.setCurrentMailingAddress(currentAddress);
                        
                    }
                    
                    String priorAssesseeMailAddress = mailingAddrPayloadDO.getOrgAinList().get(i).getPriorAssesseeMailAddress();
                    if(priorAssesseeMailAddress != null) {
                        
                        String[] priorAssessAddr = mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddress().split(",");
                        AddrPojo addr = mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentMailingAddr();
                        if(addr == null) {
                            addr = new AddrPojo();
                        }
                        
                        AddressType pAddr = new AddressType();

                        pAddr.setStreet(priorAssessAddr[0]);addr.setStreet(priorAssessAddr[0]);
                        pAddr.setCity(priorAssessAddr[1]);addr.setCity(priorAssessAddr[1]);
                        pAddr.setState(priorAssessAddr[2]);addr.setState(priorAssessAddr[2]);
                        pAddr.setZip(priorAssessAddr[3]);addr.setZip(priorAssessAddr[3]);
                        pAddr.setCountry(priorAssessAddr[4]);addr.setCountry(priorAssessAddr[4]);
                        ainRecord.setCurrentMailingAddress(pAddr);
                        mailingAddrPayloadDO.getOrgAinList().get(i).setCurrentMailingAddr(addr);
                        mailingAddrPayloadDO.getOrgAinList().get(i).setPriorAssesseeMailAddress(null);
                    }

                    if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr() != null) {
                        currentAddress = new AddressType();
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCity() != null) {
                            System.out.println("Situs Addr City :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCity());
                            currentAddress.setCity(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCity());
                        }
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCountry() != null) {
                            System.out.println("Situs Addr Country :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCountry());
                            currentAddress.setCountry(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getCountry());
                        }
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getState() != null) {
                            System.out.println("Situs Addr State :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getState());
                                                                                            
                                                                                            
                            currentAddress.setState(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getState());
                        }
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getStreet() != null) {
                            System.out.println("Situs Addr Street :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getStreet());
                            currentAddress.setStreet(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getStreet());
                        }
                        if (mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getZip() != null) {
                            System.out.println("Situs Addr Zip :  " + mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getZip());
                            currentAddress.setZip(mailingAddrPayloadDO.getOrgAinList().get(i).getCurrentSitusAddr().getZip());
                        }
                        ainRecord.setCurrentSitusAddress(currentAddress);
                    }
                }
                ainRecords.add(ainRecord);               
            }
        }

       
        System.out.println("END FACADE : Debugging SAVE->");
        System.out.println("Total AIN s set into PAYLOAD :" + ainList.getAINRecord().size());

        mailingAddresspayload.setAINList(ainList);
        payloadRecord.setMailingAddrPayload(mailingAddresspayload);
        payloadRecords.add(payloadRecord);
        request.setPayloadList(payloadList);

       // request.setAINList(ainListTypeMain);
       //request.setPropertyIdentificationList(propIdentificationlist);

        /*To be populated from BPMPayload*/
        request.setCategory(mailingAddrPayloadDO.getCategory());
        request.setSubCategory(mailingAddrPayloadDO.getSubCategory());
        request.setInputSource(mailingAddrPayloadDO.getInputSource());
        request.setEventSource(mailingAddrPayloadDO.getEventSource());
        request.setUIAction(mailingAddrPayloadDO.getUiAction());
        request.setEventDate(asXMLGregorianCalendar(mailingAddrPayloadDO.getEventDate()));
        if (mailingAddrPayloadDO.getDCAID() != null) {
            request.setDCAId((mailingAddrPayloadDO.getDCAID()));
        }
        request.setDocumentId(mailingAddrPayloadDO.getDocumentId());
        request.setDistrict(mailingAddrPayloadDO.getDistrict());
        request.setRegion(mailingAddrPayloadDO.getRegion());
        request.setCluster(mailingAddrPayloadDO.getCluster());
        request.setTransactionId(mailingAddrPayloadDO.getTransactionId());
        if (mailingAddrPayloadDO.getWorkUnitId() != null) {
            request.setWUID(new BigInteger(mailingAddrPayloadDO.getWorkUnitId()));
        }
        request.setWUName(mailingAddrPayloadDO.getWorkUnitName());
        if (mailingAddrPayloadDO.getAggregateId() != null) {
            request.setAggregateId(new Integer(mailingAddrPayloadDO.getAggregateId()));
        }
        //request.isReview
        //request.isVisited

        MailingAddrAssocWrapperServiceWrapper wrapper = new MailingAddrAssocWrapperServiceWrapper();
        ManageAddrWorkUnitResponse response = wrapper.saveManageAddrWorkUnit(request);
        System.out.println("response.getHeader().getOperationStatus():" + response.getHeader().getOperationStatus());
        System.out.println("MacWorkUnitMgmtFaçade : saveAddressWorkUnitFacade : response.getWUID:" + response.getWUID());
        logger.error(MacWorkUnitMgmtFacade.class, "saveAddressWorkUnitFacade()", "END saveAddressWorkUnitFacade", null);
        return response.getWUID();

    }

    public XMLGregorianCalendar asXMLGregorianCalendar(java.sql.Date date) {
        try {
            DatatypeFactory df = DatatypeFactory.newInstance();
            if (date == null) {
                return null;
            } else {
                GregorianCalendar gc = new GregorianCalendar();
                gc.setTimeInMillis(date.getTime());
                return df.newXMLGregorianCalendar(gc);
            }
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException("Error while trying to obtain a new instance of DatatypeFactory", e);

        }

    }
	
	public Boolean CheckIfAAUExists(BigInteger aggregateId) throws AmpException, gov.laca.amp.proxy.soap.aggregateassessment.client.FaultMessage {
      //  logger.error(MacWorkUnitMgmtFacade.class, "CheckIfAAUExists()", "Start CheckIfAAUExists", null);      
      //  System.out.println("IN FACADE of CheckIfAAUExists and aggregateId is:"+aggregateId);
        AggregateAssessmentUnitServiceWrapper aggAssUnitServWrapper = new AggregateAssessmentUnitServiceWrapper();
        Boolean response= aggAssUnitServWrapper.CheckIfAAUExists(aggregateId);
        System.out.println("Response in FACADE:"+response);  
        return response;
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
        return sb.toString();
    }
    
    public List<AINRecordPojo> retrieveOwnershipAddress(List<String> ainList,Boolean markInvestigate) throws AmpException, FaultMessage {
            logger.info(MacWorkUnitMgmtFacade.class, "retrieveOwnershipAddress()", "Start retrieveOwnershipAddress", null);
            RetrieveCurrentOwnershipInfoResponse response = null;
        
            MailingAddrAssocMgmtServiceWrapper AddrMgmtWrapper= new MailingAddrAssocMgmtServiceWrapper();
            
            
            RetrieveCurrentOwnershipInfoRequest req =  new RetrieveCurrentOwnershipInfoRequest();
            RetrieveCurrentOwnershipInfoRequest.AINList reqAin = new RetrieveCurrentOwnershipInfoRequest.AINList();
            List<RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord> ainRecList = reqAin.getAINRecord();
            RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord ainRecord = null;
            for(String ain:ainList) {
                ainRecord =  new RetrieveCurrentOwnershipInfoRequest.AINList.AINRecord();
                ainRecord.setAIN(ain);
                ainRecList.add(ainRecord);
            }
            
            gov.laca.amp.proxy.soap.addressmanagement.client.gen.RequestHeader reHead =  new gov.laca.amp.proxy.soap.addressmanagement.client.gen.RequestHeader();
            reHead.setApplicationId("1");
            reHead.setRequestId("1");
            req.setHeader(reHead);
            req.setAINList(reqAin);
            
          
            
           List<AINRecordPojo> ainsList = new ArrayList<AINRecordPojo>();
            response= AddrMgmtWrapper.retrieveOwnerShipDetailsForMultipleAINs(req);
            AddrPojo situsAddrPojo = null;
            AddrPojo mailingAddrPojo = null;
            if (response != null) {
               List<RetrieveCurrentOwnershipInfoResponse.CurrentOwnershipInfoList.CurrentOwnershipInfo> currentOwnerShipInfo= response.getCurrentOwnershipInfoList().getCurrentOwnershipInfo();
                if (currentOwnerShipInfo != null) {
                    AINRecordPojo ainPojo = null;
                    
                    for (int i = 0; i < currentOwnerShipInfo.size(); i++) {
                     
                        RetrieveCurrentOwnershipInfoResponse.CurrentOwnershipInfoList.CurrentOwnershipInfo c = currentOwnerShipInfo.get(i);
                        if (c != null) {
                            
                         
                            ainPojo = new AINRecordPojo();
                            ainPojo.setAin(c.getAIN());
                            String sain = ainPojo.getAin();
                            if(sain != null && sain.length() == 10) {
                                ainPojo.setAinDisplay(sain.substring(0, 4) + "-" + sain.substring(4, 7) + "-" + sain.substring(7, 10));
                            }
                            if (c.getRecordingDate() != null) {
                            ainPojo.setRecordingDate(toSQLDateOnly(c.getRecordingDate()));
                            }
                            ainPojo.setAoid(c.getAOID());
                            ainPojo.setAssesseeName(c.getAssesseeName());
                            ainPojo.setCluster(c.getCluster());
                            ainPojo.setCommunityName(c.getCommunityName());
                            ainPojo.setDistrict(c.getDistrict());
                            ainPojo.setDocId(c.getDocumentID());
                            ainPojo.setSeqNumber(c.getDocumentSeq());
                            ainPojo.setParcelStatus(c.getParcelStatus());
                            ainPojo.setRegion(c.getRegion());
                            ainPojo.setOhid(c.getOHID());
                                situsAddrPojo = new AddrPojo();
                                if (c.getCurrentSitusAddress() != null) {
                                    situsAddrPojo.setCity(c.getCurrentSitusAddress().getCity());
                                    situsAddrPojo.setState(c.getCurrentSitusAddress().getState());
                                    situsAddrPojo.setStreet(c.getCurrentSitusAddress().getStreet());
                                    situsAddrPojo.setCountry(c.getCurrentSitusAddress().getCountry());
                                    situsAddrPojo.setZip(c.getCurrentSitusAddress().getZip());
                                }
                                ainPojo.setCurrentSitusAddr(situsAddrPojo);
                                //Update mailing Address
                                mailingAddrPojo = new AddrPojo();
                             
                                if (c.getCurrentMailingAddress() != null) {                                        
                                    mailingAddrPojo.setCity(c.getCurrentMailingAddress().getCity());
                                    mailingAddrPojo.setState(c.getCurrentMailingAddress().getState());
                                    mailingAddrPojo.setStreet(c.getCurrentMailingAddress().getStreet());
                                    mailingAddrPojo.setCountry(c.getCurrentMailingAddress().getCountry());
                                    mailingAddrPojo.setZip(c.getCurrentMailingAddress().getZip());
                                }
                                ainPojo.setCurrentMailingAddr(mailingAddrPojo);
                            ainPojo.setCurrentMailingAddress(appendAddr(c.getCurrentMailingAddress().getStreet(),c.getCurrentMailingAddress().getCity(),c.getCurrentMailingAddress().getState(),c.getCurrentMailingAddress().getZip(),c.getCurrentMailingAddress().getCountry()));
                            ainPojo.setCurrentSitusAddress(appendAddr(c.getCurrentSitusAddress().getStreet(),c.getCurrentSitusAddress().getCity(),c.getCurrentSitusAddress().getState(),c.getCurrentSitusAddress().getZip(),c.getCurrentSitusAddress().getCountry()));
                                ainPojo.setIsVisited(Boolean.FALSE);
                                ainPojo.setIsOwnedByInvestigators(markInvestigate);
                                ainPojo.setInCareOf(c.getCareOfName());
                                ainsList.add(ainPojo);
                            }
                          }
                        
                    }
                }
       
            logger.info(MacWorkUnitMgmtFacade.class, "retrieveOwnershipAddress()", "End retrieveOwnershipAddress", null);
            return ainsList;
            
        }
    
    
    public String toUpper(String data) {
        return data != null ? data.toUpperCase() : data;
    }
    
    
   
}
