package com.example.demo.AppUser;
import com.example.demo.Registration.token.ConfirmationToken;
import com.example.demo.Registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email  not found";

    private final static String Credentials_NOT_FOUND_MSG="Credentials erronneer";

    private  final AppUserRepository appUserRepository;
//    private  final AppUsernameRepository appUsernameRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        // System.out.println(String.format(USER_NOT_FOUND_MSG,email));
        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }


    public Optional<AppUser> findAppUserByFirstName(String firstName) {
        return Optional.ofNullable(appUserRepository.findAppUserByFirstName(firstName).orElseThrow(() -> new SecurityException("User not found in database")));
    }


    public AppUser fetchUserByEmailAndPass(String email, String pass){

        return appUserRepository.findByEmailAndPassword(email, pass);
    }



    public String signUpUser(AppUser appUser){
        boolean userExists=appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if(userExists) {
            throw new IllegalStateException("email already taken");
        }
        boolean firstnameExists=appUserRepository.findAppUserByFirstName(appUser.getFirstName())
                .isPresent();
        System.out.println("Fisrt name deja Exist ?");
        System.out.println(firstnameExists);

        String encodedPassword =bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }


}
