/*
En esta clase se IMPLEMENTAN los metodos que se desarrollaran con la tabla SalesOrderHeader
*/
package DAO;

import Conexion.SQLServer_Sales;
import Sales.SalesOrderHeader;
import Interfaces.DAOSalesOrderHeader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSalesOrderHeaderImpl extends SQLServer_Sales implements DAOSalesOrderHeader {
    @Override
    public void registrar(SalesOrderHeader per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("Insert Into Alumnos (nombres , apellidos) values (?,?); ");
            //st.setString(1, per.getNombre());
            //st.setString(2, per.getApellido());

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
    public void modificar(SalesOrderHeader per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE SALES.SalesOrderHeader SET Status=? WHERE SalesOrderID = ?");
            st.setInt(1, per.getStatus());
            st.setInt(2, per.getSalesOrderID());

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
    public void eliminar(SalesOrderHeader per) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE FROM SALES.SalesOrderHeader WHERE SalesOrderID = ?");
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
    public List<SalesOrderHeader> listar() throws Exception {
        List<SalesOrderHeader> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP (5) * FROM SALES.SalesOrderHeader");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SalesOrderHeader per = new SalesOrderHeader();
                per.setSalesOrderID(rs.getInt("SalesOrderID"));
                per.setStatus(rs.getInt("Status"));
                per.setTotalDue(rs.getBigDecimal("TotalDue"));
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
    public List<SalesOrderHeader> buscar() throws Exception {
        List<SalesOrderHeader> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT TOP (1) * FROM SALES.SalesOrderHeader WHERE SalesOrderID > 75123 ORDER BY SalesOrderID DESC");
            
            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SalesOrderHeader per = new SalesOrderHeader();
                per.setSalesOrderID(rs.getInt("SalesOrderID"));
                per.setStatus(rs.getInt("Status"));
                per.setTotalDue(rs.getBigDecimal("TotalDue"));
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
