package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Servicio;
import datos.Turno;

public class TurnoDao {
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
	
	public int agregar(Turno objeto) {
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
    
    public Turno traer(long idTurno) {
    	Turno objeto = null;
		try {
			iniciaOperacion();
			objeto = (Turno) session.get(Turno.class, idTurno);
		} finally {
			session.close();
		}
		return objeto;
	}

	public void actualizar(Turno objeto) {
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

	public void eliminar(Turno objeto) {
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
	
	public List<Turno> obtenerTurnosPorSucursal(int idSucursal){
		List<Turno> turnos = null;
        try {
            iniciaOperacion();
            String hql = "SELECT t.* FROM Turno t WHERE t.puntoDeAtencion_id IN (SELECT p.idpuntoDeAtencion FROM PuntoDeAtencion p WHERE p.idpuntoDeAtencion IN(SELECT s.puntoDeAtencion_id FROM Sucursal s WHERE s.idsucursal = :idsucursal))";
            turnos = (List<Turno>) session.createQuery(hql).setParameter("idsucursal", idSucursal).getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
            session.close();
        }
        return turnos;
	}

	public List<Turno> obtenerTurnosPorEmpleado(int idEmpleado){
		List<Turno> turnos = null;
        try {
            iniciaOperacion();
            String hql = "SELECT t.* FROM Turno t INNER JOIN PuntoDeAtencion pda ON t.puntoDeAtencion_id = pda.idpuntoDeAtencion WHERE pda.empleado_id = :idEmpleado";
            turnos = (List<Turno>) session.createQuery(hql).setParameter("idEmpleado", idEmpleado).getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
            session.close();
        }
        return turnos;
	}

	public List<Turno> obtenerTurnosPorFecha(LocalDate fecha){
		List<Turno> turnos = null;
        try {
            iniciaOperacion();
            String hql = "SELECT t.* FROM Turno t WHERE CAST(t.fechaHora AS DATE) = :fecha";
            turnos = (List<Turno>) session.createQuery(hql).setParameter("fecha", fecha).getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
            session.close();
        }
        return turnos;
	}

	public List<Turno> obtenerTurnosPorRangoFechas(LocalDate desde, LocalDate hasta){
		List<Turno> turnos = null;
        try {
            iniciaOperacion();
            String hql = "SELECT t.* FROM Turno t WHERE CAST(t.fechaHora AS DATE) BETWEEN :fechaDesde AND :fechaHasta";
            turnos = (List<Turno>) session.createQuery(hql).setParameter("fechaDesde", desde).setParameter("fechaHasta", hasta).getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
            session.close();
        }
        return turnos;
	}
}
