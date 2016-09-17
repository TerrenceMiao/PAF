package org.paradise.microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by terrence on 31/10/15.
 */
public class PostalAddress {

    private String dpid;

    private String addressee;

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

    @JsonProperty("rubbishes")
    private String rubbish = "rubbishes";

    @JsonProperty("junks")
    private String junk = "junks";

    public PostalAddress() {
    }

    public PostalAddress(String dpid, String addressee, String houseNumber1, String streetName, String streetType,
            String localityName, String state, String postcode) {

        this.dpid = dpid;

        this.addressee = addressee;

        this.houseNumber1 = houseNumber1;
        this.streetName = streetName;
        this.streetType = streetType;
        this.localityName = localityName;
        this.state = state;
        this.postcode = postcode;
    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
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

    public String getRubbish() {
        return rubbish;
    }

    public String getJunk() {
        return junk;
    }


    @Override
    public String toString() {
        return "PostalAddress{"
                + "dpid ='" + dpid + '\''
                + ", addressee='" + addressee + '\''
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

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostalAddress that = (PostalAddress) o;

        return Objects.equals(lotNumber, that.lotNumber)
                && Objects.equals(flatUnitNumber, that.flatUnitNumber)
                && Objects.equals(floorLevelNumber, that.floorLevelNumber)
                && Objects.equals(houseNumber1, that.houseNumber1)
                && Objects.equals(houseNumber2, that.houseNumber2)
                && Objects.equals(streetName, that.streetName)
                && Objects.equals(streetType, that.streetType)
                && Objects.equals(localityName, that.localityName)
                && Objects.equals(postcode, that.postcode)
                && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(lotNumber, flatUnitNumber, floorLevelNumber, houseNumber1, houseNumber2, streetName, streetType,
                localityName, postcode, state);
    }

}
