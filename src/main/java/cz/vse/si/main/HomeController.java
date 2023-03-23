package cz.vse.si.main;

import cz.vse.si.logika.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.*;

public class HomeController {

   @FXML
    private ImageView hrac;
    @FXML
    private ListView<Prostor> panelVychodu;
    @FXML
    private ListView<Polozka> panelBrasny;
    @FXML
    private Button tlacitkoPoslat;//zkouška pushe z nb
    @FXML
    private TextArea vystup;
    @FXML
    private TextField vstup;

    private IHra hra = new Hra();


    private ObservableList<Prostor> seznamVychodu = FXCollections.observableArrayList();
    private ObservableList<Polozka> obsahBrasny = FXCollections.observableArrayList();
    private Map<String, Point2D> souradniceProstoru = new HashMap<>();
    @FXML
    private void initialize(){
        vystup.appendText(hra.vratUvitani() + "\n\n");
        Platform.runLater(() -> vstup.requestFocus());
        panelVychodu.setItems(seznamVychodu);
       panelBrasny.setItems(obsahBrasny);
        hra.getHerniPlan().registruj(ZmenaHry.ZMENA_MISTNOSTI, () -> {
            aktualizujSeznamVychodu();
            aktualizujPolohuHrace();
        });
        hra.registruj(ZmenaHry.KONEC_HRY, () -> aktualizujKonecHry());
        aktualizujSeznamVychodu();
        akualizujObsahBrasny();
        vlozSouradnice();
        panelVychodu.setCellFactory(param -> new ListCellProstor());
    }

    private void vlozSouradnice() {
        souradniceProstoru.put("velitelstvi", new Point2D(159, 205));
        souradniceProstoru.put("krizovatka", new Point2D(199, 228));
        souradniceProstoru.put("stoly", new Point2D(101, 7));
        souradniceProstoru.put("Morina", new Point2D(388, 22));
        souradniceProstoru.put("Morinka", new Point2D(661, 213));
        souradniceProstoru.put("hajenka", new Point2D(345, 222));
        souradniceProstoru.put("silnice", new Point2D(592, 228));
    }

    @FXML
    private void aktualizujSeznamVychodu(){
        seznamVychodu.clear();
        seznamVychodu.addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
    }
    private void akualizujObsahBrasny(){
        obsahBrasny.clear();
        obsahBrasny.addAll(hra.getHerniPlan().getBrasna().getSeznamPolozek());

    }
    private void aktualizujPolohuHrace(){
        String prostor = hra.getHerniPlan().getAktualniProstor().getJmeno();
        hrac.setLayoutX(souradniceProstoru.get(prostor).getX());
        hrac.setLayoutY(souradniceProstoru.get(prostor).getY());


    }
    private void aktualizujKonecHry() {

        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
        }
        vstup.setDisable(hra.konecHry());
        tlacitkoPoslat.setDisable(hra.konecHry());
        panelVychodu.setDisable(hra.konecHry());

    }

    @FXML
    private void poslatVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        vstup.clear();
        zpracujPrikaz(prikaz);
        akualizujObsahBrasny();
    }

    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("> " + prikaz + "\n");
        String vysledek = hra.zpracujPrikaz(prikaz);
        vystup.appendText(vysledek + "\n\n");



    }

    public void ukoncitHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš ukončit hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            Platform.exit();
        }

    }





    @FXML
    public void klikPanelVychodu(MouseEvent mouseEvent) {
        Prostor cil = panelVychodu.getSelectionModel().getSelectedItem();
        if (cil==null) return;
        String prikaz = PrikazJdi.NAZEV +" "+ cil.getJmeno();
        zpracujPrikaz(prikaz);
        akualizujObsahBrasny();
    }
    @FXML
    private void napovedaKlik(ActionEvent actionEvent) {
        Stage napovedaStage = new Stage();
        WebView wv = new WebView();
        Scene napovedaScena = new Scene(wv);
        napovedaStage.setScene(napovedaScena);
        napovedaStage.show();
        wv.getEngine().load(getClass().getResource("napoveda.html").toExternalForm());
        //wv.getEngine().load("https://www.youtube.com/watch?v=dQw4w9WgXcQ");

    }
    @FXML
    private void opakovatHru(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Opravdu chceš opakovat hru?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
        System.out.println("Opakuji hru");



        }
    }
}
