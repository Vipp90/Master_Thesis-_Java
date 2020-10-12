/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dostawca_v1_client_ejb;

import Dostawcabusiness_tier.Odbior;
import Dostawcabusiness_tier.DostawcaFacadeRemote;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import wyniki.Zapis_wynikow;

/**
 *
 * @author Grzesiek
 */
public class DostawcaClient extends javax.swing.JFrame {

    @EJB
    private static DostawcaFacadeRemote fasada;

    @Resource(lookup = "jms/JupiterConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/Topic")
    private static Topic topic;

//private static DostawcaFireforget ziarenko = new DostawcaFireforget() ;
//@Resource(mappedName = "jms/Queue")
    // private static Queue queue;
    //  @Resource(mappedName = "jms/ConnectionFactory")
    //private static ConnectionFactory connectionFactory;
    // 
    DostawcaTableModel model1;
    ProduktyzamowienieTableModel model3;

    int row;

    public DostawcaClient() {
        super();
        initComponents();
        CardLayout la = (CardLayout) this.getContentPane().getLayout();
        model1 = new DostawcaTableModel();

        //zapytania1.addActionListener (new ActionListener () {
        //  public void actionPerformed(ActionEvent e) {
        //  int i =    zapytania1.getSelectedIndex();
        //   Pokaz_zapytania2(i);
        //  }
//});
    }

    public static class AckEquivExample {

        public void run_threads() {

            SynchReceiver synchReceiver = new SynchReceiver();

            synchReceiver.start();

            try {
                synchReceiver.join();
            } catch (InterruptedException e) {
            }

        }

        /**
         * Calls the run_threads method to execute the program threads.
         *
         * @param args the topic used by the example
         */
        /**
         * Calls the run_threads method to execute the program threads.
         */
        public class SynchReceiver extends Thread {

            final String queueName = "jms/Topic";

