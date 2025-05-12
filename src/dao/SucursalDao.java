package dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Sucursal;

public class SucursalDao {
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

    public int agregar(Sucursal objeto) {
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
    
    public Sucursal traer(int id) {
        Sucursal objeto = null;
        try {
            iniciaOperacion();
            objeto = session.get(Sucursal.class, id);
            
            if (objeto != null) {
                if (objeto.getEstablecimiento() != null) {
                    Hibernate.initialize(objeto.getEstablecimiento().getSucursales());
                }
                Hibernate.initialize(objeto.getLstDiasDeAtencion());
                Hibernate.initialize(objeto.getLstEspecialidad());
            }

        } finally {
            session.close();
        }
        return objeto;
    }

    
    public Sucursal traerPorTelefono(String telefono) {
        Sucursal sucursal = null;
        try {
            iniciaOperacion();
            sucursal = (Sucursal) session.createQuery("from Sucursal s where s.telefono = :telefono")
                    .setParameter("telefono", telefono)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return sucursal;
    }
    
    public List<Sucursal> traerTodos() {
        List<Sucursal> lista = null;
        try {
            iniciaOperacion();
            String hql = "SELECT DISTINCT s FROM Sucursal s LEFT JOIN FETCH s.lstDiasDeAtencion";
            lista = session.createQuery(hql, Sucursal.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public void actualizar(Sucursal objeto) {
        try {
            iniciaOperacion();
            session.update(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }

    public void eliminar(Sucursal objeto) {
        try {
            iniciaOperacion();
            session.delete(objeto);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
    }
}