/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import Sklep.SDostawca;
import Sklep.SProdukt_Dostawca;
import Sklep.SZamowienie;
import Sklep.SZapytania;

import Warstwa_biznesowa.Fasada;

import Warstwa_integracji.TBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import rozmiar.Rozmiar_obiektow_serializowanych;
import wyniki.Zapis_wynikow;

/**
 *
 * @author Grzesiek
 */
@Stateless
public class messagebean implements messagebeanRemote {

    Fasada fasada = new Fasada();
    Zapis_wynikow zapis = new Zapis_wynikow();
    TBase base = new TBase(fasada);
    @Resource(mappedName = "jms/Queue")
    private Queue queue;
    @Resource(mappedName = "jms/Topic")
    private Topic topic;
    @Resource(mappedName = "jms/Reply")
    private Queue replyqueue;
    @Resource(mappedName = "jms/JupiterConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/ConnectionFactory")
    private ConnectionFactory queueconnectionFactory;
    @Resource(mappedName = "jms/TopicConnectionFactory")
    private ConnectionFactory topicconnectionFactory;

    @Override
    public void update_data() {
        base.update_data();
    }

    public void Zapytanieoprodukt(int wybrany, String dane_dostawcy, String tresczapytania) {
        SDostawca dostawca = new SDostawca();
        dostawca = fasada.Szukajdostawcy(dane_dostawcy);

        SProdukt_Dostawca produktdostawca = new SProdukt_Dostawca();
        produktdostawca = dostawca.getProduktydostawcy().get(wybrany);
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        GregorianCalendar data1 = new GregorianCalendar();
        data1.setTime(data);

        SZapytania zapytanie = new SZapytania();
        zapytanie.setDostawca(dostawca);
        zapytanie.setProdukt_Dostawca(produktdostawca);
        zapytanie.setTresc_zapytania(tresczapytania);
        zapytanie.setData1(data1);
        zapytanie.setOdpowiedz("");
        Connection connection = null;
        Session session = null;
        MessageProducer publisher = null;
        ObjectMessage message = null;
        try {
            connection = topicconnectionFactory.createConnection();

            session = connection.createSession(false, 0);
            publisher = session.createProducer(topic);
            message = session.createObjectMessage(zapytanie);
            publisher.send(message);
            System.out.println("Wysyłano zapytanie do dostawcy");

        } catch (JMSException e) {
            System.err.println("Wystąpił wyjątek: " + e.toString());
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                }
            }
        }

    }

    public String Wyslijzamowienie(String nrzamowienia) {
        SZamowienie zamowienie = new SZamowienie();

        zamowienie = fasada.Szukajzamowienie(nrzamowienia);

        Connection connection = null;
        Session session = null;
        MessageProducer publisher = null;
        ObjectMessage message = null;
        MessageConsumer consumer = null;

        String Selektor = null;
        try {
            //connection = topicconnectionFactory.createConnection();
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, 0);
            publisher = session.createProducer(topic);
            //publisher = session.createProducer(queue);
            message = session.createObjectMessage(zamowienie);
            message.setJMSReplyTo(replyqueue);
            publisher.send(message);
         //   long czas = System.currentTimeMillis();
           // System.out.println("Czas wysłania " +nrzamowienia + " zamowienia: " + czas);
            long rozmiar = Rozmiar_obiektow_serializowanych.rozmiar(zamowienie);
           // System.out.println("Rozmiar zamówienia: " + rozmiar);

            Selektor = message.getJMSMessageID();

            //System.out.println("Wysłano zamówienie do tematu");
            //System.out.println("Wysłano zamówienie do kolejki");
            zapis.Zapis_rozmiaru(rozmiar);

        } catch (JMSException e) {
            System.err.println("Wystąpił wyjątek: " + e.toString());
        } catch (IOException ex) {
            Logger.getLogger(messagebean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                }
            }
        }

        return Selektor;

    }

    private int getMessageSizeInBytes(Object message) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(message);
        oos.close();
        return baos.size();
    }

    public void test() {

        Connection connection = null;
        Session session = null;
        MessageProducer publisher = null;
        MessageConsumer consumer = null;
        TextMessage odpowiedz = null;
        TextMessage message = null;
        Destination wysylkowa_kolejka = queue;
        Destination odbierajaca_kolejka = replyqueue;
        try {

            //connection = topicconnectionFactory.createConnection();
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //publisher = session.createProducer(topic);
            publisher = session.createProducer(wysylkowa_kolejka);
            message = session.createTextMessage("test");

            message.setJMSReplyTo(replyqueue);
            publisher.send(message);
            System.out.println("Wysłano test");
            System.currentTimeMillis();

        } catch (JMSException e) {
            System.err.println("Wystąpił wyjątek: " + e.toString());

        }
    }

    public String Odbierzodpowiedz(ObjectMessage message) {

        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        TextMessage odpowiedz = null;

        try {
            String selector = "JMSCorrelationID='" + message.getJMSMessageID() + "'";
            connection = queueconnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(replyqueue, selector);
            connection.start();

            while (true) {
                Message m = consumer.receive(35000);

                if (m != null) {
                    if (m instanceof TextMessage) {
                        odpowiedz = (TextMessage) m;
                        System.out.println(
                                "Otrzymano komunikat: " + odpowiedz.getText());
                    } else {
                        break;
                    }
                }
            }
        } catch (JMSException e) {
            System.err.println("Wystapił wyjątek: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }
        return "BLAD";
    }

}
