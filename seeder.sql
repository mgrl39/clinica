-- Seeder para el sistema de gestión de clínicas dentales

-- Insertar especialidades
INSERT INTO specialties (name) VALUES
('Odontología General'),
('Ortodoncia'),
('Endodoncia'),
('Periodoncia'),
('Odontopediatría'),
('Cirugía Oral'),
('Implantología'),
('Estética Dental');

-- Insertar horarios
INSERT INTO schedules (first_day, last_day, first_hour, last_hour) VALUES
('MONDAY', 'FRIDAY', '08:00:00', '15:00:00'),
('MONDAY', 'THURSDAY', '14:00:00', '20:00:00'),
('MONDAY', 'WEDNESDAY', '09:00:00', '18:00:00'),
('TUESDAY', 'FRIDAY', '07:30:00', '14:30:00'),
('WEDNESDAY', 'FRIDAY', '15:00:00', '21:00:00');

-- Insertar administradores
INSERT INTO admins (name, user, password) VALUES
('Admin Principal', 'admin', '$2a$10$rPiEAgQNIT1TCoKi.RFzCeYRyPgJxXRMpTAzYA4Hm/HmKQUuVjb7.'), -- password: admin123
('María García', 'maria', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW'), -- password: maria456
('Juan López', 'juan', '$2a$10$L7xt.LOg7eN4r9smn68QT.c48h1jRtY7h3xv4z3xD0h5lLSFpZ8RO'); -- password: juan789

-- Insertar dentistas
INSERT INTO dentists (name, user, password, idSchedule) VALUES
('Dr. Carlos Rodríguez', 'carlos', '$2a$10$2JkPWnaItQRSm7HLztzRO.5y.YwMJL5P1FhZUy5RKEexJr4O1Bk1i', 1), -- password: carlos123
('Dra. Ana Martínez', 'ana', '$2a$10$FnVJAVyt3dBGQu3s6B/useU1G1QBGUPRxtZ9Wn94N.oH8.q5AoOC2', 2), -- password: ana456
('Dr. Miguel Fernández', 'miguel', '$2a$10$jPAGaOZ/x5vkMgI8jXUO7.9zw1MxEAaCDGe7Y8VR3IP2jz.YzKMwS', 3), -- password: miguel789
('Dra. Laura González', 'laura', '$2a$10$pHK3kDrYVHHdlQKeBCqhrekR9Hp0R4AWAKEbZAqDB2/JOgAbG4TS2', 4), -- password: laura123
('Dr. Pedro Sánchez', 'pedro', '$2a$10$AmXTaZaUs0MsxhESLIgJHOIRFHBMQsQJFfaSXWAOllWIu7a4hNFQK', 5); -- password: pedro456

-- Insertar pacientes
INSERT INTO patients (name, bornDate, dni, type, blood_type) VALUES
('Carmen López', '1985-06-15', '12345678A', 'MUTUAL', 'A_POSITIVE'),
('Roberto Martín', '1990-03-22', '23456789B', 'PARTICULAR', 'O_NEGATIVE'),
('Elena Gómez', '1978-11-30', '34567890C', 'MUTUAL', 'B_POSITIVE'),
('Javier Ruiz', '1995-08-10', '45678901D', 'PARTICULAR', 'AB_NEGATIVE'),
('Lucía Torres', '1982-04-05', '56789012E', 'MUTUAL', 'O_POSITIVE'),
('Mario Navarro', '1973-01-18', '67890123F', 'PARTICULAR', 'A_NEGATIVE'),
('Sofía Moreno', '1998-07-27', '78901234G', 'MUTUAL', 'B_NEGATIVE'),
('Daniel Castro', '1988-09-12', '89012345H', 'PARTICULAR', 'AB_POSITIVE'),
('Isabel Flores', '1992-05-03', '90123456I', 'MUTUAL', 'A_POSITIVE'),
('Alejandro Díaz', '1980-12-20', '01234567J', 'PARTICULAR', 'O_POSITIVE');

-- Insertar relaciones entre dentistas y especialidades
INSERT INTO dentist_specialties (dentistId, specialtyId) VALUES
(1, 1), -- Dr. Carlos: Odontología General
(1, 6), -- Dr. Carlos: Cirugía Oral
(2, 2), -- Dra. Ana: Ortodoncia
(2, 8), -- Dra. Ana: Estética Dental
(3, 3), -- Dr. Miguel: Endodoncia
(3, 7), -- Dr. Miguel: Implantología
(4, 4), -- Dra. Laura: Periodoncia
(4, 1), -- Dra. Laura: Odontología General
(5, 5), -- Dr. Pedro: Odontopediatría
(5, 2); -- Dr. Pedro: Ortodoncia

-- Insertar visitas (programadas para 2025)
INSERT INTO visits (reason, comment, date, time, idPatient, idDentist) VALUES
('ROUTINE_CHECKUP', 'Revisión anual', '2025-05-15', '09:00:00', 1, 1),
('CAVITY', 'Posible empaste en molar inferior derecho', '2025-05-15', '10:30:00', 2, 1),
('BROKEN_TOOTH', 'Fractura en incisivo central', '2025-05-16', '16:00:00', 3, 2),
('OTHER', 'Consulta sobre blanqueamiento dental', '2025-05-17', '11:00:00', 4, 2),
('ROUTINE_CHECKUP', 'Control de ortodoncia', '2025-05-18', '12:00:00', 5, 3),
('CAVITY', 'Tratamiento de caries en premolar', '2025-05-19', '09:30:00', 6, 3),
('OTHER', 'Evaluación para implante dental', '2025-05-20', '10:00:00', 7, 4),
('ROUTINE_CHECKUP', 'Revisión de encías', '2025-05-21', '17:00:00', 8, 4),
('BROKEN_TOOTH', 'Reconstrucción de molar', '2025-05-22', '16:30:00', 9, 5),
('ROUTINE_CHECKUP', 'Primera visita pediátrica', '2025-05-23', '18:00:00', 10, 5),
('CAVITY', 'Tratamiento de caries múltiples', '2025-05-26', '09:00:00', 1, 2),
('OTHER', 'Consulta sobre bruxismo', '2025-05-27', '15:00:00', 2, 3),
('ROUTINE_CHECKUP', 'Seguimiento post-endodoncia', '2025-05-28', '11:30:00', 3, 4),
('BROKEN_TOOTH', 'Evaluación para corona dental', '2025-05-29', '14:00:00', 4, 5),
('OTHER', 'Consulta sobre sensibilidad dental', '2025-05-30', '10:00:00', 5, 1);
