-- Insertar autores
INSERT INTO authors (name, biography, birth_date) VALUES 
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
INSERT INTO quotes (content, author_id, category_id) VALUES
('La imaginación es más importante que el conocimiento.', 1, 2),
('Ser o no ser, esa es la cuestión.', 2, 2),
('Puedo resistir todo excepto la tentación.', 3, 3);

-- Relacionar citas y tags
INSERT INTO quote_tags (quote_id, tag_id) VALUES
(1, 2), (1, 5), -- Einstein: ciencia, sabiduría
(2, 5), -- Shakespeare: sabiduría
(3, 1), (3, 3); -- Wilde: vida, amor