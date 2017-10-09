package ppai.Entidades;

public class Categorias {
    private double m3BasicoDesde;
    private double m3BasicoHasta;
    private double montoBasico;
    private String nombre;

    public Categorias() {
    }
    
    public void setNombre(String n){
        this.nombre = n;
    }
    
    public String getNombre(){
        return nombre;
    }
}
