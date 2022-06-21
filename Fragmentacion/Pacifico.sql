--creacion de la base de datos PARA EL FRAGMENTO EUROPE
CREATE DATABASE Pacifico
GO
--SHARDING REALIZADO EN EL SERVIDOR VINCULADO -> DESKTOP-FAT50UF\BDD02
use Pacifico
GO
create schema Sales
-- FRAGMENTO DE CUSTOMER CORRESPONDIENTE A Pacific -> TerritoryID=9
SELECT c.* INTO Customer
FROM AdventureWorks2019.Sales.Customer c
where TerritoryID =9

SELECT ORH.* INTO SalesOrderHeader
FROM AdventureWorks2019.Sales.SalesOrderHeader ORH
WHERE TerritoryID in (SELECT TerritoryID
						FROM AdventureWorks2019.Sales.SalesTerritory
						WHERE [Group]='Pacific') 
go

--FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE A [GROUP] EN SalesTerritory -> 'Pacific'

--Fragmento SalesOrderDetail Pacifico A PARTIR DE UN JOIN DE SalesOrderDetail CON EL FRAGMENTO DE SalesOrderHeader CORRESPONDIENTE A PACIFIC
SELECT DISTINCT sod.* INTO SalesOrderDetail
FROM AdventureWorks2019.Sales.SalesOrderDetail sod
JOIN (SELECT * FROM Pacifico.Sales.SalesOrderHeader) st
ON sod.SalesOrderID = st.SalesOrderID
GO

--COMPROBACIONES DE LA FRAGMENTACION

Use Europa 
-- RECONSTRUCCION
Select * From [Europe].[Sales].[SalesOrderHeader] 
UNION
Select * FROM [Norteamerica].[Sales].[SalesOrderHeader]
UNION
Select * FROM [Pacifico].[Sales].[SalesOrderHeader]
GO



-- DISJUNCION
Select * From [Europe].[Sales].[SalesOrderHeader] 
INTERSECT
Select * FROM [Norteamerica].[Sales].[SalesOrderHeader]
INTERSECT
SELECT * FROM OPENQUERY([DESKTOP-FAT50UF\BDD02],
'Select * FROM [Pacifico].[Sales].[SalesOrderHeader]')
GO
