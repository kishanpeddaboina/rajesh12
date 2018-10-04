package gov.laca.amp.modules.mac.pojo;

import java.math.BigInteger;


//Pojo for the BPM Payload AIN - List

public class AINPojo
{
  public AINPojo()
  {
    super();
  }
  
  private String ain;
  private String isPrimary;
  private BigInteger aoid;

  public void setAin(String ain)
  {
    this.ain = ain;
  }

  public String getAin()
  {
    return ain;
  }

  public void setIsPrimary(String isPrimary)
  {
    this.isPrimary = isPrimary;
  }

  public String getIsPrimary()
  {
    return isPrimary;
  }

  public void setAoid(BigInteger aoid)
  {
    this.aoid = aoid;
  }

  public BigInteger getAoid()
  {
    return aoid;
  }
}
