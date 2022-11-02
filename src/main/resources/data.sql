CREATE DATABASE vallytooldb;
use vallytooldb;
INSERT INTO `trimestre_atual` VALUES(default, true, 'PRIMEIRO');
INSERT INTO `turmas` VALUES (1,'110'),(2,'210'),(3,'310');
INSERT INTO `usuario` VALUES (1,'19216800001', 'joaofragoso376@gmail.com','Jonas Lucifer', '$2a$10$Cp7fcbz16uKsQ1hkoGfCwOTqZc8ptXcPSnina7735R09rA/oGbFSe'),(2,'19216800002', 'felipe@gmail.com','Felipe Adminastror', '$2a$10$bzMoezYrftA5SM0zKp.Mnu78128/0L6xrqwiw3XykblrT.yU7GpW.');
INSERT INTO `competencias` VALUES (1,'Atitude'),(2,'Participação'),(3,'Aproveitamento'),(4,'Entrosamento'),(5,'Frequência'),(6,'Relação Aluno/Professor');
INSERT INTO `disciplinas` VALUES (1,'Matemática'),(2,'Língua Portuguesa'),(3,'Inglês'),(4,'Química'),(5,'Física'),(6,'Geografia'),(7,'História');
INSERT INTO `roles` VALUES (1,'ROLE_PROFESSOR'),(2,'ROLE_SOP');
INSERT INTO `usuario_disciplinas` VALUES (1,1),(1,5);
INSERT INTO `usuario_turmas` VALUES (1,1),(1,2),(1,3);
INSERT INTO `usuario_roles` VALUES (1,1),(2,2);
INSERT INTO `turmas_disciplinas` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),
										(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),
										(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7);