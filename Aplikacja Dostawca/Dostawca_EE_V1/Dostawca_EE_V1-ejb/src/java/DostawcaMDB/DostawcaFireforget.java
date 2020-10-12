/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaMDB;
import Dostawcabusiness_tier.Odbior;
import DostawcaWarstwa_integracji.TBase;
import Sklep.SPozycja_zamowienia;
import Sklep.SZamowienie;
import Warstwa_biznesowa.Fasada;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import wyniki.Zapis_wynikow;

/**
 *
 * @author Grzesiek
 */
/*
@MessageDriven(mappedName = "jms/Topic", activationConfig = {
 //@ActivationConfigProperty(propertyName = "addressList", propertyValue = "192.168.0.12"),
 @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
 @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
 //     , @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
 // , @ActivationConfigProperty(propertyName = "clientId", propertyValue = "ReplyMsgBean")
 //  , @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "ReplyMsgBean")
 }
 )

 public class DostawcaFireforget implements MessageListener {

 static final Logger logger = Logger.getLogger("Fireforget");
 @Resource
 private MessageDrivenContext mdc;
 Fasada fasada = new Fasada();
 TBase base = new TBase(fasada);
 @Resource(lookup = "jms/JupiterConnectionFactory")
 public ConnectionFactory connectionFactory;
 @Resource(lookup = "jms/Queue")
 public Queue queue;
 @Resource(lookup = "jms/Reply")
 public Queue replyqueue;
 Connection con = null;

 public DostawcaFireforget() {
 }

 /**
 * onMessage method, declared as public (but not final or static), with a
 * return type of void, and with one argument of type javax.jms.Message.
 *
 * Casts the incoming Message to a TextMessage and displays the text.
 *
 * @param inMessage the incoming message
 */
