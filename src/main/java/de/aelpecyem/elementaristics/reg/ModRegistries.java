package de.aelpecyem.elementaristics.reg;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.capability.ascension.AscensionPath;
import de.aelpecyem.elementaristics.common.capability.soul.Soul;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import net.minecraft.util.ResourceLocation;

public class ModRegistries {
    //todo, give deities proper functionality, as this was made just for a test and to show off lul
    public static Deity JANUS = new Deity(0, new ResourceLocation(Elementaristics.MODID, "deity_nothingness"), null, 13553358);
    public static Deity BLIND_IDIOT = new Deity(1, new ResourceLocation(Elementaristics.MODID, "deity_idiot"), null, 5065806); //Azathoth
    public static Deity DRAGON_AETHER = new Deity(2, new ResourceLocation(Elementaristics.MODID, "deity_aether"), null, 4986465);
    public static Deity DRAGON_FIRE = new Deity(3, new ResourceLocation(Elementaristics.MODID, "deity_fire"), null, 16139267);
    public static Deity DRAGON_EARTH = new Deity(4, new ResourceLocation(Elementaristics.MODID, "deity_earth"), null, 15375);
    public static Deity DRAGON_WATER = new Deity(5, new ResourceLocation(Elementaristics.MODID, "deity_water"), null, 1049560);
    public static Deity DRAGON_AIR = new Deity(6, new ResourceLocation(Elementaristics.MODID, "deity_air"), null, 1300735);
    public static Deity GATE_AND_KEY = new Deity(7, new ResourceLocation(Elementaristics.MODID, "deity_gate"), null, 1131335); //Yog-Sothoth
    public static Deity DREAMER = new Deity(8, new ResourceLocation(Elementaristics.MODID, "deity_dreamer"), null, 8497580); //Cthulhu
    public static Deity ANGEL = new Deity(9, new ResourceLocation(Elementaristics.MODID, "deity_angel"), null, 4757545); //Lucifer
    public static Deity STORM = new Deity(10, new ResourceLocation(Elementaristics.MODID, "deity_storm"), null, 1729436);
    public static Deity KNIGHT = new Deity(11, new ResourceLocation(Elementaristics.MODID, "deity_knight"), null, 1845376); //Hastur
    public static Deity SUN = new Deity(12, new ResourceLocation(Elementaristics.MODID, "deity_sun"), null, 15194144);
    public static Deity HARBINGER = new Deity(13, new ResourceLocation(Elementaristics.MODID, "deity_harbinger"), null, 664599); //Nyarlathotep
    public static Deity QUEEN = new Deity(14, new ResourceLocation(Elementaristics.MODID, "deity_queen"), null, 6302785);
    public static Deity GOAT = new Deity(15, new ResourceLocation(Elementaristics.MODID, "deity_goat"), null, 7819310); //Shub
    public static Deity MOTH = new Deity(16, new ResourceLocation(Elementaristics.MODID, "deity_moth"), null, 1052431); //Moff
    public static Deity THREAD = new Deity(17, new ResourceLocation(Elementaristics.MODID, "deity_thread"), null, 13442512); //Ael
    public static Deity MIRROR = new Deity(18, new ResourceLocation(Elementaristics.MODID, "deity_mirror"), null, 2143412);
    public static Deity DANCER = new Deity(19, new ResourceLocation(Elementaristics.MODID, "deity_dancer"), null, 11700900);
    public static Deity KING = new Deity(20, new ResourceLocation(Elementaristics.MODID, "deity_king"), null, 6296600);
    public static Deity MOTHER = new Deity(21, new ResourceLocation(Elementaristics.MODID, "deity_mother"), null, 15481145);
    public static Deity MOON = new Deity(22, new ResourceLocation(Elementaristics.MODID, "deity_moon"), null, 16508803);
    public static Deity WITCH = new Deity(23, new ResourceLocation(Elementaristics.MODID, "deity_witch"), null, 15887104);

    public static Soul AIR;
    public static Soul EARTH;
    public static Soul FIRE;
    public static Soul WATER;

    public static Soul MANA;

    public static Soul DRAGON;
    public static Soul ANCIENT;

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
        AIR = new Soul("air", 51199, 0.9F, null);
        EARTH = new Soul("earth", 24320, 0.9F, null);
        FIRE = new Soul("fire", 16735232, 0.9F, null);
        WATER = new Soul("water", 223, 0.9F, null);

        MANA = new Soul("mana", 14102152, 0.4F, null);

        DRAGON = new Soul("dragon", 15316992, 0.1F, null);
        ANCIENT = new Soul("ancient", 5903, 0.1F, null);
    }

    private static void initAscensionPaths() {
        PATH_DEUS_EX_CARNE = new AscensionPath("deus_ex_carne");
        PATH_EGO_PERPETUUM = new AscensionPath("ego_perpetuum");
        PATH_FAMULUS_ENTHEATUS = new AscensionPath("famulus_entheatus");
        PATH_CREATUS_MATRIS = new AscensionPath("creatus_matris");
        PATH_DEUS_EX_MACHINA = new AscensionPath("deus_ex_machina");
    }
}
