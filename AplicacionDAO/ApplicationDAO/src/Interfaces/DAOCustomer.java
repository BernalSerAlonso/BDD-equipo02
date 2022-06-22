/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla Customer
*/
package Interfaces;

import Sales.Customer;
import java.util.List;
public interface DAOCustomer {
    public void modificar(Customer per) throws Exception;
    public List<Customer> listar() throws Exception;
}
