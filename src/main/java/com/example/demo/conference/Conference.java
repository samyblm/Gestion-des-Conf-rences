package com.example.demo.conference;



import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table
public class Conference {
    @Id
    @SequenceGenerator(
     name = "conference_sequence",
     sequenceName = "conference_sequence",
      allocationSize = 1
    )
   @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conference_sequence"
   )


    private long id;
    private String nom;
    private LocalDate deadline;
    private String company_name;
    private String resumer;
    private String application_condition;
    private boolean validity;

    public Conference() {
    }

    public Conference(long id,
                      String nom,
                      LocalDate deadline,
                      String company_name,
                      String resumer,
                      String application_condition,
                      boolean validity) {
        this.id = id;
        this.nom = nom;
        this.deadline = deadline;
        this.company_name = company_name;
        this.resumer = resumer;
        this.application_condition = application_condition;
        this.validity = validity;
    }

    public Conference(String nom,
                      LocalDate deadline,
                      String company_name,
                      String resumer,
                      String application_condition,
                      boolean validity) {
        this.nom = nom;
        this.deadline = deadline;
        this.company_name = company_name;
        this.resumer = resumer;
        this.application_condition = application_condition;
        this.validity = validity;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name =company_name;
    }

    public String getResumer() {
        return resumer;
    }

    public void setResumer(String resumer) {
        this.resumer = resumer;
    }

    public String getApplication_condition() {
        return application_condition;
    }

    public void setApplication_condition(String application_condition) {
        this.application_condition = application_condition;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "conference{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", deadline=" + deadline +
                ", company_name='" + company_name + '\'' +
                ", resumer='" + resumer + '\'' +
                ", application_condition='" + application_condition + '\'' +
                ", validity=" + validity +
                '}';
    }
}

