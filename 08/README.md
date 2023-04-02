# HW8

Ve složce `src` jsem vyvořil projekty `order-client`, `order-processor`, `bookings-processor` a `new-trips-processor`.

### Order client

Pomocí metody `runTest()` vytvoří náhodně bookingy a tripy ve formátu csv, a přidá je do fronty `allOrdersQueue`.

### Order processor

Naslouchá zprávám ve frontě `allOrdersQueue`. Podle obsahu zprávy rozřadí bookingy do fronty `bookingsQueue` a tripy do `newTripsQueue`.

### Bookings processor

Naslouchá zprávám ve frontě `bookingsQueue` a zpracovává je.

### New trips processor

Naslouchá zprávám ve frontě `newTripsQueue` a zpracovává je.

## [Results](results/results.md)
