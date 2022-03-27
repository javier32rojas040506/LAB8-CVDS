package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBATISClienteDAO implements ClienteDAO {

    private final Map<Long,Cliente> clientes =  new HashMap<>();

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente cl) throws PersistenceException {
        try{
            clienteMapper.insertarCliente(cl);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+cl.toString(),e);
        }

    }

    @Override
    public Cliente load(int id) throws PersistenceException {
        try{
            return clienteMapper.consultarCliente(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }


    }

    @Override
    public Cliente consultarCliente(long docu) {
        return clienteMapper.consultarCliente((int)docu);
    }

    @Override
    public void agregarItemRentadoACliente(long id, int idit, Date fechainicio, Date fechafin) {

    }

    @Override
    public List<Cliente> consultarClientes() {
        return null;
    }

    @Override
    public void agregarItemRentado(long docu, int id, java.sql.Date date, java.sql.Date valueOf) {

    }

    @Override
    public void vetar(long docu, boolean estado) {

    }
}
