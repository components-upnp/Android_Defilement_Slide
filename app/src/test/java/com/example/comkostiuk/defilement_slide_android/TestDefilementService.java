package com.example.comkostiuk.defilement_slide_android;

import org.fourthline.cling.model.action.ActionArgumentValue;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by comkostiuk on 20/04/2017.
 *
 * Cette suite de test permet de tester le bon fonctionnement du service
 * Attention: il faut préalablement démarrer l'application sur le terminal Android
 */


//On exécute les tests dans l'ordre à cause du testArret qui doit être produit en dernier
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDefilementService {

    Susbcription sub;

    @Before
    public void setUp() {
        sub = new Susbcription("DefilementController");
        sub.run();
        pause(7000);
        sub.executeAction("SetDirection",
                "NewDIrectionValue",
                "AUCUN");
    }


    @Test
    public void testServiceConnecte() {
        assertTrue(sub.isDeviceConnecte());
    }


    @Test
    public void testValInit() {
        pause(2000);
        ArrayList<Object> res = sub.executeAction("GetDirection",null,null);
        pause(2000);
        ActionArgumentValue r = (ActionArgumentValue) res.get(0);
        assertEquals("AUCUN", r.toString());
    }




    @Test
    public void testDroite() {
        pause(2000);
        sub.executeAction("SetDirection",
                "NewDIrectionValue",
                "DROITE");
        pause(2000);
        ArrayList<Object> res = sub.executeAction("GetDirection",null,null);
        pause(2000);
        ActionArgumentValue r = (ActionArgumentValue) res.get(0);
        assertEquals("Slide suivante", r.toString());
    }

    @Test
    public void testGauche() {
        pause(2000);
        sub.executeAction("SetDirection",
                "NewDIrectionValue",
                "GAUCHE");
        pause(2000);
        ArrayList<Object> res = sub.executeAction("GetDirection",null,null);
        pause(2000);
        ActionArgumentValue r = (ActionArgumentValue) res.get(0);
        assertEquals("Slide précédente", r.toString());
    }

    @Test
    public void testCommandeInconnue() {
        pause(2000);
        sub.executeAction("SetDirection",
                "NewDIrectionValue",
                "CTRE");
        pause(2000);
        ArrayList<Object> res = sub.executeAction("GetDirection",null,null);
        pause(2000);
        ActionArgumentValue r = (ActionArgumentValue) res.get(0);
        assertEquals("AUCUN", r.toString());
    }

    @Test
    public void testZarret() {
        pause(2000);
        sub.executeAction("SetDirection",
                "NewDIrectionValue",
                "CENTRE");
        pause(2000);
        assertFalse(sub.isDeviceConnecte());
    }

    //Permet de mettre l'exécution en pause, afin d'avoir le temps de recevoir les évènements
    public static void pause(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
