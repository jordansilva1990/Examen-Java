/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;

/**
 *
 * @author Jordan
 */
public class PedidoProductoPedidoDetalleDTO {
    
    PedidoDetalle pedidoDetalleDTO;
    Producto productoDTO;
    Pedido pedidoDTO;

    public PedidoProductoPedidoDetalleDTO() {
    }

    public PedidoProductoPedidoDetalleDTO(PedidoDetalle pedidoDetalleDTO, Producto productoDTO, Pedido pedidoDTO) {
        this.pedidoDetalleDTO = pedidoDetalleDTO;
        this.productoDTO = productoDTO;
        this.pedidoDTO = pedidoDTO;
    }

    public PedidoDetalle getPedidoDetalleDTO() {
        return pedidoDetalleDTO;
    }

    public void setPedidoDetalleDTO(PedidoDetalle pedidoDetalleDTO) {
        this.pedidoDetalleDTO = pedidoDetalleDTO;
    }

    public Producto getProductoDTO() {
        return productoDTO;
    }

    public void setProductoDTO(Producto productoDTO) {
        this.productoDTO = productoDTO;
    }

    public Pedido getPedidoDTO() {
        return pedidoDTO;
    }

    public void setPedidoDTO(Pedido pedidoDTO) {
        this.pedidoDTO = pedidoDTO;
    }
    
    
    
}
