package org.example;

/**
 * Enum que representa os diferentes modos de conexão com o Arduino.
 * Ela suporta dois modos:
 * <ul>
 *   <li>{@link #REAL} - Conecta com um Arduino real via {@link String serialPort}.</li>
 *   <li>{@link #EMULATION} - Usa um emulador no lugar de um Arduino físico.</li>
 * </ul>
 * Cada modo permite a definição de um {@link String serialPort} e um {@link Integer baudRate}.
 */
public enum ArduinoConnectionMode {
    /**
     * Real connection mode, usado para conectar a um Arduino físico via serialPort.
     */
    REAL {
        private String serialPort;
        private int baudRate;

        @Override
        public Arduino useArduino() {
            System.out.printf("Conexão modo: %s!%n", this);
            return new ArduinoReal(this.serialPort, this.baudRate);
        }

        @Override
        public ArduinoConnectionMode withSerialPort(String serialPort) {
            this.serialPort = serialPort;
            return this;
        }

        @Override
        public ArduinoConnectionMode withBaudRate(int baudRate) {
            this.baudRate = baudRate;
            return this;
        }
    },
    /**
     * Emulation mode, usado para simular uma conexão com o Arduino.
     */
    EMULATION {
        private String serialPort;
        private int baudRate;

        @Override
        public Arduino useArduino() {
            System.out.printf("Conexão modo: %s!%n", this);
            return new ArduinoEmulator(this.serialPort, this.baudRate);
        }

        @Override
        public ArduinoConnectionMode withSerialPort(String serialPort) {
            this.serialPort = serialPort;
            return this;
        }

        @Override
        public ArduinoConnectionMode withBaudRate(int baudRate) {
            this.baudRate = baudRate;
            return this;
        }
    };

    /**
     * Cria uma instância do Arduino dependendo do modo de conexão selecionado.
     * @return an {@link Arduino} instance.
     */
    public abstract Arduino useArduino();

    /**
     * Define a serialPort para a conexão.
     * @param serialPort o número da serialPort.
     * @return o atual {@link ArduinoConnectionMode}
     */
    public abstract ArduinoConnectionMode withSerialPort(String serialPort);

    /**
     * Define a baudRate para a conexão.
     * @param baudRate the baud rate to use.
     * @return o atual {@link ArduinoConnectionMode}
     */
    public abstract ArduinoConnectionMode withBaudRate(int baudRate);
}