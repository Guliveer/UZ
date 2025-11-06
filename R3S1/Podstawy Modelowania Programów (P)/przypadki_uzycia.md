# Przypadki Użycia - System Zarządzania Magazynami

## 1. Przypadki Użycia dla Administratora

### UC-01: Tworzenie Nowej Organizacji

**Aktor:** Administrator  
**Cel:** Dodanie nowej organizacji do systemu  
**Warunki wstępne:** Administrator jest zalogowany

**Scenariusz główny:**

1. Administrator wybiera opcję "Dodaj organizację"
2. System wyświetla formularz z polami: nazwa, adres, telefon, email, www, opis
3. Administrator wypełnia wymagane pola
4. Administrator zatwierdza formularz
5. System waliduje dane i zapisuje organizację
6. System wyświetla komunikat o sukcesie

**Scenariusze alternatywne:**

- 5a. Dane są niepoprawne - system wyświetla błędy walidacji
- 5b. Organizacja o takiej nazwie już istnieje - system wyświetla błąd

### UC-02: Dodawanie Użytkownika do Systemu

**Aktor:** Administrator  
**Cel:** Utworzenie konta dla nowego użytkownika  
**Warunki wstępne:** Administrator jest zalogowany, istnieje co najmniej jedna organizacja

**Scenariusz główny:**

1. Administrator wybiera opcję "Dodaj użytkownika"
2. System wyświetla formularz z polami: imię, nazwisko, email, rola, organizacja
3. Administrator wypełnia dane użytkownika
4. Administrator wybiera organizację z listy
5. Administrator zatwierdza formularz
6. System generuje tymczasowe hasło
7. System zapisuje użytkownika i wysyła dane logowania

### UC-03: Tworzenie Magazynu

**Aktor:** Administrator  
**Cel:** Dodanie nowego magazynu do organizacji  
**Warunki wstępne:** Administrator jest zalogowany, organizacja istnieje

**Scenariusz główny:**

1. Administrator wybiera organizację
2. Administrator wybiera opcję "Dodaj magazyn"
3. System wyświetla formularz magazynu
4. Administrator wypełnia dane: nazwa, kod, adres, opis
5. Administrator zatwierdza formularz
6. System zapisuje magazyn i przypisuje go do organizacji

---

## 2. Przypadki Użycia dla Zwykłego Użytkownika

### UC-04: Rejestracja Przyjęcia Towaru (PZ)

**Aktor:** Użytkownik  
**Cel:** Zarejestrowanie przyjęcia towaru do magazynu  
**Warunki wstępne:** Użytkownik jest zalogowany, ma dostęp do magazynu

**Scenariusz główny:**

1. Użytkownik wybiera magazyn z listy dostępnych
2. Użytkownik wybiera opcję "Przyjęcie towaru"
3. System wyświetla formularz PZ
4. Użytkownik wprowadza numer dokumentu PZ
5. Użytkownik wyszukuje i wybiera produkt
6. Użytkownik wprowadza ilość i wybiera lokację docelową
7. Użytkownik dodaje opcjonalne uwagi
8. Użytkownik zatwierdza operację
9. System aktualizuje stan magazynowy
10. System zapisuje operację w historii

**Scenariusze alternatywne:**

- 5a. Produkt nie istnieje - użytkownik może poprosić administratora o dodanie
- 6a. Lokacja jest pełna - system wyświetla ostrzeżenie
- 6b. Lokacja jest zablokowana - system nie pozwala na operację

### UC-05: Rejestracja Wydania Towaru (WZ)

**Aktor:** Użytkownik  
**Cel:** Zarejestrowanie wydania towaru z magazynu  
**Warunki wstępne:** Użytkownik jest zalogowany, produkt jest dostępny na stanie

**Scenariusz główny:**

1. Użytkownik wybiera magazyn
2. Użytkownik wybiera opcję "Wydanie towaru"
3. System wyświetla formularz WZ
4. Użytkownik wprowadza numer dokumentu WZ
5. Użytkownik wyszukuje produkt
6. System wyświetla dostępne lokacje z tym produktem
7. Użytkownik wybiera lokację i wprowadza ilość
8. Użytkownik wprowadza dane odbiorcy (opcjonalnie)
9. Użytkownik zatwierdza operację
10. System sprawdza dostępność towaru
11. System aktualizuje stan magazynowy
12. System zapisuje operację w historii

