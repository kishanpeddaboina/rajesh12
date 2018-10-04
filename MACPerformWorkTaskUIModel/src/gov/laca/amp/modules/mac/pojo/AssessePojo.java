package gov.laca.amp.modules.mac.pojo;

import gov.laca.amp.fwk.extn.pojo.AmpPojo;

import java.math.BigInteger;

import java.sql.Date;

public class AssessePojo extends AmpPojo
{
  @SuppressWarnings("compatibility:-4653844741175919752")
  private static final long serialVersionUID = 1L;
  
  public AssessePojo()
  {
    super();
  }
//  private String ain = null;
//  private BigInteger aoid = null;
  private String assesseeName = null;
  private java.sql.Date recordingDate = null;
  private String seqNumber = null;
  private String docId = null;
  private String city = null;
  private String state = null;
  private String zipcode = null;
  private String country = null;
  private String street = null;
  private java.sql.Date updatedDate = null;
  private BigInteger ohid = null;
  private String inCareOf;


//  public void setAin(String ain)
//  {
//    this.ain = ain;
//  }
//
//  public String getAin()
//  {
//    return ain;
//  }
//
//  public void setAoid(BigInteger aoid)
//  {
//    this.aoid = aoid;
//  }
//
//  public BigInteger getAoid()
//  {
//    return aoid;
//  }

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

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getCity()
  {
    return city;
  }

  public void setState(String state)
  {
    this.state = state;
  }

  public String getState()
  {
    return state;
  }

  public void setZipcode(String zipcode)
  {
    this.zipcode = zipcode;
  }

  public String getZipcode()
  {
    return zipcode;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getCountry()
  {
    return country;
  }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }


    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }


    public void setOhid(BigInteger ohid) {
        this.ohid = ohid;
    }

    public BigInteger getOhid() {
        return ohid;
    }


    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getInCareOf() {
        return inCareOf;
    }
}
