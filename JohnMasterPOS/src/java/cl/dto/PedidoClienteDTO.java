/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.dto;

import cl.dominio.Cliente;
import cl.dominio.Pedido;

/**
 *
 * @author Jordan
 */
public class PedidoClienteDTO {
  
    private Pedido pedidoDTO;
    private Cliente clienteDTO;

    public PedidoClienteDTO() {
    }

    public PedidoClienteDTO(Pedido pedidoDTO, Cliente clienteDTO) {
        this.pedidoDTO = pedidoDTO;
        this.clienteDTO = clienteDTO;
    }

    public Pedido getPedidoDTO() {
        return pedidoDTO;
    }

    public void setPedidoDTO(Pedido pedidoDTO) {
        this.pedidoDTO = pedidoDTO;
    }

    public Cliente getClienteDTO() {
        return clienteDTO;
    }

    public void setClienteDTO(Cliente clienteDTO) {
        this.clienteDTO = clienteDTO;
    }
    
}
