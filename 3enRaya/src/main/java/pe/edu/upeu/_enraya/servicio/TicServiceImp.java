package pe.edu.upeu._enraya.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu._enraya.modelo.TicTO;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicServiceImp implements TicserveceI {

    List<TicTO> almacen= new ArrayList<>();


    @Override
    public void guardarResultados(TicTO to) {

    }

    @Override
    public List<TicTO> obtenerResultados() {
        return List.of();
    }

    @Override
    public void actualizarResultados(TicTO to, int index) {

    }

    @Override
    public void eliminarResultados(int index) {

    }
}
