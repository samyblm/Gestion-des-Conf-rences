package com.example.demo.Registration.token;

public class AuthREspsonse {
        private String jwt;

            public void AuthentificationResponse(String jwt){
                this.jwt=jwt;
            }

            public String getJwt(){
                return jwt;
            }


}
