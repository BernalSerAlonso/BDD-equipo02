--Listado de consultas a programar para anlizar planes de ejecución

--1. Listar los casos potivos por entidad de residencia


SELECT ENTIDAD_RES Entidad_Residencia, count(*) Casos_Confirmados
--Indicamos que queremos extraer la informacion de la entidad de residencia, y que cuente los datos confirmados
FROM dbo.datoscovid
WHERE CLASIFICACION_FINAL between 1 and 3
--de la tabla donde en la clasificacion tengamos un valor entre 1 y 3
group by ENTIDAD_RES
--agrupado por la entidad de residencia
order by Casos_Confirmados desc;
--ordenado de manera descendente

--Consulta sobre la entidad de residencia, buscando casos con clasificacion entre 1 y 3, ordenados por la entidad, sobre el indice agrupado
--de la clasificacion final

SELECT ENTIDAD_RES Entidad_Residencia, count(*) Casos_Confirmados
--Indicamos que queremos extraer la informacion de la entidad de residencia, y que cuente los datos confirmados
FROM dbo.datoscovid_CLASIFIACION
WHERE CLASIFICACION_FINAL between 1 and 3
--de la tabla donde en la clasificacion tengamos un valor entre 1 y 3
group by ENTIDAD_RES
--agrupado por la entidad de residencia
order by Casos_Confirmados desc;
--ordenado de manera descendente

--2. Listar los casos sospechosos por entidad

SELECT ENTIDAD_RES Entidad_Residencia, count(*) Casos_Sospechosos
--Extraemos la informacion de la entidad de residencia y contamos los casos sospechosos
FROM dbo.datoscovid
--Indicamos la tabla original
WHERE CLASIFICACION_FINAL = 6
--buscamos la clasificacion de sospechoso
group by ENTIDAD_RES
--agrupamos por entidad de residencia
order by Casos_Sospechosos desc;
--ordenamos de manera descendente para mostar la entidad con mas casos a la menor

SELECT ENTIDAD_RES Entidad_Residencia, count(*) Casos_Sospechosos
--Extraemos la informacion de la entidad de residencia y contamos los casos sospechosos
FROM dbo.datoscovid_CLASIFIACION
--Indicamos la tabla copia con indice agrupado
WHERE CLASIFICACION_FINAL = 6
--buscamos la clasificacion de sospechoso
group by ENTIDAD_RES
--agrupamos por entidad de residencia
order by Casos_Sospechosos desc;
--ordenamos de manera descendente para mostar la entidad con mas casos a la menor

--3. Listar el top 5 de municipios por entidad con el mayor número de casos reportados, 
--indicando casos sospechosos y casos confirmados.

select TOP 5
    cc.ENTIDAD_RES Entidad, cc.MUNICIPIO_RES Municipio, cc.confirmado, cs.sospechoso
--extraemos los 5 primeros registros de la entidad de residencia
--el municipio que representa y la cantidad de confirmados y sospechosos
from (select ENTIDAD_RES, MUNICIPIO_RES, count(*) as Sospechoso
    --realizamos una subconsulta para determinar los casos sospechosos, y su entidad y municipio
    from dbo.datoscovid
    where CLASIFICACION_FINAL = 6
    --indicamos la clasificacion para sospechoso
    group by ENTIDAD_RES, MUNICIPIO_RES                                                 --y se agrupa por entidad y luego por municipio
	      ) cs --recibe un nombre temporal
    inner join --relacionamos la cantidad de sospechosos con el siguiente dato
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Confirmado
    --realizamos una subconsulta para determinar los casos confirmados, y su entidad y municipio
    from dbo.datoscovid
    where CLASIFICACION_FINAL between 1 and 3
    --indicamos la clsificacion para confirmados
    group by ENTIDAD_RES, MUNICIPIO_RES) cc --y se agrupa por entidad y luego por municipio
    on cc.ENTIDAD_RES =  cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cc.MUNICIPIO_RES
--agregamos la relacion entre ambas tablas para encontrar
order by confirmado desc, cs.sospechoso desc;
--ordemos la informacion de mayor a menor en ambas consultas

