package de.aelpecyem.elementaristics.proxy;


import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.client.handler.HUDHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public void giveVision(String visionName) {
        String res = Elementaristics.MODID + ":textures/visions/" + visionName + ".png";
        if (!HUDHandler.current_vision.equals(res)) {
            HUDHandler.vision_progress = 0;
            HUDHandler.current_vision = res;
        }
    }
}
