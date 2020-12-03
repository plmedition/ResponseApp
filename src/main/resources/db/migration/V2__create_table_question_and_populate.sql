--2.- Create a new table QUESTION which contains:
--ID - QUESTION
--1    What’s your name?
--2    How old are you?
--3    Where are you from?
--4    What’s your current job position?

-- DDL
DROP TABLE IF EXISTS QUESTION;

CREATE TABLE QUESTION(
  ID SERIAL PRIMARY KEY,
  QUESTION TEXT NOT NULL
);

GRANT SELECT ON QUESTION TO ${user}; -- No insert is available

-- DML
INSERT INTO QUESTION (QUESTION) VALUES ('What’s your name?');
INSERT INTO QUESTION (QUESTION) VALUES ('How old are you?');
INSERT INTO QUESTION (QUESTION) VALUES ('Where are you from?');
INSERT INTO QUESTION (QUESTION) VALUES ('What’s your current job position?');