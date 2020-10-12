/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Warstwa_Biznesowa1.Encja;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zofia
 */
public class Controller<T> {

    private EntityManagerFactory emf = null;
    protected String sciezka;
    protected String query1;
    protected String query2;

    public Controller(Class<T> entityClass) {
        
    }

    protected EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sciezka);
        }
        return emf.createEntityManager();
    }

    public boolean add(T entity) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }

    public boolean addList(Collection<T> lista) {
        EntityManager em = getEntityManager();
        T nowy = null;
        try {
            Iterator it = lista.iterator();
            em.getTransaction().begin();
            while (it.hasNext()) {
                nowy = (T) it.next();
                if (((Encja) nowy).getId() == null) {
                    em.persist(nowy);
                }
            }
            em.getTransaction().commit();
        } finally {
            em.close();
            return false;
        }
    }

       public T getById(Long id) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery(query1 + id);
            return (T) q.getSingleResult();

        } finally {
            em.close();
        }
    }
       
    
    public List<T> gets() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q
                    = em.createQuery(query2);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
   /*  public T[] getKlients_() {
        return (T[]) gets().toArray(new Object [0]);
    }*/
}
