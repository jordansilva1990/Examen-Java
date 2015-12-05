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
public class Pedido implements Serializable{
    private int ticket;
    private String medioPago;
    private char agrandaBebidaPapas;
    private char paraLlevar;
    private int total;

    public Pedido() {
    }

    public Pedido(int ticket, String medioPago, char agrandaBebidaPapas, char paraLlevar, int total) {
        this.ticket = ticket;
        this.medioPago = medioPago;
        this.agrandaBebidaPapas = agrandaBebidaPapas;
        this.paraLlevar = paraLlevar;
        this.total = total;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public String getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(String medioPago) {
        this.medioPago = medioPago;
    }

    public char getAgrandaBebidaPapas() {
        return agrandaBebidaPapas;
    }

    public void setAgrandaBebidaPapas(char agrandaBebidaPapas) {
        this.agrandaBebidaPapas = agrandaBebidaPapas;
    }

    public char getParaLlevar() {
        return paraLlevar;
    }

    public void setParaLlevar(char paraLlevar) {
        this.paraLlevar = paraLlevar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.ticket;
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
        final Pedido other = (Pedido) obj;
        if (this.ticket != other.ticket) {
            return false;
        }
        return true;
    }
 
    
    
}