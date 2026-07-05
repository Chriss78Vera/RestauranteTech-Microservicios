# RestauranteTech - Microservicios

Proyecto de evaluación práctica para gestionar platos y pedidos usando microservicios.

## Autores

* Christopher Vera
* Jordan Paillacho

## Arquitectura

* `nginx-gateway`: API Gateway en `http://localhost:8080`
* `svc-menu`: Spring Boot + PostgreSQL, puerto interno `8081`
* `svc-orders`: Spring Boot + PostgreSQL, puerto interno `8082`
* `menu-db`: PostgreSQL externo `5438`
* `orders-db`: PostgreSQL externo `5439`

Los servicios no se publican directamente al cliente. Las pruebas se hacen por el gateway en el puerto `8080`.

## Ejecucion

```bash
docker compose up -d --build
```

Ver contenedores:

```bash
docker compose ps
```

Detener todo:

```bash
docker compose down
```

## Rutas por API Gateway

### svc-menu

* `POST http://localhost:8080/api/menu/dishes`
* `GET http://localhost:8080/api/menu/dishes`
* `GET http://localhost:8080/api/menu/dishes/{id}`
* `PUT http://localhost:8080/api/menu/dishes/{id}`
* `DELETE http://localhost:8080/api/menu/dishes/{id}`

### svc-orders

* `POST http://localhost:8080/api/orders`
* `GET http://localhost:8080/api/orders`
* `GET http://localhost:8080/api/orders/{id}`

## Prueba rapida

Crear plato disponible:

```json
{
  "name": "Hamburguesa Clasica",
  "description": "Hamburguesa con queso y papas",
  "category": "PLATO_FUERTE",
  "price": 6.5,
  "available": true
}
```

Crear pedido:

```json
{
  "customerName": "Mesa 4",
  "dishId": 1,
  "quantity": 2
}
```

El pedido consulta `svc-menu`, valida el plato y calcula el total.
