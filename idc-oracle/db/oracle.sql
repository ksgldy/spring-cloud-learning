DROP TABLE "JSDEV"."AI_PROJECT_GROUP";
CREATE TABLE "JSDEV"."AI_PROJECT_GROUP" (
                                          "ID" NUMBER(20, 0) NOT NULL ,
                                          "COMPANY_ID" NUMBER NOT NULL ,
                                          "GROUP_NAME" VARCHAR2(128 BYTE) NOT NULL ,
                                          "RANK" NUMBER NOT NULL ,
                                          "CREATE_TIME" TIMESTAMP(6) NULL ,
                                          "UPDATE_TIME" TIMESTAMP(6)  NULL ,
                                          "CREATER" VARCHAR2(128 BYTE) NULL ,
                                          "UPDATER" VARCHAR2(128 BYTE) NULL ,
                                          PRIMARY KEY ("ID")
);

CREATE UNIQUE INDEX INDEX_GROUP_NAME on AI_PROJECT_GROUP(GROUP_NAME);

CREATE SEQUENCE AI_PROJECT_GROUP_ID_SEQ INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCYCLE NOCACHE;

CREATE OR REPLACE TRIGGER AI_PROJECT_GROUP_TRG BEFORE INSERT ON AI_PROJECT_GROUP FOR EACH ROW
BEGIN
SELECT AI_PROJECT_GROUP_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;


DROP TABLE "JSDEV"."AI_PROJECT_USER";
CREATE TABLE "JSDEV"."AI_PROJECT_USER" (
                                         "ID" NUMBER(20, 0) NOT NULL ,
                                         "COMPANY_ID" NUMBER NOT NULL ,
                                         "GROUP_ID" NUMBER(20, 0) NOT NULL ,
                                         "CUSTOMER_ID" VARCHAR2(128 BYTE) NOT NULL ,
                                         PRIMARY KEY ("ID")
);

CREATE UNIQUE INDEX INDEX_CUSTOMER_ID on AI_PROJECT_USER(CUSTOMER_ID);

CREATE SEQUENCE AI_PROJECT_USER_ID_SEQ INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCYCLE NOCACHE;

CREATE OR REPLACE TRIGGER AI_PROJECT_USER_TRG BEFORE INSERT ON AI_PROJECT_USER FOR EACH ROW
BEGIN
SELECT AI_PROJECT_USER_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;

DROP TABLE "JSDEV"."AI_RULE";
CREATE TABLE "JSDEV"."AI_RULE" (
                                 "ID" NUMBER(20, 0) NOT NULL ,
                                 "COMPANY_ID" NUMBER NOT NULL ,
                                 "GROUP_ID" NUMBER(20, 0) NOT NULL ,
                                 "RULE_NAME" VARCHAR2(128 BYTE) NOT NULL ,
                                 "STATE" NUMBER(1) DEFAULT 1  NULL ,
                                 "CONDITION" CLOB NULL ,
                                 "ACTION" CLOB NULL ,
                                 "RANK" NUMBER NOT NULL ,
                                 "CREATE_TIME" TIMESTAMP(6)  NULL ,
                                 "UPDATE_TIME" TIMESTAMP(6)  NULL ,
                                 "CREATER" VARCHAR2(128 BYTE) NULL ,
                                 "UPDATER" VARCHAR2(128 BYTE) NULL ,
                                 PRIMARY KEY ("ID")
);

CREATE UNIQUE INDEX INDEX_RULE_NAME on AI_RULE(RULE_NAME);

CREATE SEQUENCE AI_RULE_ID_SEQ INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCYCLE NOCACHE;

CREATE OR REPLACE TRIGGER AI_RULE_TRG BEFORE INSERT ON AI_RULE FOR EACH ROW
BEGIN
SELECT AI_RULE_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;