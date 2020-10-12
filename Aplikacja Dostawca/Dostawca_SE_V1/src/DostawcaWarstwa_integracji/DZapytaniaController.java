/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;

import Controller.Controller;
import Warstwa_biznesowa.Zapytania;

/**
 *
 * @author Grzesiek
 */
public class DZapytaniaController extends Controller<Zapytania> {
   
    public DZapytaniaController(String persistence) {
        super(Zapytania.class);
        sciezka = persistence;
        query2 = "select c from Zapytania as c";

    }
   

    

    

    public Zapytania[] getZapytania() {
        return (Zapytania[]) gets().toArray(new Zapytania[0]);
    }


}
