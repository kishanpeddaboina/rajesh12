package gov.laca.amp.modules.mac.pojo;

import java.math.BigInteger;

import java.sql.Date;


public class AINRecordPojo
{
  public AINRecordPojo()
  {
    super();
  }
  private String ain;
  private BigInteger aoid;
  private String parcelStatus;
  private String communityName;
  private AddrPojo currentSitusAddr;
  private String assesseeName;
  private java.sql.Date recordingDate;
  private String seqNumber;
  private String docId;
  private AddrPojo currentMailingAddr;
  private String district;
  private String region;
  private String cluster;
  private Boolean isApproveAddrChange;
  private Boolean isDenyAddrChange;
  private Boolean isMailingAddrInvestigationReq;
  private String remarks;
  private Boolean isSitusAddrInvestigationReq;
  private String situsAddrInvestigationNotes;
  private Boolean isVisited;
  private String originalMailAddress;
  private String priorAssesseeMailAddress;
private String currentMailingAddress;
private String currentSitusAddress;
private Boolean isOwnedByInvestigators; 
private Integer indexId;
private BigInteger ohid;
private Boolean flagMailSitus;
private String ainDisplay;
private String inCareOf;

    public void setCurrentMailingAddress(String currentMailingAddress) {
        this.currentMailingAddress = currentMailingAddress;
    }

    public String getCurrentMailingAddress() {
        return currentMailingAddress;
    }

    public void setCurrentSitusAddress(String currentSitusAddress) {
        this.currentSitusAddress = currentSitusAddress;
    }

    public String getCurrentSitusAddress() {
        return currentSitusAddress;
    }

    public void setAin(String ain)
  {
    this.ain = ain;
  }

  public String getAin()
  {
    return ain;
  }

  public void setAoid(BigInteger aoid)
  {
    this.aoid = aoid;
  }

  public BigInteger getAoid()
  {
    return aoid;
  }

  public void setParcelStatus(String parcelStatus)
  {
    this.parcelStatus = parcelStatus;
  }

  public String getParcelStatus()
  {
    return parcelStatus;
  }

  public void setCommunityName(String communityName)
  {
    this.communityName = communityName;
  }

  public String getCommunityName()
  {
    return communityName;
  }

  public void setCurrentSitusAddr(AddrPojo currentSitusAddr)
  {
    this.currentSitusAddr = currentSitusAddr;
  }

  public AddrPojo getCurrentSitusAddr()
  {
    return currentSitusAddr;
  }

  public void setAssesseeName(String assesseeName)
  {
    this.assesseeName = assesseeName;
  }

  public String getAssesseeName()
  {
    return assesseeName;
  }

  public void setRecordingDate(Date recordingDate)
  {
    this.recordingDate = recordingDate;
  }

  public Date getRecordingDate()
  {
    return recordingDate;
  }

  public void setSeqNumber(String seqNumber)
  {
    this.seqNumber = seqNumber;
  }

  public String getSeqNumber()
  {
    return seqNumber;
  }

  public void setDocId(String docId)
  {
    this.docId = docId;
  }

  public String getDocId()
  {
    return docId;
  }

  public void setCurrentMailingAddr(AddrPojo currentMailingAddr)
  {
    this.currentMailingAddr = currentMailingAddr;
  }

  public AddrPojo getCurrentMailingAddr()
  {
    return currentMailingAddr;
  }

  public void setDistrict(String district)
  {
    this.district = district;
  }

  public String getDistrict()
  {
    return district;
  }

  public void setRegion(String region)
  {
    this.region = region;
  }

  public String getRegion()
  {
    return region;
  }

  public void setCluster(String cluster)
  {
    this.cluster = cluster;
  }

  public String getCluster()
  {
    return cluster;
  }

  public void setIsApproveAddrChange(Boolean isApproveAddrChange)
  {
    this.isApproveAddrChange = isApproveAddrChange;
  }

  public Boolean getIsApproveAddrChange()
  {
    return isApproveAddrChange;
  }

  public void setIsDenyAddrChange(Boolean isDenyAddrChange)
  {
    this.isDenyAddrChange = isDenyAddrChange;
  }

  public Boolean getIsDenyAddrChange()
  {
    return isDenyAddrChange;
  }

  public void setIsMailingAddrInvestigationReq(Boolean isMailingAddrInvestigationReq)
  {
    this.isMailingAddrInvestigationReq = isMailingAddrInvestigationReq;
  }

  public Boolean getIsMailingAddrInvestigationReq()
  {
    return isMailingAddrInvestigationReq;
  }

  public void setRemarks(String remarks)
  {
    this.remarks = remarks;
  }

  public String getRemarks()
  {
    return remarks;
  }

  public void setIsSitusAddrInvestigationReq(Boolean isSitusAddrInvestigationReq)
  {
    this.isSitusAddrInvestigationReq = isSitusAddrInvestigationReq;
  }

  public Boolean getIsSitusAddrInvestigationReq()
  {
    return isSitusAddrInvestigationReq;
  }

  public void setSitusAddrInvestigationNotes(String situsAddrInvestigationNotes)
  {
    this.situsAddrInvestigationNotes = situsAddrInvestigationNotes;
  }

  public String getSitusAddrInvestigationNotes()
  {
    return situsAddrInvestigationNotes;
  }

  public void setIsVisited(Boolean isVisited)
  {
    this.isVisited = isVisited;
  }

  public Boolean getIsVisited()
  {
    return isVisited;
  }

    public void setOriginalMailAddress(String originalMailAddress) {
        this.originalMailAddress = originalMailAddress;
    }

    public String getOriginalMailAddress() {
        return originalMailAddress;
    }


    public void setIsOwnedByInvestigators(Boolean isOwnedByInvestigators) {
        this.isOwnedByInvestigators = isOwnedByInvestigators;
    }

    public Boolean getIsOwnedByInvestigators() {
        return isOwnedByInvestigators;
    }


    public void setPriorAssesseeMailAddress(String priorAssesseeMailAddress) {
        this.priorAssesseeMailAddress = priorAssesseeMailAddress;
    }

    public String getPriorAssesseeMailAddress() {
        return priorAssesseeMailAddress;
    }


    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getIndexId() {
        return indexId;
    }


    public void setOhid(BigInteger ohid) {
        this.ohid = ohid;
    }

    public BigInteger getOhid() {
        return ohid;
    }


    public void setFlagMailSitus(Boolean flagMailSitus) {
        this.flagMailSitus = flagMailSitus;
    }

    public Boolean getFlagMailSitus() {
        return flagMailSitus;
    }


    public void setAinDisplay(String ainDisplay) {
        this.ainDisplay = ainDisplay;
    }

    public String getAinDisplay() {
        return ainDisplay;
    }


    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getInCareOf() {
        return inCareOf;
    }
}
