/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import wyniki.Zapis_wynikow;

/**
 *
 * @author Grzesiek
 */
/*
@MessageDriven(mappedName = "jms/Reply", activationConfig =  {
@ActivationConfigProperty(propertyName = "addressList", propertyValue = "192.168.0.11"),   
@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class Asynchroniczne_ziarenko implements MessageListener {
static final Logger logger = Logger.getLogger("Fireforget");
@Resource
private MessageDrivenContext mdc;
//Fasada fasada = new Fasada();
//TBase base = new TBase(fasada);
@Resource(mappedName = "jms/Queue")
private  Queue queue;

@Resource(lookup = "jms/JupiterConnectionFactory")
    public ConnectionFactory connectionFactory;
    Connection con = null;  
    public Asynchroniczne_ziarenko() {
    }
  
    @Override
    public void onMessage(Message message) {
     
        Zapis_wynikow zapis = new Zapis_wynikow(); 
        ObjectMessage reply;
        Session ses = null;
        long Czas_odebrania = System.currentTimeMillis();
   try {
        
      if (message instanceof ObjectMessage){
          
          long Czas_wyslania = message.getJMSTimestamp();
    
          reply = (ObjectMessage) message;
      //logger.info("Otrzymano odpowiedz asynchronicznie");
          zapis.Zapis(Czas_odebrania, Czas_wyslania, 0, 0, 0,"");
message = null;
      
             }
      
      else { logger.warning("Komunikat niewłaściwego typu: " + message.getClass().getName());
            

 }} catch (Throwable te) {
  logger.severe(
                 "ReplyMsgBean.onMessage: Wyjątek: " + te.toString());
   }
}
    

}

   
  */     
            
     
            
   