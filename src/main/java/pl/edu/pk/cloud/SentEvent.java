package pl.edu.pk.cloud;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table( name = "SENTEVENTS")
public class SentEvent {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer idEvent;
    private String userEmail;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date sentDate;

    @SuppressWarnings("unused")
    SentEvent(){

    }
    public SentEvent(String userEmail, Date sendDate) {
        this.userEmail = userEmail;
        this.sentDate = sendDate;
    }

}
