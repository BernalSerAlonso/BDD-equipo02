/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla SalesOrderDetail
 */
package DAO;

import Conexion.SQLServer_Sales;
import Sales.SalesOrderDetail;
import Interfaces.DAOSalesOrderDetail;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DAOSalesOrderDetailImpl extends SQLServer_Sales implements DAOSalesOrderDetail {

    @Override
    public void modificar(SalesOrderDetail per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE SALES.SalesOrderDetail SET OrderQty=? WHERE SalesOrderID = ? AND ProductID = ?");
            st.setInt(1, per.getOrderQty());
            st.setInt(2, per.getSalesOrderID());
            st.setInt(3, per.getProductID());

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
    public void eliminar(SalesOrderDetail per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM SALES.SalesOrderDetail WHERE SalesOrderID = ?");
            st.setInt(1, per.getSalesOrderID());

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
    public List<SalesOrderDetail> listar() throws Exception {
        List<SalesOrderDetail> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP (5) * FROM SALES.SalesOrderDetail");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SalesOrderDetail per = new SalesOrderDetail();
                per.setSalesOrderID(rs.getInt("SalesOrderID"));
                per.setSalesOrderDetailID(rs.getInt("SalesOrderDetailID"));
                per.setCarrierTrackingNumber(rs.getString("CarrierTrackingNumber"));
                per.setProductID(rs.getInt("ProductID"));
                per.setOrderQty(rs.getInt("OrderQty"));
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

    @Override
    public List<SalesOrderDetail> buscar(SalesOrderDetail pe) throws Exception {
        List<SalesOrderDetail> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM SALES.SalesOrderDetail WHERE SalesOrderID =? ORDER BY SalesOrderID DESC");
            st.setInt(1, pe.getSalesOrderID());

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SalesOrderDetail per = new SalesOrderDetail();
                per.setSalesOrderID(rs.getInt("SalesOrderID"));
                per.setSalesOrderDetailID(rs.getInt("SalesOrderDetailID"));
                per.setCarrierTrackingNumber(rs.getString("CarrierTrackingNumber"));
                per.setProductID(rs.getInt("ProductID"));
                per.setOrderQty(rs.getInt("OrderQty"));
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
