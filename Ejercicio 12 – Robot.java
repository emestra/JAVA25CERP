public class Ejercicio12 {
    static abstract class Robot {
        public abstract void realizarTarea();
    }

    static class RobotLimpieza extends Robot {
        @Override
        public void realizarTarea() {
            System.out.println("Robot de limpieza aspira el piso");
        }
    }

    static class RobotCocina extends Robot {
        @Override
        public void realizarTarea() {
            System.out.println("Robot de cocina prepara una receta");
        }
    }

    public static void main(String[] args) {
        Robot r1 = new RobotLimpieza();
        Robot r2 = new RobotCocina();
        r1.realizarTarea();
        r2.realizarTarea();
    }
}
