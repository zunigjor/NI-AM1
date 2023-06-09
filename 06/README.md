# HW6 - RESTful - Asynchronous operation

Vytvořil jsem službu spravující Tours. Po zavolání DELETE na `/tour/{id}` se Tour přesune do seznamu dostupném přes GET na `tour/awaiting-delete`, kde se po 10 sekundách smaže, tímto simuluji uživatelské potvrzení.

Dostupné requesty:
* `GET /tour` -> `List<Tour> 200 OK`
    * Vrátí seznam všech Tours
* `GET /tour/awaiting-delete` -> `List<Tour> 200 OK`
    * Vrátí seznam všech Tours čekajících na simulované potvrzení smazání
* `GET /tour/{id}` -> `Tour 200 OK` nebo `404 NOT FOUND`
  * Vrátí Tour podle id.
* `POST /tour` -> `Tour 201 CREATED`
  * Vytvoří novou Tour
* `DELETE /tour/{id}` -> `Tour 200 OK` nebo `404 NOT FOUND`
  * Přesune Tour do seznamu awaiting-delete simulujícího povrzení smazaní Tour

příklady requestů jsou v souboru `example/requests.http`