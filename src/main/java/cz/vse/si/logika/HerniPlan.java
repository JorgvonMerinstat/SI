package cz.vse.si.logika;


import cz.vse.si.main.Pozorovatel;
import cz.vse.si.main.PredmetPozorovani;


import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 */
public class HerniPlan implements PredmetPozorovani {

    /**
     * zakládá proměnnou aktuální prostor
     */
    private Prostor aktualniProstor;
    /**
     * zakládá proměnnou výherní prostor
     */
    private Prostor vyherniProstor;
    private Set<Pozorovatel> seznamPozorovatelu = new HashSet<>();


    /**
     * zakládá proměnnou brašna
     */

    private Brasna brasna;


    /**
     *  Konstruktor který vytváří jednotlivé
     *  prostory a propojuje je pomocí východů.
     */

    public HerniPlan() {
        brasna = new Brasna(3);
        zalozProstorHry();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví velitelství.
     */
    private void zalozProstorHry(){
        Prostor velitelstvi = new Prostor("velitelstvi", "Velitelství Wehrmachtu na hradě Karlštejn, kde sedí skupina velitelů a baví se jejich situaci. \n" +
                "Vzhledem k tomu, že rudá armáda rychle postupuje z východu, není jiná možnsot, než ukončit své životy dřív, než padneme do rukou těch ruských bestií.\n" +
                "Tvoje zbraň ale selže a zůstaneš jediný naživu, jen nemáš náboje.");
        Prostor krizovatkaPodKarlstejnem = new Prostor("krizovatka", "Křižovatka silnic pod hradem Karlštejn");
        Prostor lesackaChatka = new Prostor("hajenka","Lesácká chatka, ve které spí lesák");
        Prostor morinka = new Prostor("Morinka","Obec Mořinka, ve které se nachází známý kolaborant Nováček.");
        Prostor morina = new Prostor("Morina", "Obec Mořina");
        Prostor stoly = new Prostor("stoly", "Opuštěné štoly v dolceh");
        Prostor silnice = new Prostor("silnice","Křižovatka 2 silnic, jedna směřuje do obce Mořina, druhá do obce Mořinka");

        Postava lesak = new Postava("hajny");
        Postava hagenuvStres = new Postava("stres");
        Postava cajt = new Postava("policajt");
        Postava novacek = new Postava("Novacek");

        stoly.vlozPostavu(hagenuvStres);
        morina.vlozPostavu(cajt);
        lesackaChatka.vlozPostavu(lesak);
        morinka.vlozPostavu(novacek);




        velitelstvi.setVychod(krizovatkaPodKarlstejnem);
        krizovatkaPodKarlstejnem.setVychod(lesackaChatka);
        krizovatkaPodKarlstejnem.setVychod(velitelstvi);
        krizovatkaPodKarlstejnem.setVychod(stoly);
        lesackaChatka.setVychod(krizovatkaPodKarlstejnem);
        lesackaChatka.setVychod(silnice);
        silnice.setVychod(morina);
        silnice.setVychod(morinka);
        silnice.setVychod(lesackaChatka);
        morina.setVychod(silnice);
        morinka.setVychod(silnice);
        stoly.setVychod(krizovatkaPodKarlstejnem);









        Polozka marky = new Polozka("marky", true);
        Polozka dymka = new Polozka("dymka", true);

        Polozka pistole = new Polozka("pistole", true);
        Polozka kalich = new Polozka("kalich", true);

        Polozka naboje = new Polozka("naboje", true);


        velitelstvi.vlozPolozku(marky);
        velitelstvi.vlozPolozku(dymka);
        velitelstvi.vlozPolozku(pistole);
        velitelstvi.vlozPolozku(kalich);
        lesackaChatka.vlozPolozku(naboje);

        aktualniProstor = velitelstvi;







    }
    /**
     *  Metoda vrací odkaz na aktuální prostor,
     *  ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    public Prostor getAktualniProstor(){ return aktualniProstor; }
    /**
     *  Metoda nastaví aktuální prostor,
     *  používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */

    public void setAktualniProstor(Prostor prostor){
        aktualniProstor = prostor;
        for (Pozorovatel pozorovatel : seznamPozorovatelu){
            pozorovatel.aktualizuj();
        }
    }




    public Prostor getVyherniProstor(){return vyherniProstor;}
    public Brasna getBrasna(){return brasna;}


    @Override
    public void registruj(Pozorovatel pozorovatel) {
        seznamPozorovatelu.add(pozorovatel);
    }
}
