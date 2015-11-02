package au.com.auspost.microservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by terrence on 1/11/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StreetTypes implements Serializable {

    @NotNull
    @JsonProperty("streetTypes")
    private List<StreetType> streetTypeList;

    public List<StreetType> getStreetTypeList() {
        return streetTypeList;
    }

    public void setStreetTypeList(List<StreetType> streetTypeList) {
        this.streetTypeList = streetTypeList;
    }

}
