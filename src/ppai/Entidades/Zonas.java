/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Entidades;

/**
 *
 * @author notebook
 */
public class Zonas {
    private String nombre;
    
    public Zonas() {
    }

    public Zonas(String nombre) {
        this.nombre = nombre;
    }   
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
