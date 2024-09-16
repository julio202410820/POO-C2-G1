package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CaltTO;

import java.util.List;

public interface CalcServiceI {
    public void guardarResultados(CaltTO to);
    public List<CaltTO> obtenerResultaOper();
    public void actuslizarresultado(CaltTO to, int index);
    public void eliminarResultado(int index);


}