/*
 long koniect2 = 0;
 long Czas_odebrania = 0;
 @Override
 public void onMessage(Message inMessage) {
 
     Odbior odbior = new Odbior();
    Zapis_wynikow zapis = new Zapis_wynikow();   
 Connection connection = null;
 Session session = null;
 MessageProducer publisher = null;
 TextMessage reply1 = null;
 ObjectMessage objectmessage  = null;
 ObjectMessage message  = null;
   
 try {
                    connection = connectionFactory.createConnection();
                    session = connection.createSession(false, 0);
                } catch (Exception e) {
                    System.err.println("Problem z polaczeniem: " + e.toString());

                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (JMSException ee) {
                        }
                    }

                    System.exit(1);
                }
 
 

 try{
 
 if (inMessage != null) {

//System.out.println(
//"Otrzymano komunikat asynchronicznie: ...");
//System.out.println("Czas wysłania przez klienta: " + inMessage.getJMSTimestamp());
Czas_odebrania = System.currentTimeMillis();
String wynik =     odbior.OdbiorZamowienia(inMessage);                            

koniect2 = odbior.getkoniect2(); 
String numer = odbior.getnumer();
                            
 
 
 
 
 if (        !"Otrzymano test".equals(wynik)){
          
 publisher = session.createProducer(inMessage.getJMSReplyTo());
 if (wynik == "Brak klienta"){
 //reply1 = session.createTextMessage("Brak klienta w bazie");
 }
 else if (wynik == "Istnieje"){
 //reply1 = session.createTextMessage("Zamówienie o podanym numerze już istnieje w bazie");
 }
 else if (wynik== "Dodano zamowienie"){
 //reply1 = session.createTextMessage("Zamówienie zostało przekazane do realizacji");}
  message = (ObjectMessage) inMessage;
  Serializable objectData = message.getObject();
 //reply1.setJMSCorrelationID(inMessage.getJMSMessageID());
  objectmessage = session.createObjectMessage(objectData);
  objectmessage.setJMSCorrelationID(inMessage.getJMSMessageID());
   
 publisher.send(objectmessage);
 //long Czas_wyslania = System.currentTimeMillis();
 zapis.Zapis(0,0, Czas_odebrania,koniect2, inMessage.getJMSTimestamp(), numer);
 //System.out.print("Czas odebrania: " +Czas_odebrania + "Koniect2: " + koniect2);
         
               
 }//else { System.out.println("test asynchronicznie");}   
 }          
 }} catch (JMSException ex) {
 Logger.getLogger(DostawcaFireforget.class.getName()).log(Level.SEVERE, null, ex);
 } 
 }
    
           
           
  
         
              
 
 }
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
           
 /*
 ObjectMessage msg;
 TextMessage reply1 = null;
 TextMessage reply2 = null;
 TextMessage test = null;
 Session ses = null;
 MessageProducer producer = null;
 Queue kolejka;
 //  ObjectMessage replyMsg = null;
           
 try {
 if (inMessage instanceof TextMessage){
           
 test = (TextMessage) inMessage;
 logger.info(test.getText());
           
 Connection connection = null;
 Session session = null;
 MessageProducer publisher = null;
 //     replyqueue = test.getJMSReplyTo();
 connection = connectionFactory.createConnection();
 session = connection.createSession(false, 0);
           
 // logger.info(test.getJMSReplyTo().toString());
 reply1 = session.createTextMessage("Test1");
 reply2 = session.createTextMessage("Test2");
 reply1.setJMSCorrelationID(test.getJMSMessageID());
 reply2.setJMSCorrelationID(test.getJMSMessageID());
 publisher = session.createProducer(test.getJMSReplyTo());
           
 //   logger.info(reply1.getJMSCorrelationID().toString());
 publisher.send(reply1);
 publisher.send(reply2);
 logger.info("Wysłano potwierdzenie otrzymania komunikatu");
           
 }
           
 else if (inMessage instanceof ObjectMessage){
 msg = (ObjectMessage) inMessage;
 Serializable objectData = msg.getObject();
           
 if(objectData instanceof Zapytania){
 logger.info("Otrzymano komunikat klasy: Zapytania");
 base.update_data();
 Zapytania zapytanie1 = new Zapytania();
 Zapytania zapytanie = (Zapytania) objectData;
 Klient klient = new Klient();
 klient.setPesel("1");
 klient =  fasada.Szukajklienta(klient);
 zapytanie1.setKlient(klient);
 zapytanie1.setProdukt_Dostawca(zapytanie.getProdukt_Dostawca());
 zapytanie1.setTresc_zapytania(zapytanie.getTresc_zapytania());
 zapytanie1.setData1(zapytanie.getData());
           
 String wynik =     fasada.Dodajzapytanie(zapytanie1);
 logger.info(wynik);
 base.dodaj_zapytania();
 base.update_data();
 }
           
 if(objectData instanceof Zamowienie){
 long Czas_odebrania = System.currentTimeMillis();
 System.out.println("Czas odebrania: " +Czas_odebrania);
 // System.out.println("Deliverytime: " +inMessage.getJMSTimestamp());
 System.out.println("Czas t1: " + (Czas_odebrania - inMessage.getJMSTimestamp()));
 logger.info("Otrzymano zamówienie od klienta");
           
 base.update_data();
 Zamowienie zamowienie = new Zamowienie();
 zamowienie      = (Zamowienie) objectData;
 String wynik = fasada.dodaj_zamowienie1("1", zamowienie.getNr_zamówienia());
 System.out.println(wynik);
 base.dodaj_zamowienia();
           
 List<Pozycja_zamowienia> pozycje = null;
 pozycje = zamowienie.getPozycje();
           
 for (Pozycja_zamowienia pozycja : pozycje) {
 String nip = pozycja.getMProduktDostawcy().getMdostawca().getNIP();
 String klient = "1";
 String data[] = {pozycja.getMProduktDostawcy().getMprodukt().getNazwa(),pozycja.getMProduktDostawcy().getMprodukt().getProducent(),pozycja.getMProduktDostawcy().getMprodukt().getKategoria()};
 fasada.dodaj_pozycje_zamowienia(nip, data, klient, zamowienie.getNr_zamówienia(), pozycja.getIlosc());
 }
 base.dodaj_pozycje_zamowien();
 base.update_data();
           
 Connection connection = null;
 Session session = null;
 MessageProducer publisher = null;
           
 connection = connectionFactory.createConnection();
 session = connection.createSession(false, 0);
 publisher = session.createProducer(msg.getJMSReplyTo());
 if (wynik == "Brak klienta"){
 reply1 = session.createTextMessage("Brak klienta w bazie");
 }
 else if (wynik == "Istnieje"){
 reply1 = session.createTextMessage("Zamówienie o podanym numerze już istnieje w bazie");
 }
 else{
 reply1 = session.createTextMessage("Zamówienie zostało przekazane do realizacji");}
           
 reply1.setJMSCorrelationID(inMessage.getJMSMessageID());
           
 publisher.send(reply1);
 long Czas_wyslania = System.currentTimeMillis();
 System.out.println("Czas wysłania: " + Czas_wyslania );
 System.out.println("Czas t2: " + (Czas_wyslania-Czas_odebrania));
 logger.info("Wysłano potwierdzenie otrzymania Zamówienia");
 utworzPlik();
 FileWriter file = new FileWriter("D:/t1.txt", true);
 FileWriter file1 = new FileWriter("D:/t2.txt", true);
           
 file.write(System.getProperty("line.separator"));
 file1.write(System.getProperty("line.separator"));
 file.write("" + (Czas_odebrania - inMessage.getJMSTimestamp())+ " ");
 file1.write("" + (Czas_wyslania-Czas_odebrania)+ " ");
           
 file.close();
 file1.close();
           
 }
 }
 else {
 logger.warning(
 "Komunikat niewłaściwego typu: "
 + inMessage.getClass().getName());
 }
           
           
 } catch (JMSException e) {
 logger.severe(
 "ReplyMsgBean.onMessage: Wyjątek JMSException: " + e.toString());
 } catch (Throwable te) {
 logger.severe(
 "ReplyMsgBean.onMessage: Wyjątek: " + te.toString());
 }*/
