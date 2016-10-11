package org.paradise.microservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by terrence on 17/09/2016.
 */
@JsonIgnoreProperties({"rubbishes", "junks"})
public interface PostalAddressPropertiesFilterMixIn {

}
