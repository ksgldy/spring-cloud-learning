DROP TABLE "OA"."ACCOUNT";

-- ID 主键、自增
-- USER_NAME 唯一索引
-- JOB_NUMBER 递增
CREATE TABLE "OA"."ACCOUNT" (
                              "ID" NUMBER(18, 0) NOT NULL ,
                              "REAL_NAME" VARCHAR2(128 BYTE) NOT NULL ,
                              "JOB_NUMBER" NUMBER(18,0) DEFAULT 0 NOT NULL ,
                              "CREATE_TIME" TIMESTAMP(6) NULL ,
                              PRIMARY KEY ("ID")
);

COMMENT ON table ACCOUNT IS '账户表';
COMMENT ON COLUMN "OA"."ACCOUNT"."ID" IS '主键';
COMMENT ON COLUMN "OA"."ACCOUNT"."REAL_NAME" IS '用户名';
COMMENT ON COLUMN "OA"."ACCOUNT"."JOB_NUMBER" IS '工号';
COMMENT ON COLUMN "OA"."ACCOUNT"."CREATE_TIME" IS '创建时间';


-- 创建唯一索引
CREATE UNIQUE INDEX INDEX_REAL_NAME on ACCOUNT(REAL_NAME);

-- 创建ID递增序列
CREATE SEQUENCE ACCOUNT_ID_SEQ INCREMENT BY 1 START WITH 1 NOMAXVALUE NOCYCLE NOCACHE;

-- 创建触发器实现主键自增
CREATE OR REPLACE TRIGGER ACCOUNT_TRG BEFORE INSERT ON ACCOUNT FOR EACH ROW
BEGIN
SELECT ACCOUNT_ID_SEQ.NEXTVAL INTO :NEW.ID FROM DUAL;
END;


SELECT * FROM user_sequences;
SELECT * FROM user_triggers;
