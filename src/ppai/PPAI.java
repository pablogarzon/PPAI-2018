/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppai;

import ppai.Controlador.GestorEstadisticaConsumo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author notebook
 */
public class PPAI {

    /**
     * @param args the command line arguments
     */
    private static Scanner sc = new Scanner(System.in);
    private static Date fechaDesde, fechaHasta;

    private static boolean validarFechas() {
        DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        try {
            System.out.println("Ingrese fecha desde");
            fechaDesde = df.parse(sc.nextLine());
            System.out.println("Ingrese fecha hasta");
            fechaHasta = df.parse(sc.nextLine());

            return (fechaDesde != null && fechaHasta != null)
                    && fechaDesde.before(fechaHasta)
                    && (fechaHasta.before(new Date()) || fechaHasta.equals(new Date()));
        } catch (ParseException ex) {
            System.out.println("error: " + ex);
            return false;
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        GestorEstadisticaConsumo gestor = new GestorEstadisticaConsumo();

        do {
            gestor.solicitarPeriodoConsumo();
        } while (!validarFechas());

        gestor.tomarFechasPeriodo(fechaDesde, fechaHasta);

        String[] cats = gestor.buscarCategorias();

        List<String> catSeleccionadas = new ArrayList<String>();
        String seleccionada = "";
        boolean ingresoEnd = false;
//        do {
            System.out.println("Seleccione una categoria:");
            System.out.println("Todas");
            for (int i = 0; i < cats.length; i++) {
                System.out.println(cats[i]);
            }
            System.out.print("\nseleccionada: ");
            seleccionada = sc.nextLine();
            System.out.println("");
            
//            ingresoEnd = seleccionada.equals("end");
//            if(!ingresoEnd) catSeleccionadas.add(seleccionada);
//        } while (!ingresoEnd);
        
        //String[] seleccionadas = catSeleccionadas.toArray(new String[catSeleccionadas.size()]);
        
        gestor.buscarZonasDeLocalidades("Todas");
    }

}
