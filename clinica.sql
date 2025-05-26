-- Drop existing tables if they exist (in reverse dependency order)
DROP TABLE IF EXISTS dentist_specialties;
DROP TABLE IF EXISTS visits;
DROP TABLE IF EXISTS dentists;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS specialties;

-- Specialties table
CREATE TABLE specialties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Schedules table
CREATE TABLE schedules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_day ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY') NOT NULL,
    last_day ENUM('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY') NOT NULL,
    first_hour TIME NOT NULL,
    last_hour TIME NOT NULL
);

-- Dentists table
CREATE TABLE dentists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    id_schedule BIGINT NOT NULL,
    FOREIGN KEY (id_schedule) REFERENCES schedules(id) ON DELETE RESTRICT
);

-- Admins table
CREATE TABLE admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    user VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Patients table
CREATE TABLE patients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    born_date DATE NOT NULL,
    dni VARCHAR(100) NOT NULL UNIQUE,
    type ENUM('MUTUAL', 'PARTICULAR') NOT NULL,
    blood_type ENUM('A_POSITIVE', 'A_NEGATIVE', 'B_POSITIVE', 'B_NEGATIVE', 'O_POSITIVE', 'O_NEGATIVE', 'AB_POSITIVE', 'AB_NEGATIVE') NOT NULL
);

-- DentistSpecialties relationship (many-to-many)
CREATE TABLE dentist_specialties (
    dentist_id BIGINT,
    specialty_id BIGINT,
    PRIMARY KEY (dentist_id, specialty_id),
    FOREIGN KEY (dentist_id) REFERENCES dentists(id) ON DELETE CASCADE,
    FOREIGN KEY (specialty_id) REFERENCES specialties(id) ON DELETE CASCADE
);

-- Visits table
CREATE TABLE visits (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reason ENUM('ROUTINE_CHECKUP', 'CAVITY', 'BROKEN_TOOTH', 'OTHER') NOT NULL,
    comment TEXT,
    date DATE NOT NULL,
    time TIME NOT NULL,
    idPatient BIGINT NOT NULL,
    idDentist BIGINT NOT NULL,
    
    -- Create unique constraint for date-time-dentist combination
    UNIQUE KEY unique_visit_datetime_dentist (date, time, idDentist),
    
    FOREIGN KEY (idPatient) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (idDentist) REFERENCES dentists(id) ON DELETE CASCADE
);

-- Create useful indexes
CREATE INDEX idx_visits_patient ON visits(idPatient);
CREATE INDEX idx_visits_dentist ON visits(idDentist);
CREATE INDEX idx_visits_date_time ON visits(date, time);
CREATE INDEX idx_dentists_schedule ON dentists(id_schedule);

-- Add tutor column to patients table
ALTER TABLE patients
ADD COLUMN tutor VARCHAR(100) NULL;
