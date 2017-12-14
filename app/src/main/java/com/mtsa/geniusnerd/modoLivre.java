package com.mtsa.geniusnerd;


import com.mtsa.geniusnerd.AndGraph.AGGameManager;
import com.mtsa.geniusnerd.AndGraph.AGInputManager;
import com.mtsa.geniusnerd.AndGraph.AGScene;
import com.mtsa.geniusnerd.AndGraph.AGScreenManager;
import com.mtsa.geniusnerd.AndGraph.AGSoundManager;
import com.mtsa.geniusnerd.AndGraph.AGSprite;

/**
 * Created by mathe on 13/12/2017.
 */

public class modoLivre extends AGScene {

    private AGSprite img_genius = null;

    private AGSprite img_qVermelho = null;
    private AGSprite img_qVerde = null;
    private AGSprite img_qAzul = null;
    private AGSprite img_qAmarelo = null;

    private int sound_DO = 0;
    private int sound_RE = 0;
    private int sound_MI = 0;
    private int sound_FA = 0;


    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public modoLivre(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {
        criaTela();
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
        if (AGInputManager.vrTouchEvents.screenClicked()) {

            if (img_qVermelho.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_DO);
                img_qVermelho.fadeIn(1000);
                return;
            }
            if (img_qAmarelo.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_RE);
                img_qAmarelo.fadeIn(1000);
                return;
            }
            if (img_qAzul.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_MI);
                img_qAzul.fadeIn(1000);
                return;
            }
            if (img_qVerde.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_FA);
                img_qVerde.fadeIn(1000);
                return;
            }
        }

    }

    private void criaTela() {
        setSceneBackgroundColor(1, 1, 1);

        /**
         * Carrega componentes do canto VERMELHO
         */
        img_qVermelho = createSprite(R.mipmap.vermelho3, 1, 1);
        img_qVermelho.setScreenPercent(40, 40);
        img_qVermelho.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) + 450);
        sound_DO = AGSoundManager.vrSoundEffects.loadSoundEffect("DO.wav");

        /**
         * Carrega componentes do canto AMARELO
         */
        img_qAmarelo = createSprite(R.mipmap.amarelo3, 2, 1);
        img_qAmarelo.setScreenPercent(40, 40);
        img_qAmarelo.vrPosition.setXY((AGScreenManager.iScreenWidth / 2) + 315, (AGScreenManager.iScreenHeight / 2));
        sound_RE = AGSoundManager.vrSoundEffects.loadSoundEffect("RE.wav");

        /**
         * Carrega componentes do canto AZUL
         */
        img_qAzul = createSprite(R.mipmap.azul3, 2, 1);
        img_qAzul.setScreenPercent(40, 40);
        img_qAzul.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) - 450);
        sound_MI = AGSoundManager.vrSoundEffects.loadSoundEffect("MI.wav");

        /**
         * Carrega componentes do canto VERDE
         */
        img_qVerde = createSprite(R.mipmap.verde3, 2, 1);
        img_qVerde.setScreenPercent(40, 40);
        img_qVerde.vrPosition.setXY((AGScreenManager.iScreenWidth / 2) - 315, (AGScreenManager.iScreenHeight / 2));
        sound_FA = AGSoundManager.vrSoundEffects.loadSoundEffect("FA.wav");

        /**
         * Carrega componentes do CENTRO
         */

        img_genius = createSprite(R.mipmap.genius, 1, 1);
        img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));

    }
}
