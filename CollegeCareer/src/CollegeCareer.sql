--1. ������� ���̺�
DROP TABLE CC_GRADREQ;
CREATE TABLE CC_GRADREQ(
    GRADREQ_VERSION            NUMBER(2, 1)   CONSTRAINT GRADEREQ_VERSION_PK PRIMARY KEY
    , GRADREQ_CREDIT           NUMBER(3)      NOT NULL
    , GRADREQ_CREDIT_MAJOR     NUMBER(3)      NOT NULL
    , GRADREQ_CREDIT_LIBERAL   NUMBER(3)      NOT NULL
    , GRADREQ_TOEIC            NUMBER(3)      CONSTRAINT GRADREQ_TOEIC_CHK CHECK (GRADREQ_TOEIC BETWEEN 0 AND 990)  NOT NULL
    , GRADREQ_HSK              NUMBER(1)      CONSTRAINT GRADREQ_HSK_CHK   CHECK (GRADREQ_HSK BETWEEN 0 AND 6)  NOT NULL
    , GRADREQ_JPT              NUMBER(3)      CONSTRAINT GRADREQ_JPT_CHK   CHECK (GRADREQ_JPT BETWEEN 0 AND 990)  NOT NULL
);

INSERT INTO CC_GRADREQ VALUES (1.0, 120, 54, 66, 850, 0, 0);
INSERT INTO CC_GRADREQ VALUES (2.0, 123, 57, 66, 850, 0, 740);
INSERT INTO CC_GRADREQ VALUES (3.0, 126, 60, 66, 900, 4, 740);
INSERT INTO CC_GRADREQ VALUES (3.2, 123, 57, 66, 900, 4, 740);

COMMIT;
ROLLBACK;

--2. �л� ���̺�
DROP TABLE CC_STUDENT;
CREATE TABLE CC_STUDENT(
    STUDENT_ID                      VARCHAR2(10)    CONSTRAINT STUDENT_ID_PK PRIMARY KEY
    , GRADREQ_VERSION               NUMBER(2,1)     CONSTRAINT STUDENT_GRADVER_FK REFERENCES CC_GRADREQ(GRADREQ_VERSION)ON DELETE SET NULL
    , STUDENT_NAME                  VARCHAR2(30)    NOT NULL
    , STUDENT_TRACK                 NUMBER(2)       NOT NULL
    , STUDENT_GRADE                 NUMBER(1)       CONSTRAINT STUDENT_GRADE_CHK CHECK (STUDENT_GRADE BETWEEN 1 AND 6)  NOT NULL
    , STUDENT_GROSSCREDIT           NUMBER(3)       NOT NULL
    , STUDENT_GROSSCREDIT_MAJOR     NUMBER(3)       NOT NULL
    , STUDENT_GROSSCREDIT_LIBERAL   NUMBER(3)       NOT NULL
    , STUDENT_TOEIC                 NUMBER(3)       CONSTRAINT STUDENT_TOEIC_CHK CHECK (STUDENT_TOEIC BETWEEN 0 AND 990) NOT NULL
    , STUDENT_HSK                   NUMBER(1)       CONSTRAINT STUDENT_HSK_CHK CHECK (STUDENT_HSK BETWEEN 0 AND 6)       NOT NULL
    , STUDENT_JPT                   NUMBER(3)       CONSTRAINT STUDENT_JPT_CHK CHECK (STUDENT_JPT BETWEEN 0 AND 990)     NOT NULL
);

INSERT INTO CC_STUDENT VALUES (2011111111, 1.0, '�׽�Ʈ', 2, 2, 110, 40, 70, 574, 0, 900);
INSERT INTO CC_STUDENT VALUES (2013232152, 2.0, '�赿��', 3, 4, 130, 60, 70, 975, 0, 800);
INSERT INTO CC_STUDENT VALUES (2013232088, 2.0, '�տ���', 0, 4, 114, 48, 66, 990, 3, 500);
INSERT INTO CC_STUDENT VALUES (2016232122, 3.0, '������', 2, 2, 87,  36, 51, 800, 1, 750);
INSERT INTO CC_STUDENT VALUES (2020232037, 3.2, '������', 1, 5, 0,   0,  0,  780, 0, 0);

