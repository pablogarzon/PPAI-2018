package ppai.Patrones.Iterator;

import ppai.Entidades.Categorias;

public class IteratorCategorias implements Iterator {

    private Categorias[] categorias;
    private int posicion;

    public IteratorCategorias(Object[] elementos) {
        this.categorias = (Categorias[]) elementos;
    }

    @Override
    public void primero() {
        posicion = 0;
    }

    @Override
    public boolean haTerminado() {
        return !(posicion < categorias.length && categorias[posicion] != null);
    }

    @Override
    public Object actual() {
        return categorias[posicion];
    }

    @Override
    public void siguiente() {
        posicion++;
    }
}
