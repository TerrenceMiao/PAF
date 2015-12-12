package org.paradise.microservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by terrence on 28/10/15.
 */
@Entity
public class Locality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String recordActnCode;
    private String localityId;
    private String localityName;
    private String postcode;
    private String state;
    private String localityDid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRecordActnCode() {
        return recordActnCode;
    }

    public void setRecordActnCode(String recordActnCode) {
        this.recordActnCode = recordActnCode;
    }

    public String getLocalityId() {
        return localityId;
    }

    public void setLocalityId(String localityId) {
        this.localityId = localityId;
    }

    public String getLocalityName() {
        return localityName;
    }

    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocalityDid() {
        return localityDid;
    }

    public void setLocalityDid(String localityDid) {
        this.localityDid = localityDid;
    }

    @Override
    public String toString() {
        return "Locality{"
                + "id=" + id
                + ", recordActnCode='" + recordActnCode + '\''
                + ", localityId='" + localityId + '\''
                + ", localityName='" + localityName + '\''
                + ", postcode='" + postcode + '\''
                + ", state='" + state + '\''
                + ", localityDid='" + localityDid + '\''
                + '}';
    }

}
