package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;
import ppai.Patrones.Iterator.Iterator;
import ppai.Patrones.Iterator.IteratorFacturas;

public class PromedioNormalizado implements IStrategyMetodoEstadistico {

    @Override
    public double realizarCalculo(Factura[] facturas) {
        double promedio = 0, normalizacion = 0;
        Iterator it = new IteratorFacturas(facturas);
        it.primero();

        while (!it.haTerminado()) {
            Factura actual = (Factura) it.actual();
            normalizacion += actual.calcularTotal() / (actual.getDiasDeLecturaFacturados() * 30);
            it.siguiente();
        }
        if(facturas.length > 0) {
            promedio = normalizacion / facturas.length;
        }
        return promedio;
    }
}
