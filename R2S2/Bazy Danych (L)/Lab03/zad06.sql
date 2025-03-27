CREATE TABLE studenci_P (imie VARCHAR(25), nazwisko VARCHAR(25));

INSERT INTO studenci_P (imie, nazwisko)
    SELECT imie, nazwisko
    FROM studenci
    WHERE typ_uczel_id = 'P';

SELECT * FROM studenci WHERE stud_id > 3;
SELECT * FROM studenci LIMIT 3, 4; -- 3 - offset, 4 - limit
SELECT COUNT(*) FROM studenci; -- licz wszystkie rekordy