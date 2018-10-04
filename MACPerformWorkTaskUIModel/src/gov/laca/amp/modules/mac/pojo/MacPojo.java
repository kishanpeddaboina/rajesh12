package gov.laca.amp.modules.mac.pojo;

import gov.laca.amp.fwk.extn.pojo.AmpPojo;

import java.math.BigInteger;

import java.sql.Date;

public class MacPojo extends AmpPojo{
    public MacPojo() {
        super();
    }
    
    @SuppressWarnings("compatibility")
    private static final long serialVersionUID = 1L;

    private String ain = null;
    private BigInteger aoid = null;
    private String assesseeName = null;
    private java.sql.Date recordingDate = null;
    private String seqNumber = null;
    private String docId = null;
    private String currentSitusAddress = null;
    private String currentmailingAddress = null;
    private String status;
    private String remarks;
    private String notes;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setAin(String ain) {
        this.ain = ain;
    }

    public String getAin() {
        return ain;
    }

    public void setAoid(BigInteger aoid) {
        this.aoid = aoid;
    }

    public BigInteger getAoid() {
        return aoid;
    }

    public void setRecordingDate(Date recordingDate) {
        this.recordingDate = recordingDate;
    }

    public Date getRecordingDate() {
        return recordingDate;
    }

 

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setCurrentSitusAddress(String currentSitusAddress) {
        this.currentSitusAddress = currentSitusAddress;
    }

    public String getCurrentSitusAddress() {
        return currentSitusAddress;
    }

    public void setCurrentmailingAddress(String currentmailingAddress) {
        this.currentmailingAddress = currentmailingAddress;
    }

    public String getCurrentmailingAddress() {
        return currentmailingAddress;
    }


    public void setAssesseeName(String assesseeName) {
        this.assesseeName = assesseeName;
    }

    public String getAssesseeName() {
        return assesseeName;
    }

    public void setSeqNumber(String seqNumber) {
        this.seqNumber = seqNumber;
    }

    public String getSeqNumber() {
        return seqNumber;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }
}
