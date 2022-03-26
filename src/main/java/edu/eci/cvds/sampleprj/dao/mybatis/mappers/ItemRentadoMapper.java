package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.TipoItem;

public interface ItemRentadoMapper {

    public List<ItemRentado> consultarItems();

    ItemRentado consultarItemRentado(@Param("idr") int id);

    public void insertarItemRentado(@Param("itemRentado") ItemRentado it);
}
