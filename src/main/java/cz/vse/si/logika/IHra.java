/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodování: Prilis zluťoucky kun úpěl ďábelské ódy. */
package cz.vse.si.logika;


import cz.vse.si.main.PredmetPozorovani;

/**
 *  Rozhrani ktere musi implementovat hra,
 *  je na ne navazano uzivatelské rozhrani
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public interface IHra extends PredmetPozorovani
{
    //== VEREJNE KONSTANTY =====================================================
    //== DEKLAROVANE METODY ====================================================
    /**
     *  Vrátí úvodní zprávu pro hráče.
     *
     *  @return  vrací se řetězec, který se má vypsat na obrazovku
     */
    public String vratUvitani();

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *
     *  @return  vrací se řetězec, který se má vypsat na obrazovku
     */
    public String vratEpilog();

    /**
     * Vrací informaci o tom, zda hra již skončila,
     * je jedno zda výhrou, prohrou nebo příkazem konec.
     *
     * @return   vrací true, pokud hra skončila
     */
    public boolean konecHry();

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr,
     *  rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek);


    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan();

    //== ZDĚDĚNÉ METODY ========================================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
}
