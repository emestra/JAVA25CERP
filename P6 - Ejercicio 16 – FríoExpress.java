import java.util.*;

// ==== Excepciones personalizadas ====
class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String msg) { super(msg); }
}

class CadenaFrioRotaException extends Exception {
    public CadenaFrioRotaException(String msg) { super(msg); }
}

class LimiteCreditoExcedidoException extends Exception {
    public LimiteCreditoExcedidoException(String msg) { super(msg); }
}

class ProductoNoCongeladoException extends Exception {
    public ProductoNoCongeladoException(String msg) { super(msg); }
}

class StockMinimoAlcanzadoException extends Exception {
    public StockMinimoAlcanzadoException(String msg) { super(msg); }
}

// ==== Clases de dominio ====
class Producto {
    String codigo;
    String nombre;
    String categoria;
    double temperaturaRequerida;
    double stockKg;
    double stockMinimo;
    double precioKg;

    public Producto(String codigo, String nombre, String categoria,
                    double temperaturaRequerida, double stockKg,
                    double stockMinimo, double precioKg) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.temperaturaRequerida = temperaturaRequerida;
        this.stockKg = stockKg;
        this.stockMinimo = stockMinimo;
        this.precioKg = precioKg;
    }

    public void reducirStock(double cantidad) throws StockInsuficienteException, StockMinimoAlcanzadoException {
        if (cantidad > stockKg) throw new StockInsuficienteException("Stock insuficiente para " + nombre);
        stockKg -= cantidad;
        if (stockKg <= stockMinimo) throw new StockMinimoAlcanzadoException("Stock mínimo alcanzado para " + nombre);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (" + categoria + ") Stock: " + stockKg + "kg Precio: $" + precioKg;
    }
}

class Cliente {
    String rut;
    String razonSocial;
    String direccion;
    double limiteCredito;
    double deudaActual;

    public Cliente(String rut, String razonSocial, String direccion, double limiteCredito) {
        this.rut = rut;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.limiteCredito = limiteCredito;
        this.deudaActual = 0;
    }

    public void agregarDeuda(double monto) throws LimiteCreditoExcedidoException {
        if (deudaActual + monto > limiteCredito)
            throw new LimiteCreditoExcedidoException("Límite de crédito excedido para " + razonSocial);
        deudaActual += monto;
    }

    @Override
    public String toString() {
        return razonSocial + " (RUT: " + rut + ") Deuda: $" + deudaActual;
    }
}

class Pedido {
    Cliente cliente;
    Map<Producto, Double> items = new HashMap<>();
    boolean despachado = false;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto p, double cantidad) {
        items.put(p, cantidad);
    }

    @Override
    public String toString() {
        return "Pedido de " + cliente.razonSocial + " - " + (despachado ? "Despachado" : "Pendiente");
    }
}

// ==== Sistema de gestión ====
class FrioExpress {
    TreeMap<String, Producto> inventario = new TreeMap<>();
    ArrayList<Pedido> pedidosPendientes = new ArrayList<>();
    ArrayList<Producto> cadenaFrioRota = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public void registrarProducto(Producto p) {
        inventario.put(p.codigo, p);
    }

    public void registrarCliente(Cliente c) {
        clientes.add(c);
    }

    public Cliente buscarClientePorRut(String rut) {
        for (Cliente c : clientes) {
            if (c.rut.equals(rut)) return c;
        }
        return null;
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        return inventario.get(codigo);
    }

    public void registrarIngreso(String codigo, double cantidad) throws ProductoNoCongeladoException {
        Producto p = inventario.get(codigo);
        if (p == null) throw new ProductoNoCongeladoException("Producto no encontrado: " + codigo);
        p.stockKg += cantidad;
    }

    public void registrarCadenaFrioRota(String codigo) throws ProductoNoCongeladoException {
        Producto p = inventario.get(codigo);
        if (p == null) throw new ProductoNoCongeladoException("Producto no encontrado: " + codigo);
        cadenaFrioRota.add(p);
    }

    public void procesarPedido(Pedido pedido) throws Exception {
        double total = 0;
        for (Map.Entry<Producto, Double> e : pedido.items.entrySet()) {
            Producto p = e.getKey();
            double cant = e.getValue();
            if (cant > p.stockKg) throw new StockInsuficienteException("Stock insuficiente para " + p.nombre);
            total += cant * p.precioKg;
        }
        pedido.cliente.agregarDeuda(total);
        for (Map.Entry<Producto, Double> e : pedido.items.entrySet()) {
            e.getKey().reducirStock(e.getValue());
        }
        pedidosPendientes.add(pedido);
    }

