# System Zarządzania Magazynami - Analiza Wymagań i Funkcjonalności

## Przegląd Projektu

Ten projekt zawiera kompletną analizę wymagań i specyfikację funkcjonalności dla systemu zarządzania magazynami na poziomie studenckim. System został zaprojektowany jako uniwersalne rozwiązanie dla różnych typów organizacji (sklepy, firmy, hurtownie) z fokusem na podstawowe operacje magazynowe.

## Struktura Dokumentacji

### 📋 [Funkcjonalności Systemu](funkcjonalnosci_systemu_magazynowego.md)

Główny dokument zawierający szczegółowe opisanie wszystkich funkcjonalności systemu:

- **Role użytkowników** - Administrator i Użytkownik
- **Zarządzanie organizacjami** - tworzenie i administracja organizacji
- **Zarządzanie magazynami** - konfiguracja magazynów w organizacjach
- **Zarządzanie lokacjami** - definiowanie miejsc składowania
- **Zarządzanie produktami** - katalog produktów i kategorii
- **Operacje magazynowe** - przyjęcia, wydania, inwentaryzacja, przeniesienia
- **Funkcjonalności systemowe** - bezpieczeństwo, raporty, historia

### 🏗️ [Diagramy Architektury](diagram_architektury.md)

Wizualna reprezentacja struktury systemu zawierająca:

- **Diagram ERD** - model danych z relacjami między encjami
- **Diagram przepływu operacji** - proces wykonywania operacji magazynowych
- **Diagram ról i uprawnień** - podział funkcjonalności między role
- **Diagram architektury technicznej** - warstwy aplikacji

### 📖 [Przypadki Użycia](przypadki_uzycia.md)

Szczegółowe scenariusze użytkowania systemu:

- **Przypadki dla administratora** - zarządzanie systemem
- **Przypadki dla użytkownika** - operacje magazynowe
- **Wspólne funkcjonalności** - wyszukiwanie, raporty
- **Zarządzanie danymi** - CRUD operacje
- **Obsługa błędów** - scenariusze wyjątkowe

### 🎯 [Diagram Use-Case](diagram_use_case.md)

Wizualna reprezentacja wszystkich przypadków użycia systemu:

- **Diagram Mermaid** - kompletny diagram przypadków użycia
- **Podział na role** - Administrator vs Użytkownik
- **Relacje między przypadkami** - include, extend
- **Funkcje pomocnicze** - walidacja, kontrola uprawnień
- **Legenda kolorów** - rozróżnienie typów funkcjonalności

### 📝 [Szczegółowe Przypadki Użycia](szczegolowe_przypadki_uzycia.md)

Dokładne opisy 4 najważniejszych przypadków użycia:

- **UC03 - Zarządzanie magazynami** - funkcjonalności administracyjne
- **UC07 - Przyjęcie towaru (PZ)** - operacja zwiększająca stany
- **UC08 - Wydanie towaru (WZ)** - operacja zmniejszająca stany
- **UC12 - Wyszukiwanie produktów** - funkcjonalność wspólna

## Kluczowe Charakterystyki Systemu

### 🎯 Cel Biznesowy

- Uniwersalny system zarządzania magazynami
- Odpowiedni dla różnych typów organizacji
- Poziom złożoności dostosowany do projektów studenckich
- Fokus na podstawowych operacjach magazynowych

### 🏢 Struktura Organizacyjna

```
Organizacja → Magazyny → Lokacje → Produkty
```

### 👥 Role Użytkowników

- **Administrator**: Pełny dostęp, zarządzanie systemem
- **Użytkownik**: Operacje magazynowe w przypisanych magazynach

### 📦 Podstawowe Operacje

1. **Przyjęcia (PZ)** - wprowadzanie towarów do magazynu
2. **Wydania (WZ)** - wydawanie towarów z magazynu
3. **Inwentaryzacja** - sprawdzanie i korygowanie stanów
4. **Przeniesienia** - zmiana lokalizacji produktów

## Główne Funkcjonalności

### Dla Administratora

- ✅ Zarządzanie użytkownikami i organizacjami
- ✅ Konfiguracja magazynów i lokacji
- ✅ Zarządzanie katalogiem produktów
- ✅ Dostęp do wszystkich operacji i raportów
- ✅ Kontrola uprawnień użytkowników

### Dla Użytkownika

- ✅ Operacje magazynowe (PZ, WZ, inwentaryzacja)
- ✅ Przeglądanie stanów magazynowych
- ✅ Wyszukiwanie produktów
- ✅ Historia własnych operacji
- ✅ Podstawowe raporty

### Funkcjonalności Systemowe

- ✅ Autoryzacja i kontrola dostępu
- ✅ Historia operacji (audit log)
- ✅ Podstawowe raportowanie
- ✅ Walidacja danych
- ✅ Responsywny interfejs

## Ograniczenia i Założenia

### ❌ Funkcjonalności Poza Zakresem

- Integracje z systemami zewnętrznymi
- Zaawansowane raportowanie i analityka
- Automatyczne powiadomienia
- Zarządzanie dostawcami i zamówieniami
- Obsługa kodów kreskowych

### ✅ Założenia Techniczne

- System webowy (przeglądarka)
- Baza danych relacyjna
- Interfejs w języku polskim
- Responsywny design
- Podstawowa walidacja danych

## Model Danych

### Główne Encje

- **Organizacja** - firma/instytucja
- **Użytkownik** - osoby korzystające z systemu
- **Magazyn** - miejsce składowania towarów
- **Lokacja** - konkretne miejsce w magazynie
- **Produkt** - towary w systemie
- **Stan Magazynowy** - aktualne ilości produktów
- **Operacja Magazynowa** - historia wszystkich operacji

### Kluczowe Relacje

- Organizacja → Magazyny (1:N)
- Magazyn → Lokacje (1:N)
- Lokacja → Stany Magazynowe (1:N)
- Produkt → Stany Magazynowe (1:N)
- Użytkownik → Operacje (1:N)

## Wdrożenie i Rozwój

### Fazy Implementacji

1. **Faza 1**: Podstawowy model danych i autoryzacja
2. **Faza 2**: Zarządzanie organizacjami, magazynami, produktami
3. **Faza 3**: Operacje magazynowe (PZ, WZ)
4. **Faza 4**: Inwentaryzacja i przeniesienia
5. **Faza 5**: Raporty i finalizacja

### Możliwości Rozszerzenia

- Moduł zarządzania dostawcami
- Zaawansowane raportowanie
- Integracja z systemami ERP
- Obsługa kodów kreskowych
- Aplikacja mobilna
- Powiadomienia i alerty

## Podsumowanie

Dokumentacja zawiera kompletną analizę wymagań dla systemu zarządzania magazynami odpowiedniego dla projektów studenckich. System został zaprojektowany z uwzględnieniem:

- **Praktyczności** - funkcjonalności odpowiednie do rzeczywistych potrzeb
- **Prostoty** - nie nadmiernie skomplikowany, ale nie trywialny
- **Skalowalności** - możliwość rozszerzenia w przyszłości
- **Edukacyjności** - demonstracja kluczowych koncepcji zarządzania magazynem

Wszystkie opisane funkcjonalności są realistyczne do implementacji na poziomie studenckim i stanowią solidną podstawę do stworzenia działającego systemu zarządzania magazynami.
