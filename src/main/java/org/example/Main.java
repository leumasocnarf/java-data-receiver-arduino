package org.example;

import static org.example.ArduinoConnectionMode.EMULATION;
import static org.example.ArduinoConnectionMode.REAL;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArduinoDataReceiver receiver = new ArduinoDataReceiver.Builder()
                .useConnectionMode(
                        EMULATION.usingSerialPort("COM3")
                                .usingBaudRate(9600))
                .withSensorReader(new TemperatureSensorReader())
                .withSensorReader(new HumiditySensorReader())
                .withSensorReader(new LightSensorReader())
                .build();

        receiver.connect();

        while (receiver.isConnected()) {
            receiver.fetchData();
        }
    }
}