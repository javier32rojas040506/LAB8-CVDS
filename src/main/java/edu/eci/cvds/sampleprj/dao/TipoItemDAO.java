package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;

public interface TipoItemDAO {
    public void save(Item it) throws PersistenceException;

    public Item load(int id) throws PersistenceException;
}
