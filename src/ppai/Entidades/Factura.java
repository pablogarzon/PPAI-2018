/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Entidades;

import java.util.Date;

/**
 *
 * @author notebook
 */
public class Factura {
    private int diasDeLecturaFacturados;
    private Date fechaFacturacion;
    private int nroFactura;
    private int numero;
    private Lectura lectura;

    public Factura() {
    }
    
    public double calcularTotal(){
        double total=0;
        //...
        return total;
    }
    
    public boolean sosDePeriodoLectura(Date fechaDesde, Date fechaHasta){
        return this.lectura.esDePeriodo(fechaDesde, fechaHasta);
    }
    
}
