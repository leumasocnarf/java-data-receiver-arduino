package org.example;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;

public class ArduinoReal implements Arduino {
    private final String serialPortName;
    private final SerialPort serialPort;

    public ArduinoReal(String serialPortName, int baudRate) {
        this.serialPort = SerialPort.getCommPort(serialPortName);
        this.serialPortName = serialPortName;
        this.serialPort.setBaudRate(baudRate);

        if (serialPort.openPort()) {
            System.out.printf("Conexão estabelecida através da serial port %s com baud rate %d.%n", serialPortName, baudRate);
        } else {
            System.out.println("Não foi possível estabelecer uma conexão.");
        }

    }

    @Override
    public InputStream sendDataStream() {
        return this.serialPort.getInputStream();
    }

    @Override
    public void closePort() {
        System.out.printf("Terminando conexão com %s%n...", this.serialPortName);
        this.serialPort.closePort();
    }
}
