package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;

public class MyBATISTipoItemDAO implements TipoItemDAO {
    @Override
    public void save(Item it) throws PersistenceException {

    }

    @Override
    public Item load(int id) throws PersistenceException {
        return null;
    }
}
