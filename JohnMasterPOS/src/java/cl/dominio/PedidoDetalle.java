/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dominio;

import java.io.Serializable;

/**
 *
 * @author Jordan
 */
public class PedidoDetalle implements Serializable{
    private int idPedidoDetalle;
    private int cantidad;

    public PedidoDetalle() {
    }

    public PedidoDetalle(int idPedidoDetalle, int cantidad) {
        this.idPedidoDetalle = idPedidoDetalle;
        this.cantidad = cantidad;
    }

    public int getIdPedidoDetalle() {
        return idPedidoDetalle;
    }

    public void setIdPedidoDetalle(int idPedidoDetalle) {
        this.idPedidoDetalle = idPedidoDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.idPedidoDetalle;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoDetalle other = (PedidoDetalle) obj;
        if (this.idPedidoDetalle != other.idPedidoDetalle) {
            return false;
        }
        return true;
    }
    
    
}
