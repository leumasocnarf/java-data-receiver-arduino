package org.example;

/**
 * Interface que abstrai a formatação de dados de um Sensor no Arduino.
 */
public interface SensorReader {
    void formatData(String data);
}

/**
 * Classe para formatação de dados de um sensor de temperatura.
 */
class TemperatureSensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float temperature = Float.parseFloat(data);

        System.out.printf(temperature > 30 ?
                "🌡 Temperatura : %.1f °C 🔴 %n" :
                "🌡 Temperatura : %.1f °C 🟢 %n", temperature);
    }
}

/**
 * Classe para formatação de dados de um sensor de umidade.
 */
class HumiditySensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float humidity = Float.parseFloat(data);

        System.out.printf(humidity < 40.0 ?
                "💧 Umidade : %.1f %% 🔴 %n" :
                "💧 Umidade : %.1f %% 🟢 %n", humidity);
    }
}

/**
 * Classe para formatação de dados de um sensor de luz.
 */
class LightSensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float light = Float.parseFloat(data);

        System.out.printf(light > 800 ?
                "🔆 Luz : %.0f 🔴 %n" :
                "🔆 Luz : %.0f 🟢 %n", light);
    }
}
