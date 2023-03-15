package cz.vse.si.logika;

/**
 * Třída PrikazJdi implementuje pro hru příkaz jdi.
 */
public class PrikazJdi implements IPrikaz {

    /**
     * příkaz jdi se volá klíčovým slovem "jdi"
     */

    public static final String NAZEV = "jdi";
    /**
     * nastavuje proměnnou hra
     */

    private Hra hra;
    /**
     * herní plán
     */

    private HerniPlan plan;

    /**
     * Konstruktor třídy PrikazJdi
     * @param hra
     */

    public PrikazJdi(Hra hra) {
        this.hra = hra;
        plan = hra.getHerniPlan();
    }

    /**
     *  provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     * @param parametry jako  parametr obsahuje jméno prostoru (východu), do kterého se má vejít
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Zadej jméno východu!";
        }
        String smer = parametry[0];
        Prostor sousedniProstor = plan.getAktualniProstor().vratVedlejsiProstor(smer);
        if (sousedniProstor == null){
            return "Tam nemůžeš!";
        }
        else {
            plan.setAktualniProstor(sousedniProstor);
            if(sousedniProstor.equals(plan.getVyherniProstor())){
                hra.setEpilog("Došel jsi do výherního prostoru a hra končí!");
                hra.setKonecHry(true);
            }
            return sousedniProstor.dlouhyPopis();
        }


    }

    /**
     * Metoda vrací název příkazu
     * @return nazev prikazu
     */

    @Override
    public String getJmeno() {
        return NAZEV;
    }
}
