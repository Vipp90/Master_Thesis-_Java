/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DostawcaWarstwa_integracji;

import Controller.Controller;
import Warstwa_biznesowa.Klient;


/**
 *
 * @author Grzesiek
 */
public class DKlientController extends Controller<Klient>{

   /* private EntityManagerFactory emf = null;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Dostawca_SEPU");
        }
        return emf.createEntityManager();
    }*/
     public DKlientController(String persistence) {
        super(Klient.class);
        sciezka = persistence;
        query1="from Klient k where k.id";
        query2="select c from Klient as c";

    }

   /* public boolean Add_klient(Klient klient) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(klient);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/

   /* public boolean addKlientList(Collection<Klient> klienci) {
        EntityManager em = getEntityManager();
        Klient nowyklient = null;
        try {
            Iterator it = klienci.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowyklient = (Klient) it.next();
                if (nowyklient.getId() == null) {
                    em.persist(nowyklient);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/

    public Klient[] getKlients_() {
        return (Klient[]) gets().toArray(new Klient[0]);
    }

 /*   public List<Klient> getKlients() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from Klient as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }*/

    /*
     public void save (Klient current){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Klient kk = em.find(Klient.class, current.getId());
     kk.setImie(current.getImie());
     kk.setNazwisko(current.getNazwisko());
     kk.setPesel(current.getPesel());
     em.persist(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }


     public void delete(Long id){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Klient kk = em.find(Klient.class, id);
     em.remove(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }

     */
  /*  public Klient getById(Long id) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("from Klient k where k.id" + id);
            List<Klient> result = new ArrayList<>();
            return (Klient) q.getSingleResult();

        } finally {
            em.close();
        }
    }*/

}
