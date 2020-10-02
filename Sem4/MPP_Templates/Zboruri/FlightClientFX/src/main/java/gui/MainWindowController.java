package gui;

import flight.domain.*;
import flight.domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.FlightException;
import services.IFlightObserver;
import services.IFlightServer;

import java.net.SocketException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MainWindowController implements Initializable, IFlightObserver{
    public Button backButton;
    public TableView<Zbor> tableZboruri;
    public TableColumn<Zbor, String> destinatieColumn;
    public TableColumn<Zbor, LocalDateTime> plecareColumn;
    public TableColumn<Zbor, String> aeroportColumn;
    public TableColumn<Zbor, Integer> locuriDisponibile;

    public TableView<ZborDTO> tableCautare;
    public TableColumn<ZborDTO, LocalTime> oraColumn;
    public TableColumn<ZborDTO, Integer> locuriColumn;

    public TextField destinatieCautareBox;
    public TextField plecareCautareBox;

    public TextField oraBox;
    public TextField numeClientTextField;
    public TextField adresaTextField;
    public TextArea numeTuristiTextField;
    public TextField locuriTextField;
    public Button cumparaButton;

    private Angajat angajat;
    private IFlightServer server;
    private ObservableList<Zbor> model = FXCollections.observableArrayList();
    private ObservableList<ZborDTO> modelCautare = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        destinatieColumn.setCellValueFactory(new PropertyValueFactory<Zbor,String>("destinatie"));
        plecareColumn.setCellValueFactory(new PropertyValueFactory<Zbor, LocalDateTime>("plecare"));
        aeroportColumn.setCellValueFactory(new PropertyValueFactory<Zbor, String>("aeroport"));
        locuriDisponibile.setCellValueFactory(new PropertyValueFactory<Zbor, Integer>("locuri"));
        tableZboruri.setItems(model);
        tableZboruri.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue!= null)
                showDestPlecare(newValue);
        } );
    }

    public void handleBack(ActionEvent actionEvent) {
        try {
            logOut();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch(Exception e) {
            System.out.println("OK!!");
        }
    }

    public void setAngajat(Angajat angajat) {
        this.angajat = angajat;
    }

    public void logOut() {
        try {
            server.logOut(angajat,this);
        } catch (FlightException e) {
            System.out.println("Logout error " + e);
        }

    }

    public void handleCauta(){
        oraColumn.setCellValueFactory(new PropertyValueFactory<ZborDTO, LocalTime>("ora"));
        locuriColumn.setCellValueFactory(new PropertyValueFactory<ZborDTO, Integer>("locuri"));
        tableCautare.setItems(modelCautare);


        String destinatie = destinatieCautareBox.getText();
        String data = plecareCautareBox.getText();
        try {
            modelCautare.setAll(StreamSupport.stream(server.getZboruriCautare(destinatie,data).spliterator(),false).collect(Collectors.toList()));
        } catch (FlightException e) {
            e.printStackTrace();
        }
        tableCautare.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue!= null)
                showOra(newValue);
        } );
    }

    private void showOra(ZborDTO newValue) {
        oraBox.setText(newValue.getOra().toString());
    }

    private void showDestPlecare(Zbor z){
        destinatieCautareBox.setText(z.getDestinatie());
        String month;
        if(z.getPlecare().getMonthValue()< 10)
            month="0"+z.getPlecare().getMonthValue();
        else
            month=String.valueOf(z.getPlecare().getMonthValue());
        String data = z.getPlecare().getYear()+"-"+month+"-"+z.getPlecare().getDayOfMonth();
        plecareCautareBox.setText(data);
    }
    public void handleCumpara() throws FlightException {
        String numeClient = numeClientTextField.getText();
        String adresaClient = adresaTextField.getText();
        String turisti = numeTuristiTextField.getText();
        Integer locuri = Integer.parseInt(locuriTextField.getText());

        String destinatie = destinatieCautareBox.getText();
        String ora = oraBox.getText();
        String data = plecareCautareBox.getText();
        Client c = null;
        Zbor z = null;
        try {
            c = server.findClient(numeClient, adresaClient);
            z = server.findZbor(destinatie, data, ora);
        }catch (FlightException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flights");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        Bilet b;
        Integer index = 0;
        if(z.getLocuri() - locuri > 0)
            index = server.findIndex();
            b = new Bilet(++index,z.getId(),c.getID(),turisti,locuri);
        try {
            System.out.println(b.toString());
            //server.zborUpdate(z.getID(), z.getLocuri()-locuri);
            server.cumparaBilet(b,z.getId());
        } catch (FlightException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flights");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }


    public void setServer(IFlightServer server) throws FlightException {
        this.server = server;
    }

    public void firstLoad(Iterable<Zbor> zboruri){
        model.setAll(StreamSupport.stream(zboruri.spliterator(),false).collect(Collectors.toList()));
    }

    @Override
    public void ticketBought(Iterable<Zbor> zboruri){
        model.setAll(StreamSupport.stream(zboruri.spliterator(),false).collect(Collectors.toList()));
    }
}
