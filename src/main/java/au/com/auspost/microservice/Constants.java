package au.com.auspost.microservice;

import org.modelmapper.ModelMapper;

/**
 * Created by terrence on 31/10/15.
 */
public final class Constants {

    public static final String CURRENT_VERSION = "/v1";

    public static final String ADDRESS_REQUEST_PATH = CURRENT_VERSION + "/address";
    public static final String SUBURBS_REQUEST_PATH = CURRENT_VERSION + "/suburbs";
    public static final String STREETS_REQUEST_PATH = CURRENT_VERSION + "/streets";

    public static final int STREETS_NUMBER_LENGTH = 5;

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    private Constants() {
    }

}
