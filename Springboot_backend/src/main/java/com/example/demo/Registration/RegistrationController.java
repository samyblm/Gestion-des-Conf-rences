package com.example.demo.Registration;
import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserService;
import com.example.demo.Registration.token.ConfirmationToken;
import com.example.demo.Registration.token.ConfirmationTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController   {

    @Autowired
    private JwtUtility jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    ObjectMapper mapper = new ObjectMapper();
    private final ConfirmationTokenService confirmationTokenService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final RegistrationService registrationService;
    private final AppUserService appUserService;


    @CrossOrigin
    @GetMapping("test")
    public String home(@RequestHeader String authorization) throws JsonProcessingException {

//        HashMap<String, String> map = new HashMap<>();
        System.out.println("ouaaaaiiis sel3aaaaa");

        String token = null;
        String userName = null;
        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }

        System.out.println(authorization);

        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);
        String jsonString = mapper.writeValueAsString(userObj);

        return jsonString;
    }




    @CrossOrigin
    @PostMapping
    public Map<String, String> register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }



    @CrossOrigin
    @PostMapping("login")
    public Map<String, String> loginUser(@RequestBody AppUser user) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        String admail=jwtTokenUtil.ADMINmail;
        String admdp=jwtTokenUtil.ADMINmdp;
        String Email = user.getEmail();
        String Pass = user.getPassword();
        System.out.println("hada wsh 9al yacine afficher1" + Email);
        System.out.println("hada wsh 9al yacine afficher2" + Pass);
        System.out.println("hada wsh 9al yacine afficher3" + user.toString());
        if(Email.equals(admail) && Pass.equals(admdp)){
            map.put("isError","false");
            map.put("Type", "Admin");
//            return "AHla bel Admin";
            return map;
        } else {
            Boolean Valide = false;
            AppUser userObj = null;
            if (Email != null && Pass != null) {
                try {
                    userObj = (AppUser) appUserService.loadUserByUsername(Email);
                    Valide = bCryptPasswordEncoder.matches(Pass, userObj.getPassword());
                    map.put("emailSignin", "");
                    if (Valide == true) {
                        map.put("isError","false");
                        final UserDetails userDetails = appUserService.loadUserByUsername(Email);
                        final String jwt = jwtTokenUtil.generateToken(userDetails);
                        String token = jwt;
                        ConfirmationToken confirmationToken = new ConfirmationToken(
                                token,
                                LocalDateTime.now(),
                                LocalDateTime.now().plusMinutes(15),
                                userObj
                        );
                        confirmationTokenService.saveConfirmationToken(confirmationToken);
                        map.put("token", token);
                    }
                    else if (Valide != true ) {
                        map.put("isError","true");
                        map.put("passwordSignin", "Wrong password, please try again");
                    }
                    return map;
                }
                catch (UsernameNotFoundException e) {
                    System.out.println("this is the exception part");
                    map.put("isError","true");
                    map.put("emailSignin", "Invalid credentials, please try again");
                    return map;
                }
            }
        }
        return map;
    }



    @CrossOrigin
    @GetMapping(path = "confirm/{TOKEN}")
    public String confirm(@PathVariable("TOKEN") String token) {
        System.out.println("token activate yacine mail");
        System.out.println(token);
        return registrationService.confirmToken(token);
    }
}

