public class Ejercicio11 {
    static abstract class PlataformaStreaming {
        public abstract void reproducirContenido(String contenido);
    }

    static class Netflix extends PlataformaStreaming {
        @Override
        public void reproducirContenido(String contenido) {
            System.out.println("Netflix reproduce: " + contenido + " en HD");
        }
    }

    static class YouTube extends PlataformaStreaming {
        @Override
        public void reproducirContenido(String contenido) {
            System.out.println("YouTube reproduce: " + contenido + " con anuncios");
        }
    }

    public static void main(String[] args) {
        PlataformaStreaming n = new Netflix();
        PlataformaStreaming y = new YouTube();
        n.reproducirContenido("Serie de ciencia ficción");
        y.reproducirContenido("Tutorial de Java");
    }
}
