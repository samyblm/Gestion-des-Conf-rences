package com.example.demo.Notification.Notifweb;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;


@Entity
@Table
public class Notif {

    @Id
    @SequenceGenerator(
            name = "conf_sequence",
            sequenceName = "conf_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conf_sequence"
    )

    private long id;


    private String idnotif;
    private String usermail;
    private String body;



    public Notif() {

    }

    public Notif(long id, String idnotif, String usermail, String body) {
        this.id = id;
        this.idnotif = idnotif;
        this.usermail = usermail;
        this.body = body;
    }
    public Notif( String idnotif,String usermail , String body) {
        this.idnotif = idnotif;
        this.usermail = usermail;
        this.body = body;
    }




    public String getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(String idnotif) {
        this.idnotif = idnotif;
    }

    public String  getUserid() {
        return usermail;
    }

    public void setUserid(String usermail) {
        this.usermail = usermail;
    }

    public String getBody() {
        return body;
    }

    public List<String> getBodyArray() {
        return Arrays.asList(body.split("$"));
    }

    public void setBody(String body) {
        this.body = body;
    }



    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", idnotif=" + idnotif +
                ", usermail=" + usermail +
                ", body='" + body + '\'' +
                '}';
    }


}
