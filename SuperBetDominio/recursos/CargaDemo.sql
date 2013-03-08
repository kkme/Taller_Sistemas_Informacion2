/*
SQLyog Community v8.71 
MySQL - 5.5.15 : Database - superbetdb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `superbetdb`;

/*Data for the table `bet` */

/*Data for the table `bet_result` */

/*Data for the table `bet_type` */

insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (1,'Simple'),(2,'Colocado'),(3,'Combinada'),(4,'Largo plazo'),(5,'MedioTiempo / Final'),(6,'Totales(Over/under)'),(7,'Resultado exacto'),(8,'Primer goleador'),(9,'Primera parte');

/*Data for the table `blackboard_bet` */

/*Data for the table `blackboard_bet_user` */

/*Data for the table `config_params` */

/*Data for the table `sport` */

insert  into `sport`(`SPORT_ID`,`NAME`) values (1,'Futbol'),(2,'Basquetbol'),(3,'Boxeo'),(4,'Automovilismo'),(5,'Otros');

/*Data for the table `country` */

insert  into `country`(`COUNTRY_ID`,`NAME`) values (1,'Uruguay'),(2,'Argentina'),(3,'Brasil'),(4,'Chile'),(5,'Bolivia'),(6,'Paraguay'),(7,'Italia'),(8,'Inglaterra'),(9,'España'),(10,'Portugal'),(11,'Alemania'),(12,'Francia'),(13,'Holanda'),(14,'Estados Unidos'),(15,'América');

/*Data for the table `contest` */

insert  into `contest`(`CONTEST_ID`,`NAME`,`COUNTRY_ID`,`SPORT_ID`) values (1,'Torneo Apertura 2011',1,1),(2,'Eliminatorias 2014',15,1),(3,'Torneo Clausura 2011',2,1),(4,'Premier League 2011',8,1),(5,'Liga Española',9,1),(6,'Liga Italiana',7,1),(7,'Bundesliga',11,1),(8,'Campeonato Brasileirao',3,1),(9,'Basketball NBA Este/Oeste',14,2);

/*Data for the table `location` */

insert  into `location`(`LOCATION_ID`,`LATITUDE`,`LONGITUDE`) values (1,-34.844239422276196,-56.16783121679691),(2,-34.894801204532236,-56.15293959234623),(3,-34.76636426195491,-58.629712854370155),(4,-34.664769536110114,-58.85355928991703),(5,-34.664769536110114,-58.85355928991703),(6,-34.51553836865814,-58.764295373901405),(7,-34.51553836865814,-58.764295373901405),(8,-34.51553836865814,-58.764295373901405),(9,-34.51553836865814,-58.764295373901405),(10,-34.51553836865814,-58.764295373901405),(11,-34.51553836865814,-58.764295373901405),(12,-34.51553836865814,-58.764295373901405),(13,-34.894801204532236,-56.15246752355961),(14,-34.88483919080201,-56.158561502441444),(15,-34.90370615175192,-56.13040903662113),(16,-34.86234101442675,-56.20151976202396),(17,-34.8388152127293,-56.13199690435795),(18,-34.87339722571507,-56.212677751525916),(19,-34.89592757530131,-56.15293959234623),(20,-34.866636916416375,-56.129593645080604),(21,-34.897053930625624,-56.15362623785404),(22,-34.863256553338466,-56.17285231207279),(23,-32.35984408830224,-54.16934946630863),(24,39.31872843970281,-1.1713025913086312),(25,40.33957745365678,-3.6871717319336312),(26,40.04374807031473,-4.353217874511756),(27,41.409100320952646,2.0627977504882438),(28,40.40444484485661,-3.7160108432617562),(29,-34.89539959092632,-56.15444162939457),(30,-34.893146819464455,-56.245679651245155),(31,-34.91648112864443,-56.166779790863075),(32,-34.85931262080448,-56.20415905569462),(33,-34.8387447660982,-56.13223293875126),(34,-34.87381972659075,-56.21239880178837),(35,-34.85691799807381,-56.162273679718055),(36,-34.89539959092632,-56.15444162939457),(37,-34.51553836865814,-58.764295373901405);

/*Data for the table `event` */

