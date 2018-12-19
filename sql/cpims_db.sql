/*
 Navicat Premium Data Transfer

 Source Server         : ali-mssql-server
 Source Server Type    : SQL Server
 Source Server Version : 14003045
 Source Host           : 120.79.54.63:1433
 Source Catalog        : cpims_db
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 14003045
 File Encoding         : 65001

 Date: 19/12/2018 21:55:27
*/


-- ----------------------------
-- Table structure for cpims_customer
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_customer]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_customer]
GO

CREATE TABLE [dbo].[cpims_customer] (
  [CUSTOMER_ID] int  IDENTITY(1,1) NOT NULL,
  [CUSTOMER_NAME] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_TEL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_EMAIL] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [CUSTOMER_ADDRESS] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_customer] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_customer
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_customer] ON
GO

INSERT INTO [dbo].[cpims_customer] ([CUSTOMER_ID], [CUSTOMER_NAME], [CUSTOMER_PERSON], [CUSTOMER_TEL], [CUSTOMER_EMAIL], [CUSTOMER_ADDRESS]) VALUES (N'1214', N'醴陵荣旗瓷业有限公司', N'陈娟', N'17716786888', N'23369888@136.com', N'中国 湖南 醴陵市 嘉树乡玉茶村柏树组')
GO

INSERT INTO [dbo].[cpims_customer] ([CUSTOMER_ID], [CUSTOMER_NAME], [CUSTOMER_PERSON], [CUSTOMER_TEL], [CUSTOMER_EMAIL], [CUSTOMER_ADDRESS]) VALUES (N'1215', N'深圳市松林达电子有限公司', N'刘明', N'85263335-820', N'85264958@126.com', N'中国 广东 深圳市宝安区 深圳市宝安区福永社区桥头村桥塘路育')
GO

INSERT INTO [dbo].[cpims_customer] ([CUSTOMER_ID], [CUSTOMER_NAME], [CUSTOMER_PERSON], [CUSTOMER_TEL], [CUSTOMER_EMAIL], [CUSTOMER_ADDRESS]) VALUES (N'1216', N'贵阳小爱机器人科技有限公司', N'谢光莲', N'574935749', N'monica@xiaoi.com', N'贵州省贵阳市观山湖区贵州金融城')
GO

INSERT INTO [dbo].[cpims_customer] ([CUSTOMER_ID], [CUSTOMER_NAME], [CUSTOMER_PERSON], [CUSTOMER_TEL], [CUSTOMER_EMAIL], [CUSTOMER_ADDRESS]) VALUES (N'1217', N'贵阳小爱机器人科技有限公司', N'谢光莲', N'574935749', N'monica@xiaoi.com', N'贵州省贵阳市观山湖区贵州金融城')
GO

INSERT INTO [dbo].[cpims_customer] ([CUSTOMER_ID], [CUSTOMER_NAME], [CUSTOMER_PERSON], [CUSTOMER_TEL], [CUSTOMER_EMAIL], [CUSTOMER_ADDRESS]) VALUES (N'1218', N'贵阳小爱机器人科技有限公司', N'谢光莲', N'574935749', N'monica@xiaoi.com', N'贵州省贵阳市观山湖区贵州金融城')
GO

SET IDENTITY_INSERT [dbo].[cpims_customer] OFF
GO


-- ----------------------------
-- Table structure for cpims_parts
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_parts]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_parts]
GO

