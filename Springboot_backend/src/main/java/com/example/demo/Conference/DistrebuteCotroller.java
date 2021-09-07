package com.example.demo.Conference;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/Conference/dest")
public class DistrebuteCotroller {

    private final DistrebuteService distrebuteService;
    @Autowired
    public DistrebuteCotroller(DistrebuteService distrebuteService) {
        this.distrebuteService = distrebuteService;
    }


    @PostMapping(path="dist/{conferenceId}")
    public void distribute (@PathVariable("conferenceId")Long conferenceId){
        distrebuteService.Distrubute(conferenceId);

    }
}
