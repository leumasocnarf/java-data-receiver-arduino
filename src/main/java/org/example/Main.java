package org.example;

import static org.example.ArduinoConnectionMode.EMULATION;

/**
 * Para uma conexão real com Arduino basta utilizar:
 * @import static org.example.ArduinoConnectionMode.REAL;
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ArduinoDataReceiver receiver = new ArduinoDataReceiver()
                .usingConnectionMode(EMULATION.withSerialPort("COM3").withBaudRate(9600))
                .withSensorReader(new TemperatureSensorReader())
                .withSensorReader(new HumiditySensorReader())
                .withSensorReader(new LightSensorReader());

        receiver.connect();

        while (receiver.isConnected()) {
            receiver.fetchData();
        }
    }
}