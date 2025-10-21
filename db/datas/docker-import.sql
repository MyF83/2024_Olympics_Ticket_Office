

-- Insert countries 
INSERT IGNORE INTO countries (country_id, name) VALUES (1, 'France');
INSERT IGNORE INTO countries (country_id, name) VALUES (2, 'United Kingdom');
INSERT IGNORE INTO countries (country_id, name) VALUES (3, 'Sweden');
INSERT IGNORE INTO countries (country_id, name) VALUES (4, 'Italy');
INSERT IGNORE INTO countries (country_id, name) VALUES (5, 'Germany');
INSERT IGNORE INTO countries (country_id, name) VALUES (6, 'Brazil');
INSERT IGNORE INTO countries (country_id, name) VALUES (7, 'Marocco');
INSERT IGNORE INTO countries (country_id, name) VALUES (8, 'Spain');
INSERT IGNORE INTO countries (country_id, name) VALUES (9, 'Portugal');
INSERT IGNORE INTO countries (country_id, name) VALUES (10, 'Belgium');
INSERT IGNORE INTO countries (country_id, name) VALUES (11, 'Netherlands');
INSERT IGNORE INTO countries (country_id, name) VALUES (12, 'Switzerland');
INSERT IGNORE INTO countries (country_id, name) VALUES (13, 'United States');
INSERT IGNORE INTO countries (country_id, name) VALUES (14, 'Canada');
INSERT IGNORE INTO countries (country_id, name) VALUES (15, 'Australia');
INSERT IGNORE INTO countries (country_id, name) VALUES (16, 'New Zealand');
INSERT IGNORE INTO countries (country_id, name) VALUES (17, 'South Africa');
INSERT IGNORE INTO countries (country_id, name) VALUES (18, 'Japan');
INSERT IGNORE INTO countries (country_id, name) VALUES (19, 'China');
INSERT IGNORE INTO countries (country_id, name) VALUES (20, 'India');


-- Insert sites 
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (1, 'Stade de France', 'Stadium for the opening ceremony', 'Stade de France', 'Saint-Denis', '93216', null, 10000, 3000, 1000);
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (1, 'Stade de France', 'Stadium for the opening and closing ceremony, seven-players rugby, and athletics', 'Stade de France', 'Saint-Denis', '93216', 1, 10000, 3000, 1000, 'https://maps.app.goo.gl/tdLaZvNvihKx9YA7A');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (2, 'The Seine river', 'Place for opening ceremony', 'La Seine', 'Paris', '75000', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/jjEixk1g4v81rH2D8');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (3, 'Olympic swimming-pool', 'Place for swimming, diving and water-polo', 'Piscine Georges-Vallerey, 148 Avenue Gambetta', 'Paris', '75020', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/WzfJVvpzyYU7rcvBA');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (4, 'Urban Park at The Concorde', 'Place for basket-ball 3x3, BMX freestyle, breakdance and skateboard', 'Place de la Concorde', 'Paris', '75008', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/iobnhok3u4TJcAKC7');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (5, 'Stadium Pierre-Mauroy', 'Place for basketball and handball matches', '261 Boulevard de Tournai', "Villeneuve-d'Ascq", '59650', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/zLoeTjcUymCL6MUW6');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (6, 'Stadium of La Beaujoire', 'Place for football matches', '5 Rue de la Beaujoire', 'Nantes', '44300', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/FVeWJ4iuYqrHATbF6');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (7, 'Stadium Allianz Riviera of Nice', 'Place for football matches', 'Boulevard des Jardiniers', 'Nice', '06200', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/ayYezdjh9wFZiCpC6');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (8, 'Atlantic Metropolitan Stadium of Bordeaux', 'Place for football matches', 'Cours Jules Ladoumègue', 'Bordeaux', '33300', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/kfTRXiVLZpvhcpYE9');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (9, 'Velodrome Stadium Of Marseille Orange Velodrome', 'Place for football games', '3 Boulevard Michelet', 'Marseille', '13008', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/42t6KQeu8Tp8HpcS7');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (10, 'Olympic Park of Lyon Groupama Stadium', 'Place for football matches', '10 Avenue Simone Veil', 'Décines-Charpieu', '69150', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/p11aeh3BvNcBAYUc6');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (11, 'Stadium of Roland-Garros', 'Place for tennis and boxing matches', '2 Avenue Gordon Bennett', 'Paris', '75016', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/XXFRDCMq8uKJdjveA');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (12, 'Stadium of Bercy, Accor Arena', 'Place for basket-ball, trampoline, rhythmic gymnastics', '8 Boulevard de Bercy', 'Paris', '75012', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/ojb3yWLVkbFhsyRY8');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (13, 'Invalid Esplanade', 'Place for the archery competition', 'Esplanade des Invalides', 'Paris', '75007', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/UajYuBETZjHvtXGw9');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (14, 'Great Palace', 'Place for the taekwondo and fencing meetings', '3 Avenue du Général Eisenhower', 'Paris', '75008', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/GHkvoDZ2WJ3D1rXJ8');
INSERT IGNORE INTO sites (site_id, name, description, address, city, postalCode, country_id, nbseatsc1, nbseatsc2, nbseatsc3, planUrl) VALUES (15, 'Porte de Versailles Exhibition Center', 'Place for table tennis, volleyball, handball and weightlifting', '1 Place de la Porte de Versailles', 'Paris', '75015', 1, 20000, 5000, 2000, 'https://maps.app.goo.gl/S78Y9tmgNcYXxGi39');



-- Insert offers 
INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nbSpectators) VALUES (1, 'Solo Offer', 'Get 5% off with the Solo Offer', 'png/Solo.png', 5, 1);
INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nbSpectators) VALUES (2, 'Duo Offer', 'A 15% off for a 2-persons discount ticket', 'png/Duo.png', 15, 2);
INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nbSpectators) VALUES (3, 'Family Offer', 'Get 35% off with a 4-persons family Offer', 'png/Family.png', 35, 4);

