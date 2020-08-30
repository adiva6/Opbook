package com.example.opbook.validator;

import com.example.opbook.controller.LectureController;
import org.json.simple.JSONArray;
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

public class VideoValidator implements ConstraintValidator<VideoConstraint, String> {
    private static final Logger logger = LoggerFactory.getLogger(LectureController.class);
    private final String TOKEN = "CLC1yQEIl7bJAQijtskBCMS2yQEIqZ3KAQiXrMoBCIa1ygEImbXKAQjnyMoBCOnIygEI8MnKAQi0y8oBCJXWygE=";
    private final String YOUTUBE_VERIFIER_URL = "https://www.googleapis.com/youtube/v3/videos?id=%s&key=AIzaSyCFf072bQGKxQcEmT7ecpyaTyXyzzKCOF0";
    @Override
    public void initialize(VideoConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return verifyVideoId(s);
    }

    private boolean verifyVideoId(String youtubeId) {
        try {
            String verifierAddress = String.format(YOUTUBE_VERIFIER_URL, youtubeId);
            URL url = new URL(verifierAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-client-data", TOKEN);
            con.connect();
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return false;
            }

            JSONParser parser = new JSONParser();
            JSONObject response = (JSONObject) parser.parse(new InputStreamReader(con.getInputStream(),
                    StandardCharsets.UTF_8));
            JSONArray items = (JSONArray) response.get("items");
            return items.size() > 0;

        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
