package au.com.auspost.microservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by miaot on 13/10/2015.
 */
@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String recordActnCode;
    private String typeId;
    private String typeItem;
    private String typeItemAbbr;
    private String typeActnCode;

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

        return "Code{"
                + "recordActnCode='" + recordActnCode + '\''
                + ", typeId='" + typeId + '\''
                + ", typeItem='" + typeItem + '\''
                + ", typeItemAbbr='" + typeItemAbbr + '\''
                + ", typeActnCode='" + typeActnCode + '\''
                + '}';
    }

}
