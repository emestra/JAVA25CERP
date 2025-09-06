public class Ejercicio3 {
    static class Animal {
        String nombre;
        public Animal(String nombre) { this.nombre = nombre; }
        public void hacerSonido() { System.out.println("Sonido genérico"); }
    }

    static class Gato extends Animal {
        public Gato(String nombre) { super(nombre); }
        @Override
        public void hacerSonido() { System.out.println(nombre + " dice: Miau!"); }
    }

    public static void main(String[] args) {
        Gato g = new Gato("Michi");
        g.hacerSonido();
    }
}
