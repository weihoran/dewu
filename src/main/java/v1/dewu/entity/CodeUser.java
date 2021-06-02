
package v1.dewu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity
@Table
public class CodeUser {
    //mark id as primary key
    @Id
    @Column
    private String code;

    @Column(nullable = false)
    private int times;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void consumeTimes() {
        if (this.times >= 1)
            this.times--;
    }

}
