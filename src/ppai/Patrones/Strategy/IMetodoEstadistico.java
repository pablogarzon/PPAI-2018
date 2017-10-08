/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai.Patrones.Strategy;

import ppai.Entidades.Factura;
import java.util.Date;
import java.util.List;

/**
 *
 * @author notebook
 */
public interface IMetodoEstadistico {

    public double realizarCalculo(List<Factura> facturas);
}