-- Insert policies 
INSERT IGNORE INTO policies (policy_id, title, description, url, creationDate, version, isActive) VALUES (1, 'Security Policies', 'Policies of the Olympic Games Ticketing System : every user of this application must have a secure password. In the case not, may encounter many years in jail !', 'policies/policy_1.html', '2023-10-01', 1, false);
INSERT IGNORE INTO policies (policy_id, title, description, url, creationDate, version, isActive) VALUES (2, 'Security Policies', 'Policies of the Olympic Games Ticketing System (version 2) : every user of this application must have a secure password. In the case not, may encounter many years in jail !','policies/policy_2.html', '2024-02-23', 2, true);


-- Insert roles 
INSERT IGNORE INTO roles (role_id, name, description) VALUES (1, 'SUPERADMIN', 'Super administrator with ALL permissions');
INSERT IGNORE INTO roles (role_id, name, description) VALUES (2, 'EMPLOYEE', 'Employee with limited permissions for managing sales, sports, offers, events and other informations');
INSERT IGNORE INTO roles (role_id, name, description) VALUES (3, 'USER', 'User with restricted permissions'); 



-- Insert keysgenerations 
INSERT IGNORE INTO keysgenerations (key_id, keyGenerated) VALUES (1, 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9Gv');
INSERT IGNORE INTO keysgenerations (key_id, keyGenerated) VALUES (2, 'FuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C');
INSERT IGNORE INTO keysgenerations (key_id, keyGenerated) VALUES (3, '123ygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fll12345');
INSERT IGNORE INTO keysgenerations (key_id, keyGenerated) VALUES (4, '85269478tjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M901258493681');



-- Insert challengers 
-- INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', null);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (2, 'Mickael Kadoz',2);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (3, 'Emily Watson', 2);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (4, 'Tommy Johansson', 3);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (5, 'Luca Rossi', 4);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (6, 'Hans Müller', 5);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (7, 'Carlos Silva', 6);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (8, 'Fatima El Amrani', 7);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (9, 'Sofia Garcia', 8);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (10, 'Miguel Oliveira', 9);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (11, 'Emma Dupont', 10);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (12, 'Liam van der Meer', 11);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (13, 'Sophie Dubois', 12);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (14, 'Ethan Brown', 13);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (15, 'Ava Johnson', 14);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (16, 'Oliver Smith', 15);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (17, 'Charlotte Wilson', 16);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (18, 'Lucas Taylor', 17);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (19, 'Mia Anderson', 17);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (20, 'Noah Thomas', 19);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (21, 'Chi Ling', 19);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (22, 'Yuki Tanaka', 18);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (23, 'Akira Yamamoto', 18);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (24, 'Hana Suzuki', 18);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (25, 'Li Wei', 19);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (26, 'Wang Fang', 19);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (27, 'Zhang Wei', 19); 
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (28, 'Dinesh Kapoor,', 20);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (29, 'Priya Sharma', 20);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (30, 'Rajesh Patel', 20);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (31, 'Caroline Janson', 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (32, "French Men's Rugby Sevens Team", 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (33, 'Equipe de France féminine de plongeon', 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (34, "French women's diving team", 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (35, "French men's swimming team", 1);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (36, "U.S. Men's Swimming Team", 13);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (37, "Swedish men's diving team", 3);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (38, "Swedish women's diving team", 3);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (39, "Swedish women's volleyball team", 3);
 INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (40, "French women's volleyball team", 1);






-- Insert ceremonies 
-- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', null);
INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', 2);
INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (2, 'Closing Ceremony of Paralympic Games', 'The closing ceremony of the Olympic Games for paralympic games', 1);
INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (3, 'Closing Ceremony', 'The closing ceremony of the Olympic Games', 1);
INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (4, 'Opening Ceremony of Paralympic Games', 'The opening ceremony of the Paralympic Games', 4);


-- Insert sports 
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (1, 'Athletics', 'Athletics is a collection of sporting events that involves competitive running, jumping, throwing, and walking.', false, null);
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (1, 'Swimming', 'Swimming olympic games competition', false, 3);
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (2, 'Archery', 'Archery olympic games competition', true, 13);
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (3, 'Table-tennis', 'Table-tennis olympic games competition', false, 15);
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (4, 'Volleyball', 'Volleyball olympic games competition', true, 15); 
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (5, 'Handball', 'Handball olympic games competition', false, 15);
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (6, 'Taekwondo', 'Taekwondo olympic games competition', false, 14);    
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (7, 'Fencing', 'Fencing olympic games competition', true, 14);        
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (8, 'Boxing', 'Boxing olympic games competition', true, 11); 
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (9, 'Football', 'Football olympic games competition', true, 7); 
INSERT IGNORE INTO sports (sport_id, name, description, isParalympic, site_id) VALUES (10, 'BMX freestyle', 'BMX freestyle olympic games competition', true, 4);






-- Insert events 
-- INSERT IGNORE INTO events (event_id, title, date, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, '2024-07-04', 'The opening ceremony of the Olympic Games', null, null, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, 'The opening ceremony', '2024-06-29', '20h00', 'img/Overture-ceremony-sml.jpg', 'The opening ceremony of the Olympic Games will begin at 20h00 (GMT Paris) and will probably finish at 22h00, maybe more. It will take place in the border of the Seine. Many artists will be there tonight to celebrate this event.', null, 1, null, null, 1590.50, 1, 9999, 1899.90, 1, 2999, 11299.55, 1, 999);
INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (2, 'First table tennis men match', '2024-07-04', '14h00', 'img/Table-tennis-men-sml.jpg', 'First table tennis men match will start at 14h00.', 3, null, 1, 4, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (3, 'First voleyball women match','2024-07-04', '15h30', 'img/Volley-ball-women-sml.jpg', 'First voleyball women match to start the course to the golden medal, at 15h30.', 4, null, 38, 39, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (4, 'Closing ceremony','2024-07-21', '20h30', 'img/Closing-ceremony-sml.jpg', 'Closing ceremony of the Olympic Games will start at 20h30 (GMT Paris) on the France Stadium of Paris.', null, 3, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);





-- Insert userselections 
-- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (1, 3, 457.25, null, null);
INSERT IGNORE INTO userselections (usersel_id, nbPersons, amount, offer_id, event_id) VALUES (1, 1, 457.25, 1, 1);
INSERT IGNORE INTO userselections (usersel_id, nbPersons, amount, offer_id, event_id) VALUES (2, 1, 851.55, 1, 1);
INSERT IGNORE INTO userselections (usersel_id, nbPersons, amount, offer_id, event_id) VALUES (3, 3, 851.55, 2, 1);
INSERT IGNORE INTO userselections (usersel_id, nbPersons, amount, offer_id, event_id) VALUES (4, 4, 851.55, 3, 1);




-- Insert userskeys 
-- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (1, '2024-03-12', null, null);
-- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (1, '2022-03-12', null, 1);
-- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (2, '2024-01-13', null, 2);
-- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (3, '2024-04-17', null, 3);
-- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (4, '2024-04-18', null, 4);
INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (1, '2022-03-12', 1);
INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (2, '2024-01-13', 2);
INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (3, '2024-04-17', 3);
INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (4, '2024-04-18', 4);



-- Insert users 
-- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', 'passwordnotsecuredAdmin', '0698765432', '36 Quai des Orfèvres', '2022-01-01', null, null, null, null, '2023-10-01', 'Paris', '75001', 1);
INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phoneNumber, address, creationDate, role_id, userkey_id, usersel_id, policy_id, policySignDate, city, postalCode, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', '$2a$10$4BKCmFTHVs.dR0P47MocpuOp3X4OgPaI73T8kVxiKRTPLHi6yk/mC', '0698765432', '36 Quai des Orfèvres', '2022-01-01', 1, 1, null, 1, '2023-10-01', 'Paris', '75001', 1);
INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phoneNumber, address, creationDate, role_id, userkey_id, usersel_id, policy_id, policySignDate, city, postalCode, country_id) VALUES (2, 'Myriam', 'Fournier', 'Fournier-Myriam', 'superbachelor@etudiants.fr', '$2a$10$leSMRP2E1fpcS9nU6ZLnke1ncNKIQmmx7eXnYSaf.kgsQlz3GCMgy', '0612345678', '1 place de la Concorde', '2023-10-01', 3, 2, 1, 1, '2023-10-01', 'Paris', '75001', 1);
INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phoneNumber, address, creationDate, role_id, userkey_id, usersel_id, policy_id, policySignDate, city, postalCode, country_id) VALUES (3, 'James', 'Bond', 'Bond-James', '007@british-secret-services.uk', '$2a$10$zo.EdUjGAxBoNctBe5vsR.D8iMiHwes8BFWGazqdILcvjD4MnfHVm', '007007007', '36 Ravenstreet', '2023-10-05', 3, 3, 2, 1, '2023-10-05', 'London', '25099', 2);
INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phoneNumber, address, creationDate, role_id, userkey_id, usersel_id, policy_id, policySignDate, city, postalCode, country_id) VALUES (4, 'Oyee', 'Empl', 'Empl-Oyee', 'employee@joparis2024.fr', '$2a$10$rfXZfQf99jbNn48qxOl3X.TnTXurA1zDmr1HFc/9QX3zomNggrr62', '0606060606', '12 rue des Lumières', '2024-03-05', 2, 4, 3, 2, '2024-03-05', 'Paris', '75001', 1);





-- Insert carts 
-- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id, usersel_id) VALUES (1, '2024-03-12', 1257.30, null, null);
-- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id, usersel_id) VALUES (1, '2024-03-12', 1257.30, 3, 1);
INSERT IGNORE INTO carts (cart_id, date, totalAmount, user_id) VALUES (1, '2024-03-12', 1257.30, 3);
INSERT IGNORE INTO carts (cart_id, date, totalAmount, user_id) VALUES (2, '2024-03-13', 328.90, 2);
INSERT IGNORE INTO carts (cart_id, date, totalAmount, user_id) VALUES (3, '2024-03-20', 726.20, 4);

-- Insert saleskeys 
-- INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (1, '2024-03-12', null);
INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (1, '2024-03-12', 3);
INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (2, '2024-03-13', 2);
INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (3, '2024-03-20', 4);

-- Insert sales 
-- INSERT IGNORE INTO sales (sale_id, date, user_id, cart_id, salekey_id) VALUES (1, '2024-03-12', null, null, null);
-- INSERT IGNORE INTO sales (sale_id, date, user_id, cart_id, salekey_id) VALUES (1, '2024-03-12', 3, 1, 1);
INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (1, '2024-03-12', 1, 1);
INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (2, '2024-03-13', 2, 2);
INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (3, '2024-03-20', 3, 3);


-- Insert tickets 
-- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, userkey_id, salekey_id, sale_id, cart_id, user_id, usersel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', null, null, null, null, null, null);
-- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, userkey_id, salekey_id, sale_id, cart_id, user_id, usersel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 3, 1, 1, 1, 3, 1);
INSERT IGNORE INTO tickets (ticket_id, date, keyAssembly, fileName, sale_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 1);
INSERT IGNORE INTO tickets (ticket_id, date, keyAssembly, fileName, sale_id) VALUES (2, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 2);
INSERT IGNORE INTO tickets (ticket_id, date, keyAssembly, fileName, sale_id) VALUES (3, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 3);

-- Insert controls 
-- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, null, null, null);
-- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', true, 1, 3, 1);
-- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (2, '2024-03-15', '0jm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, 2, 2, 1);
INSERT IGNORE INTO controls (control_id, date, scancode, isTicketValid, ticket_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', true, 1);






-- -- Insert countries 
-- INSERT IGNORE INTO countries (country_id, name) VALUES (1, 'France');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (2, 'United Kingdom');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (3, 'Sweden');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (4, 'Italy');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (5, 'Germany');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (6, 'Brazil');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (7, 'Marocco');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (8, 'Spain');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (9, 'Portugal');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (10, 'Belgium');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (11, 'Netherlands');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (12, 'Switzerland');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (13, 'United States');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (14, 'Canada');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (15, 'Australia');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (16, 'New Zealand');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (17, 'South Africa');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (18, 'Japan');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (19, 'China');
-- INSERT IGNORE INTO countries (country_id, name) VALUES (20, 'India');


-- -- Insert sites 
-- -- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (1, 'Stade de France', 'Stadium for the opening ceremony', 'Stade de France', 'Saint-Denis', '93216', null, 10000, 3000, 1000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (1, 'Stade de France', 'Stadium for the opening and closing ceremony, seven-players rugby, and athletism', 'Stade de France', 'Saint-Denis', '93216', 1, 10000, 3000, 1000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (2, 'The Seine river', 'Place for opening ceremony', 'The Seine river', 'Saint-Denis', '93216', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (3, 'Olympic swimming-pool', 'Place for swimming, diving and water-polo', 'Piscine Georges-Vallerey, 148 av. Gambetta', 'Saint-Denis', '93216', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (4, 'Urban Park at The Concorde', 'Place for the opening ceremony for paralympic games, basket-ball 3x3, BMX freestyle, breakdance and skateboard', 'Concorde square', 'Paris', '93000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (5, 'Stadium Pierre-Mauroy', 'Place for the opening ceremony for paralympic games', 'Stade Pierre-Mauroy', 'Lille', '59000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (6, 'Stadium of La Beaujoire', 'Place for football matches', 'Stade de la Beaujoire', 'Nantes', '44000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (7, 'Stadium Allianz Riviera of Nice ', 'Place for the football matches', 'Stade de Nice', 'Nice', '06000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (8, 'Stadium of Bordeaux', 'Place for football matches', 'Stade de Bordeaux', 'Bordeaux', '33000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (9, 'Velodrome Stadium Of Marseille', 'Place for paralympic football games', 'Stadium of Marseille', 'Marseille', '13000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (10, 'Olympic Park of Lyon', 'Place for paralympic football matches', 'Stade de Lyon', 'Lyon', '69000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (11, 'Stadium of Roland-Garros', 'Place for tennis and boxe matches', 'Roland-Garros', 'Paris', '78000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (12, 'Stadium of Bercy', 'Place for basket-ball, trampoline, rhythmic gymnastics', 'Stade de Bercy', 'Paris', '75000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (13, 'Invalid Esplanade', 'Place for the archery competition', 'Invalid Esplanade', 'Paris', '76000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (14, 'Great Palace', 'Place for the taekwondo and fencing meetings', 'Grand Palais', 'Paris', '78000', 1, 20000, 5000, 2000);
-- INSERT IGNORE INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (15, 'Porte de Versailles Exhibition Center', 'Place for the table-tennis, volleyball, handball and weightlifting meetings', 'Porte de Versailles Exhibition Center', 'Paris', '78000', 1, 20000, 5000, 2000);


-- -- Insert offers 
-- INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nb_spectators) VALUES (1, 'Solo Offer', 'Get 5% off with the Solo Offer', 'png/Solo.png', 5, 1);
-- INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nb_spectators) VALUES (2, 'Duo Offer', 'A 15% off for a 2-persons discount ticket', 'png/Duo.png', 15, 2);
-- INSERT IGNORE INTO offers (offer_id, title, description, image, rate, nb_spectators) VALUES (3, 'Family Offer', 'Get 35% off with a 4-persons family Offer', 'png/Family.png', 35, 4);

-- -- Insert policies 
-- INSERT IGNORE INTO policies (policy_id, title, description, url, creation_date, version, is_active) VALUES (1, 'Security Policies', 'Policies of the Olympic Games Ticketing System : every user of this application must have a secure password. In the case not, may encounter many years in jail !', 'policies/policy_1.html', '2023-10-01', 1, false);
-- INSERT IGNORE INTO policies (policy_id, title, description, url, creation_date, version, is_active) VALUES (2, 'Security Policies', 'Policies of the Olympic Games Ticketing System (version 2) : every user of this application must have a secure password. In the case not, may encounter many years in jail !','policies/policy_2.html', '2024-02-23', 2, true);


-- -- Insert roles 
-- INSERT IGNORE INTO roles (role_id, name, description) VALUES (1, 'SUPERADMIN', 'Super administrator with ALL permissions');
-- INSERT IGNORE INTO roles (role_id, name, description) VALUES (2, 'EMPLOYEE', 'Employee with limited permissions for managing sales, sports, offers, events and other informations');
-- INSERT IGNORE INTO roles (role_id, name, description) VALUES (3, 'USER', 'User with restricted permissions'); 



-- -- Insert keysgenerations 
-- INSERT IGNORE INTO keysgenerations (key_id, key_generated) VALUES (1, 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9Gv');
-- INSERT IGNORE INTO keysgenerations (key_id, key_generated) VALUES (2, 'FuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C');
-- INSERT IGNORE INTO keysgenerations (key_id, key_generated) VALUES (3, '123ygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fll12345');
-- INSERT IGNORE INTO keysgenerations (key_id, key_generated) VALUES (4, '85269478tjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M901258493681');



-- -- Insert challengers 
-- -- INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', null);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (2, 'Mickael Kadoz',2);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (3, 'Emily Watson', 2);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (4, 'Tommy Johansson', 3);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (5, 'Luca Rossi', 4);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (6, 'Hans Müller', 5);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (7, 'Carlos Silva', 6);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (8, 'Fatima El Amrani', 7);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (9, 'Sofia Garcia', 8);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (10, 'Miguel Oliveira', 9);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (11, 'Emma Dupont', 10);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (12, 'Liam van der Meer', 11);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (13, 'Sophie Dubois', 12);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (14, 'Ethan Brown', 13);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (15, 'Ava Johnson', 14);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (16, 'Oliver Smith', 15);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (17, 'Charlotte Wilson', 16);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (18, 'Lucas Taylor', 17);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (19, 'Mia Anderson', 17);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (20, 'Noah Thomas', 19);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (21, 'Chi Ling', 19);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (22, 'Yuki Tanaka', 18);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (23, 'Akira Yamamoto', 18);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (24, 'Hana Suzuki', 18);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (25, 'Li Wei', 19);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (26, 'Wang Fang', 19);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (27, 'Zhang Wei', 19); 
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (28, 'Dinesh Kapoor,', 20);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (29, 'Priya Sharma', 20);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (30, 'Rajesh Patel', 20);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (31, 'Caroline Janson', 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (32, "French Men's Rugby Sevens Team", 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (33, 'Equipe de France féminine de plongeon', 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (34, "French women's diving team", 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (35, "French men's swimming team", 1);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (36, "U.S. Men's Swimming Team", 13);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (37, "Swedish men's diving team", 3);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (38, "Swedish women's diving team", 3);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (39, "Swedish women's volleyball team", 3);
--  INSERT IGNORE INTO challengers (challenger_id, name, country_id) VALUES (40, "French women's volleyball team", 1);






-- -- Insert ceremonies 
-- -- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', null);
-- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', 2);
-- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (2, 'Closing Ceremony of Paralympic Games', 'The closing ceremony of the Olympic Games for paralympic games', 1);
-- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (3, 'Closing Ceremony', 'The closing ceremony of the Olympic Games', 1);
-- INSERT IGNORE INTO ceremonies (cerem_id, name, description, site_id) VALUES (4, 'Opening Ceremony of Paralympic Games', 'The opening ceremony of the Paralympic Games', 4);


-- -- Insert sports 
-- -- INSERT IGNORE INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (1, 'Athletics', 'Athletics is a collection of sporting events that involves competitive running, jumping, throwing, and walking.', false, null);
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (1, 'Swimming', 'Swimming olympic games competition', false, 3);
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (2, 'Archery', 'Archery olympic games competition', true, 13);
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (3, 'Table-tennis', 'Table-tennis olympic games competition', false, 15);
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (4, 'Volleyball', 'Volleyball olympic games competition', true, 15); 
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (5, 'Handball', 'Handball olympic games competition', false, 15);
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (6, 'Taekwondo', 'Taekwondo olympic games competition', false, 14);    
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (7, 'Fencing', 'Fencing olympic games competition', true, 14);        
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (8, 'Boxing', 'Boxing olympic games competition', true, 11); 
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (9, 'Football', 'Football olympic games competition', true, 7); 
-- INSERT IGNORE INTO sports (sport_id, name, description, is_paralympic, site_id) VALUES (10, 'BMX freestyle', 'BMX freestyle olympic games competition', true, 4);






-- -- Insert events 
-- -- INSERT IGNORE INTO events (event_id, title, date, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, '2024-07-04', 'The opening ceremony of the Olympic Games', null, null, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
-- INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, 'The opening ceremony', '2024-06-29', '20h00', 'img/Overture-ceremony-sml.jpg', 'The opening ceremony of the Olympic Games will begin at 20h00 (GMT Paris) and will probably finish at 22h00, maybe more. It will take place in the border of the Seine. Many artists will be there tonight to celebrate this event.', null, 1, null, null, 1590.50, 1, 9999, 1899.90, 1, 2999, 11299.55, 1, 999);
-- INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (2, 'First table tennis men match', '2024-07-04', '14h00', 'img/Table-tennis-men-sml.jpg', 'First table tennis men match will start at 14h00.', 3, null, 1, 4, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
-- INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (3, 'First voleyball women match','2024-07-04', '15h30', 'img/Volley-ball-women-sml.jpg', 'First voleyball women match to start the course to the golden medal, at 15h30.', 4, null, 38, 39, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
-- INSERT IGNORE INTO events (event_id, title, date, time, image, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (4, 'Closing ceremony','2024-07-21', '20h30', 'img/Closing-ceremony-sml.jpg', 'Closing ceremony of the Olympic Games will start at 20h30 (GMT Paris) on the France Stadium of Paris.', null, 3, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);





-- -- Insert userselections 
-- -- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (1, 3, 457.25, null, null);
-- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (1, 1, 457.25, 1, 1);
-- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (2, 1, 851.55, 1, 1);
-- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (3, 3, 851.55, 2, 1);
-- INSERT IGNORE INTO userselections (usersel_id, nb_persons, amount, offer_id, event_id) VALUES (4, 4, 851.55, 3, 1);




-- -- Insert userskeys 
-- -- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (1, '2024-03-12', null, null);
-- -- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (1, '2022-03-12', null, 1);
-- -- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (2, '2024-01-13', null, 2);
-- -- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (3, '2024-04-17', null, 3);
-- -- INSERT IGNORE INTO userskeys (userkey_id, date, user_id, key_id) VALUES (4, '2024-04-18', null, 4);
-- INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (1, '2022-03-12', 1);
-- INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (2, '2024-01-13', 2);
-- INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (3, '2024-04-17', 3);
-- INSERT IGNORE INTO userskeys (userkey_id, date, key_id) VALUES (4, '2024-04-18', 4);



-- -- Insert users 
-- -- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', 'passwordnotsecuredAdmin', '0698765432', '36 Quai des Orfèvres', '2022-01-01', null, null, null, null, '2023-10-01', 'Paris', '75001', 1);
-- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phone_number, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', '$2a$10$4BKCmFTHVs.dR0P47MocpuOp3X4OgPaI73T8kVxiKRTPLHi6yk/mC', '0698765432', '36 Quai des Orfèvres', '2022-01-01', 1, 1, null, 1, '2023-10-01', 'Paris', '75001', 1);
-- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phone_number, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (2, 'Myriam', 'Fournier', 'Fournier-Myriam', 'superbachelor@etudiants.fr', '$2a$10$leSMRP2E1fpcS9nU6ZLnke1ncNKIQmmx7eXnYSaf.kgsQlz3GCMgy', '0612345678', '1 place de la Concorde', '2023-10-01', 3, 2, 1, 1, '2023-10-01', 'Paris', '75001', 1);
-- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phone_number, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (3, 'James', 'Bond', 'Bond-James', '007@british-secret-services.uk', '$2a$10$zo.EdUjGAxBoNctBe5vsR.D8iMiHwes8BFWGazqdILcvjD4MnfHVm', '007007007', '36 Ravenstreet', '2023-10-05', 3, 3, 2, 1, '2023-10-05', 'London', '25099', 2);
-- INSERT IGNORE INTO users (user_id, firstname, lastname, username, email, password, phone_number, address, creation_date, role_id, userkey_id, usersel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (4, 'Oyee', 'Empl', 'Empl-Oyee', 'employee@joparis2024.fr', '$2a$10$rfXZfQf99jbNn48qxOl3X.TnTXurA1zDmr1HFc/9QX3zomNggrr62', '0606060606', '12 rue des Lumières', '2024-03-05', 2, 4, 3, 2, '2024-03-05', 'Paris', '75001', 1);





-- -- Insert carts 
-- -- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id, usersel_id) VALUES (1, '2024-03-12', 1257.30, null, null);
-- -- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id, usersel_id) VALUES (1, '2024-03-12', 1257.30, 3, 1);
-- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id) VALUES (1, '2024-03-12', 1257.30, 3);
-- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id) VALUES (2, '2024-03-13', 328.90, 2);
-- INSERT IGNORE INTO carts (cart_id, date, total_amount, user_id) VALUES (3, '2024-03-20', 726.20, 4);

-- -- Insert saleskeys 
-- -- INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (1, '2024-03-12', null);
-- INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (1, '2024-03-12', 3);
-- INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (2, '2024-03-13', 2);
-- INSERT IGNORE INTO saleskeys (salekey_id, date, key_id) VALUES (3, '2024-03-20', 4);

-- -- Insert sales 
-- -- INSERT IGNORE INTO sales (sale_id, date, user_id, cart_id, salekey_id) VALUES (1, '2024-03-12', null, null, null);
-- -- INSERT IGNORE INTO sales (sale_id, date, user_id, cart_id, salekey_id) VALUES (1, '2024-03-12', 3, 1, 1);
-- INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (1, '2024-03-12', 1, 1);
-- INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (2, '2024-03-13', 2, 2);
-- INSERT IGNORE INTO sales (sale_id, date, cart_id, salekey_id) VALUES (3, '2024-03-20', 3, 3);


-- -- Insert tickets 
-- -- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, userkey_id, salekey_id, sale_id, cart_id, user_id, usersel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', null, null, null, null, null, null);
-- -- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, userkey_id, salekey_id, sale_id, cart_id, user_id, usersel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 3, 1, 1, 1, 3, 1);
-- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, sale_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 1);
-- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, sale_id) VALUES (2, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 2);
-- INSERT IGNORE INTO tickets (ticket_id, date, key_assembly, file_name, sale_id) VALUES (3, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 3);

-- -- Insert controls 
-- -- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, null, null, null);
-- -- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', true, 1, 3, 1);
-- -- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id, userkey_id, salekey_id) VALUES (2, '2024-03-15', '0jm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, 2, 2, 1);
-- INSERT IGNORE INTO controls (control_id, date, scancode, is_ticket_valid, ticket_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', true, 1);




