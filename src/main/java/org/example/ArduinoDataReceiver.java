package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.IntStream;

public class ArduinoDataReceiver {
    private Arduino arduino;
    private final List<SensorReader> sensors;
    private final ArduinoConnectionMode connectionMode;
    private boolean isConnected;

    private ArduinoDataReceiver(Builder builder) {
        this.sensors = builder.sensors;
        this.connectionMode = builder.connectionMode;
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

    public static class Builder {
        private ArduinoConnectionMode connectionMode;
        private final List<SensorReader> sensors = new ArrayList<>();

        public Builder useConnectionMode(ArduinoConnectionMode connectionMode) {
            this.connectionMode = connectionMode;
            return this;
        }

        public Builder withSensorReader(SensorReader sensor) {
            this.sensors.add(sensor);
            return this;
        }

        public ArduinoDataReceiver build() {
            return new ArduinoDataReceiver(this);
        }
    }
}
