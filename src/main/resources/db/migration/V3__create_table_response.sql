DROP TABLE IF EXISTS RESPONSE;

CREATE TABLE RESPONSE(
ID SERIAL PRIMARY KEY,
XID_QUESTION INT NOT NULL,
XID_PERSON INT NOT NULL,
RESPONSE VARCHAR NOT NULL,
COMMENTS VARCHAR NULL,
CONSTRAINT XID_QUESTION FOREIGN KEY (XID_QUESTION) REFERENCES QUESTION(ID),
CONSTRAINT XID_PERSON FOREIGN KEY (XID_PERSON) REFERENCES PERSON(ID),
CONSTRAINT UNIQUE_PERSON_QUESTION_RESPONSE UNIQUE ( XID_PERSON, XID_QUESTION, RESPONSE)
);

GRANT SELECT, INSERT ON RESPONSE TO ${user};

