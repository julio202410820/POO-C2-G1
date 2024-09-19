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
    TableView<TicTO> tableView;

    @FXML
    TableColumn<TicTO, String> nomP, nom1, nom2, nomG,Punt, Estad;

    private ObservableList<TicTO> ticTOList;
    private int inexEdit;




    @FXML
    public void initialize() {
        System.out.println("holas");
        ticTOList = FXCollections.observableArrayList();
        tableView.setItems(ticTOList);
        nomP.setCellValueFactory(new PropertyValueFactory<>("nombrePartida"));
        nom1.setCellValueFactory(new PropertyValueFactory<>("nombreJugador1"));
        nom2.setCellValueFactory(new PropertyValueFactory<>("nombreJugador2"));
        nomG.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        Punt.setCellValueFactory(new PropertyValueFactory<>("punto"));
        Estad.setCellValueFactory(new PropertyValueFactory<>("estado"));


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
            storeResult("X");
            Anular();
        }
        // Verifica combinaciones para "O"
        else if (checkWinner("O")) {
            System.out.println("Gana O");
            storeResult("O");
            Anular();
        } else if (TableroLleno()) {
            System.out.println("Empate");
            storeResult("Empate");
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

    private boolean TableroLleno() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                if (btn.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    private void storeResult(String winner) {
        String nom1 = jug1.getText();
        String nom2 = jug2.getText();

        TicTO ticTO = new TicTO();
        ticTO.setNombrePartida("Partida " + (ticTOList.size() + 1));
        ticTO.setNombreJugador1(nom1);
        ticTO.setNombreJugador2(nom2);
        ticTO.setGanador(winner.equals("X") ? nom1+" "+"Ganador" : winner.equals("O") ? nom2+" "+"Ganador"   : "Empate");
        ticTO.setPunto(winner.equals("X") ? 1: winner.equals("O") ? 1: 0 );
        if (winner.equals("Anulado")) {
            ticTO.setEstado("Anulado");
        } else if (winner.equals("X") || winner.equals("O") || winner.equals("Empate")) {
            ticTO.setEstado("Finalizado");
        }
        ticTOList.add(ticTO);
    }




}
