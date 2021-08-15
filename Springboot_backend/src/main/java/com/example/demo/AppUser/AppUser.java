package com.example.demo.AppUser;

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

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "use_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long idConf;
    private String idArticles="";

    public List<String> getidArticles() {
        return Arrays.asList(idArticles.split(";"));
    }
    public void setIdArticles(String idArticles) {
        this.idArticles = idArticles;
    }

    public void addIdArticles(String idarticle) {
        this.idArticles= this.idArticles+";"+idarticle;
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

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password,
                   AppUserRole appUserRole
                  ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(appUserRole.name());
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
        if(this.getEmail()==null){
            return true;
        }else {
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
