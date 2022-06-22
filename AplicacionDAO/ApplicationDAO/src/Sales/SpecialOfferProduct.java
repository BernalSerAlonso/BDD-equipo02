/*
En esta clase se encuentran los Set y Get para la  tabla SpecialOfferProduct con su respectivo tipo de dato
*/
package Sales;

import java.sql.Date;
import java.sql.Timestamp;

public class SpecialOfferProduct {
    private int SpecialOfferID;
    private int ProductID;
    private String rowguid;
    private Date ModifiedDate;
    private int aux;
    
    public int getSpecialOfferID(){
        return SpecialOfferID;
    }
    public void setSpecialOfferID(int SpecialOfferID){
        this.SpecialOfferID = SpecialOfferID;
    } 
    public int getProductID(){
        return ProductID;
    }
    public void setProductID(int ProductID){
        this.ProductID = ProductID;
    } 
    public String getrowguid(){
        return rowguid;
    }
    public void setrowguid(String rowguid){
        this.rowguid = rowguid;
    } 
    public Date getModifiedDate(){
        return ModifiedDate;
    }
    public void setModifiedDate(Date ModifiedDate){
        this.ModifiedDate = ModifiedDate;
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }
    
    
}


