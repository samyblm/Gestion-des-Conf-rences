package com.example.demo.Registration.token;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private  final ConfirmationTokenRepository confirmationTokenRepository;
    private static  ConfirmationTokenRepository cconfirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public static Long DeleteToken(String token) {
        Optional<ConfirmationToken> Tok = cconfirmationTokenRepository.findByToken(token);
        cconfirmationTokenRepository.deleteById(Tok.get().getId());
       return Tok.get().getId();
    }
}