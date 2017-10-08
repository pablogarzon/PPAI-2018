/*
 * The MIT License
 *
 * Copyright 2017 grupo 11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ppai.Controlador;

import java.util.ArrayList;
import ppai.Entidades.*;
import ppai.Patrones.Iterator.*;
import java.util.Date;
import java.util.List;

public class GestorEstadisticaConsumo {

    private Date fechaDesde, fechaHasta;
    private final Categorias[] categorias;
    private final Localidades[] localidades;
    private Zonas[] zonas;
    private Propiedad[] propiedades;
    private List<Factura> facturasPeriodo;
    private int metodoEstadistico;

    public GestorEstadisticaConsumo() {
        Categorias c1 = new Categorias();
        c1.setNombre("Resindenciales");
        Categorias c2 = new Categorias();
        c2.setNombre("No Resindenciales");
        categorias = new Categorias[]{c1, c2};

        Zonas[] zonas1 = {new Zonas("z1"), new Zonas("z2"), new Zonas("z3")};
        Zonas[] zonas2 = {new Zonas("z4"), new Zonas("z5"), new Zonas("z6")};

        this.localidades = new Localidades[]{new Localidades("Arroyito", zonas1), new Localidades("Carlos Paz", zonas2)};
    }

    //solo sirve para ejecutar desde consola
    public void tomarSelecciionOpcEstadisticaConsumo() {
        System.out.println("Opción Estadistica de Consumo");
    }

    //solo sirve para ejecutar desde consola
    public void solicitarPeriodoConsumo() {
        System.out.println("ingrese el periodo de consumo");
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

    /**
     * recorre las localidades buscando el nombre de la localidad y las zonas
     * que contiene
     *
     * @param filtro
     * @return un diccionario que tiene como clave el nombre de la localidad y
     * como valor las zonas
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

    /**
     * obtiene las facturas asociadas al periodo recorriendo las propiedades y
     * consulta si pertenece a la zona seleccionada
     *
     * @param zona
     * @param categoria
     */
    public void tomarOpcFiltradoLocalidadConZona(String zona, String categoria) {
        Iterator it = new IteratorPropiedades(this.propiedades);
        it.primero();

        while (!it.haTerminado()) {
            Propiedad actual = (Propiedad) it.actual();
            if (actual.esDeZona(zona)) {
                List<Factura> facturas = actual.buscarFacturasXPeriodo(fechaDesde, fechaHasta, zona, categoria);
                facturasPeriodo.addAll(facturas);
            }
            it.siguiente();
        }

    }

    public void tomarSeleccionMetodoEstadistico(int opcMetodoEstadistico) {
        this.metodoEstadistico = opcMetodoEstadistico;
    }

    public void tomarConfirmacionGeneracionReporte() {
        buscarFacturasAsociadasALecturaPeriodo();
    }

    private void buscarFacturasAsociadasALecturaPeriodo() {

    }
}
