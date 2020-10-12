/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Warstwa_integracji;

import Controller.Controller;
import Sklep.SDostawca;

/**
 *
 * @author Grzesiek
 */
public class DostawcaController extends Controller <SDostawca> {

   
    public DostawcaController(String persistence) {
     
        super(SDostawca.class);
        sciezka = persistence;
        query1="from SDostawca k where k.id";
        query2="select c from SDostawca as c";

    }
    
    /*  private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }*/

   /* public boolean Add(SDostawca dostawca) {
      return Add(dostawca);
    }*/
    
    /*  public boolean Add_dostawca(SDostawca dostawca) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(dostawca);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/

  /*  public boolean addDostawcaList(Collection<SDostawca> dostawcy) {
       return addList(dostawcy);
    }*/
    
  /*   public boolean addDostawcaList(ArrayList<SDostawca> dostawcy) {
        EntityManager em = getEntityManager();
        SDostawca dostawca = null;
        try {
            Iterator it = dostawcy.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                dostawca = (SDostawca) it.next();
                if (dostawca.getId() == null) {
                    em.persist(dostawca);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }*/


    public SDostawca[] getProviders_() {
        return (SDostawca[]) gets().toArray(new SDostawca[0]);
    }

  /*  public List<SDostawca> getProviders() {
        
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("select c from SDostawca as c");
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

   /* public SDostawca getById(Long id) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery("from Dostawca k where k.id" + id);
            List<SDostawca> result = new ArrayList<>();
            return (SDostawca) q.getSingleResult();

        } finally {
            em.close();
        }
    }*/

}
