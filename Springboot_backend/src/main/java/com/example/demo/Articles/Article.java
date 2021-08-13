package com.example.demo.Articles;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table
public class Article {
    @Id
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence"

    )
    private long id;
    private String chercheur_id="";
    private long conference_id=12;
    private String name;
    private String auteur;
    private String titre;
    private String theme;
    private String resumer;
    private String mots_cle;
    private LocalDate date;
    @OneToOne
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


    /*private String article_pdf;*/
    public Article() {
    }
    public Article(long id,
                   String chercheur_id,
                   long conference_id,
                   String name,
                   String auteur,
                   String titre,
                   String theme,
                   String resumer,
                   String mots_cle,
                   LocalDate date,
                   File file) {
        this.id = id;
        this.chercheur_id = chercheur_id;
        this.conference_id = conference_id;
        this.name = name;
        this.auteur = auteur;
        this.titre = titre;
        this.theme = theme;
        this.resumer = resumer;
        this.mots_cle = mots_cle;
        this.date = date;
        this.file=file;
    }
    public Article(
                   String name,
                   String auteur,
                   String titre,
                   String theme,
                   String resumer,
                   String mots_cle,
                   LocalDate date) {
        this.name = name;
        this.auteur = auteur;
        this.titre = titre;
        this.theme = theme;
        this.resumer = resumer;
        this.mots_cle = mots_cle;
        this.date = date;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getChercheur_id() {
        return chercheur_id;
    }
    public void setChercheur_id(String chercheur_id) {
        this.chercheur_id = chercheur_id;
    }
    public long getConference_id() {
        return conference_id;
    }
    public void setConference_id(long conference_id) {
        this.conference_id = conference_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public String getResumer() {
        return resumer;
    }
    public void setResumer(String resumer) {
        this.resumer = resumer;
    }
    public String getMots_cle() {
        return mots_cle;
    }
    public void setMots_cle(String mots_cle) {
        this.mots_cle = mots_cle;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "article [auteur=" + auteur + ", chercheur_id=" + chercheur_id + ", conference_id=" + conference_id
                + ", date=" + date + ", id=" + id + ", mots_cle=" + mots_cle + ", name=" + name + ", resumer=" + resumer
                + ", theme=" + theme + ", titre=" + titre + ", fileID=" + file.getId() + ", fileName=" + file.getFileName() +  "]";
    }







}


