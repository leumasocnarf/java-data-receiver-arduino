package org.example;

/**
 * Classe enum que representa modos de conexão com configuração de SerialPort e BaudRate
 */
public enum ArduinoConnectionMode {
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

    public abstract Arduino useArduino();
    public abstract ArduinoConnectionMode withSerialPort(String serialPort);
    public abstract ArduinoConnectionMode withBaudRate(int baudRate);
}