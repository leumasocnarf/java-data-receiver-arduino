package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Classe responsável por receber dados de um Arduino.
 * Ela tem as seguintes propriedades:
 * <ul>
 *     <li>{@link #arduino} - Instância de {@link Arduino} usada para a comunicação.</li>
 *     <li>{@link #sensors} - Uma lista de {@link SensorReader} referentes aos sensores conectados no Arduino.</li>
 *     <li>{@link #connectionMode} - Modo de conexão {@link ArduinoConnectionMode REAL} ou {@link ArduinoConnectionMode EMULATION} usado para comunicar com o Arduino.</li>
 *     <li>{@link #isConnected} - Indica se o receptor está conectado ou não com o Arduino.</li>
 * </ul>
 */
public class ArduinoDataReceiver {
    private Arduino arduino;
    private final List<SensorReader> sensors = new ArrayList<>();
    private ArduinoConnectionMode connectionMode;
    private boolean isConnected;

    /**
     * Define um ArduinoConnectionMode.
     * @param connectionMode ArduinoConnectionMode.
     * @return this ArduinoDataReceiver.
     */
    public ArduinoDataReceiver usingConnectionMode(ArduinoConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
        return this;
    }

    /**
     * Adiciona um SensorReader ao sensors ArrayList.
     * @param sensor SensorReader.
     * @return this ArduinoDataReceiver.
     */
    public ArduinoDataReceiver withSensorReader(SensorReader sensor) {
        this.sensors.add(sensor);
        return this;
    }

    /**
     * Estabelece conexão com um Arduino através dos parâmetros definidos.
     * @param serialPort porta de conexão com o Arduino.
     * @param baudRate o baud rate usado pelo Arduino.
     */
    public void connect(String serialPort, int baudRate) {
        isConnected = true;

        switch (connectionMode) {
            case EMULATION, REAL -> this.arduino = connectionMode.useArduino(serialPort, baudRate);

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
     * A partir de {@link ArduinoConnectionMode} recebe dados de um {@link ArduinoReal} ou {@link ArduinoEmulator}.
     * @throws InterruptedException InputStream de sendDataStream();
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
     * @param scanner Scanner para ler dados do Arduino.
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