CREATE TABLE [dbo].[cpims_parts] (
  [PARTS_ID] int  IDENTITY(1,1) NOT NULL,
  [PARTS_NAME] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
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
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'103', N'五孔插座西门子12', N'电器', N'86*86', N'1.85')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'104', N'陶瓷马克杯', N'陶瓷', N'9*9*11', N'3.5')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'105', N'精酿苹果醋', N'饮料', N'312ml', N'300')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'107', N'Intel 酷睿i9 9900K', N'CPU', N'37.5*37.5mm', N'4999')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'108', N'Intel 酷睿i7 9700K', N'CPU', N'37.5*37.5mm', N'3499')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'109', N'华硕PRIME Z390-A', N'主板', N'30.5×24.4cm', N'1999')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'110', N'技嘉Z370 HD3', N'主板', N'30.5×22.5cm', N'1299')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'111', N'金士顿HyperX Savage 8GB DDR4 2400', N'内存', N'288pin', N'569')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'112', N'影驰GAMER 8GB DDR4 2400', N'内存', N'288pin', N'459')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'113', N'芝奇Trident Z RGB 32GB DDR4 3200', N'内存', N'288pin', N'2699')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'114', N'希捷Barracuda 1TB 7200转 64MB 单碟', N'硬盘', N'146.99*101.6*20.17mm', N'279')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'115', N'西部数据1TB 7200转 64MB SATA3 蓝盘', N'硬盘', N'26.1*147*101.6mm', N'299')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'118', N'HGST 7K1000 1TB 7200转 32MB', N'硬盘', N'100*70*9.5mm', N'399')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'119', N'三星860 EVO SATA III', N'固态硬盘', N'100×69.85×6.8mm', N'399')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'120', N'金士顿A400（240GB）', N'固态硬盘', N'100×69.9×7.0mm', N'399')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'122', N'七彩虹iGame GeForce RTX 2080 Ti', N'显卡', N'304×118×52mm', N'9999')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'123', N'NVIDIA GeForce RTX 2080Ti', N'显卡', N'269×111×40mm', N'9999')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'124', N'AOC AG273QCG', N'显示器', N'27英寸', N'5999')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'125', N'先马涡轮(飓风版黑)', N'机箱', N'465×233×517mm', N'399')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'126', N'航嘉WD600K', N'电源', N'150×86×140mm', N'399')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'127', N'Tt 零度水冷装备', N'散热器', N'282.5x129x64mm', N'2899')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'128', N'罗技G903无线鼠标', N'鼠标', N'130.3×66.5×40.4mm', N'899')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'129', N'罗技G610 Orion游戏机械键盘', N'键盘', N'443.5×153×38mm', N'500')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'130', N'森然播吧Ⅱ代', N'声卡', N'10.5mm', N'798')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'131', N'LR-Link LREC9902BF-2QSFP', N'网卡', NULL, N'10000')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'132', N'111', N'111', N'111', N'111')
GO

INSERT INTO [dbo].[cpims_parts] ([PARTS_ID], [PARTS_NAME], [PARTS_TYPE], [PARTS_SIZE], [PARTS_VALUE]) VALUES (N'133', N'222', N'222', N'222', N'222')
GO

SET IDENTITY_INSERT [dbo].[cpims_parts] OFF
GO


-- ----------------------------
-- Table structure for cpims_record_in
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_in]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_in]
GO

CREATE TABLE [dbo].[cpims_record_in] (
  [RECORD_ID] int  IDENTITY(1,1) NOT NULL,
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
GO

INSERT INTO [dbo].[cpims_record_in] ([RECORD_ID], [RECORD_SUPPLIERID], [RECORD_PARTSID], [RECORD_NUMBER], [RECORD_TIME], [RECORD_PERSON], [RECORD_REPOSITORYID]) VALUES (N'15', N'1015', N'105', N'1000', N'2016-12-31 00:00:00.0000000', N'admin', N'1004')
GO

INSERT INTO [dbo].[cpims_record_in] ([RECORD_ID], [RECORD_SUPPLIERID], [RECORD_PARTSID], [RECORD_NUMBER], [RECORD_TIME], [RECORD_PERSON], [RECORD_REPOSITORYID]) VALUES (N'16', N'1015', N'105', N'200', N'2017-01-02 00:00:00.0000000', N'admin', N'1004')
GO

SET IDENTITY_INSERT [dbo].[cpims_record_in] OFF
GO


-- ----------------------------
-- Table structure for cpims_record_out
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_out]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_out]
GO

CREATE TABLE [dbo].[cpims_record_out] (
  [RECORD_ID] int  IDENTITY(1,1) NOT NULL,
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
GO

INSERT INTO [dbo].[cpims_record_out] ([RECORD_ID], [RECORD_CUSTOMERID], [RECORD_PARTSID], [RECORD_NUMBER], [RECORD_TIME], [RECORD_PERSON], [RECORD_REPOSITORYID]) VALUES (N'7', N'1214', N'104', N'750', N'2016-12-31 00:00:00.0000000', N'admin', N'1003')
GO

SET IDENTITY_INSERT [dbo].[cpims_record_out] OFF
GO


-- ----------------------------
-- Table structure for cpims_record_storage
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_record_storage]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_record_storage]
GO

