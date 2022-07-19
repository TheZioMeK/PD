package pl.edu.pk.calendar;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

public class CalEvent {
    private String nameEvent;
    private DateTime startEvent;
    private boolean allDay;

    public String getNameEvent() {
        return nameEvent;
    }

    public DateTime getStartEvent() {
        return startEvent;
    }

    public boolean isAllDay() {
        return allDay;
    }

    CalEvent(Event event){
        nameEvent = event.getSummary();
        startEvent = event.getStart().getDateTime();
        allDay = false;
        if (startEvent == null) {
            startEvent = event.getStart().getDate();
            allDay = true;
        }



    }
}
