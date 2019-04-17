package htwg.konstantz.cloud.ex1.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import htwg.konstantz.cloud.ex1.entities.ImageFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class Credentials {




    public static Optional<GoogleCredentials> getCredentials() {
        InputStream resourceAsStream = Credentials.class.getResourceAsStream("service-account.json");
        GoogleCredentials googleCredentials = null;
        try {
             googleCredentials = GoogleCredentials.fromStream(resourceAsStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  Optional.ofNullable(googleCredentials);
    }
}
