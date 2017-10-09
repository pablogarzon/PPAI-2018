package ppai.Entidades;

public class Zonas {
    private String nombre;
    
    public Zonas() {
    }

    public Zonas(String nombre) {
        this.nombre = nombre;
    }   
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
