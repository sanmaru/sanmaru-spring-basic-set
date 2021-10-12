CREATE TABLE "SANMARU"."USER_HISTORY"
(	"ID" NUMBER(19,0) NOT NULL ENABLE,
     "USERNAME" VARCHAR2(255 CHAR),
     "ACCESSIP" VARCHAR2(255 CHAR),
     "EVENT_NAME" VARCHAR2(255 CHAR),
     "EVENT_TIME" NUMBER(19,0),
     PRIMARY KEY ("ID")
)
;

CREATE SEQUENCE  "SANMARU"."USER_HISTORY_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;

