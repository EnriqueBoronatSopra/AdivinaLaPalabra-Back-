
INSERT INTO `adivinalapalabra`.`user` SELECT 1,'maria','maria123'
WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 1);
INSERT INTO `adivinalapalabra`.`user` SELECT 2,'fran','fran456'
WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 2);
INSERT INTO `adivinalapalabra`.`user` SELECT 3,'pepe','pepe789'
WHERE NOT EXISTS(SELECT * FROM `user` WHERE id = 3);
COMMIT;


