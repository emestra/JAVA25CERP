public class Ejercicio13 {
    static abstract class VehiculoAereo {
        public abstract void despegar();
    }

    static class Avion extends VehiculoAereo {
        @Override
        public void despegar() {
            System.out.println("Avión acelera en pista y despega");
        }
    }

    static class Helicoptero extends VehiculoAereo {
        @Override
        public void despegar() {
            System.out.println("Helicóptero eleva vuelo verticalmente");
        }
    }

    public static void main(String[] args) {
        VehiculoAereo v1 = new Avion();
        VehiculoAereo v2 = new Helicoptero();
        v1.despegar();
        v2.despegar();
    }
}
