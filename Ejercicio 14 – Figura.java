public class Ejercicio14 {
    static abstract class Figura {}
    static class Poligono extends Figura {}
    static class Estrella extends Figura {}

    public static void main(String[] args) {
        Figura[] figuras = { new Poligono(), new Estrella(), new Poligono() };
        int poligonos = 0, estrellas = 0;
        for (Figura f : figuras) {
            if (f instanceof Poligono) poligonos++;
            else if (f instanceof Estrella) estrellas++;
        }
        System.out.println("Polígonos: " + poligonos + ", Estrellas: " + estrellas);
    }
}
