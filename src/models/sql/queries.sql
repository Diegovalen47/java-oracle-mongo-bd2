-- Generos
select c.genero, s.nombre, p.tipo, sum(nro_unidades) as TotalUni
from cliente c join venta v on c.codigo = v.cliente join producto p on p.codigo = v.producto join sucursal s on s.codigo = v.sucursal
group by c.genero, s.nombre, p.tipo
order by c.genero

-- Marcas
select s.NOMBRE as sucursal, sum(v.NRO_UNIDADES) as totalUni, p.marca, p.TIPO
from PRODUCTO p join venta v on p.CODIGO = v.PRODUCTO join SUCURSAL s on  v.SUCURSAL = s.CODIGO
group by s.nombre, p.TIPO, p.marca
order by p.marca

-- Vendedores
select vd.codigo, vd.nombre, s.nombre as sucursal, sum(nro_unidades) as TotalUni
from vendedor vd join venta v on v.vendedor = vd.codigo join sucursal s on v.sucursal = s.codigo
group by vd.codigo, vd.nombre, s.nombre
order by vd.CODIGO
