/*
En esta clase se encuentran los Set y Get para la  tabla SalesOrderDetail con su respectivo tipo de dato
*/
package Sales;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class SalesOrderDetail {
    private int SalesOrderID;
    private int SalesOrderDetailID;
    private String CarrierTrackingNumber;
    private int OrderQty;
    private int ProductID;
    private int SpecialOfferID;
    private BigDecimal UnitPrice;
    private BigDecimal UnitPriceDiscount;
    private BigDecimal LineTotal;
    private String rowguid;
    private Date ModifiedDate;        
    
    public int getSalesOrderID(){
        return SalesOrderID;
    }
    public void setSalesOrderID(int SalesOrderID){
        this.SalesOrderID = SalesOrderID;
    }
    public int getSalesOrderDetailID(){
        return SalesOrderDetailID;
    }
    public void setSalesOrderDetailID(int SalesOrderDetailID){
        this.SalesOrderDetailID = SalesOrderDetailID;
    }
    public String getCarrierTrackingNumber(){
        return CarrierTrackingNumber;
    }
    public void setCarrierTrackingNumber(String CarrierTrackingNumber){
        this.CarrierTrackingNumber = CarrierTrackingNumber;
    }
    public int getOrderQty(){
        return OrderQty;
    }
    public void setOrderQty(int OrderQty){
        this.OrderQty = OrderQty;
    }
    public int getProductID(){
        return ProductID;
    }
    public void setProductID(int ProductID){
        this.ProductID = ProductID;
    }
    public int getSpecialOfferID(){
        return SpecialOfferID;
    }
    public void setSpecialOfferID(int SpecialOfferID){
        this.SpecialOfferID = SpecialOfferID;
    }
    public BigDecimal getUnitPrice(){
        return UnitPrice;
    }
    public void setUnitPrice(BigDecimal UnitPrice){
        this.UnitPrice = UnitPrice;
    }
    public BigDecimal getUnitPriceDiscount(){
        return UnitPriceDiscount;
    }
    public void setUnitPriceDiscount(BigDecimal UnitPriceDiscount){
        this.UnitPriceDiscount = UnitPriceDiscount;
    }
    public BigDecimal getLineTotal(){
        return LineTotal;
    }
    public void setLineTotal(BigDecimal LineTotal){
        this.LineTotal = LineTotal;
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
