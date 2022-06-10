## sql 데이터 정의어

```sql

CREATE TABLE BOARD (
       POST_NUM             NUMBER NOT NULL,
       BOARD_NUM            NUMBER NOT NULL,
       TITLE                VARCHAR2(300) NOT NULL,
       POST_CONTENT         VARCHAR2(3000) NOT NULL,
       REGDATE              DATE NOT NULL,
       HIT                  NUMBER NOT NULL,
       PLACE_NUM            NUMBER NOT NULL,
       USER_NUM             NUMBER NOT NULL
);


ALTER TABLE BOARD
       ADD  ( PRIMARY KEY (POST_NUM) ) ;


CREATE TABLE BOARD_IMAGE (
       B_IMAGE_NUM          NUMBER NOT NULL,
       POST_NUM             NUMBER NOT NULL,
       B_IMAGE_FILE         VARCHAR2(500) NULL
);


ALTER TABLE BOARD_IMAGE
       ADD  ( PRIMARY KEY (B_IMAGE_NUM) ) ;


CREATE TABLE DOG (
       USER_NUM             NUMBER NOT NULL,
       DOG_NUM              NUMBER NOT NULL,
       DOG_NAME             VARCHAR2(20) NOT NULL,
       DOG_AGE              NUMBER NOT NULL,
       DOG_GENDER           NUMBER NOT NULL,
       DOG_DESEXED          NUMBER DEFAULT 0 NOT NULL,
       DOG_WEIGHT           NUMBER NOT NULL
);


ALTER TABLE DOG
       ADD  ( PRIMARY KEY (DOG_NUM) ) ;


CREATE TABLE PAYMENT (
       P_NUM                NUMBER NOT NULL,
       R_NUM                NUMBER NOT NULL,
       PAYMENT_DATE         DATE NOT NULL,
       USER_NUM             NUMBER NULL
);


ALTER TABLE PAYMENT
       ADD  ( PRIMARY KEY (P_NUM) ) ;


CREATE TABLE PLACE (
       REGION_NUM           NUMBER NOT NULL,
       PTYPE_NUM            NUMBER NOT NULL,
       PLACE_NUM            NUMBER NOT NULL,
       PLACE_NAME           VARCHAR2(100) NOT NULL,
       PLACE_EXPLAIN        VARCHAR2(3000) NULL,
       PLACE_ADDR           VARCHAR2(500) NOT NULL,
       PLACE_SITE           VARCHAR2(70) NOT NULL,
       PLACE_WEIGHT         NUMBER DEFAULT 0 NOT NULL,
       PLACE_TEL            VARCHAR2(15) NOT NULL,
       PLACE_PARK           NUMBER DEFAULT 0 NULL,
       PLACE_SPA            NUMBER DEFAULT 0 NULL,
       PLACE_MEAL           NUMBER DEFAULT 0 NULL,
       PARKING_FEE          NUMBER NULL,
       SPA_FEE              NUMBER NULL,
       MEAL_FEE             NUMBER NULL,
       PLACE_THUMBNAIL      VARCHAR2(500) DEFAULT 'default_image.jpg' NOT NULL,
       PLACE_DETAIL         VARCHAR2(3000) NULL,
       PLACE_HIT            NUMBER DEFAULT 0 NOT NULL,
       PLACE_LAT                 VARCHAR2(500) NOT NULL,
       PLACE_LNG                VARCHAR2(500) NOT NULL
);


ALTER TABLE PLACE
       ADD  ( PRIMARY KEY (PLACE_NUM) ) ;


CREATE TABLE PLACE_IMAGE (
       IMAGE_NUM            NUMBER NOT NULL,
       PLACE_NUM            NUMBER NOT NULL,
       PLACE_FILE           VARCHAR2(500) NULL
);


ALTER TABLE PLACE_IMAGE
       ADD  ( PRIMARY KEY (IMAGE_NUM) ) ;


CREATE TABLE PLAN (
       USER_NUM             NUMBER NOT NULL,
       PLAN_NUM             NUMBER  NOT NULL,
       PLAN_NAME            VARCHAR2(20) DEFAULT '여행계획1' NOT NULL,
       PLAN_DATE            DATE NOT NULL,
       FLOW_NUM             NUMBER NOT NULL,
       FLOW_NAME            VARCHAR2(30) NOT NULL,
       PLACE_ADDR           VARCHAR2(500) NULL,
       PLAN_ID              NUMBER NOT NULL,
       PLACE_NUM            NUMBER NULL
);


ALTER TABLE PLAN
       ADD  ( PRIMARY KEY (PLAN_NUM) ) ;


CREATE TABLE REPLY (
       REPLY_NUM            NUMBER NOT NULL,
       POST_NUM             NUMBER NOT NULL,
       REF_REPLY_NUM        NUMBER NULL,
       USER_NUM             NUMBER NOT NULL,
       REF_USER_NUM         NUMBER NULL,
       REPLY_GROUP          NUMBER NOT NULL,
       REPLY_LEVEL          NUMBER NOT NULL,
       REPLY_STEP           NUMBER NOT NULL,
       REPLY_CONTENT        VARCHAR2(3000) NOT NULL
);


ALTER TABLE REPLY
       ADD  ( PRIMARY KEY (REPLY_NUM) ) ;


CREATE TABLE ROOM (
       ROOM_NUM             NUMBER NOT NULL,
       ROOM_NAME            VARCHAR2(20) NOT NULL,
       ROOM_PRICE           NUMBER NOT NULL,
       PLACE_NUM            NUMBER NOT NULL
);


ALTER TABLE ROOM
       ADD  ( PRIMARY KEY (ROOM_NUM) ) ;


CREATE TABLE USER_DIBS (
       DIB_NUM              NUMBER NOT NULL,
       USER_NUM             NUMBER NOT NULL,
       PLACE_NUM            NUMBER NOT NULL
);


ALTER TABLE USER_DIBS
       ADD  ( PRIMARY KEY (DIB_NUM) ) ;


CREATE TABLE USER_INFO (
       USER_NUM             NUMBER NOT NULL,
       USER_ID              VARCHAR2(20) NOT NULL,
       USER_PWD             VARCHAR2(20) NOT NULL,
       USER_NICK            VARCHAR2(20) NOT NULL,
       USER_PHONE           VARCHAR2(15) NOT NULL,
       USER_FILE            VARCHAR2(500) DEFAULT 'default_image.jpg' NOT NULL
);


ALTER TABLE USER_INFO
       ADD  ( PRIMARY KEY (USER_NUM) ) ;


CREATE TABLE USER_RESERVATION (
       R_NUM                NUMBER NOT NULL,
       CHECKIN_DATE         DATE NOT NULL,
       CHECKOUT_DATE        DATE NOT NULL,
       PEOPLE_CNT           NUMBER NOT NULL,
       S_DOG_CNT            NUMBER NOT NULL,
       M_DOG_CNT            NUMBER NOT NULL,
       L_DOG_CNT            NUMBER NOT NULL,
       R_PARK               NUMBER NOT NULL,
       R_SPA                NUMBER NOT NULL,
       R_MEAL               NUMBER NOT NULL,
       R_PRICE              NUMBER NOT NULL,
       USER_NUM             NUMBER NOT NULL,
       ROOM_NUM             NUMBER NOT NULL,
       PLACE_NUM            NUMBER NULL,
       PRIMARY KEY(R_NUM,USER_NUM)
);



ALTER TABLE BOARD
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE
                             ON DELETE SET NULL ) ;


ALTER TABLE BOARD
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO ) ;


ALTER TABLE BOARD_IMAGE
       ADD  ( FOREIGN KEY (POST_NUM)
                             REFERENCES BOARD
                             ON DELETE SET NULL ) ;


ALTER TABLE DOG
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO
                             ON DELETE CASCADE ) ;


ALTER TABLE PAYMENT
       ADD  ( FOREIGN KEY (USER_NUM, R_NUM)
                             REFERENCES USER_RESERVATION
                             ON DELETE CASCADE ) ;


ALTER TABLE PLACE_IMAGE
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE ) ;


ALTER TABLE PLAN
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE
                             ON DELETE SET NULL ) ;


ALTER TABLE PLAN
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO
                             ON DELETE CASCADE ) ;


ALTER TABLE REPLY
       ADD  ( FOREIGN KEY (POST_NUM)
                             REFERENCES BOARD ) ;


ALTER TABLE REPLY
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO ) ;


ALTER TABLE ROOM
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE ) ;


ALTER TABLE USER_DIBS
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE ) ;


ALTER TABLE USER_DIBS
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO
                             ON DELETE CASCADE ) ;


ALTER TABLE USER_RESERVATION
       ADD  ( FOREIGN KEY (PLACE_NUM)
                             REFERENCES PLACE
                             ON DELETE SET NULL ) ;


ALTER TABLE USER_RESERVATION
       ADD  ( FOREIGN KEY (ROOM_NUM)
                             REFERENCES ROOM ) ;


ALTER TABLE USER_RESERVATION
       ADD  ( FOREIGN KEY (USER_NUM)
                             REFERENCES USER_INFO
                             ON DELETE CASCADE ) ;
```
                        
