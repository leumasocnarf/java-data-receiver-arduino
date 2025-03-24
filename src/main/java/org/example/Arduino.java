package org.example;

import java.io.InputStream;

/**
 * Interface que representa uma abstração das ações de um Arduino.
 */
public interface Arduino {

    InputStream sendDataStream() throws InterruptedException;
    void closePort();
}
