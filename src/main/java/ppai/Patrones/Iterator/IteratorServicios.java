package ppai.Patrones.Iterator;

import ppai.Entidades.Factura;

public class IteratorServicios implements Iterator {
    
    private Factura[] facturas;
    private int posicion;
    
    public IteratorServicios(Object[] facturas) {
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
