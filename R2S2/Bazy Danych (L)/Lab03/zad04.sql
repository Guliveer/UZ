CREATE OR REPLACE VIEW studenci_view AS
    SELECT
        S.imie AS imie_studenta,
        S.nazwisko AS nazwisko_studenta,
        U.nazwa AS nazwa_uczelni
    FROM
        studenci S, uczelnie U
    WHERE
        S.typ_uczel_id = U.typ_uczel_id;
