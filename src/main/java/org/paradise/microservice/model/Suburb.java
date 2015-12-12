package org.paradise.microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by terrence on 1/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suburb implements Serializable {

    @NotNull
    @JsonProperty("suburb")
    private String localityName;

    @NotNull
    @JsonProperty("postcode")
    private String postcode;

    @NotNull
    @JsonProperty("state")
    private String state;

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
