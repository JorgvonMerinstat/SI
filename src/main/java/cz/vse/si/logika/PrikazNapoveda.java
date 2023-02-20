package cz.vse.si.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 */
public class PrikazNapoveda implements IPrikaz{
    /**
     * příkaz pomoc se volá klíčovým slovem "pomoc"
     */
    private static final String JMENO = "pomoc";
    /**
     * seznam platných příkazů
     */
    private SeznamPrikazu platnePrikazy;
    /**
    *  Konstruktor třídy
    *
     *  @param platnePrikazy seznam příkazů,
     *                       které je možné ve hře použít,
     *                       aby je nápověda mohla zobrazit uživateli.
    */
    public PrikazNapoveda(SeznamPrikazu platnePrikazy){this.platnePrikazy = platnePrikazy;}
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je protloukat se malebnou krajinou českého krasu\n"
                + "a pokusit se nějakým způsobem přežít nástrahy, které ti osud nastraží.\n"
                + "\n"
                + "Můžeš zadat tyto příkazy:\n"
                + platnePrikazy.vratJmenaPrikazu();
    }
    @Override
    /**
     * vrací jméno
     */
    public String getJmeno() {
        return JMENO;
    }
}
