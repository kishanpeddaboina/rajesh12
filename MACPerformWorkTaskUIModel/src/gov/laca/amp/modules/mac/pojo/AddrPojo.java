package gov.laca.amp.modules.mac.pojo;

import gov.laca.amp.fwk.extn.pojo.AmpPojo;


public class AddrPojo extends AmpPojo
{
  @SuppressWarnings("compatibility:3278561479521258523")
  private static final long serialVersionUID = 1L;

  public AddrPojo()
  {
    super();
  }
  private String street;
  private String city;
  private String state;
  private String zip;
  private String country;

  public void setStreet(String street)
  {
    this.street = street;
  }

  public String getStreet()
  {
    return street;
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

  public void setZip(String zip)
  {
    this.zip = zip;
  }

  public String getZip()
  {
    return zip;
  }

  public void setCountry(String country)
  {
    this.country = country;
  }

  public String getCountry()
  {
    return country;
  }
}
