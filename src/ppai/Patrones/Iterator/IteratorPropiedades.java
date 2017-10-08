/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Patrones.Iterator;

import ppai.Entidades.Propiedad;

/**
 *
 * @author notebook
 */
public class IteratorPropiedades implements Iterator {

    private Propiedad[] propiedades;
    private int posicion;

    public IteratorPropiedades(Object[] propiedades) {
        this.propiedades = (Propiedad[]) propiedades;
    }
    
    @Override
    public void primero() {
        posicion = 0;
    }

    @Override
    public boolean haTerminado() {
        return !(posicion < propiedades.length && propiedades[posicion] != null);
    }

    @Override
    public Object actual() {
        return this.propiedades[posicion];
    }

    @Override
    public void siguiente() {
        posicion++;
    }
    
}
