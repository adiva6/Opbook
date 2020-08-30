package com.example.opbook.validator;


import com.example.opbook.controller.UserController;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final String EMAIL_VERIFIER_URL = "http://apilayer.net/api/check?access_key=df08351118403ee23478633720dc676b&email=%s&smtp=1&format=1";

    @Override
    public void initialize(EmailConstraint emailConstraint) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return verifyEmail(s);
    }

    private boolean verifyEmail(String email) {
        try {
            String verifierAddress = String.format(EMAIL_VERIFIER_URL, email);
            URL url = new URL(verifierAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return false;
            }

            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream(),
                    StandardCharsets.UTF_8));
            return (boolean) response.get("smtp_check");

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
