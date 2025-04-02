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
    private float minTemperature = Float.MAX_VALUE;
    private float maxTemperature = Float.MIN_VALUE;


    @Override
    public void formatData(String data) {
        float temperature = Float.parseFloat(data);

        if (temperature < minTemperature) minTemperature = temperature;
        if (temperature > maxTemperature) maxTemperature = temperature;

        System.out.printf(temperature > 30 ?
                "🌡 Temperatura - Atual: %.1f °C 🔴| " :
                "🌡 Temperatura - Atual: %.1f °C 🟢| ", temperature);

        System.out.printf("📉 Mínima registrada: %.1f °C | 📈 Máxima registrada: %.1f °C%n", minTemperature, maxTemperature);
    }
}

/**
 * Classe para formatação de dados de um sensor de umidade.
 */
class HumiditySensorReader implements SensorReader {
    private float minHumidity = Float.MAX_VALUE;
    private float maxHumidity = Float.MIN_VALUE;


    @Override
    public void formatData(String data) {
        float humidity = Float.parseFloat(data);

        if (humidity < minHumidity) minHumidity = humidity;
        if (humidity > maxHumidity) maxHumidity = humidity;

        System.out.printf(humidity < 40.0 ?
                "💧 Umidade     - Atual: %.1f %%  🔴| " :
                "💧 Umidade     - Atual: %.1f %%  🟢| ", humidity);

        System.out.printf("📉 Mínima registrada: %.1f %%  | 📈 Máxima registrada: %.1f %%%n", minHumidity, maxHumidity);
    }
}

/**
 * Classe para formatação de dados de um sensor de luz.
 */
class LightSensorReader implements SensorReader {
    private float minLight = Float.MAX_VALUE;
    private float maxLight = Float.MIN_VALUE;

    @Override
    public void formatData(String data) {
        float light = Float.parseFloat(data);

        if (light < minLight) minLight = light;
        if (light > maxLight) maxLight = light;

        System.out.printf(light > 800 ?
                "🔆 Luz         - Atual: %.0f     🔴| " :
                "🔆 Luz         - Atual: %.0f     🟢| ", light);

        System.out.printf("📉 Mínima registrada: %.0f     | 📈 Máxima registrada: %.0f %n", minLight, maxLight);
    }
}
