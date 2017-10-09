package ppai.Entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ppai.Patrones.Iterator.*;

public class Servicio {

    private Date fechaAlta;
    private Date fechaSolicitud;
    private Factura[] facturas;
    private Categorias categoria;

    public List<Factura> buscarFacturasXPeriodo(Date fechaDesde, Date fechaHasta) {
        Iterator it = new IteratorFacturas(this.facturas);
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

    public boolean esDeCategoria(String nomCategoria) {
        return this.categoria.getNombre().equals(nomCategoria);
    }
}
