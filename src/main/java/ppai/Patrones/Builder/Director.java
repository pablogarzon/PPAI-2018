package ppai.Patrones.Builder;

import java.util.Date;
import java.util.List;
import ppai.Controlador.GestorEstadisticaConsumo.PromedioZonaCategoria;

public class Director {
    IBuilderReporteEstadistico builderReporteEstadistico;
    
    public Director(IBuilderReporteEstadistico builderReporteEstadistico) {
        this.builderReporteEstadistico = builderReporteEstadistico;
    }
    
    public void construir(Date fechaDesde, Date fechaHasta, List<PromedioZonaCategoria> pzc){
        this.builderReporteEstadistico.construirEncabezado(fechaDesde, fechaHasta);
        this.builderReporteEstadistico.construirGraficoDeBarras(pzc);
    }
    
    public Object getProducto(){
        return this.builderReporteEstadistico.obtenerProducto();
    }
}
