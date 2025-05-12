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
import datos.Usuario;

public class UsuarioDao {
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
	
	public int agregar(Usuario objeto) {
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
    
    public Usuario traer(int idUsuario) {
    	Usuario objeto = null;
		try {
			iniciaOperacion();
			objeto = (Usuario) session.get(Usuario.class, idUsuario);
		} finally {
			session.close();
		}
		return objeto;
	}
    
    public Usuario traerPorDni(int dni) {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) session
                .createQuery("FROM Usuario WHERE dni = :d")
                .setParameter("d", dni)
                .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }
    
    public Usuario traerPorEmail(String email) {
        Usuario usuario = null;
        try {
            iniciaOperacion();
            usuario = (Usuario) session
                .createQuery("FROM Usuario WHERE email = :e")
                .setParameter("e", email)
                .uniqueResult();
        } finally {
            session.close();
        }
        return usuario;
    }

	public void actualizar(Usuario objeto) {
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

	public void eliminar(Usuario objeto) {
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
	
    public List<Usuario> obtenerUsuariosPorFecha(LocalDate fecha, boolean estado) {
        List<Usuario> usuarios = null;
        try {
            iniciaOperacion();
            LocalDateTime inicio = fecha.atStartOfDay();
            LocalDateTime fin = fecha.plusDays(1).atStartOfDay();
            String hql = "SELECT DISTINCT u FROM Usuario u " +
                    "WHERE u.fechaAlta >= :inicio AND u.fechaAlta < :fin AND u.estado = :estado";
            Query<Usuario> query = session.createQuery(hql, Usuario.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .setParameter("estado", estado);
            usuarios = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return usuarios;
    }

    public List<Usuario> obtenerUsuariosPorRangoFechas(LocalDate desde, LocalDate hasta) {
        List<Usuario> usuarios = null;
        try {
            iniciaOperacion();
            LocalDateTime inicio = desde.atStartOfDay();
            LocalDateTime fin = hasta.plusDays(1).atStartOfDay();
            String hql = "SELECT DISTINCT u FROM Usuario u " +
                    "WHERE u.fechaAlta >= :inicio AND u.fechaAlta < :fin";
            Query<Usuario> query = session.createQuery(hql, Usuario.class)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin);
            usuarios = query.getResultList();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return usuarios;
    }

}
