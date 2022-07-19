package pl.edu.pk.calendar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarService {

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "PowiadomieniaSMS";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    public static GoogleCredentials getCredentials(String name) throws IOException {

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/home/theziomek/Downloads/demo/src/main/resources/" + name + ".json"))
                .createScoped(Collections.singletonList(CalendarScopes.CALENDAR_READONLY));
        return credentials;
    }

    public static Events getEvents(GoogleCredentials credential, String calendarEmail) throws IOException, GeneralSecurityException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credential);



        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();


        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = service.events().list(calendarEmail)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events;
    }

    public static List<CalEvent> getEventsList(Events events){

        List<Event> items = events.getItems();
        List<CalEvent> eventsList = new ArrayList<>();
        for (Event event : items) {
            eventsList.add(new CalEvent(event));
        }

        return eventsList;

    }


}
