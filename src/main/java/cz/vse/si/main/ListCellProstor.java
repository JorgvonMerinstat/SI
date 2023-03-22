package cz.vse.si.main;

import cz.vse.si.logika.Prostor;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ListCellProstor extends ListCell<Prostor> {
    @Override
    protected void updateItem(Prostor prostor, boolean empty) {
        super.updateItem(prostor, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        } else {
            setText(prostor.getJmeno());
            String cesta = getClass().getResource("prostory/"+prostor.getJmeno()+".jpg").toExternalForm();
            ImageView iw = new ImageView(cesta);
            setGraphic(iw);
            iw.setFitWidth(100);
            iw.setPreserveRatio(true);
        }
    }
}