select TOP 5
    cc.ENTIDAD_RES Entidad, cc.MUNICIPIO_RES Municipio, cc.confirmado, cs.sospechoso
--extraemos los 5 primeros registros de la entidad de residencia
--el municipio que representa y la cantidad de confirmados y sospechosos
from (select ENTIDAD_RES, MUNICIPIO_RES, count(*) as Sospechoso
    --realizamos una subconsulta para determinar los casos sospechosos, y su entidad y municipio
    from dbo.datoscovid_CLASIFIACION
    where CLASIFICACION_FINAL = 6
    --indicamos la clsificacion para confirmados de la tabla con indice agrupado
    group by ENTIDAD_RES, MUNICIPIO_RES                                                 --y se agrupa por entidad y luego por municipio
	      ) cs --recibe un nombre temporal
    inner join --relacionamos la cantidad de sospechosos con el siguiente dato
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Confirmado
    --realizamos una subconsulta para determinar los casos confirmados, y su entidad y municipio
    from dbo.datoscovid_CLASIFIACION
    where CLASIFICACION_FINAL between 1 and 3
    --indicamos la clasificacion para sospechoso de la tabla con indice agrupado
    group by ENTIDAD_RES, MUNICIPIO_RES) cc --y se agrupa por entidad y luego por municipio
    on cc.ENTIDAD_RES =  cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cc.MUNICIPIO_RES
--agregamos la relacion entre ambas tablas para encontrar
order by confirmado desc, cs.sospechoso desc;
--ordemos la informacion de mayor a menor en ambas consultas


--4. Determinar el municipio con el mayor número de defunciones en casos confirmados.

SELECT TOP 3
    MUNICIPIO_RES Municipio_Residencia, count(*) Defunciones_CasosConfirmados
--Colocamos un top 3 para poder ver la diferencia entre estados
--contamos el numero de casos
FROM dbo.datoscovid
--de la tabla original
WHERE FECHA_DEF != '9999-99-99' AND CLASIFICACION_FINAL between 1 and 3
--la fecha que aparece en el campo es la que tiene por defaul
--cuando la persona no tiene fecha de fallecimiento, por eso pedimos que sea diferente 
group by MUNICIPIO_RES
--agrupadmos por municipio
order by Defunciones_CasosConfirmados desc;
--ordenamos de mayor a menor

SELECT TOP 3
    MUNICIPIO_RES Municipio_Residencia, count(*) Defunciones_CasosConfirmados
--Colocamos un top 3 para poder ver la diferencia entre estados
--contamos el numero de casos
FROM dbo.datoscovid_CLASIFIACION
--de la tabla con indices agrupados
WHERE FECHA_DEF != '9999-99-99' AND CLASIFICACION_FINAL between 1 and 3
--la fecha que aparece en el campo es la que tiene por defaul
--cuando la persona no tiene fecha de fallecimiento, por eso pedimos que sea diferente 
group by MUNICIPIO_RES
--agrupadmos por municipio
order by Defunciones_CasosConfirmados desc;
--ordenamos de mayor a menor

--5. Determinar por entidad, si de casos sospechosos hay defunciones reportadas asociadas a neumonia.


SELECT ENTIDAD_UM Entidad, count(*) Defunciones_porNeumonia
--Solicitamos la informacion de la entidad medica y el recuento de defunciones
FROM dbo.datoscovid
--de la tabla original
WHERE CLASIFICACION_FINAL=6 AND FECHA_DEF != '9999-99-99' AND NEUMONIA = 1
--ocupamos 3 condiones, para saber casos sospechosos (6)
--y si dieron positivo a neumonia	
group by ENTIDAD_UM
--agrupamos por en entidad medica
order by Defunciones_porNeumonia desc;
--ordenamos de mayor a menor

SELECT ENTIDAD_UM Entidad, count(*) Defunciones_porNeumonia
--Solicitamos la informacion de la entidad medica y el recuento de defunciones
FROM dbo.datoscovid_CLASIFIACION
--de la tabla con indices agrupados
WHERE CLASIFICACION_FINAL=6 AND FECHA_DEF != '9999-99-99' AND NEUMONIA = 1
--ocupamos 3 condiones, para saber casos sospechosos (6)
--y si dieron positivo a neumonia	
group by ENTIDAD_UM
--agrupamos por en entidad medica
order by Defunciones_porNeumonia desc;
--ordenamos de mayor a menor

