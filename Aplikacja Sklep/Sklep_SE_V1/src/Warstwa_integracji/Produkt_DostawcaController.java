/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SProdukt_Dostawca;

/**
 *
 * @author Grzesiek
 */
public class Produkt_DostawcaController extends Controller<SProdukt_Dostawca> {

  //  String sciezka;
    public Produkt_DostawcaController(String persistence) {
        super(SProdukt_Dostawca.class);
        sciezka = persistence;
        query1 = "from SProdukt_Dostawca k where k.id=";
        query2 = "select c from SProdukt_Dostawca as c";

    }
    /* private EntityManagerFactory emf = null;

     private EntityManager getEntityManager() {
     if (emf == null) {
     emf = Persistence.createEntityManagerFactory(sciezka);
     }
     return emf.createEntityManager();
     }*/

    /*  public boolean addProduct(SProdukt_Dostawca produktdostawca) {
     EntityManager em = getEntityManager();
     try {
     em.getTransaction().begin();
     em.persist(produktdostawca);
     em.getTransaction().commit();
     } finally {
     em.close();
     return false;
     }
     }*/
 /*   public boolean addProduct(SProdukt_Dostawca produktdostawca) {
        return Add(produktdostawca);
    }*/

    /*  public boolean addProducts(ArrayList<SProdukt_Dostawca> produkty) {
     EntityManager em = getEntityManager();
     SProdukt_Dostawca nowyprodukt = null;
     try {
     Iterator it = produkty.iterator();
     em.getTransaction().begin();
     while (it.hasNext()) {
     nowyprodukt = (SProdukt_Dostawca) it.next();
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
  /*  public boolean addProducts(ArrayList<SProdukt_Dostawca> produkty) {
        return addList(produkty);
    }*/

    public SProdukt_Dostawca[] getTProducts_() {
        return (SProdukt_Dostawca[]) gets().toArray(new SProdukt_Dostawca[0]);
    }
    /*  public SProdukt_Dostawca[] getTProducts_() {
     return (SProdukt_Dostawca[]) getTProducts().toArray(new SProdukt_Dostawca[0]);
     }

     public List<SProdukt_Dostawca> getTProducts() {
     EntityManager em = getEntityManager();
     try {
     javax.persistence.Query q
     = em.createQuery("select c from SProdukt_Dostawca as c");
     return q.getResultList();
     } finally {
     em.close();
     }
     }*/


    /* public boolean addTProduktydostawcy(ArrayList<Dostawca> dostawcy) {
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
     }*/
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
    /* public SProdukt_Dostawca getById(Long id) {
     EntityManager em = getEntityManager();
     try {
     javax.persistence.Query q
     = em.createQuery("from Produkt_Dostawca k where k.id=" + id);
     List<SProdukt_Dostawca> result = new ArrayList<>();
     return (SProdukt_Dostawca) q.getSingleResult();

     } finally {
     em.close();
     }
     }*/
}
