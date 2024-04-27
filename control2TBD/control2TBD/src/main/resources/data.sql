-- Insert Users
INSERT INTO public.user_entity(id, name, surname, username, password)
VALUES
    (1, 'nicolas', 'gajardo', 'niga', '1234'),
    (2, 'yuyunisse', 'careno', 'yuca', '4321'),
    (3, 'María', 'García', 'mgar', 'password123'),
    (4, 'Juan', 'Pérez', 'jperez', 'securepass'),
    (5, 'Laura', 'López', 'llopez', 'qwerty'),
    (6, 'Pedro', 'Martínez', 'pmart', 'p@ssw0rd');



-- Insert tasks
INSERT INTO public.task_entity(id, title, description, expiredate, active, user_id)
VALUES
    (1, 'Comprar comestibles', 'Ir al supermercado y comprar frutas, verduras y productos básicos', '2024-04-30', true, 1),
    (2, 'Reunión de equipo', 'Organizar una reunión con el equipo para discutir los próximos pasos del proyecto', '2024-05-05', true, 4),
    (3, 'Preparar presentación', 'Crear una presentación para el cliente sobre el progreso del proyecto', '2024-05-10', true, 2),
    (4, 'Enviar informe semanal', 'Preparar y enviar el informe semanal a los superiores', '2024-05-03', true, 3),
    (5, 'Entrenamiento en línea', 'Asistir al curso de capacitación en línea sobre nuevas herramientas de desarrollo', '2024-05-08', false, 6),
    (6, 'Llamada con el cliente', 'Programar y realizar una llamada con el cliente para discutir los requisitos adicionales', '2024-05-12', true, 5),
    (7, 'Revisión de código', 'Realizar una revisión exhaustiva del código del nuevo módulo', '2024-05-15', false, 1),
    (8, 'Actualizar documentación', 'Actualizar la documentación del proyecto con los cambios recientes', '2024-05-20', true, 4),
    (9, 'Reunión de seguimiento', 'Organizar una reunión de seguimiento para revisar el progreso del proyecto', '2024-05-25', false, 5),
    (10, 'Resolver incidencias', 'Abordar las incidencias reportadas por los usuarios y proporcionar soluciones', '2024-05-28', true, 3);
