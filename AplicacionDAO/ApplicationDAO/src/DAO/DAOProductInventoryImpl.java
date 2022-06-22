/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla ProductInventory
*/
package DAO;

import Conexion.SQLServer_Production;
import Production.ProductInventory;
import Interfaces.DAOProductInventory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOProductInventoryImpl extends SQLServer_Production implements DAOProductInventory {

    @Override
    public void registrar(ProductInventory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO [AdventureWorks_PRODUCTION].[dbo].productinventory(ProductID,LocationID,Shelf,Bin,Quantity,rowguid,ModifiedDate) VALUES(?,?,?,?,?,UPPER(NEWID()),SYSDATETIME());");
            st.setInt(1, per.getProductID());
            st.setInt(2, per.getLocationID());
            st.setString(3, per.getShelf().toUpperCase());
            st.setInt(4, per.getbin());
            st.setInt(5, per.getQuantity());

            st.executeUpdate();
            System.out.println("Registrado Correctamente");
        } catch (Exception e) {
            System.out.println("Error en Registrar");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(ProductInventory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE [AdventureWorks_PRODUCTION].[dbo].productinventory SET Quantity=? ,rowguid= UPPER(NEWID()), ModifiedDate= SYSDATETIME() WHERE ProductID = ? AND LocationID = ?;");
            st.setInt(1, per.getQuantity());
            st.setInt(2, per.getProductID());
            st.setInt(3, per.getLocationID());

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
    public void eliminar(ProductInventory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM [AdventureWorks_PRODUCTION].[dbo].productinventory WHERE ProductID = ? AND LocationID = ?;");
            st.setInt(1, per.getProductID());
            st.setInt(2, per.getLocationID());

            st.executeUpdate();
            System.out.println("Eliminado Correctamente");
        } catch (Exception e) {
            System.out.println("Error en Eliminar");
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<ProductInventory> listar() throws Exception {
        List<ProductInventory> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP(10) * FROM [AdventureWorks_PRODUCTION].[dbo].productinventory ORDER BY ModifiedDate DESC;");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductInventory per = new ProductInventory();
                per.setProductID(rs.getInt("ProductID"));
                per.setLocationID(rs.getInt("LocationID"));
                per.setShelf(rs.getString("Shelf"));
                per.setQuantity(rs.getInt("Quantity"));
                per.setrowguid(rs.getString("rowguid"));
                per.setModifiedDate(rs.getDate("ModifiedDate"));
                lista.add(per);
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
