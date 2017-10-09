package ppai;

import ppai.Controlador.GestorEstadisticaConsumo;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import ppai.Entidades.Localidades;
import ppai.Entidades.Zonas;

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

    private static int solicitarSeleccionOpcFiltrado() {
        System.out.println("\nSeleccione una opcion de filtrado");
        System.out.println("1- Sumatoria");
        System.out.println("2- Promedio Normalizado");
        System.out.println("3- Media con desviación estandard");
        System.out.print("\nseleccionada: ");
        int seleccion = sc.nextInt();
        return seleccion;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        GestorEstadisticaConsumo gestor = new GestorEstadisticaConsumo();

        do {
            System.out.println("ingrese el periodo de consumo");
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
        Localidades[] localidades = gestor.buscarZonasDeLocalidades("Todas");

        System.out.println("Localidades:");
        for (Localidades localidad : localidades) {
            System.out.println("\t" + localidad.getNombre());
            System.out.println("\tzonas:");
            for (Zonas zona : localidad.getZonas()) {
                System.out.println("\t\t" + zona.getNombre());
            }
        }

        gestor.tomarSeleccionOpcEstadisticaConsumo(solicitarSeleccionOpcFiltrado());
        gestor.tomarConfirmacionGeneracionReporte();
    }

}
