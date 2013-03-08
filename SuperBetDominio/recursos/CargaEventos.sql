

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `superbetdb`;



insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (1,'Simple'), (2,'Colocado'),(3,'Combinada'),(4,'Largo plazo'),(5,'MedioTiempo / Final'),(6,'Totales(Over/under)'),(7,'Resultado exacto'),(8,'Primer goleador'),(9,'Primera parte');


insert  into `combination`(`COMBINATION_ID`,`DESCRIPTION`,`PAID`,`NAME`) values (1,'Permite combinar resultados del campeonato y clásico',NULL,'Combinacion Clasico/Campeonato');




insert  into `country`(`COUNTRY_ID`,`NAME`) values (1,'Uruguay'),(2,'Argentina'),(3,'Brasil'),(4,'Chile'),(5,'Bolivia'),(6,'Paraguay'),(7,'Italia'),(8,'Inglaterra'),(9,'España'),(10,'Portugal'),(11,'Alemania'),(12,'Francia'),(13,'Holanda'),(14,'Estados Unidos'),(15,'América');




insert  into `sport`(`SPORT_ID`,`NAME`) values (1,'Futbol'),(2,'Basquetbol'),(3,'Boxeo'),(4,'Automovilismo'),(5,'Otros');




insert  into `contest`(`CONTEST_ID`,`NAME`,`COUNTRY_ID`,`SPORT_ID`) values (1,'Torneo Apertura 2011',1,1),(2,'Eliminatorias 2014',15,1),(3,'Torneo Clausura 2011',2,1),(4,'Premier League 2011',8,1),(5,'Liga Española',9,1),(6,'Liga Italiana',7,1),(7,'Bundesliga',11,1),(8,'Campeonato Brasileirao',3,1),(9,'Basketball NBA Este/Oeste',14,2);


insert  into `location`(`LOCATION_ID`,`LATITUDE`,`LONGITUDE`) values (1,-34.844239422276196,-56.16783121679691),(2,-34.894801204532236,-56.15293959234623),(3,-34.76636426195491,-58.629712854370155),(4,-34.664769536110114,-58.85355928991703),(5,-34.664769536110114,-58.85355928991703),(6,-34.51553836865814,-58.764295373901405),(7,-34.51553836865814,-58.764295373901405),(8,-34.51553836865814,-58.764295373901405),(9,-34.51553836865814,-58.764295373901405),(10,-34.51553836865814,-58.764295373901405),(11,-34.51553836865814,-58.764295373901405),(12,-34.51553836865814,-58.764295373901405),(13,-34.894801204532236,-56.15246752355961),(14,-34.88483919080201,-56.158561502441444),(15,-34.90370615175192,-56.13040903662113),(16,-34.86234101442675,-56.20151976202396),(17,-34.8388152127293,-56.13199690435795),(18,-34.87339722571507,-56.212677751525916),(19,-34.89592757530131,-56.15293959234623),(20,-34.866636916416375,-56.129593645080604),(21,-34.897053930625624,-56.15362623785404),(22,-34.863256553338466,-56.17285231207279),(23,-32.35984408830224,-54.16934946630863),(24,39.31872843970281,-1.1713025913086312),(25,40.33957745365678,-3.6871717319336312),(26,40.04374807031473,-4.353217874511756),(27,41.409100320952646,2.0627977504882438),(28,40.40444484485661,-3.7160108432617562),(29,-34.89539959092632,-56.15444162939457),(30,-34.893146819464455,-56.245679651245155),(31,-34.91648112864443,-56.166779790863075),(32,-34.85931262080448,-56.20415905569462),(33,-34.8387447660982,-56.13223293875126),(34,-34.87381972659075,-56.21239880178837),(35,-34.85691799807381,-56.162273679718055),(36,-34.89539959092632,-56.15444162939457),(37,-34.51553836865814,-58.764295373901405);


insert  into `event`(`EVENT_ID`,`DATE_END`,`IMPORTANCE`,`DATE_BEGIN`,`NAME`,`CONTEST_ID`,`LOCATION_ID`) 

values 

