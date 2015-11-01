package au.com.auspost.microservice.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by terrence on 1/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dpid implements Serializable {

    @NotNull
    @JsonProperty("dpid")
    private String dpid;

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
    }

}
