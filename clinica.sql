-- Drop existing tables if they exist (in reverse dependency order)
DROP TABLE IF EXISTS DentistSpecialties;
DROP TABLE IF EXISTS Visits;
DROP TABLE IF EXISTS Dentists;
DROP TABLE IF EXISTS Patients;
DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Schedules;
DROP TABLE IF EXISTS Specialties;

-- Specialties table
CREATE TABLE Specialties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Schedules table
CREATE TABLE Schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    firstDay ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY') NOT NULL,
    lastDay ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY') NOT NULL,
    firstHour TIME NOT NULL,
    lastHour TIME NOT NULL
);

-- Dentists table
CREATE TABLE Dentists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    idSchedule BIGINT NOT NULL,
    FOREIGN KEY (idSchedule) REFERENCES Schedules(id) ON DELETE RESTRICT
);

-- Admins table
CREATE TABLE Admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- Patients table
CREATE TABLE Patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    bornDate DATE NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    type ENUM('MUTUAL', 'PARTICULAR') NOT NULL,
    bloodType ENUM('A_POSITIVE', 'A_NEGATIVE', 'B_POSITIVE', 'B_NEGATIVE', 'O_POSITIVE', 'O_NEGATIVE', 'AB_POSITIVE', 'AB_NEGATIVE') NOT NULL
);

-- DentistSpecialties relationship (many-to-many)
CREATE TABLE DentistSpecialties (
    dentistId BIGINT,
    specialtyId BIGINT,
    PRIMARY KEY (dentistId, specialtyId),
    FOREIGN KEY (dentistId) REFERENCES Dentists(id) ON DELETE CASCADE,
    FOREIGN KEY (specialtyId) REFERENCES Specialties(id) ON DELETE CASCADE
);

-- Visits table
CREATE TABLE Visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reason ENUM('ROUTINE_CHECKUP', 'CAVITY', 'BROKEN_TOOTH', 'OTHER') NOT NULL,
    comment TEXT,
    date DATE NOT NULL,
    time TIME NOT NULL,
    idPatient BIGINT NOT NULL,
    idDentist BIGINT NOT NULL,
    
    -- Create unique constraint for date-time-dentist combination
    UNIQUE KEY unique_visit_datetime_dentist (date, time, idDentist),
    
    FOREIGN KEY (idPatient) REFERENCES Patients(id) ON DELETE CASCADE,
    FOREIGN KEY (idDentist) REFERENCES Dentists(id) ON DELETE CASCADE
);

-- Create useful indexes
CREATE INDEX idx_visits_patient ON Visits(idPatient);
CREATE INDEX idx_visits_dentist ON Visits(idDentist);
CREATE INDEX idx_visits_date_time ON Visits(date, time);
CREATE INDEX idx_dentists_schedule ON Dentists(idSchedule);
