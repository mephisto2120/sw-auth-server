USE `auth` ;

INSERT INTO `role` (`rol_id`,`rol_name`,`rol_desc`) VALUES (1,'USER','Default role for all common users');
INSERT INTO `role` (`rol_id`,`rol_name`,`rol_desc`) VALUES (2,'MODERATOR','Role for users which can see some particular data of users');
INSERT INTO `role` (`rol_id`,`rol_name`,`rol_desc`) VALUES (3,'ADMIN','Role for administrator of system');

INSERT INTO `status` (`sts_id`,`sts_name`,`sts_desc`) VALUES (1,'AC','Indicates that user is active');
INSERT INTO `status` (`sts_id`,`sts_name`,`sts_desc`) VALUES (2,'IN','Indicates that user is inactive');
INSERT INTO `status` (`sts_id`,`sts_name`,`sts_desc`) VALUES (3,'DL','Indicates that user has been deleted');
INSERT INTO `status` (`sts_id`,`sts_name`,`sts_desc`) VALUES (4,'BN','Indicates that user has been banned');