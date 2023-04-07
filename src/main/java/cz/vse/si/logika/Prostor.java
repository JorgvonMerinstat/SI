package cz.vse.si.logika;

import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;
/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 */

public class Prostor {
    /**
     * jméno prostoru
     */

    private String jmeno;
    /**
     * popis prostoru
     */
    private String popis;
    /**
     * východy z prostoru
     */

    private Set<Prostor> vychody;
    /**
     * položky v prostoru
     */

    private List<Polozka> seznamPolozek;
    /**
     * postavy v prostoru
     */
    private List<Postava> seznamPostav;
    /**
     * Vytvoreni prostoru se zadanym popisem, napr. "kuchyn", "hala", "travnik
     * pred domem"
     *
     * @param jmeno jméno prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */

    public Prostor(String jmeno, String popis) {
        this.jmeno = jmeno;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamPolozek = new ArrayList<Polozka>();
        seznamPostav = new ArrayList<Postava>();
    }

    /**
     * Definuje východ z prostoru
     *
     * @param vedlejsi prostor, který sousedí s aktuálním prostorem
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Porovnává, jestli jsou dva prostory shodné
     * @param o objekt, který se porovnává s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */
    @Override

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prostor)) {
            return false;
        }
        Prostor druhy = (Prostor) o;
        return (Objects.equals(this.jmeno, druhy.jmeno));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override


    public int hashCode() {
        int vysledek = 3;
        int hashJmena = Objects.hashCode(this.jmeno);
        vysledek = 37 * vysledek + hashJmena;
        return vysledek;
    }
    /**
     * Vrací jméno prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return jmeno prostoru
     */


    public String getJmeno() {
        return jmeno;
    }
    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */

    public String dlouhyPopis() {
        return "Nacházíš se v " + popis + "\n" + "Položky: " + seznamPolozek() +"\n" + "Postava: " + seznamPostav() + "\n" + popisVychodu();
    }
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */

    private String popisVychodu() {
        String vracenyText = "východy: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getJmeno();
        }
        return vracenyText;
    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param jmenoSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */

    public Prostor vratVedlejsiProstor(String jmenoSouseda) {
        List<Prostor> hledaneProstory =
                vychody.stream()
                        .filter(vedlejsi -> vedlejsi.getJmeno().equals(jmenoSouseda))
                        .collect(Collectors.toList());
        if (hledaneProstory.isEmpty()) {
            return null;
        } else {
            return hledaneProstory.get(0);
        }
    }
    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */

    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * vkládání položky od prostoru
     * @param neco
     */

    public void vlozPolozku(Polozka neco) {
        seznamPolozek.add(neco);
    }

    /**
     * obsahuje prostor položku?
     * @param jmenoPolozky
     * @return
     */



    public boolean obsahujePolozku(String jmenoPolozky) {
        for (Polozka neco : seznamPolozek) {
            if (neco.getJmeno().equals(jmenoPolozky)) {
                return true;
            }
        }
        return false;
    }

    /**
     * vyndání položky z prostoru
     * @param jmenoPolozky
     * @return
     */

    public Polozka vyberPolozku(String jmenoPolozky) {
        Polozka vybranaPolozka = null;
        for (Polozka neco : seznamPolozek) {
            if (neco.getJmeno().equals(jmenoPolozky)) {
                vybranaPolozka = neco;
            }
        }
        if (vybranaPolozka != null) {
            if (vybranaPolozka.muzuPrenest()) {
                seznamPolozek.remove(vybranaPolozka);
            } else {
                vybranaPolozka = null;
            }
        }
        return vybranaPolozka;
    }

    /**
     * seznam položek
     * @return
     */

    private String seznamPolozek(){
        String seznam = "";
        for (Polozka neco : seznamPolozek){
            seznam = seznam + neco.getJmeno()+" ";
        }
        return seznam;

    }

    /**
     * vkládání postavy
     * @param nekdo
     */
    public void vlozPostavu(Postava nekdo){seznamPostav.add(nekdo);}

    private String seznamPostav(){
        String seznam = "";
        for (Postava nekdo : seznamPostav){
            seznam = seznam + nekdo.getJmeno() + " ";
        }
        return seznam;
    }

    /**
     * obsahuje prostor postavu?
     * @param jmenoPostavy
     * @return
     */
    public boolean obsahujePostavu(String jmenoPostavy) {
        for (Postava nekdo : seznamPostav) {
            if (nekdo.getJmeno().equals(jmenoPostavy)) {
                return true;
            }
        }
        return false;
    }
    /**
     * vyndání postavy z prostoru
     * @param
     * @return
     */
    public Postava vyberPostavu(String jmenoPostavy) {
        Postava vybranaPostava = null;
        for (Postava nekdo : seznamPostav) {
            if (nekdo.getJmeno().equals(jmenoPostavy)) {
                vybranaPostava = nekdo;
            }
        }
        if (vybranaPostava != null) {

                seznamPostav.remove(vybranaPostava);


            }
    return vybranaPostava;
    }


}