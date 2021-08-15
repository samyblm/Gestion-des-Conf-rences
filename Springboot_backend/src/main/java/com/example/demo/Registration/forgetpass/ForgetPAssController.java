package com.example.demo.Registration.forgetpass;

import com.example.demo.AppUser.AppUser;
import com.example.demo.AppUser.AppUserRepository;
import com.example.demo.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/v1/registration/reset")
@AllArgsConstructor
public class ForgetPAssController {


        private final ForgetPassService forgetPassService;
        private final AppUserService appUserService;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        private   AppUserRepository appUserRepository;


        @CrossOrigin
        @PostMapping
        public void resset(@RequestBody ForgetPassRequest request){
                forgetPassService.reset(request);
             }

    @CrossOrigin
    @PostMapping("pass/{Email}")
    public void resetPass(@PathVariable String Email ,@RequestBody String Pass ){
        System.out.println(Email);
        System.out.println(Pass);
        if(Email !=null){
            AppUser userObj = (AppUser) appUserService.loadUserByUsername(Email);
            userObj.setPassword(Pass);
            String encodedPassword =bCryptPasswordEncoder.encode(userObj.getPassword());
            System.out.println(encodedPassword);
            userObj.setPassword(encodedPassword);
            appUserRepository.save(userObj);
        }
    }

}

