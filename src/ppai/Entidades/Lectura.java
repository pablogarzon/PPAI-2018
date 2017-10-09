package ppai.Entidades;

import java.util.Date;

public class Lectura {
    private double estimada;
    private Date fechaHoraLectura;
    private double valorCorregido;
    private double valorLectura;
    
    public boolean esDePeriodo(Date fechaDesde, Date fechaHasta){
        return fechaHoraLectura.after(fechaDesde) && fechaHoraLectura.before(fechaHasta);
    }
}
