INSERT INTO resource(res, role, user) VALUES ('A', 'READ', 'vasya');
INSERT INTO resource(res, role, user) VALUES ('A.B.C', 'WRITE', 'vasya');
INSERT INTO resource(res, role, user) VALUES('A.B', 'EXECUTE', 'admin');
INSERT INTO resource(res, role, user) VALUES ('A', 'READ', 'admin');
INSERT INTO resource(res, role, user) VALUES('A.B', 'WRITE', 'admin');
INSERT INTO resource(res, role, user) VALUES ('A.B.C', 'READ', 'admin');
INSERT INTO resource(res, role, user) VALUES('B', 'EXECUTE', 'q');
INSERT INTO resource(res, role, user) VALUES ('A.A.A', 'EXECUTE', 'vasya');

