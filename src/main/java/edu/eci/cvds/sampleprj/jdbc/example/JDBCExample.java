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
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {

    public static void main(String args[]) {
        try {
            String url = "jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver = "com.mysql.jdbc.Driver";
            String user = "bdprueba";
            String pwd = "prueba2019";

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, user, pwd);
            con.setAutoCommit(false);

            System.out.println("Valor total pedido 1:" + valorTotalPedido(con, 1));

            List<String> prodsPedido = nombresProductosPedido(con, 1);

            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod : prodsPedido) {
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");

            int suCodigoECI = 2165690;
            // registrarNuevoProducto(con, suCodigoECI, "Juan Rojas", 180000);
            con.commit();

            con.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Agregar un nuevo producto con los parámetros dados
     * 
     * @param con    la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre, int precio)
            throws SQLException {
        // Crear preparedStatement
        String insertProductString = "INSERT INTO ORD_PRODUCTOS VALUES(?,?,?)";
        PreparedStatement insertProduct = con.prepareStatement(insertProductString);
        // Asignar parámetros
        insertProduct.setInt(1, codigo);
        insertProduct.setString(2, nombre);
        insertProduct.setInt(3, precio);
        // usar 'execute'
        insertProduct.executeUpdate();
        con.commit();

    }

    /**
     * Consultar los nombres de los productos asociados a un pedido
     * 
     * @param con          la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np = new LinkedList<>();

        // Crear prepared statement
        String getNamesProducts = "SELECT Pr.nombre " + "FROM ORD_PRODUCTOS AS Pr "
                + "INNER JOIN ORD_DETALLE_PEDIDO AS Dp " + "ON Dp.producto_fk = Pr.codigo "
                + "WHERE Dp.pedido_fk = ? ;";
        PreparedStatement productosPedido = con.prepareStatement(getNamesProducts);
        // asignar parámetros
        productosPedido.setInt(1, codigoPedido);
        // usar executeQuery
        ResultSet resultSet = productosPedido.executeQuery();
        // Sacar resultados del ResultSet
        while (resultSet.next()) {
            np.add(resultSet.getString(1));
        }
        // Llenar la lista y retornarla
        return np;
    }

    /**
     * Calcular el costo total de un pedido
     * 
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido) throws SQLException{
        // Inicializar Variables
        int totalCost = 0;
        // Crear prepared statement
        String productName = "SELECT SUM(Po.precio*Op.cantidad) " + "FROM ORD_PRODUCTOS Po "
                + "INNER JOIN  ORD_DETALLE_PEDIDO Op " + " ON Po.codigo = Op.producto_fk " + "WHERE Op.pedido_fk = ? ;";
        PreparedStatement productoPedido = con.prepareStatement(productName);
        // asignar parámetros
        productoPedido.setInt(1, codigoPedido);
        // usar executeQuery
        ResultSet resultSet = productoPedido.executeQuery();
        // Sacar resultado del ResultSet
        while (resultSet.next()) {
            totalCost += resultSet.getInt(1);
        }
        return totalCost;
    }

}
