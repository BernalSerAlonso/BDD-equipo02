/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla ProductDescription
*/
package DAO;

import Conexion.SQLServer_Production;
import Production.ProductDescription;
import Interfaces.DAOProductDescription;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOProductDescriptionImpl extends SQLServer_Production implements DAOProductDescription {

    @Override
    public void registrar(ProductDescription per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO [AdventureWorks_PRODUCTION].[dbo].[ProductDescription](Description,rowguid,ModifiedDate) VALUES(?,UPPER(NEWID()),SYSDATETIME());");
            st.setString(1, per.getDescription());

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
    public void modificar(ProductDescription per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE [AdventureWorks_PRODUCTION].[dbo].productdescription SET Description= ?, rowguid= UPPER(NEWID()), ModifiedDate= SYSDATETIME() WHERE ProductDescriptionID = ?;");
            st.setString(1, per.getDescription());
            st.setInt(2, per.getProductDescriptionID());

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
    public void eliminar(ProductDescription per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM [AdventureWorks_PRODUCTION].[dbo].productdescription WHERE ProductDescriptionID = ?;");
            st.setInt(1, per.getProductDescriptionID());

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
    public List<ProductDescription> listar() throws Exception {
        List<ProductDescription> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP(10) * FROM [AdventureWorks_PRODUCTION].[dbo].productdescription ORDER BY ProductDescriptionID DESC;");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDescription pc = new ProductDescription();
                pc.setProductDescriptionID(rs.getInt("ProductDescriptionID"));
                pc.setDescription(rs.getString("Description"));
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
