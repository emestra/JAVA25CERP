public class Ejercicio2 {
    static class Animal {
        String nombre;
        public Animal(String nombre) { this.nombre = nombre; }
        public void hacerSonido() { System.out.println("Sonido genérico"); }
    }

    static class Perro extends Animal {
        public Perro(String nombre) { super(nombre); }
        @Override
        public void hacerSonido() { System.out.println(nombre + " dice: Guau!"); }
    }

    public static void main(String[] args) {
        Perro p = new Perro("Firulais");
        p.hacerSonido();
    }
}
