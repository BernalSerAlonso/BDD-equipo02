/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla Product
*/
package DAO;

import Conexion.SQLServer_Production;
import Production.Product;
import Interfaces.DAOProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOProductImpl extends SQLServer_Production implements DAOProduct {

    @Override
    public void registrar(Product per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO [AdventureWorks_PRODUCTION].[dbo].[Product]"
                    + "		(Name, ProductNumber,MakeFlag,FinishedGoodsFlag,Color,"
                    + "		SafetyStockLevel,ReorderPoint,StandardCost,ListPrice,Size,"
                    + "		SizeUnitMeasureCode,WeightUnitMeasureCode,Weight,DaysToManufacture,ProductLine,"
                    + "		Class,Style,ProductSubcategoryID,ProductModelID,SellStartDate,"
                    + "		SellEndDate,DiscontinuedDate,rowguid,ModifiedDate)"
                    + "VALUES(?,?,0,0,NULL,?,?,?,?,NULL,"
                    + "        NULL,NULL,NULL,0,NULL,"
                    + "        NULL,NULL,NULL,NULL,SYSDATETIME(),"
                    + "        NULL,NULL,UPPER(NEWID()),SYSDATETIME())");
            st.setString(1, per.getName());
            st.setString(2, per.getProductNumber());
            st.setShort(3, per.getSafetyStockLevel());
            st.setShort(4, per.getReorderPoint());
            st.setFloat(5, per.getStandardCost());
            st.setFloat(6, per.getListPrice());

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
    public void modificar(Product per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE [AdventureWorks_PRODUCTION].[dbo].[Product] SET StandardCost = ?,ModifiedDate = SYSDATETIME() WHERE ProductID = ?;");
            st.setFloat(1, per.getStandardCost());
            st.setInt(2, per.getProductID());

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
    public void eliminar(Product per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM [AdventureWorks_PRODUCTION].[dbo].[Product] WHERE ProductID = ?;");
            st.setInt(1, per.getProductID());

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
    public List<Product> listar() throws Exception {
        List<Product> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT  TOP(10) * FROM [AdventureWorks_PRODUCTION].[dbo].Product ORDER BY ProductID DESC;");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product per = new Product();
                per.setProductID(rs.getInt("ProductID"));
                per.setName(rs.getString("Name"));
                per.setProductNumber(rs.getString("ProductNumber"));
                per.setStandardCost(rs.getFloat("StandardCost"));
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
