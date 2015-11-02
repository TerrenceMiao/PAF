package au.com.auspost.microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by terrence on 3/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suburbs implements Serializable {

    @NotNull
    @JsonProperty("suburbs")
    private List<Suburb> suburbList;

    public List<Suburb> getSuburbList() {
        return suburbList;
    }

    public void setSuburbList(List<Suburb> suburbList) {
        this.suburbList = suburbList;
    }

}
