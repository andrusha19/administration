﻿CREATE TABLE users (
name  VARCHAR (128) NOT NULL PRIMARY KEY,
password CHAR (32) NOT NULL,
email VARCHAR (128) NOT NULL,
registration_time TIMESTAMP NOT NULL,
last_edit_time TIMESTAMP NOT NULL,
role VARCHAR(128) NOT NULL);