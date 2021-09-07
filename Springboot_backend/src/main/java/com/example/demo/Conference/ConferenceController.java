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
    private final DistrebuteService distrebuteService;


    @Autowired
    public ConferenceController(ConferenceService conferenceService, AppUserService appUserService, AppUserRepository appUserRepository, DistrebuteService distrebuteService) {
        this.conferenceService = conferenceService;
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
        this.distrebuteService = distrebuteService;
    }

    @GetMapping
    public List<Conference> getConferences(){
        return conferenceService.getConference();
    }

    @GetMapping(path = "{conferenceId}")
    public Optional<Conference> getConference(@PathVariable  Long conferenceId){
        return conferenceService.getConferences(conferenceId);
    }


    @PostMapping(path = "dist/{conferenceId}")
    public void distrubute(@PathVariable  Long conferenceId){
             distrebuteService.Distrubute(conferenceId);
    }


    @PostMapping
    public void registerNewConference(@RequestBody Conference conference , @RequestHeader String authorization){

        String token = null;
        String userName = null;

        List<String> viewers= conference.getidViewers();





        if(null != authorization && authorization.startsWith("Bearer ")) {
            token = authorization.substring(7);
            userName = jwtTokenUtil.getUsernameFromToken(token);
        }
        AppUser userObj = (AppUser) appUserService.loadUserByUsername(userName);

        conferenceService.addNewConf(conference,userName);

        Long Conf= conference.getId();
        userObj.setIdConf(Conf);
        userObj.setconferencier(true);
        appUserRepository.save(userObj);
        System.out.println(Conf);
        System.out.println(userObj);

        for (String value:viewers
        ) {
            Optional<AppUser> userObjj = appUserRepository.findByEmail(value);
            AppUser userap= userObjj.orElseThrow(()-> new IllegalStateException("user does not exist"));
            System.out.println(userap + "1hda sam ya sam blk user");
            System.out.println(conference.getId() + "1hda sam ya sam blk 0");
            System.out.println(Long.toString(Conf) + "2hda sam ya sam blk 0");
            userap.addidConfInvtforrevieuw(Long.toString(Conf),userap.getidConfInvtforrevieuwString());
            appUserRepository.save(userap);

        }


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

    @GetMapping(path = "rev/{conferenceId}")
    public String  getrev(@PathVariable("conferenceId") Long conferenceId){

        String map="{"+ "\n" ;
        Conference conf=conferenceService.getConferences(conferenceId).orElseThrow(()-> new IllegalStateException("Confe dont exist"));

        for (String value:conf.getidViewersFinal()
        ) {
            System.out.println(value);
            map=map+ '"' + "Accepted" + '"' + ":" +'"'+ value+'"'+","+ "\n";
        }
        for (String value:conf.getidViewers()
        ) {
            System.out.println(value);
            map=map +'"'+ "Waiting"+'"' + ":" +'"'+ value+'"'+","+ "\n";
        }
        for (String value:conf.getidViewersRefus()
        ) {
            System.out.println(value);
            map=map +'"'+ "Refused" +'"'+ ":" +'"'+ value+'"'+","  ;
        }


        map = map.substring(0, map.length() -1);
        map= map + "\n";
        map=map + "}" ;

        return map;


    }




}
