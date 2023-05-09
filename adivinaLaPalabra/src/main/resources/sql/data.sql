
INSERT INTO `adivinalapalabra`.`user` SELECT 1,'maria','mariabd34'
WHERE NOT EXISTS(SELECT 1 FROM `user` WHERE name = 'maria');
INSERT INTO `adivinalapalabra`.`user` SELECT 2,'fran','fran456'
WHERE NOT EXISTS(SELECT 2 FROM `user` WHERE name = 'fran');
INSERT INTO `adivinalapalabra`.`user` SELECT 3,'pepe','pepe789'
WHERE NOT EXISTS(SELECT 3 FROM `user` WHERE name = 'pepe');
COMMIT;


