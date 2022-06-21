
/*
CREATE DATABASE Europa_Pacifico
GO

use Europa_Pacifico
GO*/
--SE USÓ PARA REPLICAR LAS TABLAS NECESARIAS PARA LAS CONSULTAS EN LOS FRAGMENTOS DE NorteAmerica y Europe
create schema Production 
create schema Sales
--REPLICA DE SpecialOffer
SELECT so.* INTO Sales.SpecialOffer
FROM AdventureWorks2019.Sales.SpecialOffer so
go
--REPLICA DE SpecialOfferProduct
SELECT sop.* INTO Sales.SpecialOfferProduct
FROM AdventureWorks2019.Sales.SpecialOfferProduct sop
go
--REPLICA DE SalesPerson
SELECT sp.* INTO Sales.SalesPerson
FROM AdventureWorks2019.Sales.SalesPerson sp
go
--REPLICA DE Product
SELECT p.* INTO Production.Product
FROM AdventureWorks2019.Production.Product p
go
--REPLICA DE ProductCategory
SELECT pc.* INTO Production.ProductCategory
FROM AdventureWorks2019.Production.ProductCategory pc
go
--REPLICA DE ProductSubcategory
SELECT psc.* INTO Production.ProductSubcategory
FROM AdventureWorks2019.Production.ProductSubcategory psc
go
