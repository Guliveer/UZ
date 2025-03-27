CREATE TABLE studenci_2
    AS SELECT
        imie, nazwisko, typ_uczel_id
    FROM
        studenci
    WHERE
        typ_uczel_id = 'P';

CREATE TABLE uczelnie
(
    typ_uczel_id CHAR(1) NOT NULL PRIMARY KEY,
    nazwa VARCHAR(20) NOT NULL
);
INSERT INTO uczelnie VALUES ('U', 'Uniwersytet');
INSERT INTO uczelnie VALUES ('P', 'Politechnika');
INSERT INTO uczelnie VALUES ('A', 'Akademia');

CREATE TABLE studenci_3
    AS SELECT
        S.imie, S.nazwisko, U.nazwa
    FROM
        studenci AS S, uczelnie AS U
    WHERE
        U.typ_uczel_id = S.typ_uczel_id AND
        U.nazwa = 'Politechnika';
