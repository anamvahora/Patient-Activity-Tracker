-- ======================================================
-- 1. DATABASE SETUP
-- ======================================================
DROP DATABASE IF EXISTS medical;
CREATE DATABASE medical;
USE medical;

-- ======================================================
-- 2. TABLE CREATION
-- ======================================================

-- Patient Table
CREATE TABLE `patienttable` (
  `PatientID` int(11) NOT NULL AUTO_INCREMENT,
  `PtLastName` varchar(128) DEFAULT NULL,
  `PtPreviousLastName` varchar(128) DEFAULT NULL,
  `PtFirstName` varchar(128) DEFAULT NULL,
  `HomeAddress1` varchar(128) DEFAULT NULL,
  `HomeCity` varchar(128) DEFAULT NULL,
  `HomeState/Province/Region` varchar(50) DEFAULT NULL,
  `HomeZip` varchar(15) DEFAULT NULL,
  `Country` varchar(75) DEFAULT NULL,
  `Citizenship` varchar(75) DEFAULT NULL,
  `PtMobilePhone` varchar(14) DEFAULT NULL,
  `EmergencyPhoneNumber` varchar(14) DEFAULT NULL,
  `EmailAddress` varchar(128) DEFAULT NULL,
  `PtSS#` varchar(12) DEFAULT NULL,
  `DOB` datetime DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `EthnicAssociation` varchar(75) DEFAULT NULL,
  `MaritalStatus` varchar(25) DEFAULT NULL,
  `CurrentPrimaryHCP` varchar(128) DEFAULT NULL,
  `Comments` varchar(254) DEFAULT NULL,
  `NextOfKin` varchar(128) DEFAULT NULL,
  `NextOfKinRelationshipToPatient` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`PatientID`),
  KEY `I_PtLastFirstName` (`PtLastName`,`PtFirstName`),
  KEY `I_SSN` (`PtSS#`)
); 

-- General Medical History Table
CREATE TABLE `generalmedicalhistorytable` (
  `GeneralMedicalHistoryID` int(11) NOT NULL AUTO_INCREMENT,
  `PatientID` int(11) DEFAULT NULL,
  `Tobacco` varchar(50) DEFAULT NULL,
  `TobaccoQuantity` varchar(75) DEFAULT NULL,
  `Tobaccoduraton` varchar(75) DEFAULT NULL,
  `Alcohol` varchar(50) DEFAULT NULL,
  `AlcoholQuantity` varchar(75) DEFAULT NULL,
  `Alcoholduration` varchar(75) DEFAULT NULL,
  `Drug` varchar(25) DEFAULT NULL,
  `DrugType` varchar(254) DEFAULT NULL,
  `Drugduration` varchar(75) DEFAULT NULL,
  `BloodType` varchar(10) DEFAULT NULL,
  `Rh` varchar(10) DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`GeneralMedicalHistoryID`),
  -- CRITICAL: This Unique Key allows the Upsert SP to update instead of duplicate
  UNIQUE KEY `Unique_PatientID` (`PatientID`), 
  CONSTRAINT `fk_patient` FOREIGN KEY (`PatientID`) REFERENCES `patienttable` (`PatientID`) ON DELETE CASCADE
);

-- =========================
-- SHORTNESS OF BREATH 
-- =========================
CREATE TABLE sobtable (
  SOBID INT AUTO_INCREMENT PRIMARY KEY,
  PatientID INT,
  SOBDate DATE,
  Severity VARCHAR(10),
  Notes VARCHAR(255),
  FOREIGN KEY (PatientID) REFERENCES patienttable(PatientID)
);

-- =========================
-- ACTIVITY TOLERANCE 
-- =========================
CREATE TABLE activitytable (
  ActivityID INT AUTO_INCREMENT PRIMARY KEY,
  PatientID INT,
  ActivityDate DATE,
  ActivityName VARCHAR(100),
  Difficulty VARCHAR(50),
  FOREIGN KEY (PatientID) REFERENCES patienttable(PatientID)
);


-- ======================================================
-- 3. STORED PROCEDURES (GENERAL MEDICAL HISTORY)
-- ======================================================
DELIMITER //

CREATE PROCEDURE GetGeneralMedicalHistorySP(IN p_PID INT)
BEGIN
    SELECT * FROM generalmedicalhistorytable 
    WHERE PatientID = p_PID AND deleted = 0;
END //

