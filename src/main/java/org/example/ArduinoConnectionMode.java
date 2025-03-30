package org.example;

/**
 * Enum que representa os diferentes modos de conexão com o Arduino.
 * Ela suporta dois modos:
 * <ul>
 *   <li>{@link #REAL} - Conecta com um Arduino real.</li>
 *   <li>{@link #EMULATION} - Usa um emulador em código no lugar de um Arduino físico.</li>
 * </ul>
 */
public enum ArduinoConnectionMode {
    /**
     * Real connection mode: usado para conectar a um Arduino físico.
     */
    REAL {
        @Override
        public Arduino useArduino(String serialPort, int baudRate) {
            System.out.printf("Conexão modo: %s!%n", this);
            return new ArduinoReal(serialPort, baudRate);
        }

    },
    /**
     * Emulation mode: usado para emular uma conexão com o Arduino.
     */
    EMULATION {
        @Override
        public Arduino useArduino(String serialPort, int baudRate) {
            System.out.printf("Conexão modo: %s!%n", this);
            return new ArduinoEmulator(serialPort, baudRate);
        }
    };

    /**
     * Cria uma instância do Arduino dependendo do modo de conexão selecionado.
     * @param serialPort porta de conexão com o Arduino.
     * @param baudRate o baud rate usado pelo Arduino.
     * @return an {@link Arduino} instance.
     */
    public abstract Arduino useArduino(String serialPort, int baudRate);
}