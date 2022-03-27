/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsImpl;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsStub;
//import jdk.internal.access.JavaSecurityAccess;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void mainLab7(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();


        //Crear el mapper y usarlo: 
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        //cm...
        System.out.println(cm.consultarClientes());
        System.out.println(cm.consultarCliente(2));
        
        
        sqlss.commit();
        sqlss.close();

    }

    /**
     * Programa que usa la logica punto 9 parte I
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler {
        ServiciosAlquiler serviciosAlquilerSt = new ServiciosAlquilerItemsStub();
        ServiciosAlquiler serviciosAlquilerIm = new ServiciosAlquilerItemsStub();
        //St
        // Crear cliente Stub
        try {
            Cliente cliente = new Cliente("Rojas FC", 2165117, "312540044", "casa", "jago@" );
            serviciosAlquilerSt.registrarCliente(cliente);
        }catch (ExcepcionServiciosAlquiler ex){
            System.out.println(ex.getMessage());
        }
        // Consultar Clientes
        try {
            List<Cliente> clientes = serviciosAlquilerIm.consultarClientes();
            System.out.println(clientes.toString());
        } catch (ExcepcionServiciosAlquiler ex){
            System.out.println(ex.getMessage());
        }


    }


}