(1,'2011-11-20 19:00:00',3,'2011-11-20 17:00:00','Boca vs Racing',3,3),
(2,'2011-11-18 19:00:00',3,'2011-11-18 17:00:00','San Martin vs All Boys',3,4),
(3,'2011-11-19 20:00:00',3,'2011-11-19 18:00:00','Independiente vs Olimpo',3,5),
(4,'2011-11-21 14:00:00',3,'2011-11-21 12:00:00','Banfield vs Estudiantes',3,6),
(5,'2011-11-20 18:00:00',3,'2011-11-20 16:00:00','San Lorenzo vs Union',3,7),
(6,'2011-11-04 20:00:00',3,'2011-11-04 18:15:00','All Boys vs Independiente',3,8),
(7,'2011-11-06 21:00:00',3,'2011-11-06 19:10:00','Boca vs Sarfield',3,9),
(8,'2011-11-06 00:00:00',3,'2011-11-05 22:20:00','Racing vs Argentinos',3,10),
(9,'2011-11-12 19:00:00',3,'2011-11-12 17:00:00','Danubio vs River',1,17),
(11,'2011-11-12 19:00:00',3,'2011-11-12 17:00:00','Fenix vs Cerrito',1,18),
(12,'2011-11-12 19:00:00',4,'2011-11-12 17:00:00','Rampla vs Nacional',1,19),
(13,'2011-11-13 13:00:00',4,'2011-11-13 11:00:00','Tanque vs Cerro',1,20),
(14,'2011-11-13 19:00:00',4,'2011-11-13 17:00:00','Peñarol vs Wanderers',1,21),
(15,'2011-11-13 17:00:00',4,'2011-11-13 17:00:00','Rentistas vs Liverpool',1,22),
(16,'2011-11-13 19:00:00',4,'2011-11-13 17:00:00','Cerro Largo vs Defensor',1,23),
(17,'2011-11-29 00:00:00',4,'2011-11-29 00:00:00','Racing vs Valencia',5,24),
(18,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Osasuna vs Atl Madrid',5,25),
(19,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Real Sociedad vs Sporting Gijon',5,26),
(20,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Villareal vs Barcelona',5,27),
(21,'2011-11-29 22:00:00',3,'2011-11-29 20:00:00','Real Madrid vs Zaragoza',5,28),
(22,'2011-11-19 19:00:00',4,'2011-11-19 17:00:00','Nacional vs Peñarol',1,29),
(23,'2011-11-19 19:00:00',3,'2011-11-19 17:00:00','Rampla vs Racing',1,30),
(24,'2011-11-19 19:00:00',3,'2011-11-19 17:00:00','Defensor vs Bella Vista',1,31),
(25,'2011-11-19 19:00:00',3,'2011-11-19 19:00:00','Wanderers vs Cerrito',1,32),
(26,'2011-11-19 16:00:00',3,'2011-11-19 14:00:00','Danubio vs Cerro',1,33),
(27,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Fenix vs Liverpool',1,34),
(28,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Tanque vs River',1,35),
(29,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Torneo Apertura',1,36),
(30,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Torneo Apertura',3,37);


insert  into `event_type`(`EVENT_ID`,`BET_TYPE_ID`,`DATE_BET_END`,`PAYED`,`DATE_BET_BEGIN`,`COMBINATION_ID`,`ganador_RESULT_ID`) values (1,2,'2012-10-08 22:21:00',NULL,'2012-04-09 22:21:00',1,NULL),(1,5,'2012-10-08 22:16:00',NULL,'2012-03-12 22:16:00',1,NULL),(2,6,'2012-11-05 22:28:00',NULL,'2012-10-08 22:28:00',1,NULL),(2,8,'2012-11-05 22:38:00',NULL,'2012-10-08 22:38:00',1,NULL);

insert  into `result`(`RESULT_ID`,`PAYMENT`,`DESCRIPTION`) values (1,1.1,'Nacional Gana el Campeonato'),(2,1.9,'River Gana el Campeonato'),(3,1.1,'Liverpool Gana el Campeonato'),(4,1.1,'Peñarol Gana el Campeonato'),(5,1.4,'Liverpool sale entre los primeros 3'),(6,1.2,'Peñarol Sale entre los primeros 3'),(7,1.3,'Nacional Sale Entre los primeros 3'),(8,1.1,'Medio Gana Peñarol / Final Gana Nacional'),(9,1.2,'Medio Gana Peñarol / Final Gana Perñarol'),(10,1.1,'Medio Gana Nacional / Final Gana Nacional'),(11,1.2,'Medio Gana Nacional / Final Gana Peñarol'),(12,1.1,'Nac 1 - Peñ 1'),(13,1.1,'Nac 0 - Peñ 0'),(14,1.1,'Nac 2 - Peñ 2'),(15,1.2,'Nac 1 - Peñ 0'),(16,1.2,'Nac 0 - Peñ 1'),(17,1.2,'Nac 1 - Peñ 2'),(18,1.2,'Nac 2 - Peñ 1');

insert  into `event_type_result`(`EVENT_TYPE_EVENT_ID`,`EVENT_TYPE_BET_TYPE_ID`,`resultados_RESULT_ID`) values (1,2,5),(1,2,6),(1,2,7),(1,5,1),(1,5,2),(1,5,3),(1,5,4),(2,6,8),(2,6,9),(2,6,10),(2,6,11),(2,8,12),(2,8,13),(2,8,14),(2,8,15),(2,8,16),(2,8,17),(2,8,18);


insert  into `source`(`SOURCE_ID`,`NAME`,`URL`) values (1,'FIFA.com','http://es.fifa.com/rss/index.xml'),(2,'ESPN.com','http://search.espn.go.com/rss/amy-nelson/');


insert  into `user`(`USER_ID`,`CITY`,`COUNTRY`,`EMAIL`,`ID_CARD`,`AMOUNT`,`NAMES`,`PASSWORD`,`PHONE`,`PHOTOURL`,`ROLE`,`STATE`,`SURNAMES`,`USERNAME`) values (1,NULL,NULL,NULL,NULL,0,'Chuck Norris','a',NULL,'/resources/norris.jpg','administrador','',NULL,'a'),(2,'Montevideo','Uruguay','delbetu.usuario@gmail.com','158795465',125,'Usuario','u',NULL,'/resources/default_image.jpg','usuario','','Perez García','u'),(3,'Montevideo','Uruguay','delbetu.moderador@gmail.com','67489581',0,'Leslie','m',NULL,'/resources/leslie.jpg','moderador','','Neilsen','m'),(4,'Montevideo','Uruguay',NULL,NULL,0,'GRUPO 04','G04',NULL,'/resources/default_image.jpg','usuario','','G04','G04');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
