# SQL Manager 2005 for MySQL 3.7.5.1
# ---------------------------------------
# Host     : 192.168.135.34
# Port     : 3388
# Database : auction


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES cp1251 */;

SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS auction;

CREATE DATABASE auction
    CHARACTER SET 'cp1251'
    COLLATE 'cp1251_general_ci';

USE auction;

#
# Structure for the `users` table : 
#

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  ID int(11) NOT NULL auto_increment,
  NAME varchar(100) NOT NULL,
  ADDRESS varchar(100) NOT NULL,
  LOGIN varchar(20) NOT NULL,
  PASSWORD varchar(20) NOT NULL,
  PRIMARY KEY  (ID),
  UNIQUE KEY ID (ID),
  UNIQUE KEY LOGIN (LOGIN,PASSWORD)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251 COMMENT='??????? ?????????????????? ????';

#
# Structure for the `items` table : 
#

DROP TABLE IF EXISTS items;

CREATE TABLE items (
  ID int(11) NOT NULL auto_increment,
  TITLE varchar(100) NOT NULL,
  DESCRIPTION varchar(100) default NULL,
  SELLER int(11) NOT NULL,
  START_PRICE float(9,2) NOT NULL,
  BID_INC float(9,2) default NULL,
  BEST_OFFER float(9,2) default NULL,
  BIDDER int(11) default NULL,
  STOP_DATE bigint(20) default NULL,
  BUY_IT_NOW smallint(6) default NULL,
  SOLD smallint(6) default '0',
  PRIMARY KEY  (ID),
  KEY SELLER (SELLER),
  KEY BIDDER (BIDDER),
  KEY STOP_DATE (STOP_DATE),
  CONSTRAINT items_fk1 FOREIGN KEY (SELLER) REFERENCES users (ID),
  CONSTRAINT items_fk2 FOREIGN KEY (BIDDER) REFERENCES users (ID)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;

#
# Data for the `users` table  (LIMIT 0,500)
#

INSERT INTO `users` (ID, NAME, ADDRESS, LOGIN, PASSWORD) VALUES 
  (7,'Mr.Smith','smith@mail.com','1','111111'),
  (8,'Mr.Black','black@mail.com','2','222222');

COMMIT;

#
# Data for the `items` table  (LIMIT 0,500)
#

INSERT INTO `items` (ID, TITLE, DESCRIPTION, SELLER, START_PRICE, BID_INC, BEST_OFFER, BIDDER, STOP_DATE, BUY_IT_NOW, SOLD) VALUES 
  (11,'Old noisy cat','fat bastard',8,100,NULL,100,7,1227676683187,1,1),
  (12,'Grey Old Piano','i hate music',7,200,10,270.99,8,1243226246875,0,0),
  (13,'dog','dolmatin',7,100,NULL,100,8,1228544677953,1,1),
  (14,'PHONE','black',8,200,NULL,200,7,1227681277375,1,1),
  (15,'cup','of cofee',7,20,NULL,20,8,1229409357640,1,1),
  (16,'item','1',7,300,10,NULL,NULL,1226916506656,0,0),
  (17,'1','1',7,500,10,600,8,1226400640187,0,0),
  (18,'Computer','Intel Pentium 4',8,1000,100,NULL,NULL,1226833045968,0,0);

COMMIT;



/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;