--6. Listar por entidad el total de casos sospechosos, casos confirmados, total de defunciones en los meses 
de marzo a agosto 2020 y de diciembre 2020 a mayo 2021.

select cc.ENTIDAD_RES Entidad, cc.MUNICIPIO_RES Municipio, cc.Confirmado1 CasosConfirmados, cs.sospechoso CasosSospechosos, ccc.Defunciones Defunciones_MAR20_AGO20, cct.Defunciones2 Defunciones_DIC20_MAY21
from --Solicitamos la entidad de residencia, el municipio de residencia
    --Y las respectivas cuentas de casos y defunciones
    (select ENTIDAD_RES, MUNICIPIO_RES, count(*) as Sospechoso
    --primero calculamoslos casos sospechosos
    from dbo.datoscovid
    where CLASIFICACION_FINAL = 6
    --clasificacion de sospechoso
    group by ENTIDAD_RES, MUNICIPIO_RES) as cs --agrupamos por entidad y municipio
    inner join --primera relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Confirmado1
    --consulta por confirmados
    from dbo.datoscovid
    where CLASIFICACION_FINAL between 1 and 3
    --clasificacion de confirmados
    group by ENTIDAD_RES, MUNICIPIO_RES) as cc --agrupamos por entidad y municipio	
    on cc.ENTIDAD_RES =  cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cc.MUNICIPIO_RES --condicion de reunion
    inner join --segunda relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Defunciones
    --calculamos defunciones de mar-ago
    from dbo.datoscovid
    where FECHA_DEF BETWEEN '2021-03-01' AND '2021-08-31'
    --usamos la condiciones entre con las 2 fechas solicitadas
    group by ENTIDAD_RES, MUNICIPIO_RES) as ccc --agrupamos
    on ccc.ENTIDAD_RES = cs.ENTIDAD_RES and cs.MUNICIPIO_RES = ccc.MUNICIPIO_RES --condicion de reunion con la primer relacion de datos
    inner join --tercera relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Defunciones2
    --calculamos defunciones de dic-abr
    from dbo.datoscovid
    where FECHA_DEF BETWEEN '2020-12-01' AND '2021-04-31'
    --usamos la condiciones entre con las 2 fechas solicitadas
    group by ENTIDAD_RES, MUNICIPIO_RES) as cct --agrupamos
    on cct.ENTIDAD_RES = cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cct.MUNICIPIO_RES
--condicion de reunion 
order by Entidad asc, Municipio asc, Confirmado1 desc, Sospechoso desc;
--ordenamos entidad de menor a mayor, municipio de menor a mayo
--confirmados me mayor a menor, sospechosos de mayor a menor

