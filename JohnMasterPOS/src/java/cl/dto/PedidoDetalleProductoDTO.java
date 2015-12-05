/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.PedidoDetalle;
import cl.dominio.Producto;

/**
 *
 * @author Simon
 */
public class PedidoDetalleProductoDTO {
    PedidoDetalle pedidoDetalleDTO;
    Producto productoDTO;

    public PedidoDetalleProductoDTO(PedidoDetalle pedidoDetalleDTO, Producto productoDTO) {
        this.pedidoDetalleDTO = pedidoDetalleDTO;
        this.productoDTO = productoDTO;
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
    
    
}
