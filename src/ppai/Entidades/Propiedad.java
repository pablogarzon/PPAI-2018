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
public class Propiedad {

    private String calle;
    private String Departamento;
    private int nroIdentificacionCatastral;
    private int nro;
    private int piso;
    private Servicio[] servicios;
    private Zonas zona;

    protected void esDomicilioFacturacion() {

    }

    public boolean existeLecturaActualRegistrada() {
        return true;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @return the Departamento
     */
    public String getDepartamento() {
        return Departamento;
    }

    /**
     * @return the nroIdentificacionCatastral
     */
    public int getNroIdentificacionCatastral() {
        return nroIdentificacionCatastral;
    }

    /**
     * @return the nro
     */
    public int getNro() {
        return nro;
    }

    /**
     * @return the piso
     */
    public int getPiso() {
        return piso;
    }

    public List<Factura> buscarFacturasXPeriodo(Date fechaDesde, Date fechaHasta, String nomZona, String nomCategoria) {
        List<Factura> facturas = new ArrayList<>();

        Iterator it = crearIterador(this.servicios);
        it.primero();
        while (!it.haTerminado()) {
            Servicio actual = (Servicio) it.actual();
            if (actual.esDeCategoria(nomCategoria)) {
                facturas.addAll(actual.buscarFacturasXPeriodo(fechaDesde, fechaHasta));
            }
            it.siguiente();
        }

        return facturas;
    }

    private Iterator crearIterador(Servicio[] servicios) {
        return new IteratorServicios(servicios);
    }

    public boolean esDeZona(String nombreZona){
        return zona.getNombre().equals(nombreZona);
    }
}
