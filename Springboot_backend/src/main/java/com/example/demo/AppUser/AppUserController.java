package com.example.demo.AppUser;
import com.example.demo.Conference.Conference;
import com.example.demo.Conference.ConferenceRepository;
import com.example.demo.Registration.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/Appuser")
public class AppUserController {

    @Autowired
    private JwtUtility jwtTokenUtil;
    private final AppUserService appUserService;
    private final ConferenceRepository conferenceRepository;
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserService appUserService, ConferenceRepository conferenceRepository, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.conferenceRepository = conferenceRepository;
        this.appUserRepository = appUserRepository;
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


}
