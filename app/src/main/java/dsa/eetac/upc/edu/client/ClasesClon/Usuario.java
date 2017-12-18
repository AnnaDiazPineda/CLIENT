package dsa.eetac.upc.edu.client.ClasesClon;

import java.util.Vector;

/**
 * Created by anita on 18/12/2017.
 */

public class Usuario {
    String nombre;
    String email;
    String password;

    Vector<Pedido> pedidosRealizados;

    public Usuario() {
        pedidosRealizados = new Vector<Pedido>();
    }

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        pedidosRealizados = new Vector<Pedido>();
    }

    public void añadirPedido(Pedido p)
    {
        pedidosRealizados.add(p);
    }

    public Vector<Pedido> getPedidosRealizados() {
        return pedidosRealizados;
    }

    public void setPedidosRealizados(Vector<Pedido> pedidosRealizados) {
        this.pedidosRealizados = pedidosRealizados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
