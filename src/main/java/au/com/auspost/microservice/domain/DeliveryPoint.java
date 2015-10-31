package au.com.auspost.microservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 * Created by terrence on 28/10/15.
 */
@Entity
public class DeliveryPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String recordActnCode;
    private String delivyPointId;
    private String delivyPointGroupId;
    @Column(name = "HOUSE_NBR_1")
    private String houseNbr1;
    @Column(name = "HOUSE_NBR_SFX_1")
    private String houseNbrSfx1;
    @Column(name = "HOUSE_NBR_2")
    private String houseNbr2;
    @Column(name = "HOUSE_NBR_SFX_2")
    private String houseNbrSfx2;
    private String flatUnitType;
    private String flatUnitNbr;
    private String floorLevelType;
    private String floorLevelNbr;
    private String lotNbr;
    private String postalDeliveryNbr;
    private String postalDeliveryNbrPfx;
    private String postalDeliveryNbrSfx;
    private String primaryPointInd;

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

    public String getDelivyPointId() {
        return delivyPointId;
    }

    public void setDelivyPointId(String delivyPointId) {
        this.delivyPointId = delivyPointId;
    }

    public String getDelivyPointGroupId() {
        return delivyPointGroupId;
    }

    public void setDelivyPointGroupId(String delivyPointGroupId) {
        this.delivyPointGroupId = delivyPointGroupId;
    }

    public String getHouseNbr1() {
        return houseNbr1;
    }

    public void setHouseNbr1(String houseNbr1) {
        this.houseNbr1 = houseNbr1;
    }

    public String getHouseNbrSfx1() {
        return houseNbrSfx1;
    }

    public void setHouseNbrSfx1(String houseNbrSfx1) {
        this.houseNbrSfx1 = houseNbrSfx1;
    }

    public String getHouseNbr2() {
        return houseNbr2;
    }

    public void setHouseNbr2(String houseNbr2) {
        this.houseNbr2 = houseNbr2;
    }

    public String getHouseNbrSfx2() {
        return houseNbrSfx2;
    }

    public void setHouseNbrSfx2(String houseNbrSfx2) {
        this.houseNbrSfx2 = houseNbrSfx2;
    }

    public String getFlatUnitType() {
        return flatUnitType;
    }

    public void setFlatUnitType(String flatUnitType) {
        this.flatUnitType = flatUnitType;
    }

    public String getFlatUnitNbr() {
        return flatUnitNbr;
    }

    public void setFlatUnitNbr(String flatUnitNbr) {
        this.flatUnitNbr = flatUnitNbr;
    }

    public String getFloorLevelType() {
        return floorLevelType;
    }

    public void setFloorLevelType(String floorLevelType) {
        this.floorLevelType = floorLevelType;
    }

    public String getFloorLevelNbr() {
        return floorLevelNbr;
    }

    public void setFloorLevelNbr(String floorLevelNbr) {
        this.floorLevelNbr = floorLevelNbr;
    }

    public String getLotNbr() {
        return lotNbr;
    }

    public void setLotNbr(String lotNbr) {
        this.lotNbr = lotNbr;
    }

    public String getPostalDeliveryNbr() {
        return postalDeliveryNbr;
    }

    public void setPostalDeliveryNbr(String postalDeliveryNbr) {
        this.postalDeliveryNbr = postalDeliveryNbr;
    }

    public String getPostalDeliveryNbrPfx() {
        return postalDeliveryNbrPfx;
    }

    public void setPostalDeliveryNbrPfx(String postalDeliveryNbrPfx) {
        this.postalDeliveryNbrPfx = postalDeliveryNbrPfx;
    }

    public String getPostalDeliveryNbrSfx() {
        return postalDeliveryNbrSfx;
    }

    public void setPostalDeliveryNbrSfx(String postalDeliveryNbrSfx) {
        this.postalDeliveryNbrSfx = postalDeliveryNbrSfx;
    }

    public String getPrimaryPointInd() {
        return primaryPointInd;
    }

    public void setPrimaryPointInd(String primaryPointInd) {
        this.primaryPointInd = primaryPointInd;
    }

    @Override
    public String toString() {
        return "DeliveryPoint{"
                + "id=" + id
                + ", recordActnCode='" + recordActnCode + '\''
                + ", delivyPointId='" + delivyPointId + '\''
                + ", delivyPointGroupId='" + delivyPointGroupId + '\''
                + ", houseNbr1='" + houseNbr1 + '\''
                + ", houseNbrSfx1='" + houseNbrSfx1 + '\''
                + ", houseNbr2='" + houseNbr2 + '\''
                + ", houseNbrSfx2='" + houseNbrSfx2 + '\''
                + ", flatUnitType='" + flatUnitType + '\''
                + ", flatUnitNbr='" + flatUnitNbr + '\''
                + ", floorLevelType='" + floorLevelType + '\''
                + ", floorLevelNbr='" + floorLevelNbr + '\''
                + ", lotNbr='" + lotNbr + '\''
                + ", postalDeliveryNbr='" + postalDeliveryNbr + '\''
                + ", postalDeliveryNbrPfx='" + postalDeliveryNbrPfx + '\''
                + ", postalDeliveryNbrSfx='" + postalDeliveryNbrSfx + '\''
                + ", primaryPointInd='" + primaryPointInd + '\''
                + '}';
    }

}
