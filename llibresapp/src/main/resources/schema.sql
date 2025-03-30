CREATE TABLE IF NOT EXISTS book (
    idllibre INT AUTO_INCREMENT PRIMARY KEY,
    titol VARCHAR(255),
    autor VARCHAR(255),
    editorial VARCHAR(255),
    datapublicacio DATE,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    tematica VARCHAR(255)
);
