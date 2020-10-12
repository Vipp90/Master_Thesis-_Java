/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SKlient;

/**
 *
 * @author Grzesiek
 */
public class KlientController extends Controller <SKlient>{

   
    public KlientController(String persistence) {
        super(SKlient.class);
        sciezka = persistence;
        query1="from SKlient k where k.id";
        query2="select c from SKlient as c";

    }
  /*  private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }*/

   /* public boolean Add_klient(SKlient klient) {
       return add(klient);
    }*/
    
     /*  public boolean Add_klient(SKlient klient) {
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

  /*  public boolean addKlientList(Collection<SKlient> klienci) {
       return addList(klienci);
    }*/
    
     /*   public boolean addKlientList(ArrayList<SKlient> klienci) {
        EntityManager em = getEntityManager();
        SKlient klient = null;
        try {
            Iterator it = klienci.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                klient = (SKlient) it.next();
                if (klient.getId() == null) {
                    em.persist(klient);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
    

    public SKlient[] getKlients_() {
        return (SKlient[]) gets().toArray(new SKlient[0]);
    }

  /*  public List<SKlient> getKlients() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from SKlient as c");
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
 /*   public SKlient getById(Long id) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("from Klient k where k.id" + id);
            List<SKlient> result = new ArrayList<>();
            return (SKlient) q.getSingleResult();

        } finally {
            em.close();
        }
    }*/

}
