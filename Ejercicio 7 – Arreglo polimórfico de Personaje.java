public class Ejercicio7 {
    static abstract class Personaje {
        String nombre;
        int nivel;
        public Personaje(String nombre, int nivel) {
            this.nombre = nombre; this.nivel = nivel;
        }
        public abstract void accionEspecial();
    }

    static class Mago extends Personaje {
        public Mago(String nombre, int nivel) { super(nombre, nivel); }
        @Override
        public void accionEspecial() {
            System.out.println(nombre + " lanza un hechizo de fuego!");
        }
    }

    static class Guerrero extends Personaje {
        public Guerrero(String nombre, int nivel) { super(nombre, nivel); }
        @Override
        public void accionEspecial() {
            System.out.println(nombre + " realiza un ataque con espada!");
        }
    }

    public static void main(String[] args) {
        Personaje[] equipo = {
            new Mago("Merlín", 10),
            new Guerrero("Conan", 8),
            new Mago("Gandalf", 12),
            new Guerrero("Xena", 9)
        };
        for (Personaje p : equipo) p.accionEspecial();
    }
}
