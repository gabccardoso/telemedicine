package com.hackaton.telemedicine.infrastructure.gateways.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.hackaton.telemedicine.config.GoogleApiConfig;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.*;
import java.util.*;

@Service
public class GoogleMeetLinkGenerator {

    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final String TIME_ZONE = "America/Sao_Paulo"; // Time zone for Brazil

    private final GoogleApiConfig googleApiConfig;

    public GoogleMeetLinkGenerator(GoogleApiConfig googleApiConfig) {
        this.googleApiConfig = googleApiConfig;
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws Exception {
        FileInputStream in = new FileInputStream(googleApiConfig.getCredentialsFilePath());
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, Collections.singletonList(CalendarScopes.CALENDAR_EVENTS))
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public String createTeleconsultaEvent(String summary, String description, LocalDate data, LocalTime inicio, LocalTime fim, List<String> attendeesEmails) throws Exception {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        LocalDateTime startDateTime = data.atTime(inicio);
        LocalDateTime endDateTime = data.atTime(fim);

        // Converta LocalDateTime para ZonedDateTime
        ZonedDateTime startZonedDateTime = startDateTime.atZone(ZoneId.of(TIME_ZONE));
        ZonedDateTime endZonedDateTime = endDateTime.atZone(ZoneId.of(TIME_ZONE));

        // Converta ZonedDateTime para DateTime do Google API
        DateTime startDateTimeGoogle = new DateTime(startZonedDateTime.toInstant().toEpochMilli());
        DateTime endDateTimeGoogle = new DateTime(endZonedDateTime.toInstant().toEpochMilli());

        // Crie o evento do Google Calendar
        Event event = new Event()
                .setSummary(summary)
                .setDescription(description)
                .setStart(new EventDateTime()
                        .setDateTime(startDateTimeGoogle)
                        .setTimeZone(TIME_ZONE))
                .setEnd(new EventDateTime()
                        .setDateTime(endDateTimeGoogle)
                        .setTimeZone(TIME_ZONE));

        EventAttendee[] attendees = attendeesEmails.stream()
                .map(email -> new EventAttendee().setEmail(email))
                .toArray(EventAttendee[]::new);
        event.setAttendees(List.of(attendees));

        ConferenceSolutionKey conferenceSolutionKey = new ConferenceSolutionKey().setType("hangoutsMeet");
        CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest().setRequestId("sample123").setConferenceSolutionKey(conferenceSolutionKey);
        ConferenceData conferenceData = new ConferenceData().setCreateRequest(createConferenceRequest);
        event.setConferenceData(conferenceData);

        event = service.events().insert("primary", event).setConferenceDataVersion(1).execute();

        return event.getConferenceData().getEntryPoints().get(0).getUri();
    }
}
