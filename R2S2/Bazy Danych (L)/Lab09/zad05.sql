-- Zadanie 05

-- Wyłączenie sprawdzania kluczy obcych (jeśli potrzebne)
SET FOREIGN_KEY_CHECKS = 0;

-- Usunięcie pracowników z działów 3 i 5
DELETE FROM emp
    WHERE dept_id IN (3, 5);

-- Usunięcie działów 3 i 5
DELETE FROM dept
    WHERE id IN (3, 5);

-- Ponowne włączenie sprawdzania kluczy obcych
SET FOREIGN_KEY_CHECKS = 1;