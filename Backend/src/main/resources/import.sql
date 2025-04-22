

-- Insert countries 
INSERT INTO countries (country_id, name) VALUES (1, 'France');
INSERT INTO countries (country_id, name) VALUES (2, 'United Kingdom');
INSERT INTO countries (country_id, name) VALUES (3, 'Sweden');
INSERT INTO countries (country_id, name) VALUES (4, 'Italy');
INSERT INTO countries (country_id, name) VALUES (5, 'Germany');
INSERT INTO countries (country_id, name) VALUES (6, 'Brazil');
INSERT INTO countries (country_id, name) VALUES (7, 'Marocco');
INSERT INTO countries (country_id, name) VALUES (8, 'Spain');
INSERT INTO countries (country_id, name) VALUES (9, 'Portugal');
INSERT INTO countries (country_id, name) VALUES (10, 'Belgium');
INSERT INTO countries (country_id, name) VALUES (11, 'Netherlands');
INSERT INTO countries (country_id, name) VALUES (12, 'Switzerland');
INSERT INTO countries (country_id, name) VALUES (13, 'United States');
INSERT INTO countries (country_id, name) VALUES (14, 'Canada');
INSERT INTO countries (country_id, name) VALUES (15, 'Australia');
INSERT INTO countries (country_id, name) VALUES (16, 'New Zealand');
INSERT INTO countries (country_id, name) VALUES (17, 'South Africa');
INSERT INTO countries (country_id, name) VALUES (18, 'Japan');
INSERT INTO countries (country_id, name) VALUES (19, 'China');
INSERT INTO countries (country_id, name) VALUES (20, 'India');

