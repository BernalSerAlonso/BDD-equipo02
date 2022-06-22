/*
En esta clase se encuentran los Set y Get para la  tabla Customer con su respectivo tipo de dato
*/
package Sales;

import java.sql.Date;
import java.sql.Timestamp;

public class Customer {
    private int CustomerID;
    private int PersonID;
    private int StoreID;
    private int TerritoryID;
    private String AccountNumber;
    private String rowguid;
    private Date ModifiedDate;
     
     public int getCustomerID(){
        return CustomerID;
    }
    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    } 
    public int getPersonID(){
        return PersonID;
    }
    public void setPersonID(int PersonID){
        this.PersonID = PersonID;
    } 
    public int getStoreID(){
        return StoreID;
    }
    public void setStoreID(int StoreID){
        this.StoreID = StoreID;
    }
    public int getTerritoryID(){
        return TerritoryID;
    }
    public void setTerritoryID(int TerritoryID){
        this.TerritoryID = TerritoryID;
    }
     public String getAccountNumber(){
        return AccountNumber;
    }
    public void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
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
}
