package com.mtsa.geniusnerd;


import com.mtsa.geniusnerd.AndGraph.AGGameManager;
import com.mtsa.geniusnerd.AndGraph.AGInputManager;
import com.mtsa.geniusnerd.AndGraph.AGScene;
import com.mtsa.geniusnerd.AndGraph.AGScreenManager;
import com.mtsa.geniusnerd.AndGraph.AGSprite;
import com.mtsa.geniusnerd.AndGraph.AGTimer;

/**
 * Created by mathe on 13/12/2017.
 */

public class Splash extends AGScene {

    AGSprite insect = null;
    AGTimer timeSplash = null;

    Splash(AGGameManager manager) {
        super(manager);
    }

    //Quando a aplicação abre
    @Override
    public void init() {
        setSceneBackgroundColor(1, 1, 1);
        timeSplash = new AGTimer(1500);

        insect = createSprite(R.mipmap.insectgames, 1, 1);
        insect.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
    }

    //Quando pára por algum motivo (ligação, aplicação minimizada, etc.)
    @Override
    public void stop() {

    }

    //Quando volta para o primeiro plano
    @Override
    public void restart() {

    }

    //Funcionamento normal
    @Override
    public void loop() {
        //Tem que atualizar o contador de tempo, senão ele nunca muda.
        timeSplash.update();

        if (AGInputManager.vrTouchEvents.screenClicked()) {
            this.vrGameManager.setCurrentScene(1);
            return;
        }

        if (timeSplash.isTimeEnded()) {
            this.vrGameManager.setCurrentScene(1);
            return;
        }

    }
}
