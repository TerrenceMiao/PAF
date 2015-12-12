package org.paradise.microservice.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by terrence on 28/10/15.
 */
@Entity
public class DeliveryPointGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String recordActnCode;
    private String delivyPointGroupId;
    private String localityId;
    private String streetName;
    private String streetType;
    private String streetSfx;
    private String postalDeliveryType;
    private String delivyPointGroupDid;

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

    public String getDelivyPointGroupId() {
        return delivyPointGroupId;
    }

    public void setDelivyPointGroupId(String delivyPointGroupId) {
        this.delivyPointGroupId = delivyPointGroupId;
    }

    public String getLocalityId() {
        return localityId;
    }

    public void setLocalityId(String localityId) {
        this.localityId = localityId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getStreetSfx() {
        return streetSfx;
    }

    public void setStreetSfx(String streetSfx) {
        this.streetSfx = streetSfx;
    }

    public String getPostalDeliveryType() {
        return postalDeliveryType;
    }

    public void setPostalDeliveryType(String postalDeliveryType) {
        this.postalDeliveryType = postalDeliveryType;
    }

    public String getDelivyPointGroupDid() {
        return delivyPointGroupDid;
    }

    public void setDelivyPointGroupDid(String delivyPointGroupDid) {
        this.delivyPointGroupDid = delivyPointGroupDid;
    }

    @Override
    public String toString() {
        return "DeliveryPointGroup{"
                + "id=" + id
                + ", recordActnCode='" + recordActnCode + '\''
                + ", delivyPointGroupId='" + delivyPointGroupId + '\''
                + ", localityId='" + localityId + '\''
                + ", streetName='" + streetName + '\''
                + ", streetType='" + streetType + '\''
                + ", streetSfx='" + streetSfx + '\''
                + ", postalDeliveryType='" + postalDeliveryType + '\''
                + ", delivyPointGroupDid='" + delivyPointGroupDid + '\''
                + '}';
    }

}
