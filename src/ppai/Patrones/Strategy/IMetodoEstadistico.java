package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;
import java.util.Date;
import java.util.List;

public interface IMetodoEstadistico {

    public double realizarCalculo(List<Factura> facturas);
}
