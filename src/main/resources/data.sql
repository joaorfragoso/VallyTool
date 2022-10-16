CREATE DATABASE vallytooldb;
use vallytooldb;

INSERT INTO turmas VALUES (1, '110');
INSERT INTO turmas VALUES (2, '120');
INSERT INTO turmas VALUES (3, '130');
INSERT INTO turmas VALUES (4, '140');
INSERT INTO turmas VALUES (5, '150');
INSERT INTO turmas VALUES (6, '210');
INSERT INTO turmas VALUES (7, '220');
INSERT INTO turmas VALUES (8, '230');
INSERT INTO turmas VALUES (9, '240');
INSERT INTO turmas VALUES (10, '250');
INSERT INTO turmas VALUES (11, '310');
INSERT INTO turmas VALUES (12, '320');
INSERT INTO turmas VALUES (13, '330');
INSERT INTO turmas VALUES (14, '340');
INSERT INTO turmas VALUES (15, '350');

INSERT INTO tb_prof_turmas VALUES (3, 1);
INSERT INTO tb_prof_turmas VALUES (3, 6);
INSERT INTO tb_prof_turmas VALUES (3, 11);

INSERT INTO disciplinas VALUES (1, "Matemática");
INSERT INTO disciplinas VALUES (2, "Língua Portuguesa");
INSERT INTO disciplinas VALUES (3, "Inglês");
INSERT INTO disciplinas VALUES (4, "Química");
INSERT INTO disciplinas VALUES (5, "Física");
INSERT INTO disciplinas VALUES (6, "Geografia");
INSERT INTO disciplinas VALUES (7, "História");

INSERT INTO tb_prof_disciplinas VALUES (3, 1);
INSERT INTO tb_prof_disciplinas VALUES (3, 5);
