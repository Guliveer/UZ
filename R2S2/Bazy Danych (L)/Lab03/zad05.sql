INSERT INTO studenci VALUES
    (1, 'Artur', 'Nowakowski', 'P'),
    (2, 'Jan', 'Kowalski', 'P'),
    (3, 'Roman', 'Nowak', 'U'),
    (4, 'Stefan', 'Antkowiak', 'A'),
    (5, 'Ewa', 'Konieczna', 'A'),
    (6, 'Anna', 'Wojtasik', 'A'),
    (7, 'Marek', 'Pawlak', 'P');
COMMIT;

INSERT INTO studenci (imie, nazwisko) VALUES ('Artur', 'Gramacki');
INSERT INTO studenci SET imie='Tomasz', nazwisko='Wisniewski';