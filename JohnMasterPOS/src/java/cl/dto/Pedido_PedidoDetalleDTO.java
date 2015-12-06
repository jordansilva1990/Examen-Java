/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Pedido;
import cl.dominio.PedidoDetalle;

/**
 *
 * @author Simon
 */
public class Pedido_PedidoDetalleDTO {
    private Pedido pedidoDTO;
    private PedidoDetalle pedidoDetalleDTO;

    public Pedido_PedidoDetalleDTO() {
    }

    public Pedido_PedidoDetalleDTO(Pedido pedidoDTO, PedidoDetalle pedidoDetalleDTO) {
        this.pedidoDTO = pedidoDTO;
        this.pedidoDetalleDTO = pedidoDetalleDTO;
    }

    public Pedido getPedidoDTO() {
        return pedidoDTO;
    }

    public void setPedidoDTO(Pedido pedidoDTO) {
        this.pedidoDTO = pedidoDTO;
    }

    public PedidoDetalle getPedidoDetalleDTO() {
        return pedidoDetalleDTO;
    }

    public void setPedidoDetalleDTO(PedidoDetalle pedidoDetalleDTO) {
        this.pedidoDetalleDTO = pedidoDetalleDTO;
    }
    
    
}
