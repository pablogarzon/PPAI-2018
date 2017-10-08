/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Patrones.Iterator;

/**
 *
 * @author notebook
 */
public interface Iterator {
    
    public void primero();
    public boolean haTerminado();
    public Object actual();
    public void siguiente();
    
}
