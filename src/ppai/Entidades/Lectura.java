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
public class Lectura {
    private double estimada;
    private Date fechaHoraLectura;
    private double valorCorregido;
    private double valorLectura;
    
    public boolean esDePeriodo(Date fechaDesde, Date fechaHasta){
        return fechaHoraLectura.after(fechaDesde) && fechaHoraLectura.before(fechaHasta);
    }
}
