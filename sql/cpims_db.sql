/*
 Navicat Premium Data Transfer

 Source Server         : ali-mssql-server
 Source Server Type    : SQL Server
 Source Server Version : 14003045
 Source Host           : 120.79.54.63:1433
 Source Catalog        : cpcpims_db
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003045
 File Encoding         : 65001

 Date: 15/12/2018 19:09:56
*/


-- ----------------------------
-- Table structure for cpims_customer
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_customer]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_customer]
GO

CREATE TABLE [dbo].[cpims_customer] (
  [CUSTOMER_ID] int identity(1, 1) NOT NULL,
  [CUSTOMER_NAME] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_TEL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_EMAIL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_ADDRESS] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_customer] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_customer
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_customer] ON
INSERT INTO [dbo].[cpims_customer](CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PERSON, CUSTOMER_TEL, CUSTOMER_EMAIL, CUSTOMER_ADDRESS)  
  VALUES (N'1214', N'醴陵荣旗瓷业有限公司', N'陈娟', N'17716786888', N'23369888@136.com', N'中国 湖南 醴陵市 嘉树乡玉茶村柏树组')
GO

INSERT INTO [dbo].[cpims_customer](CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PERSON, CUSTOMER_TEL, CUSTOMER_EMAIL, CUSTOMER_ADDRESS)
  VALUES (N'1215', N'深圳市松林达电子有限公司', N'刘明', N'85263335-820', N'85264958@126.com', N'中国 广东 深圳市宝安区 深圳市宝安区福永社区桥头村桥塘路育')
GO
SET IDENTITY_INSERT [dbo].[cpims_customer] OFF


-- ----------------------------
-- Table structure for cpims_parts
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_parts]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_parts]
GO

CREATE TABLE [dbo].[cpims_parts] (
  [PARTS_ID] int identity(1, 1)  NOT NULL,
  [PARTS_NAME] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PARTS_TYPE] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [PARTS_SIZE] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [PARTS_VALUE] float(53)  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_parts] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_parts
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_parts] ON
INSERT INTO [dbo].[cpims_parts](PARTS_ID, PARTS_NAME, PARTS_TYPE, PARTS_SIZE, PARTS_VALUE)  
  VALUES (N'103', N'五孔插座西门子墙壁开关', N'电器', N'86*86', N'1.85')
GO

INSERT INTO [dbo].[cpims_parts](PARTS_ID, PARTS_NAME, PARTS_TYPE, PARTS_SIZE, PARTS_VALUE)
  VALUES (N'104', N'陶瓷马克杯', N'陶瓷', N'9*9*11', N'3.5')
GO

INSERT INTO [dbo].[cpims_parts](PARTS_ID, PARTS_NAME, PARTS_TYPE, PARTS_SIZE, PARTS_VALUE)
  VALUES (N'105', N'精酿苹果醋', N'饮料', N'312ml', N'300')
GO

INSERT INTO [dbo].[cpims_parts](PARTS_ID, PARTS_NAME, PARTS_TYPE, PARTS_SIZE, PARTS_VALUE)
  VALUES (N'106', N'小米8', N'手机', N'100', N'2499')
GO
SET IDENTITY_INSERT [dbo].[cpims_parts] OFF


-- ----------------------------
-- Table structure for cpims_record_in
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_in]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_in]
GO

CREATE TABLE [dbo].[cpims_record_in] (
  [RECORD_ID] int identity(1, 1)  NOT NULL,
  [RECORD_SUPPLIERID] int  NOT NULL,
  [RECORD_PARTSID] int  NOT NULL,
  [RECORD_NUMBER] int  NOT NULL,
  [RECORD_TIME] datetime2(7)  NOT NULL,
  [RECORD_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [RECORD_REPOSITORYID] int  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_record_in] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_record_in
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_record_in] ON
INSERT INTO [dbo].[cpims_record_in](RECORD_ID, RECORD_SUPPLIERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID)  
  VALUES (N'15', N'1015', N'105', N'1000', N'2016-12-31 00:00:00.0000000', N'admin', N'1004')
GO

INSERT INTO [dbo].[cpims_record_in](RECORD_ID, RECORD_SUPPLIERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID)
  VALUES (N'16', N'1015', N'105', N'200', N'2017-01-02 00:00:00.0000000', N'admin', N'1004')
GO
SET IDENTITY_INSERT [dbo].[cpims_record_in] OFF

-- ----------------------------
-- Table structure for cpims_record_out
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_out]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_out]
GO

