package org.paradise.microservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by terrence on 11/10/2016.
 */
public class UselessDto {

    private String aString;
    private Integer anInteger;
    private Boolean aBoolean;

    private String hidden;
    private String notHidden;

    public String getaString() {
        return aString;
    }

    public void setaString(String aString) {
        this.aString = aString;
    }

    public Integer getAnInteger() {
        return anInteger;
    }

    public void setAnInteger(Integer anInteger) {
        this.anInteger = anInteger;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    @JsonIgnore
    public String getNotHidden() {
        return notHidden;
    }

    public void setNotHidden(String notHidden) {
        this.notHidden = notHidden;
    }

}
