/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.persistencia;

import cl.dto.PedidoDetalleProductoDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Simon
 */
public class PedidoDetalleProductoDAO {
    private Connection cnx;

    public PedidoDetalleProductoDAO(Connection cnx) {
        this.cnx = cnx;
    }
}
