/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wyniki;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zesio
 */
public class Zapis_wynikow {
    
 public void Zapis (long Czas_odebrania_sklep,long Czas_wyslania_dostawca, long cz_odebrania,long koniect2,long cz_wyslania_odklienta, String wynik){

     try {
         if( cz_odebrania!= 0 || cz_wyslania_odklienta!=0){ 
       //  System.out.println("Czas odebrania " + wynik +" zamowienia: " +cz_odebrania);
        // System.out.println("Czas t1: " + (cz_odebrania - cz_wyslania_odklienta)+"  "+ wynik +" zamowienia: ");
       //  System.out.println("Czas t2: " + (koniect2-cz_odebrania)+"  "+ wynik +" zamowienia: ");
       //  System.out.println("Czas wysłania odpowiedzi: " + cz_wyslania +"  "+ wynik +" zamowienia: "  );
         utworzPlik("D:/t1.txt");
         utworzPlik("D:/t2.txt");
         
         FileWriter file = new FileWriter("D:/t1.txt", true);
         FileWriter file1 = new FileWriter("D:/t2.txt", true);
         file.write(System.getProperty("line.separator"));
         file1.write(System.getProperty("line.separator"));
         file.write("" + (cz_odebrania - cz_wyslania_odklienta)+ " ");
         file1.write("" + (koniect2-cz_odebrania)+ " ");
         file.close();
         file1.close();}
         else {
      
          //   System.out.print("Czas otrzymania odpowiedzi synchronicznie: " + Czas_odpowiedzi_sklep+ "  "+ wynik+ " zamowienia"+ "\n");
   //   System.out.print("Czas wyslania odpowiedzi synchronicznie: " + Czas_wyslania_sklep +"  "+ wynik+ " zamowienia" + "\n");
    //  System.out.print("Czas t3 " + (Czas_odpowiedzi_sklep -Czas_wyslania_sklep)+"  "+ wynik+ " zamowienia" );
      utworzPlik("Z:/t3.txt");        
FileWriter file = new FileWriter("Z:/t3.txt", true);

file.write(System.getProperty("line.separator"));
file.write(""+ (Czas_odebrania_sklep -Czas_wyslania_dostawca) +" ");

 
file.close();

         
         
         }
     } catch (IOException ex) {
         Logger.getLogger(Zapis_wynikow.class.getName()).log(Level.SEVERE, null, ex);
     } 
        
           
      
     }
 
  public void Zapis_rozmiaru (long rozmiar) throws IOException
  {
   utworzPlik("Z:/rozmiar.txt");        
FileWriter file = new FileWriter("Z:/rozmiar.txt", true);

file.write(System.getProperty("line.separator"));
file.write(""+ rozmiar);

 
file.close();
  
  
  
  }
 
            public void utworzPlik(String sciezka){
          File plik = new File(sciezka);
              if( (plik.isFile() == true)) {
           // System.out.println("plik istnieje");
        }
        else{
            try{
                boolean b = plik.createNewFile();
                
            }
            
            catch(IOException e){
                System.out.println("Nie można utworzyć pliku");
            }
        } 
         
        
     
            }}
     
