CREATE DATABASE Europe
GO

use Europe
GO
create schema Sales
create schema Production

SELECT c.* INTO Sales.Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID IN (7,8,10)

SELECT ORH.* INTO Sales.SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
						FROM AdventureWorks2019.Sales.SalesTerritory
						WHERE [Group]='EUROPE') 

--Fragmento SalesOrderDetail Norteamerica
SELECT DISTINCT sod.* INTO Sales.SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
JOIN (SELECT * FROM Europe.Sales.SalesOrderHeader) st
ON sod.SalesOrderID = st.SalesOrderID
GO
