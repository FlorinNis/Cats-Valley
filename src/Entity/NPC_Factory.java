package Entity;

import Main.GamePanel;

public class NPC_Factory {

    public Entity getNPC(NPC_Type type, GamePanel gp) {

        return switch (type) {
            case BOY -> new NPC_boy(gp);
            case PisicaSTART -> new NPC_Pisica_1(gp);
            case PisicaCASA -> new NPC_Pisica_2(gp);
            case dungeonKeeper -> new NPC_Pisica_3(gp);
        };
    }
}
