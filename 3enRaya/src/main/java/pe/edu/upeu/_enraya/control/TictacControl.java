package pe.edu.upeu._enraya.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu._enraya.modelo.TicTO;
import pe.edu.upeu._enraya.servicio.TicserveceI;

import java.util.List;

@Component
public class TictacControl {
    @Autowired
    TicserveceI a;


    Button[][] tablero;
    @FXML
    private Button Iniciar, Anular;

    @FXML
    public void initialize() {
        System.out.println("holas");

        tablero = new Button[][]{
                {btn00, btn01, btn02},
                {btn10, btn11, btn12},
                {btn20, btn21, btn22}
        };
        Anular();

    }

    @FXML
    Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22;
    boolean turno = true;
    boolean juegoActivo = false;

    @FXML
    private TextField jug1;
    @FXML
    private TextField jug2;

    @FXML
    private void jugador1() {
        String Jug1 = jug1.getText();
        System.out.println(Jug1);
    }

    @FXML
    private void jugador2() {
        String Jug2 = jug2.getText();
        System.out.println(Jug2);
    }


    @FXML
    void imprimir() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(tablero[i][j].getText() + "\t");
            }
            System.out.println("");
        }
    }

    @FXML
    void accionButon(ActionEvent e) {
        Button b = (Button) e.getSource();
        b.setText(turno ? "X" : "O");
        turno = !turno;
        // Verifica combinaciones para "X"
        if (checkWinner("X")) {
            System.out.println("Gana X");
            Anular();
        }
        // Verifica combinaciones para "O"
        else if (checkWinner("O")) {
            System.out.println("Gana O");
            Anular();
        } else if (isTableroLleno()) {
            System.out.println("Empate");
            Anular(); // Desactiva todos los botones
        }


    }

    boolean checkWinner(String player) {

        if (btn00.getText().equals(player) && btn01.getText().equals(player) && btn02.getText().equals(player))
            return true;
        if (btn10.getText().equals(player) && btn11.getText().equals(player) && btn12.getText().equals(player))
            return true;
        if (btn20.getText().equals(player) && btn21.getText().equals(player) && btn22.getText().equals(player))
            return true;
        if (btn00.getText().equals(player) && btn10.getText().equals(player) && btn20.getText().equals(player))
            return true;
        if (btn01.getText().equals(player) && btn11.getText().equals(player) && btn21.getText().equals(player))
            return true;
        if (btn02.getText().equals(player) && btn12.getText().equals(player) && btn22.getText().equals(player))
            return true;
        if (btn00.getText().equals(player) && btn11.getText().equals(player) && btn22.getText().equals(player))
            return true;
        if (btn02.getText().equals(player) && btn11.getText().equals(player) && btn20.getText().equals(player))
            return true;

        return false;
    }

    @FXML
    public void Iniciar() {
        activarDesactivar(false);
        limpiarTablero();
        turno = true;
        juegoActivo = true;

    }

    @FXML
    public void Anular() {
        activarDesactivar(true);
        juegoActivo = false;

    }

    public void activarDesactivar(boolean indi) {
        btn00.setDisable(indi);
        btn01.setDisable(indi);
        btn02.setDisable(indi);
        btn10.setDisable(indi);
        btn11.setDisable(indi);
        btn12.setDisable(indi);
        btn20.setDisable(indi);
        btn21.setDisable(indi);
        btn22.setDisable(indi);
    }

    private void limpiarTablero() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                btn.setText("");
            }
        }
    }

    private boolean isTableroLleno() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                if (btn.getText().isEmpty()) {
                    return false; //Si hay un botón vacío el tablero no está lleno
                }
            }
        }
        return true; //Todos los botones están llenos
    }

    @FXML
    TableView<TicTO> tableView;

    @FXML
    TableColumn<TicTO, String> nomP, nom1, nom2, nomG, Punt, Estad;

    private ObservableList<TicTO> ticTOList;
    private int inexEdit = -1;


}