-- Insert challengers 
-- INSERT INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', null);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (1, 'Francois Bellando', 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (2, 'Mickael Kadoz',2);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (3, 'Emily Watson', 2);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (4, 'Tommy Johansson', 3);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (5, 'Luca Rossi', 4);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (6, 'Hans Müller', 5);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (7, 'Carlos Silva', 6);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (8, 'Fatima El Amrani', 7);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (9, 'Sofia Garcia', 8);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (10, 'Miguel Oliveira', 9);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (11, 'Emma Dupont', 10);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (12, 'Liam van der Meer', 11);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (13, 'Sophie Dubois', 12);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (14, 'Ethan Brown', 13);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (15, 'Ava Johnson', 14);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (16, 'Oliver Smith', 15);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (17, 'Charlotte Wilson', 16);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (18, 'Lucas Taylor', 17);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (19, 'Mia Anderson', 17);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (20, 'Noah Thomas', 19);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (21, 'Chi Ling', 19);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (22, 'Yuki Tanaka', 18);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (23, 'Akira Yamamoto', 18);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (24, 'Hana Suzuki', 18);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (25, 'Li Wei', 19);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (26, 'Wang Fang', 19);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (27, 'Zhang Wei', 19); 
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (27, 'Dinesh Kapoor,', 20);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (28, 'Priya Sharma', 20);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (29, 'Rajesh Patel', 20);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (30, 'Caroline Janson', 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (31, "French Men's Rugby Sevens Team", 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (32, 'Equipe de France féminine de plongeon', 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (33, "French women's diving team", 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (34, "French men's swimming team", 1);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (35, "U.S. Men's Swimming Team", 13);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (36, "Swedish men's diving team", 3);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (37, "Swedish women's diving team", 3);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (38, "Swedish women's volleyball team", 3);
 INSERT INTO challengers (challenger_id, name, country_id) VALUES (39, "French women's volleyball team", 1);



-- Insert sites 
-- INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (1, 'Stade de France', 'Stadium for the opening ceremony', 'Stade de France', 'Saint-Denis', '93216', null, 10000, 3000, 1000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (1, 'Stade de France', 'Stadium for the opening and closing ceremony, seven-players rugby, and athletism', 'Stade de France', 'Saint-Denis', '93216', 1, 10000, 3000, 1000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (2, 'The Seine river', 'Place for opening ceremony', 'The Seine river', 'Saint-Denis', '93216', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (3, 'Olympic swimming-pool', 'Place for swimming, diving and water-polo', 'Piscine Georges-Vallerey, 148 av. Gambetta', 'Saint-Denis', '93216', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (4, 'Urban Park at The Concorde', 'Place for the opening ceremony for paralympic games, basket-ball 3x3, BMX freestyle, breakdance and skateboard', 'Concorde square', 'Paris', '93000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (5, 'Stadium Pierre-Mauroy', 'Place for the opening ceremony for paralympic games', 'Stade Pierre-Mauroy', 'Lille', '59000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (6, 'Stadium of La Beaujoire', 'Place for football matches', 'Stade de la Beaujoire', 'Nantes', '44000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (7, 'Stadium Allianz Riviera of Nice ', 'Place for the football matches', 'Stade de Nice', 'Nice', '06000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (8, 'Stadium of Bordeaux', 'Place for football matches', 'Stade de Bordeaux', 'Bordeaux', '33000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (9, 'Velodrome Stadium Of Marseille', 'Place for paralympic football games', 'Stadium of Marseille', 'Marseille', '13000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (10, 'Olympic Park of Lyon', 'Place for paralympic football matches', 'Stade de Lyon', 'Lyon', '69000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (11, 'Stadium of Roland-Garros', 'Place for tennis and boxe matches', 'Roland-Garros', 'Paris', '78000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (12, 'Stadium of Bercy', 'Place for basket-ball, trampoline, rhythmic gymnastics', 'Stade de Bercy', 'Paris', '75000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (13, 'Invalid Esplanade', 'Place for the archery competition', 'Invalid Esplanade', 'Paris', '76000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (14, 'Great Palace', 'Place for the taekwondo and fencing meetings', 'Grand Palais', 'Paris', '78000', 1, 20000, 5000, 2000);
INSERT INTO sites (site_id, name, description, address, city, postal_code, country_id, nbseatsc1, nbseatsc2, nbseatsc3) VALUES (15, 'Porte de Versailles Exhibition Center', 'Place for the table-tennis, volleyball, handball and weightlifting meetings', 'Porte de Versailles Exhibition Center', 'Paris', '78000', 1, 20000, 5000, 2000);



-- Insert ceremonies 
-- INSERT INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', null);
INSERT INTO ceremonies (cerem_id, name, description, site_id) VALUES (1, 'Opening Ceremony', 'The opening ceremony of the Olympic Games', 2);
INSERT INTO ceremonies (cerem_id, name, description, site_id) VALUES (2, 'Closing Ceremony of Paralympic Games', 'The closing ceremony of the Olympic Games for paralympic games', 1);
INSERT INTO ceremonies (cerem_id, name, description, site_id) VALUES (3, 'Closing Ceremony', 'The closing ceremony of the Olympic Games', 1);
INSERT INTO ceremonies (cerem_id, name, description, site_id) VALUES (4, 'Opening Ceremony of Paralympic Games', 'The opening ceremony of the Paralympic Games', 4);


-- Insert sports 
--INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (1, 'Athletics', 'Athletics is a collection of sporting events that involves competitive running, jumping, throwing, and walking.', false, null);
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (1, 'Swimming', 'Swimming olympic games competition', false, 3);
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (2, 'Archery', 'Archery olympic games competition', true, 13);
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (3, 'Table-tennis', 'Table-tennis olympic games competition', false, 15);
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (4, 'Volleyball', 'Volleyball olympic games competition', false, 15); 
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (5, 'Handball', 'Handball olympic games competition', false, 15);
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (6, 'Taekwondo', 'Taekwondo olympic games competition', false, 14);    
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (7, 'Fencing', 'Fencing olympic games competition', true, 14);        
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (8, 'Boxing', 'Boxing olympic games competition', true, 11); 
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (9, 'Football', 'Football olympic games competition', true, 7); 
INSERT INTO sports (sport_id, name, description, is_paralymp, site_id) VALUES (9, 'BMX freestyle', 'BMX freestyle olympic games competition', true, 4);

-- Insert events
-- INSERT INTO events (event_id, date, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, '2024-07-04', 'The opening ceremony of the Olympic Games', null, null, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT INTO events (event_id, date, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (1, '2024-06-29', 'The opening ceremony of the Olympic Games', null, 1, null, null, 1590.50, 1, 9999, 1899.90, 1, 2999, 11299.55, 1, 999);
INSERT INTO events (event_id, date, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (2, '2024-07-04', 'Fist tennis men match', 3, null, 1, 4, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT INTO events (event_id, date, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (3, '2024-07-04', 'Fist voleyball women match', 4, null, 38, 39, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);
INSERT INTO events (event_id, date, description, sport_id, cerem_id, challenger1_id, challenger2_id, pricec1, nbseatssoldc1, nbseatsavailc1, pricec2, nbseatssoldc2, nbseatsavailc2, pricec3, nbseatssoldc3, nbseatsavailc3) VALUES (4, '2024-07-21', 'Closing ceremony of the Olympic Games', null, 3, null, null, 590.50, 1, 9999, 899.90, 1, 2999, 1299.55, 1, 999);



-- Insert offers 
INSERT INTO offers (offer_id, title, description, rate, nb_spectators) VALUES (1, 'Solo Offer', 'No special discount ticket pricing (default)', 0, 1);
INSERT INTO offers (offer_id, title, description, rate, nb_spectators) VALUES (2, 'Duo Offer', 'A 10% off for you 2-persons discount ticket', 10, 2);
INSERT INTO offers (offer_id, title, description, rate, nb_spectators) VALUES (3, 'Quatro Offer', 'A 20% off for you 4-persons discount ticket', 20, 4);


-- Insert userselections 
-- INSERT INTO userselections (user_sel_id, nb_persons, amount, offer_id, event_id) VALUES (1, 3, 457.25, null, null);
INSERT INTO userselections (user_sel_id, nb_persons, amount, offer_id, event_id) VALUES (1, 1, 457.25, 1, 1);
INSERT INTO userselections (user_sel_id, nb_persons, amount, offer_id, event_id) VALUES (2, 1, 851.55, 1, 1);
INSERT INTO userselections (user_sel_id, nb_persons, amount, offer_id, event_id) VALUES (3, 3, 851.55, 2, 1);
INSERT INTO userselections (user_sel_id, nb_persons, amount, offer_id, event_id) VALUES (4, 4, 851.55, 3, 1);

-- Insert roles 
INSERT INTO roles (role_id, name, description) VALUES (1, 'SUPERADMIN', 'Super administrator with ALL permissions');
INSERT INTO roles (role_id, name, description) VALUES (2, 'EMPLOYEE', 'Employee with limited permissions for managing sales, sports, offers, events and other informations');
INSERT INTO roles (role_id, name, description) VALUES (3, 'USER', 'User with restricted permissions'); 



-- Insert policies 
INSERT INTO policies (policy_id, title, description, creation_date, version) VALUES (1, 'Security Policies', 'Policies of the Olympic Games Ticketing System : every user of this application must have a secure password. In the case not, may encounter many years in jail !', '2023-10-01', 1);
INSERT INTO policies (policy_id, title, description, creation_date, version) VALUES (2, 'Security Policies', 'Policies of the Olympic Games Ticketing System (version 2) : every user of this application must have a secure password. In the case not, may encounter many years in jail !', '2024-02-23', 2);


-- Insert keysgenerations
INSERT INTO keysgenerations (key_id, key_generated) VALUES (1, 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9Gv');
INSERT INTO keysgenerations (key_id, key_generated) VALUES (2, 'FuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C');
INSERT INTO keysgenerations (key_id, key_generated) VALUES (3, '123ygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fll12345');
INSERT INTO keysgenerations (key_id, key_generated) VALUES (4, '85269478tjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M901258493681');

-- Insert userskeys
-- INSERT INTO userskeys (user_key_id, date, user_id, key_id) VALUES (1, '2024-03-12', null, null);
INSERT INTO userskeys (user_key_id, date, user_id, key_id) VALUES (1, '2022-03-12', null, 1);
INSERT INTO userskeys (user_key_id, date, user_id, key_id) VALUES (2, '2024-01-13', null, 2);
INSERT INTO userskeys (user_key_id, date, user_id, key_id) VALUES (3, '2024-04-17', null, 3);
INSERT INTO userskeys (user_key_id, date, user_id, key_id) VALUES (4, '2024-04-18', null, 4);

-- Insert users 
-- INSERT INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, user_key_id, user_sel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', 'passwordnotsecuredAdmin', '0698765432', '36 Quai des Orfèvres', '2022-01-01', null, null, null, null, '2023-10-01', 'Paris', '75001', 1);
INSERT INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, user_key_id, user_sel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (1, 'Min', 'Superad', 'Superad-Min', 'admin@joparis2024.fr', 'passwordnotsecuredAdmin', '0698765432', '36 Quai des Orfèvres', '2022-01-01', 1, 1, null, 1, '2023-10-01', 'Paris', '75001', 1);
INSERT INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, user_key_id, user_sel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (2, 'Myriam', 'Fournier', 'Fournier-Myriam', 'superbachelor@etudiants.fr', 'passwordnotsecured1', '0612345678', '1 place de la Concorde', '2023-10-01', 3, 2, 1, 1, '2023-10-01', 'Paris', '75001', 1);
INSERT INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, user_key_id, user_sel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (3, 'James', 'Bond', 'Bond-James', '007@british-secret-services.uk', 'passwordnotsecured007', '007007007', '36 Ravenstreet', '2023-10-05', 3, 3, 2, 1, '2023-10-05', 'London', '25099', 2);
INSERT INTO users (user_id, firstname, lastname, username, email, password, phonenumber, address, creation_date, role_id, user_key_id, user_sel_id, policy_id, policy_sign_date, city, postal_code, country_id) VALUES (4, 'Oyee', 'Empl', 'Empl-Oyee', 'employee@joparis2024.fr', 'passwordnotsecuredemployee', '0606060606', '12 rue des Lumières', '2024-03-05', 2, 4, 3, 2, '2024-03-05', 'Paris', '75001', 1);





-- Insert carts
-- INSERT INTO carts (cart_id, date, total_amount, user_id, user_sel_id) VALUES (1, '2024-03-12', 1257.30, null, null);
INSERT INTO carts (cart_id, date, total_amount, user_id, user_sel_id) VALUES (1, '2024-03-12', 1257.30, 3, 1);


-- Insert saleskeys 
-- INSERT INTO saleskeys (sale_key_id, date, key_id) VALUES (1, '2024-03-12', null);
INSERT INTO saleskeys (sale_key_id, date, key_id) VALUES (1, '2024-03-12', 3);

-- Insert sales
-- INSERT INTO sales (sale_id, date, user_id, cart_id, sale_key_id) VALUES (1, '2024-03-12', null, null, null);
INSERT INTO sales (sale_id, date, user_id, cart_id, sale_key_id) VALUES (1, '2024-03-12', 2, 1, 1);


-- Insert tickets 
-- INSERT INTO tickets (ticket_id, date, key_assembly, file_name, user_key_id, sale_key_id, sale_id, cart_id, user_id, user_sel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', null, null, null, null, null, null);
INSERT INTO tickets (ticket_id, date, key_assembly, file_name, user_key_id, sale_key_id, sale_id, cart_id, user_id, user_sel_id) VALUES (1, '2024-05-03', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', '6d4106d1d3da4970a54146c3f9de2976d6c0c151', 1, 1, 1, 1, 1, 1);

-- Insert controls 
-- INSERT INTO controls (control_id, date, scan_code, is_ticket_valid, ticket_id, user_key_id, sale_key_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, null, null, null);
INSERT INTO controls (control_id, date, scan_code, is_ticket_valid, ticket_id, user_key_id, sale_key_id) VALUES (1, '2024-03-12', 'Ajm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', true, 1, 1, 1);
INSERT INTO controls (control_id, date, scan_code, is_ticket_valid, ticket_id, user_key_id, sale_key_id) VALUES (2, '2024-03-15', '0jm3CVB71c42cJpTuCkDkon1v1HQxx3VVPhYrtHA9Dgci1FsV6YKiGeN9ILdhUnqnJj2sKS8p7gqFkqA3KrwvgNsXL0UTT6nKDTacXt0Ea4LiflOz1kBpBYrId5XnXoOnSHjkXb83FFS3lpSeIKFXWyo1fgKmgOmSqcvCFO5nN8uJtuA2xEJlDUstITPY617feDTg6hsBNg4PGsTtxcR3LoDRQQlMd4HwAZqyE21gTPoBklU6Kd4jhzvgsVwD9GvFuoygPMUtjH9C8NHMTmxDfFbMz2XQnQ6dK5cMzV3YHGDXRkBp2UfKSgwHRZLQV9QBePArs3I6KWijyz1qyBxAofowUA12WO1xbMIE16RnzsrP9ecUfQ3dnnkAlwGYbpXwScUdBlv3N9yezJayBsbJTZHXusu8ZMgqWzUE0BcrrgoxISk4ov5HfUmQ0IEjHQPX1oAQWXbnkEdLBh6tlXj0UNjGzQVujeFFyY7d5ZXBIwU7tMSJ05M90X4fllPWV8C', false, 2, 2, 1);





