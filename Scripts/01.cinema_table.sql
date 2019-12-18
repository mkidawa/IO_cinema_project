--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- SETTINGS  - Table contains settings of system
--- CREATED BY: PZajac
--- drop table SETTINGS
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='SETTINGS' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table SETTINGS';

	CREATE TABLE [dbo].SETTINGS(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited

	Symbol			nvarchar(100)		collate Polish_CS_AS	not null default '',
	Value			nvarchar(250)		collate Polish_CS_AS	not null default '',

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT SETTINGS_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'SETTINGS_Id')
--BEGIN
--	Print 'Crating index  SETTINGS_Id';
--	CREATE UNIQUE INDEX SETTINGS_Id 	 ON SETTINGS(Id);
--END;
--GO
--- finish SETTINGS

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Product  - Table contains product detail
--- CREATED BY: PZajac
--- drop table Product
--------------------------------------------------------------------------------------------
IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Product' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Product';

	CREATE TABLE [dbo].Product(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields
	
	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	Price			decimal(9,2)		not null default 0,
	Amount 			int 				not null default 0 ,

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Product_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Product_Id')
--BEGIN
--	Print 'Crating index  Product_Id';
--	CREATE UNIQUE INDEX Product_Id 	 ON Product(Id);
--END;
--GO
--- finish Product


--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- PackPO  - Table contains pack position
--- CREATED BY: PZajac
--- drop table PackPO
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='PackPO' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table PackPO';

	CREATE TABLE [dbo].PackPO(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	PackHId			bigint				not null default 0,
	ProductId		bigint				not null default 0,

	-- Fields to be edited
	Amount			decimal(9,2)		not null default 0,

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	_Price			decimal(9,2)		not null default 0,

	-- This line should be removed if the table does not have primary key
	CONSTRAINT PackPO_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'PackPO_Id')
--BEGIN
--	Print 'Crating index  PackPO_Id';
--	CREATE UNIQUE INDEX PackPO_Id 	 ON PackPO(Id);
--END;
--GO
--- finish PackPO

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- PackH  - Table cointains pack header
--- CREATED BY: PZajac
--- drop table PackH
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='PackH' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table PackH';

	CREATE TABLE [dbo].PackH(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	_Price			decimal(9,2)		not null default 0,

	-- This line should be removed if the table does not have primary key
	CONSTRAINT PackH_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'PackH_Id')
--BEGIN
--	Print 'Crating index  PackH_Id';
--	CREATE UNIQUE INDEX PackH_Id 	 ON PackH(Id);
--END;
--GO
--- finish PackH

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- SalePO  - Table contains sale position
--- CREATED BY: PZajac
--- drop table SalePO
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='SalePO' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table SalePO';

	CREATE TABLE [dbo].SalePO(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	PackHId			bigint				not null default 0,
	SaleHId			bigint				not null default 0,
	
	-- Fields to be edited
	Amount			decimal(9,2)		not null default 0,

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	_Price			decimal(9,2)		not null default 0,

	-- This line should be removed if the table does not have primary key
	CONSTRAINT SalePO_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'SalePO_Id')
--BEGIN
--	Print 'Crating index  SalePO_Id';
--	CREATE UNIQUE INDEX SalePO_Id 	 ON SalePO(Id);
--END;
--GO
--- finish SalePO

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- SaleH  - Table contains sale header
--- CREATED BY: PZajac
--- drop table SaleH
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='SaleH' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table SaleH';

	CREATE TABLE [dbo].SaleH(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	UserId			bigint				not null default 0,

	-- Fields to be edited

	-- Other fiels
	SaleDate		datetime2(3)		not null default '1900-01-01',

	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	_Price			decimal(9,2)		not null default 0,

	-- This line should be removed if the table does not have primary key
	CONSTRAINT SaleH_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'SaleH_Id')
--BEGIN
--	Print 'Crating index  SaleH_Id';
--	CREATE UNIQUE INDEX SaleH_Id 	 ON SaleH(Id);
--END;
--GO
--- finish SaleH

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Users  - Table contains users information
--- CREATED BY: PZajac
--- drop table Users
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Users' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Users';

	CREATE TABLE [dbo].Users(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	FirstName		nvarchar(100)		collate Polish_CS_AS	not null default '',
	LastName		nvarchar(100)		collate Polish_CS_AS	not null default '',
	Login			nvarchar(100)		collate Polish_CS_AS	not null default '',
	PasswordHash	nvarchar(max)		collate Polish_CS_AS	not null default '',
	CodeHash		nvarchar(max)		collate Polish_CS_AS	not null default '',
	BaseSalary		decimal(9,2)		not null default 0,
	HourlyRate		decimal(9,2)		not null default 0,
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Users_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Users_Id')
--BEGIN
--	Print 'Crating index  Users_Id';
--	CREATE UNIQUE INDEX Users_Id 	 ON Users(Id);
--END;
--GO
--- finish Users

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- UsersPermission  - Table contains info about users permission
--- CREATED BY: PZajac
--- drop table UsersPermission
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='UsersPermission' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table UsersPermission';

	CREATE TABLE [dbo].UsersPermission(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	PermissionId	bigint				not null default 0,
	UserId			bigint				not null default 0,
	-- Fields to be edited

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT UsersPermission_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'UsersPermission_Id')
--BEGIN
--	Print 'Crating index  UsersPermission_Id';
--	CREATE UNIQUE INDEX UsersPermission_Id 	 ON UsersPermission(Id);
--END;
--GO
--- finish UsersPermission

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTPermission  - Table contains dictionary of user permission
--- CREATED BY: PZajac
--- drop table DICTPermission
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTPermission' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTPermission';

	CREATE TABLE [dbo].DICTPermission(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(200)		not null default '',
	Code			int					not null default 0,
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTPermission_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTPermission_Id')
--BEGIN
--	Print 'Crating index  DICTPermission_Id';
--	CREATE UNIQUE INDEX DICTPermission_Id 	 ON DICTPermission(Id);
--END;
--GO
--- finish DICTPermission

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- TnAData  - Table contains T&A workers data
--- CREATED BY: PZajac
--- drop table TnAData
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='TnAData' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table TnAData';

	CREATE TABLE [dbo].TnAData(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	UsersId			bigint				not null default 0,
	-- Fields to be edited
	DateDay			date				not null default '1900-01-01',
	TimeFrom		datetime2(3)		not null default '1900-01-01',
	TimeTo			datetime2(3)		not null default '1900-01-01',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT TnAData_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'TnAData_Id')
--BEGIN
--	Print 'Crating index  TnAData_Id';
--	CREATE UNIQUE INDEX TnAData_Id 	 ON TnAData(Id);
--END;
--GO
--- finish TnAData

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Task  - Table contains task detail
--- CREATED BY: PZajac
--- drop table Task
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Task' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Task';

	CREATE TABLE [dbo].Task(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	Description		nvarchar(1000)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Task_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Task_Id')
--BEGIN
--	Print 'Crating index  Task_Id';
--	CREATE UNIQUE INDEX Task_Id 	 ON Task(Id);
--END;
--GO
--- finish Task

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Schedule  - Table contains user's shwduled tasks
--- CREATED BY: PZajac
--- drop table Schedule
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Schedule' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Schedule';

	CREATE TABLE [dbo].Schedule(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	UsersId			bigint				not null default 0,
	TaskId			bigint				not null default 0,
	SheduleStatusId	bigint				not null default 0,
	-- Fields to be edited
	DateFrom		datetime2(3)		not null default '1900-01-01',
	DateTo			datetime2(3)		not null default '1900-01-01',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Schedule_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Schedule_Id')
--BEGIN
--	Print 'Crating index  Schedule_Id';
--	CREATE UNIQUE INDEX Schedule_Id 	 ON Schedule(Id);
--END;
--GO
--- finish Schedule

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTScheduleStatus  - Table contains dictionary of schedule status
--- CREATED BY: PZajac
--- drop table DICTScheduleStatus
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTScheduleStatus' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTScheduleStatus';

	CREATE TABLE [dbo].DICTScheduleStatus(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTScheduleStatus_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTScheduleStatus_Id')
--BEGIN
--	Print 'Crating index  DICTScheduleStatus_Id';
--	CREATE UNIQUE INDEX DICTScheduleStatus_Id 	 ON DICTScheduleStatus(Id);
--END;
--GO
--- finish DICTScheduleStatus

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Movie  - Table contains info about movie
--- CREATED BY: PZajac
--- drop table Movie
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Movie' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Movie';

	CREATE TABLE [dbo].Movie(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields
	flg2D			tinyint				not null default 0,
	flg3D			tinyint				not null default 0,
	flgVR			tinyint				not null default 0,
	-- Fields that will be joined with other tables
	MovieTypeId		bigint				not null default 0,
	MovieStateId	bigint				not null default 0,

	-- Fields to be edited
	Title			nvarchar(250)		collate Polish_CS_AS	not null default '',
	Description		nvarchar(1000)		collate Polish_CS_AS	not null default '',
	MovieTime		time(3)				default '00:00:00',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Movie_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Movie_Id')
--BEGIN
--	Print 'Crating index  Movie_Id';
--	CREATE UNIQUE INDEX Movie_Id 	 ON Movie(Id);
--END;
--GO
--- finish Movie

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTMovieType  - Table contains dictionary of movie type
--- CREATED BY: PZajac
--- drop table DICTMovieType
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTMovieType' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTMovieType';

	CREATE TABLE [dbo].DICTMovieType(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTMovieType_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTMovieType_Id')
--BEGIN
--	Print 'Crating index  DICTMovieType_Id';
--	CREATE UNIQUE INDEX DICTMovieType_Id 	 ON DICTMovieType(Id);
--END;
--GO
--- finish DICTMovieType

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTMovieState  - Table contains dictionary of movie state
--- CREATED BY: PZajac
--- drop table DICTMovieState
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTMovieState' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTMovieState';

	CREATE TABLE [dbo].DICTMovieState(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTMovieState_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTMovieState_Id')
--BEGIN
--	Print 'Crating index  DICTMovieState_Id';
--	CREATE UNIQUE INDEX DICTMovieState_Id 	 ON DICTMovieState(Id);
--END;
--GO
--- finish DICTMovieState

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Hall  - Table contains hall info
--- CREATED BY: PZajac
--- drop table Hall
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Hall' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Hall';

	CREATE TABLE [dbo].Hall(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields
	flg2D			tinyint				not null default 0,
	flg3D			tinyint				not null default 0,
	flgVR			tinyint				not null default 0,
	flgX			int					not null default 0,
	flgY			int					not null default 0,
	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	Description		nvarchar(1000)		collate Polish_CS_AS	not null default '',

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Hall_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Hall_Id')
--BEGIN
--	Print 'Crating index  Hall_Id';
--	CREATE UNIQUE INDEX Hall_Id 	 ON Hall(Id);
--END;
--GO
--- finish Hall

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTPersonType  - Table contains dictionary of person type
--- CREATED BY: PZajac
--- drop table DICTPersonType
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTPersonType' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTPersonType';

	CREATE TABLE [dbo].DICTPersonType(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTPersonType_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTPersonType_Id')
--BEGIN
--	Print 'Crating index  DICTPersonType_Id';
--	CREATE UNIQUE INDEX DICTPersonType_Id 	 ON DICTPersonType(Id);
--END;
--GO
--- finish DICTPersonType

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Person  - Table contains information about person
--- CREATED BY: PZajac
--- drop table Person
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Person' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Person';

	CREATE TABLE [dbo].Person(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	FirstName		nvarchar(100)		collate Polish_CS_AS	not null default '',
	LastName		nvarchar(100)		collate Polish_CS_AS	not null default '',
	DateBorn		datetime2(3)		not null default '1900-01-01',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Person_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Person_Id')
--BEGIN
--	Print 'Crating index  Person_Id';
--	CREATE UNIQUE INDEX Person_Id 	 ON Person(Id);
--END;
--GO
--- finish Person

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- MoviePersonPersonType  - Table connect movie with person and person type
--- CREATED BY: PZajac
--- drop table MoviePersonPersonType
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='MoviePersonPersonType' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table MoviePersonPersonType';

	CREATE TABLE [dbo].MoviePersonPersonType(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	MovieId			bigint				not null default 0,
	PersonTypeId	bigint				not null default 0,
	PersonId		bigint				not null default 0,
	-- Fields to be edited

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT MoviePersonPersonType_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'MoviePersonPersonType_Id')
--BEGIN
--	Print 'Crating index  MoviePersonPersonType_Id';
--	CREATE UNIQUE INDEX MoviePersonPersonType_Id 	 ON MoviePersonPersonType(Id);
--END;
--GO
--- finish MoviePersonPersonType

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Performance  - Table contains info about performance
--- CREATED BY: PZajac
--- drop table Performance
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Performance' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Performance';

	CREATE TABLE [dbo].Performance(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	HallId			bigint				not null default 0,
	MovieId			bigint				not null default 0,
	-- Fields to be edited
	AdsDuration		bigint  		    not null default '0',
	PerformanceType varchar(255)        not null default ''
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Performance_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Performance_Id')
--BEGIN
--	Print 'Crating index  Performance_Id';
--	CREATE UNIQUE INDEX Performance_Id 	 ON Performance(Id);
--END;
--GO
--- finish Performance

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- Reservation  - Table contains info about reservation
--- CREATED BY: PZajac
--- drop table Reservation
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='Reservation' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table Reservation';

	CREATE TABLE [dbo].Reservation(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields
	flgPosX			int					not null default 0,
	flgPosY			int					not null default 0,

	-- Fields that will be joined with other tables
	StatusId		bigint				not null default 0,
	TimeTableId		bigint				not null default 0,
	ReservationDate	datetime2(3)		not null default '1900-01-01',
	-- Fields to be edited

	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT Reservation_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'Reservation_Id')
--BEGIN
--	Print 'Crating index  Reservation_Id';
--	CREATE UNIQUE INDEX Reservation_Id 	 ON Reservation(Id);
--END;
--GO
--- finish Reservation

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- DICTReservationStatus  - Table contains dictionary of reservation status
--- CREATED BY: PZajac
--- drop table DICTReservationStatus
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='DICTReservationStatus' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table DICTReservationStatus';

	CREATE TABLE [dbo].DICTReservationStatus(
	Id				BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	
	-- Fields to be edited
	Name			nvarchar(100)		collate Polish_CS_AS	not null default '',
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT DICTReservationStatus_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'DICTReservationStatus_Id')
--BEGIN
--	Print 'Crating index  DICTReservationStatus_Id';
--	CREATE UNIQUE INDEX DICTReservationStatus_Id 	 ON DICTReservationStatus(Id);
--END;
--GO
--- finish DICTReservationStatus

--------------------------------------------------------------------------------------------
--- TABLE DEFINITION
--- TimeTable  - Table contains info about timetable
--- CREATED BY: PZajac
--- drop table TimeTable
--------------------------------------------------------------------------------------------

IF db_name()<>'master' and
   not exists (	select * from INFORMATION_SCHEMA.TABLES
		where table_name='TimeTable' and table_type='BASE TABLE')
BEGIN
	Print 'Creating table TimeTable';

	CREATE TABLE [dbo].TimeTable(
	Id		BIGINT IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,	-- Primary Key
	-- Status-like fields

	-- Fields that will be joined with other tables
	PerformanceId	bigint				not null default 0,
	-- Fields to be edited
	PerformanceDate	datetime2(3)		not null default '1900-01-01'
	-- Other fiels
	
	-- Fields to be calculated only by triggers
	-- This kind of fields must begin with '_'
	
	-- This line should be removed if the table does not have primary key
	CONSTRAINT TimeTable_Id PRIMARY KEY CLUSTERED (Id)
  	);

END;
go
-- This line should be removed if the table has primary key
--if not exists (select * from sysindexes where name like 'TimeTable_Id')
--BEGIN
--	Print 'Crating index  TimeTable_Id';
--	CREATE UNIQUE INDEX TimeTable_Id 	 ON TimeTable(Id);
--END;
--GO
--- finish TimeTable