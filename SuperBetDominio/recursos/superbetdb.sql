
USE `superbetdb`;


INSERT INTO `superbetdb`.`user` VALUES (null, null,null,null,null,null,"Chuck Norris","a",null,"http://www.los3dragones.com/imagenes/chucknorris/norris4.jpg","administrador", "registered", null,"a");

/*
USER_ID, CITY,         COUNTRY, EMAIL ,                       ID_CARD , AMOUNT , NAMES ,    PASSWORD , PHONE , PHOTOURL                                                       , ROLE , SURNAMES        , USERNAME
*/
INSERT INTO `superbetdb`.`user` VALUES 
(null,   "MONTEVIDEO","URUGUAY","delbetu+usuario@gmail.com","158795465",0.0    ,"Usuario", "u",      null,    "http://mubeco.webuda.com/modules/Members/images/pas_image.jpg","usuario","registered", "Perez García" ,"u");

INSERT INTO `superbetdb`.`user` VALUES 
(null,    "MONTEVIDEO","URUGUAY","delbetu+moderador@gmail.com","67489581", NULL, 'Leslie', 'm',     NULL, 'http://lafarandula.com/wp-content/uploads/2010/11/leslie-nielsen.jpg', 'moderador', 'registered', 'Neilsen', 'm');

/* Inserto el usuario que representa al otro grupo */
insert  into `user`(`USER_ID`,`CITY`,`COUNTRY`,`EMAIL`,`ID_CARD`,`AMOUNT`,`NAMES`,`PASSWORD`,`PHONE`,`PHOTOURL`,`ROLE`,`STATE`,`SURNAMES`,`USERNAME`) values (4,NULL,NULL,NULL,NULL,NULL,'Grupo 05','g05',NULL,NULL,'usuario','',NULL,'g05');




/* Datos para tipos de apuesta */

insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (1,'Apuesta Simple');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (2,'Colocado');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (3,'Combinada');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (4,'En Directo');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (5,'Largo plazo');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (6,'MedioTiempo / Final');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (7,'Totales(Over/under)');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (8,'Resultado exacto');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (9,'Primer goleador');
insert  into `bet_type`(`BET_TYPE_ID`,`NAME`) values (10,'Primera parte');

/*Datos para deportes */

insert  into `sport`(`SPORT_ID`,`NAME`) values (1,'Automovilismo'),(2,'Basquetbol'),(3,'Boxeo'),(4,'Futbol');

/* Datos para competiciones */

insert  into `contest`(`CONTEST_ID`,`NAME`,`SPORT_ID`) values (1,'Campeonato Uruguayo Apertura 2011',1),(2,'Futbol Argentino',1),(3,'Futbol Brasilero',2),(4,'Champions League',2),(5,'JJ.OO Londres 2012 - Basquetbol (M)',2),(6,'JJ.OO Londres 2012 - Basquetbol (F)',2),(7,'WBC Campeonato Mundial - Peso mosca (M)',3),(8,'WBC Campeonato Mundial - Peso mediano (M)',3),(9,'Eurocopa 2012',4),(10,'Copa Libertadores 2012',4),(11,'Copa Sudamericana 2012',4),(13,'UEFA Champions League 2011-2012',4),(14,'Campeonato mundial Brasil 2013',5),(15,'JJ.OO Londres 2012 - 100 m. llanos (M)',6),(16,'JJ.OO Londres 2012 - 100 m. llanos (F)',6),(17,'JJ.OO Londres 2012 - Lanzamiento de disco (M)',6);

/* Datos para paises */

insert  into `country`(`COUNTRY_ID`,`NAME`) values (1,'Uruguay'),(2,'Argentina'),(3,'Brasil'),(4,'Chile'),(5,'Bolivia'),(6,'Paraguay'),(7,'Italia'),(8,'Inglaterra'),(9,'España'),(10,'Portugal'),(11,'Alemania'),(12,'Francia'),(13,'Holanda');

/* Fuentes RSS */

INSERT INTO `source`(`SOURCE_ID`,`NAME`,`URL`) VALUES (1,'FIFA.com','http://es.fifa.com/rss/index.xml'),(2,'ESPN.com','http://search.espn.go.com/rss/amy-nelson/');
