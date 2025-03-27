CREATE TABLE Book (
    idBook INT AUTO_INCREMENT PRIMARY KEY,
    titol VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    editorial VARCHAR(255),
    datapublicacio DATE,
    isbn VARCHAR(13) NOT NULL UNIQUE,
    tematica VARCHAR(255)
);
