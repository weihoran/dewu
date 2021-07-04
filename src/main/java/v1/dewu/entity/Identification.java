package v1.dewu.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Identification {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long serialNumber;

    @Column
    private String productName;

    @Column
    private String expert;

    @ElementCollection
    private List<String> imagelist;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getExpert(){return expert;}

    public void setExpert(String expert) {
        this.expert = expert;
    }

    public List getImagelist() {
        return imagelist;
    }

    public void setImagelist(List imagelist) {
        this.imagelist = imagelist;
    }
}
