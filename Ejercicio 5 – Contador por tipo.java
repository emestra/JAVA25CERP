public class Ejercicio5 {
    static class Animal {}
    static class Perro extends Animal {}
    static class Gato extends Animal {}

    public static void main(String[] args) {
        Animal[] animales = { new Perro(), new Gato(), new Perro(), new Perro(), new Gato() };
        int perros = 0, gatos = 0;
        for (Animal a : animales) {
            if (a instanceof Perro) perros++;
            else if (a instanceof Gato) gatos++;
        }
        System.out.println("Perros: " + perros + ", Gatos: " + gatos);
    }
}
