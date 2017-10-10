package ppai.Controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ppai.Entidades.*;
import ppai.Patrones.Iterator.*;
import ppai.Patrones.Strategy.*;

public class GestorEstadisticaConsumo {

    private Date fechaDesde, fechaHasta;
    private final Categorias[] categorias;
    private final Localidades[] localidades;
    private Propiedad[] propiedades;
    private Factura[] facturasPeriodo;
    private int metodoEstadistico;

    public GestorEstadisticaConsumo() {
        Categorias c1 = new Categorias();
        c1.setNombre("Resindenciales");
        Categorias c2 = new Categorias();
        c2.setNombre("No Resindenciales");
        categorias = new Categorias[]{c1, c2};

        Zonas[] zonas1 = {new Zonas("z1")};
        Zonas[] zonas2 = {new Zonas("z2"), new Zonas("z3")};
        Zonas[] zonas3 = {new Zonas("z4"), new Zonas("z5"), new Zonas("z6"), new Zonas("z7")};

        this.localidades = new Localidades[]{
            new Localidades("Arroyito", zonas1),
            new Localidades("Carlos Paz", zonas2),
            new Localidades("Córdoba", zonas3)
        };
    }

    public void tomarFechasPeriodo(Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public void tomarSeleccionOpcEstadisticaConsumo(int metodoEstadistico) {
        this.metodoEstadistico = metodoEstadistico;
    }

    public String[] buscarCategorias() {
        Iterator it = new IteratorCategorias(this.categorias);
        String[] cats = new String[this.categorias.length];
        it.primero();
        int cont = 0;
        while (!it.haTerminado()) {
            Categorias actual = (Categorias) it.actual();
            cats[cont] = actual.getNombre();
            it.siguiente();
            cont++;
        }

        return cats;
    }

    /**
     * recorre las localidades y las zonas que contienen
     *
     * @param filtro
     * @return un array con localidades y sus zonas
     */
    public Localidades[] buscarZonasDeLocalidades(String filtro) {
        List<Localidades> zl = new ArrayList<>();
        Localidades[] zonasLocalidades;
        Iterator it = new IteratorLocalidades(this.localidades);
        it.primero();

        while (!it.haTerminado()) {
            Localidades actual = (Localidades) it.actual();
            if (filtro.equals("Todas") || filtro.equals(actual.getNombre())) {
                zl.add(actual);
            }

            it.siguiente();
        }
        zonasLocalidades = zl.toArray(new Localidades[zl.size()]);

        return zonasLocalidades;
    }

    public void tomarSeleccionMetodoEstadistico(int opcMetodoEstadistico) {
        this.metodoEstadistico = opcMetodoEstadistico;
    }

    public void tomarConfirmacionGeneracionReporte(Localidades[] localidades) {
        buscarFacturas(localidades);
        IStrategyMetodoEstadistico metodoEstadistico;
        switch (this.metodoEstadistico) {
            case 1:
                metodoEstadistico = new MediaConDesviacionEstandard();
                break;
            case 2:
                metodoEstadistico = new PromedioNormalizado();
                break;
            default:
                metodoEstadistico = new Sumatoria();
        }
        metodoEstadistico.realizarCalculo(facturasPeriodo);

    }

    private void buscarFacturas(Localidades[] localidades) {
        Iterator itLocalidades = new IteratorLocalidades(localidades);
        Iterator itZonas;
        itLocalidades.primero();
        while (!itLocalidades.haTerminado()) {
            Localidades localidad = (Localidades) itLocalidades.actual();
            itZonas = new IteratorZonas(localidad.getZonas());
            itZonas.primero();
            
            while (!itZonas.haTerminado()) {
                Zonas actualZona = (Zonas) itZonas.actual();

                Iterator itCat = new IteratorCategorias(categorias);
                itCat.primero();

                while (!itCat.haTerminado()) {
                    Categorias actualCat = (Categorias) itCat.actual();
                    buscarFacturasAsociadasALecturaPeriodo(actualZona.getNombre(), actualCat.getNombre());
                    itCat.primero();
                }

                itZonas.siguiente();
            }
        }
    }

    /**
     * obtiene las facturas asociadas al periodo recorriendo las propiedades y
     * consulta si pertenece a la zona seleccionada
     *
     * @param zona
     * @param categoria
     */
    private void buscarFacturasAsociadasALecturaPeriodo(String zona, String categoria) {
        Iterator it = new IteratorPropiedades(this.propiedades);
        it.primero();

        while (!it.haTerminado()) {
            Propiedad actual = (Propiedad) it.actual();
            if (actual.esDeZona(zona)) {
                List<Factura> facturas = actual.buscarFacturasXPeriodo(fechaDesde, fechaHasta, zona, categoria);
                facturasPeriodo = facturas.toArray(new Factura[facturas.size()]);
            }
            it.siguiente();
        }
    }
}