select cc.ENTIDAD_RES Entidad, cc.MUNICIPIO_RES Municipio, cc.Confirmado1 CasosConfirmados, cs.sospechoso CasosSospechosos, ccc.Defunciones Defunciones_MAR20_AGO20, cct.Defunciones2 Defunciones_DIC20_MAY21
from --Solicitamos la entidad de residencia, el municipio de residencia
    --Y las respectivas cuentas de casos y defunciones
    (select ENTIDAD_RES, MUNICIPIO_RES, count(*) as Sospechoso
    --primero calculamoslos casos sospechosos en tabla agrupada
    from dbo.datoscovid_CLASIFIACION
    where CLASIFICACION_FINAL = 6
    --clasificacion de sospechoso
    group by ENTIDAD_RES, MUNICIPIO_RES) as cs --agrupamos por entidad y municipio
    inner join --primera relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Confirmado1
    --consulta por confirmados
    from dbo.datoscovid_CLASIFIACION
    where CLASIFICACION_FINAL between 1 and 3
    --clasificacion de confirmados en tabla agrupada
    group by ENTIDAD_RES, MUNICIPIO_RES) as cc --agrupamos por entidad y municipio	
    on cc.ENTIDAD_RES =  cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cc.MUNICIPIO_RES --condicion de reunion
    inner join --segunda relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Defunciones
    --calculamos defunciones de mar-ago
    from dbo.datoscovid_CLASIFIACION
    where FECHA_DEF BETWEEN '2021-03-01' AND '2021-08-31'
    --usamos la condiciones entre con las 2 fechas solicitadas en tabla agrupada
    group by ENTIDAD_RES, MUNICIPIO_RES) as ccc --agrupamos
    on ccc.ENTIDAD_RES = cs.ENTIDAD_RES and cs.MUNICIPIO_RES = ccc.MUNICIPIO_RES --condicion de reunion con la primer relacion de datos
    inner join --tercera relacion de datos
    (select ENTIDAD_RES, MUNICIPIO_RES, count (*) as Defunciones2
    --calculamos defunciones de dic-abr
    from dbo.datoscovid_CLASIFIACION
    where FECHA_DEF BETWEEN '2020-12-01' AND '2021-04-31'
    --usamos la condiciones entre con las 2 fechas solicitadas en tabla agrupada
    group by ENTIDAD_RES, MUNICIPIO_RES) as cct --agrupamos
    on cct.ENTIDAD_RES = cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cct.MUNICIPIO_RES
--condicion de reunion 
order by Entidad asc, Municipio asc, Confirmado1 desc, Sospechoso desc;
--ordenamos entidad de menor a mayor, municipio de menor a mayo
--confirmados me mayor a menor, sospechosos de mayor a menor

--7. Listar los 5 municipios con el mayor número de casos confirmados en niños menores de 13 años con alguna comorbilidad reportada y cuantos de esos casos fallecieron.

select TOP 5
    cs.MUNICIPIO_RES Municipio, cs.Confirmados NiñosConfirmados , cc.Defunciones
--Solicitamos el municipio, el total de niñor confirmados con obesidad y la defunciones
from (SELECT MUNICIPIO_RES, count(*) as Confirmados
    --Subconsulta para niños Confirmados
    FROM dbo.datoscovid
    --de tabla original
    WHERE CLASIFICACION_FINAL BETWEEN 1 AND 3 AND EDAD < 13 AND OBESIDAD = 1
    --clasificacion de confirmados, menores de 13, y obsesidad confirmada
    group by MUNICIPIO_RES) as cs --agrupamos
    INNER JOIN --relacionamos confirmados con defunciones
    (SELECT MUNICIPIO_RES, count(*) as Defunciones
    --subconsulta para las defunciones de esos niños
    from dbo.datoscovid
    --de la tabla original
    where FECHA_DEF != '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3 AND EDAD < 13 AND OBESIDAD = 1--confirmados, menores de 13, con obsesidad y que hayan fallecido
    group by MUNICIPIO_RES ) as cc --agrupamos
    on cs.MUNICIPIO_RES = cc.MUNICIPIO_RES
--condicion de reunion
order by cs.Confirmados desc
--ordenamos confirmados de mayor a menor

select TOP 5
    cs.MUNICIPIO_RES Municipio, cs.Confirmados NiñosConfirmados , cc.Defunciones
--Solicitamos el municipio, el total de niñor confirmados con obesidad y la defunciones
from (SELECT MUNICIPIO_RES, count(*) as Confirmados
    --Subconsulta para niños Confirmados
    FROM dbo.datoscovid_CLASIFIACION
    --de tabla original
    WHERE CLASIFICACION_FINAL BETWEEN 1 AND 3 AND EDAD < 13 AND OBESIDAD = 1
    --clasificacion de confirmados, menores de 13, y obsesidad confirmada
    group by MUNICIPIO_RES) as cs --agrupamos
    INNER JOIN --relacionamos confirmados con defunciones
    (SELECT MUNICIPIO_RES, count(*) as Defunciones
    --subconsulta para las defunciones de esos niños
    from dbo.datoscovid_CLASIFIACION
    --de la tabla original
    where FECHA_DEF != '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3 AND EDAD < 13 AND OBESIDAD = 1--confirmados, menores de 13, con obsesidad y que hayan fallecido
    group by MUNICIPIO_RES ) as cc --agrupamos
    on cs.MUNICIPIO_RES = cc.MUNICIPIO_RES
