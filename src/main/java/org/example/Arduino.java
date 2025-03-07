package org.example;

import java.io.InputStream;

public interface Arduino {

    InputStream sendDataStream() throws InterruptedException;
    void closePort();
}
