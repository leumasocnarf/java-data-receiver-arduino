package org.example;

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
        public ArduinoConnectionMode usingSerialPort(String serialPort) {
            this.serialPort = serialPort;
            return this;
        }

        @Override
        public ArduinoConnectionMode usingBaudRate(int baudRate) {
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
        public ArduinoConnectionMode usingSerialPort(String serialPort) {
            this.serialPort = serialPort;
            return this;
        }

        @Override
        public ArduinoConnectionMode usingBaudRate(int baudRate) {
            this.baudRate = baudRate;
            return this;
        }
    };

    public abstract Arduino useArduino();
    public abstract ArduinoConnectionMode usingSerialPort(String serialPort);
    public abstract ArduinoConnectionMode usingBaudRate(int baudRate);
}