package dao;

import java.util.List;

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
	    Empleado empleado = null;
	    try {
	        iniciaOperacion();
	        empleado = (Empleado) session
	            .createQuery("FROM Empleado e LEFT JOIN FETCH e.lstEspecialidades WHERE e.id = :id")
	            .setParameter("id", idEmpleado)
	            .uniqueResult();
	    } finally {
	        session.close();
	    }
	    return empleado;
	}

    public Empleado traerPorDni(int dni) {
    	Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = (Empleado) session
            	.createQuery("FROM Empleado e LEFT JOIN FETCH e.lstEspecialidades WHERE e.dni = :d")
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
                .createQuery("FROM Empleado e LEFT JOIN FETCH e.lstEspecialidades WHERE cuil = :c")
                .setParameter("c", cuil)
                .uniqueResult();
        } finally {
            session.close();
        }
        return empleado;
    }
    
    public List<Empleado> traerTodos() {
        List<Empleado> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("FROM Empleado", Empleado.class).list();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Empleado> traerPorEspecialidad(String nombreEsp) {
        iniciaOperacion();
        List<Empleado> lista = session.createQuery(
            "SELECT DISTINCT e FROM Empleado e " +
            "JOIN e.lstEspecialidades esp " +
            "WHERE esp.nombre = :nombre", Empleado.class)
            .setParameter("nombre", nombreEsp)
            .list();
        session.close();
        return lista;
    }

    public Empleado traerPorMatricula(String matricula) {
        Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = session
                .createQuery("FROM Empleado e WHERE e.matricula = :matricula", Empleado.class)
                .setParameter("matricula", matricula)
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
