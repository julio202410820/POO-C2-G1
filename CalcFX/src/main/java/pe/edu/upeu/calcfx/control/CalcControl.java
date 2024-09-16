package pe.edu.upeu.calcfx.control;


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
import pe.edu.upeu.calcfx.modelo.CaltTO;
import pe.edu.upeu.calcfx.servicio.CalcServiceI;

import java.util.List;

@Component
public class CalcControl {

    @Autowired
    CalcServiceI serviceI;

    @FXML
    TextField txtResultado;

    @FXML
    TableView tableView;
    @FXML
    TableColumn<CaltTO, String> cVAl1, cVAl2, cResult;
    @FXML
    TableColumn<CaltTO, Character>cOper;

    @FXML
    TableColumn<CaltTO, Void> cOpc;

    private ObservableList<CaltTO> calcTOList;
    private int indexEdit=-1;




    @FXML
    public void accionButton(ActionEvent event){
        System.out.println("hello Word");
        Button button=(Button)event.getSource();
        switch (button.getId()){
            case "btn7","btn8","btn9","btn6","btn5","btn4","btn3","btn2","btn1","btn0":{
                escribirNumeros(button.getText());
            }break;
            case "btnSum","btnMult","btnRest","btnDiv":{
                operador(button.getText());
            }break;
            case "btnIgual":{
                calcularResultado();
            }break;
            case "btnBorrar":{
                txtResultado.clear();
            }

        }
    }

    public void escribirNumeros(String valor){
        txtResultado.appendText(valor);
    }
    public void operador(String valor){
        txtResultado.appendText(" "+valor+" ");
    }
    public void calcularResultado() {
        String[] valores = txtResultado.getText().split(" ");
        double val1 = Double.parseDouble(String.valueOf(valores[0]));
        double val2 = Double.parseDouble(String.valueOf(valores[2]));

        switch (valores[1]) {
            case "+": {txtResultado.setText(String.valueOf(val1 + val2));}break;
            case "-": {txtResultado.setText(String.valueOf(val1 - val2));}break;
            case "/": {txtResultado.setText(String.valueOf(val1 / val2));}break;
            case "*": {txtResultado.setText(String.valueOf(val1 * val2));}break;

        }
        CaltTO to=new CaltTO();
        to.setNum1(String.valueOf(val1));
        to.setNum2(String.valueOf(val2));
        to.setOperador(valores[1].charAt(0));
        to.setResultado(String.valueOf(txtResultado.getText()));
        if(indexEdit!=-1){
            serviceI.actuslizarresultado(to, indexEdit);
        }else{
            serviceI.guardarResultados(to);
        }
        indexEdit=-1;
        listaOper();
    }
    private void editOperCalc(CaltTO cal, int index) {
        System.out.println("Editing: " + cal.getNum1() + " Index:"+index);
        txtResultado.setText(cal.getNum1()+" "+cal.getOperador()+" "+cal.getNum2());
        indexEdit=index;
    }
    private void deleteOperCalc(CaltTO cal, int index) {
        System.out.println("Deleting: " + cal.getNum2());
        serviceI.eliminarResultado(index);
        listaOper();
        //tableView.getItems().remove(cal); // Elimina la operación del TableView
    }
    private void addActionButtonsToTable() {
        Callback<TableColumn<CaltTO, Void>, TableCell<CaltTO, Void>>
                cellFactory = param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            {
                editButton.getStyleClass().setAll("btn", "btn-success");
                editButton.setOnAction(event -> {
                    CaltTO cal = getTableView().getItems().get(getIndex());
                    editOperCalc(cal, getIndex());
                });

                deleteButton.getStyleClass().setAll("btn", "btn-danger");
                deleteButton.setOnAction(event -> {
                    CaltTO cal = getTableView().getItems().get(getIndex());
                    deleteOperCalc(cal,getIndex());
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        cOpc.setCellFactory(cellFactory);
    }
    public void listaOper(){
        List<CaltTO> lista=serviceI.obtenerResultaOper();
        for (CaltTO to:lista) {
            System.out.println(to.toString());
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Vincular columnas con propiedades de CalcTO
        cVAl1.setCellValueFactory(new PropertyValueFactory<CaltTO,
                        String>("num1"));

        cVAl1.setCellFactory(TextFieldTableCell.<CaltTO>forTableColumn());
        cVAl2.setCellValueFactory(new PropertyValueFactory<CaltTO,
                String>("num1"));

        cVAl2.setCellFactory(TextFieldTableCell.<CaltTO>forTableColumn());
        cOper.setCellValueFactory(new
                PropertyValueFactory<>("Operador"));
        cOper.setCellFactory(ComboBoxTableCell.<CaltTO,
                Character>forTableColumn('+', '-', '/', '*'));
        cResult.setCellValueFactory(new PropertyValueFactory<CaltTO,
                String>("Resultado"));

        cResult.setCellFactory(TextFieldTableCell.<CaltTO>forTableColumn());
        // Agregar botones de eliminar y modificar
        addActionButtonsToTable();
        calcTOList = FXCollections.observableArrayList(serviceI.obtenerResultaOper());
        // Asignar los datos al TableView
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);

        cOper.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cResult.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cOpc.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        tableView.setItems(calcTOList);
    }



}
