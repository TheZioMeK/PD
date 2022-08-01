package pl.edu.pk.cloud;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "USERSTATS")
public class UserStat {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Integer idStat;
    private Long userQuantity;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date UserStatDate;

    @SuppressWarnings("unused")
    UserStat(){

    }

    public UserStat(Long userQuantity, Date sentDate) {
        this.userQuantity = userQuantity;
        this.UserStatDate = sentDate;
    }
}