CREATE TABLE [dbo].[cpims_record_out] (
  [RECORD_ID] int identity(1, 1)  NOT NULL,
  [RECORD_CUSTOMERID] int  NOT NULL,
  [RECORD_PARTSID] int  NOT NULL,
  [RECORD_NUMBER] int  NOT NULL,
  [RECORD_TIME] datetime2(7)  NOT NULL,
  [RECORD_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [RECORD_REPOSITORYID] int  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_record_out] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_record_out
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_record_out] ON
INSERT INTO [dbo].[cpims_record_out](RECORD_ID, RECORD_CUSTOMERID, RECORD_PARTSID, RECORD_NUMBER, RECORD_TIME, RECORD_PERSON, RECORD_REPOSITORYID)
  VALUES (N'7', N'1214', N'104', N'750', N'2016-12-31 00:00:00.0000000', N'admin', N'1003')
GO
SET IDENTITY_INSERT [dbo].[cpims_record_out] OFF

-- ----------------------------
-- Table structure for cpims_record_storage
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_storage]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_storage]
GO

CREATE TABLE [dbo].[cpims_record_storage] (
  [RECORD_PARTSID] int identity(1, 1)  NOT NULL,
  [RECORD_REPOSITORY] int  NOT NULL,
  [RECORD_NUMBER] int  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_record_storage] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_record_storage
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_record_storage] ON
INSERT INTO [dbo].[cpims_record_storage](RECORD_PARTSID, RECORD_REPOSITORY, RECORD_NUMBER)
  VALUES (N'103', N'1005', N'10000')
GO

INSERT INTO [dbo].[cpims_record_storage](RECORD_PARTSID, RECORD_REPOSITORY, RECORD_NUMBER)
  VALUES (N'104', N'1003', N'1750')
GO

INSERT INTO [dbo].[cpims_record_storage](RECORD_PARTSID, RECORD_REPOSITORY, RECORD_NUMBER)
  VALUES (N'105', N'1004', N'2000')
GO
SET IDENTITY_INSERT [dbo].[cpims_record_storage] OFF


-- ----------------------------
-- Table structure for cpims_repo_admin
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_repo_admin]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_repo_admin]
GO

CREATE TABLE [dbo].[cpims_repo_admin] (
  [REPO_ADMIN_ID] int identity(1, 1)  NOT NULL,
  [REPO_ADMIN_NAME] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_ADMIN_SEX] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_ADMIN_TEL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_ADMIN_ADDRESS] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_ADMIN_BIRTH] datetime2(7)  NOT NULL,
  [REPO_ADMIN_REPOID] int  NULL
)
GO

ALTER TABLE [dbo].[cpims_repo_admin] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_repo_admin
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_repo_admin] ON
INSERT INTO [dbo].[cpims_repo_admin](REPO_ADMIN_ID, REPO_ADMIN_NAME, REPO_ADMIN_SEX, REPO_ADMIN_TEL, REPO_ADMIN_ADDRESS, REPO_ADMIN_BIRTH, REPO_ADMIN_REPOID)
  VALUES (N'1018', N'王皓', N'女', N'12345874526', N'中国佛山', N'2016-12-09 00:00:00.0000000', N'1004')
GO

INSERT INTO [dbo].[cpims_repo_admin](REPO_ADMIN_ID, REPO_ADMIN_NAME, REPO_ADMIN_SEX, REPO_ADMIN_TEL, REPO_ADMIN_ADDRESS, REPO_ADMIN_BIRTH, REPO_ADMIN_REPOID)
  VALUES (N'1019', N'李富荣', N'男', N'1234', N'广州', N'2016-12-07 00:00:00.0000000', N'1003')
GO
SET IDENTITY_INSERT [dbo].[cpims_repo_admin] OFF


-- ----------------------------
-- Table structure for cpims_respository
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_respository]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_respository]
GO

CREATE TABLE [dbo].[cpims_respository] (
  [REPO_ID] int identity(1, 1)  NOT NULL,
  [REPO_ADDRESS] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_STATUS] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_AREA] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_DESC] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[cpims_respository] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_respository
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_respository] ON
INSERT INTO [dbo].[cpims_respository](REPO_ID, REPO_ADDRESS, REPO_STATUS, REPO_AREA, REPO_DESC) 
 VALUES (N'1003', N'北京顺义南彩工业园区彩祥西路9号', N'可用', N'11000㎡', N'提供服务完整')
GO

INSERT INTO [dbo].[cpims_respository](REPO_ID, REPO_ADDRESS, REPO_STATUS, REPO_AREA, REPO_DESC) 
  VALUES (N'1004', N'广州白云石井石潭路大基围工业区', N'可用', N'1000㎡', N'物流极为便利')
GO

INSERT INTO [dbo].[cpims_respository](REPO_ID, REPO_ADDRESS, REPO_STATUS, REPO_AREA, REPO_DESC) 
  VALUES (N'1005', N' 香港北区文锦渡路（红桥新村）', N'可用', N'5000.00㎡', NULL)
