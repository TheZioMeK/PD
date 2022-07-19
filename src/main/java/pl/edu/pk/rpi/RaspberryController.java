package pl.edu.pk.rpi;

public class RaspberryController {

    public static boolean sendSMS(String number, String message){
        int port = RaspberryService.openCommunication();
        try {
            RaspberryService.sendSMS(port,number,message);
            RaspberryService.closeCommunication(port);
            return true;
        } catch (InterruptedException e) {
            return false;
        }

    }

}
