# HW7 - RESTful - Conditional GET

V tomto úkolu jsem:

#### Naplnil `RestApplicationRepository.java` daty
```json
[
   {
     "id": "1",
     "name": "City tour",
     "customers": [
       "Jorge",
       "Franta"
     ]
   },
   {
     "id": "2",
     "name": "Village tour",
     "customers": [
       "Pepa",
       "Ondra"
     ]
   }
 ]
```
#### Vytvořil služby:
* `GET /tour/last-modified`
  * Vrací seznam Tours s hlavičkou `Last-Modified`.
  * -> `List<Tour>, 200 OK`
  * -> `304 NOT MODIFIED` pokud request obsahuje header `If-Modified-Since` datum starší než poslední aktualizace data modifikace v repozitáři.
* `GET /tour/weak-etag`
  * Vrací seznam Tours s hlavičkou `ETag` obsahující slabý hash, vytvořený pouze z atributů `id` a `name`.
  * -> `List<Tour>, 200 OK`
  * -> `304 NOT MODIFIED` pokud request obsahuje header `If-None-Match` se slabým nezměněným ETagem.
* `GET /tour/strong-etag`
  * Vrací seznam Tours s hlavičkou `ETag` obsahující silný hash.
  * -> `List<Tour>, 200 OK`
  * -> `304 NOT MODIFIED` pokud request obsahuje header `If-None-Match` se silným nezměněným ETagem.
* `POST /tour/{id}/customer`
  * Přidá do Tour zákazníka.
    * To aktualizuje datum modifikace repozitáře.
    * Má vliv na silný ETag.
    * Na slabý ETag, vytvořený pouze z atributů `id` a `name` nemá vliv.
  * -> `Tour, 200 OK` v odpovědi je upravená tour pro kontrolu.
  * -> `404 NOT FOUND` tour nebyla nalezena.
  * 
#### Ukázky requestů a odpovědí se nachází ve složce `examples`
