# System Zarządzania Magazynami - Specyfikacja Funkcjonalności

## 1. Struktura Systemu

### Hierarchia Danych

```
Organizacja
├── Magazyny
    ├── Lokacje
        ├── Produkty
```

### Role Użytkowników

- **Administrator** - pełny dostęp do wszystkich funkcjonalności
- **Użytkownik** - ograniczony dostęp, głównie operacje magazynowe

---

## 2. Funkcjonalności dla Administratora

### 2.1 Zarządzanie Użytkownikami

- Dodawanie nowych użytkowników do systemu
- Edycja danych użytkowników (imię, nazwisko, email, rola)
- Usuwanie użytkowników z systemu
- Przypisywanie użytkowników do organizacji
- Zmiana ról użytkowników (admin/user)
- Resetowanie haseł użytkowników

### 2.2 Zarządzanie Organizacjami

- Tworzenie nowych organizacji
- Edycja danych organizacji (nazwa, adres, dane kontaktowe)
- Usuwanie organizacji
- Przeglądanie listy wszystkich organizacji w systemie

### 2.3 Zarządzanie Magazynami

- Tworzenie nowych magazynów w ramach organizacji
- Edycja danych magazynów (nazwa, adres, opis)
- Usuwanie magazynów
- Przypisywanie użytkowników do konkretnych magazynów

### 2.4 Zarządzanie Lokacjami

- Tworzenie lokacji w magazynach (regały, półki, strefy)
- Edycja lokacji (nazwa, kod, opis, pojemność)
- Usuwanie lokacji
- Przenoszenie lokacji między magazynami

### 2.5 Zarządzanie Produktami

- Dodawanie nowych produktów do systemu
- Edycja danych produktów (nazwa, kod, opis, jednostka miary)
- Usuwanie produktów z systemu
- Zarządzanie kategoriami produktów

---

## 3. Funkcjonalności dla Zwykłego Użytkownika

### 3.1 Przeglądanie Danych

- Przeglądanie listy magazynów przypisanych do użytkownika
- Przeglądanie lokacji w dostępnych magazynach
- Przeglądanie produktów i ich stanów magazynowych
- Wyszukiwanie produktów po nazwie lub kodzie

### 3.2 Operacje Magazynowe

- Rejestrowanie przyjęć towarów do magazynu
- Rejestrowanie wydań towarów z magazynu
- Przeprowadzanie inwentaryzacji
- Przenoszenie produktów między lokacjami

### 3.3 Zarządzanie Własnymi Danymi

- Edycja własnego profilu (imię, nazwisko, email)
- Zmiana hasła
- Przeglądanie historii własnych operacji

---

## 4. Zarządzanie Organizacjami

### 4.1 Dane Organizacji

- **Nazwa organizacji** - unikalna nazwa identyfikująca organizację
- **Adres** - pełny adres siedziby organizacji
- **Dane kontaktowe** - telefon, email, strona www
- **Opis** - krótki opis działalności organizacji
- **Data utworzenia** - automatycznie zapisywana data

### 4.2 Operacje na Organizacjach

- **Tworzenie** - tylko administrator może tworzyć nowe organizacje
- **Edycja** - modyfikacja danych organizacji
- **Usuwanie** - usunięcie organizacji wraz z wszystkimi powiązanymi danymi
- **Przeglądanie** - lista wszystkich organizacji z podstawowymi informacjami

---

## 5. Zarządzanie Magazynami

### 5.1 Dane Magazynu

- **Nazwa magazynu** - nazwa identyfikująca magazyn w ramach organizacji
- **Kod magazynu** - unikalny kod alfanumeryczny
- **Adres** - lokalizacja magazynu
- **Opis** - dodatkowe informacje o magazynie
- **Status** - aktywny/nieaktywny
- **Organizacja** - powiązanie z organizacją nadrzędną

### 5.2 Operacje na Magazynach

- **Tworzenie** - dodawanie nowego magazynu do organizacji
- **Edycja** - modyfikacja danych magazynu
- **Usuwanie** - usunięcie magazynu wraz z lokacjami i stanami
- **Aktywacja/Deaktywacja** - zmiana statusu magazynu
- **Przypisywanie użytkowników** - określenie kto ma dostęp do magazynu

---

## 6. Zarządzanie Lokacjami

### 6.1 Dane Lokacji

- **Nazwa lokacji** - nazwa opisowa (np. "Regał A1", "Strefa chłodnicza")
- **Kod lokacji** - unikalny kod w ramach magazynu
- **Typ lokacji** - regał, półka, strefa, kontener
- **Pojemność** - maksymalna ilość produktów
- **Opis** - dodatkowe informacje o lokacji
- **Status** - dostępna/zajęta/zablokowana

### 6.2 Operacje na Lokacjach

- **Tworzenie** - dodawanie nowej lokacji do magazynu
- **Edycja** - modyfikacja danych lokacji
- **Usuwanie** - usunięcie pustej lokacji
- **Blokowanie/Odblokowanie** - zarządzanie dostępnością lokacji
- **Przenoszenie** - zmiana przypisania do magazynu

---

## 7. Zarządzanie Produktami

### 7.1 Dane Produktu

