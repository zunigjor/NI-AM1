## Log výsledků
Order client vztvořil 10 zpráv ve frontě `allOrdersQueue`:
```
2022-12-17 15:46:42.154  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added trip;1;Vilnius;Berlin;Travelo;1338 to allOrdersQueue
2022-12-17 15:46:45.822  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added trip;2;Riga;Prague;Travelify;1167 to allOrdersQueue
2022-12-17 15:46:49.455  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;3;4;1132;Thákurova 9 to allOrdersQueue
2022-12-17 15:46:53.308  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;4;3;1352;Sněmovní 176/4 to allOrdersQueue
2022-12-17 15:46:56.962  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;5;3;1153;Thákurova 9 to allOrdersQueue
2022-12-17 15:47:00.613  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added trip;6;Riga;Paris;AAA Travel;1150 to allOrdersQueue
2022-12-17 15:47:04.264  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;7;4;1151;Technická 2 to allOrdersQueue
2022-12-17 15:47:08.206  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;8;5;1441;Sněmovní 176/4 to allOrdersQueue
2022-12-17 15:47:12.130  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added booking;9;4;1423;Technická 2 to allOrdersQueue
2022-12-17 15:47:15.847  INFO 1695421 --- [           main] c.c.f.n.o.OrderClientApplication         : Added trip;10;Vilnius;Prague;Travelo;1216 to allOrdersQueue
```
Order processor roztřídil zprávy z fronty `allOrdersQueue` do front `bookingsQueue` a `newTripsQueue`:
```
2022-12-17 15:46:42.151  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: trip;1;Vilnius;Berlin;Travelo;1338
2022-12-17 15:46:42.155  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding trip;1;Vilnius;Berlin;Travelo;1338 to newTripsQueue
2022-12-17 15:46:45.823  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: trip;2;Riga;Prague;Travelify;1167
2022-12-17 15:46:45.823  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding trip;2;Riga;Prague;Travelify;1167 to newTripsQueue
2022-12-17 15:46:49.456  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;3;4;1132;Thákurova 9
2022-12-17 15:46:49.457  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;3;4;1132;Thákurova 9 to bookingsQueue
2022-12-17 15:46:53.309  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;4;3;1352;Sněmovní 176/4
2022-12-17 15:46:53.309  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;4;3;1352;Sněmovní 176/4 to bookingsQueue
2022-12-17 15:46:56.962  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;5;3;1153;Thákurova 9
2022-12-17 15:46:56.963  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;5;3;1153;Thákurova 9 to bookingsQueue
2022-12-17 15:47:00.613  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: trip;6;Riga;Paris;AAA Travel;1150
2022-12-17 15:47:00.614  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding trip;6;Riga;Paris;AAA Travel;1150 to newTripsQueue
2022-12-17 15:47:04.265  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;7;4;1151;Technická 2
2022-12-17 15:47:04.265  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;7;4;1151;Technická 2 to bookingsQueue
2022-12-17 15:47:08.207  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;8;5;1441;Sněmovní 176/4
2022-12-17 15:47:08.207  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;8;5;1441;Sněmovní 176/4 to bookingsQueue
2022-12-17 15:47:12.131  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: booking;9;4;1423;Technická 2
2022-12-17 15:47:12.131  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding booking;9;4;1423;Technická 2 to bookingsQueue
2022-12-17 15:47:15.847  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Received message: trip;10;Vilnius;Prague;Travelo;1216
2022-12-17 15:47:15.848  INFO 1695006 --- [ntContainer#0-3] c.c.f.n.o.OrderProcessorApplication      : Adding trip;10;Vilnius;Prague;Travelo;1216 to newTripsQueue
```
Bookings processor zpracoval zprávy z fronty `bookingsQueue`.
```
2022-12-17 15:46:49.458  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;3;4;1132;Thákurova 9
2022-12-17 15:46:53.310  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;4;3;1352;Sněmovní 176/4
2022-12-17 15:46:56.963  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;5;3;1153;Thákurova 9
2022-12-17 15:47:04.266  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;7;4;1151;Technická 2
2022-12-17 15:47:08.208  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;8;5;1441;Sněmovní 176/4
2022-12-17 15:47:12.132  INFO 1694929 --- [ntContainer#0-3] c.c.f.n.b.BookingsProcessorApplication   : Processing booking;9;4;1423;Technická 2
```
New trips processor zpracoval zprávy z fronty `newTripsQueue`.
```
2022-12-17 15:48:12.255  INFO 1695877 --- [ntContainer#0-1] c.c.f.n.n.NewTripsProcessorApplication   : Processing 1;Vilnius;Berlin;Travelo;1338
2022-12-17 15:48:12.258  INFO 1695877 --- [ntContainer#0-1] c.c.f.n.n.NewTripsProcessorApplication   : Processing 2;Riga;Prague;Travelify;1167
2022-12-17 15:48:12.258  INFO 1695877 --- [ntContainer#0-1] c.c.f.n.n.NewTripsProcessorApplication   : Processing 6;Riga;Paris;AAA Travel;1150
2022-12-17 15:48:12.259  INFO 1695877 --- [ntContainer#0-1] c.c.f.n.n.NewTripsProcessorApplication   : Processing 10;Vilnius;Prague;Travelo;1216
```