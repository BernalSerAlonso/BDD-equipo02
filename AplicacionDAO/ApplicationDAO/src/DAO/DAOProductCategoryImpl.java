/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla ProductCategory
*/
package DAO;

import Conexion.SQLServer_Production;
import Production.ProductCategory;
import Interfaces.DAOProductCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOProductCategoryImpl extends SQLServer_Production implements DAOProductCategory {

    @Override
    public void registrar(ProductCategory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO [AdventureWorks_PRODUCTION].[dbo].[ProductCategory] (Name,rowguid,ModifiedDate)VALUES (?,UPPER(NEWID()),SYSDATETIME())");
            st.setString(1, per.getName());

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
    public void modificar(ProductCategory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE [AdventureWorks_PRODUCTION].[dbo].[productcategory] SET Name= ?, rowguid= UPPER(NEWID()), ModifiedDate= SYSDATETIME() WHERE ProductCategoryID = ?;");
            st.setString(1, per.getName());
            st.setInt(2, per.getProductCategoryID());

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
    public void eliminar(ProductCategory per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM [AdventureWorks_PRODUCTION].[dbo].productcategory WHERE ProductCategoryID = ?");
            st.setInt(1, per.getProductCategoryID());

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
    public List<ProductCategory> listar() throws Exception {
        List<ProductCategory> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM [AdventureWorks_PRODUCTION].[dbo].productcategory");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductCategory pc = new ProductCategory();
                pc.setProductCategoryID(rs.getInt("ProductCategoryID"));
                pc.setName(rs.getString("Name"));
                pc.setRowguid(rs.getString("rowguid"));
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
