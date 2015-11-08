package au.com.auspost.microservice;

/**
 * Created by terrence on 7/11/15.
 */
public class Comment {

    public String author;
    public String text;

    Comment() {
    }

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

}
