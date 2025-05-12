package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

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

	public Turno traer(int idTurno) {
		Turno objeto = null;
		try {
			iniciaOperacion();
			objeto = (Turno) session.get(Turno.class, idTurno);
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Turno> traerTodos() {
	    List<Turno> lista = null;
	    try {
	        iniciaOperacion();
	        lista = session.createQuery(
	            "SELECT DISTINCT t FROM Turno t " +
	            "JOIN FETCH t.cliente " +
	            "JOIN FETCH t.empleado e " +
	            "LEFT JOIN FETCH e.lstEspecialidades " +
	            "JOIN FETCH t.sucursal s " +
	            "LEFT JOIN FETCH s.lstDiasDeAtencion " +
	            "JOIN FETCH t.servicio", Turno.class)
	            .list();
	    } finally {
	        session.close();
	    }
	    return lista;
	}


	public List<Turno> traerPorCliente(int idCliente) {
		iniciaOperacion();
		List<Turno> lista = session.createQuery("FROM Turno t " + "JOIN FETCH t.cliente " + "JOIN FETCH t.empleado "
				+ "JOIN FETCH t.sucursal " + "LEFT JOIN FETCH t.servicio " + "WHERE t.cliente.id = :idCliente",
				Turno.class).setParameter("idCliente", idCliente).list();
		session.close();
		return lista;
	}

	public List<Turno> traerPorEmpleado(int idEmpleado) {
		iniciaOperacion();
		List<Turno> lista = session
				.createQuery("FROM Turno t " + "JOIN FETCH t.cliente " + "JOIN FETCH t.empleado e "
						+ "JOIN FETCH t.sucursal " + "LEFT JOIN FETCH t.servicio "
						+ "LEFT JOIN FETCH e.lstEspecialidades " + "WHERE e.id = :idEmpleado", Turno.class)
				.setParameter("idEmpleado", idEmpleado).list();
		session.close();
		return lista;
	}

	public List<Turno> traerPorSucursal(int idSucursal) {
		iniciaOperacion();
		List<Turno> lista = session
				.createQuery("FROM Turno t " + "JOIN FETCH t.cliente " + "JOIN FETCH t.empleado "
						+ "JOIN FETCH t.sucursal s " + "LEFT JOIN FETCH t.servicio "
						+ "LEFT JOIN FETCH s.lstDiasDeAtencion " + "WHERE s.id = :idSucursal", Turno.class)
				.setParameter("idSucursal", idSucursal).list();
		session.close();
		return lista;
	}

	public List<Turno> traerPorServicio(int idServicio) {
		iniciaOperacion();
		List<Turno> lista = session.createQuery("FROM Turno t " + "JOIN FETCH t.cliente " + "JOIN FETCH t.empleado "
				+ "JOIN FETCH t.sucursal " + "JOIN FETCH t.servicio s " + "WHERE s.id = :idServicio", Turno.class)
				.setParameter("idServicio", idServicio).list();
		session.close();
		return lista;
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

	public List<Turno> obtenerTurnosPorSucursal(int idSucursal) {
		List<Turno> turnos = new ArrayList<>();
		try {
			iniciaOperacion();
			String hql = "FROM Turno t WHERE t.sucursal.id = :idsucursal";
			Query<Turno> query = session.createQuery(hql, Turno.class).setParameter("idsucursal", idSucursal);
			turnos = query.getResultList();
			for (Turno turno : turnos) {
				System.out.println(turno);
			}
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return turnos;
	}

	public List<Turno> obtenerTurnosPorFecha(LocalDate fecha, boolean estado) {
		List<Turno> turnos = null;
		try {
			iniciaOperacion();
			LocalDateTime inicio = fecha.atStartOfDay();
			LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
			String hql = "SELECT DISTINCT t FROM Turno t " + "JOIN FETCH t.cliente " + "LEFT JOIN FETCH t.servicio "
					+ "JOIN FETCH t.sucursal s " + "LEFT JOIN FETCH s.lstDiasDeAtencion " + "JOIN FETCH t.empleado e "
					+ "LEFT JOIN FETCH e.lstEspecialidades "
					+ "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin AND t.estado = :estado";
			Query<Turno> query = session.createQuery(hql, Turno.class).setParameter("inicio", inicio)
					.setParameter("fin", fin).setParameter("estado", estado);
			;
			turnos = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return turnos;
	}

	public List<Turno> obtenerTurnosPorRangoFechas(LocalDate desde, LocalDate hasta) {
		List<Turno> turnos = null;
		try {
			iniciaOperacion();
			LocalDateTime inicio = desde.atStartOfDay();
			LocalDateTime fin = hasta.plusDays(1).atStartOfDay();
			String hql = "SELECT DISTINCT t FROM Turno t " + "JOIN FETCH t.cliente " + "LEFT JOIN FETCH t.servicio "
					+ "JOIN FETCH t.sucursal s " + "LEFT JOIN FETCH s.lstDiasDeAtencion " + "JOIN FETCH t.empleado e "
					+ "LEFT JOIN FETCH e.lstEspecialidades " + "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin";
			Query<Turno> query = session.createQuery(hql, Turno.class).setParameter("inicio", inicio)
					.setParameter("fin", fin);
			turnos = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return turnos;
	}

	public List<Turno> traerTurnosPorFechaYSucursal(LocalDate fecha, int idSucursal) {
	    LocalDateTime inicio = fecha.atStartOfDay();
	    LocalDateTime fin = inicio.plusDays(1);

	    try {
	        iniciaOperacion();
	        return session.createQuery(
	            "SELECT DISTINCT t FROM Turno t " +
	            "JOIN FETCH t.cliente " +
	            "JOIN FETCH t.servicio " +
	            "JOIN FETCH t.empleado e " +
	            "LEFT JOIN FETCH e.lstEspecialidades " +
	            "JOIN FETCH t.sucursal s " +
	            "LEFT JOIN FETCH s.lstDiasDeAtencion " +
	            "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin AND s.id = :idSucursal",
	            Turno.class)
	            .setParameter("inicio", inicio)
	            .setParameter("fin", fin)
	            .setParameter("idSucursal", idSucursal)
	            .getResultList();
	    } finally {
	        session.close();
	    }
	}
	
	public List<Turno> traerTurnosPorFechaYServicio(LocalDate fecha, int idServicio) {
	    LocalDateTime inicio = fecha.atStartOfDay();
	    LocalDateTime fin = inicio.plusDays(1);

	    try {
	        iniciaOperacion();
	        return session.createQuery(
	            "SELECT DISTINCT t FROM Turno t " +
	            "JOIN FETCH t.cliente " +
	            "JOIN FETCH t.servicio s " +
	            "JOIN FETCH t.empleado e " +
	            "LEFT JOIN FETCH e.lstEspecialidades " +
	            "JOIN FETCH t.sucursal s2 " +
	            "LEFT JOIN FETCH s2.lstDiasDeAtencion " +
	            "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin AND s.id = :idServicio",
	            Turno.class)
	            .setParameter("inicio", inicio)
	            .setParameter("fin", fin)
	            .setParameter("idServicio", idServicio)
	            .getResultList();
	    } finally {
	        session.close();
	    }
	}
	public List<Turno> traerTurnosPorFechaYCliente(LocalDate fecha, int idCliente) {
	    LocalDateTime inicio = fecha.atStartOfDay();
	    LocalDateTime fin = inicio.plusDays(1);

	    try {
	        iniciaOperacion();
	        return session.createQuery(
	            "SELECT DISTINCT t FROM Turno t " +
	            "JOIN FETCH t.cliente c " +
	            "JOIN FETCH t.servicio " +
	            "JOIN FETCH t.empleado e " +
	            "LEFT JOIN FETCH e.lstEspecialidades " +
	            "JOIN FETCH t.sucursal s " +
	            "LEFT JOIN FETCH s.lstDiasDeAtencion " +
	            "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin AND c.id = :idCliente",
	            Turno.class)
	            .setParameter("inicio", inicio)
	            .setParameter("fin", fin)
	            .setParameter("idCliente", idCliente)
	            .getResultList();
	    } finally {
	        session.close();
	    }
	}
	
	public List<Turno> traerTurnosPorFechaYEmpleado(LocalDate fecha, int idEmpleado) {
	    LocalDateTime inicio = fecha.atStartOfDay();
	    LocalDateTime fin = inicio.plusDays(1);

	    try {
	        iniciaOperacion();
	        return session.createQuery(
	            "SELECT DISTINCT t FROM Turno t " +
	            "JOIN FETCH t.cliente " +
	            "JOIN FETCH t.servicio " +
	            "JOIN FETCH t.empleado e " +
	            "LEFT JOIN FETCH e.lstEspecialidades " +
	            "JOIN FETCH t.sucursal s " +
	            "LEFT JOIN FETCH s.lstDiasDeAtencion " +
	            "WHERE t.fechaHora >= :inicio AND t.fechaHora < :fin AND e.id = :idEmpleado",
	            Turno.class)
	            .setParameter("inicio", inicio)
	            .setParameter("fin", fin)
	            .setParameter("idEmpleado", idEmpleado)
	            .getResultList();
	    } finally {
	        session.close();
	    }
	}
}