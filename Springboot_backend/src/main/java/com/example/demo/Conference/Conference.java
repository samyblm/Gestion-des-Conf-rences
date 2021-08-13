package com.example.demo.Conference;


import com.example.demo.Articles.Article;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import static java.util.Collections.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;

@Entity
@Table
public class Conference {


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
    private String nom;
    private LocalDate deadline;
    private String company_name;
    private String resumer;
    private String application_condition;
    private boolean validity;
    private String Owner;

//    private Long idArticles;


    private String idArticles="po";
    private String idViewers="";

    public List<String> getidArticles() {
        return Arrays.asList(idArticles.split(";"));
    }



    public List<String> getidViewers() {
        return Arrays.asList(idViewers.split(" "));
    }


    public String getidArticlesString() {
        return idArticles;
    }

//    public String getidViewer() {
//        return idViewers;
//    }


//    public Long getIdArticles() {
//        return idArticles;
//    }


//    public ArrayList<Long> getIdArticles() {
//        return (ArrayList<Long>) idArticles;
//    }

    public void setIdArticles(String idArticles) {
        this.idArticles = idArticles;
    }

    public void addIdArticles(String idarticle,String kdim) {

        if(kdim.startsWith("po")){
            this.idArticles= idarticle;
        }else {
            this.idArticles= this.idArticles+";"+idarticle;
        }

    }

    public void addidViewers(String idViewers) {
        this.idViewers= this.idViewers+" "+idViewers;
    }

//        public void setIdArticles(Long idArticles) {
//      this.idArticles = idArticles;
//    }




    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }




    public Conference(long id,
                      String nom,
                      LocalDate deadline,
                      String company_name,
                      String resumer,
                      String application_condition,
                      boolean validity,String Owner,String idArticles,String idViewers) {
        this.id = id;
        this.nom = nom;
        this.deadline = deadline;
        this.company_name = company_name;
        this.resumer = resumer;
        this.application_condition = application_condition;
        this.validity = validity;
        this.Owner = Owner;
        this.idArticles=idArticles;
        this.idViewers=idViewers;
    }
    public Conference(String nom,
                      LocalDate deadline,
                      String company_name,
                      String resumer,
                      String application_condition,
                      boolean validity,String Owner,String idArticles,String idViewers){
        this.nom = nom;
        this.deadline = deadline;
        this.company_name = company_name;
        this.resumer = resumer;
        this.application_condition = application_condition;
        this.validity = validity;
        this.Owner = Owner;
        this.idArticles=idArticles;
        this.idViewers=idViewers;
    }

    public Conference() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Conference{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", deadline=" + deadline +
                ", company_name='" + company_name + '\'' +
                ", resumer='" + resumer + '\'' +
                ", application_condition='" + application_condition + '\'' +
                ", validity=" + validity +
                ", Owner='" + Owner + '\'' +
                "idArticles'" +  idArticles + '\'' +
                "idViewers" +  idViewers + '\'' +
                '}';
    }

}




@Converter
 class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        return string != null ? Arrays.asList(string.split(SPLIT_CHAR)) : emptyList();
    }
}