            @Override
            public void run() {

                Odbior odbior = new Odbior();
                Zapis_wynikow zapis = new Zapis_wynikow();
                Connection connection = null;
                Session session = null;
                MessageConsumer receiver = null;
                ObjectMessage objectmessage = null;
                ObjectMessage message = null;
                MessageProducer publisher = null;
                TextMessage reply1 = null;

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

                try {
                  //  System.out.println(
                   //         "  ODBIORCA: Utworzono " + "sesje typu client-acknowledge");
                    receiver = session.createConsumer(topic);
                    connection.start();
                    while (true) {
                        Message m = receiver.receive(1);
                        if (m != null) {
                          //  System.out.println(
                               //     "Otrzymano komunikat: ...");
                       //     System.out.println("Czas wysłania przez klienta: " + m.getJMSTimestamp());
                            long Czas_odebrania = System.currentTimeMillis();
                            String wynik = odbior.OdbiorZamowienia(m);
                            String numer = odbior.getnumer();
                            long koniect2 = odbior.getkoniect2();
                            if (!"Otrzymano test".equals(wynik)) {
                                publisher = session.createProducer(m.getJMSReplyTo());
                                if (wynik == "Brak klienta") {
                                    reply1 = session.createTextMessage("Brak klienta w bazie");
                                } else if (wynik == "Istnieje") {
                                    reply1 = session.createTextMessage("Zamówienie o podanym numerze już istnieje w bazie");
                                } else if (wynik == "Dodano zamowienie") {
                                    reply1 = session.createTextMessage("Zamówienie zostało przekazane do realizacji");
                                }
                                message = (ObjectMessage) m;
                                Serializable objectData = message.getObject();
                                //reply1.setJMSCorrelationID(m.getJMSMessageID());
                                 objectmessage = session.createObjectMessage(objectData);
                               // reply1.setJMSCorrelationID(m.getJMSMessageID());
                                objectmessage.setJMSCorrelationID(m.getJMSMessageID());
                                publisher.send(objectmessage);
                                long Czas_wyslania = System.currentTimeMillis();
                                zapis.Zapis(0, 0, Czas_odebrania, koniect2, m.getJMSTimestamp(), numer);
                            } else {
                                //System.out.println("test synchronicznie");
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

                /*
                
                
                 Connection connection = null ;
                 Session session = null;
                 MessageConsumer receiver = null;
                 TextMessage message = null;
                 ObjectMessage Objectmsg = null;
                 MessageProducer publisher = null;
                 TextMessage reply1 = null;
                 try {
                 connection = connectionFactory.createConnection();
                 session = connection.createSession(
                 false,
                 0);
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
                
                 try {
                 System.out.println(
                 "  ODBIORCA: Utworzono " + "sesje typu client-acknowledge");
                 receiver = session.createConsumer(queue);
                 connection.start();
                 while (true) {
                 Message m = receiver.receive(1);

                 if (m != null) {
                 if (m instanceof TextMessage) {
                 message = (TextMessage) m;
                 System.out.println(
                 "Otrzymano komunikat: " + message.getText());
                 } else if (m instanceof ObjectMessage)  {
                 Objectmsg = (ObjectMessage) m;
                 Serializable objectData = Objectmsg.getObject();
                 if(objectData instanceof Zamowienie){
                 long Czas_odebrania = System.currentTimeMillis();
                 System.out.println("Czas odebrania: " +Czas_odebrania);
                
                 System.out.println("Czas t1: " + (Czas_odebrania - m.getJMSTimestamp()));
                 System.out.println(
                 "Otrzymano zamówienie od klienta");
                
                 fasada.update_data();
                 Zamowienie zamowienie = new Zamowienie();
                 zamowienie      = (Zamowienie) objectData;
                 String wynik = fasada.dodaj_zamowienie1("1", zamowienie.getNr_zamówienia());
                 System.out.println(wynik);
                 fasada.dodaj_zamowienia();
                
                 List<Pozycja_zamowienia> pozycje;
                 pozycje = zamowienie.getPozycje();
                
                 for (Pozycja_zamowienia pozycja : pozycje) {
                 String nip = pozycja.getMProduktDostawcy().getMdostawca().getNIP();
                 String klient = "1";
                 String data[] = {pozycja.getMProduktDostawcy().getMprodukt().getNazwa(),pozycja.getMProduktDostawcy().getMprodukt().getProducent(),pozycja.getMProduktDostawcy().getMprodukt().getKategoria()};
                 fasada.dodaj_pozycje_zamowienia(nip, data, klient, zamowienie.getNr_zamówienia(), pozycja.getIlosc());
                 }
                 fasada.dodaj_pozycje_zamowien();
                 fasada.update_data();
                 publisher = session.createProducer(m.getJMSReplyTo());
                 if (wynik == "Brak klienta"){
                 reply1 = session.createTextMessage("Brak klienta w bazie");
                 }
                 else if (wynik == "Istnieje"){
                 reply1 = session.createTextMessage("Zamówienie o podanym numerze już istnieje w bazie");
                 }
                 else{
                 reply1 = session.createTextMessage("Zamówienie zostało przekazane do realizacji");}
                
                 reply1.setJMSCorrelationID(m.getJMSMessageID());       
                
                 publisher.send(reply1);
                 long Czas_wyslania = System.currentTimeMillis();
                 System.out.println("Czas wysłania: " + Czas_wyslania );
                 System.out.println("Czas t2: " + (Czas_wyslania-Czas_odebrania));
                 System.out.println("Wysłano potwierdzenie otrzymania Zamówienia");
                
                 utworzPlik();
                 FileWriter file = new FileWriter("D:/t1.txt", true);
                 FileWriter file1 = new FileWriter("D:/t2.txt", true);
                
                 file.write(System.getProperty("line.separator"));
                 file1.write(System.getProperty("line.separator"));
                 file.write("" + (Czas_odebrania - m.getJMSTimestamp())+ " ");
                 file1.write("" + (Czas_wyslania-Czas_odebrania)+ " ");
                
                 file.close();
                 file1.close();
                
                
                
                
                 }
                 }else {break;}
                 }
                 }
                
                
                
                
                 //  message = (TextMessage) receiver.receive();
                 //  System.out.println(
                 //   "  ODBIORCA: Przetwarzanie " + "komunikatu: "
                 //   + message.getText());
                 System.out.println(
                 "  ODBIORCA: Teraz potwierdza " + "komunikat");
                 message.acknowledge();
                 } catch (JMSException e) {
                 System.err.println("Wystapil wyjatek: " + e.toString());
                 } catch (IOException ex) {
                 Logger.getLogger(DostawcaClient.class.getName()).log(Level.SEVERE, null, ex);
                 } finally {
                 if (connection != null) {
                 try {
                 connection.close();
                 } catch (JMSException e) {
                 }
                 }
                 }
                 */
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pusty = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Dodaj_dostawce = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Nazwa = new javax.swing.JTextField();
        NIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Adres1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Adres2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Nrtelefon = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Utwórzklienta = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Nrkonta = new javax.swing.JTextField();
        Dodaj_produkt = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Nazwa_prod = new javax.swing.JTextField();
        Producent = new javax.swing.JTextField();
        Kategoria = new javax.swing.JTextField();
        Cena = new javax.swing.JTextField();
        Dostawca = new javax.swing.JTextField();
        Add_produkt = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jButton1.setText("Dostawcy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Produkty");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Zamówienia");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PustyLayout = new javax.swing.GroupLayout(Pusty);
        Pusty.setLayout(PustyLayout);
        PustyLayout.setHorizontalGroup(
            PustyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PustyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PustyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PustyLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jButton4))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        PustyLayout.setVerticalGroup(
            PustyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PustyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PustyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(70, 70, 70)
                .addComponent(jButton4)
                .addContainerGap(468, Short.MAX_VALUE))
        );

        getContentPane().add(Pusty, "card3");

        jLabel1.setText("Nazwa:");

        Nazwa.setName("Nazwa"); // NOI18N

        NIP.setName("NIP"); // NOI18N

        jLabel2.setText("NIP:");

        Adres1.setName("Adres1"); // NOI18N

        jLabel3.setText("Adres: ");
        jLabel3.setName("Adres1"); // NOI18N

        Adres2.setName("Adres2"); // NOI18N

        jLabel4.setText("Kod pocztowy: ");
        jLabel4.setName("Adres2"); // NOI18N

        Nrtelefon.setName("Nrtelefon"); // NOI18N

        jLabel5.setText("Nr telefonu: ");
        jLabel5.setName("Nrtelefon"); // NOI18N

        Utwórzklienta.setText("Utwórz dostawcę");
        Utwórzklienta.setName("Utwórz klienta"); // NOI18N
        Utwórzklienta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UtwórzklientaActionPerformed(evt);
            }
        });