COMMIT;
ROLLBACK;

--3. �������� ���̺�
DROP TABLE CC_MAJOR;
CREATE TABLE CC_MAJOR(
    MAJOR_NO                   VARCHAR2(15)    CONSTRAINT MAJOR_NO_PK PRIMARY KEY
    , MAJOR_NAME               VARCHAR2(100)   NOT NULL
    , MAJOR_PROFNAME           VARCHAR2(30)    NOT NULL
    , MAJOR_CREDIT             NUMBER(1)       NOT NULL
    , MAJOR_TRACK              NUMBER(2)       NOT NULL
    , MAJOR_MINGRADE           NUMBER(1)       CONSTRAINT MAJOR_MINGRADE_CHK        CHECK (MAJOR_MINGRADE BETWEEN 1 AND 3)  NOT NULL
    , MAJOR_SCORE              NUMBER(3, 2)    DEFAULT 0 CONSTRAINT MAJOR_SCORE_CHK CHECK (MAJOR_SCORE BETWEEN 0 AND 5)     NOT NULL
);

INSERT INTO CC_MAJOR VALUES ('BIZ1001', '�濵��������', '�̺���', 3, 0, 1, 4.75);
INSERT INTO CC_MAJOR VALUES ('ACC3001', 'MANAGERIAL ACCOUNTING', 'WARREN BUFFETT', 3, 1, 3, 2.5);
INSERT INTO CC_MAJOR VALUES ('MGT2001', 'ORGANIZATIONAL BEHAVIOR', 'BRUNO MARS', 3, 2, 2, 4.98);
INSERT INTO CC_MAJOR VALUES ('FIN3001', 'FINANCIAL MANAGEMENT', 'JUNBAE BANG', 3, 3, 3, 4.0);
INSERT INTO CC_MAJOR VALUES ('MKT2001', 'MARKETING PRINCIPLE', 'SNOOP DOGG', 3, 4, 2, 5.00);
INSERT INTO CC_MAJOR VALUES ('ENT2001', 'ENTREPRENEURSHIP', 'JEFF BEZOS', 3, 5, 2, 4.12);
INSERT INTO CC_MAJOR VALUES ('ANA2001', 'BUSINESS ANALYTICS', 'OH HOON KWON', 3, 6, 2, 3.75);

COMMIT;
ROLLBACK;

--4. ������� ���̺�
DROP TABLE CC_LIBERALARTS;
CREATE TABLE CC_LIBERALARTS(
    LIBERALARTS_NO                   VARCHAR2(15)    CONSTRAINT LIBERALARTS_NO_PK PRIMARY KEY
    , LIBERALARTS_NAME               VARCHAR2(100)   NOT NULL
    , LIBERALARTS_PROFNAME           VARCHAR2(30)    NOT NULL
    , LIBERALARTS_CREDIT             NUMBER(1)       NOT NULL
    , LIBERALARTS_TRACK              NUMBER(2)       NOT NULL
    , LIBERALARTS_MINGRADE           NUMBER(1)       CONSTRAINT LIBERALARTS_MINGRADE_CHK        CHECK (LIBERALARTS_MINGRADE BETWEEN 1 AND 3)  NOT NULL
    , LIBERALARTS_SCORE              NUMBER(3, 2)    DEFAULT 0 CONSTRAINT LIBERALARTS_SCORE_CHK CHECK (LIBERALARTS_SCORE BETWEEN 0 AND 5)     NOT NULL
);

