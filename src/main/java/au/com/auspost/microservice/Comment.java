package au.com.auspost.microservice;

/**
 * Created by terrence on 7/11/15.
 */
public class Comment {

    public String author;

    public String streetNumber;
    public String streetName;
    public String streetType;
    public String suburb;
    public String state;
    public String postcode;

    Comment() {
    }

    public Comment(String author, String streetNumber, String streetName, String streetType, String suburb, String state, String postcode) {
        this.author = author;

        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.streetType = streetType;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

}
