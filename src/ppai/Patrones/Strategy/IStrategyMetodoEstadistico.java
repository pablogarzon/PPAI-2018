package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;
import java.util.Date;

public interface IStrategyMetodoEstadistico {
    public double realizarCalculo(Factura[] facturas);
}
