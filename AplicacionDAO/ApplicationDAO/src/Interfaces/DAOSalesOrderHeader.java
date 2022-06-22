/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla SalesOrderHeader
*/
package Interfaces;

import Sales.SalesOrderHeader;
import java.util.List;
public interface DAOSalesOrderHeader {
    
    public void registrar(SalesOrderHeader per) throws Exception;
    public void modificar(SalesOrderHeader per) throws Exception;
    public void eliminar(SalesOrderHeader per) throws Exception;
    public List<SalesOrderHeader> listar() throws Exception;
    public List<SalesOrderHeader> buscar() throws Exception;
}
