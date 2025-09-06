import java.util.Random;

public class Ejercicio9 {
    static abstract class InstrumentoMusical {
        public abstract void tocarNota(String nota);
    }

    static class Guitarra extends InstrumentoMusical {
        @Override
        public void tocarNota(String nota) {
            System.out.println("Guitarra toca: " + nota);
        }
    }

    static class Piano extends InstrumentoMusical {
        @Override
        public void tocarNota(String nota) {
            System.out.println("Piano toca: " + nota);
        }
    }

    public static void main(String[] args) {
        InstrumentoMusical[] banda = { new Guitarra(), new Piano() };
        String[] notas = { "Do", "Re", "Mi", "Fa", "Sol" };
        Random rnd = new Random();

        for (InstrumentoMusical inst : banda) {
            for (int i = 0; i < 3; i++) {
                inst.tocarNota(notas[rnd.nextInt(notas.length)]);
            }
        }
    }
}
