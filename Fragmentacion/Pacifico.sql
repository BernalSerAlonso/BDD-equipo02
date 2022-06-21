--FragmeSELEntacion SALESORDERHEADER 
CREATE DATABASE Pacifico
GO

use Pacifico
GO


SELECT c.* INTO Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID =9

SELECT ORH.* INTO SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
						FROM AdventureWorks2019.Sales.SalesTerritory
						WHERE [Group]='Pacific') 
go



--Fragmento SalesOrderDetail Norteamerica
SELECT DISTINCT sod.* INTO SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
JOIN (SELECT * FROM Pacifico.dbo.SalesOrderHeader) st
ON sod.SalesOrderID = st.SalesOrderID
GO



Use Europa 
-- RECONSTRUCCION
Select * From [Europa].[dbo].[SalesOrderHeader] 
UNION
Select * FROM [Norteamerica].[dbo].[SalesOrderHeader]
UNION
Select * FROM [Pacifico].[dbo].[SalesOrderHeader]
GO



-- DISJUNCION
Select * From [Europa].[dbo].[SalesOrderHeader] 
INTERSECT
Select * FROM [Norteamerica].[dbo].[SalesOrderHeader]
INTERSECT
Select * FROM [Pacifico].[dbo].[SalesOrderHeader]
GO
