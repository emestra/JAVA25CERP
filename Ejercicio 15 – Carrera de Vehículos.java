public class Ejercicio15 {
    static abstract class Vehiculo {
        String nombre;
        int distancia;
        public Vehiculo(String nombre) { this.nombre = nombre; }
        public abstract void avanzar();
    }

    static class Auto extends Vehiculo {
        public Auto(String nombre) { super(nombre); }
        @Override
        public void avanzar() {
            distancia += (int)(Math.random() * 10 + 1);
        }
    }

    static class Moto extends Vehiculo {
        public Moto(String nombre) { super(nombre); }
        @Override
        public void avanzar() {
            distancia += (int)(Math.random() * 12 + 1);
        }
    }

    public static void main(String[] args) {
        Vehiculo[] competidores = { new Auto("Ford"), new Moto("Yamaha") };
        for (int ronda = 1; ronda <= 5; ronda++) {
            for (Vehiculo v : competidores) v.avanzar();
        }
        Vehiculo ganador = competidores[0];
        for (Vehiculo v : competidores) {
            if (v.distancia > ganador.distancia) ganador = v;
        }
        System.out.println("Ganador: " + ganador.nombre + " con " + ganador.distancia + " metros");
    }
}
