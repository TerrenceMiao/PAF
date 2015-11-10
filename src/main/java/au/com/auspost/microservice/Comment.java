package au.com.auspost.microservice;

/**
 * Created by terrence on 7/11/15.
 */
public class Comment {

    public String author;
    public String text;

    public String streetNumber;
    public String streetName;
    public String suburb;
    public String state;
    public String postcode;

    Comment() {
    }

    public Comment(String author, String text, String streetNumber, String streetName, String suburb, String state, String postcode) {
        this.author = author;
        this.text = text;

        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.state = state;
        this.postcode = postcode;
    }

}