INSERT INTO CC_LIBERALARTS VALUES ('LIT1001', 'MUSIC THEORY', 'LADY GAGA', 2, 1, 1, 4.75);
INSERT INTO CC_LIBERALARTS VALUES ('HHM2001', 'WORLD HISTORY', 'SIMA QIAN', 3, 2, 2, 3.5);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1001', 'ENGLISH LV1', 'DONALD TRUMP', 2, 3, 1, 3.58);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1002', 'ENGLISH LV2', 'JOE BIDEN', 2, 3, 1, 3.78);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1003', 'ENGLISH LV3', 'BARAK OBAMA', 3, 3, 1, 4.28);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1011', 'CHINESE LV1', 'MAO ZHEDONG', 2, 3, 1, 4.38);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1012', 'CHINESE LV2', 'HU JINTAO', 2, 3, 1, 3.58);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1013', 'CHINESE LV3', 'BLIZZARD', 3, 3, 1, 2.18);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1021', 'JAPANESE LV1', 'CHAR AZNABLE', 2, 3, 1, 4.78);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1022', 'JAPANESE LV2', 'AMURO RAY', 2, 3, 1, 3.28);
INSERT INTO CC_LIBERALARTS VALUES ('LAN1023', 'JAPANESE LV3', 'YOSHIKAWA KAZUE', 3, 3, 1, 4.98);
INSERT INTO CC_LIBERALARTS VALUES ('PHI1001', 'A LIFE OF HAPPINESS AND FULFILLMENT', 'JIM CARREY', 2, 4, 1, 5.0);
INSERT INTO CC_LIBERALARTS VALUES ('SOC3001', 'CONSTITUTIONAL LAW', 'MYUNSEUNG KIM', 3, 5, 3, 4.12);
INSERT INTO CC_LIBERALARTS VALUES ('WLD2001', 'GAME THEORY', 'HENRY KISSINGER', 3, 6, 2, 4.44);
INSERT INTO CC_LIBERALARTS VALUES ('MAT2001', 'CALCULUS', 'ARITHESIA KANDINSKY', 3, 7, 2, 3.15);
INSERT INTO CC_LIBERALARTS VALUES ('SCI2001', 'ROBOTICS FOUNDATION', 'ALPHA GO', 3, 8, 2, 3.65);
INSERT INTO CC_LIBERALARTS VALUES ('BIO2001', 'A CIRCLE OF LIFE', 'SIMBA HUNTER', 2, 9, 2, 4.48);
INSERT INTO CC_LIBERALARTS VALUES ('SWE2001', 'JAVA LV1', 'LEE JONG HO', 3, 0, 2, 5.00);

COMMIT;
ROLLBACK;

--5. ������û ���̺�
DROP TABLE CC_ENROLL;
CREATE TABLE CC_ENROLL(
    ENROLL_ID              VARCHAR2(400)   CONSTRAINT ENROLL_ID_PK        PRIMARY KEY
    , STUDENT_ID           VARCHAR2(10)    CONSTRAINT ENROLL_STUDENTID_FK REFERENCES CC_STUDENT(STUDENT_ID)        ON DELETE SET NULL
    , GRADREQ_VERSION     NUMBER(2,1)     CONSTRAINT ENROLL_GRADVER_FK   REFERENCES CC_GRADREQ(GRADREQ_VERSION)   ON DELETE SET NULL
    , MAJOR_NO             VARCHAR2(15)    CONSTRAINT ENROLL_MAJORNO_FK   REFERENCES CC_MAJOR(MAJOR_NO)            ON DELETE SET NULL
    , LIBERALARTS_NO       VARCHAR2(15)    CONSTRAINT ENROLL_LIBERTNO_FK  REFERENCES CC_LIBERALARTS(LIBERALARTS_NO)ON DELETE SET NULL
);

--6. ������û ������
DROP SEQUENCE SEQ_ENROLL_SEQUENCE;
CREATE SEQUENCE SEQ_ENROLL_SEQUENCE;

INSERT INTO 
    CC_ENROLL(ENROLL_ID, STUDENT_ID, GRADREQ_VERSION, MAJOR_NO
    ) VALUES (SEQ_ENROLL_SEQUENCE.NEXTVAL, '2011111111', 1.0, 'ACC3001');

COMMIT;
ROLLBACK;