    public void listarBajoStock() {
        System.out.println("=== Productos bajo stock mínimo ===");
        for (Producto p : inventario.values()) {
            if (p.stockKg <= p.stockMinimo) {
                System.out.println(p);
            }
        }
    }

    public void listarPedidosPendientes() {
        System.out.println("=== Pedidos pendientes ===");
        for (Pedido ped : pedidosPendientes) {
            if (!ped.despachado) System.out.println(ped);
        }
    }
}

// ==== Main interactivo ====
public class Ejercicio16Interactivo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FrioExpress sistema = new FrioExpress();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú FríoExpress ===");
            System.out.println("1. Registrar producto");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Ingresar mercadería");
            System.out.println("4. Registrar cadena de frío rota");
            System.out.println("5. Crear pedido");
            System.out.println("6. Listar productos bajo stock mínimo");
            System.out.println("7. Listar pedidos pendientes");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            try {
                switch (op) {
                    case 1:
                        System.out.print("Código: ");
                        String cod = sc.nextLine();
                        System.out.print("Nombre: ");
                        String nom = sc.nextLine();
                        System.out.print("Categoría: ");
                        String cat = sc.nextLine();
                        System.out.print("Temp. requerida: ");
                        double temp = sc.nextDouble();
                        System.out.print("Stock inicial (kg): ");
                        double stock = sc.nextDouble();
                        System.out.print("Stock mínimo: ");
                        double stockMin = sc.nextDouble();
                        System.out.print("Precio por kg: ");
                        double precio = sc.nextDouble();
                        sistema.registrarProducto(new Producto(cod, nom, cat, temp, stock, stockMin, precio));
                        break;
                    case 2:
                        System.out.print("RUT: ");
                        String rut = sc.nextLine();
                        System.out.print("Razón social: ");
                        String rs = sc.nextLine();
                        System.out.print("Dirección: ");
                        String dir = sc.nextLine();
                        System.out.print("Límite de crédito: ");
                        double lim = sc.nextDouble();
                        sistema.registrarCliente(new Cliente(rut, rs, dir, lim));
                        break;
                    case 3:
                        System.out.print("Código producto: ");
                        String codIng = sc.nextLine();
                        System.out.print("Cantidad (kg): ");
                        double cantIng = sc.nextDouble();
                        sistema.registrarIngreso(codIng, cantIng);
                        break;
                    case 4:
                        System.out.print("Código producto: ");
                        String codFrio = sc.nextLine();
                        sistema.registrarCadenaFrioRota(codFrio);
                        break;
                    case 5:
                        System.out.print("RUT cliente: ");
                        String rutPed = sc.nextLine();
                        Cliente cli = sistema.buscarClientePorRut(rutPed);
                        if (cli == null) {
                            System.out.println("Cliente no encontrado.");
                            break;
                        }
                        Pedido ped = new Pedido(cli);
                        String seguir;
                        do {
                            System.out.print("Código producto: ");
                            String codProd = sc.nextLine();
                            Producto prod = sistema.buscarProductoPorCodigo(codProd);
                            if (prod == null) {
                                System.out.println("Producto no encontrado.");
                            } else {
                                System.out.print("Cantidad (kg): ");
                                double cant = sc.nextDouble();
                                sc.nextLine();
                                ped.agregarProducto
								                                ped.agregarProducto(prod, cant);
                            }
                            System.out.print("¿Agregar otro producto al pedido? (s/n): ");
                            seguir = sc.nextLine();
                        } while (seguir.equalsIgnoreCase("s"));

                        try {
                            sistema.procesarPedido(ped);
                            System.out.println("Pedido registrado con éxito.");
                        } catch (Exception e) {
                            System.out.println("Error al procesar el pedido: " + e.getMessage());
                        }
                        break;
                    case 6:
                        sistema.listarBajoStock();
                        break;
                    case 7:
                        sistema.listarPedidosPendientes();
                        break;
                    case 0:
                        salir = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // limpiar buffer
            }
        }
        sc.close();
    }
}
