package com.mtsa.geniusnerd;


import com.mtsa.geniusnerd.AndGraph.AGGameManager;
import com.mtsa.geniusnerd.AndGraph.AGInputManager;
import com.mtsa.geniusnerd.AndGraph.AGScene;
import com.mtsa.geniusnerd.AndGraph.AGScreenManager;
import com.mtsa.geniusnerd.AndGraph.AGSoundManager;
import com.mtsa.geniusnerd.AndGraph.AGSprite;
import com.mtsa.geniusnerd.AndGraph.AGTimer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mathe on 13/12/2017.
 */

public class modoDesafio extends AGScene {

    private final Random random = new Random();
    AGTimer timer = null;
    private AGSprite img_genius = null;
    private AGSprite img_right = null;
    private AGSprite img_wrong = null;
    private AGSprite img_repeat = null;
    private AGSprite img_ready = null;
    private AGSprite img_qVermelho = null;
    private AGSprite img_qVerde = null;
    private AGSprite img_qAzul = null;
    private AGSprite img_qAmarelo = null;
    private int sound_DO = 0;
    private int sound_RE = 0;
    private int sound_MI = 0;
    private int sound_FA = 0;
    private int sound_right = 0;
    private int sound_wrong = 0;
    private int nivel = 1;
    private int[] sequencia_jogo = null;
    private ArrayList<Integer> sequencia_jogo2 = new ArrayList<>();
    private ArrayList<Integer> sequencia_jogador = new ArrayList<>();
    private ArrayList<Integer> sequencia_jogo_aux = new ArrayList<>();
    private int count_jogador = 0;
    private int numero_sequencia = 0;


    /*******************************************
     * Name: CAGScene()
     * Description: Scene construtor
     * Parameters: CAGameManager
     * Returns: none
     *****************************************
     * @param pManager*/
    public modoDesafio(AGGameManager pManager) {
        super(pManager);
    }

    @Override
    public void init() {

        timer = new AGTimer(1000);

        criaTela();
        configuraJogo2();
    }

    @Override
    public void stop() {

    }

    @Override
    public void restart() {
        criaTela();
        configuraJogo2();
    }

    @Override
    public void loop() {


        verificaClique();
        verificaSequencia();

    }

