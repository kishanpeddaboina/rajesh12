package gov.laca.amp.modules.mac.pojo;


public class MailingAddrPayloadDO
{
  public MailingAddrPayloadDO()
  {
    super();
  }
  
  private String inCareOf;
  private String mailingAddress;

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
}
