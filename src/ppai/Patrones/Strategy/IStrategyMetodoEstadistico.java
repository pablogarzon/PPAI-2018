package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;

public interface IStrategyMetodoEstadistico {

    public double realizarCalculo(Factura[] facturas);
}
