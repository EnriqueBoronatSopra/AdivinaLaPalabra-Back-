
INSERT INTO `adivinalapalabra`.`user` SELECT 1,'maria','$2a$10$b19JdQp1xIN8ZDHBXXwloe0UrBz/3gUm9ov.fTdacZ0ZsT9xlZhOK'
 WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 1);
INSERT INTO `adivinalapalabra`.`user` SELECT 2,'fran','$2a$10$uiqIptVkAsrAJorYJ6.sdu5gmd4x3e3ZavQ.xJSa3yUQ8F0JryQl2'
 WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 2);
INSERT INTO `adivinalapalabra`.`user` SELECT 3,'pepe','$2a$10$z3ULyP5i88QEX.o9Wh78.etXEqP5IwvCawnnGdydf.PP2JAXD7se6'
 WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 3);
COMMIT;