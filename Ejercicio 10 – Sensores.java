public class Ejercicio10 {
    static class Sensor {
        double valor;
        public double medir() { return valor; }
    }

    static class SensorTemperatura extends Sensor {
        @Override
        public double medir() {
            valor = 15 + Math.random() * 10;
            return valor;
        }
    }

    static class SensorPresion extends Sensor {
        @Override
        public double medir() {
            valor = 900 + Math.random() * 200;
            return valor;
        }
    }

    public static void main(String[] args) {
        Sensor t = new SensorTemperatura();
        Sensor p = new SensorPresion();
        System.out.println("Temperatura: " + t.medir() + " °C");
        System.out.println("Presión: " + p.medir() + " hPa");
    }
}
