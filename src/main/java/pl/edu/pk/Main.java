package pl.edu.pk;

import com.google.api.client.util.DateTime;
import pl.edu.pk.cloud.SentEventRepository;
import pl.edu.pk.cloud.UserStatRepository;
import pl.edu.pk.user.User;
import pl.edu.pk.user.UserRepository;

import java.util.Date;


public class Main {

    private static final String path = "/home/theziomek/pd";

    public static void main(String[] a){


        Date start = new Date();

        long llopng = start.getMinutes();

        System.out.println((60-llopng)*60000);



//        User nowy = UserRepository.getUserById(1);
//        UserRepository.addUser("testowekonto654@gmail.com", "728719047", 1);
//
//        UserStatRepository.addUserStat(UserRepository.getUsersQuantity(), new Date());
//
       //SentEventRepository.addSentEvent("tes2t",new Date());


    }
}
