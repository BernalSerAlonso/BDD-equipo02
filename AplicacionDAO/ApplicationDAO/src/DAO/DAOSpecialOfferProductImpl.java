/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla SpecialOfferProduct
*/
package DAO;

import Conexion.SQLServer_Sales;
import Sales.SpecialOfferProduct;
import Interfaces.DAOSpecialOfferProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSpecialOfferProductImpl extends SQLServer_Sales implements DAOSpecialOfferProduct {

    @Override
    public void modificar(SpecialOfferProduct per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE SALES.SpecialOfferProduct SET SpecialOfferID = ?,rowguid=NEWID(), ModifiedDate = SYSDATETIME() where SpecialOfferID = ? AND ProductID = ?  ");
            st.setInt(1, per.getSpecialOfferID());//Nuevo SpecialOfferID
            st.setInt(2, per.getAux());//Antiguo SpecialOfferID
            st.setInt(3, per.getProductID()); //

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
    public List<SpecialOfferProduct> listar() throws Exception {
        List<SpecialOfferProduct> lista = null;
        SpecialOfferProduct per = new SpecialOfferProduct();
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP (5) * FROM SALES.SpecialOfferProduct order by ModifiedDate desc");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SpecialOfferProduct pc = new SpecialOfferProduct();
                pc.setSpecialOfferID(rs.getInt("SpecialOfferID"));
                pc.setProductID(rs.getInt("ProductID"));
                pc.setrowguid(rs.getString("rowguid"));
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
