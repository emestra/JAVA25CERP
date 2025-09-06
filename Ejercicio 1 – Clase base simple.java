public class Ejercicio1 {
    static class Animal {
        String nombre;
        public Animal(String nombre) { this.nombre = nombre; }
        public void hacerSonido() { System.out.println("Sonido genérico"); }
    }

    public static void main(String[] args) {
        Animal a = new Animal("Criatura");
        a.hacerSonido();
    }
}