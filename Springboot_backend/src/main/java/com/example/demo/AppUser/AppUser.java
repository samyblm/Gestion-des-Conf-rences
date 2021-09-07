package com.example.demo.AppUser;

import com.example.demo.Articles.File;
import com.example.demo.Comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @SequenceGenerator(name = "user_sequence", sequenceName = "use_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String curruntJob="Develloper";
    private String telf="";
    private String date="11/11/2000";
    private String sex="M";



    private String experience = "";
    private String competence = "";
    private String password;
    private Long idConf;
    private String idArticles = "";
    private String speciality;
    private String location;
    private Boolean chercheur = false;
    private Boolean conferencier = false;
    private Boolean reviewer = false;
    private String idConfInvtforrevieuw = "po";
    private String idConfInvtConfirmerev = "po";
    private String getIdArticlesCorrig ="";
    private String test ="";
    @OneToOne
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }



//    public String getExperience() {
//        return experience;
//    }



    public void setExperience(String experience) {
        this.experience = experience;
    }

//    public String getCompetence() {
//        return competence;
//    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }



    public String getCurruntJob() {
        return curruntJob;
    }

    public void setCurruntJob(String curruntJob) {
        this.curruntJob = curruntJob;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getexperience() {
        return Arrays.asList(experience.split(";"));
    }

    public List<String> getcompetence() {
        return Arrays.asList(competence.split(";"));
    }



    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public List<String> getTestarray() {
        return Arrays.asList(test.split(" "));
    }

    public String getspecialite() {
        return speciality;
    }

    public List<String> getspecialitearray() {
        return Arrays.asList(speciality.split("$"));
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setspecialite(String speciality) {
        this.speciality = speciality;
    }

    public List<String> getIdArticlesCorrig() {
        return Arrays.asList(idConfInvtConfirmerev.split(" "));
    }

    public String getIdArticlesCorrigString() {
        return getIdArticlesCorrig;
    }

    public void setIdArticlesCorrigString(String getIdArticlesCorrig) {
        this.getIdArticlesCorrig = getIdArticlesCorrig;
    }

    public List<String> getidConfInvtConfirmerev() {
        return Arrays.asList(idConfInvtConfirmerev.split(" "));
    }

    public void setidConfInvtConfirmerev(String idConfInvtConfirmerev) {
        this.idConfInvtConfirmerev = idConfInvtConfirmerev;
    }

    public String getidConfInvtConfirmerevString() {
        return idConfInvtConfirmerev;
    }

    public void addidConfInvtConfirmerev(String idConfInvtConfirmerev, String kdim) {

        if (kdim.startsWith("po")) {
            this.idConfInvtConfirmerev = idConfInvtConfirmerev;
        } else {
            this.idConfInvtConfirmerev = this.idConfInvtConfirmerev + " " + idConfInvtConfirmerev;
        }

    }

    public Boolean getchercheur() {
        return chercheur;
    }

    public Boolean getconferencier() {
        return conferencier;
    }

    public Boolean getreviewer() {
        return reviewer;
    }

    public void setchercheur(Boolean chercheur) {
        this.chercheur = chercheur;
    }

    public void setconferencier(Boolean conferencier) {
        this.conferencier = conferencier;
    }

    public void setreviewer(Boolean reviewer) {
        this.reviewer = reviewer;
    }

    public List<String> getidConfInvtforrevieuw() {
        return Arrays.asList(idConfInvtforrevieuw.split(" "));
    }

    public void setidConfInvtforrevieuw(String idConfInvtforrevieuw) {
        this.idConfInvtforrevieuw = idConfInvtforrevieuw;
    }

    public String getidConfInvtforrevieuwString() {
        return idConfInvtforrevieuw;
    }

    public void addidConfInvtforrevieuw(String idConfInvtforrevieuw, String kdim) {

        if (kdim.startsWith("po")) {
            this.idConfInvtforrevieuw = " " + idConfInvtforrevieuw;
        } else {
            this.idConfInvtforrevieuw = this.idConfInvtforrevieuw + " " + idConfInvtforrevieuw;
        }

    }

    public List<String> getidArticles() {
        return Arrays.asList(idArticles.split(";"));
    }

    public void setIdArticles(String idArticles) {
        this.idArticles = idArticles;
    }

    public void addIdArticles(String idarticle) {
        this.idArticles = this.idArticles + ";" + idarticle;
    }

    public Long getIdConf() {
        return idConf;
    }

    public void setIdConf(Long IdConf) {
        idConf = IdConf;
    }

    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String firstName, String lastName, String email, String password, String speciality, String location,
            AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.speciality = speciality;
        this.appUserRole = appUserRole;
        this.location = location;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public boolean isEmmpty() {
        if (this.getEmail() == null) {
            return true;
        } else {
            return false;
        }
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments == null ? null : new ArrayList<>(comments);
    }

    public void setComments(List<Comment> comments) {

        if (comments == null) {
            this.comments = null;
        } else {
            this.comments = Collections.unmodifiableList(comments);
        }
    }

}