GO
SET IDENTITY_INSERT [dbo].[cpims_respository] OFF

-- ----------------------------
-- Table structure for cpims_supplier
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_supplier]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_supplier]
GO

CREATE TABLE [dbo].[cpims_supplier] (
  [SUPPLIER_ID] int identity(1, 1)  NOT NULL,
  [SUPPLIER_NAME] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_TEL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_EMAIL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_ADDRESS] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_supplier] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_supplier
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_supplier] ON
INSERT INTO [dbo].[cpims_supplier](SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_PERSON, SUPPLIER_TEL, SUPPLIER_EMAIL, SUPPLIER_ADDRESS)
  VALUES (N'1013', N'浙江奇同电器有限公司', N'王泽伟', N'13777771126', N'86827868@126.com', N'中国 浙江 温州市龙湾区 龙湾区永强大道1648号')
GO

INSERT INTO [dbo].[cpims_supplier](SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_PERSON, SUPPLIER_TEL, SUPPLIER_EMAIL, SUPPLIER_ADDRESS)
  VALUES (N'1014', N'醴陵春天陶瓷实业有限公司', N'温仙容', N'13974167256', N'23267999@126.com', N'中国 湖南 醴陵市 东正街15号')
GO

INSERT INTO [dbo].[cpims_supplier](SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_PERSON, SUPPLIER_TEL, SUPPLIER_EMAIL, SUPPLIER_ADDRESS)
  VALUES (N'1015', N'洛阳嘉吉利饮品有限公司', N'郑绮云', N'26391678', N'22390898@qq.com', N'中国 广东 佛山市顺德区 北滘镇怡和路2号怡和中心14楼')
GO

INSERT INTO [dbo].[cpims_supplier](SUPPLIER_ID, SUPPLIER_NAME, SUPPLIER_PERSON, SUPPLIER_TEL, SUPPLIER_EMAIL, SUPPLIER_ADDRESS)
  VALUES (N'1016', N'小米', N'雷军', N'111', N'leijun@xioami.com', N'小米公司')
GO
SET IDENTITY_INSERT [dbo].[cpims_supplier] OFF


-- ----------------------------
-- Table structure for cpims_user
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_user]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_user]
GO

CREATE TABLE [dbo].[cpims_user] (
  [USER_ID] int identity(1, 1)  NOT NULL,
  [USER_USERNAME] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [USER_PASSWORD] nvarchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_user] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_user
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_user] ON
INSERT INTO [dbo].[cpims_user](USER_ID, USER_USERNAME, USER_PASSWORD)
  VALUES (N'1001', N'admin', N'6f5379e73c1a9eac6163ab8eaec7e41c')
GO

INSERT INTO [dbo].[cpims_user](USER_ID, USER_USERNAME, USER_PASSWORD)
  VALUES (N'1018', N'王皓', N'50f202f4862360e55635b0a9616ded13')
GO

INSERT INTO [dbo].[cpims_user](USER_ID, USER_USERNAME, USER_PASSWORD)
  VALUES (N'1019', N'李富荣', N'c4b3af5a5ab3e3d5aac4c5a5436201b8')
GO
SET IDENTITY_INSERT [dbo].[cpims_user] OFF



