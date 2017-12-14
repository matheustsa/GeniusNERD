package com.mtsa.geniusnerd;

import com.mtsa.geniusnerd.AndGraph.AGGameManager;
import com.mtsa.geniusnerd.AndGraph.AGInputManager;
import com.mtsa.geniusnerd.AndGraph.AGScene;
import com.mtsa.geniusnerd.AndGraph.AGScreenManager;
import com.mtsa.geniusnerd.AndGraph.AGSprite;

/**
 * Created by mathe on 13/12/2017.
 */

public class Sobre extends AGScene {

    AGSprite mtsacorp = null;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public Sobre(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        mtsacorp = createSprite(R.mipmap.mtsa, 1, 1);
        mtsacorp.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void loop() {

        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.setCurrentScene(1);
            return;
        }
    }
}