    private void verificaClique() {
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.setCurrentScene(1);
            sequencia_jogo2.clear();
            sequencia_jogador.clear();
            sequencia_jogo_aux.clear();
            return;
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) {

            if (img_qVermelho.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_DO);
                img_qVermelho.fadeIn(1500);
                sequencia_jogador.add(1);
                count_jogador++;
                System.out.println("count: " + count_jogador);
                System.out.println("nivel: " + nivel);

            }
            if (img_qAmarelo.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_RE);
                img_qAmarelo.fadeIn(1500);
                sequencia_jogador.add(2);
                count_jogador++;
                System.out.println("count: " + count_jogador);
                System.out.println("nivel: " + nivel);
            }
            if (img_qAzul.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_MI);
                img_qAzul.fadeIn(1500);
                sequencia_jogador.add(3);
                count_jogador++;
                System.out.println("count: " + count_jogador);
                System.out.println("nivel: " + nivel);
            }
            if (img_qVerde.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrSoundEffects.play(sound_FA);
                img_qVerde.fadeIn(1500);
                sequencia_jogador.add(4);
                count_jogador++;
                System.out.println("count: " + count_jogador);
                System.out.println("nivel: " + nivel);
            }
            if (img_repeat.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                img_repeat.fadeOut(1000);
                img_genius = createSprite(R.mipmap.genius, 1, 1);
                img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
                img_genius.fadeIn(1000);
            }
            if (img_ready.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                img_ready.fadeOut(1000);
                img_genius = createSprite(R.mipmap.genius, 1, 1);
                img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
                img_genius.fadeIn(1000);

                try {
                    newRound2();
                    System.out.println("NEW ROUND!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("CAN'T START NEW ROUND!");
                }
            }
        }// SCREEN CLICK
    }

    private void verificaSequencia() {
        if (count_jogador == nivel) {
            for (int i = 0; i < nivel; i++) {
                sequencia_jogo_aux.add(sequencia_jogo2.get(i));
            }
            if (sequencia_jogador.equals(sequencia_jogo_aux)) {
                try {
                    AGSoundManager.vrSoundEffects.play(sound_right);
                    count_jogador = 0;
                    nivel++;
                    sequencia_jogador.clear();
                    sequencia_jogo_aux.clear();
                    System.out.println("SEQUENCIA CORRETA");
                    Thread.sleep(1000);
                    newRound2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("ERRO NA VERIFICAÇÃO");
                }
            } else {
                try {
                    AGSoundManager.vrSoundEffects.play(sound_wrong);
                    count_jogador = 0;
                    sequencia_jogador.clear();
                    sequencia_jogo_aux.clear();
                    System.out.println("SEQUENCIA ERRADA");
                    Thread.sleep(500);
                    newRound2();
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("ERRO NA VERIFICAÇÃO");
                }
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
        img_right = createSprite(R.mipmap.button_right, 1, 1);
        img_right.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_wrong = createSprite(R.mipmap.button_wrong, 1, 1);
        img_wrong.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_repeat = createSprite(R.mipmap.button_repeat, 1, 1);
        img_repeat.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_genius = createSprite(R.mipmap.genius, 1, 1);
        img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_ready = createSprite(R.mipmap.button_ready, 1, 1);
        img_ready.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        sound_right = AGSoundManager.vrSoundEffects.loadSoundEffect("right.wav");
        sound_wrong = AGSoundManager.vrSoundEffects.loadSoundEffect("wrong.wav");
    }

    private void criaTela2() {
        setSceneBackgroundColor(1, 1, 1);

        /**
         * Carrega componentes do canto VERMELHO
         */
        img_qVermelho = createSprite(R.mipmap.vermelho, 2, 1);
        img_qVermelho.setScreenPercent(40, 40);
        img_qVermelho.addAnimation(5, false, 0, 1);
        img_qVermelho.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) + 450);
        sound_DO = AGSoundManager.vrSoundEffects.loadSoundEffect("DO.wav");

        /**
         * Carrega componentes do canto AMARELO
         */
        img_qAmarelo = createSprite(R.mipmap.amarelo, 2, 1);
        img_qAmarelo.setScreenPercent(40, 40);
        img_qAmarelo.addAnimation(5, false, 0, 1);
        img_qAmarelo.vrPosition.setXY((AGScreenManager.iScreenWidth / 2) + 315, (AGScreenManager.iScreenHeight / 2));
        sound_RE = AGSoundManager.vrSoundEffects.loadSoundEffect("RE.wav");

        /**
         * Carrega componentes do canto AZUL
         */
        img_qAzul = createSprite(R.mipmap.azul, 2, 1);
        img_qAzul.setScreenPercent(40, 40);
        img_qAzul.addAnimation(5, false, 0, 1);
        img_qAzul.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2) - 450);
        sound_MI = AGSoundManager.vrSoundEffects.loadSoundEffect("MI.wav");

        /**
         * Carrega componentes do canto VERDE
         */
        img_qVerde = createSprite(R.mipmap.verde, 2, 1);
        img_qVerde.setScreenPercent(40, 40);
        img_qVerde.addAnimation(5, false, 0, 1);
        img_qVerde.vrPosition.setXY((AGScreenManager.iScreenWidth / 2) - 315, (AGScreenManager.iScreenHeight / 2));
        sound_FA = AGSoundManager.vrSoundEffects.loadSoundEffect("FA.wav");

        /**
         * Carrega componentes do CENTRO
         */
        img_right = createSprite(R.mipmap.button_right, 1, 1);
        img_right.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_wrong = createSprite(R.mipmap.button_wrong, 1, 1);
        img_wrong.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_repeat = createSprite(R.mipmap.button_repeat, 1, 1);
        img_repeat.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_genius = createSprite(R.mipmap.genius, 1, 1);
        img_genius.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        img_ready = createSprite(R.mipmap.button_ready, 1, 1);
        img_ready.vrPosition.setXY((AGScreenManager.iScreenWidth / 2), (AGScreenManager.iScreenHeight / 2));
        sound_right = AGSoundManager.vrSoundEffects.loadSoundEffect("right.wav");
        sound_wrong = AGSoundManager.vrSoundEffects.loadSoundEffect("wrong.wav");
    }

    private void configuraJogo2() {
        timer = new AGTimer(1500);

        for (int i = 0; i < 99; i++) {
            sequencia_jogo2.add(random.nextInt(4) + 1);
        }

        System.out.println("===========================================================");
        for (int i = 0; i < sequencia_jogo2.size(); i++) {
            System.out.print(" " + sequencia_jogo2.get(i));
        }
        System.out.println("\n===========================================================");
    }

    private void configuraJogo() {
        sequencia_jogo = new int[99];

        for (int i = 0; i < sequencia_jogo.length; i++) {
            numero_sequencia = random.nextInt(4) + 1; // +1 pq vai de 0 a 3
            sequencia_jogo[i] = numero_sequencia;
        }

        System.out.println("===========================================================");
        for (int i = 0; i < sequencia_jogo.length; i++) {
            System.out.print(" " + sequencia_jogo[i]);

        }
        System.out.println("===========================================================");
    }

    private void newRound2() throws InterruptedException {
        for (int i = 0; i < nivel; i++) {
            Thread.sleep(1000);
            timer.restart();
            switch (sequencia_jogo2.get(i)) {
                case 1:
//                    timer.update();
//                    System.out.println("timer: "+timer.getCurrentTime());
                    img_qVermelho.fadeIn(1000);
                    img_qVermelho.fadeOut(2000);
                    img_qVermelho.fadeIn(1000);
                    AGSoundManager.vrSoundEffects.play(sound_DO);
                    break;

                case 2:
                    timer.update();
                    System.out.println("timer: " + timer.getCurrentTime());
                    img_qAmarelo.fadeIn(1000);
                    img_qAmarelo.fadeOut(2000);
                    img_qAmarelo.fadeIn(1000);
                    AGSoundManager.vrSoundEffects.play(sound_DO);
//                    img_qAmarelo.fadeIn(1000);
                    break;
                case 3:
                    timer.update();
                    System.out.println("timer: " + timer.getCurrentTime());
                    img_qAzul.fadeIn(1000);
                    img_qAzul.fadeOut(2000);
                    img_qAzul.fadeIn(1000);
                    AGSoundManager.vrSoundEffects.play(sound_DO);
//                    img_qAzul.fadeIn(1000);
                    break;
                case 4:
                    timer.update();
                    System.out.println("timer: " + timer.getCurrentTime());
                    img_qVerde.fadeIn(1000);
                    img_qVerde.fadeOut(2000);
                    img_qVerde.fadeIn(1000);
                    AGSoundManager.vrSoundEffects.play(sound_DO);
//                    img_qVerde.fadeIn(1000);
                    break;
            }// SWITCH
        }
    }

    private void newRound() throws InterruptedException {
        for (int i = 2; i < sequencia_jogo.length; i++) {
            if (nivel <= i) {
                switch (sequencia_jogo[i]) {
                    case 1:
                        img_qVermelho.fadeIn(1000);
                        AGSoundManager.vrSoundEffects.play(sound_DO);
                        Thread.sleep(1000);
                        break;
                    case 2:
                        img_qAmarelo.fadeIn(1000);
                        AGSoundManager.vrSoundEffects.play(sound_RE);
                        Thread.sleep(1000);
                        break;
                    case 3:
                        img_qAzul.fadeIn(1000);
                        AGSoundManager.vrSoundEffects.play(sound_MI);
                        Thread.sleep(1000);
                        break;
                    case 4:
                        img_qVerde.fadeIn(1000);
                        AGSoundManager.vrSoundEffects.play(sound_FA);
                        Thread.sleep(1000);
                        break;
                    default:
                        break;
                }// SWITCH
            }// IF
        }// FOR
    }// NEWROUND()
}// MAIN
