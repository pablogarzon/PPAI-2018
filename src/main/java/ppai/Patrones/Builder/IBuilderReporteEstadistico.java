package ppai.Patrones.Builder;

import java.util.Date;
import java.util.List;
import ppai.Controlador.GestorEstadisticaConsumo;

public interface IBuilderReporteEstadistico {

    public void construirEncabezado(Date fechaDesde, Date fechaHasta);

    public void construirGraficoDeBarras(List<GestorEstadisticaConsumo.PromedioZonaCategoria> pzc);

    public Object obtenerProducto();
}
