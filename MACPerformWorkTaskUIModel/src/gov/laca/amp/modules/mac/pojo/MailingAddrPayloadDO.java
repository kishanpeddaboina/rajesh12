package gov.laca.amp.modules.mac.pojo;

import gov.laca.amp.fwk.extn.pojo.AmpPojo;

import java.math.BigInteger;

import java.sql.Date;

import java.util.List;

public class MailingAddrPayloadDO extends AmpPojo
{
  @SuppressWarnings("compatibility:283988163225578608")
  private static final long serialVersionUID = 1L;

  public MailingAddrPayloadDO()
  {
    super();
  }
  
    private String payloadType;
  private String inCareOf;
  private String origInCareOf;
  private String mailingAddress;
  private Boolean isSetasSitusAddr;
  private Boolean isAuthorizedAgent;
  private String agentName;
  private String propertyOwnerName;
  private String phone;
  private String email;
  private String signature;
  private Date date;
  private List<AINRecordPojo> ainList;
    private List<AINRecordPojo> orgAinList;
 private AddrPojo AssessCurMailingAddr;
    private AddrPojo MailingAddr;
  //BPM Payload related data
//  <mail:Category/>
//           <mail:SubCategory/>
//           <mail:EventDate/>
//           <mail:EventSource/>
//           <mail:UIAction/>
//           <mail:District/>
//           <mail:Region/>
//           <mail:Cluster/>
//           <mail:DocumentId/>
//           <mail:DCAId/>
//           <mail:InputSource>WorkTask</mail:InputSource>
//           <mail:TransactionId/>

    private String category;
    private String subCategory;
    private java.sql.Date eventDate;
    private String eventSource;

    public void setWorkUnitId(String workUnitId) {
        this.workUnitId = workUnitId;
    }

    public String getWorkUnitId() {
        return workUnitId;
    }

    public void setWorkUnitName(String workUnitName) {
        this.workUnitName = workUnitName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getWorkUnitName() {
        return workUnitName;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
    private String uiAction;
    private String district;
    private String region;
    private String cluster;
    private String documentId;
    private BigInteger dCAID;
    private String inputSource;
    private String transactionId;
    private String workUnitId;
    private String workUnitName;
    private String aggregateId;
    private String userId;
    private String userName;
    private String userType;
    private String userLevel;
    
  
  public void setInCareOf(String inCareOf)
  {
    this.inCareOf = inCareOf;
  }

  public String getInCareOf()
  {
    return inCareOf;
  }

  public void setMailingAddress(String mailingAddress)
  {
    this.mailingAddress = mailingAddress;
  }

  public String getMailingAddress()
  {
    return mailingAddress;
  }

  public void setIsSetasSitusAddr(Boolean isSetasSitusAddr)
  {
    this.isSetasSitusAddr = isSetasSitusAddr;
  }

  public Boolean getIsSetasSitusAddr()
  {
    return isSetasSitusAddr;
  }

  public void setIsAuthorizedAgent(Boolean isAuthorizedAgent)
  {
    this.isAuthorizedAgent = isAuthorizedAgent;
  }

  public Boolean getIsAuthorizedAgent()
  {
    return isAuthorizedAgent;
  }

  public void setAgentName(String agentName)
  {
    this.agentName = agentName;
  }

  public String getAgentName()
  {
    return agentName;
  }

  public void setPropertyOwnerName(String propertyOwnerName)
  {
    this.propertyOwnerName = propertyOwnerName;
  }

  public String getPropertyOwnerName()
  {
    return propertyOwnerName;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail()
  {
    return email;
  }

  public void setSignature(String signature)
  {
    this.signature = signature;
  }

  public String getSignature()
  {
    return signature;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Date getDate()
  {
    return date;
  }

  public void setAinList(List<AINRecordPojo> ainList)
  {
    this.ainList = ainList;
  }

  public List<AINRecordPojo> getAinList()
  {
    return ainList;
  }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setUiAction(String uiAction) {
        this.uiAction = uiAction;
    }

    public String getUiAction() {
        return uiAction;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getCluster() {
        return cluster;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDCAID(BigInteger dCAID) {
        this.dCAID = dCAID;
    }

    public BigInteger getDCAID() {
        return dCAID;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setPayloadType(String payloadType) {
        this.payloadType = payloadType;
    }

    public String getPayloadType() {
        return payloadType;
    }

    public void setAssessCurMailingAddr(AddrPojo AssessCurMailingAddr) {
        this.AssessCurMailingAddr = AssessCurMailingAddr;
    }

    public AddrPojo getAssessCurMailingAddr() {
        return AssessCurMailingAddr;
    }

    public void setMailingAddr(AddrPojo MailingAddr) {
        this.MailingAddr = MailingAddr;
    }

    public AddrPojo getMailingAddr() {
        return MailingAddr;
    }

    public void setOrgAinList(List<AINRecordPojo> orgAinList) {
        this.orgAinList = orgAinList;
    }

    public List<AINRecordPojo> getOrgAinList() {
        return orgAinList;
    }


    public void setOrigInCareOf(String origInCareOf) {
        this.origInCareOf = origInCareOf;
    }

    public String getOrigInCareOf() {
        return origInCareOf;
    }
}
