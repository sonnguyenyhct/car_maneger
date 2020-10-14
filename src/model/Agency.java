package model;

public class Agency {
    private int agencyID;
    private String agencyName;
    private String agencyAddress;
    private String agencyPhone;

    public Agency(int agencyID, String agencyName, String agencyAddress, String agencyPhone) {
        this.agencyID = agencyID;
        this.agencyName = agencyName;
        this.agencyAddress = agencyAddress;
        this.agencyPhone = agencyPhone;
    }

    public Agency() {
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress;
    }

    public String getAgencyPhone() {
        return agencyPhone;
    }

    public void setAgencyPhone(String agencyPhone) {
        this.agencyPhone = agencyPhone;
    }

    @Override
    public String toString() {
        return "Agency{" +
                "agencyID=" + agencyID +
                ", agencyName='" + agencyName + '\'' +
                ", agencyAddress='" + agencyAddress + '\'' +
                ", agencyPhone='" + agencyPhone + '\'' +
                '}';
    }
}
