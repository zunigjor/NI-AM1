# HW9

V tomto úkolu jsem vytvořil jendoduchý load balancer, který uchovává informace o healthy a unhealthy zdrojích. Tyto informace aktualizuje každých 1000ms.

Na healthy zdroje předává dotazy z URL `/test`. Výběr ze seznamu healthy zdrojů se dělá na základě metody `Random().nextInt()`.

V případě, že nějaký zdroj se stane nedostupným, právě ve chvíli kdy dorazí dotaz, tak load balancer odstraní nedostupný zdroj ze seznamu healthy zdrojů a pokusí se přeposlat dotaz na zdroj jiný.

### Výpis logů

Do výpisu logů vybírám zajímavé časti, tedy chvíle kdy se zdroje měnily z healthy na unhealthy nebo naopak.

V této části je vidět, že se dokola nevybírá stejný zdroj.
```
2022-12-17 19:11:21.841  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute1/list HEALTHY
2022-12-17 19:11:21.846  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list HEALTHY
2022-12-17 19:11:21.851  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 500: http://147.32.233.18:8888/MI-MDW-LastMinute2/list UNHEALTHY
2022-12-17 19:11:22.517  INFO 1748963 --- [nio-8080-exec-4] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
2022-12-17 19:11:22.531  INFO 1748963 --- [nio-8080-exec-4] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list USED
2022-12-17 19:11:22.531  INFO 1748963 --- [nio-8080-exec-4] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
2022-12-17 19:11:22.842  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute1/list HEALTHY
2022-12-17 19:11:22.847  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list HEALTHY
2022-12-17 19:11:22.852  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 500: http://147.32.233.18:8888/MI-MDW-LastMinute2/list UNHEALTHY
2022-12-17 19:11:23.777  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
2022-12-17 19:11:23.790  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute1/list USED
2022-12-17 19:11:23.790  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
```

Zde je vidět, že ve chvíli kdy se zdroj přiřadí mezi healthy zdroje, tak je používán.
```
2022-12-17 19:12:18.879  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute1/list HEALTHY
2022-12-17 19:12:18.884  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list HEALTHY
2022-12-17 19:12:18.889  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute2/list HEALTHY
2022-12-17 19:12:18.978  INFO 1748963 --- [nio-8080-exec-2] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
2022-12-17 19:12:18.984  INFO 1748963 --- [nio-8080-exec-2] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list USED
2022-12-17 19:12:18.984  INFO 1748963 --- [nio-8080-exec-2] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------

...

2022-12-17 19:12:22.874  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute1/list HEALTHY
2022-12-17 19:12:22.879  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute3/list HEALTHY
2022-12-17 19:12:22.885  INFO 1748963 --- [        Timer-0] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute2/list HEALTHY
2022-12-17 19:12:23.545  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
2022-12-17 19:12:23.553  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : 200: http://147.32.233.18:8888/MI-MDW-LastMinute2/list USED
2022-12-17 19:12:23.553  INFO 1748963 --- [nio-8080-exec-5] c.c.f.n.l.LoadBalanceApplication         : ----------------------------------------------------------
```