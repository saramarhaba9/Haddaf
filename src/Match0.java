/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Manar
 */
@Entity
@Table(name="match0")
public class Match0 implements java.io.Serializable{
    @Id
    @Column(name="id")
    private int id;
    @Column(name="stadiumName")
    private String stadiumName;
     @Column(name="time0")
    private String time0;
     @Column(name="type")
    private String type;
     @Column(name="code")
    private String code;

    public Match0() {
    }

    public Match0(int id, String stadiumName, String time, String type, String code) {
        this.id = id;
        this.stadiumName = stadiumName;
        this.time0 = time;
        this.type = type;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getTime() {
        return time0;
    }

    public void setTime(String time) {
        this.time0 = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
     
}
