package ppai.Patrones.Iterator;

import ppai.Entidades.Zonas;

public class IteratorZonas implements Iterator {

    private Zonas[] zonas;
    private int posicion;
    
    public IteratorZonas(Object[] elementos) {
        this.zonas = (Zonas[]) elementos;
    }
    
    @Override
    public void primero() {
        posicion = 0;
    }

    @Override
    public boolean haTerminado() {
        return !(posicion < zonas.length && zonas[posicion] != null);
    }

    @Override
    public Object actual() {
        return zonas[posicion];
    }

    @Override
    public void siguiente() {
        posicion++;
    }
    
}