**Scenariusze alternatywne:**

- 10a. Niewystarczająca ilość na stanie - system wyświetla błąd
- 7a. Użytkownik wprowadza ilość większą niż dostępna - system wyświetla ostrzeżenie

### UC-06: Przeprowadzenie Inwentaryzacji

**Aktor:** Użytkownik  
**Cel:** Sprawdzenie i skorygowanie stanów magazynowych  
**Warunki wstępne:** Użytkownik ma uprawnienia do inwentaryzacji

**Scenariusz główny:**

1. Użytkownik wybiera lokację do inwentaryzacji
2. Użytkownik rozpoczyna inwentaryzację
3. System blokuje operacje na wybranej lokacji
4. System wyświetla listę produktów w lokacji ze stanami systemowymi
5. Użytkownik fizycznie liczy produkty
6. Użytkownik wprowadza rzeczywiste stany
7. System porównuje stany i wyświetla różnice
8. Użytkownik potwierdza korekty
9. System aktualizuje stany magazynowe
10. System odblokuje lokację
11. System zapisuje raport inwentaryzacji

---

## 3. Wspólne Przypadki Użycia

### UC-07: Wyszukiwanie Produktu

**Aktor:** Użytkownik/Administrator  
**Cel:** Znalezienie produktu w systemie  
**Warunki wstępne:** Użytkownik jest zalogowany

**Scenariusz główny:**

1. Użytkownik wprowadza frazę wyszukiwania (nazwa lub kod)
2. System wyszukuje produkty pasujące do frazy
3. System wyświetla listę znalezionych produktów
4. Użytkownik wybiera produkt z listy
5. System wyświetla szczegóły produktu i jego stany

### UC-08: Przeglądanie Stanów Magazynowych

**Aktor:** Użytkownik/Administrator  
**Cel:** Sprawdzenie aktualnych stanów produktów  
**Warunki wstępne:** Użytkownik ma dostęp do magazynu

**Scenariusz główny:**

1. Użytkownik wybiera magazyn
2. System wyświetla listę lokacji w magazynie
3. Użytkownik wybiera lokację
4. System wyświetla produkty w lokacji z ilościami
5. Użytkownik może filtrować produkty po kategorii
6. System aktualizuje widok zgodnie z filtrem

### UC-09: Zmiana Hasła

**Aktor:** Użytkownik/Administrator  
**Cel:** Zmiana hasła dostępu do systemu  
**Warunki wstępne:** Użytkownik jest zalogowany

**Scenariusz główny:**

1. Użytkownik wybiera opcję "Zmień hasło"
2. System wyświetla formularz zmiany hasła
3. Użytkownik wprowadza aktualne hasło
4. Użytkownik wprowadza nowe hasło dwukrotnie
5. System waliduje poprawność aktualnego hasła
6. System sprawdza czy nowe hasła są identyczne
7. System sprawdza siłę nowego hasła
8. System zapisuje nowe hasło (zaszyfrowane)
9. System wyświetla komunikat o sukcesie

**Scenariusze alternatywne:**

- 5a. Aktualne hasło jest niepoprawne - system wyświetla błąd
- 6a. Nowe hasła się różnią - system wyświetla błąd
- 7a. Hasło jest za słabe - system wyświetla wymagania

---

## 4. Przypadki Użycia Zarządzania Danymi

### UC-10: Dodawanie Nowego Produktu

**Aktor:** Administrator  
**Cel:** Dodanie nowego produktu do systemu  
**Warunki wstępne:** Administrator jest zalogowany

**Scenariusz główny:**

1. Administrator wybiera opcję "Dodaj produkt"
2. System wyświetla formularz produktu
3. Administrator wypełnia dane: nazwa, kod, opis, jednostka miary
4. Administrator wybiera kategorię z listy
5. Administrator opcjonalnie wprowadza cenę
6. Administrator zatwierdza formularz
7. System waliduje unikalność kodu produktu
8. System zapisuje produkt
9. System wyświetla komunikat o sukcesie