-- ----------------------------
-- Primary Key structure for table cpims_customer
-- ----------------------------
ALTER TABLE [dbo].[cpims_customer] ADD CONSTRAINT [PK__cpims_cust__1CE12D379D67ED13] PRIMARY KEY CLUSTERED ([CUSTOMER_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cpims_parts
-- ----------------------------
ALTER TABLE [dbo].[cpims_parts] ADD CONSTRAINT [PK__cpims_parts__C1833A1D838B18B3] PRIMARY KEY CLUSTERED ([PARTS_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO



-- ----------------------------
-- Indexes structure for table cpims_record_in
-- ----------------------------
CREATE NONCLUSTERED INDEX [RECORD_SUPPLIERID]
ON [dbo].[cpims_record_in] (
  [RECORD_SUPPLIERID] ASC
)
GO

CREATE NONCLUSTERED INDEX [RECORD_PARTSID]
ON [dbo].[cpims_record_in] (
  [RECORD_PARTSID] ASC
)
GO

CREATE NONCLUSTERED INDEX [RECORD_REPOSITORYID]
ON [dbo].[cpims_record_in] (
  [RECORD_REPOSITORYID] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table cpims_record_in
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_in] ADD CONSTRAINT [PK__cpims_reco__E2DA602FA45E9A64] PRIMARY KEY CLUSTERED ([RECORD_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table cpims_record_out
-- ----------------------------
CREATE NONCLUSTERED INDEX [RECORD_CUSTOMERID]
ON [dbo].[cpims_record_out] (
  [RECORD_CUSTOMERID] ASC
)
GO

CREATE NONCLUSTERED INDEX [RECORD_PARTSID]
ON [dbo].[cpims_record_out] (
  [RECORD_PARTSID] ASC
)
GO

CREATE NONCLUSTERED INDEX [RECORD_REPOSITORYID]
ON [dbo].[cpims_record_out] (
  [RECORD_REPOSITORYID] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table cpims_record_out
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_out] ADD CONSTRAINT [PK__cpims_reco__E2DA602FE9F6AFF7] PRIMARY KEY CLUSTERED ([RECORD_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table cpims_record_storage
-- ----------------------------
CREATE NONCLUSTERED INDEX [RECORD_REPOSITORY]
ON [dbo].[cpims_record_storage] (
  [RECORD_REPOSITORY] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table cpims_record_storage
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_storage] ADD CONSTRAINT [PK__cpims_reco__DF20374E7FD46FB5] PRIMARY KEY CLUSTERED ([RECORD_PARTSID], [RECORD_REPOSITORY])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Indexes structure for table cpims_repo_admin
-- ----------------------------
CREATE NONCLUSTERED INDEX [REPO_ADMIN_REPOID]
ON [dbo].[cpims_repo_admin] (
  [REPO_ADMIN_REPOID] ASC
)
GO


-- ----------------------------
-- Primary Key structure for table cpims_repo_admin
-- ----------------------------
ALTER TABLE [dbo].[cpims_repo_admin] ADD CONSTRAINT [PK__cpims_repo__1FA3996160AC8139] PRIMARY KEY CLUSTERED ([REPO_ADMIN_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cpims_respository
-- ----------------------------
ALTER TABLE [dbo].[cpims_respository] ADD CONSTRAINT [PK__cpims_resp__672DC31C4B88452E] PRIMARY KEY CLUSTERED ([REPO_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cpims_supplier
-- ----------------------------
ALTER TABLE [dbo].[cpims_supplier] ADD CONSTRAINT [PK__cpims_supp__88565CC14BBA5CD7] PRIMARY KEY CLUSTERED ([SUPPLIER_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table cpims_user
-- ----------------------------
ALTER TABLE [dbo].[cpims_user] ADD CONSTRAINT [PK__cpims_user__F3BEEBFFB7B5DBF6] PRIMARY KEY CLUSTERED ([USER_ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table cpims_record_in
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_in] ADD CONSTRAINT [cpims_record_in_ibfk_1] FOREIGN KEY ([RECORD_SUPPLIERID]) REFERENCES [dbo].[cpims_supplier] ([SUPPLIER_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[cpims_record_in] ADD CONSTRAINT [cpims_record_in_ibfk_2] FOREIGN KEY ([RECORD_PARTSID]) REFERENCES [dbo].[cpims_parts] ([PARTS_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[cpims_record_in] ADD CONSTRAINT [cpims_record_in_ibfk_3] FOREIGN KEY ([RECORD_REPOSITORYID]) REFERENCES [dbo].[cpims_respository] ([REPO_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table cpims_record_out
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_out] ADD CONSTRAINT [cpims_record_out_ibfk_1] FOREIGN KEY ([RECORD_CUSTOMERID]) REFERENCES [dbo].[cpims_customer] ([CUSTOMER_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[cpims_record_out] ADD CONSTRAINT [cpims_record_out_ibfk_2] FOREIGN KEY ([RECORD_PARTSID]) REFERENCES [dbo].[cpims_parts] ([PARTS_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[cpims_record_out] ADD CONSTRAINT [cpims_record_out_ibfk_3] FOREIGN KEY ([RECORD_REPOSITORYID]) REFERENCES [dbo].[cpims_respository] ([REPO_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table cpims_record_storage
-- ----------------------------
ALTER TABLE [dbo].[cpims_record_storage] ADD CONSTRAINT [cpims_record_storage_ibfk_1] FOREIGN KEY ([RECORD_PARTSID]) REFERENCES [dbo].[cpims_parts] ([PARTS_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[cpims_record_storage] ADD CONSTRAINT [cpims_record_storage_ibfk_2] FOREIGN KEY ([RECORD_REPOSITORY]) REFERENCES [dbo].[cpims_respository] ([REPO_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table cpims_repo_admin
-- ----------------------------
ALTER TABLE [dbo].[cpims_repo_admin] ADD CONSTRAINT [cpims_repo_admin_ibfk_1] FOREIGN KEY ([REPO_ADMIN_REPOID]) REFERENCES [dbo].[cpims_respository] ([REPO_ID]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

