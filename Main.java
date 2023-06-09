import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ElementoPedido {
    private int id;
    private String nombre;
    private double precio;

    public ElementoPedido(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

class Hamburguesa extends ElementoPedido {
    public Hamburguesa(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }
}

class Bebida extends ElementoPedido {
    public Bebida(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }
}

class Postre extends ElementoPedido {
    public Postre(int id, String nombre, double precio) {
        super(id, nombre, precio);
    }
}

class Cliente {
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String apellidos, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }
}

class Hamburgueseria {
    private List<ElementoPedido> elementos;
    private List<Cliente> clientes;

    public Hamburgueseria() {
        elementos = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public void agregarElemento(ElementoPedido elemento) {
        elementos.add(elemento);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public int getNumeroElementos() {
        return elementos.size();
    }

    public ElementoPedido getElemento(int indice) {
        if (indice >= 0 && indice < elementos.size()) {
            return elementos.get(indice);
        }
        return null;
    }

    public void mostrarMenu() {
        System.out.println("----- Menú -----");
        System.out.println("Hamburguesas:");
        for (ElementoPedido elemento : elementos) {
            if (elemento instanceof Hamburguesa) {
                System.out.println(elemento.getId() + ". " + elemento.getNombre() + " ($" + elemento.getPrecio() + ")");
            }
        }
        System.out.println("Bebidas:");
        for (ElementoPedido elemento : elementos) {
            if (elemento instanceof Bebida) {
                System.out.println(elemento.getId() + ". " + elemento.getNombre() + " ($" + elemento.getPrecio() + ")");
            }
        }
        System.out.println("Postre:");
        for (ElementoPedido elemento : elementos) {
            if (elemento instanceof Postre) {
                System.out.println(elemento.getId() + ". " + elemento.getNombre() + " ($" + elemento.getPrecio() + ")");
            }
        }
    }

    public void gestionarPedidos(String password) {
        if (password.equals("admin123")) {
            System.out.println("Acceso permitido. Gestionando pedidos...");
            // Lógica para gestionar los pedidos
        } else {
            System.out.println("Contraseña incorrecta. Acceso denegado.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hamburgueseria hamburgueseria = new Hamburgueseria();

        // Agregar elementos al menú
        hamburgueseria.agregarElemento(new Hamburguesa(1, "Hamburguesa con queso", 3.0));
        hamburgueseria.agregarElemento(new Hamburguesa(2, "Hamburguesa con bacon", 4.0));
        hamburgueseria.agregarElemento(new Hamburguesa(3, "Hamburguesa con cebolla caramelizada", 5.0));
        hamburgueseria.agregarElemento(new Hamburguesa(4, "Hamburguesa sin pan", 2.0));
        hamburgueseria.agregarElemento(new Bebida(5, "Sprite", 2.0));
        hamburgueseria.agregarElemento(new Bebida(6, "Pepsi", 2.0));
        hamburgueseria.agregarElemento(new Bebida(7, "Zumo de Piña", 3.0));
        hamburgueseria.agregarElemento(new Bebida(8, "Cerveza", 4.0));
        hamburgueseria.agregarElemento(new Bebida(9, "Agua", 1.5));
        hamburgueseria.agregarElemento(new Postre(10, "Helado de chocolate", 3.0));

        // Registro de clientes
        System.out.println("----- Registro de clientes -----");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese sus apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Ingrese su dirección completa: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese su número de teléfono: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, apellidos, direccion, telefono);
        hamburgueseria.agregarCliente(cliente);

        // Mostrar menú
        System.out.println("----- Menú -----");
        hamburgueseria.mostrarMenu();

        // Selección de productos
        List<ElementoPedido> pedido = new ArrayList<>();
        boolean seguirAgregando = true;
        while (seguirAgregando) {
            System.out.print("Ingrese el ID del producto que desea agregar (0 para finalizar): ");
            int idProducto = scanner.nextInt();
            if (idProducto == 0) {
                seguirAgregando = false;
            } else {
                System.out.print("Ingrese la cantidad: ");
                int cantidad = scanner.nextInt();
                ElementoPedido producto = hamburgueseria.getElemento(idProducto - 1);
                if (producto != null) {
                    pedido.add(producto);
                    System.out.println("Producto agregado: " + producto.getNombre() + " (Cantidad: " + cantidad + ")");
                } else {
                    System.out.println("ID de producto inválido.");
                }
            }
        }

        // Resumen del pedido
        System.out.println("----- Resumen del pedido -----");
        double total = 0.0;
        for (ElementoPedido producto : pedido) {
            System.out.println(producto.getNombre() + " - $" + producto.getPrecio());
            total += producto.getPrecio();
        }
        System.out.println("Total: $" + total);

        // Confirmar pedido
        System.out.print("¿Desea confirmar el pedido? (S/N): ");
        String confirmacion = scanner.next();
        if (confirmacion.equalsIgnoreCase("S")) {
            System.out.println("Pedido confirmado.");
        } else {
            System.out.println("Pedido cancelado.");
        }
    }
}