CREATE PROCEDURE UpsertGeneralMedicalHistorySP(
    IN p_PID INT,
    IN p_Tobacco VARCHAR(50),
    IN p_TobaccoQty VARCHAR(75),
    IN p_TobaccoDur VARCHAR(75), -- Added
    IN p_Alcohol VARCHAR(50),
    IN p_AlcoholQty VARCHAR(75),
    IN p_AlcoholDur VARCHAR(75), -- Added
    IN p_Drug VARCHAR(25),       -- Added (Yes/No)
    IN p_DrugType VARCHAR(254),
    IN p_DrugDur VARCHAR(75),    -- Added
    IN p_BloodType VARCHAR(10),
    IN p_Rh VARCHAR(10)
)
BEGIN
    INSERT INTO generalmedicalhistorytable (
        PatientID, Tobacco, TobaccoQuantity, Tobaccoduraton, 
        Alcohol, AlcoholQuantity, Alcoholduration, 
        Drug, DrugType, Drugduration, 
        BloodType, Rh
    )
    VALUES (
        p_PID, p_Tobacco, p_TobaccoQty, p_TobaccoDur, 
        p_Alcohol, p_AlcoholQty, p_AlcoholDur, 
        p_Drug, p_DrugType, p_DrugDur, 
        p_BloodType, p_Rh
    )
    ON DUPLICATE KEY UPDATE
        Tobacco = p_Tobacco,
        TobaccoQuantity = p_TobaccoQty,
        Tobaccoduraton = p_TobaccoDur,
        Alcohol = p_Alcohol,
        AlcoholQuantity = p_AlcoholQty,
        Alcoholduration = p_AlcoholDur,
        Drug = p_Drug,
        DrugType = p_DrugType,
        Drugduration = p_DrugDur,
        BloodType = p_BloodType,
        Rh = p_Rh;
END //

CREATE PROCEDURE DeleteGeneralMedicalHistorySP(IN p_PID INT)
BEGIN
    UPDATE generalmedicalhistorytable SET deleted = 1 WHERE PatientID = p_PID;
END //

-- ======================================================
-- 4. STORED PROCEDURES (PATIENT DEMOGRAPHICS)
-- ======================================================

CREATE PROCEDURE GetPatientbyID(IN p_PID INT)
BEGIN
    SELECT * FROM patienttable WHERE PatientID = p_PID;
END //

CREATE PROCEDURE InsertPatientSP(
    IN p_LName VARCHAR(128), IN p_PrevLName VARCHAR(128), IN p_FName VARCHAR(128),
    IN p_Addr1 VARCHAR(128), IN p_City VARCHAR(128), IN p_State VARCHAR(50),
    IN p_Zip VARCHAR(15), IN p_Country VARCHAR(75), IN p_Citizen VARCHAR(75),
    IN p_Mobile VARCHAR(14), IN p_EmergPhone VARCHAR(14), IN p_Email VARCHAR(128),
    IN p_SSN VARCHAR(12), IN p_DOB DATETIME, IN p_Gender VARCHAR(50),
    IN p_Ethnic VARCHAR(75), IN p_Marital VARCHAR(25), IN p_HCP VARCHAR(128),
    IN p_Comments VARCHAR(254), IN p_NOK VARCHAR(128), IN p_NOKRel VARCHAR(50)
)
BEGIN
    INSERT INTO patienttable (
        PtLastName, PtPreviousLastName, PtFirstName, HomeAddress1, HomeCity, 
        `HomeState/Province/Region`, HomeZip, Country, Citizenship, PtMobilePhone, 
        EmergencyPhoneNumber, EmailAddress, `PtSS#`, DOB, Gender, 
        EthnicAssociation, MaritalStatus, CurrentPrimaryHCP, Comments, NextOfKin, 
        NextOfKinRelationshipToPatient
    )
    VALUES (
        p_LName, p_PrevLName, p_FName, p_Addr1, p_City, p_State, p_Zip, p_Country, 
        p_Citizen, p_Mobile, p_EmergPhone, p_Email, p_SSN, p_DOB, p_Gender, 
        p_Ethnic, p_Marital, p_HCP, p_Comments, p_NOK, p_NOKRel
    );
END //

CREATE PROCEDURE UpdatePatientSP(
    IN p_PID INT,
    IN p_LName VARCHAR(128), IN p_PrevLName VARCHAR(128), IN p_FName VARCHAR(128),
    IN p_Addr1 VARCHAR(128), IN p_City VARCHAR(128), IN p_State VARCHAR(50),
    IN p_Zip VARCHAR(15), IN p_Country VARCHAR(75), IN p_Citizen VARCHAR(75),
    IN p_Mobile VARCHAR(14), IN p_EmergPhone VARCHAR(14), IN p_Email VARCHAR(128),
    IN p_SSN VARCHAR(12), IN p_DOB DATETIME, IN p_Gender VARCHAR(50),
    IN p_Ethnic VARCHAR(75), IN p_Marital VARCHAR(25), IN p_HCP VARCHAR(128),
    IN p_Comments VARCHAR(254), IN p_NOK VARCHAR(128), IN p_NOKRel VARCHAR(50)
)
BEGIN
    UPDATE patienttable SET
        PtLastName = p_LName, PtPreviousLastName = p_PrevLName, PtFirstName = p_FName,
        HomeAddress1 = p_Addr1, HomeCity = p_City, `HomeState/Province/Region` = p_State,
        HomeZip = p_Zip, Country = p_Country, Citizenship = p_Citizen,
        PtMobilePhone = p_Mobile, EmergencyPhoneNumber = p_EmergPhone, EmailAddress = p_Email,
        `PtSS#` = p_SSN, DOB = p_DOB, Gender = p_Gender, EthnicAssociation = p_Ethnic,
        MaritalStatus = p_Marital, CurrentPrimaryHCP = p_HCP, Comments = p_Comments,
        NextOfKin = p_NOK, NextOfKinRelationshipToPatient = p_NOKRel
    WHERE PatientID = p_PID;
