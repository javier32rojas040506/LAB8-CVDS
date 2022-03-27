package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public class MyBATISTipoItemDAO implements TipoItemDAO {
    @Inject
    private TipoItemMapper tipoItemMapper;

    @Override
    public void save(TipoItem tit) throws PersistenceException {
        try{
            tipoItemMapper.insertTipoItem(tit);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+tit.toString(),e);
        }
    }

    @Override
    public TipoItem load(int id) throws PersistenceException {
        try{
            return tipoItemMapper.getTipoItem(id);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
    }

    @Override
    public List<TipoItem> loadTiposItems() {
        return null;
    }
}
