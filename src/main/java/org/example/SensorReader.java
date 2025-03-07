package org.example;

public interface SensorReader {
    void formatData(String data);
}

class TemperatureSensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float temperature = Float.parseFloat(data);

        System.out.printf(temperature > 30 ?
                "🌡 Temperatura : %.1f °C 🔴 %n" :
                "🌡 Temperatura : %.1f °C 🟢 %n", temperature);
    }
}

class HumiditySensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float humidity = Float.parseFloat(data);

        System.out.printf(humidity < 40.0 ?
                "💧 Humidade : %.1f %% 🔴 %n" :
                "💧 Humidade : %.1f %% 🟢 %n", humidity);
    }
}

class LightSensorReader implements SensorReader {

    @Override
    public void formatData(String data) {
        float light = Float.parseFloat(data);

        System.out.printf(light > 800 ?
                "🔆 Luz : %.0f 🔴 %n" :
                "🔆 Luz : %.0f 🟢 %n", light);
    }
}
