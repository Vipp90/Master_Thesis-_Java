/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rozmiar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Rozmiar_obiektow_serializowanych {
     public static long rozmiar(Serializable obiekt) {
        if (obiekt == null) {
            return 0;
        }
        try {
            ByteArrayOutputStream bufor = new ByteArrayOutputStream();
            new ObjectOutputStream(bufor).writeObject(obiekt);
            bufor.close();
            return bufor.size();
        } catch (IOException e) {
            System.out.println("Blad: "+e);
        }
        return -1;
    }
}
