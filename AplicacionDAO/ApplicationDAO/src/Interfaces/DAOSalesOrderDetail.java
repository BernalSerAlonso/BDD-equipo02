/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla SalesOrderDetail
*/
package Interfaces;

import Sales.SalesOrderDetail;
import java.util.List;
public interface DAOSalesOrderDetail {
    
    public void modificar(SalesOrderDetail per) throws Exception;
    public void eliminar(SalesOrderDetail per) throws Exception;
    public List<SalesOrderDetail> listar() throws Exception;
    public List<SalesOrderDetail> buscar(SalesOrderDetail per) throws Exception;
}
