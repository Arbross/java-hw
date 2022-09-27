package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.classes.GoogleResponse;
import org.example.interfaces.IReCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class ReCaptchaService implements IReCaptchaService {
    @Autowired
    private final RestOperations restTemplate;
    public boolean verify(String reCaptchaToken) throws IOException {
        if (reCaptchaToken == null || "".equals(reCaptchaToken)) {
            return false;
        }

        try {
            URI verifyUri = URI.create("https://www.google.com/recaptcha/api/siteverify?secret=6LdZha4gAAAAAAZ_kSRTNSlGN3y0UAtgE-WH36KN&response=" + reCaptchaToken);

            GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);

            if(!googleResponse.isSuccess()) {
                return false;
            }

            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
