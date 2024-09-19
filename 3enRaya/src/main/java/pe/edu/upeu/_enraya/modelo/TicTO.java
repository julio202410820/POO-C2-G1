package pe.edu.upeu._enraya.modelo;

public class TicTO {
    private String   nombrePartida;
    private String nombreJugador1;
    private String nombreJugador2;
    private String ganador;
    private int punto;
    private String estado;

    public String getNombrePartida() {
        return nombrePartida;
    }

    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }

    public String getNombreJugador1() {
        return nombreJugador1;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }

    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
