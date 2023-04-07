package cz.vse.si.main;

import cz.vse.si.logika.Polozka;
import cz.vse.si.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellBrasna extends ListCell<Polozka> {
    @Override
    protected void updateItem(Polozka polozka, boolean empty) {
        super.updateItem(polozka, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        } else {
            setText(polozka.getJmeno());
            String cesta = getClass().getResource("polozky/"+polozka.getJmeno()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            setGraphic(iw);
            iw.setFitHeight(25);
            iw.setPreserveRatio(true);
        }
    }
}
