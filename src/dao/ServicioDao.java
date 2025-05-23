package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Servicio;

public class ServicioDao {
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
	
	public int agregar(Servicio objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he; 
        } finally {
            session.close();
        }
        return id;
    }
    
    public Servicio traer(int idServicio) {
    	Servicio objeto = null;
		try {
			iniciaOperacion();
			objeto = (Servicio) session.get(Servicio.class, idServicio);
		} finally {
			session.close();
		}
		return objeto;
	}
    
    public List<Servicio> traerTodos() {
        List<Servicio> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("FROM Servicio", Servicio.class).list();
        } finally {
            session.close();
        }
        return lista;
    }
    
	public void actualizar(Servicio objeto) {
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

	public void eliminar(Servicio objeto) {
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
	
	public Servicio traerPorNombreServicio(String nombreServicio){
		Servicio s = null;
        try {
            iniciaOperacion();
            String hql = "from Servicio s where s.nombreServicio =:nombreServicio";
            s = (Servicio) session.createQuery(hql).setParameter("nombreServicio", nombreServicio).uniqueResult();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
            session.close();
        }
        return s;
	}
}
