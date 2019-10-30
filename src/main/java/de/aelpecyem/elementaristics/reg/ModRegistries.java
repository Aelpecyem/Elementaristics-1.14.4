package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.common.capability.ascension.AscensionPath;
import de.aelpecyem.elementaristics.common.capability.soul.Soul;

public class ModRegistries {
    public static Soul MANA;
    public static Soul DRAGON;


    public static AscensionPath PATH_DEUS_EX_CARNE; //The God from Flesh
    public static AscensionPath PATH_EGO_PERPETUUM; //The Unceasing Ego
    public static AscensionPath PATH_FAMULUS_ENTHEATUS; //The Divinely Inspired Servant

    public static AscensionPath PATH_CREATUS_MATRIS; //The Mother's Child
    public static AscensionPath PATH_DEUS_EX_MACHINA; //The God from Machine

    public static void init() {
        initSouls();
        initAscensionPaths();
    }

    private static void initSouls() {
        MANA = new Soul("mana", 14102152, 0.4F, null);
        DRAGON = new Soul("dragon", 15316992, 0.1F, null);
    }

    private static void initAscensionPaths() {
        PATH_DEUS_EX_CARNE = new AscensionPath("deus_ex_carne");
        PATH_EGO_PERPETUUM = new AscensionPath("ego_perpetuum");
        PATH_FAMULUS_ENTHEATUS = new AscensionPath("famulus_entheatus");
        PATH_CREATUS_MATRIS = new AscensionPath("creatus_matris");
        PATH_DEUS_EX_MACHINA = new AscensionPath("deus_ex_machina");
    }
}
