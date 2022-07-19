package pl.edu.pk.calendar;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static pl.edu.pk.calendar.CalendarService.getEventsList;

public class CalendarController {

    public static List<CalEvent> userEvents(String userFileName, String userEmail) throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = CalendarService.getCredentials(userFileName);
        List<CalEvent> lista = getEventsList(CalendarService.getEvents(credentials,userEmail));
        return lista;
    }

}
