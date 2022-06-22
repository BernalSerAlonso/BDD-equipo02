/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla Customer
*/
package DAO;

import Conexion.SQLServer_Sales;
import Sales.Customer;
import Interfaces.DAOCustomer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCustomerImpl extends SQLServer_Sales implements DAOCustomer {
    @Override
    public void modificar(Customer per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("  UPDATE SALES.Customer SET TerritoryID = ?, ModifiedDate = SYSDATETIME() where CustomerID = ? ");
            st.setInt(1, per.getTerritoryID());
            st.setInt(2, per.getCustomerID());

            st.executeUpdate();
            System.out.println("Modificado Correctamente");
        } catch (Exception e) {
            System.out.println("Error en Modificar");
            throw e;
        } finally {
            this.cerrar();
        }
    }


    @Override
    public List<Customer> listar() throws Exception {
        List<Customer> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP (5) * FROM sales.customer");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer pc = new Customer();
                pc.setCustomerID(rs.getInt("CustomerID"));
                pc.setPersonID(rs.getInt("PersonID"));
                pc.setStoreID(rs.getInt("StoreID"));
                pc.setTerritoryID(rs.getInt("TerritoryID"));
                pc.setAccountNumber(rs.getString("AccountNumber"));
                pc.setModifiedDate(rs.getDate("ModifiedDate"));
                
                lista.add(pc);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error en Listar");
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
}