insert  into `event`(`EVENT_ID`,`DATE_END`,`IMPORTANCE`,`DATE_BEGIN`,`NAME`,`CONTEST_ID`,`LOCATION_ID`) values (1,'2011-11-20 19:00:00',3,'2011-11-20 17:00:00','Boca vs Racing',3,3),(2,'2011-11-18 19:00:00',3,'2011-11-18 17:00:00','San Martin vs All Boys',3,4),(3,'2011-11-19 20:00:00',3,'2011-11-19 18:00:00','Independiente vs Olimpo',3,5),(4,'2011-11-21 14:00:00',3,'2011-11-21 12:00:00','Banfield vs Estudiantes',3,6),(5,'2011-11-20 18:00:00',3,'2011-11-20 16:00:00','San Lorenzo vs Union',3,7),(6,'2011-11-04 20:00:00',3,'2011-11-04 18:15:00','All Boys vs Independiente',3,8),(7,'2011-11-06 21:00:00',3,'2011-11-06 19:10:00','Boca vs Sarfield',3,9),(8,'2011-11-06 00:00:00',3,'2011-11-05 22:20:00','Racing vs Argentinos',3,10),(9,'2011-11-12 19:00:00',3,'2011-11-12 17:00:00','Danubio vs River',1,17),(11,'2011-11-12 19:00:00',3,'2011-11-12 17:00:00','Fenix vs Cerrito',1,18),(12,'2011-11-12 19:00:00',4,'2011-11-12 17:00:00','Rampla vs Nacional',1,19),(13,'2011-11-13 13:00:00',4,'2011-11-13 11:00:00','Tanque vs Cerro',1,20),(14,'2011-11-13 19:00:00',4,'2011-11-13 17:00:00','Peñarol vs Wanderers',1,21),(15,'2011-11-13 17:00:00',4,'2011-11-13 17:00:00','Rentistas vs Liverpool',1,22),(16,'2011-11-13 19:00:00',4,'2011-11-13 17:00:00','Cerro Largo vs Defensor',1,23),(17,'2011-11-29 00:00:00',4,'2011-11-29 00:00:00','Racing vs Valencia',5,24),(18,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Osasuna vs Atl Madrid',5,25),(19,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Real Sociedad vs Sporting Gijon',5,26),(20,'2011-11-29 19:00:00',3,'2011-11-29 17:00:00','Villareal vs Barcelona',5,27),(21,'2011-11-29 22:00:00',3,'2011-11-29 20:00:00','Real Madrid vs Zaragoza',5,28),(22,'2011-11-19 19:00:00',4,'2011-11-19 17:00:00','Nacional vs Peñarol',1,29),(23,'2011-11-19 19:00:00',3,'2011-11-19 17:00:00','Rampla vs Racing',1,30),(24,'2011-11-19 19:00:00',3,'2011-11-19 17:00:00','Defensor vs Bella Vista',1,31),(25,'2011-11-19 19:00:00',3,'2011-11-19 19:00:00','Wanderers vs Cerrito',1,32),(26,'2011-11-19 16:00:00',3,'2011-11-19 14:00:00','Danubio vs Cerro',1,33),(27,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Fenix vs Liverpool',1,34),(28,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Tanque vs River',1,35),(29,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Torneo Apertura',1,36),(30,'2011-11-19 13:00:00',3,'2011-11-19 11:00:00','Torneo Apertura',3,37);

/*Data for the table `result` */

insert  into `result`(`RESULT_ID`,`PAYMENT`,`DESCRIPTION`) values (19,1.4,'Gana All Boys'),(20,1.3,'Gana Independiente'),(21,1.4,'Empatan'),(22,1.4,'Viatri'),(23,1.4,'Jonathan Ramirez'),(24,1.1,'2 Goles'),(25,1.1,'0 Goles'),(26,1.3,'4 Goles'),(27,1.5,'6 Goles'),(28,1.2,'1 Goles'),(29,1.2,'0 x 0'),(30,1.2,'1 x 1'),(31,1.3,'1 x 0'),(32,1.3,'0 x 1'),(33,1.1,'1 x 0'),(34,1.1,'0 x 0'),(35,1.3,'1 x 2'),(36,1.8,'2 x 1 / 2 x 2'),(37,1.65,'0 x 0 / 1 x 1'),(38,1.6,'0 x 0 / 0 x 2'),(39,1.8,'1 x 1 / 1 x 2'),(40,1.3,'Gana Nacional'),(41,1.3,'Gana Peñarol'),(42,1.3,'Empatan'),(43,1.5,'2 x 1'),(44,1.4,'2 x 2'),(45,1.2,'1 x 0'),(46,1.2,'0 x 1'),(47,1.1,'0 x 0'),(48,1.1,'1 x 0'),(49,1.9,'Rosano'),(50,1.3,'Recoba'),(51,1.3,'Lopez'),(52,1.5,'Medina'),(53,1.4,'Bueno'),(54,1.4,'Joao Pedro'),(55,1.2,'0 x 0'),(56,1.4,'1 x 0'),(57,1.2,'0 x 1'),(58,1.99,'0 x 5'),(59,1.4,'Gana Rampla'),(60,1.4,'Gana Racing'),(61,1.2,'Empatan'),(62,1.35,'Gana Defensor'),(63,1.35,'Gana Bella Vista'),(64,1.2,'Empatan'),(65,1.3,'Gana Wanderers'),(66,1.5,'Gana Cerrito'),(67,1.3,'Empatan'),(68,1.3,'Gana Danubio'),(69,1.3,'Gana Cerro'),(70,1.3,'Empatan'),(71,1.4,'1 x 1'),(72,1.4,'2 x 2'),(73,1.2,'1 x 0'),(74,1.4,'0 x 1'),(75,1.4,'Gana Fenix'),(76,1.4,'Gana Liverpool'),(77,1.3,'Empatan'),(78,1.4,'Gana Tanque'),(79,1.5,'Gana River'),(80,1.3,'Empatan');

/*Data for the table `event_type` */

insert  into `event_type`(`EVENT_ID`,`BET_TYPE_ID`,`DATE_BET_END`,`PAYED`,`DATE_BET_BEGIN`,`COMBINATION_ID`,`ganador_RESULT_ID`) values (6,1,'2012-12-16 16:56:00',NULL,'2012-12-16 16:56:00',NULL,NULL),(7,8,'2011-10-30 17:00:00',NULL,'2011-10-30 17:00:00',NULL,NULL),(8,6,'2011-10-30 17:03:00',NULL,'2011-10-30 17:03:00',NULL,NULL),(9,7,'2011-10-30 17:07:00',NULL,'2011-10-30 17:07:00',NULL,NULL),(11,9,'2011-10-30 17:10:00',NULL,'2011-10-30 17:10:00',NULL,NULL),(12,5,'2011-10-30 17:45:00',NULL,'2011-10-30 17:45:00',NULL,NULL),(22,1,'2011-10-30 18:10:00',NULL,'2011-10-30 18:10:00',NULL,NULL),(22,7,'2011-10-30 18:22:00',NULL,'2011-10-30 18:22:00',NULL,NULL),(22,8,'2011-10-30 18:27:00',NULL,'2011-10-30 18:27:00',NULL,NULL),(22,9,'2011-10-30 18:30:00',NULL,'2011-10-30 18:30:00',NULL,NULL),(23,1,'2011-10-30 18:33:00',NULL,'2011-10-30 18:33:00',NULL,NULL),(24,1,'2011-10-30 18:34:00',NULL,'2011-10-30 18:34:00',NULL,NULL),(25,1,'2011-10-30 18:35:00',NULL,'2011-10-30 18:35:00',NULL,NULL),(26,1,'2011-10-30 18:37:00',NULL,'2011-10-30 18:37:00',NULL,NULL),(26,7,'2011-10-30 18:40:00',NULL,'2011-10-30 18:40:00',NULL,NULL),(27,1,'2011-10-30 18:43:00',NULL,'2011-10-30 18:43:00',NULL,NULL),(28,1,'2011-10-30 18:49:00',NULL,'2011-10-30 18:49:00',NULL,NULL);

/*Data for the table `event_type_result` */

insert  into `event_type_result`(`EVENT_TYPE_EVENT_ID`,`EVENT_TYPE_BET_TYPE_ID`,`resultados_RESULT_ID`) values (6,1,19),(6,1,20),(6,1,21),(7,8,22),(7,8,23),(8,6,24),(8,6,25),(8,6,26),(8,6,27),(8,6,28),(9,7,29),(9,7,30),(9,7,31),(9,7,32),(11,9,33),(11,9,34),(11,9,35),(12,5,36),(12,5,37),(12,5,38),(12,5,39),(22,1,40),(22,1,41),(22,1,42),(22,7,43),(22,7,44),(22,7,45),(22,7,46),(22,7,47),(22,7,48),(22,8,49),(22,8,50),(22,8,51),(22,8,52),(22,8,53),(22,8,54),(22,9,55),(22,9,56),(22,9,57),(22,9,58),(23,1,59),(23,1,60),(23,1,61),(24,1,62),(24,1,63),(24,1,64),(25,1,65),(25,1,66),(25,1,67),(26,1,68),(26,1,69),(26,1,70),(26,7,71),(26,7,72),(26,7,73),(26,7,74),(27,1,75),(27,1,76),(27,1,77),(28,1,78),(28,1,79),(28,1,80);

/*Data for the table `source` */

insert  into `source`(`SOURCE_ID`,`NAME`,`URL`) values (1,'FIFA.com','http://es.fifa.com/rss/index.xml'),(2,'ESPN.com','http://search.espn.go.com/rss/amy-nelson/');

/*Data for the table `user` */

insert  into `user`(`USER_ID`,`CITY`,`COUNTRY`,`EMAIL`,`ID_CARD`,`AMOUNT`,`NAMES`,`PASSWORD`,`PHONE`,`PHOTOURL`,`ROLE`,`STATE`,`SURNAMES`,`USERNAME`) values (1,NULL,NULL,NULL,NULL,0,'Chuck Norris','a',NULL,'/resources/norris.jpg','administrador','',NULL,'a'),(2,'Montevideo','Uruguay','delbetu.usuario@gmail.com','158795465',125,'Usuario','u',NULL,'/resources/default_image.jpg','usuario','','Perez García','u'),(3,'Montevideo','Uruguay','delbetu.moderador@gmail.com','67489581',0,'Leslie','m',NULL,'/resources/leslie.jpg','moderador','','Neilsen','m'),(4,'Montevideo','Uruguay',NULL,NULL,0,'GRUPO 04','G04',NULL,'/resources/default_image.jpg','usuario','','G04','G04');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
