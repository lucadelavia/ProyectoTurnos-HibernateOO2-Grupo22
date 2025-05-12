package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.DiasDeAtencion;

public class DiasDeAtencionDao {
    private static Session session;
    private Transaction tx;

    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("ERROR en la capa de acceso a datos", he);
    }

    public int agregar(DiasDeAtencion objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public List<DiasDeAtencion> traerTodos() {
        List<DiasDeAtencion> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("FROM DiasDeAtencion", DiasDeAtencion.class).list();
        } finally {
            session.close();
        }
        return lista;
    }

    
    public void actualizar(DiasDeAtencion objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public void eliminar(DiasDeAtencion objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
    }

    public DiasDeAtencion traer(int idDiasDeAtencion) {
        DiasDeAtencion objeto = null;
        try {
            iniciaOperacion();
            objeto = (DiasDeAtencion) session.get(DiasDeAtencion.class, idDiasDeAtencion);
        } finally {
            session.close();
        }
        return objeto;
    }
}