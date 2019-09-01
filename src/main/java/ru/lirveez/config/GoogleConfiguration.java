package ru.lirveez.config;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Configuration
public class GoogleConfiguration {

    @Bean
    public Credential credential() throws IOException {
        InputStream is = GoogleConfiguration.class.getResourceAsStream("/googleCreds.json");
        return GoogleCredential.fromStream(is)
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
    }

    @Bean
    public static Sheets getSheetsService(Credential credential) throws GeneralSecurityException, IOException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("Vk-swgoh-bot").build();
    }
}
