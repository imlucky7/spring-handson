CREATE TABLE "ZGROUP" 
   (	"GROUP_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"GROUP_NAME" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(20 BYTE), 
	 CONSTRAINT "ZGROUP_PK" PRIMARY KEY ("GROUP_ID")
   );
   
CREATE TABLE "ZUSER" 
   (	"USER_ID" VARCHAR2(20 BYTE), 
	"USER_NAME" VARCHAR2(20 BYTE), 
	"ADDRESS" VARCHAR2(20 BYTE), 
	"MOBILE_NO" VARCHAR2(10 BYTE), 
	"GRP_ID" VARCHAR2(20 BYTE), 
	 CONSTRAINT "ZUSER_PK" PRIMARY KEY ("USER_ID")
   );
   
CREATE TABLE "ZDOCUMENT" 
   (	"DOCUMENT_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"TITLE" VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	"CREATE_DATE" DATE NOT NULL ENABLE, 
	"CREATE_BY" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "ZDOCUMENT_PK" PRIMARY KEY ("DOCUMENT_ID")
   );
   
CREATE TABLE "Z_DOCUMENT_GROUP" 
   (	"DOC_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"GRP_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "Z_DOCUMENT_GROUP_FK1" FOREIGN KEY ("DOC_ID")
	  REFERENCES "ZDOCUMENT" ("DOCUMENT_ID") ENABLE, 
	 CONSTRAINT "Z_DOCUMENT_GROUP_FK2" FOREIGN KEY ("GRP_ID")
	  REFERENCES "ZGROUP" ("GROUP_ID") ENABLE
   );