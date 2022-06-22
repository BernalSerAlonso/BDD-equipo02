/*
En esta clase se DESCRIBEN los metodos que se desarrollaran con la tabla SpecialOfferProduct
*/
package Interfaces;

import Sales.SpecialOfferProduct;
import java.util.List;

public interface DAOSpecialOfferProduct {
    public void modificar(SpecialOfferProduct per) throws Exception;
    public List<SpecialOfferProduct> listar() throws Exception;
}
