package pe.edu.upeu.sysalmacenfx.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.servicio.ClienteService;

@Component
public class ReenController {

    @FXML
    private VBox miContenedor;

    @FXML
    private TextField medida;

    @FXML
    private TextField Tipbanda;

    @FXML
    private TextField Costo;

    @FXML
    private DatePicker fecha;

    @FXML
    private Label lbnMsg;

    @FXML
    private TableView<Cliente> tableViewReen;

    @FXML
    private TableColumn<Cliente, String> colmedida;
    @FXML
    private TableColumn<Cliente, String> colbanda;
    @FXML
    private TableColumn<Cliente, String> colfecha;
    @FXML
    private TableColumn<Cliente, String> colCosto;
    @FXML
    private TableColumn<Cliente, Void> colAccion;

    private final ClienteService clienteService;
    Long idProductoCE=0L;

    @Autowired
    public ReenController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @FXML
    public void initialize() {
        // Configurar las columnas de la tabla
        colmedida.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDniruc()));
        colbanda.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombres()));
        colfecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRepLegal()));
        colCosto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoDocumento()));

        // Configurar la columna de acciones
        colAccion.setCellFactory(new Callback<>() {
            @Override
            public TableCell<Cliente, Void> call(TableColumn<Cliente, Void> param) {
                return new TableCell<>() {
                    private final Button editButton = new Button("Editar");
                    private final Button deleteButton = new Button("Eliminar");
                    private final HBox actionButtons = new HBox(5, editButton, deleteButton);

                    {
                        editButton.setOnAction(event -> editarCliente(getTableView().getItems().get(getIndex())));
                        deleteButton.setOnAction(event -> eliminarCliente(getTableView().getItems().get(getIndex())));
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(actionButtons);
                        }
                    }
                };
            }
        });

        // Cargar datos de la base de datos
        cargarDatos();
    }
    private void cargarDatos() {
        tableViewReen.getItems().clear(); // Limpia los datos actuales en la tabla
        tableViewReen.getItems().addAll(clienteService.list()); // Añade todos los clientes obtenidos de la base de datos
        idProductoCE=0L;
    }
    @FXML
    private void guardarSer(ActionEvent event) {
        try {
            Cliente nuevoCliente = Cliente.builder()
                    .dniruc(medida.getText())          // Medida
                    .nombres(Tipbanda.getText())       // Tipo de Banda
                    .repLegal(fecha.getValue().toString()) // Fecha como String
                    .tipoDocumento(Costo.getText())    // Costo
                    .build();

            clienteService.save(nuevoCliente);  // Guardar el cliente usando el servicio

            // Actualizar la tabla y mostrar mensaje
            tableViewReen.getItems().add(nuevoCliente);
            lbnMsg.setText("Registro guardado exitosamente.");

            // Limpiar campos
            limpiarCampos();
        } catch (Exception e) {
            lbnMsg.setText("Error al guardar el registro.");
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelarSer(ActionEvent event) {
        limpiarCampos();
        lbnMsg.setText("Operación cancelada.");
    }

    private void limpiarCampos() {
        medida.clear();
        Tipbanda.clear();
        Costo.clear();
        fecha.setValue(null);
    }

    private void editarCliente(Cliente cliente) {
        // Llenar los campos con los datos del cliente seleccionado
        medida.setText(cliente.getDniruc());
        Tipbanda.setText(cliente.getNombres());
        Costo.setText(cliente.getTipoDocumento());
        fecha.setValue(java.time.LocalDate.parse(cliente.getRepLegal()));
        idProductoCE=0L;


        // Actualizar el mensaje
        lbnMsg.setText("Editando cliente: " + cliente.getDniruc());

        // Puedes implementar una funcionalidad adicional para guardar los cambios
    }

    private void eliminarCliente(Cliente cliente) {
        // Eliminar el cliente de la base de datos y de la tabla
        clienteService.delete(cliente.getDniruc());
        tableViewReen.getItems().remove(cliente);

        // Actualizar el mensaje
        lbnMsg.setText("Cliente eliminado: " + cliente.getDniruc());
    }
}