--condicion de reunion
order by cs.Confirmados desc
--ordenamos confirmados de mayor a menor


--8. Determinar si en el año 2020 hay una mayor cantidad de defunciones menores de edad que en el año 2021 y 2022.	  

SELECT c1.def Defunciones2020, (c2.def2 + c3.def3) "Defunciones21+22", CASE when c1.def>(c2.def2 + c3.def3) then 'Mayor' else 'Fue menor' end AS Proporcion20vs21
FROM --Total de defunciones en 2020, en 21 sumandole 22 y la condicion para saber cual es mayor
    (select count(*) as def
    from dbo.datoscovid
    where FECHA_ACTUALIZACION LIKE '%2020%' AND EDAD < 18) c1, --Subconsulta con la condicion de que la fecha sea 2020 y sean menores de edad
    (select count(*) as def2
    from dbo.datoscovid
    where FECHA_ACTUALIZACION LIKE '%2021%' AND EDAD < 18) c2,--Subconsulta con la condicion de que la fecha sea 2021 y sean menores de edad
    (select count(*) as def3
    from dbo.datoscovid
    where FECHA_ACTUALIZACION LIKE '%2022%' AND EDAD < 18) c3;--Subconsulta con la condicion de que la fecha sea 2022 y sean menores de edad

SELECT c1.def Defunciones2020, (c2.def2 + c3.def3) "Defunciones21+22", CASE when c1.def>(c2.def2 + c3.def3) then 'Mayor' else 'Fue menor' end AS Proporcion20vs21
FROM
    (select count(*) as def
    from dbo.datoscovid_CLASIFIACION
    where FECHA_ACTUALIZACION LIKE '%2020%' AND EDAD < 18) c1, --Subconsulta con la condicion de que la fecha sea 2020 y sean menores de edad, de la tabla copia
    (select count(*) as def2
    from dbo.datoscovid_CLASIFIACION
    where FECHA_ACTUALIZACION LIKE '%2021%' AND EDAD < 18) c2,--Subconsulta con la condicion de que la fecha sea 2021 y sean menores de edad, de la tabla copia
    (select count(*) as def3
    from dbo.datoscovid_CLASIFIACION
    where FECHA_ACTUALIZACION LIKE '%2022%' AND EDAD < 18) c3;--Subconsulta con la condicion de que la fecha sea 2022 y sean menores de edad, de la tabla copia

--9. Determinar si en el año 2021 hay un pocentaje mayor al 60 de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2020.
SELECT cs.EST2021, cc.EST2020, CONCAT(100-ROUND((CAST(cc.EST2020 AS float)/CAST(cs.EST2021 AS FLOAT))*100,0),'%') as Porcentaje
--Solcitiamos los datos de la subconsulta 2021 y 2020
--Cast convierte valores (de int a float)
--ROUND redondea una valor decimar
--dividimos 2021 entre 2020 y multiplicamos por 100 para sacar el porcentaje
--CONCAT une 2 cadenas de texto, el numero obtenido y el %, y lo restamos de 100
FROM
    (	SELECT count(*) as EST2021
    --subconsulta de 2021
    FROM dbo.datoscovid
    WHERE FECHA_SINTOMAS LIKE '%2021%' AND RESULTADO_ANTIGENO = 1) AS cs, --condicion de año y resultados por lab
    (	SELECT count(*) as EST2020
    --subconsulta de 2020
    FROM dbo.datoscovid
    WHERE FECHA_SINTOMAS LIKE '%2020%' AND RESULTADO_ANTIGENO = 1) AS cc
--condicion de año y resultados por lab



