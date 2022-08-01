package pl.edu.pk.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "SMSUSERS")
public class User {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer id;
    private String calendarEmail;
    private String phoneNumber;
    private Integer notificationTime;
    private boolean activated;


    /**
     *  Hibernate (JPA) needs it.
     */
    @SuppressWarnings("unused")
    User(){

    }


    public User(String calendarEmail, String phoneNumber, Integer notificationTime) {
        this.calendarEmail = calendarEmail;
        this.phoneNumber = phoneNumber;
        this.notificationTime = notificationTime;
        this.activated = true;
    }

    public Integer getId() {
        return id;
    }

    public String getCalendarEmail() {
        return calendarEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Integer getNotificationTime() {
        return notificationTime;
    }

    @Override
    public String toString() {
        return id + " " + phoneNumber;
    }
}
