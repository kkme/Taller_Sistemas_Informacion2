/*
Descripcion 
MySQL - 5.5.15 : Database - superbetdb
Este archivo contiene una carga para realizar pruebas
Eventos: 		Campeonato Apertura 2011 , Partido Clásico Nacional Peñarol , Combinacion de anteriores
TipoEventos:	Apuesta a Campeon (Largo Plazo) , Entre los primeros 3 (Colocado) , MedioTiempo/Final , Resultado Exacto

*/

USE `superbetdb`;


insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (2,'Colocado'),(3,'Combinada'),(4,'Largo plazo'),(5,'MedioTiempo / Final'),(6,'Totales(Over/under)'),(7,'Resultado exacto'),(8,'Primer goleador'),(9,'Primera parte');

insert  into `combination`(`COMBINATION_ID`,`DESCRIPTION`,`PAID`,`NAME`) values (1,'Permite combinar resultados del campeonato y clásico',NULL,'Combinacion Clasico/Campeonato');

insert  into `country`(`COUNTRY_ID`,`NAME`) values (1,'Uruguay'),(2,'Argentina'),(3,'Brasil'),(4,'Chile'),(5,'Bolivia'),(6,'Paraguay'),(7,'Italia'),(8,'Inglaterra'),(9,'España'),(10,'Portugal'),(11,'Alemania'),(12,'Francia'),(13,'Holanda'),(14,'Estados Unidos');

insert  into `sport`(`SPORT_ID`,`NAME`) values (1,'Futbol'),(2,'Basquetbol'),(3,'Boxeo'),(4,'Automovilismo'),(5,'Otros');

insert  into `contest`(`CONTEST_ID`,`NAME`,`COUNTRY_ID`,`SPORT_ID`) values (1,'Torneo Apertura 2011',1,1),(2,'Eliminatorias 2014',NULL,1),(3,'Torneo Clausura 2011',2,1),(4,'Premier League 2011',8,1),(5,'Liga Española',9,1),(6,'Liga Italiana',7,1),(7,'Bundesliga',11,1),(8,'Campeonato Brasileirao',3,1),(9,'Basketball NBA Este/Oeste',14,2);

insert  into `location`(`LOCATION_ID`,`LATITUDE`,`LONGITUDE`) values (1,-34.844239422276196,-56.16783121679691),(2,-34.894801204532236,-56.15293959234623);

insert  into `event`(`EVENT_ID`,`DATE_END`,`IMPORTANCE`,`DATE_BEGIN`,`NAME`,`CONTEST_ID`,`LOCATION_ID`) values (1,'2011-11-30 23:59:00',4,'2011-04-01 23:59:00','Campeonato Apertura 2011',1,1),(2,'2011-11-14 18:00:00',4,'2011-11-14 16:00:00','Partido clásico',1,2);

insert  into `result`(`RESULT_ID`,`PAYMENT`,`DESCRIPTION`) values (1,1.1,'Nacional Gana el Campeonato'),(2,1.9,'River Gana el Campeonato'),(3,1.1,'Liverpool Gana el Campeonato'),(4,1.1,'Peñarol Gana el Campeonato'),(5,1.4,'Liverpool sale entre los primeros 3'),(6,1.2,'Peñarol Sale entre los primeros 3'),(7,1.3,'Nacional Sale Entre los primeros 3'),(8,1.1,'Medio Gana Peñarol / Final Gana Nacional'),(9,1.2,'Medio Gana Peñarol / Final Gana Perñarol'),(10,1.1,'Medio Gana Nacional / Final Gana Nacional'),(11,1.2,'Medio Gana Nacional / Final Gana Peñarol'),(12,1.1,'Nac 1 - Peñ 1'),(13,1.1,'Nac 0 - Peñ 0'),(14,1.1,'Nac 2 - Peñ 2'),(15,1.2,'Nac 1 - Peñ 0'),(16,1.2,'Nac 0 - Peñ 1'),(17,1.2,'Nac 1 - Peñ 2'),(18,1.2,'Nac 2 - Peñ 1');

insert  into `event_type`(`EVENT_ID`,`BET_TYPE_ID`,`DATE_BET_END`,`PAYED`,`DATE_BET_BEGIN`,`COMBINATION_ID`,`ganador_RESULT_ID`) values (1,2,'2012-10-08 22:21:00',NULL,'2012-04-09 22:21:00',1,NULL),(1,5,'2012-10-08 22:16:00',NULL,'2012-03-12 22:16:00',1,NULL),(2,6,'2012-11-05 22:28:00',NULL,'2012-10-08 22:28:00',1,NULL),(2,8,'2012-11-05 22:38:00',NULL,'2012-10-08 22:38:00',1,NULL);

insert  into `event_type_result`(`EVENT_TYPE_EVENT_ID`,`EVENT_TYPE_BET_TYPE_ID`,`resultados_RESULT_ID`) values (1,2,5),(1,2,6),(1,2,7),(1,5,1),(1,5,2),(1,5,3),(1,5,4),(2,6,8),(2,6,9),(2,6,10),(2,6,11),(2,8,12),(2,8,13),(2,8,14),(2,8,15),(2,8,16),(2,8,17),(2,8,18);

insert  into `source`(`SOURCE_ID`,`NAME`,`URL`) values (1,'FIFA.com','http://es.fifa.com/rss/index.xml'),(2,'ESPN.com','http://search.espn.go.com/rss/amy-nelson/');

insert  into `user`(`USER_ID`,`CITY`,`COUNTRY`,`EMAIL`,`ID_CARD`,`AMOUNT`,`NAMES`,`PASSWORD`,`PHONE`,`PHOTOURL`,`ROLE`,`STATE`,`SURNAMES`,`USERNAME`) 
values
(1,NULL,NULL,NULL,NULL,0.0,'Chuck Norris','a',NULL,'/resources/norris.jpg','administrador','',NULL,'a'),
(2,'Montevideo','Uruguay','delbetu.usuario@gmail.com','158795465',125,'Usuario','u',NULL,'/resources/default_image.jpg','usuario','','Perez García','u'),
(3,'Montevideo','Uruguay','delbetu.moderador@gmail.com','67489581',0.0,'Leslie','m',NULL,'/resources/leslie.jpg','moderador','','Neilsen','m'),
(4,'Montevideo','Uruguay',NULL,NULL,0.0,'GRUPO 04','G04',NULL,'/resources/default_image.jpg','usuario','','G04','G04');


