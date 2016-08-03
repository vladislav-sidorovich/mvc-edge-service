INSERT INTO client (client_id, name, secret) VALUES ('mvc-edge','demo', 'secret');

INSERT INTO users (user_name, client_id) VALUES ('bob', 'mvc-edge');
INSERT INTO users (user_name, client_id) VALUES ('jim', 'mvc-edge');

