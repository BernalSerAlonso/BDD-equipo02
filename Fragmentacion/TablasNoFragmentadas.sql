Use Europe
/*
CREATE DATABASE Europa_Pacifico
GO

use Europa_Pacifico
GO*/

create schema Production 
create schema Sales

SELECT so.* INTO Sales.SpecialOffer
FROM AdventureWorks2019.Sales.SpecialOffer so
go

SELECT sop.* INTO Sales.SpecialOfferProduct
FROM AdventureWorks2019.Sales.SpecialOfferProduct sop
go

SELECT sp.* INTO Sales.SalesPerson
FROM AdventureWorks2019.Sales.SalesPerson sp
go

SELECT p.* INTO Production.Product
FROM AdventureWorks2019.Production.Product p
go

SELECT pc.* INTO Production.ProductCategory
FROM AdventureWorks2019.Production.ProductCategory pc
go

SELECT psc.* INTO Production.ProductSubcategory
FROM AdventureWorks2019.Production.ProductSubcategory psc
go

Use Europa 
-- RECONSTRUCCION
SELECT * FROM OPENQUERY([DESKTOP-FAT50UF\BDD02],'
Select * From [Europa].[dbo].[SalesOrderHeader] 
UNION
Select * FROM [Norteamerica].[dbo].[SalesOrderHeader]
UNION
Select * FROM [Pacifico].[dbo].[SalesOrderHeader]')
GO

SELECT * FROM SalesOrderDetail


-- DISJUNCION
Select * From [Europa].[dbo].[SalesOrderHeader]
INTERSECT
Select * FROM [Norteamerica].[dbo].[SalesOrderHeader]
INTERSECT
SELECT * FROM [Pacifico].[dbo].[SalesOrderHeader]
GO


