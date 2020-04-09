/*
 Navicat Premium Data Transfer

 Source Server         : JSDEV
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : localhost:1521
 Source Schema         : JSDEV

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 08/04/2020 21:28:37
*/


-- ----------------------------
-- Table structure for AI_PROJECT_GROUP
-- ----------------------------
DROP TABLE "JSDEV"."AI_PROJECT_GROUP";
CREATE TABLE "JSDEV"."AI_PROJECT_GROUP" (
  "ID" NUMBER(20) NOT NULL ,
  "COMPANY_ID" NUMBER NOT NULL ,
  "GROUP_NAME" VARCHAR2(128 BYTE) NOT NULL ,
  "RANK" NUMBER NOT NULL ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "CREATER" VARCHAR2(128 BYTE) ,
  "UPDATER" VARCHAR2(128 BYTE) 
)
TABLESPACE "JSDEV"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of AI_PROJECT_GROUP
-- ----------------------------
INSERT INTO "JSDEV"."AI_PROJECT_GROUP" VALUES ('28', '123', 'test-group-name-1', '22', TO_TIMESTAMP('2020-04-08 21:24:09.939000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), TO_TIMESTAMP('2020-04-08 21:24:09.939000', 'SYYYY-MM-DD HH24:MI:SS:FF6'), '7', '7');
COMMIT;

-- ----------------------------
-- Table structure for AI_PROJECT_USER
-- ----------------------------
DROP TABLE "JSDEV"."AI_PROJECT_USER";
CREATE TABLE "JSDEV"."AI_PROJECT_USER" (
  "ID" NUMBER(20) NOT NULL ,
  "COMPANY_ID" NUMBER NOT NULL ,
  "GROUP_ID" NUMBER(20) NOT NULL ,
  "CUSTOMER_ID" VARCHAR2(128 BYTE) NOT NULL 
)
TABLESPACE "JSDEV"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for AI_RULE
-- ----------------------------
DROP TABLE "JSDEV"."AI_RULE";
CREATE TABLE "JSDEV"."AI_RULE" (
  "ID" NUMBER(20) NOT NULL ,
  "COMPANY_ID" NUMBER NOT NULL ,
  "GROUP_ID" NUMBER(20) NOT NULL ,
  "RULE_NAME" VARCHAR2(128 BYTE) NOT NULL ,
  "STATE" NUMBER(1) DEFAULT 1   ,
  "CONDITION" CLOB ,
  "ACTION" CLOB ,
  "RANK" NUMBER NOT NULL ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "CREATER" VARCHAR2(128 BYTE) ,
  "UPDATER" VARCHAR2(128 BYTE) 
)
TABLESPACE "JSDEV"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Sequence structure for AI_PROJECT_GROUP_ID_SEQ
-- ----------------------------
DROP SEQUENCE "JSDEV"."AI_PROJECT_GROUP_ID_SEQ";
CREATE SEQUENCE "JSDEV"."AI_PROJECT_GROUP_ID_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Sequence structure for AI_PROJECT_USER_ID_SEQ
-- ----------------------------
DROP SEQUENCE "JSDEV"."AI_PROJECT_USER_ID_SEQ";
CREATE SEQUENCE "JSDEV"."AI_PROJECT_USER_ID_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Sequence structure for AI_RULE_ID_SEQ
-- ----------------------------
DROP SEQUENCE "JSDEV"."AI_RULE_ID_SEQ";
CREATE SEQUENCE "JSDEV"."AI_RULE_ID_SEQ" MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 NOCACHE;

-- ----------------------------
-- Primary Key structure for table AI_PROJECT_GROUP
-- ----------------------------
ALTER TABLE "JSDEV"."AI_PROJECT_GROUP" ADD CONSTRAINT "SYS_C007179" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table AI_PROJECT_GROUP
-- ----------------------------
ALTER TABLE "JSDEV"."AI_PROJECT_GROUP" ADD CONSTRAINT "SYS_C007175" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_GROUP" ADD CONSTRAINT "SYS_C007176" CHECK ("COMPANY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_GROUP" ADD CONSTRAINT "SYS_C007177" CHECK ("GROUP_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_GROUP" ADD CONSTRAINT "SYS_C007178" CHECK ("RANK" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table AI_PROJECT_GROUP
-- ----------------------------
CREATE UNIQUE INDEX "JSDEV"."INDEX_GROUP_NAME"
  ON "JSDEV"."AI_PROJECT_GROUP" ("GROUP_NAME" ASC)
  LOGGING
  TABLESPACE "JSDEV"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Triggers structure for table AI_PROJECT_GROUP
-- ----------------------------
CREATE TRIGGER "JSDEV"."AI_PROJECT_GROUP_TRG" BEFORE INSERT ON "JSDEV"."AI_PROJECT_GROUP" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW 
BEGIN
    SELECT AI_PROJECT_GROUP_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

-- ----------------------------
-- Primary Key structure for table AI_PROJECT_USER
-- ----------------------------
ALTER TABLE "JSDEV"."AI_PROJECT_USER" ADD CONSTRAINT "SYS_C007163" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table AI_PROJECT_USER
-- ----------------------------
ALTER TABLE "JSDEV"."AI_PROJECT_USER" ADD CONSTRAINT "SYS_C007159" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_USER" ADD CONSTRAINT "SYS_C007160" CHECK ("COMPANY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_USER" ADD CONSTRAINT "SYS_C007161" CHECK ("GROUP_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_PROJECT_USER" ADD CONSTRAINT "SYS_C007162" CHECK ("CUSTOMER_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table AI_PROJECT_USER
-- ----------------------------
CREATE UNIQUE INDEX "JSDEV"."INDEX_CUSTOMER_ID"
  ON "JSDEV"."AI_PROJECT_USER" ("CUSTOMER_ID" ASC)
  LOGGING
  TABLESPACE "JSDEV"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Triggers structure for table AI_PROJECT_USER
-- ----------------------------
CREATE TRIGGER "JSDEV"."AI_PROJECT_USER_TRG" BEFORE INSERT ON "JSDEV"."AI_PROJECT_USER" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW 
BEGIN
    SELECT AI_PROJECT_USER_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/

-- ----------------------------
-- Primary Key structure for table AI_RULE
-- ----------------------------
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007169" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table AI_RULE
-- ----------------------------
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007164" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007165" CHECK ("COMPANY_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007166" CHECK ("GROUP_ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007167" CHECK ("RULE_NAME" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
ALTER TABLE "JSDEV"."AI_RULE" ADD CONSTRAINT "SYS_C007168" CHECK ("RANK" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table AI_RULE
-- ----------------------------
CREATE UNIQUE INDEX "JSDEV"."INDEX_RULE_NAME"
  ON "JSDEV"."AI_RULE" ("RULE_NAME" ASC)
  LOGGING
  TABLESPACE "JSDEV"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Triggers structure for table AI_RULE
-- ----------------------------
CREATE TRIGGER "JSDEV"."AI_RULE_TRG" BEFORE INSERT ON "JSDEV"."AI_RULE" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW 
BEGIN
    SELECT AI_RULE_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;
/