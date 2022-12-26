use vallytooldb;
INSERT INTO `trimestre_atual` VALUES(default, true, 'PRIMEIRO');
INSERT INTO `usuario` VALUES (1,'00000000000', 'host@gmail.com','Host', '$2a$10$JRZrRRmKHjAfVrWQsyP43u3aBgy7oStir847QlIe6YRkWYr1R2CxG');
INSERT INTO `roles` VALUES (1,'ROLE_PROFESSOR'),(2,'ROLE_SOP');
INSERT INTO `usuario_roles` VALUES (1,2);