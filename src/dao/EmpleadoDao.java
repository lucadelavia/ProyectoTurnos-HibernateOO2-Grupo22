package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Empleado;


public class EmpleadoDao {
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
	
	public int agregar(Empleado objeto) {
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
    
    public Empleado traer(int idEmpleado) {
    	Empleado objeto = null;
		try {
			iniciaOperacion();
			objeto = (Empleado) session.get(Empleado.class, idEmpleado);
		} finally {
			session.close();
		}
		return objeto;
	}

    public Empleado traerPorDni(int dni) {
    	Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = (Empleado) session
                .createQuery("FROM Empleado WHERE dni = :d")
                .setParameter("d", dni)
                .uniqueResult();
        } finally {
            session.close();
        }
        return empleado;
    }
    
    public Empleado traerPorCuil(int cuil) {
    	Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = (Empleado) session
                .createQuery("FROM Empleado WHERE cuil = :c")
                .setParameter("c", cuil)
                .uniqueResult();
        } finally {
            session.close();
        }
        return empleado;
    }
    
	public void actualizar(Empleado objeto) {
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

	public void eliminar(Empleado objeto) {
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