CREATE TABLE [dbo].[cpims_record_storage] (
  [RECORD_PARTSID] int NOT NULL,
  [RECORD_REPOSITORY] int  NOT NULL,
  [RECORD_NUMBER] int  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_record_storage] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_record_storage
-- ----------------------------

INSERT INTO [dbo].[cpims_record_storage] ([RECORD_PARTSID], [RECORD_REPOSITORY], [RECORD_NUMBER]) VALUES (N'103', N'1005', N'10000')
GO

INSERT INTO [dbo].[cpims_record_storage] ([RECORD_PARTSID], [RECORD_REPOSITORY], [RECORD_NUMBER]) VALUES (N'104', N'1003', N'1750')
GO

INSERT INTO [dbo].[cpims_record_storage] ([RECORD_PARTSID], [RECORD_REPOSITORY], [RECORD_NUMBER]) VALUES (N'105', N'1004', N'2000')
GO



-- ----------------------------
-- Table structure for cpims_repo_admin
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_repo_admin]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_repo_admin]
GO

CREATE TABLE [dbo].[cpims_repo_admin] (
  [REPO_ADMIN_ID] int  IDENTITY(1,1) NOT NULL,
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
GO

INSERT INTO [dbo].[cpims_repo_admin] ([REPO_ADMIN_ID], [REPO_ADMIN_NAME], [REPO_ADMIN_SEX], [REPO_ADMIN_TEL], [REPO_ADMIN_ADDRESS], [REPO_ADMIN_BIRTH], [REPO_ADMIN_REPOID]) VALUES (N'1018', N'王皓', N'女', N'12345874526', N'中国佛山', N'2016-12-09 00:00:00.0000000', N'1004')
GO

INSERT INTO [dbo].[cpims_repo_admin] ([REPO_ADMIN_ID], [REPO_ADMIN_NAME], [REPO_ADMIN_SEX], [REPO_ADMIN_TEL], [REPO_ADMIN_ADDRESS], [REPO_ADMIN_BIRTH], [REPO_ADMIN_REPOID]) VALUES (N'1019', N'李富荣', N'男', N'1234', N'广州', N'2016-12-07 00:00:00.0000000', N'1003')
GO

INSERT INTO [dbo].[cpims_repo_admin] ([REPO_ADMIN_ID], [REPO_ADMIN_NAME], [REPO_ADMIN_SEX], [REPO_ADMIN_TEL], [REPO_ADMIN_ADDRESS], [REPO_ADMIN_BIRTH], [REPO_ADMIN_REPOID]) VALUES (N'1036', N'张三', N'男', N'13708991488', N'贵州省贵阳市花溪区', N'1988-09-11 00:00:00.0000000', N'1007')
GO

SET IDENTITY_INSERT [dbo].[cpims_repo_admin] OFF
GO


-- ----------------------------
-- Table structure for cpims_respository
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_respository]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_respository]
GO

CREATE TABLE [dbo].[cpims_respository] (
  [REPO_ID] int  IDENTITY(1,1) NOT NULL,
  [REPO_ADDRESS] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_STATUS] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_AREA] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [REPO_DESC] nvarchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[cpims_respository] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_respository
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_respository] ON
GO

INSERT INTO [dbo].[cpims_respository] ([REPO_ID], [REPO_ADDRESS], [REPO_STATUS], [REPO_AREA], [REPO_DESC]) VALUES (N'1003', N'北京顺义南彩工业园区彩祥西路9号', N'可用', N'11000㎡', N'提供服务完整')
GO

INSERT INTO [dbo].[cpims_respository] ([REPO_ID], [REPO_ADDRESS], [REPO_STATUS], [REPO_AREA], [REPO_DESC]) VALUES (N'1004', N'广州白云石井石潭路大基围工业区', N'可用', N'1000㎡', N'物流极为便利')
GO

