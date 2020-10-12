/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SProdukt;

/**
 *
 * @author Grzesiek
 */
public class ProduktController extends Controller <SProdukt>{

  

    public ProduktController(String persistence) {
        super(SProdukt.class);
        sciezka = persistence;
        query1="from SProdukt k where k.id";
        query2="select c from SProdukt as c";

    }
 /*   private EntityManagerFactory emf = null;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }*/

 /*   public boolean addProduct(SProdukt produkt) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produkt);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
  /*  public boolean addProduct(SProdukt produkt) 
    {
        return Add(produkt);
    }*/

   /* public boolean addProducts(ArrayList<SProdukt> produkty) {
        EntityManager em = getEntityManager();
        SProdukt nowyprodukt = null;
        try {
            Iterator it = produkty.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowyprodukt = (SProdukt) it.next();
                if (nowyprodukt.getId() == null) {
                    em.persist(nowyprodukt);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/
 /*   public boolean addProducts(ArrayList<SProdukt> produkty)
    {
        return addList(produkty);
    }*/

  /*  public SProdukt[] getTProducts_() {
        return (SProdukt[]) getTProducts().toArray(new SProdukt[0]);
    }

    public List<SProdukt> getTProducts() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from SProdukt as c");
            return q.getResultList();
        } finally {
            em.close();
        }
    }*/
    
    public SProdukt[] getTProducts_() {
        return (SProdukt[]) gets().toArray(new SProdukt[0]);
    }

    /*  public boolean addTProduktydostawcy(ArrayList<Dostawca> dostawcy) {
     EntityManager em = getEntityManager();
     Produkt nowyprodukt = null;
     Iterator it = dostawcy.iterator();
     em.getTransaction().begin();
     try {
     while (it.hasNext()) {
     Dostawca nowydostawca = (Dostawca) it.next();
     if (nowydostawca.getId() == null) {
     continue;
     }
     Iterator it_ = nowydostawca.getProdukt().iterator();
     while (it_.hasNext()) {
     nowyprodukt = (Produkt) it_.next();
     if (nowyprodukt.getId() == null) {
     em.persist(nowyprodukt);
     }
     }
     }
     em.getTransaction().commit();
     } finally {
     em.close();
     return false;
     }
     }
     /*
     public void savetytul (Tytul_filmu current){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Tytul_filmu kk = em.find(Tytul_filmu.class, current.getId());
     kk.setTytul_polski(current.getTytul_polski());
     kk.setRezyser(current.getRezyser());
     kk.setRok_produkcji(current.getRok_produkcji());
     em.persist(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }
     public void deletetytul(Long id){
     EntityManager em = getEntityManager();
     try{
     em.getTransaction().begin();
     Tytul_filmu kk = em.find(Tytul_filmu.class, id);
     em.remove(kk);
     em.getTransaction().commit();
     }finally {
     em.close();
     }
     }
     */
   /* public SProdukt getById(Long id) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("from Produkt k where k.id" + id);
            List<SProdukt> result = new ArrayList<>();
            return (SProdukt) q.getSingleResult();

        } finally {
            em.close();
        }
    }*/
    
}
