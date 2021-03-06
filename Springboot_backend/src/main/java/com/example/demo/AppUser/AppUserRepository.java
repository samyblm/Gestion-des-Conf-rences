package com.example.demo.AppUser;

import com.example.demo.Comment.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findAppUserByFirstName(String firstName);
    Optional<AppUser> findAppUserById(Long id);
    public AppUser findByEmailAndPassword(String email,String password);


    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
    default AppUser getUser( AppUser currentUser) {
        return getUserByName(currentUser.getFirstName());
    }
    default AppUser getUserByName(String firstName) {
        return findAppUserByFirstName(firstName).orElseThrow(() -> new ResourceNotFoundException("User", "username", firstName));
    }
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +"SET a.locked = TRUE WHERE a.email = ?1")
    int lockedAppUser(String email);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE AppUser a " +"SET a.email = ?2 WHERE a.email = ?1")
    int updateAppUser( String mail,String email);
}
