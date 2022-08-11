package pl.edu.pk.connector;

import com.google.api.client.util.DateTime;
import pl.edu.pk.calendar.CalEvent;
import pl.edu.pk.calendar.CalendarController;
import pl.edu.pk.cloud.SentEventRepository;
import pl.edu.pk.cloud.UserStatRepository;
import pl.edu.pk.rpi.RaspberryController;
import pl.edu.pk.user.User;
import pl.edu.pk.user.UserRepository;

import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Background implements Runnable {
    @Override
    public void run() {

        while (true) {
            Long quantity = UserRepository.getUsersQuantity();

            for (int i = 1; i <= quantity; i++) {
                User user = UserRepository.getUserById(i);
                try {
                    List<CalEvent> list = CalendarController.userEvents(user.getCalendarEmail() + ".json", user.getCalendarEmail());
                    for(int j = 0; j < list.size(); j++)
                    {
                        DateTime now = new DateTime(new Date());
                        CalEvent event = list.get(j);
                        Date eventTime = new Date(event.getStartEvent().getValue());
                        if ((event.getStartEvent().getValue() <= (now.getValue() - 3600000* user.getNotificationTime()))   &&  (event.getStartEvent().getValue() > (now.getValue() - 3600000 * (user.getNotificationTime() - 1L) ))){
                            RaspberryController.sendSMS(user.getPhoneNumber(), "Przypomienie: " + event.getNameEvent() + " o godzinie: " + eventTime.getHours()+":"+eventTime.getMinutes() );
                            SentEventRepository.addSentEvent(user.getCalendarEmail(), new Date());
                        }

                    }
                } catch (IOException | GeneralSecurityException e) {
                    System.out.println("Problem z uwierzytelnieniem uzytkownika: " + user.getCalendarEmail() + "!");
                    return;
                }

            }

            Long userQuantity = UserRepository.getUsersQuantity();
            UserStatRepository.addUserStat(userQuantity, new Date());

            Date end = new Date();
            long timeToSleep = ((60-end.getMinutes())*60000);
            try {
                Thread.sleep(timeToSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
