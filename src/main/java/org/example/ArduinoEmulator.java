package org.example;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

/**
 * Classe que representa a instância de um Arduino Emulado.
 */
public class ArduinoEmulator implements Arduino {

    public ArduinoEmulator(String port, int baudRate) {
        System.out.printf("Simulando conexão através da serial port %s com baud rate %d.%n", port, baudRate);
    }

    /**
     * Emula o envio de dados de um Arduino.
     * @return Um novo stream de dados a cada 2000 millis.
     * @throws InterruptedException Thread.sleep();
     */
    @Override
    public InputStream sendDataStream() throws InterruptedException {
        Random random = new Random();

        while (true) {
            float temperature = 10 + random.nextFloat() * 25;  // Randomiza Temperatura entre 10 e 35 °C
            float humidity = 20 + random.nextFloat() * 60;  // Randomiza umidade entre 20 e 80%
            int light = 200 + random.nextInt(800);  // Randomiza Luz entre 200 e 1000

            String data = temperature + " " + humidity + " " + light;

            Thread.sleep(2000);

            return new ByteArrayInputStream(data.getBytes());
        }
    }

    @Override
    public void closePort() {
        System.out.println("Terminando simulação...");
    }
}