SELECT cs.EST2021, cc.EST2020, CONCAT(100-ROUND((CAST(cc.EST2020 AS float)/CAST(cs.EST2021 AS FLOAT))*100,0),'%') as Porcentaje
--Solcitiamos los datos de la subconsulta 2021 y 2020
--Cast convierte valores (de int a float)
--ROUND redondea una valor decimar
--dividimos 2021 entre 2020 y multiplicamos por 100 para sacar el porcentaje
--CONCAT une 2 cadenas de texto, el numero obtenido y el %, y lo restamos de 100
FROM
    (	SELECT count(*) as EST2021
    --subconsulta de 2021
    FROM dbo.datoscovid_CLASIFIACION
    WHERE FECHA_SINTOMAS LIKE '%2021%' AND RESULTADO_ANTIGENO = 1) AS cs, --condicion de año y resultados por lab, en tabla copia
    (	SELECT count(*) as EST2020
    --subconsulta de 2020
    FROM dbo.datoscovid_CLASIFIACION
    WHERE FECHA_SINTOMAS LIKE '%2020%' AND RESULTADO_ANTIGENO = 1) AS cc
--condicion de año y resultados por lab, en tabla copia


--10. Determinar en que rango de edad: menor de edad, 19 a 40, 40 a 60 o mayor de 60 hay mas casos reportados que se hayan recuperado. 


SELECT c1.def Menor19, c2.def2 De19a40, c3.def2 De40a60, c4.def2 Mayor60,
    CASE 
WHEN c1.def>c2.def2 and c1.def>c3.def2 and c1.def>c4.def2 then 'Mas muertes en menores de 19' --caso para menores de 19
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c2.def2>c1.def and c2.def2>c3.def2 and c2.def2>c4.def2 then 'Mas muertes entre 19 y 40'  --caso para entre 19 y 40
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c3.def2>c1.def and c3.def2>c2.def2 and c3.def2>c4.def2 then 'Mas muertes 40 y 60' --caso para entre 40 y 60
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c4.def2>c1.def and c4.def2>c2.def2 and c4.def2>c3.def2 then 'Mas muertes en mayores de 60' --caso para mayores de 60
end --end representa el fin de un CASE
end
end
end
FROM
    (select count(*) as def
    --Cuenta de menores de 19
    from dbo.datoscovid
    where EDAD < 19 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c1,
    (select count(*) as def2
    --Cuenta de entre 19 y 40
    from dbo.datoscovid
    where EDAD BETWEEN 19 AND 40 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c2,
    (select count(*) as def2
    --Entre 40 y 60
    from dbo.datoscovid
    where EDAD BETWEEN 40 AND 60 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c3,
    (select count(*) as def2
    --Mayores a 60
    from dbo.datoscovid
    where EDAD > 60 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c4



SELECT c1.def Menor19, c2.def2 De19a40, c3.def2 De40a60, c4.def2 Mayor60,
    CASE 
WHEN c1.def>c2.def2 and c1.def>c3.def2 and c1.def>c4.def2 then 'Mas muertes en menores de 19' --caso para menores de 19
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c2.def2>c1.def and c2.def2>c3.def2 and c2.def2>c4.def2 then 'Mas muertes entre 19 y 40' --caso para entre 19 y 40
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c3.def2>c1.def and c3.def2>c2.def2 and c3.def2>c4.def2 then 'Mas muertes 40 y 60' --caso para entre 50 y 60
else --condicion para saber si este segmento es el mas grande
CASE 
WHEN c4.def2>c1.def and c4.def2>c2.def2 and c4.def2>c3.def2 then 'Mas muertes en mayores de 60'   --caso para mayores de 60
end	--end representa el fin de un CASE
end
end
end
FROM
    (select count(*) as def
    --Cuenta de menores de 19
    from dbo.datoscovid_CLASIFIACION
    where EDAD < 19 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c1, --condicion de confirmados con recuperados
    (select count(*) as def2
    --Cuenta de entre 19 y 40
    from dbo.datoscovid_CLASIFIACION
    where EDAD BETWEEN 19 AND 40 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c2,
    (select count(*) as def2
    --Entre 40 y 60
    from dbo.datoscovid_CLASIFIACION
    where EDAD BETWEEN 40 AND 60 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c3,
    (select count(*) as def2
    --Mayores a 60
    from dbo.datoscovid_CLASIFIACION
    where EDAD > 60 AND FECHA_DEF = '9999-99-99' AND CLASIFICACION_FINAL BETWEEN 1 AND 3) as c4