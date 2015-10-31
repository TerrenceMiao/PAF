package au.com.auspost.microservice.dto;

/**
 * Created by terrence on 31/10/15.
 */
public class PostalAddress {

    private String lotNumber;
    private String flatUnitNumber;
    private String floorLevelNumber;
    private String houseNumber;

    private String streetName;
    private String streetType;

    private String localityName;
    private String postcode;
    private String state;

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getFlatUnitNumber() {
        return flatUnitNumber;
    }

    public void setFlatUnitNumber(String flatUnitNumber) {
        this.flatUnitNumber = flatUnitNumber;
    }

    public String getFloorLevelNumber() {
        return floorLevelNumber;
    }

    public void setFloorLevelNumber(String floorLevelNumber) {
        this.floorLevelNumber = floorLevelNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PostalAddress{"
                + "lotNumber='" + lotNumber + '\''
                + ", flatUnitNumber='" + flatUnitNumber + '\''
                + ", floorLevelNumber='" + floorLevelNumber + '\''
                + ", houseNumber='" + houseNumber + '\''
                + ", streetName='" + streetName + '\''
                + ", streetType='" + streetType + '\''
                + ", localityName='" + localityName + '\''
                + ", postcode='" + postcode + '\''
                + ", state='" + state + '\''
                + '}';
    }

}
