package au.com.auspost.microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by terrence on 31/10/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address implements Serializable {

    @JsonProperty("lotNumber")
    private String lotNumber;

    @JsonProperty("flatUnitNumber")
    private String flatUnitNumber;

    @JsonProperty("floorLevelNumber")
    private String floorLevelNumber;

    @NotNull
    @JsonProperty("houseNumber")
    private String houseNumber1;

    @JsonProperty("houseNumber2")
    private String houseNumber2;

    @NotNull
    @JsonProperty("suburb")
    private String streetName;

    @NotNull
    @JsonProperty("suburb")
    private String streetType;

    @NotNull
    @JsonProperty("suburb")
    private String localityName;

    @NotNull
    @JsonProperty("postcode")
    private String postcode;

    @NotNull
    @JsonProperty("state")
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

}
