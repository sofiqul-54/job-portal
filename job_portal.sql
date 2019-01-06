-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema job_portal
--

CREATE DATABASE IF NOT EXISTS job_portal;
USE job_portal;

--
-- Definition of table `area`
--

DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `area_id` int(10) unsigned NOT NULL auto_increment,
  `city_id` int(10) unsigned NOT NULL,
  `area_name` varchar(45) NOT NULL,
  `area_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `area`
--

/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` (`area_id`,`city_id`,`area_name`,`area_status`) VALUES 
 (1,1,'dsfd',1),
 (2,1,'Narayangong',1),
 (3,1,'Lalbag',1),
 (4,1,'aaaaaaaaaaaa',1),
 (5,1,'aaaaaaaaaaaa',1);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;


--
-- Definition of table `career_info`
--

DROP TABLE IF EXISTS `career_info`;
CREATE TABLE `career_info` (
  `career_info_id` int(10) unsigned NOT NULL auto_increment,
  `employee_id` int(10) unsigned NOT NULL,
  `career_objective` varchar(500) NOT NULL,
  `present_salary` double NOT NULL,
  `expected_salary` double NOT NULL,
  `looking_for` varchar(45) NOT NULL,
  `available_for` varchar(45) NOT NULL,
  PRIMARY KEY  (`career_info_id`),
  KEY `FK_career_info_emp_id` (`employee_id`),
  CONSTRAINT `FK_career_info_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee_basic_info` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `career_info`
--

/*!40000 ALTER TABLE `career_info` DISABLE KEYS */;
INSERT INTO `career_info` (`career_info_id`,`employee_id`,`career_objective`,`present_salary`,`expected_salary`,`looking_for`,`available_for`) VALUES 
 (1,2,'dsf dfdfgdfg',10000,20000,'Mid','JAN');
/*!40000 ALTER TABLE `career_info` ENABLE KEYS */;


--
-- Definition of table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `city_id` int(10) unsigned NOT NULL auto_increment,
  `state_id` int(10) unsigned NOT NULL,
  `city_name` varchar(45) NOT NULL,
  `city_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`city_id`),
  KEY `FK_city_state_id` (`state_id`),
  CONSTRAINT `FK_city_state_id` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `city`
--

/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`city_id`,`state_id`,`city_name`,`city_status`) VALUES 
 (1,1,'Dhaka',1);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


--
-- Definition of table `company_category`
--

DROP TABLE IF EXISTS `company_category`;
CREATE TABLE `company_category` (
  `com_cat_id` int(10) unsigned NOT NULL auto_increment,
  `com_cat_name` varchar(45) NOT NULL,
  `com_cat_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`com_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_category`
--

/*!40000 ALTER TABLE `company_category` DISABLE KEYS */;
INSERT INTO `company_category` (`com_cat_id`,`com_cat_name`,`com_cat_status`) VALUES 
 (1,'Test3',1),
 (2,'Software Development',1),
 (3,'Test',1),
 (4,'Test2',1),
 (5,'sdsdsd',1),
 (6,'Web designer',1);
/*!40000 ALTER TABLE `company_category` ENABLE KEYS */;


--
-- Definition of table `country`
--

DROP TABLE IF EXISTS `country`;
CREATE TABLE `country` (
  `country_id` int(10) unsigned NOT NULL auto_increment,
  `country_name` varchar(45) NOT NULL,
  `country_code` varchar(10) NOT NULL,
  `country_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `country`
--

/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`country_id`,`country_name`,`country_code`,`country_status`) VALUES 
 (1,'Bangladesh','100',1),
 (2,'India','101',1),
 (3,'Japan','103',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


--
-- Definition of table `education_info`
--

DROP TABLE IF EXISTS `education_info`;
CREATE TABLE `education_info` (
  `edu_info_id` int(10) unsigned NOT NULL auto_increment,
  `employee_id` int(10) unsigned NOT NULL,
  `edu_level` varchar(45) NOT NULL,
  `edu_title` varchar(45) NOT NULL,
  `major_group` varchar(45) NOT NULL,
  `institute_name` varchar(100) NOT NULL,
  `emp_result` varchar(45) NOT NULL,
  `passing_year` varchar(4) NOT NULL,
  `achievement` varchar(100) NOT NULL,
  PRIMARY KEY  (`edu_info_id`),
  KEY `FK_education_info_emp_id` (`employee_id`),
  CONSTRAINT `FK_education_info_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee_basic_info` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `education_info`
--

/*!40000 ALTER TABLE `education_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `education_info` ENABLE KEYS */;


--
-- Definition of table `employee_basic_info`
--

DROP TABLE IF EXISTS `employee_basic_info`;
CREATE TABLE `employee_basic_info` (
  `employee_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `middle_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `dob` date NOT NULL,
  `contact_no` varchar(45) NOT NULL,
  `profile_date` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(500) default NULL,
  `father_name` varchar(45) NOT NULL,
  `mother_name` varchar(45) NOT NULL,
  `marital_status` varchar(45) NOT NULL,
  `nationality` varchar(45) NOT NULL,
  PRIMARY KEY  (`employee_id`),
  KEY `FK_employee_basic_info_user_id` (`user_id`),
  CONSTRAINT `FK_employee_basic_info_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee_basic_info`
--

/*!40000 ALTER TABLE `employee_basic_info` DISABLE KEYS */;
INSERT INTO `employee_basic_info` (`employee_id`,`user_id`,`first_name`,`middle_name`,`last_name`,`gender`,`dob`,`contact_no`,`profile_date`,`email`,`address`,`father_name`,`mother_name`,`marital_status`,`nationality`) VALUES 
 (1,3,'Md.','','Kamal','Male','2015-06-16','01712733365','2015-06-16','info@kamal.com','Dhaka','Md. Akbar Hossain','Tahera Khatun','Married','Bangladeshi'),
 (2,1,'Md.','','Salauddin','Male','1999-06-05','01765435467','2015-06-16','test_test.com','Dhaka','Md. Akbar Hossain','Tahera Khatun','Married','Bangladeshi');
/*!40000 ALTER TABLE `employee_basic_info` ENABLE KEYS */;


--
-- Definition of table `employer`
--

DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer` (
  `employer_id` int(10) unsigned NOT NULL auto_increment,
  `user_id` int(10) unsigned NOT NULL,
  `com_cat_id` int(10) unsigned NOT NULL,
  `area_id` int(10) unsigned NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `contact_person` varchar(45) NOT NULL,
  `contact_email` varchar(45) NOT NULL,
  `company_website` varchar(45) NOT NULL,
  `company_details` varchar(200) NOT NULL,
  `company_phone` varchar(45) NOT NULL,
  `company_fax` varchar(45) NOT NULL,
  `company_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`employer_id`),
  KEY `FK_employer_user_id` (`user_id`),
  KEY `FK_employer_com_cat_id` (`com_cat_id`),
  KEY `FK_employer_area_id` (`area_id`),
  CONSTRAINT `FK_employer_area_id` FOREIGN KEY (`area_id`) REFERENCES `area` (`area_id`),
  CONSTRAINT `FK_employer_com_cat_id` FOREIGN KEY (`com_cat_id`) REFERENCES `company_category` (`com_cat_id`),
  CONSTRAINT `FK_employer_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employer`
--

/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` (`employer_id`,`user_id`,`com_cat_id`,`area_id`,`company_name`,`contact_person`,`contact_email`,`company_website`,`company_details`,`company_phone`,`company_fax`,`company_status`) VALUES 
 (1,2,1,1,'The Computers Ltd.','A Bari','tcl_it@yahoo.com','www.tclbd.com','Software company','8319091','8319091',1);
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;


--
-- Definition of table `employment_history`
--

DROP TABLE IF EXISTS `employment_history`;
CREATE TABLE `employment_history` (
  `emp_history_id` int(10) unsigned NOT NULL auto_increment,
  `employee_id` int(10) unsigned NOT NULL,
  `company_name` varchar(45) NOT NULL,
  `com_cat_id` int(10) unsigned NOT NULL,
  `company_location` varchar(100) NOT NULL,
  `department` varchar(45) NOT NULL,
  `position_held` varchar(45) NOT NULL,
  `area_of_exp` varchar(200) NOT NULL,
  `responsibilities` varchar(300) NOT NULL,
  `form_date` date NOT NULL,
  `to_date` varchar(45) NOT NULL,
  PRIMARY KEY  (`emp_history_id`),
  KEY `FK_employment_history_emp_id` (`employee_id`),
  KEY `FK_employment_history_com_cat_id` (`com_cat_id`),
  CONSTRAINT `FK_employment_history_com_cat_id` FOREIGN KEY (`com_cat_id`) REFERENCES `company_category` (`com_cat_id`),
  CONSTRAINT `FK_employment_history_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee_basic_info` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employment_history`
--

/*!40000 ALTER TABLE `employment_history` DISABLE KEYS */;
INSERT INTO `employment_history` (`emp_history_id`,`employee_id`,`company_name`,`com_cat_id`,`company_location`,`department`,`position_held`,`area_of_exp`,`responsibilities`,`form_date`,`to_date`) VALUES 
 (1,1,'The Computers Ltd.',2,'Dhaka','IT','Software Engineer','Java, MySQL','Software Development','2008-12-17','Continuing'),
 (2,1,'The XXX Ltd.',1,'Dhaka','IT','Web Developer','Java, MySQL','Software Development','2008-12-17','2015-06-18');
/*!40000 ALTER TABLE `employment_history` ENABLE KEYS */;


--
-- Definition of table `job_applied`
--

DROP TABLE IF EXISTS `job_applied`;
CREATE TABLE `job_applied` (
  `applied_id` int(10) unsigned NOT NULL auto_increment,
  `employee_id` int(10) unsigned NOT NULL,
  `job_id` int(10) unsigned NOT NULL,
  `cover_letter` varchar(1000) NOT NULL,
  `exp_salary` double NOT NULL,
  `apply_status` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`applied_id`),
  KEY `FK_job_applied_emp_id` (`employee_id`),
  KEY `FK_job_applied_job_id` (`job_id`),
  CONSTRAINT `FK_job_applied_emp_id` FOREIGN KEY (`employee_id`) REFERENCES `employee_basic_info` (`employee_id`),
  CONSTRAINT `FK_job_applied_job_id` FOREIGN KEY (`job_id`) REFERENCES `job_post` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_applied`
--

/*!40000 ALTER TABLE `job_applied` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_applied` ENABLE KEYS */;


--
-- Definition of table `job_category`
--

DROP TABLE IF EXISTS `job_category`;
CREATE TABLE `job_category` (
  `job_cat_id` int(10) unsigned NOT NULL auto_increment,
  `job_cat_name` varchar(45) NOT NULL,
  `job_cat_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`job_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_category`
--

/*!40000 ALTER TABLE `job_category` DISABLE KEYS */;
INSERT INTO `job_category` (`job_cat_id`,`job_cat_name`,`job_cat_status`) VALUES 
 (1,'Software Engineer',1);
/*!40000 ALTER TABLE `job_category` ENABLE KEYS */;


--
-- Definition of table `job_post`
--

DROP TABLE IF EXISTS `job_post`;
CREATE TABLE `job_post` (
  `job_id` int(10) unsigned NOT NULL auto_increment,
  `employer_id` int(10) unsigned NOT NULL,
  `job_cat_id` int(10) unsigned NOT NULL,
  `job_title` varchar(45) NOT NULL,
  `job_location` varchar(45) NOT NULL,
  `no_vacancy` int(10) unsigned NOT NULL default '1',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `expr_date` date NOT NULL,
  `skills_req` varchar(1000) NOT NULL,
  `edu_req` varchar(500) NOT NULL,
  `basic_req` varchar(1000) NOT NULL,
  `salary_given` varchar(45) NOT NULL,
  `job_post_status` int(10) unsigned NOT NULL default '1',
  `job_nature` varchar(45) default NULL,
  `experience_required` varchar(100) default NULL,
  `other_benefit` varchar(500) default NULL,
  `age_limit` varchar(45) default NULL,
  PRIMARY KEY  (`job_id`),
  KEY `FK_job_post_employer_id` (`employer_id`),
  KEY `FK_job_post_job_cat_id` (`job_cat_id`),
  CONSTRAINT `FK_job_post_employer_id` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`employer_id`),
  CONSTRAINT `FK_job_post_job_cat_id` FOREIGN KEY (`job_cat_id`) REFERENCES `job_category` (`job_cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `job_post`
--

/*!40000 ALTER TABLE `job_post` DISABLE KEYS */;
INSERT INTO `job_post` (`job_id`,`employer_id`,`job_cat_id`,`job_title`,`job_location`,`no_vacancy`,`start_date`,`end_date`,`expr_date`,`skills_req`,`edu_req`,`basic_req`,`salary_given`,`job_post_status`,`job_nature`,`experience_required`,`other_benefit`,`age_limit`) VALUES 
 (1,1,1,'programmer','dhaka',2,'2015-05-31','2015-06-18','2015-06-30','dgfdfg','sfdsdf','gdfdg','8900',1,NULL,NULL,NULL,NULL),
 (2,1,1,'y','dhaka',0,'2015-05-31','2015-05-31','2015-05-31','','','','',1,NULL,NULL,NULL,NULL),
 (3,1,1,'y','h',0,'2015-05-31','2015-05-31','2015-05-31','vgh','hghj','gfh','8789',1,NULL,NULL,NULL,NULL),
 (4,1,1,'Assistant Systems Engineer','Dhaka',2,'2015-06-09','2015-06-30','2015-06-30','Age 22 to 30 year(s)<br>\r\nMust have aptitude to buildup career in ICT support<br>\r\nShould have ability of extensive tour and provide support to customer throughout Bangladesh','B.Sc or Diploma in Computer Science','Basic knowledge on Microsoft Operating System<br>\r\nBasic knowledge about Computer Accessories','Negotiable',1,NULL,NULL,NULL,NULL),
 (5,1,1,'Senior Java/ Web Developer','Dhaka',11,'2015-06-01','2015-06-30','2015-06-30','2 to 5 year(s)<br>\r\nThe applicants should have experience in the following area(s):\r\nWeb Developer/Web Designer','University Graduate','Work as Senior Java/Web Developer under High experienced Japanese Project Manager.\r\nMainly, will be assigned to BtoC Web Service development project using OSS and latest Web technologies.\r\nResponsible to not only the assigned deliverable but also schedule/quality control of other members in your team are required.','Tk. 40000 - 70000',1,NULL,NULL,NULL,NULL),
 (6,1,1,'aaaaaaaaaaa','bbbbbbbb',3,'2015-06-15','2015-06-15','2015-06-15','cvcxvcx','dsfds','hello<div>naht','vcv',1,NULL,NULL,NULL,NULL),
 (7,1,1,'Test one','Dhaka',3,'2015-06-15','2015-06-15','2015-06-15','cvcxvcx','dsfds','hello<div><span style=\"font-weight: bold;\">naht</span></div><div style=\"font-weight: normal;\">demn</div>','negotiable',1,NULL,NULL,NULL,NULL),
 (8,1,1,'IT Executive','Dhaka',3,'2015-06-01','2015-06-30','2015-06-30','<ul style=\"margin: 5px 0px 0px; color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\"><li style=\"margin-bottom: 8px;\">Age 25 to 30 year(s)</li><li style=\"margin-bottom: 8px;\">3 to 4 years experience in similar field</li><li style=\"margin-bottom: 8px;\">Well knowledge in MS Office package, especially MS Word, MS Excel and Power Point.</li><li style=\"margin-bottom: 8px;\">Proficiency (both written and spoken) in English and Bangla are Compulsory</li><li style=\"margin-bottom: 8px;\">Smart, presentable, pro-active, independent and result oriented</li><li style=\"margin-bottom: 8px;\">Able to perform multi-tasking</li><li style=\"margin-bottom: 8px;\">Able and willing to work under pressure</li></ul>','<ul style=\"margin: 5px 0px 0px; color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\"><li style=\"margin-bottom: 8px;\">Minimum Graduate</li><li style=\"margin-bottom: 8px;\">Diploma certified with basic computer knowledge will get preferred</li></ul>','<ul style=\"margin: 5px 0px 0px; color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\"><li style=\"margin-bottom: 8px;\">Design and develop dynamic web applications, websites, social media contents</li><li style=\"margin-bottom: 8px;\">Create and develop creative graphics design concepts</li><li style=\"margin-bottom: 8px;\">Maintain and configure server &amp; network infrastructure components, wireless infrastructure, Hardware Maintenance &amp; Trouble Shooting and User &amp; Bandwidth Management on Router</li><li style=\"margin-bottom: 8px;\">Monitor LAN/WAN against prepared baseline and take necessary preventive and/or corrective measures for any unusual activities.</li></ul>','negotiable',1,NULL,NULL,NULL,NULL),
 (9,1,1,'Software QA Engineer','Dhaka',2,'2015-06-01','2015-06-30','2015-06-30','<ul style=\"margin: 5px 0px 0px; color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\"><li style=\"margin-bottom: 8px;\">Must have strong knowledge of C++ or C#</li><li style=\"margin-bottom: 8px;\">Must be familiar with any relational database like Microsoft SQL Server, MySQL, Oracle, etc.</li><li style=\"margin-bottom: 8px;\">Must have strong knowledge of SQL</li><li style=\"margin-bottom: 8px;\">Must be familiar with any IDE like Microsoft Visual Studio, Eclipse, Netbeans, etc.</li><li style=\"margin-bottom: 8px;\">Must be familiar with HTML and CSS</li><li style=\"margin-bottom: 8px;\">Must have good knowledge of object oriented software development</li><li style=\"margin-bottom: 8px;\">Familiarity with client-side programming including JavaScript, AJAX and jQuery is preferred</li></ul>','<ul><li><span style=\"color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\">B.Sc or M.Sc in Computer Science/ Engineering or equivalent.</span></li></ul>','<ul style=\"margin: 5px 0px 0px; color: rgb(51, 51, 51); font-family: Arial, Helvetica, sans-serif, solaimanLipi; font-size: 12px; background-color: rgb(255, 255, 255);\"><li style=\"margin-bottom: 8px;\">Plan and execute test cases for application features</li><li style=\"margin-bottom: 8px;\">Develop and document test configurations and test environments</li><li style=\"margin-bottom: 8px;\">Review the production code and write down test codes to verify functionality</li><li style=\"margin-bottom: 8px;\">Measure application performance and suggest improvements</li><li style=\"margin-bottom: 8px;\">Explore application requirements and post relevant queries/suggestions</li><li style=\"margin-bottom: 8px;\">Prepare and maintain test documentation suite that includes test scripts, feature workflow diagrams, checklists, test reports, release note and system support documentation</li></ul>','Negotiable',1,'Full Time','3 to 4 year(s)','<div><font color=\"#333333\" face=\"Arial, Helvetica, sans-serif, solaimanLipi\"><span style=\"font-size: 12px;\">As per Company Policy</span></font></div>','At most 30 year(s)'),
 (10,1,1,'Senior software engineer','Dhaka',2,'2017-02-04','2017-03-04','2017-03-05','<span style=\"color: rgb(34, 34, 34); font-family: Raleway, HelveticaNeue, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 15px; font-weight: 600;\">Skill Required</span>','<span style=\"color: rgb(34, 34, 34); font-family: Raleway, HelveticaNeue, &quot;Helvetica Neue&quot;, Helvetica, Arial, sans-serif; font-size: 15px; font-weight: 600;\">Educational Requirements</span>','Basic skill','100000-150000',1,'Full Time','4','','30-40');
/*!40000 ALTER TABLE `job_post` ENABLE KEYS */;


--
-- Definition of table `seq_questions`
--

DROP TABLE IF EXISTS `seq_questions`;
CREATE TABLE `seq_questions` (
  `que_id` int(10) unsigned NOT NULL auto_increment,
  `question` varchar(50) NOT NULL,
  PRIMARY KEY  (`que_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `seq_questions`
--

/*!40000 ALTER TABLE `seq_questions` DISABLE KEYS */;
INSERT INTO `seq_questions` (`que_id`,`question`) VALUES 
 (1,'What is your first school name?'),
 (2,'Which city you born?'),
 (3,'What is your first job?');
/*!40000 ALTER TABLE `seq_questions` ENABLE KEYS */;


--
-- Definition of table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int(10) unsigned NOT NULL auto_increment,
  `country_id` int(10) unsigned NOT NULL,
  `state_name` varchar(45) NOT NULL,
  `state_code` varchar(10) NOT NULL,
  `state_status` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`state_id`),
  KEY `FK_state_country_id` (`country_id`),
  CONSTRAINT `FK_state_country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `state`
--

/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` (`state_id`,`country_id`,`state_name`,`state_code`,`state_status`) VALUES 
 (1,1,'Dhaka','100',1),
 (3,2,'sdsad','21',1),
 (4,2,'dfsf','3231',1);
/*!40000 ALTER TABLE `state` ENABLE KEYS */;


--
-- Definition of table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(10) unsigned NOT NULL auto_increment,
  `role_id` int(10) unsigned NOT NULL,
  `uname` varchar(20) NOT NULL,
  `pass` varchar(45) NOT NULL,
  `que_id` int(10) unsigned NOT NULL,
  `sec_answer` varchar(45) NOT NULL,
  `reg_date` date NOT NULL,
  `isactive` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`user_id`),
  KEY `FK_user_info_role_id` (`role_id`),
  KEY `FK_user_info_que_id` (`que_id`),
  CONSTRAINT `FK_user_info_que_id` FOREIGN KEY (`que_id`) REFERENCES `seq_questions` (`que_id`),
  CONSTRAINT `FK_user_info_role_id` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`user_id`,`role_id`,`uname`,`pass`,`que_id`,`sec_answer`,`reg_date`,`isactive`) VALUES 
 (1,1,'admin','123456',1,'aaa','2015-05-30',1),
 (2,2,'employer1','123456',1,'bbb','2015-05-30',1),
 (3,3,'employee1','123456',2,'ccc','2015-05-30',1),
 (4,2,'employer2','123456',1,'Lab','2015-06-18',1);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


--
-- Definition of table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(10) unsigned NOT NULL auto_increment,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`role_id`,`role_name`) VALUES 
 (1,'admin'),
 (2,'employer'),
 (3,'employee'),
 (4,'DBA');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
