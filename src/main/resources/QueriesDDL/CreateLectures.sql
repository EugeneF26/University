CREATE TABLE Lectures
(
id SERIAL PRIMARY KEY,
title TEXT,
CONSTRAINT sc_unique_lecture UNIQUE (id, title),
CONSTRAINT sc_unique_lecture2 UNIQUE (title)
);