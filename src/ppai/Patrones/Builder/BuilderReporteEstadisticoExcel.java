package ppai.Patrones.Builder;

import java.io.File;
import java.util.Date;
import java.util.List;
import ppai.Controlador.GestorEstadisticaConsumo;

public class BuilderReporteEstadisticoExcel implements IBuilderReporteEstadistico {

    @Override
    public void construirEncabezado(Date fechaDesde, Date fechaHasta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void construirGraficoDeBarras(List<GestorEstadisticaConsumo.PromedioZonaCategoria> pzc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object obtenerProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
