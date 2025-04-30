-- Insertar usuarios de prueba
INSERT INTO users (username, password, email, created_at) VALUES 
('usuario1', 'password123', 'usuario1@example.com', CURRENT_TIMESTAMP()),
('usuario2', 'password123', 'usuario2@example.com', CURRENT_TIMESTAMP());

-- Insertar autores (corregido nombre de tabla)
INSERT INTO author (name, biography, birth_date) VALUES 
('Albert Einstein', 'Físico teórico alemán, conocido por desarrollar la teoría de la relatividad', '1879-03-14'),
('William Shakespeare', 'Dramaturgo, poeta y actor inglés, considerado el escritor más importante en lengua inglesa', '1564-04-23'),
('Oscar Wilde', 'Escritor, poeta y dramaturgo irlandés', '1854-10-16');

-- Insertar categorías
INSERT INTO categories (name, description) VALUES 
('Motivación', 'Frases que inspiran y motivan'),
('Filosofía', 'Pensamientos filosóficos y reflexiones profundas'),
('Humor', 'Citas con contenido humorístico');

-- Insertar tags
INSERT INTO tags (name) VALUES 
('vida'),
('ciencia'),
('amor'),
('éxito'),
('sabiduría');

-- Insertar citas
INSERT INTO quotes (content, author_id, category_id, created_at) VALUES
('La imaginación es más importante que el conocimiento.', 1, 2, CURRENT_TIMESTAMP()),
('Ser o no ser, esa es la cuestión.', 2, 2, CURRENT_TIMESTAMP()),
('Puedo resistir todo excepto la tentación.', 3, 3, CURRENT_TIMESTAMP());

-- Relacionar citas y tags
INSERT INTO quote_tags (quote_id, tag_id) VALUES
(1, 2), (1, 5), -- Einstein: ciencia, sabiduría
(2, 5), -- Shakespeare: sabiduría
(3, 1), (3, 3); -- Wilde: vida, amor

-- Agregar algunos favoritos
INSERT INTO favorites (user_id, quote_id, created_at) VALUES
(1, 1, CURRENT_TIMESTAMP()),
(1, 3, CURRENT_TIMESTAMP()),
(2, 2, CURRENT_TIMESTAMP());
