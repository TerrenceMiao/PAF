package au.com.auspost.microservice.dto;

/**
 * Created by terrence on 31/10/15.
 */
public class PostalAddress {

    private String author;

    private String lotNumber;
    private String flatUnitNumber;
    private String floorLevelNumber;
    private String houseNumber1;
    private String houseNumber2;

    private String streetName;
    private String streetType;

    private String localityName;
    private String postcode;
    private String state;

    public PostalAddress() {
    }

    public PostalAddress(String author, String houseNumber1, String streetName, String streetType, String localityName, String state, String postcode) {

        this.author = author;

        this.houseNumber1 = houseNumber1;
        this.streetName = streetName;
        this.streetType = streetType;
        this.localityName = localityName;
        this.state = state;
        this.postcode = postcode;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getHouseNumber1() {
        return houseNumber1;
    }

    public void setHouseNumber1(String houseNumber1) {
        this.houseNumber1 = houseNumber1;
    }

    public String getHouseNumber2() {
        return houseNumber2;
    }

    public void setHouseNumber2(String houseNumber2) {
        this.houseNumber2 = houseNumber2;
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
                + "author='" + author + '\''
                + ", lotNumber='" + lotNumber + '\''
                + ", flatUnitNumber='" + flatUnitNumber + '\''
                + ", floorLevelNumber='" + floorLevelNumber + '\''
                + ", houseNumber1='" + houseNumber1 + '\''
                + ", houseNumber2='" + houseNumber2 + '\''
                + ", streetName='" + streetName + '\''
                + ", streetType='" + streetType + '\''
                + ", localityName='" + localityName + '\''
                + ", postcode='" + postcode + '\''
                + ", state='" + state + '\''
                + '}';
    }

}
