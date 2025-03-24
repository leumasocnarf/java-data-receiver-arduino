package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Classe que representa um receptor de dados de um Arduino.
 */
public class ArduinoDataReceiver {
    private Arduino arduino;
    private final List<SensorReader> sensors = new ArrayList<>();
    private ArduinoConnectionMode connectionMode;
    private boolean isConnected;

    /**
     * Define um ArduinoConnectionMode.
     * @param connectionMode de ArduinoConnectionMode.
     * @return this ArduinoDataReceiver.
     */
    public ArduinoDataReceiver usingConnectionMode(ArduinoConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
        return this;
    }

    /**
     * Adiciona um SensorReader ao sensors ArrayList.
     * @param sensor de SensorReader.
     * @return this ArduinoDataReceiver.
     */
    public ArduinoDataReceiver withSensorReader(SensorReader sensor) {
        this.sensors.add(sensor);
        return this;
    }

    public void connect() {
        isConnected = true;

        switch (connectionMode) {
            case EMULATION, REAL -> this.arduino = connectionMode.useArduino();

            default -> throw new IllegalStateException("Valor inesperado: " + connectionMode);
        }
    }

    public void disconnect() {
        isConnected = false;

        switch (connectionMode) {
            case EMULATION, REAL -> this.arduino.closePort();

            default -> throw new IllegalStateException("Valor inesperado: " + connectionMode);
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Recebe dados da conexão com o Arduino EMULATION ou REAL.
     * @throws InterruptedException do InputStream de sendDataStream();
     */
    public void fetchData() throws InterruptedException {
        if (isConnected) {

            switch (connectionMode) {
                case EMULATION, REAL -> {

                    try (Scanner emulatedScanner = new Scanner(this.arduino.sendDataStream())) {
                        processData(emulatedScanner);
                    }
                }
                default -> throw new IllegalStateException("Valor inesperado: " + connectionMode);
            }

        } else {
            System.out.println("Não há conexão para receber dados.");
        }
    }

    /**
     * Processa e formata os dados recebidos.
     * @param scanner de Scanner para ler dados do Arduino.
     */
    private void processData(Scanner scanner) {

        System.out.println("Em: " + LocalDateTime.now()
                .format(DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM)
                ));

        while (scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(" ");

            IntStream.range(0, sensors.size())
                    .forEach(i -> sensors.get(i).formatData(values[i]));
        }

        System.out.println("----------------------");
    }
}
