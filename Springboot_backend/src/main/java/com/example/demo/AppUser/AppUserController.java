package com.example.demo.AppUser;
import com.example.demo.Conference.Conference;
import com.example.demo.Conference.ConferenceRepository;
import com.example.demo.Registration.JwtUtility;
import com.example.demo.Registration.token.ConfirmationToken;
import com.example.demo.Registration.token.ConfirmationTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "api/v1/Appuser")
public class AppUserController {

    @Autowired
    private JwtUtility jwtTokenUtil;
    private final AppUserService appUserService;
    private final ConferenceRepository conferenceRepository;
    private final AppUserRepository appUserRepository;
    private  final ConfirmationTokenService confirmationTokenService;
    ObjectMapper mapper = new ObjectMapper();

    public AppUserController(AppUserService appUserService, ConferenceRepository conferenceRepository, AppUserRepository appUserRepository, ConfirmationTokenService confirmationTokenService) {
        this.appUserService = appUserService;
        this.conferenceRepository = conferenceRepository;
        this.appUserRepository = appUserRepository;
        this.confirmationTokenService = confirmationTokenService;
    }


    @PostMapping(path = "accept/{conferenceId}")
    public void AcceptInvtRev(@PathVariable Long conferenceId,@RequestHeader String authorization){
        String token = null;
        String userName = null;
        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }
        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);
        userObj.setreviewer(true);
        userObj.addidConfInvtConfirmerev(Long.toString(conferenceId),userObj.getidConfInvtConfirmerevString());
        System.out.println(userObj.getidConfInvtforrevieuwString() + "test deleellet 1");
        System.out.println(userObj.getidConfInvtforrevieuwString().replaceAll(" "+Long.toString(conferenceId),"") + "test deleellet 2");
        userObj.setidConfInvtforrevieuw(userObj.getidConfInvtforrevieuwString().replaceAll(" "+Long.toString(conferenceId),""));
        appUserRepository.save(userObj);
       Conference conf=conferenceRepository.findById(conferenceId).orElseThrow(()-> new IllegalStateException("conf does not exist"));
       conf.addidViewersFinal(userName,conf.getidViewersFinalString());
       conf.setidViewers(conf.getidViewersString().replaceAll(userName,""));
       conferenceRepository.save(conf);
    }


    @PostMapping(path="refuse/{conferenceId}")
    public void refuseInvtRev(@PathVariable Long conferenceId,@RequestHeader String authorization){
        String token = null;
        String userName = null;
        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }
        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);

        Conference conf=conferenceRepository.findById(conferenceId).orElseThrow(()-> new IllegalStateException("conf does not exist"));

        userObj.setidConfInvtforrevieuw(userObj.getidConfInvtforrevieuwString().replaceAll(" "+Long.toString(conferenceId),""));
        appUserRepository.save(userObj);
        conf.addidViewersRefus(userName,conf.getidViewersRefusString());
        conf.setidViewers(conf.getidViewersString().replaceAll(userName,""));
        conferenceRepository.save(conf);



    }






    //get 1 USer
    @GetMapping(path="get/{USermail}")
    public String home(@PathVariable("USermail") String USermail) throws JsonProcessingException {

        AppUser userObj = (AppUser) appUserService.loadUserByUsername(USermail);
        String jsonString = mapper.writeValueAsString(userObj);

        return jsonString;
    }

    //get all users
    @GetMapping(path="gets")
    public List<AppUser> getusers()throws JsonProcessingException {
        List<AppUser> userObj = (List<AppUser>) appUserRepository.findAll();

        return  userObj;
    }

    //delete user

    @DeleteMapping(path = "{USermail}")
    public void deleteArticle(
            @PathVariable("USermail") String USermail){
        appUserService.lockedAppUser(USermail);
    }


    @PutMapping(path = "update/{USermail}")
    public Map<String, String> updateArticle(
            @PathVariable("USermail") String USermail,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String resume,
            @RequestParam(required = false) String mots_cle,
            @RequestParam(required = false) String theme){
        HashMap<String, String> map = new HashMap<>();
        map.put("ydra", "jwt");

        AppUser userObj = (AppUser) appUserRepository.findByEmail(USermail).orElseThrow();
        if (name !=null && name.length() > 0 && !Objects.equals(userObj.getEmail(), name)){
            appUserService.updateAppUser(USermail,name);
            System.out.println("name");
            System.out.println(name);
            System.out.println("test email");
            AppUser user=new AppUser();
            AppUser userObj12 = (AppUser) appUserRepository.findByEmail(name).orElseThrow();

            System.out.println( userObj12.getEmail());
           String jwt= appUserService.generate_new_token(name);
            map.put("token", jwt);
        }

        return map;


    }



}
