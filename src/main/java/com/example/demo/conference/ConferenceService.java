package com.example.demo.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;
      @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }


    public List<Conference> getConferences(){
        return  conferenceRepository.findAll();
    }


    public Optional<Conference> getConference(Long conferenceId) {   return conferenceRepository.findById(conferenceId);  }

    public void addNewConference(Conference conference){
         conferenceRepository.save(conference);


    }

    public void deleteConference(Long conferenceId) {
          boolean exists =conferenceRepository.existsById(conferenceId);
          if (!exists){
                  throw new IllegalStateException("Conference with id " + conferenceId + "does not exists");
              }

          
          conferenceRepository.deleteById(conferenceId);
      }
    @Transactional
    public void updateConference(Long conferenceId,
                                 String nom,
                                 LocalDate deadline,
                                 String company_name,
                                 String resumer,
                                 String application_condition,
                                 boolean validity) {
          Conference conference = conferenceRepository.findById(conferenceId)
                  .orElseThrow(()-> new IllegalStateException("Conference with id "+conferenceId+"does not exists"));


          if ( nom !=null &&
               nom.length()>0 &&
               !Objects.equals(conference.getNom(),nom)){
             conference.setNom(nom);
          }
         if ( deadline!=null &&
              !Objects.equals(conference.getDeadline(),deadline)){
            conference.setDeadline(deadline);
          }
         if ( company_name !=null &&
             company_name.length()>0 &&
             !Objects.equals(conference.getCompany_name(),company_name)){
            conference.setCompany_name(company_name);
         }
         if ( resumer !=null &&
            resumer.length()>0 &&
            !Objects.equals(conference.getResumer(),resumer)){
           conference.setResumer(resumer);
          }
         if ( application_condition !=null &&
            application_condition.length()>0 &&
            !Objects.equals(conference.getApplication_condition(),application_condition)){
           conference.setApplication_condition(application_condition);
         }
         if (
                 !Objects.equals(conference.isValidity(), validity)){
             conference.setValidity(validity);
        }

}



}
