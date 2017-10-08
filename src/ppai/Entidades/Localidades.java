/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Entidades;

import ppai.Patrones.Iterator.Iterator;
import ppai.Patrones.Iterator.IteratorZonas;

/**
 *
 * @author notebook
 */
public class Localidades {
    private int id;
    private String Nombre;
    private Zonas[] zonas;

    public Localidades() {
    }

    public Localidades(String Nombre, Zonas[] zonas) {
        this.Nombre = Nombre;
        this.zonas = zonas;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the zonas
     */
    public Zonas[] getZonas() {
        Zonas[] z = new Zonas[this.zonas.length];
        
        Iterator it = new IteratorZonas(this.zonas);
        it.primero();
        int cont = 0;
        while (!it.haTerminado()) {
            Zonas actual = (Zonas) it.actual();
            z[cont] = actual;
            it.siguiente();
            cont++;
        }

        return z;
    }

    /**
     * @param zonas the zonas to set
     */
    public void setZonas(Zonas[] zonas) {
        this.zonas = zonas;
    }
    
    
}
