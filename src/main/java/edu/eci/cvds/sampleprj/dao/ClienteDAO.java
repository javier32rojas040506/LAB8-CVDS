package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;

public interface ClienteDAO {

    public void save(Cliente it) throws PersistenceException;

    public Cliente load(int id) throws PersistenceException;

    Cliente consultarCliente(long docu);

    public void agregarItemRentadoACliente( long id, int idit, Date fechainicio, Date fechafin);

    List<Cliente> consultarClientes();

    void agregarItemRentado(long docu, int id, java.sql.Date date, java.sql.Date valueOf);

    void vetar(long docu, boolean estado);
}
