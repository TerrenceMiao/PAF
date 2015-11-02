package au.com.auspost.microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by terrence on 3/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreetType implements Serializable {

    @NotNull
    @JsonProperty("streetType")
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}
