CREATE DATABASE proj_HCUPSID_CA;
USE proj_HCUPSID_CA;

#load CORE tables

CREATE TABLE `ca2006trim2` (
  `idkey` varchar(255) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `ASCHED` int(11) DEFAULT NULL,
  `ASOURCE` varchar(255) DEFAULT NULL,
  `ASOURCE_X` varchar(255) DEFAULT NULL,
  `AWEEKEND` int(11) DEFAULT NULL,
  `DIED` int(11) DEFAULT NULL,
  `DRG` int(11) DEFAULT NULL,
  `DRG18` int(11) DEFAULT NULL,
  `DRG24` int(11) DEFAULT NULL,
  `DRGVER` int(11) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX3` varchar(255) DEFAULT NULL,
  `DX4` varchar(255) DEFAULT NULL,
  `DX5` varchar(255) DEFAULT NULL,
  `DX6` varchar(255) DEFAULT NULL,
  `DX7` varchar(255) DEFAULT NULL,
  `DX8` varchar(255) DEFAULT NULL,
  `DX9` varchar(255) DEFAULT NULL,
  `DX10` varchar(255) DEFAULT NULL,
  `DX11` varchar(255) DEFAULT NULL,
  `DX12` varchar(255) DEFAULT NULL,
  `DX13` varchar(255) DEFAULT NULL,
  `DX14` varchar(255) DEFAULT NULL,
  `DX15` varchar(255) DEFAULT NULL,
  `DX16` varchar(255) DEFAULT NULL,
  `DX17` varchar(255) DEFAULT NULL,
  `DX18` varchar(255) DEFAULT NULL,
  `DX19` varchar(255) DEFAULT NULL,
  `DX20` varchar(255) DEFAULT NULL,
  `DX21` varchar(255) DEFAULT NULL,
  `DX22` varchar(255) DEFAULT NULL,
  `DX23` varchar(255) DEFAULT NULL,
  `DX24` varchar(255) DEFAULT NULL,
  `DX25` varchar(255) DEFAULT NULL,
  `DXATADMIT1` varchar(255) DEFAULT NULL,
  `DXATADMIT2` varchar(255) DEFAULT NULL,
  `DXATADMIT3` varchar(255) DEFAULT NULL,
  `DXATADMIT4` varchar(255) DEFAULT NULL,
  `DXATADMIT5` varchar(255) DEFAULT NULL,
  `DXATADMIT6` varchar(255) DEFAULT NULL,
  `DXATADMIT7` varchar(255) DEFAULT NULL,
  `DXATADMIT8` varchar(255) DEFAULT NULL,
  `DXATADMIT9` varchar(255) DEFAULT NULL,
  `DXATADMIT10` varchar(255) DEFAULT NULL,
  `DXATADMIT11` varchar(255) DEFAULT NULL,
  `DXATADMIT12` varchar(255) DEFAULT NULL,
  `DXATADMIT13` varchar(255) DEFAULT NULL,
  `DXATADMIT14` varchar(255) DEFAULT NULL,
  `DXATADMIT15` varchar(255) DEFAULT NULL,
  `DXATADMIT16` varchar(255) DEFAULT NULL,
  `DXATADMIT17` varchar(255) DEFAULT NULL,
  `DXATADMIT18` varchar(255) DEFAULT NULL,
  `DXATADMIT19` varchar(255) DEFAULT NULL,
  `DXATADMIT20` varchar(255) DEFAULT NULL,
  `DXATADMIT21` varchar(255) DEFAULT NULL,
  `DXATADMIT22` varchar(255) DEFAULT NULL,
  `DXATADMIT23` varchar(255) DEFAULT NULL,
  `DXATADMIT24` varchar(255) DEFAULT NULL,
  `DXATADMIT25` varchar(255) DEFAULT NULL,
  `FEMALE` int(11) DEFAULT NULL,
  `HCUP_ED` int(11) DEFAULT NULL,
  `HCUP_OS` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `LOS` varchar(255) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `RACE_X` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL,
  `BYEAR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


SELECT @RNUM := @RNUM + 1 AS ROWNUM, t.*


alter table ca2006trim2 add column ROWNUM INT not null AUTO_INCREMENT PRIMARY KEY;
delete from ca2006trim2 where ROWNUM=90932;
delete from ca2006trim2 where ROWNUM=90950;

CREATE TABLE `ca2007trim2` (
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `ASCHED` int(11) DEFAULT NULL,
  `ASOURCE` varchar(255) DEFAULT NULL,
  `ASOURCE_X` varchar(255) DEFAULT NULL,
  `AWEEKEND` int(11) DEFAULT NULL,
  `DIED` int(11) DEFAULT NULL,
  `DRG` int(11) DEFAULT NULL,
  `DRG24` int(11) DEFAULT NULL,
  `DRGVER` int(11) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX3` varchar(255) DEFAULT NULL,
  `DX4` varchar(255) DEFAULT NULL,
  `DX5` varchar(255) DEFAULT NULL,
  `DX6` varchar(255) DEFAULT NULL,
  `DX7` varchar(255) DEFAULT NULL,
  `DX8` varchar(255) DEFAULT NULL,
  `DX9` varchar(255) DEFAULT NULL,
  `DX10` varchar(255) DEFAULT NULL,
  `DX11` varchar(255) DEFAULT NULL,
  `DX12` varchar(255) DEFAULT NULL,
  `DX13` varchar(255) DEFAULT NULL,
  `DX14` varchar(255) DEFAULT NULL,
  `DX15` varchar(255) DEFAULT NULL,
  `DX16` varchar(255) DEFAULT NULL,
  `DX17` varchar(255) DEFAULT NULL,
  `DX18` varchar(255) DEFAULT NULL,
  `DX19` varchar(255) DEFAULT NULL,
  `DX20` varchar(255) DEFAULT NULL,
  `DX21` varchar(255) DEFAULT NULL,
  `DX22` varchar(255) DEFAULT NULL,
  `DX23` varchar(255) DEFAULT NULL,
  `DX24` varchar(255) DEFAULT NULL,
  `DX25` varchar(255) DEFAULT NULL,
  `DXPOA1` varchar(255) DEFAULT NULL,
  `DXPOA2` varchar(255) DEFAULT NULL,
  `DXPOA3` varchar(255) DEFAULT NULL,
  `DXPOA4` varchar(255) DEFAULT NULL,
  `DXPOA5` varchar(255) DEFAULT NULL,
  `DXPOA6` varchar(255) DEFAULT NULL,
  `DXPOA7` varchar(255) DEFAULT NULL,
  `DXPOA8` varchar(255) DEFAULT NULL,
  `DXPOA9` varchar(255) DEFAULT NULL,
  `DXPOA10` varchar(255) DEFAULT NULL,
  `DXPOA11` varchar(255) DEFAULT NULL,
  `DXPOA12` varchar(255) DEFAULT NULL,
  `DXPOA13` varchar(255) DEFAULT NULL,
  `DXPOA14` varchar(255) DEFAULT NULL,
  `DXPOA15` varchar(255) DEFAULT NULL,
  `DXPOA16` varchar(255) DEFAULT NULL,
  `DXPOA17` varchar(255) DEFAULT NULL,
  `DXPOA18` varchar(255) DEFAULT NULL,
  `DXPOA19` varchar(255) DEFAULT NULL,
  `DXPOA20` varchar(255) DEFAULT NULL,
  `DXPOA21` varchar(255) DEFAULT NULL,
  `DXPOA22` varchar(255) DEFAULT NULL,
  `DXPOA23` varchar(255) DEFAULT NULL,
  `DXPOA24` varchar(255) DEFAULT NULL,
  `DXPOA25` varchar(255) DEFAULT NULL,
  `FEMALE` int(11) DEFAULT NULL,
  `HCUP_ED` int(11) DEFAULT NULL,
  `HCUP_OS` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `idkey` varchar(255) DEFAULT NULL,
  `LOS` varchar(255) DEFAULT NULL,
  `LOS_X` int(11) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `RACE_X` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL,
  `BYEAR` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
alter table ca2007trim2 add column ROWNUM INT not null AUTO_INCREMENT PRIMARY KEY;


CREATE TABLE `ca2008trim2` (
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `ASCHED` int(11) DEFAULT NULL,
  `ASOURCE` varchar(255) DEFAULT NULL,
  `ASOURCE_X` varchar(255) DEFAULT NULL,
  `AWEEKEND` int(11) DEFAULT NULL,
  `DIED` int(11) DEFAULT NULL,
  `DRG` int(11) DEFAULT NULL,
  `DRG24` int(11) DEFAULT NULL,
  `DRGVER` int(11) DEFAULT NULL,
  `DRG_NoPOA` int(11) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX3` varchar(255) DEFAULT NULL,
  `DX4` varchar(255) DEFAULT NULL,
  `DX5` varchar(255) DEFAULT NULL,
  `DX6` varchar(255) DEFAULT NULL,
  `DX7` varchar(255) DEFAULT NULL,
  `DX8` varchar(255) DEFAULT NULL,
  `DX9` varchar(255) DEFAULT NULL,
  `DX10` varchar(255) DEFAULT NULL,
  `DX11` varchar(255) DEFAULT NULL,
  `DX12` varchar(255) DEFAULT NULL,
  `DX13` varchar(255) DEFAULT NULL,
  `DX14` varchar(255) DEFAULT NULL,
  `DX15` varchar(255) DEFAULT NULL,
  `DX16` varchar(255) DEFAULT NULL,
  `DX17` varchar(255) DEFAULT NULL,
  `DX18` varchar(255) DEFAULT NULL,
  `DX19` varchar(255) DEFAULT NULL,
  `DX20` varchar(255) DEFAULT NULL,
  `DX21` varchar(255) DEFAULT NULL,
  `DX22` varchar(255) DEFAULT NULL,
  `DX23` varchar(255) DEFAULT NULL,
  `DX24` varchar(255) DEFAULT NULL,
  `DX25` varchar(255) DEFAULT NULL,
  `DXPOA1` varchar(255) DEFAULT NULL,
  `DXPOA2` varchar(255) DEFAULT NULL,
  `DXPOA3` varchar(255) DEFAULT NULL,
  `DXPOA4` varchar(255) DEFAULT NULL,
  `DXPOA5` varchar(255) DEFAULT NULL,
  `DXPOA6` varchar(255) DEFAULT NULL,
  `DXPOA7` varchar(255) DEFAULT NULL,
  `DXPOA8` varchar(255) DEFAULT NULL,
  `DXPOA9` varchar(255) DEFAULT NULL,
  `DXPOA10` varchar(255) DEFAULT NULL,
  `DXPOA11` varchar(255) DEFAULT NULL,
  `DXPOA12` varchar(255) DEFAULT NULL,
  `DXPOA13` varchar(255) DEFAULT NULL,
  `DXPOA14` varchar(255) DEFAULT NULL,
  `DXPOA15` varchar(255) DEFAULT NULL,
  `DXPOA16` varchar(255) DEFAULT NULL,
  `DXPOA17` varchar(255) DEFAULT NULL,
  `DXPOA18` varchar(255) DEFAULT NULL,
  `DXPOA19` varchar(255) DEFAULT NULL,
  `DXPOA20` varchar(255) DEFAULT NULL,
  `DXPOA21` varchar(255) DEFAULT NULL,
  `DXPOA22` varchar(255) DEFAULT NULL,
  `DXPOA23` varchar(255) DEFAULT NULL,
  `DXPOA24` varchar(255) DEFAULT NULL,
  `DXPOA25` varchar(255) DEFAULT NULL,
  `FEMALE` int(11) DEFAULT NULL,
  `HCUP_ED` int(11) DEFAULT NULL,
  `HCUP_OS` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `idkey` varchar(255) DEFAULT NULL,
  `LOS` varchar(255) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `RACE_X` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
alter table ca2008trim2 add column ROWNUM INT not null AUTO_INCREMENT PRIMARY KEY;

CREATE TABLE `ca2009trim2` (
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `ASCHED` int(11) DEFAULT NULL,
  `ASOURCE` varchar(255) DEFAULT NULL,
  `ASOURCE_X` varchar(255) DEFAULT NULL,
  `AWEEKEND` int(11) DEFAULT NULL,
  `DIED` int(11) DEFAULT NULL,
  `DRG` int(11) DEFAULT NULL,
  `DRG24` int(11) DEFAULT NULL,
  `DRGVER` int(11) DEFAULT NULL,
  `DRG_NoPOA` int(11) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX3` varchar(255) DEFAULT NULL,
  `DX4` varchar(255) DEFAULT NULL,
  `DX5` varchar(255) DEFAULT NULL,
  `DX6` varchar(255) DEFAULT NULL,
  `DX7` varchar(255) DEFAULT NULL,
  `DX8` varchar(255) DEFAULT NULL,
  `DX9` varchar(255) DEFAULT NULL,
  `DX10` varchar(255) DEFAULT NULL,
  `DX11` varchar(255) DEFAULT NULL,
  `DX12` varchar(255) DEFAULT NULL,
  `DX13` varchar(255) DEFAULT NULL,
  `DX14` varchar(255) DEFAULT NULL,
  `DX15` varchar(255) DEFAULT NULL,
  `DX16` varchar(255) DEFAULT NULL,
  `DX17` varchar(255) DEFAULT NULL,
  `DX18` varchar(255) DEFAULT NULL,
  `DX19` varchar(255) DEFAULT NULL,
  `DX20` varchar(255) DEFAULT NULL,
  `DX21` varchar(255) DEFAULT NULL,
  `DX22` varchar(255) DEFAULT NULL,
  `DX23` varchar(255) DEFAULT NULL,
  `DX24` varchar(255) DEFAULT NULL,
  `DX25` varchar(255) DEFAULT NULL,
  `DXPOA1` varchar(255) DEFAULT NULL,
  `DXPOA2` varchar(255) DEFAULT NULL,
  `DXPOA3` varchar(255) DEFAULT NULL,
  `DXPOA4` varchar(255) DEFAULT NULL,
  `DXPOA5` varchar(255) DEFAULT NULL,
  `DXPOA6` varchar(255) DEFAULT NULL,
  `DXPOA7` varchar(255) DEFAULT NULL,
  `DXPOA8` varchar(255) DEFAULT NULL,
  `DXPOA9` varchar(255) DEFAULT NULL,
  `DXPOA10` varchar(255) DEFAULT NULL,
  `DXPOA11` varchar(255) DEFAULT NULL,
  `DXPOA12` varchar(255) DEFAULT NULL,
  `DXPOA13` varchar(255) DEFAULT NULL,
  `DXPOA14` varchar(255) DEFAULT NULL,
  `DXPOA15` varchar(255) DEFAULT NULL,
  `DXPOA16` varchar(255) DEFAULT NULL,
  `DXPOA17` varchar(255) DEFAULT NULL,
  `DXPOA18` varchar(255) DEFAULT NULL,
  `DXPOA19` varchar(255) DEFAULT NULL,
  `DXPOA20` varchar(255) DEFAULT NULL,
  `DXPOA21` varchar(255) DEFAULT NULL,
  `DXPOA22` varchar(255) DEFAULT NULL,
  `DXPOA23` varchar(255) DEFAULT NULL,
  `DXPOA24` varchar(255) DEFAULT NULL,
  `DXPOA25` varchar(255) DEFAULT NULL,
  `DaysToEvent` int(11) DEFAULT NULL,
  `FEMALE` int(11) DEFAULT NULL,
  `HCUP_ED` int(11) DEFAULT NULL,
  `HCUP_OS` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `idkey` varchar(255) DEFAULT NULL,
  `LOS` varchar(255) DEFAULT NULL,
  `LOS_X` varchar(255) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `RACE_X` int(11) DEFAULT NULL,
  `VisitLink` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

alter table ca2009trim2 add column ROWNUM INT not null AUTO_INCREMENT PRIMARY KEY;

CREATE TABLE `ca2010trim2_all` (
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `ASCHED` int(11) DEFAULT NULL,
  `ASOURCE` varchar(255) DEFAULT NULL,
  `ASOURCE_X` varchar(255) DEFAULT NULL,
  `AWEEKEND` int(11) DEFAULT NULL,
  `DIED` int(11) DEFAULT NULL,
  `DRG` int(11) DEFAULT NULL,
  `DRG24` int(11) DEFAULT NULL,
  `DRGVER` int(11) DEFAULT NULL,
  `DRG_NoPOA` int(11) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX3` varchar(255) DEFAULT NULL,
  `DX4` varchar(255) DEFAULT NULL,
  `DX5` varchar(255) DEFAULT NULL,
  `DX6` varchar(255) DEFAULT NULL,
  `DX7` varchar(255) DEFAULT NULL,
  `DX8` varchar(255) DEFAULT NULL,
  `DX9` varchar(255) DEFAULT NULL,
  `DX10` varchar(255) DEFAULT NULL,
  `DX11` varchar(255) DEFAULT NULL,
  `DX12` varchar(255) DEFAULT NULL,
  `DX13` varchar(255) DEFAULT NULL,
  `DX14` varchar(255) DEFAULT NULL,
  `DX15` varchar(255) DEFAULT NULL,
  `DX16` varchar(255) DEFAULT NULL,
  `DX17` varchar(255) DEFAULT NULL,
  `DX18` varchar(255) DEFAULT NULL,
  `DX19` varchar(255) DEFAULT NULL,
  `DX20` varchar(255) DEFAULT NULL,
  `DX21` varchar(255) DEFAULT NULL,
  `DX22` varchar(255) DEFAULT NULL,
  `DX23` varchar(255) DEFAULT NULL,
  `DX24` varchar(255) DEFAULT NULL,
  `DX25` varchar(255) DEFAULT NULL,
  `DXPOA1` varchar(255) DEFAULT NULL,
  `DXPOA2` varchar(255) DEFAULT NULL,
  `DXPOA3` varchar(255) DEFAULT NULL,
  `DXPOA4` varchar(255) DEFAULT NULL,
  `DXPOA5` varchar(255) DEFAULT NULL,
  `DXPOA6` varchar(255) DEFAULT NULL,
  `DXPOA7` varchar(255) DEFAULT NULL,
  `DXPOA8` varchar(255) DEFAULT NULL,
  `DXPOA9` varchar(255) DEFAULT NULL,
  `DXPOA10` varchar(255) DEFAULT NULL,
  `DXPOA11` varchar(255) DEFAULT NULL,
  `DXPOA12` varchar(255) DEFAULT NULL,
  `DXPOA13` varchar(255) DEFAULT NULL,
  `DXPOA14` varchar(255) DEFAULT NULL,
  `DXPOA15` varchar(255) DEFAULT NULL,
  `DXPOA16` varchar(255) DEFAULT NULL,
  `DXPOA17` varchar(255) DEFAULT NULL,
  `DXPOA18` varchar(255) DEFAULT NULL,
  `DXPOA19` varchar(255) DEFAULT NULL,
  `DXPOA20` varchar(255) DEFAULT NULL,
  `DXPOA21` varchar(255) DEFAULT NULL,
  `DXPOA22` varchar(255) DEFAULT NULL,
  `DXPOA23` varchar(255) DEFAULT NULL,
  `DXPOA24` varchar(255) DEFAULT NULL,
  `DXPOA25` varchar(255) DEFAULT NULL,
  `DaysToEvent` int(11) DEFAULT NULL,
  `FEMALE` varchar(255) DEFAULT NULL,
  `HCUP_ED` int(11) DEFAULT NULL,
  `HCUP_OS` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `idkey` varchar(255) DEFAULT NULL,
  `LOS` varchar(255) DEFAULT NULL,
  `LOS_X` varchar(255) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `RACE_X` varchar(255) DEFAULT NULL,
  `VisitLink` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `ICD9CM_chapter_copy` (
  `Chapter` varchar(255) DEFAULT NULL,
  `ChapterTitle` varchar(255) DEFAULT NULL,
  `Block` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm.chapter.txt" into table ICD9CM_chapter_copy ignore 1 lines;

CREATE TABLE `ICD9CM_chapter` (
  `Chapter` varchar(255) DEFAULT NULL,
  `ChapterTitle` varchar(255) DEFAULT NULL,
  `Block` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm.chapter.txt" into table ICD9CM_chapter ignore 1 lines;
alter table ICD9CM_chapter add index (Block, Chapter);
alter table ICD9CM_chapter_copy add index (Block, Chapter);

create table ca2006trim3_chapter(
select concat(a.YEAR, ":", a.idkey) "mergeIdkey", concat(a.YEAR, ":", a.PNUM_R) "mergePNUM_R", a.idkey, a.AGE, a.AGEDAY, 
a.AGEMONTH, a.AMONTH, a.DIED, 
b.Chapter "DX1_Chapter", a.DX1, substr(a.DX1, 1,3) "DX1_lv3", c.Chapter "DX2_Chapter", 
a.DX2, substr(a.DX2, 1,3) "DX2_lv3",a.DXATADMIT1, a.DXATADMIT2, a.FEMALE, a.HISPANIC_X, a.HOSPBRTH, a.HospitalUnit, a.PNUM_R, a.RACE, a.YEAR, a.AYEAR, a.DMONTH, a.BMONTH, a.BYEAR, a.ROWNUM 
from ca2006trim2 a, ICD9CM_chapter b, ICD9CM_chapter c
where substr(a.DX1, 1,3)= b.Block and substr(a.DX2, 1,3)=c.Block
);
delete from ca2006trim3_chapter where DX1_Chapter ="V" or DX1_Chapter="E";

create table ca2007trim3_chapter(
select concat(a.YEAR, ":", a.idkey) "mergeIdkey", concat(a.YEAR, ":", a.PNUM_R) "mergePNUM_R", a.idkey, a.AGE, a.AGEDAY, 
a.AGEMONTH, a.AMONTH, a.DIED, 
b.Chapter "DX1_Chapter", a.DX1, substr(a.DX1, 1,3) "DX1_lv3", c.Chapter "DX2_Chapter", 
a.DX2, substr(a.DX2, 1,3) "DX2_lv3", a.DXPOA1 "DXATADMIT1", a.DXPOA2 "DXATADMIT2", a.FEMALE, a.HISPANIC_X, a.HOSPBRTH, a.HospitalUnit, a.PNUM_R, a.RACE, a.YEAR, a.AYEAR, a.DMONTH, a.BMONTH, a.BYEAR, a.ROWNUM 
from ca2007trim2 a, ICD9CM_chapter b, ICD9CM_chapter c
where substr(a.DX1, 1,3)= b.Block and substr(a.DX2, 1,3)=c.Block
);
delete from ca2007trim3_chapter where DX1_Chapter ="V" or DX1_Chapter="E";

create table ca2008trim3_chapter(
select concat(a.YEAR, ":", a.idkey) "mergeIdkey", concat(a.YEAR, ":", a.PNUM_R) "mergePNUM_R", a.idkey, a.AGE, a.AGEDAY, 
a.AGEMONTH, a.AMONTH, a.DIED, 
b.Chapter "DX1_Chapter", a.DX1, substr(a.DX1, 1,3) "DX1_lv3", c.Chapter "DX2_Chapter", 
a.DX2, substr(a.DX2, 1,3) "DX2_lv3", a.DXPOA1 "DXATADMIT1", a.DXPOA2 "DXATADMIT2", a.FEMALE, a.HISPANIC_X, a.HOSPBRTH, a.HospitalUnit, a.PNUM_R, a.RACE, a.YEAR, a.AYEAR, a.DMONTH, a.BMONTH,  a.ROWNUM 
from ca2008trim2 a, ICD9CM_chapter b, ICD9CM_chapter c
where substr(a.DX1, 1,3)= b.Block and substr(a.DX2, 1,3)=c.Block
);
delete from ca2008trim3_chapter where DX1_Chapter ="V" or DX1_Chapter="E";


create table ca2009trim3_chapter(
select concat(a.YEAR, ":", a.idkey) "mergeIdkey", concat(a.YEAR, ":", a.PNUM_R) "mergePNUM_R", a.idkey, a.AGE, a.AGEDAY, 
a.AGEMONTH, a.AMONTH, a.DIED, 
b.Chapter "DX1_Chapter", a.DX1, substr(a.DX1, 1,3) "DX1_lv3", c.Chapter "DX2_Chapter", 
a.DX2, substr(a.DX2, 1,3) "DX2_lv3", a.DXPOA1 "DXATADMIT1", a.DXPOA2 "DXATADMIT2", a.FEMALE, a.HISPANIC_X, a.HOSPBRTH, a.HospitalUnit, a.PNUM_R, a.RACE, a.YEAR, a.AYEAR, a.DMONTH, a.BMONTH,  a.ROWNUM 
from ca2009trim2 a, ICD9CM_chapter b, ICD9CM_chapter c
where substr(a.DX1, 1,3)= b.Block and substr(a.DX2, 1,3)=c.Block
);
delete from ca2009trim3_chapter where DX1_Chapter ="V" or DX1_Chapter="E";


alter table ca2010trim2_all add column ROWNUM INT not null AUTO_INCREMENT PRIMARY KEY;
create table ca2010trim3_all_chapter(
select concat(a.YEAR, ":", a.idkey) "mergeIdkey", concat(a.YEAR, ":", a.PNUM_R) "mergePNUM_R", a.idkey, a.AGE, a.AGEDAY, 
a.AGEMONTH, a.AMONTH, a.DIED, 
b.Chapter "DX1_Chapter", a.DX1, substr(a.DX1, 1,3) "DX1_lv3", c.Chapter "DX2_Chapter", 
a.DX2, substr(a.DX2, 1,3) "DX2_lv3", a.DXPOA1 "DXATADMIT1", a.DXPOA2 "DXATADMIT2", a.FEMALE, a.HISPANIC_X, a.HOSPBRTH, a.HospitalUnit, a.PNUM_R, a.RACE, a.YEAR, a.AYEAR, a.DMONTH, a.BMONTH, a.ROWNUM 
from ca2010trim2_all a, ICD9CM_chapter b, ICD9CM_chapter c
where substr(a.DX1, 1,3)= b.Block and substr(a.DX2, 1,3)=c.Block
);
delete from ca2010trim3_all_chapter where DX1_Chapter ="V" or DX1_Chapter="E";

CREATE TABLE `CASID_trim3All` (
  `YEAR` int(11) DEFAULT NULL,
  `mergeIdkey` varchar(255) DEFAULT NULL,
  `mergePNUM_R` varchar(255) DEFAULT NULL,
  `idkey` varchar(255) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  `AGEDAY` int(11) DEFAULT NULL,
  `AGEMONTH` int(11) DEFAULT NULL,
  `AYEAR` int(11) DEFAULT NULL,
  `AMONTH` int(11) DEFAULT NULL,
  `DMONTH` int(11) DEFAULT NULL,
  `DIED` varchar(255) DEFAULT NULL,
  `PNUM_R` int(11) DEFAULT NULL,
  `RACE` varchar(255) DEFAULT NULL,
  `BMONTH` int(11) DEFAULT NULL,
  `FEMALE` int(11) DEFAULT NULL,
  `HISPANIC_X` varchar(255) DEFAULT NULL,
  `HOSPBRTH` int(11) DEFAULT NULL,
  `HospitalUnit` int(11) DEFAULT NULL,
  `DX1_Chapter` varchar(255) DEFAULT NULL,
  `DX1` varchar(255) DEFAULT NULL,
  `DX1_lv3` varchar(255) DEFAULT NULL,
  `DX2_Chapter` varchar(255) DEFAULT NULL,
  `DX2` varchar(255) DEFAULT NULL,
  `DX2_lv3` varchar(255) DEFAULT NULL,
  `DXATADMIT1` varchar(255) DEFAULT NULL,
  `DXATADMIT2` varchar(255) DEFAULT NULL,
  `ROWNUM` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ca2006trim3" into table CASID_trim3All ignore 1 lines;
load data local infile "ca2007trim3" into table CASID_trim3All ignore 1 lines;
load data local infile "ca2008trim3" into table CASID_trim3All ignore 1 lines;
load data local infile "ca2009trim3" into table CASID_trim3All ignore 1 lines;
load data local infile "ca2010trim3All" into table CASID_trim3All ignore 1 lines;

alter table CASID_trim3All add index (YEAR, mergeIdkey, mergePNUM_R, idkey, PNUM_R, DX1, DX1_Chapter, DX1_lv3);

create table CASID_trim3All_noPreg (select * from CASID_trim3All where DX1_Chapter!=11);
alter table CASID_trim3All_noPreg add index (YEAR, mergeIdkey, mergePNUM_R, idkey, PNUM_R, DX1, DX1_Chapter, DX1_lv3);

CREATE TABLE `ICD9CM_umls_name` (
  `CUI` varchar(255) DEFAULT NULL,
  `LAT` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `SAB` varchar(255) DEFAULT NULL,
  `STR` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "umls_ICD9CM_name.txt" into table ICD9CM_umls_name ignore 1 lines;


##query for obesrvation period, no of event by each person
select YEAR, mergePNUM_R, count(distinct mergeIdkey) "no_event", period_diff(max(STR_TO_DATE(concat(AYEAR,"/",AMONTH),'%Y/%m')), min(STR_TO_DATE(concat(AYEAR,"/",AMONTH),'%Y/%m'))) "eventPeriod_Day",  
max(STR_TO_DATE(concat(AYEAR,"/",AMONTH),'%Y/%m')) "latestEvent", min(STR_TO_DATE(concat(AYEAR,"/",AMONTH),'%Y/%m')) "earlistEvent" from CASID_trim3All group by mergePNUM_R;


CREATE TABLE `CASID_trim3All_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_ComDir_HCUP.edge.binomTest" into table CASID_trim3All_ComDir ignore 1 lines;

CREATE TABLE `CASID_trim3All_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,
  `RR_Limit` float DEFAULT NULL,
  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_noPAT_onsetInterval365.0.NRpair.infoAll.binomTest" into table CASID_trim3All_RR ignore 1 lines;
alter table CASID_trim3All_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_trim3All_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_trim3All_RR;
update CASID_trim3All_RR set nor_RRLimit=(RR_Limit-0)/(40281.09375-0);
update CASID_trim3All_RR set nor_RRnoLimit=(RR_noLimit-0)/(40281.09375-0);

CREATE TABLE `CASID_trim3All_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,
  `RR_Limit` float DEFAULT NULL,
  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_trim3All_noPreg_RR ignore 1 lines;
alter table CASID_trim3All_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_trim3All_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_trim3All_noPreg_RR;
update CASID_trim3All_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(35441.69140625-0);
update CASID_trim3All_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0)/(35441.69140625-0);


CREATE TABLE `CASID_trim3All_prevalence` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL,
  `norFreq` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_prevalence.node" into table CASID_trim3Al_prevalence ignore 1 lines;
alter table CASID_trim3All_prevalence add column norFreq double after ICD9cm_freqRatio;
update  CASID_trim3All_prevalence set norFreq=(ICD9cm_freqRatio-0.0000005910843205426627)/(0.0853443007058729-0.0000005910843205426627);


CREATE TABLE `CASID_trim3All_noPreg_prevalence` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` float DEFAULT NULL,
  `norFreq` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_noPreg_prevalence.node" into table CASID_trim3All_noPreg_prevalence ignore 1 lines;


#Asian or Pacific Islander
CREATE TABLE `CASID_tr3AnoPreg_prev` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
alter table CASID_tr3AnoPreg_prev add column norFreq double after ICD9cm_freqRatio;
select min(ICD9cm_freqRatio), max(ICD9cm_freqRatio) from CASID_tr3AnoPreg_prev;
update CASID_tr3AnoPreg_prev set norFreq=(ICD9cm_freqRatio-0.000009252491233264556)/(0.12180904708592788-0.000009252491233264556);

#White
CREATE TABLE `CASID_tr3WnoPreg_prev` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_lv3_noPreg_prevW.node" into table CASID_tr3WnoPreg_prev ignore 1 lines;
alter table CASID_tr3WnoPreg_prev add column norFreq double after ICD9cm_freqRatio;
select min(ICD9cm_freqRatio), max(ICD9cm_freqRatio) from CASID_tr3WnoPreg_prev;
select * from CASID_tr3WnoPreg_prev limit 10;
update CASID_tr3WnoPreg_prev set norFreq=(ICD9cm_freqRatio-0.000001128926830865311)/(0.09599716413580087-0.000001128926830865311);
select * from CASID_tr3WnoPreg_prev limit 10;

#Hispanic
CREATE TABLE `CASID_tr3HnoPreg_prev` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_lv3_noPreg_prevH.node" into table CASID_tr3HnoPreg_prev ignore 1 lines;
alter table CASID_tr3HnoPreg_prev add column norFreq double after ICD9cm_freqRatio;
select min(ICD9cm_freqRatio), max(ICD9cm_freqRatio) from CASID_tr3HnoPreg_prev;
select * from CASID_tr3HnoPreg_prev limit 10;
update CASID_tr3HnoPreg_prev set norFreq=(ICD9cm_freqRatio-0.000003427063520622355)/(0.09124899330009081-0.000003427063520622355);
select * from CASID_tr3HnoPreg_prev limit 10;

#Black
CREATE TABLE `CASID_tr3BnoPreg_prev` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_lv3_noPreg_prevB.node" into table CASID_tr3BnoPreg_prev ignore 1 lines;
alter table CASID_tr3BnoPreg_prev add column norFreq double after ICD9cm_freqRatio;
select min(ICD9cm_freqRatio), max(ICD9cm_freqRatio) from CASID_tr3BnoPreg_prev;
select * from CASID_tr3BnoPreg_prev limit 10;
update CASID_tr3BnoPreg_prev set norFreq=(ICD9cm_freqRatio-0.000008171870786379125)/(0.10263869707692182-0.000008171870786379125);
select * from CASID_tr3BnoPreg_prev limit 10;

#Native Amarican
CREATE TABLE `CASID_tr3NAnoPreg_prev` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_lv3_noPreg_prevNA.node" into table CASID_tr3NAnoPreg_prev ignore 1 lines;
alter table CASID_tr3NAnoPreg_prev add column norFreq double after ICD9cm_freqRatio;
select min(ICD9cm_freqRatio), max(ICD9cm_freqRatio) from CASID_tr3NAnoPreg_prev;
select * from CASID_tr3NAnoPreg_prev limit 10;
update CASID_tr3NAnoPreg_prev set norFreq=(ICD9cm_freqRatio-0.0005790387955993051)/(0.056745801968731906-0.0005790387955993051);
select * from CASID_tr3NAnoPreg_prev limit 10;




CREATE TABLE `CASID_trim3All_noPreg_lv5_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv5_RR_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_trim3All_noPreg_lv5_RR ignore 1 lines;
alter table CASID_trim3All_noPreg_lv5_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_trim3All_noPreg_lv5_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_trim3All_noPreg_lv5_RR;
update CASID_trim3All_noPreg_lv5_RR set nor_RRLimit=(RR_Limit-0)/(1488551-0);
update CASID_trim3All_noPreg_lv5_RR set nor_RRnoLimit=(RR_noLimit-0.005353027489036322)/(1488551-0.005353027489036322);

#for asian and pacific islander only
CREATE TABLE `CASID_tr3A_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_AsPI_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_tr3A_noPreg_RR ignore 1 lines;
alter table CASID_tr3A_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_tr3A_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_tr3A_noPreg_RR;
update CASID_tr3A_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(4323.16015625-0);
update CASID_tr3A_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0.01399952918291092)/(4323.16015625-0.01399952918291092);

#for white
CREATE TABLE `CASID_tr3W_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_W_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_tr3W_noPreg_RR ignore 1 lines;
alter table CASID_tr3W_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_tr3W_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_tr3W_noPreg_RR;
update CASID_tr3W_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(8201.82421875-0);
update CASID_tr3W_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0.003899127710610628)/(8201.82421875-0.003899127710610628);

#for black
CREATE TABLE `CASID_tr3B_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_B_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_tr3B_noPreg_RR ignore 1 lines;
alter table CASID_tr3B_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_tr3B_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_tr3B_noPreg_RR;
update CASID_tr3B_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(12237.099609375-0);
update CASID_tr3B_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0.009598174132406712)/(12237.099609375-0.009598174132406712);


#for hispanic
CREATE TABLE `CASID_tr3H_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_H_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_tr3H_noPreg_RR ignore 1 lines;
alter table CASID_tr3H_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_tr3H_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_tr3H_noPreg_RR;
update CASID_tr3H_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(48632.5-0);
update CASID_tr3H_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0.004802204202860594)/(48632.5-0.004802204202860594);

#for native american
CREATE TABLE `CASID_tr3NA_noPreg_RR` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `noA` int(11) DEFAULT NULL,
  `noB` int(11) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_interval` int(11) DEFAULT NULL,
  `RR_noLimit` float DEFAULT NULL,

  `RR_Limit` float DEFAULT NULL,

  `prob_A` float DEFAULT NULL,
  `prob_B` float DEFAULT NULL,
  `ntrials_unionPAT` int(11) DEFAULT NULL,
  `nsuccesses` int(11) DEFAULT NULL,
  `expected_randomSuccess` float DEFAULT NULL,
  `p_successRandom` float DEFAULT NULL,
  `binomial_pvalue` float DEFAULT NULL,
  `binomial_fdr` float DEFAULT NULL,
  `binomial_bonferroni` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "DX1_lv3_RR_NatAm_noPAT_onsetInterval_noPregSet365.0.NRpair.info.binomTest" into table CASID_tr3NA_noPreg_RR ignore 1 lines;
alter table CASID_tr3NA_noPreg_RR add column nor_RRLimit double after RR_Limit;
alter table CASID_tr3NA_noPreg_RR add column nor_RRnoLimit double after RR_noLimit;
select min(RR_Limit), max(RR_Limit), min(RR_noLimit), max(RR_noLimit) from CASID_tr3NA_noPreg_RR;
update CASID_tr3NA_noPreg_RR set nor_RRLimit=(RR_Limit-0)/(1727-0);
update CASID_tr3NA_noPreg_RR set nor_RRnoLimit=(RR_noLimit-0.26950687170028687)/(1727-0.26950687170028687);

#for asian or pacific islander
CREATE TABLE `CASID_tr3AnoPreg_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_noPreg_AsPI_ComDir_HCUP.edge.binomTest" into table CASID_tr3AnoPreg_ComDir ignore 1 lines;

#for white
CREATE TABLE `CASID_tr3WnoPreg_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_noPreg_W_ComDir_HCUP.edge.binomTest" into table CASID_tr3WnoPreg_ComDir ignore 1 lines;

#for black
CREATE TABLE `CASID_tr3BnoPreg_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_noPreg_B_ComDir_HCUP.edge.binomTest" into table CASID_tr3BnoPreg_ComDir ignore 1 lines;

#for hispanic
CREATE TABLE `CASID_tr3HnoPreg_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_noPreg_H_ComDir_HCUP.edge.binomTest" into table CASID_tr3HnoPreg_ComDir ignore 1 lines;

#for Native American
CREATE TABLE `CASID_tr3NAnoPreg_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv3_noPreg_NatAm_ComDir_HCUP.edge.binomTest" into table CASID_tr3NAnoPreg_ComDir ignore 1 lines;




CREATE TABLE `CASID_trim3All_noPreg_lv5_prevalence` (
  `ICD9cm` varchar(255) DEFAULT NULL,
  `total_N` int(11) DEFAULT NULL,
  `no_ICD9cm` int(11) DEFAULT NULL,
  `ICD9cm_freqRatio` float DEFAULT NULL,
  `norFreq` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9CM_lv5_noPreg_prevalence.node" into table CASID_trim3All_noPreg_lv5_prevalence ignore 1 lines;
alter table CASID_trim3All_noPreg_lv5_prevalence add column norFreq double after ICD9cm_freqRatio;
update CASID_trim3All_noPreg_lv5_prevalence set norFreq=(ICD9cm_freqRatio-0.0000006717942255818343)/(0.06880785524845123-0.0000006717942255818343);

CREATE TABLE `CASID_trim3All_noPreg_lv5_ComDir` (
  `Dis_A` varchar(255) DEFAULT NULL,
  `Dis_B` varchar(255) DEFAULT NULL,
  `commonAll` int(11) DEFAULT NULL,
  `common_dir` int(11) DEFAULT NULL,
  `priorA` int(11) DEFAULT NULL,
  `priorB` int(11) DEFAULT NULL,
  `meanDif` float DEFAULT NULL,
  `minDif` float DEFAULT NULL,
  `maxDif` float DEFAULT NULL,
  `stdDif` float DEFAULT NULL,
  `Dir` float DEFAULT NULL,
  `DirLabel` int(11) DEFAULT NULL,
  `prob_priorA` float DEFAULT NULL,
  `prob_priorB` float DEFAULT NULL,
  `ntrials_unionPAT_direction` int(11) DEFAULT NULL,
  `nsuccesses_priorA` int(11) DEFAULT NULL,
  `nsuccesses_priorB` int(11) DEFAULT NULL,
  `expected_randomSuccessA` float DEFAULT NULL,
  `expected_randomSuccessB` float DEFAULT NULL,
  `binomial_pvalueA2B` float DEFAULT NULL,
  `binomial_fdrA2B` float DEFAULT NULL,
  `binomial_bonferroniA2B` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
load data local infile "ICD9cm_lv5_noPreg_ComDir_HCUP.edge.binomTest" into table CASID_trim3All_noPreg_lv5_ComDir ignore 1 lines;

#made data by RACE information
#White
create table CASID_trim3W_noPreg (select * from CASID_trim3All_noPreg where RACE='1'); 
#Black
create table CASID_trim3B_noPreg (select * from CASID_trim3All_noPreg where RACE='2');
#Hispanic
create table CASID_trim3H_noPreg (select * from CASID_trim3All_noPreg where RACE='3');
#Asian or pacific Islander
create table CASID_trim3A_noPreg (select * from CASID_trim3All_noPreg where RACE='4');
#Native Amarican
create table CASID_trim3NA_noPreg (select * from CASID_trim3All_noPreg where RACE='5');
alter table CASID_trim3W_noPreg add index (`YEAR`,`mergeIdkey`,`mergePNUM_R`,`idkey`,`PNUM_R`,`DX1`,`DX1_Chapter`,`DX1_lv3`);
alter table CASID_trim3B_noPreg add index (`YEAR`,`mergeIdkey`,`mergePNUM_R`,`idkey`,`PNUM_R`,`DX1`,`DX1_Chapter`,`DX1_lv3`);
alter table CASID_trim3H_noPreg add index (`YEAR`,`mergeIdkey`,`mergePNUM_R`,`idkey`,`PNUM_R`,`DX1`,`DX1_Chapter`,`DX1_lv3`);
alter table CASID_trim3A_noPreg add index (`YEAR`,`mergeIdkey`,`mergePNUM_R`,`idkey`,`PNUM_R`,`DX1`,`DX1_Chapter`,`DX1_lv3`);
alter table CASID_trim3NA_noPreg add index (`YEAR`,`mergeIdkey`,`mergePNUM_R`,`idkey`,`PNUM_R`,`DX1`,`DX1_Chapter`,`DX1_lv3`);


