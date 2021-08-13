package com.example.demo.Conference;



import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRepository;
import com.example.demo.AppUser.AppUserService;
import com.example.demo.Registration.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Conference")
public class ConferenceController {

    @Autowired
    private JwtUtility jwtTokenUtil;

    private final ConferenceService conferenceService;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;

    public ConferenceController(ConferenceService conferenceService, AppUserService appUserService, AppUserRepository appUserRepository) {
        this.conferenceService = conferenceService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public List<Conference> getConferences(){
        return conferenceService.getConference();
    }

    @GetMapping(path = "{conferenceId}")
    public Optional<Conference> getConference(@PathVariable  Long conferenceId){
        return conferenceService.getConferences(conferenceId);
    }


    @PostMapping
    public void registerNewConference(@RequestBody Conference conference , @RequestHeader String authorization){

        String token = null;
        String userName = null;

        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }
        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);

        conferenceService.addNewConf(conference,userName);

        Long Conf= conference.getId();
        userObj.setIdConf(Conf);
        appUserRepository.save(userObj);
        System.out.println(Conf);
        System.out.println(userObj);


    }

    @DeleteMapping(path="{conferenceId}")
    public void deleteConference(@PathVariable("conferenceId")Long conferenceId){
        conferenceService.deleteConference(conferenceId);
    }




    @PutMapping(path = "{conferenceId}")
    public void updateConference(
            @PathVariable("conferenceId") Long conferenceId,
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) LocalDate deadline,
            @RequestParam(required = false) String company_name,
            @RequestParam(required = false) String resumer,
            @RequestParam(required = false) String application_condition,
            @RequestParam(required = false) boolean validity
    ){
        conferenceService.updateConference(conferenceId,nom,deadline,company_name,resumer,application_condition,validity);
    }




}
