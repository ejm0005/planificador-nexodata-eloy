package planificador;

public class Proceso {
    private String nombre;
    private int llegada;
    private int rafaga;
    private int restante;
    private int ordenFichero;
    private EstadoProceso estado;

    private int fin = -1;
    private int primeraEjecucion = -1;

    // Constructor adaptado a LectorCSV (recibe nombre, llegada, ráfaga y ordenFichero)
    public Proceso(String nombre, int llegada, int rafaga, int ordenFichero) {
        this.nombre = nombre;
        this.llegada = llegada;
        this.rafaga = rafaga;
        this.restante = rafaga;
        this.ordenFichero = ordenFichero;
        this.estado = EstadoProceso.NUEVO;
    }

    /** Copia limpia, para poder simular varios algoritmos con los mismos datos. */
    public Proceso copia() {
        return new Proceso(nombre, llegada, rafaga, ordenFichero);
    }

    public void ejecutarUnaUnidad(int instante) {
        if (primeraEjecucion < 0) primeraEjecucion = instante;
        restante--;
    }

    public boolean haTerminado() {
        return restante == 0;
    }

    // Métricas. Todas se miden en instantes, no en casillas del diagrama.
    public int getRetorno() {
        return fin - llegada;
    }

    public int getEspera() {
        return getRetorno() - rafaga;
    }

    public int getRespuesta() {
        return primeraEjecucion - llegada;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public int getLlegada() {
        return llegada;
    }

    public int getRafaga() {
        return rafaga;
    }

    public int getRestante() {
        return restante;
    }

    public int getOrdenFichero() {
        return ordenFichero;
    }

    public EstadoProceso getEstado() {
        return estado;
    }

    public void setEstado(EstadoProceso estado) {
        this.estado = estado;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
}
