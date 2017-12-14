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

public class NewGame extends AGScene {

    AGSprite img_genius = null;
    AGSprite bt_modoDesafio = null;
    AGSprite bt_modoLivre = null;
    AGSprite bt_sobre = null;

    int sound_click = 0;

    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public NewGame(AGGameManager pManager) {
        super(pManager);
    }


    /**
     * Chamado sempre que a tela for iniciada
     */
    @Override
    public void init() {
        carregaTela();
    }

    /**
     * Chamado quando a aplicação sofre uma interrupção
     * Ex: Aplicação minimizada, Chamada recebida, etc
     */
    @Override
    public void stop() {

    }

    /**
     * Chamado quando a aplicação volta da interrupção
     */
    @Override
    public void restart() {

    }

    /**
     * Chamado conforme atualização da tela
     */
    @Override
    public void loop() {
        /**
         * Fechar aplicativo ao pressionar o botão de voltar
         */
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            System.exit(0);
            return;
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {

            /**
             * Muda para a tela do MODO DESAFIO
             */
            if (bt_modoDesafio.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                vrGameManager.setCurrentScene(2);
                AGSoundManager.vrSoundEffects.play(sound_click);
                return;
            }

            /**
             * Muda para a tela do MODO LIVRE
             */
            if (bt_modoLivre.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                vrGameManager.setCurrentScene(3);
                AGSoundManager.vrSoundEffects.play(sound_click);
                return;
            }

            /**
             * Muda para a tela SOBRE
             */
            if (bt_sobre.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                vrGameManager.setCurrentScene(4);
                AGSoundManager.vrSoundEffects.play(sound_click);
                return;
            }

        }
    }

    /**
     * Carrega componentes visuais da tela
     */
    private void carregaTela() {
        setSceneBackgroundColor(0, 0, 0.2f);

        img_genius = createSprite(R.mipmap.genius_logo, 1, 1);
        img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) + 400);

        bt_modoDesafio = createSprite(R.mipmap.button_modo_desafio, 1, 1);
        bt_modoDesafio.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) - 100);

        bt_modoLivre = createSprite(R.mipmap.button_modo_livre, 1, 1);
        bt_modoLivre.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) - 300);

        bt_sobre = createSprite(R.mipmap.button_sobre, 1, 1);
        bt_sobre.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) - 550);

        sound_click = AGSoundManager.vrSoundEffects.loadSoundEffect("click.wav");

    }
}
