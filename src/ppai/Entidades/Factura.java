package ppai.Entidades;

import java.util.Date;

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

    /**
     * @return the diasDeLecturaFacturados
     */
    public int getDiasDeLecturaFacturados() {
        return diasDeLecturaFacturados;
    }

    /**
     * @param diasDeLecturaFacturados the diasDeLecturaFacturados to set
     */
    public void setDiasDeLecturaFacturados(int diasDeLecturaFacturados) {
        this.diasDeLecturaFacturados = diasDeLecturaFacturados;
    }
    
}
