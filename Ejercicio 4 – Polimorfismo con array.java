public class Ejercicio4 {
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

    static class Gato extends Animal {
        public Gato(String nombre) { super(nombre); }
        @Override
        public void hacerSonido() { System.out.println(nombre + " dice: Miau!"); }
    }

    public static void main(String[] args) {
        Animal[] animales = { new Perro("Firulais"), new Gato("Michi") };
        for (Animal a : animales) a.hacerSonido();
    }
}
