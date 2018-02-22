package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;
import ppai.Patrones.Iterator.Iterator;
import ppai.Patrones.Iterator.IteratorFacturas;

public class Sumatoria implements IStrategyMetodoEstadistico {

    @Override
    public double realizarCalculo(Factura[] facturas) {
        double suma = 0;
        Iterator it = new IteratorFacturas(facturas);
        it.primero();

        while (!it.haTerminado()) {
            Factura actual = (Factura) it.actual();
            suma += actual.calcularTotal();
            it.siguiente();
        }
        return suma;
    }
}
