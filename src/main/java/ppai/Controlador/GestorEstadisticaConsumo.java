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
    private IStrategyMetodoEstadistico metodoEstadistico;

    public GestorEstadisticaConsumo() {
        Categorias c1 = new Categorias();
        c1.setNombre("Resindenciales");
        Categorias c2 = new Categorias();
        c2.setNombre("No Resindenciales");
        categorias = new Categorias[]{c1, c2};

        Zonas[] zonas1 = {new Zonas("z1")};
        Zonas[] zonas2 = {new Zonas("z2"), new Zonas("z3")};
        Zonas[] zonas3 = {new Zonas("zona norte"), new Zonas("zona sur"), new Zonas("zona centro")};

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

    public String[] buscarLocalidades() {
        String[] localidades;
        List<String> zl = new ArrayList<>();
        Iterator it = new IteratorLocalidades(this.localidades);
        it.primero();

        while (!it.haTerminado()) {
            Localidades actual = (Localidades) it.actual();
            zl.add(actual.getNombre());
            it.siguiente();
        }
        localidades = zl.toArray(new String[zl.size()]);
        return localidades;
    }

    /**
     * recorre las localidades y las zonas que contienen
     *
     * @param filtro
     * @return un array con zonas
     */
    public String[] buscarZonas(String filtro) {
        List<String> zl = new ArrayList<>();
        String[] zonasLocalidades;
        Iterator it = new IteratorLocalidades(this.localidades);
        Iterator itZonas;
        it.primero();

        while (!it.haTerminado()) {
            Localidades actual = (Localidades) it.actual();
            if (filtro.equals("Todas") || filtro.equals(actual.getNombre())) {
                itZonas = new IteratorZonas(actual.getZonas());
                itZonas.primero();
                while (!itZonas.haTerminado()) {
                    Zonas actualZona = (Zonas) itZonas.actual();
                    zl.add(actualZona.getNombre());
                    itZonas.siguiente();
                }
            }

            it.siguiente();
        }
        zonasLocalidades = zl.toArray(new String[zl.size()]);

        return zonasLocalidades;
    }

    public void tomarSeleccionOpcEstadisticaConsumo(String opcMetodoEstadistico) {
        switch (opcMetodoEstadistico) {
            case "Media Con Desviacion Estandard":
                metodoEstadistico = new MediaConDesviacionEstandard();
                break;
            case "Promedio Normalizado":
                metodoEstadistico = new PromedioNormalizado();
                break;
            default:
                metodoEstadistico = new Sumatoria();
        }
    }

    public void tomarConfirmacionGeneracionReporte(String zonasSelecc) {
        Iterator itLocalidades = new IteratorLocalidades(this.localidades);
        Iterator itZonas;
        itLocalidades.primero();
        List<PromedioZonaCategoria> pzc = new ArrayList<>();
        try {
            while (!itLocalidades.haTerminado()) {
                Localidades localidad = (Localidades) itLocalidades.actual();
                itZonas = new IteratorZonas(localidad.getZonas());
                itZonas.primero();

                while (!itZonas.haTerminado()) {
                    Zonas actualZona = (Zonas) itZonas.actual();
                    if (actualZona.getNombre().equals(zonasSelecc)) {
                        Iterator itCat = new IteratorCategorias(categorias);
                        itCat.primero();
                        while (!itCat.haTerminado()) {
                            Categorias actualCat = (Categorias) itCat.actual();
                            Factura[] facturasPeriodo = buscarFacturasAsociadasALecturaPeriodo(actualZona.getNombre(), actualCat.getNombre());
                            double promedio = metodoEstadistico.realizarCalculo(facturasPeriodo);
                            pzc.add(new PromedioZonaCategoria(actualZona.getNombre(), actualCat.getNombre(), promedio));
                            itCat.siguiente();
                        }
                    }
                    itZonas.siguiente();
                }
                itLocalidades.siguiente();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * obtiene las facturas asociadas al periodo recorriendo las propiedades y
     * consulta si pertenece a la zona seleccionada
     *
     * @param zona
     * @param categoria
     */
    private Factura[] buscarFacturasAsociadasALecturaPeriodo(String zona, String categoria) {
        Factura[] facturasPeriodoZonaCat = null;
        Iterator it = new IteratorPropiedades(this.propiedades);
        it.primero();

        while (!it.haTerminado()) {
            Propiedad actual = (Propiedad) it.actual();
            if (actual.esDeZona(zona)) {
                List<Factura> facturas = actual.buscarFacturasXPeriodo(fechaDesde, fechaHasta, zona, categoria);
                facturasPeriodoZonaCat = facturas.toArray(new Factura[facturas.size()]);
            }
            it.siguiente();
        }

        return facturasPeriodoZonaCat;
    }

    public class PromedioZonaCategoria {

        private final String zona;
        private final String categoria;
        private final double promedio;

        public PromedioZonaCategoria(String zona, String categoria, Double promedio) {
            this.zona = zona;
            this.categoria = categoria;
            this.promedio = promedio;
        }
    }
}
