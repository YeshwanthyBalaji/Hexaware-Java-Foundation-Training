CREATE DATABASE CrimeManage;
USE CrimeManage;

-- Creating tables
CREATE TABLE Crime ( 
    CrimeID INT PRIMARY KEY AUTO_INCREMENT, 
    IncidentType VARCHAR(255), 
    IncidentDate DATE, 
    Location VARCHAR(255), 
    Description TEXT, 
    Status VARCHAR(20) 
);

CREATE TABLE Victim ( 
    VictimID INT PRIMARY KEY AUTO_INCREMENT, 
    CrimeID INT, 
    Name VARCHAR(255), 
    ContactInfo VARCHAR(255), 
    Injuries VARCHAR(255), 
    FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID) 
);

CREATE TABLE Suspect ( 
    SuspectID INT PRIMARY KEY AUTO_INCREMENT, 
    CrimeID INT, 
    Name VARCHAR(255), 
    Description TEXT, 
    CriminalHistory TEXT, 
    FOREIGN KEY (CrimeID) REFERENCES Crime(CrimeID) 
);

-- Inserting samples
INSERT INTO Crime (CrimeID, IncidentType, IncidentDate, Location, Description, Status) VALUES 
    (1, 'Robbery', '2023-09-15', '123 Main St, Cityville', 'Armed robbery at a convenience store', 'Open'), 
    (2, 'Homicide', '2023-09-20', '456 Elm St, Townsville', 'Investigation into a murder case', 'Under Investigation'), 
    (3, 'Theft', '2023-09-10', '789 Oak St, Villagetown', 'Shoplifting incident at a mall', 'Closed'); 
    
INSERT INTO Victim (VictimID, CrimeID, Name, ContactInfo, Injuries) VALUES 
    (1, 1, 'John Doe', 'johndoe@example.com', 'Minor injuries'), 
    (2, 2, 'Jane Smith', 'janesmith@example.com', 'Deceased'),
    (3, 3, 'Alice Johnson', 'alicejohnson@example.com', 'None');

INSERT INTO Suspect (SuspectID, CrimeID, Name, Description, CriminalHistory) VALUES 
    (1, 1, 'Robber 1', 'Armed and masked robber', 'Previous robbery convictions'), 
    (2, 2, 'Unknown', 'Investigation ongoing', NULL), 
    (3, 3, 'Suspect 1', 'Shoplifting suspect', 'Prior shoplifting arrests'); 

-- Queries
-- 1.Select all open incidents. 
SELECT * FROM Crime
WHERE Status = 'Open';

-- 2.Find the total number of incidents. 
SELECT COUNT(CrimeID) as total_incidents
FROM Crime;

-- 3.List all unique incident types. 
SELECT DISTINCT IncidentType FROM Crime;

-- 4.Retrieve incidents that occurred between '2023-09-01' and '2023-09-10'. 
SELECT * FROM Crime
WHERE IncidentDate BETWEEN '2023-09-01' AND '2023-09-10';

-- 5.List persons involved in incidents in descending order of age. 
ALTER TABLE Victim ADD Age INT;
ALTER TABLE Suspect ADD Age INT;

UPDATE Victim SET Age = 34 WHERE VictimID = 1;
UPDATE Victim SET Age = 28 WHERE VictimID = 2;
UPDATE Victim SET Age = 22 WHERE VictimID = 3;
UPDATE Suspect SET Age = 30 WHERE SuspectID = 1;
UPDATE Suspect SET Age = 40 WHERE SuspectID = 2;
UPDATE Suspect SET Age = 25 WHERE SuspectID = 3;

SELECT Name, Age
FROM Victim
UNION
SELECT Name, Age
FROM Suspect
ORDER BY Age DESC;

-- 6. Find the average age of persons involved in incidents.
SELECT AVG(Age) AS avg_age
FROM (
    SELECT Age FROM Victim
    UNION 
    SELECT Age FROM Suspect
) AS all_age;

 -- 7.List incident types and their counts, only for open cases. 
SELECT 
    IncidentType,
    COUNT(CrimeID) AS Countt
FROM Crime
WHERE Status = 'Open'
GROUP BY IncidentType;
    
-- 8.Find persons with names containing 'Doe'. 
SELECT * FROM Victim
WHERE Name LIKE '%Doe%';
    
