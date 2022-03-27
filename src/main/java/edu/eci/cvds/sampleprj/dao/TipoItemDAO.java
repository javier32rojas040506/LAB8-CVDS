package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.exceptions.PersistenceException;

public interface TipoItemDAO {
    public void save(TipoItem it) throws PersistenceException;

    public TipoItem load(int id) throws PersistenceException;
}
