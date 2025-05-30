package org.example;

import static org.example.ArduinoConnectionMode.EMULATION;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArduinoDataReceiver receiver = new ArduinoDataReceiver()
                .usingConnectionMode(EMULATION)
                .withSensorsReaders(
                        new TemperatureSensorReader(),
                        new HumiditySensorReader(),
                        new LightSensorReader()
                );

        receiver.connect("COM3", 9600);

        while (receiver.isConnected()) {
            receiver.fetchData();
        }
    }
}