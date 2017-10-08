/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Patrones.Iterator;

import ppai.Entidades.Factura;

/**
 *
 * @author notebook
 */
public class IteratorFacturas implements Iterator {

    private Factura[] facturas;
    private int posicion;

    public IteratorFacturas(Object[] facturas) {
        this.facturas = (Factura[]) facturas;
    }

    @Override
    public void primero() {
        posicion = 0;
    }

    @Override
    public boolean haTerminado() {
        return !(posicion < facturas.length && facturas[posicion] != null);
    }

    @Override
    public Object actual() {
        return facturas[posicion];
    }

    @Override
    public void siguiente() {
        posicion++;
    }

}
