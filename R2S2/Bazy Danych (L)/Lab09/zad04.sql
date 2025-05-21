-- Zadanie 04

-- Aktualizacja nazwisk na wielkie litery
UPDATE emp
    SET last_name = UPPER(last_name);

-- Imię: pierwsza litera wielka, reszta małe (capitalize)
UPDATE emp
    SET first_name = CONCAT(UPPER(LEFT(first_name, 1)), LOWER(SUBSTRING(first_name, 2)));

-- Podwyżka 100 zł
UPDATE emp
    SET salary = salary + 100;

-- Zaokrąglanie daty do 1. dnia miesiąca (jeśli dzień > 15, to do kolejnego miesiąca)
UPDATE emp
    SET start_date = CASE
        WHEN DAY(start_date) > 15
            THEN DATE_FORMAT(DATE_ADD(LAST_DAY(start_date), INTERVAL 1 DAY), '%Y-%m-01')
        ELSE DATE_FORMAT(start_date, '%Y-%m-01')
    END;

-- Zastąpienie NULL znakiem zapytania
UPDATE emp
    SET
        first_name = IFNULL(first_name, '?'),
        last_name = IFNULL(last_name, '?'),
        userid = IFNULL(userid, '?'),
        comments = IFNULL(comments, '?');

-- Uzupełnienie kolumny comments zgodnie z formatem
UPDATE emp e
    JOIN dept d ON e.dept_id = d.id
    JOIN region r ON d.region_id = r.id
    SET e.comments = CONCAT(
        e.first_name, ' ', e.last_name, ' : ',
        UPPER(LEFT(e.first_name, 1)), '.', UPPER(LEFT(e.last_name, 1)), '. : ',
        FORMAT(e.salary, 2), ' zł : ',
        DATE_FORMAT(e.start_date, '%d-%m-%Y'), ' : ',
        r.name, ' : ',
        d.name
    );