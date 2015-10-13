package au.com.auspost.microservice.domain;

import javax.persistence.Entity;

/**
 * Created by miaot on 13/10/2015.
 */
@Entity
public class Code {

    private String recordActnCode;
    private String typeId;
    private String typeItem;
    private String typeItemAbbr;
    private String typeActnCode;

    public String getRecordActnCode() {
        return recordActnCode;
    }

    public void setRecordActnCode(String recordActnCode) {
        this.recordActnCode = recordActnCode;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }

    public String getTypeItemAbbr() {
        return typeItemAbbr;
    }

    public void setTypeItemAbbr(String typeItemAbbr) {
        this.typeItemAbbr = typeItemAbbr;
    }

    public String getTypeActnCode() {
        return typeActnCode;
    }

    public void setTypeActnCode(String typeActnCode) {
        this.typeActnCode = typeActnCode;
    }

    @Override
    public String toString() {
        return "Code{" +
                "recordActnCode='" + recordActnCode + '\'' +
                ", typeId='" + typeId + '\'' +
                ", typeItem='" + typeItem + '\'' +
                ", typeItemAbbr='" + typeItemAbbr + '\'' +
                ", typeActnCode='" + typeActnCode + '\'' +
                '}';
    }

}