        jLabel6.setText("Numer konta: ");
        jLabel6.setName("Login"); // NOI18N

        Nrkonta.setName("Nrkonta"); // NOI18N

        javax.swing.GroupLayout Dodaj_dostawceLayout = new javax.swing.GroupLayout(Dodaj_dostawce);
        Dodaj_dostawce.setLayout(Dodaj_dostawceLayout);
        Dodaj_dostawceLayout.setHorizontalGroup(
            Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                        .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nazwa)
                                    .addComponent(NIP)
                                    .addComponent(Adres1)
                                    .addComponent(Adres2)))
                            .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(30, 30, 30)
                                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                                        .addComponent(Utwórzklienta)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Nrtelefon, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                    .addComponent(Nrkonta))))
                        .addGap(199, 199, 199))
                    .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        Dodaj_dostawceLayout.setVerticalGroup(
            Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dodaj_dostawceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(Nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Adres1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Adres2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Nrtelefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Dodaj_dostawceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Nrkonta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 366, Short.MAX_VALUE)
                .addComponent(Utwórzklienta)
                .addGap(30, 30, 30))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Imie");
        jLabel1.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(Dodaj_dostawce, "Dostawcy");

        jLabel7.setText("Nazwa");

        jLabel8.setText("Producent");

        jLabel9.setText("Kategoria");

        jLabel10.setText("Cena");

        jLabel11.setText("Dostawca");

        Nazwa_prod.setName("Nazwa"); // NOI18N

        Producent.setName("Producent"); // NOI18N
        Producent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProducentActionPerformed(evt);
            }
        });

        Kategoria.setName("Kategoria"); // NOI18N
        Kategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KategoriaActionPerformed(evt);
            }
        });

        Cena.setName("Cena"); // NOI18N
        Cena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CenaActionPerformed(evt);
            }
        });

        Dostawca.setName("Dostawca"); // NOI18N
        Dostawca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DostawcaActionPerformed(evt);
            }
        });

        Add_produkt.setText("Dodaj produkt");
        Add_produkt.setName("Dodaj_produkt"); // NOI18N
        Add_produkt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add_produktActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Dodaj_produktLayout = new javax.swing.GroupLayout(Dodaj_produkt);
        Dodaj_produkt.setLayout(Dodaj_produktLayout);
        Dodaj_produktLayout.setHorizontalGroup(
            Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dodaj_produktLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Kategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cena, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Producent, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(41, 41, 41)
                        .addComponent(Nazwa_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Dodaj_produktLayout.createSequentialGroup()
                        .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Add_produkt)
                            .addGroup(Dodaj_produktLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Dostawca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1)))
                .addContainerGap(517, Short.MAX_VALUE))
        );
        Dodaj_produktLayout.setVerticalGroup(
            Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dodaj_produktLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Nazwa_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Producent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9))
                    .addGroup(Dodaj_produktLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Kategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Cena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Dodaj_produktLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(Dostawca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addComponent(Add_produkt)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        Add_produkt.getAccessibleContext().setAccessibleName("");

        getContentPane().add(Dodaj_produkt, "Produkty");

        jMenu1.setText("File");

        jMenuItem3.setText("Zamówienia");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setText("Zapytania o produkt");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void list_content(ArrayList<String> col,
            JComboBox list) {
        String s;
        list.removeAllItems();
        Iterator<String> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    public void paintComponent(Graphics g) {
        paintComponent(g);
    }

    class MyTableModel extends AbstractTableModel {

        private String[] columnNames = {"Nazwa",
            "Producent",
            "Kategoria"
        };
        private Object[][] data;

        public void setData(Object[][] val) {
            data = val;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 0) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class DostawcaTableModel extends MyTableModel {

        private String[] columnNames1 = {
            "Nazwa",
            "Adres",
            "NIP"
        };

        public String getColumnName(int col) {
            return columnNames1[col];
        }
    }

    class ProduktyzamowienieTableModel extends DostawcaTableModel {

        private String[] columnNames2 = {
            "Nazwa",
            "Producent",
            "Ilość"
        };

        public String getColumnName(int col) {
            return columnNames2[col];
        }
    }

    private class RowListener1 implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }

        }
    }

    private class RowListener2 implements ListSelectionListener {

        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }

        }
    }

    private void UtwórzklientaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UtwórzklientaActionPerformed

        String dane_dostawca1[] = {Nazwa.getText(), NIP.getText(), Adres1.getText(), Adres2.getText(), Nrtelefon.getText(), Nrkonta.getText()};

        if (dane_dostawca1[0].equals("") || dane_dostawca1[1].equals("") || dane_dostawca1[2].equals("") || dane_dostawca1[3].equals("") || dane_dostawca1[4].equals("") || dane_dostawca1[5].equals("")) {
            JOptionPane.showMessageDialog(this, "Prosze podać dane");
            return;
        }

        try {
            Integer.parseInt(Nrtelefon.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "W polu 'numer telefonu' wymagany format liczbowy");
            return;
        }

 //String wynik =  fasada.(dane_dostawca1); 
        //if (wynik == null)