END //

CREATE PROCEDURE DeletePatientSP(IN p_PID INT)
BEGIN
    DELETE FROM patienttable WHERE PatientID = p_PID;
END //

-- ======================================================
-- STORED PROCEDURES (SHORTNESS OF BREATH)
-- ======================================================

CREATE PROCEDURE GetSOBByPatientID(IN p_PID INT)
BEGIN
    SELECT * FROM sobtable WHERE PatientID = p_PID;
END //

CREATE PROCEDURE InsertSOBSP(
    IN p_PID INT,
    IN p_Date DATE,
    IN p_Severity VARCHAR(10),
    IN p_Notes VARCHAR(255)
)
BEGIN
    INSERT INTO sobtable (PatientID, SOBDate, Severity, Notes)
    VALUES (p_PID, p_Date, p_Severity, p_Notes);
END //

CREATE PROCEDURE DeleteSOBSP(IN p_SOBID INT)
BEGIN
    DELETE FROM sobtable WHERE SOBID = p_SOBID;
END //
-- ======================================================
-- STORED PROCEDURES (ACTIVITY TOLERANCE)
-- ======================================================

CREATE PROCEDURE GetActivityByPatientID(IN p_PID INT)
BEGIN
    SELECT * FROM activitytable WHERE PatientID = p_PID;
END //

CREATE PROCEDURE InsertActivitySP(
    IN p_PID INT,
    IN p_Date DATE,
    IN p_Name VARCHAR(100),
    IN p_Difficulty VARCHAR(50)
)
BEGIN
    INSERT INTO activitytable (PatientID, ActivityDate, ActivityName, Difficulty)
    VALUES (p_PID, p_Date, p_Name, p_Difficulty);
END //

CREATE PROCEDURE DeleteActivitySP(IN p_ActivityID INT)
BEGIN
    DELETE FROM activitytable WHERE ActivityID = p_ActivityID;
END //

DELIMITER ;

-- ======================================================
-- 5. SAMPLE DATA (SAILOR MOON)
-- ======================================================
INSERT INTO `patienttable` (`PtFirstName`, `PtLastName`, `HomeCity`, `DOB`, `Gender`, `CurrentPrimaryHCP`, `Comments`)
VALUES 
('Usagi', 'Tsukino', 'Azabu-Juuban', '2004-06-30', 'Female', 'Dr. Mamoru Chiba', 'Patient complains of chronic sleepiness and frequent crying.'),
('Ami', 'Mizuno', 'Azabu-Juuban', '2004-09-10', 'Female', 'Dr. Mizuno (Mother)', 'Extremely high IQ; patient maintains a very healthy lifestyle.'),
('Rei', 'Hino', 'Azabu-Juuban', '2004-04-17', 'Female', 'Grandpa Hino', 'High stress levels due to shrine duties; exhibits fire-related incidents.'),
('Makoto', 'Kino', 'Azabu-Juuban', '2004-12-05', 'Female', 'Dr. Itoh', 'Very athletic; high physical strength; minor electrical sensitivity.'),
('Minako', 'Aino', 'Azabu-Juuban', '2004-10-22', 'Female', 'Artemis Health', 'Highly active; former career in London; idol aspirations.');

INSERT INTO `generalmedicalhistorytable` (`PatientID`, `Tobacco`, `Alcohol`, `BloodType`, `Rh`, `DrugType`)
VALUES 
(1, 'No', 'No', 'O', '+', 'None'),
(2, 'No', 'No', 'A', '+', 'None'),
(3, 'No', 'No', 'AB', '+', 'None'),
(4, 'No', 'No', 'O', '+', 'None'),
(5, 'No', 'No', 'B', '+', 'None');
-- ======================================================
-- 6. YOUR SAMPLE DATA (THEMED TO MATCH PARTNER)
-- ======================================================

-- Shortness of Breath Entries for IDs 1-5
INSERT INTO sobtable (PatientID, SOBDate, Severity, Notes)
VALUES 
(1, '2026-04-10', 'Mild', 'Occurs after running to school late.'),
(2, '2026-04-12', 'None', 'Excellent respiratory health.'),
(3, '2026-04-14', 'Moderate', 'Shortness of breath after intensive meditation near fire.'),
(4, '2026-04-15', 'Mild', 'Occurs after heavy physical training sessions.'),
(5, '2026-04-16', 'None', 'No issues reported during idol dance practice.');

-- Activity Tolerance Entries for IDs 1-5
INSERT INTO activitytable (PatientID, ActivityDate, ActivityName, Difficulty)
VALUES 
(1, '2026-04-10', 'Running to School', 'Hard'),
(2, '2026-04-12', 'Swimming Laps', 'Easy'),
(3, '2026-04-14', 'Archery Practice', 'Moderate'),
(4, '2026-04-15', 'Weightlifting', 'Easy'),
(5, '2026-04-16', 'Dance Rehearsal', 'Moderate');

-- Verification Selects
SELECT * FROM sobtable;
SELECT * FROM activitytable;