package com.example.demo.conference;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/conference")

public class ConferenceController {
    private ConferenceService conferenceService ;
    @Autowired

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @GetMapping
    public List<Conference> getConferences(){ return conferenceService.getConferences();
    }
    @GetMapping(path = "{conferenceId}")
    public Optional<Conference> getConference(@PathVariable  Long conferenceId){
        return conferenceService.getConference(conferenceId);
    }
    @PostMapping
    public void registerNewConference (@RequestBody Conference conference){
            conferenceService.addNewConference(conference);
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