/// {
        //JOptionPane.showMessageDialog(this, "Ten dostawca już istnieje");
// }
// else 
        //   JOptionPane.showMessageDialog(this, "Utworzono dostawce o numerze NIP " + NIP.getText().toString());
        Nazwa.setText("");
        NIP.setText("");
        Adres1.setText("");
        Adres2.setText("");
        Nrtelefon.setText("");
        Nrkonta.setText("");
 //fasada.update_data();
//fasada.dodaj_dostawcow();

        // table_content1();  
        // TODO add your handling code here:
    }//GEN-LAST:event_UtwórzklientaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CardLayout la = (CardLayout) this.getContentPane().getLayout();
        la.show(this.getContentPane(), "Dostawcy");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout la = (CardLayout) this.getContentPane().getLayout();
        la.show(this.getContentPane(), "Produkty");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ProducentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProducentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProducentActionPerformed

    private void KategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KategoriaActionPerformed

    private void CenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CenaActionPerformed

    private void DostawcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DostawcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DostawcaActionPerformed

    private void Add_produktActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add_produktActionPerformed
        /*
         String k;
         String data[] = {Nazwa_prod.getText(), Producent.getText(), Kategoria.getText(), Cena.getText(), Dostawca.getText()};
         if (Nazwa_prod.getText().equals("") || Producent.getText().equals("") || Kategoria.getText().equals("") || Cena.getText().equals("") || Dostawca.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "Prosze podać dane");
         return;
         }
         try {
         Float.parseFloat(Cena.getText());

         } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(this, "W polu 'cena' wymagany format liczbowy");
         return;
         }
         try {
         Integer.parseInt(Dostawca.getText());

         } catch (NumberFormatException e) {
         JOptionPane.showMessageDialog(this, "W polu 'Dostawca' wymagany format liczbowy");
         return;
         }

         // k= fasada.dodaj_produkt(data);
         //if (k=="brak")
         {
         JOptionPane.showMessageDialog(null, "Dostawca produktu nie istnieje w bazie");
         }

         //if (k==null)
         {
         JOptionPane.showMessageDialog(null, "Produkt o podanych parametrach już istnieje w bazie");
         }
         //if (k=="OK")
         {
         JOptionPane.showMessageDialog(null, "Dodano produkt do bazy");
         }
         */
        for (int i = 0; i < 2500; i++) {
            String data[] = {Integer.toString(i), Integer.toString(i), Integer.toString(i), Integer.toString(i)};
            fasada.dodaj_produkt(data, "1", 1);
            if (i == 101) {
                System.out.print("Dodano produkty");
            }
        }
        fasada.dodaj_produkty();
        Nazwa_prod.setText("");
        Producent.setText("");
        Kategoria.setText("");
        Cena.setText("");
        Dostawca.setText("");
       // table_content();

        // TODO add your handling code here:
    }//GEN-LAST:event_Add_produktActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        CardLayout la = (CardLayout) this.getContentPane().getLayout();
        la.show(this.getContentPane(), "Zamowienia");

      //  Pokaz_produkty();
        //     ArrayList<String> help3 =  fasada.getdostawcy2();
        //   if (help3 != null) {
        //         list_content(help3, Dostawcy);
        //   }
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CardLayout la = (CardLayout) this.getContentPane().getLayout();
        la.show(this.getContentPane(), "Zapytanie");
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DostawcaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DostawcaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DostawcaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DostawcaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DostawcaClient().setVisible(true);

            }
        });
        AckEquivExample aee = new AckEquivExample();
        aee.run_threads();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add_produkt;
    private javax.swing.JTextField Adres1;
    private javax.swing.JTextField Adres2;
    private javax.swing.JTextField Cena;
    private javax.swing.JPanel Dodaj_dostawce;
    private javax.swing.JPanel Dodaj_produkt;
    private javax.swing.JTextField Dostawca;
    private javax.swing.JTextField Kategoria;
    private javax.swing.JTextField NIP;
    private javax.swing.JTextField Nazwa;
    private javax.swing.JTextField Nazwa_prod;
    private javax.swing.JTextField Nrkonta;
    private javax.swing.JTextField Nrtelefon;
    private javax.swing.JTextField Producent;
    private javax.swing.JPanel Pusty;
    private javax.swing.JButton Utwórzklienta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    // End of variables declaration//GEN-END:variables

    private Message createJMSMessageForjmsQueue(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }
}
