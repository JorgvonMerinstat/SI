package cz.vse.si.main;

import cz.vse.si.logika.Hra;
import cz.vse.si.logika.IHra;
import cz.vse.si.logika.PrikazJdi;
import cz.vse.si.logika.Prostor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class HomeController implements Pozorovatel {

    @FXML
    private ListView<Prostor> panelVychodu;
    @FXML
    private Button tlacitkoPoslat;//zkouška pushe z nb
    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private IHra hra = new Hra();

    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
        hra.getHerniPlan().registruj(this);
        aktualizujSeznamVychodu();

    }
    @FXML
    private void aktualizujSeznamVychodu(){
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }

    @FXML
    private void poslatVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");


        if (hra.konecHry()){
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            tlacitkoPoslat.setDisable(true);
            panelVychodu.setDisable(true);
        }
    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }

    }

    @Override
    public void aktualizuj() {
        aktualizujSeznamVychodu();
    }

    @FXML
    public void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = PrikazJdi.NAZEV +" "+ cil;
        zpracujPrikaz(prikaz);
    }
}
