-- Zadanie 02

-- Sprawdzenie aktualnego statusu sprawdzania kluczy obcych (opcjonalnie)
SELECT @@FOREIGN_KEY_CHECKS;

-- Wyłączenie sprawdzania kluczy obcych
SET FOREIGN_KEY_CHECKS = 0;

-- Kasowanie danych z tabel
DELETE FROM customer;
DELETE FROM dept;
DELETE FROM emp;
DELETE FROM inventory;
DELETE FROM item;
DELETE FROM ord;
DELETE FROM price_category;
DELETE FROM product;
DELETE FROM region;
DELETE FROM title;
DELETE FROM warehouse;

-- Włączenie sprawdzania kluczy obcych
SET FOREIGN_KEY_CHECKS = 1;

-- Sprawdzenie czy ponownie włączone (opcjonalnie)
SELECT @@FOREIGN_KEY_CHECKS;