- **Nazwa produktu** - pełna nazwa produktu
- **Kod produktu** - unikalny identyfikator (SKU, EAN)
- **Opis** - szczegółowy opis produktu
- **Kategoria** - klasyfikacja produktu
- **Jednostka miary** - szt., kg, l, m, itp.
- **Cena jednostkowa** - opcjonalna cena za jednostkę
- **Status** - aktywny/nieaktywny

### 7.2 Operacje na Produktach

- **Dodawanie** - tworzenie nowego produktu w systemie
- **Edycja** - modyfikacja danych produktu
- **Usuwanie** - usunięcie produktu (tylko jeśli brak stanów)
- **Aktywacja/Deaktywacja** - zarządzanie dostępnością produktu
- **Kategoryzacja** - przypisywanie do kategorii

### 7.3 Kategorie Produktów

- **Tworzenie kategorii** - definiowanie nowych kategorii
- **Edycja kategorii** - zmiana nazwy i opisu kategorii
- **Usuwanie kategorii** - usunięcie pustej kategorii
- **Hierarchia kategorii** - możliwość tworzenia podkategorii

---

## 8. Podstawowe Operacje Magazynowe

### 8.1 Przyjęcia Towarów (PZ)

- **Rejestracja przyjęcia** - wprowadzenie nowego towaru do magazynu
- **Dane przyjęcia**:
  - Data i godzina przyjęcia
  - Numer dokumentu PZ
  - Produkt i ilość
  - Lokacja docelowa
  - Użytkownik wykonujący operację
  - Uwagi
- **Aktualizacja stanu** - automatyczne zwiększenie stanu magazynowego

### 8.2 Wydania Towarów (WZ)

- **Rejestracja wydania** - wydanie towaru z magazynu
- **Dane wydania**:
  - Data i godzina wydania
  - Numer dokumentu WZ
  - Produkt i ilość
  - Lokacja źródłowa
  - Odbiorca (opcjonalnie)
  - Użytkownik wykonujący operację
  - Uwagi
- **Aktualizacja stanu** - automatyczne zmniejszenie stanu magazynowego
- **Kontrola dostępności** - sprawdzenie czy wystarczająca ilość na stanie

### 8.3 Inwentaryzacja

- **Rozpoczęcie inwentaryzacji** - zablokowanie operacji na wybranych lokacjach
- **Rejestracja stanu rzeczywistego** - wprowadzenie policzonej ilości
- **Porównanie stanów** - zestawienie stanu systemowego z rzeczywistym
- **Korekty inwentaryzacyjne** - automatyczne wyrównanie różnic
- **Zakończenie inwentaryzacji** - odblokowanie lokacji

### 8.4 Przeniesienia Wewnętrzne

- **Przeniesienie między lokacjami** - zmiana lokalizacji produktu
- **Dane przeniesienia**:
  - Data i godzina operacji
  - Produkt i ilość
  - Lokacja źródłowa
  - Lokacja docelowa
  - Powód przeniesienia
  - Użytkownik wykonujący
- **Kontrola dostępności** - sprawdzenie stanu w lokacji źródłowej

### 8.5 Stany Magazynowe

- **Aktualny stan** - bieżąca ilość produktu w lokacji
- **Historia stanów** - zmiany stanu w czasie
- **Stany minimalne** - opcjonalne ostrzeżenia o niskich stanach
- **Rezerwacje** - opcjonalne blokowanie ilości pod konkretne zamówienia

---

## 9. Dodatkowe Funkcjonalności Systemowe

### 9.1 Wyszukiwanie i Filtrowanie

- Wyszukiwanie produktów po nazwie, kodzie, kategorii
- Filtrowanie operacji po dacie, użytkowniku, typie
- Wyszukiwanie lokacji po kodzie, nazwie, magazynie

### 9.2 Historia Operacji

- Rejestr wszystkich operacji magazynowych
- Możliwość przeglądania historii dla konkretnego produktu
- Historia zmian danych systemowych (audit log)

### 9.3 Podstawowe Raporty

- Raport stanów magazynowych
- Raport operacji w okresie
- Raport inwentaryzacji
- Lista produktów o niskich stanach

### 9.4 Bezpieczeństwo

- Logowanie użytkowników (login/hasło)
- Kontrola dostępu na poziomie organizacji i magazynu
- Sesje użytkowników z automatycznym wylogowaniem
- Podstawowe logowanie działań użytkowników

---

## 10. Ograniczenia i Założenia Systemu

### 10.1 Ograniczenia Funkcjonalne

- Brak integracji z systemami zewnętrznymi
- Brak zaawansowanego raportowania i analityki
- Brak automatycznych powiadomień
- Brak zarządzania dostawcami i zamówieniami
- Brak obsługi kodów kreskowych

### 10.2 Założenia Techniczne

- System webowy dostępny przez przeglądarkę
- Baza danych relacyjna
- Interfejs użytkownika w języku polskim
- Responsywny design (dostosowanie do urządzeń mobilnych)
- Podstawowa walidacja danych wejściowych

### 10.3 Założenia Biznesowe

- System przeznaczony do celów edukacyjnych
- Obsługa podstawowych procesów magazynowych
- Prostota użytkowania nad zaawansowanymi funkcjami
- Możliwość demonstracji kluczowych koncepcji zarządzania magazynem
