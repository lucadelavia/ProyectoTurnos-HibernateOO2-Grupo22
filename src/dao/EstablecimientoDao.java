package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Establecimiento;
import datos.Sucursal;

public class EstablecimientoDao {
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
	
	public int agregar(Establecimiento objeto) {
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
    
	public Establecimiento traer(int idEstablecimiento) {
	    Establecimiento objeto = null;
	    try {
	        iniciaOperacion();
	        objeto = (Establecimiento) session.get(Establecimiento.class, idEstablecimiento);
	        if (objeto != null) {
	            objeto.getSucursales().size(); 
	        }
	    } finally {
	        session.close();
	    }
	    return objeto;
	}
    
    public Establecimiento traerPorNombreEstablecimiento(String nombre) {
        Establecimiento est = null;
        try {
            iniciaOperacion();
            String hql = "from Establecimiento est where est.nombre = :nombre";
            est = (Establecimiento) session
                    .createQuery(hql)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return est;
    }
    
    public Establecimiento traerPorCuitEstablecimiento(String cuit) {
        Establecimiento est = null;
        try {
            iniciaOperacion();
            String hql = "from Establecimiento est where est.cuit = :cuit";
            est = (Establecimiento) session
                    .createQuery(hql)
                    .setParameter("cuit", cuit)
                    .uniqueResult();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            session.close();
        }
        return est;
    }

    public List<Establecimiento> traerTodos() {
        List<Establecimiento> lista = null;
        try {
            iniciaOperacion();
            String hql = "SELECT DISTINCT e FROM Establecimiento e " +
                         "LEFT JOIN FETCH e.sucursales s " +
                         "LEFT JOIN FETCH s.lstDiasDeAtencion";
            lista = session.createQuery(hql, Establecimiento.class).getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    
	public void actualizar(Establecimiento objeto) {
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

	public void eliminar(Establecimiento objeto) {
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
	
	public void removerSucursal(Establecimiento est, Sucursal suc) {
	    try {
	        iniciaOperacion();
	        est.getSucursales().remove(suc);
	        suc.setEstablecimiento(null);
	        session.update(suc);
	        tx.commit();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	        throw he;
	    } finally {
	        session.close();
	    }
	}
}