INSERT INTO [dbo].[cpims_respository] ([REPO_ID], [REPO_ADDRESS], [REPO_STATUS], [REPO_AREA], [REPO_DESC]) VALUES (N'1005', N' 香港北区文锦渡路（红桥新村）', N'可用', N'5000.00㎡', NULL)
GO

INSERT INTO [dbo].[cpims_respository] ([REPO_ID], [REPO_ADDRESS], [REPO_STATUS], [REPO_AREA], [REPO_DESC]) VALUES (N'1006', N'贵州省贵阳市花溪区', N'可用', N'5000平米', N'大容量，交通不太方便')
GO

INSERT INTO [dbo].[cpims_respository] ([REPO_ID], [REPO_ADDRESS], [REPO_STATUS], [REPO_AREA], [REPO_DESC]) VALUES (N'1007', N'贵阳市观山湖区观山东路', N'不可用', N'1000平米', N'')
GO

SET IDENTITY_INSERT [dbo].[cpims_respository] OFF
GO


-- ----------------------------
-- Table structure for cpims_supplier
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_supplier]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_supplier]
GO

CREATE TABLE [dbo].[cpims_supplier] (
  [SUPPLIER_ID] int  IDENTITY(1,1) NOT NULL,
  [SUPPLIER_NAME] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_PERSON] nvarchar(10) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_TEL] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_EMAIL] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [SUPPLIER_ADDRESS] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_supplier] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_supplier
-- ----------------------------
SET IDENTITY_INSERT [dbo].[cpims_supplier] ON
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1013', N'浙江奇同电器有限公司', N'王泽伟', N'13777771126', N'86827868@126.com', N'中国 浙江 温州市龙湾区 龙湾区永强大道1648号')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1014', N'醴陵春天陶瓷实业有限公司', N'温仙容', N'13974167256', N'23267999@126.com', N'中国 湖南 醴陵市 东正街15号')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1015', N'洛阳嘉吉利饮品有限公司', N'郑绮云', N'26391678', N'22390898@qq.com', N'中国 广东 佛山市顺德区 北滘镇怡和路2号怡和中心14楼')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1017', N'Intel', N'Jack', N'534957349', N'Jack@Intel.com', N'中国 广州')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1019', N'深圳市亚宁电子有限公司', N'叶春恒', N' 86-0755-89574775', N'yechunheng@yaning.com', N'深圳市龙岗区布吉镇丹竹头沙平北路462号1楼')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1020', N'Intel', N'Jack', N'534957349', N'Jack@Intel.com', N'中国 广州')
GO

INSERT INTO [dbo].[cpims_supplier] ([SUPPLIER_ID], [SUPPLIER_NAME], [SUPPLIER_PERSON], [SUPPLIER_TEL], [SUPPLIER_EMAIL], [SUPPLIER_ADDRESS]) VALUES (N'1021', N'Intel', N'Jack', N'534957349', N'Jack@Intel.com', N'中国 广州')
GO

SET IDENTITY_INSERT [dbo].[cpims_supplier] OFF
GO


-- ----------------------------
-- Table structure for cpims_user
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[cpims_user]') AND type IN ('U'))
	DROP TABLE [dbo].[cpims_user]
GO

CREATE TABLE [dbo].[cpims_user] (
  [USER_ID] int  NOT NULL,
  [USER_USERNAME] nvarchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [USER_PASSWORD] nvarchar(40) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL
)
GO

ALTER TABLE [dbo].[cpims_user] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of cpims_user
-- ----------------------------
INSERT INTO [dbo].[cpims_user]  VALUES (N'0', N'詹伟伟', N'123456')
GO

INSERT INTO [dbo].[cpims_user]  VALUES (N'1001', N'admin', N'6f5379e73c1a9eac6163ab8eaec7e41c')
GO

INSERT INTO [dbo].[cpims_user]  VALUES (N'1018', N'王皓', N'50f202f4862360e55635b0a9616ded13')
GO

INSERT INTO [dbo].[cpims_user]  VALUES (N'1019', N'李富荣', N'c4b3af5a5ab3e3d5aac4c5a5436201b8')
GO

INSERT INTO [dbo].[cpims_user]  VALUES (N'1036', N'张三', N'75de88b7ad86c73a90cab60ff96743a1')
GO


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

