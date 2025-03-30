package org.example;

import java.io.InputStream;

/**
 * Interface que representa uma abstração das ações de um Arduino.
 */
public interface Arduino {

    /**
     * Recebe dados de um Arduino.
     * @return InputStream
     * @throws InterruptedException InputStream
     */
    InputStream sendDataStream() throws InterruptedException;

    /**
     * Fecha a porta de conexão com o Arduino.
     */
    void closePort();
}
