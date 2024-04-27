-- Insert Users
INSERT INTO public.user_entity(name, surname, username, password)
VALUES
    ('nicolas', 'gajardo', 'niga', '1234'),
    ('yuyunisse', 'careno', 'yuca', '4321'),
    ('María', 'García', 'mgar', 'password123'),
    ('Juan', 'Pérez', 'jperez', 'securepass'),
    ('Laura', 'López', 'llopez', 'qwerty'),
    ('Pedro', 'Martínez', 'pmart', 'p@ssw0rd');



-- Insert tasks
-- Insertar todas las tareas en una sola inserción
INSERT INTO public.task_entity(title, description, expiredate, active, user_id)
VALUES
    ('Comprar comestibles', 'Ir al supermercado y comprar frutas, verduras y productos básicos', '2024-04-30', true, 1),
    ('Reunión de equipo', 'Organizar una reunión con el equipo para discutir los próximos pasos del proyecto', '2024-05-05', true, 4),
    ('Preparar presentación', 'Crear una presentación para el cliente sobre el progreso del proyecto', '2024-05-10', false, 2),
    ('Enviar informe semanal', 'Preparar y enviar el informe semanal a los superiores', '2024-05-03', true, 3),
    ('Entrenamiento en línea', 'Asistir al curso de capacitación en línea sobre nuevas herramientas de desarrollo', '2024-05-08', false, 6),
    ('Llamada con el cliente', 'Programar y realizar una llamada con el cliente para discutir los requisitos adicionales', '2024-05-12', true, 5),
    ('Revisión de código', 'Realizar una revisión exhaustiva del código del nuevo módulo', '2024-05-15', false, 1),
    ('Actualizar documentación', 'Actualizar la documentación del proyecto con los cambios recientes', '2024-05-20', true, 4),
    ('Reunión de seguimiento', 'Organizar una reunión de seguimiento para revisar el progreso del proyecto', '2024-05-25', false, 5),
    ('Resolver incidencias', 'Abordar las incidencias reportadas por los usuarios y proporcionar soluciones', '2024-05-28', true, 3),
    ('Optimización de Código', 'Realizar una revisión del código existente para identificar áreas de mejora en cuanto a eficiencia y legibilidad', '2024-05-05', true, 2),
    ('Configuración de Servidores', 'Configurar nuevos servidores para manejar el crecimiento del tráfico del sistema y garantizar la escalabilidad', '2024-05-10', true, 4),
    ('Creación de Manual de Usuario', 'Elaborar un manual detallado para usuarios finales que describa cómo utilizar todas las funciones del sistema', '2024-05-15', true, 1),
    ('Análisis de Competencia', 'Realizar un análisis de la competencia para identificar fortalezas, debilidades, oportunidades y amenazas en el mercado', '2024-05-20', true, 3),
    ('Investigación de Seguridad', 'Realizar una auditoría de seguridad en el sistema para identificar posibles vulnerabilidades y proponer soluciones', '2024-05-25', true, 6),
    ('Desarrollo de API Rest', 'Crear una API REST para permitir la integración del sistema con otras aplicaciones externas', '2024-05-28', true, 5),
    ('Creación de Dashboard de Métricas', 'Diseñar y desarrollar un panel de control para visualizar métricas clave del sistema en tiempo real', '2024-06-02', true, 2),
    ('Implementación de Funcionalidad de Notificaciones', 'Integrar un sistema de notificaciones para informar a los usuarios sobre eventos importantes en el sistema', '2024-06-07', true, 4),
    ('Traducción de la Interfaz de Usuario', 'Traducir la interfaz de usuario del sistema a otros idiomas para facilitar su adopción en mercados internacionales', '2024-06-12', true, 1),
    ('Elaboración de Plan de Marketing', 'Desarrollar un plan de marketing para promocionar el producto y aumentar su visibilidad en línea', '2024-06-17', true, 3);