### UC-11: Tworzenie Lokacji w Magazynie

**Aktor:** Administrator  
**Cel:** Dodanie nowej lokacji do magazynu  
**Warunki wstępne:** Administrator jest zalogowany, magazyn istnieje

**Scenariusz główny:**

1. Administrator wybiera magazyn
2. Administrator wybiera opcję "Dodaj lokację"
3. System wyświetla formularz lokacji
4. Administrator wypełnia: nazwa, kod, typ, pojemność, opis
5. Administrator zatwierdza formularz
6. System waliduje unikalność kodu w ramach magazynu
7. System zapisuje lokację
8. System przypisuje lokację do magazynu

### UC-12: Przeniesienie Produktu Między Lokacjami

**Aktor:** Użytkownik  
**Cel:** Zmiana lokalizacji produktu w magazynie  
**Warunki wstępne:** Użytkownik ma dostęp do magazynu, produkt istnieje w lokacji źródłowej

**Scenariusz główny:**

1. Użytkownik wyszukuje produkt
2. System wyświetla lokacje z tym produktem
3. Użytkownik wybiera lokację źródłową
4. Użytkownik wybiera opcję "Przenieś"
5. System wyświetla dostępne lokacje docelowe
6. Użytkownik wybiera lokację docelową
7. Użytkownik wprowadza ilość do przeniesienia
8. Użytkownik wprowadza powód przeniesienia
9. System sprawdza dostępność w lokacji źródłowej
10. System wykonuje przeniesienie (zmniejsza stan źródłowy, zwiększa docelowy)
11. System zapisuje operację w historii

---

## 5. Przypadki Użycia Raportowania

### UC-13: Generowanie Raportu Stanów

**Aktor:** Użytkownik/Administrator  
**Cel:** Wygenerowanie raportu aktualnych stanów magazynowych  
**Warunki wstępne:** Użytkownik ma dostęp do magazynu

**Scenariusz główny:**

1. Użytkownik wybiera opcję "Raport stanów"
2. System wyświetla opcje filtrowania
3. Użytkownik wybiera magazyn, kategorie produktów (opcjonalnie)
4. Użytkownik zatwierdza parametry raportu
5. System generuje raport ze stanami wszystkich produktów
6. System wyświetla raport w przeglądarce
7. Użytkownik może wyeksportować raport do PDF/Excel

### UC-14: Przeglądanie Historii Operacji

**Aktor:** Użytkownik/Administrator  
**Cel:** Sprawdzenie historii operacji magazynowych  
**Warunki wstępne:** Użytkownik jest zalogowany

**Scenariusz główny:**

1. Użytkownik wybiera opcję "Historia operacji"
2. System wyświetla filtry: data od/do, typ operacji, użytkownik, produkt
3. Użytkownik ustawia filtry
4. System wyświetla listę operacji spełniających kryteria
5. Użytkownik może kliknąć na operację aby zobaczyć szczegóły
6. System wyświetla pełne informacje o wybranej operacji

---

## 6. Scenariusze Błędów i Wyjątków

### UC-15: Obsługa Braku Uprawnień

**Aktor:** Użytkownik  
**Cel:** Próba dostępu do zasobu bez odpowiednich uprawnień

**Scenariusz:**

1. Użytkownik próbuje wykonać operację
2. System sprawdza uprawnienia użytkownika
3. System stwierdza brak uprawnień
4. System wyświetla komunikat o braku dostępu
5. System przekierowuje do strony głównej lub logowania

### UC-16: Obsługa Sesji Wygasłej

**Aktor:** Użytkownik  
**Cel:** Kontynuacja pracy po wygaśnięciu sesji

**Scenariusz:**

1. Użytkownik próbuje wykonać operację
2. System sprawdza ważność sesji
3. System stwierdza wygaśnięcie sesji
4. System wyświetla komunikat o konieczności ponownego logowania
5. System przekierowuje do strony logowania
6. Użytkownik loguje się ponownie
7. System przywraca sesję i przekierowuje do żądanej strony
