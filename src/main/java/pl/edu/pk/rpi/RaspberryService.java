package pl.edu.pk.rpi;

import com.pi4j.wiringpi.Serial;

public class RaspberryService {
    private static final String ENTER = "\r";

    public static int openCommunication(){


        int serialPort = Serial.serialOpen("/dev/ttyAMA0", 9600);
        if (serialPort == -1) {
            System.out.println("Serial Port Failed");
            return 0;
        } else {
            Serial.serialPuts(serialPort, "AT+CMGF=1" + ENTER);
            System.out.println("Serial port ok");
            return serialPort;
        }
    }

    public static boolean sendSMS(int serialPort, String number, String message) throws InterruptedException {
        Serial.serialPuts(serialPort, "AT+CMGS=\"+48"+ number + "\"" + ENTER);
        Serial.serialPuts(serialPort, message);
        Thread.sleep(3000);
        Serial.serialPuts(serialPort, "\u001A" + ENTER);

        return true;
    }

    public static void closeCommunication(int serialPort) throws InterruptedException {
        Thread.sleep(1000);
        Serial.serialClose(serialPort);
    }



}
