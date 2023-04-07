package cz.vse.si.logika;

/**
 * Zde se nachazi struktura prikazu pouzij, ktery zaroven ukoncuje hru.
 */
public class PrikazPouzij implements IPrikaz{
    /**
     Nastavení toho jak se bude příkaz volat
     */
    private static final String JMENO = "pouzij";
    /**
     * herní plán
     */
    private HerniPlan plan;
    /**
     * hra
     */
    private Hra hra;

    /**
     *
     * předpis příkazu
     * @param plan
     * @param hra
     */
    public PrikazPouzij(HerniPlan plan, Hra hra){
        this.plan = plan;
        this.hra = hra;
    }








    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length == 0 | parametry.length == 1){
            return "Musíš zadat co cheš použít a na co.";
        }

        String nazevPouziteho = parametry[0];
        String nazevUziteho = parametry[1];

        switch (nazevPouziteho){
            case "pistole":
                   if (plan.getBrasna().obsahujePolozku("naboje")) {
                       if (plan.getAktualniProstor().obsahujePostavu(nazevUziteho)) {
                           plan.getAktualniProstor().vyberPostavu(nazevUziteho);
                           return "Zabil jsi " + nazevUziteho;
                       }
                   }

                   break;
            case "kalich":
                  if (plan.getAktualniProstor().obsahujePostavu("Novacek")){
                      plan.getBrasna().vyndejZBrasny("kalich");

                      hra.setKonecHry(true);
                      return "Nováček je nadšený ze zlatého kalichu a na oplátku tě u sebe nechá a provdá za tebe dceru. \n"
                              +"Hodil jsi svou minulost za hlavu a žiješ poklidně u Nováčků.";


                  }
                break;
            case "dymka":
                  if (plan.getAktualniProstor().obsahujePostavu("stres")){
                      plan.getAktualniProstor().vyberPostavu(nazevUziteho);
                      hra.setKonecHry(true);
                      return "Zbavil ses svého stresu a bloudíš po štolách.\n " +
                              "Abys přežil, kradeš horníkům a později trampům jídlo a věci. Díky tomu se z tebe stane\n" +
                              "proslulé strašislo dolů Malá Amerika";
                  }

                break;
            case "marky":
                if (plan.getAktualniProstor().obsahujePostavu("policajt")){
                    plan.getBrasna().vyndejZBrasny("marky");
                    hra.setKonecHry(true);
                    return "Policajtovi jsi dal všechny své říšské marky. On ti naoplátku zajistí cestu do Německa.\n" +
                            "Jsi sice bez peněz, ale naživu - můžeš být rád!";

                }
            break;





            }


        return "To teď nemůžeš!";
    }



    @Override
    public String getJmeno() {
        return JMENO;
    }

}