/*
En esta clase se encuentran los Set y Get para la  tabla SalesOrderHeader con su respectivo tipo de dato
*/
package Sales;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.sql.Date;

public class SalesOrderHeader {
    private int SalesOrderID;
    private short RevisionNumber;
    private Timestamp OrderDate;
    private Timestamp DueDate;
    private Timestamp ShipDate;
    private int Status;
    private boolean OnlineOrderFlag;
    private String SalesOrderNumber;
    private String PurchaseOrderNumber;
    private String AccountNumber;
    private int CustomerID;
    private int SalesPersonID;
    private int TerritoryID;
    private int BillToAddressID;
    private int ShipToAddressID;
    private int ShipMethodID;
    private int CreditCardID;
    private String CreditCardApprovalCode;
    private int CurrencyRateID;
    private BigDecimal SubTotal;
    private BigDecimal TaxAmt;
    private BigDecimal Freight;
    private BigDecimal TotalDue;
    private String Comment;
    private String rowguid;
    private Date ModifiedDate;
    
    public int getSalesOrderID(){
        return SalesOrderID;
    }
    public void setSalesOrderID(int SalesOrderID){
        this.SalesOrderID = SalesOrderID;
    }
    public short getRevisionNumber(){
        return RevisionNumber;
    }
    public void setRevisionNumber(short RevisionNumber){
        this.RevisionNumber = RevisionNumber;
    }
    public Timestamp getOrderDate(){
        return OrderDate;
    }
    public void setOrderDate(Timestamp OrderDate){
        this.OrderDate = OrderDate;
    }
    public Timestamp getDueDate(){
        return DueDate;
    }
    public void setDueDate(Timestamp DueDate){
        this.DueDate = DueDate;
    }
    public Timestamp getShipDate(){
        return ShipDate;
    }
    public void setShipDate(Timestamp ShipDate){
        this.ShipDate = ShipDate;
    }
    public int getStatus(){
        return Status;
    }
    public void setStatus(int Status){
        this.Status = Status;
    }
    public boolean getOnlineOrderFlag(){
        return OnlineOrderFlag;
    }
    public void setStatus(boolean OnlineOrderFlag){
        this.OnlineOrderFlag = OnlineOrderFlag;
    }
    public String getSalesOrderNumber(){
        return SalesOrderNumber;
    }
    public void setSalesOrderNumber(String SalesOrderNumber){
        this.SalesOrderNumber = SalesOrderNumber;
    }
    public String getPurchaseOrderNumber(){
        return PurchaseOrderNumber;
    }
    public void setPurchaseOrderNumber(String PurchaseOrderNumber){
        this.PurchaseOrderNumber = PurchaseOrderNumber;
    }
    public String getAccountNumber(){
        return AccountNumber;
    }
    public void setAccountNumber(String AccountNumber){
        this.AccountNumber = AccountNumber;
    }
    public int getCustomerID(){
        return CustomerID;
    }
    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    }
    public int getSalesPersonID(){
        return SalesPersonID;
    }
    public void setSalesPersonID(int SalesPersonID){
        this.SalesPersonID = SalesPersonID;
    }
    public int getTerritoryID(){
        return TerritoryID;
    }
    public void setTerritoryID(int TerritoryID){
        this.TerritoryID = TerritoryID;
    }
    public int getBillToAddressID(){
        return BillToAddressID;
    }
    public void setBillToAddressID(int BillToAddressID){
        this.BillToAddressID = BillToAddressID;
    }
    public int getShipToAddressID(){
        return ShipToAddressID;
    }
    public void setShipToAddressID(int ShipToAddressID){
        this.ShipToAddressID = ShipToAddressID;
    }
    public int getShipMethodID(){
        return ShipMethodID;
    }
    public void setShipMethodID(int ShipMethodID){
        this.ShipMethodID = ShipMethodID;
    }
    public int getCreditCardID(){
        return CreditCardID;
    }
    public void setCreditCardID(int CreditCardID){
        this.CreditCardID = CreditCardID;
    }
    public String getCreditCardApprovalCode(){
        return CreditCardApprovalCode;
    }
    public void setCreditCardApprovalCode(String CreditCardApprovalCode){
        this.CreditCardApprovalCode = CreditCardApprovalCode;
    }
    public int getCurrencyRateID(){
        return CurrencyRateID;
    }
    public void setCurrencyRateID(int CurrencyRateID){
        this.CurrencyRateID = CurrencyRateID;
    }
    public BigDecimal getSubTotal(){
        return SubTotal;
    }
    public void setSubTotal(BigDecimal SubTotal){
        this.SubTotal = SubTotal;
    }
    public BigDecimal getTaxAmt(){
        return TaxAmt;
    }
    public void setTaxAmt(BigDecimal TaxAmt){
        this.TaxAmt = TaxAmt;
    }
    public BigDecimal getFreight(){
        return Freight;
    }
    public void setFreight(BigDecimal Freight){
        this.Freight = Freight;
    }
    public BigDecimal getTotalDue(){
        return TotalDue;
    }
    public void setTotalDue(BigDecimal TotalDue){
        this.TotalDue = TotalDue;
    }
    public String getComment(){
        return Comment;
    }
    public void setComment(String Comment){
        this.Comment = Comment;
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
