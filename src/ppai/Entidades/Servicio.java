/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ppai.Patrones.Iterator.*;

/**
 *
 * @author notebook
 */
public class Servicio {

    private Date fechaAlta;
    private Date fechaSolicitud;
    private Factura[] facturas;
    private Categorias categoria;

    public List<Factura> buscarFacturasXPeriodo(Date fechaDesde, Date fechaHasta) {
        Iterator it = crearIterador(this.facturas);
        List<Factura> listFacturas = new ArrayList<>();
        it.primero();
        while (!it.haTerminado()) {
            Factura actual = (Factura) it.actual();
            if (actual.sosDePeriodoLectura(fechaDesde, fechaHasta)) {
                listFacturas.add(actual);
            }
            it.siguiente();
        }

        return listFacturas;
    }

    private Iterator crearIterador(Object[] elements) {
        return new IteratorFacturas(elements);
    }

    public boolean esDeCategoria(String nomCategoria) {
        return this.categoria.getNombre().equals(nomCategoria);
    }
}