-- 9.Retrieve the names of persons involved in open cases and closed cases.
SELECT Victim.Name, Crime.Status
FROM Victim
JOIN Crime ON Crime.CrimeID = Victim.CrimeId
WHERE Crime.Status IN ('Open','Closed');
    
-- 10. List incident types where there are persons aged 30 or 35 involved. 
SELECT 
	  Crime.CrimeID,
	  Crime.IncidentType
From Crime
JOIN Victim ON Crime.CrimeID = Victim.CrimeID
WHERE Age IN (30, 35);
    
-- 11. Find persons involved in incidents of the same type as 'Robbery'.
SELECT 
		Victim.Name
FROM Victim
JOIN Crime ON Victim.CrimeID = Crime.CrimeID
WHERE IncidentType = 'Robbery';
    
-- 12. List incident types with more than one open case. 
SELECT IncidentType, COUNT(*) AS OpenCaseCount
FROM Crime
WHERE Status = 'Open'
GROUP BY IncidentType
HAVING COUNT(*) > 1;
    
-- 13.  List all incidents with suspects whose names also appear as victims in other incidents.
SELECT DISTINCT 
    C1.IncidentType, 
    C1.IncidentDate, 
    C1.Location, 
    C1.Description, 
    S1.Name AS SuspectName
FROM Crime C1
JOIN Suspect S1 ON C1.CrimeID = S1.CrimeID
WHERE S1.Name IN (
    SELECT V1.Name
    FROM Victim V1
    AND V1.CrimeID != C1.CrimeID
);

-- 14.Retrieve all incidents along with victim and suspect details. 
SELECT 
    Crime.CrimeID,
    Crime.IncidentType,
    Crime.IncidentDate,
    Crime.Location,
    Crime.Description,
    Crime.Status,
    Victim.Name AS VictimName,
    Victim.ContactInfo AS VictimContact,
    Victim.Injuries AS VictimInjury,
    Suspect.Name AS SuspectName,
    Suspect.Description AS SuspectDescription,
    Suspect.CriminalHistory AS SuspectCriminalHistory
FROM Crime
LEFT JOIN Victim ON Crime.CrimeID = Victim.CrimeID
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID;

-- 15. Find incidents where the suspect is older than any victim. 
SELECT 
    Crime.CrimeID,
    Crime.IncidentType,
    Crime.IncidentDate,
    Crime.Location,
    Crime.Description AS IncidentDescription
FROM Crime 
JOIN Victim ON Crime.CrimeID = Victim.CrimeID
JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID
WHERE Suspect.Age > Victim.Age;

-- 16. Find suspects involved in multiple incidents: 
SELECT 
    Suspect.Name AS SuspectName,
    COUNT(DISTINCT Crime.CrimeID) AS IncidentCount
FROM Suspect
JOIN Crime ON Suspect.CrimeID = Crime.CrimeID
GROUP BY Suspect.Name
HAVING COUNT(DISTINCT Crime.CrimeID) > 1;

-- 17. List incidents with no suspects involved. 
SELECT 
    Crime.CrimeID,
    Crime.IncidentType,
    Crime.IncidentDate,
    Crime.Location,
    Crime.Description AS IncidentDescription,
    Crime.Status AS CrimeStatus
FROM Crime
LEFT JOIN Suspect ON Crime.CrimeID = Suspect.CrimeID
WHERE Suspect.SuspectID IS NULL;

-- 18. List all cases where at least one incident is of type 'Homicide' and all other incidents are of type 'Robbery'. 



-- 19. Retrieve a list of all incidents and the associated suspects, showing suspects for each incident, or 'No Suspect' if there are none. 
SELECT 
    c.CrimeID,
    IF(s.Name IS NULL, 'No Suspect', s.Name) AS SuspectName
FROM 
    Crime c
LEFT JOIN 
    Suspect s ON c.CrimeID = s.CrimeID;

-- 20.  List all suspects who have been involved in incidents with incident types 'Robbery' or 'Assault' 
SELECT 
    s.SuspectID,
    s.Name AS SuspectName,
    c.CrimeID,
    c.IncidentType,
    c.IncidentDate,
    c.Location
FROM Suspect s
JOIN Crime c ON s.CrimeID = c.CrimeID
WHERE c.IncidentType IN ('Robbery', 'Assault');

