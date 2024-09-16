package pe.edu.upeu._enraya.servicio;


import pe.edu.upeu._enraya.modelo.TicTO;

import java.util.List;

public interface TicserveceI {
    public void guardarResultados(TicTO to);//C

    public List<TicTO> obtenerResultados();//R

    public void actualizarResultados(TicTO to, int index);//U

    public void eliminarResultados(int intex);//D
}

