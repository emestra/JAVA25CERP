public class Ejercicio8 {
    static abstract class InstrumentoMusical {
        public abstract void tocarNota(String nota);
    }

    static class Guitarra extends InstrumentoMusical {
        @Override
        public void tocarNota(String nota) {
            System.out.println("Guitarra toca: " + nota + " con cuerdas");
        }
    }

    static class Piano extends InstrumentoMusical {
        @Override
        public void tocarNota(String nota) {
            System.out.println("Piano toca: " + nota + " con teclas");
        }
    }

    public static void main(String[] args) {
        InstrumentoMusical g = new Guitarra();
        InstrumentoMusical p = new Piano();
        g.tocarNota("Do");
        p.tocarNota("Sol");
    }
}
