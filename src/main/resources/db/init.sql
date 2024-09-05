create table ISOP_COMMON_CLIENT_SESSION
(
    ID           int8         NOT NULL,
    TICKET_ID    VARCHAR(255) not null,
    CLIENT_ID    VARCHAR(255),
    PAGE_SIZE    int8,
    ROW_COUNT    int8,
    OPERATION_ID VARCHAR(255),
    CREATED      TIMESTAMP(6) not null,
    START_ROW    int8,
    END_ROW      int8,
    MES_SEQ_NUM  int8,
    IS_LAST_MSG  VARCHAR(1),
    IS_NEW       VARCHAR(1),
    IS_CLOSED    VARCHAR(1),
    CUSTOM_DATA  varchar(10485760),
    MSG_IN_GROUP int8,
    CONSTRAINT iccs_pkey PRIMARY KEY (ID)
);