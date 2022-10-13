package org.example.interfaces;

import java.io.IOException;

public interface IReCaptchaService {
    boolean verify(String reCaptchaToken) throws IOException;
}
