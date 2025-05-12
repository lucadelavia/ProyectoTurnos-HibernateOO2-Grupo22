package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;

public class ClienteDao{
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
	
	public int agregar(Cliente objeto) {
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
    
    public Cliente traer(int idCliente) {
    	Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.get(Cliente.class, idCliente);
		} finally {
			session.close();
		}
		return objeto;
	}

    public List<Cliente> traerTodos() {
        List<Cliente> lista = null;
        try {
            iniciaOperacion();
            lista = session.createQuery("FROM Cliente", Cliente.class).list();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public Cliente traerPorNroCliente(int nroCliente) {
        iniciaOperacion();
        Cliente c = session.createQuery("FROM Cliente c WHERE c.nroCliente = :nro", Cliente.class)
            .setParameter("nro", nroCliente)
            .uniqueResult();
        session.close();
        return c;
    }
    
    public List<Cliente> traerClientesConNroMayorA(int limite) {
        iniciaOperacion();
        List<Cliente> lista = session.createQuery(
            "FROM Cliente c WHERE c.nroCliente > :limite", Cliente.class)
            .setParameter("limite", limite)
            .list();
        session.close();
        return lista;
    }
    
	public void actualizar(Cliente objeto) {
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

	public void eliminar(Cliente objeto) {
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
