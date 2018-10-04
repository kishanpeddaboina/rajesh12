package gov.laca.amp.modules.mac.pojo;

public class MacAddressPojo {
    public MacAddressPojo() {
        super();
    }
    
    private String inCareOf = null;
    private String mailingAddress = null;
    public void setInCareOf(String inCareOf) {
        this.inCareOf = inCareOf;
    }

    public String getInCareOf() {
        return inCareOf;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }
    
}
