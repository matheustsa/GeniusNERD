//Aplication package
package com.mtsa.geniusnerd;

//Used Packages

import android.os.Bundle;

import com.mtsa.geniusnerd.AndGraph.AGActivityGame;
import com.mtsa.geniusnerd.AndGraph.AGGameManager;

public class Principal extends AGActivityGame {
    public void onCreate(Bundle saved) {
        super.onCreate(saved);

        /********************************************
         * Name: AGActivityManager
         * Parameters: Activity utilizada, boolean (utiliza acelerômetro?)
         ******************************************/
        this.vrManager = new AGGameManager(this, false);

        Splash splash = new Splash(vrManager);
        NewGame newGame = new NewGame(vrManager);
        modoDesafio modoDesafio = new modoDesafio(vrManager);
        modoLivre modoLivre = new modoLivre(vrManager);
        Sobre sobre = new Sobre(vrManager);

        vrManager.addScene(splash); // Nº da cena: 0
        vrManager.addScene(newGame); // Nº da cena: 1
        vrManager.addScene(modoDesafio); // Nº da cena: 2
        vrManager.addScene(modoLivre); // Nº da cena: 3
        vrManager.addScene(sobre); // Nº da cena: 4

    }
}
