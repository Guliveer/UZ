/*==============================================================*/
/* Tabela: studenci */
/*==============================================================*/
-- To też jest znak komentarza, wszystko za znakami '--'
-- jest pomijane przez analizator SQL-a.
--
-- Polecenie kasujące tabelę (na wypadek, gdyby tabela o takiej nazwie już
-- istniała u bieżącego użytkownika.
--
DROP TABLE IF EXISTS studenci;

CREATE TABLE studenci (
    stud_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    imie VARCHAR(20) NOT NULL,
    nazwisko VARCHAR(30) NOT NULL,
    typ_uczel_id CHAR(1) NULL
);
--
--
-- Wstawienie kilku przykładowych rekordów.
INSERT INTO studenci VALUES
(1, 'Artur', 'Nowakowski', 'P'),
(2, 'Jan', 'Kowalski', 'P'),
(3, 'Roman', 'Nowak', 'U'),
(4, 'Stefan', 'Antkowiak', 'A'),
(5, 'Ewa', 'Konieczna', 'A'),
(6, 'Anna', 'Wojtasik', 'A'),
(7, 'Marek', 'Pawlak', 'P');

show tables;
describe studenci;