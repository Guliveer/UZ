# Szczegółowe Przypadki Użycia - System Zarządzania Magazynami

## Wprowadzenie

Niniejszy dokument zawiera szczegółowe opisy 4 najważniejszych przypadków użycia dla systemu zarządzania magazynami na poziomie studenckim. Wybrane przypadki reprezentują różne aspekty systemu i różne role użytkowników.

---

## 1. UC03 - Zarządzanie Magazynami

### Nazwa przypadku użycia

**Zarządzanie magazynami**

### Aktor główny

**Administrator**

### Cel

Umożliwienie administratorowi tworzenia, edycji i zarządzania magazynami w ramach organizacji, w tym przypisywania użytkowników do konkretnych magazynów.

### Warunki wstępne

- Administrator jest zalogowany w systemie
- W systemie istnieje co najmniej jedna organizacja
- Administrator ma pełne uprawnienia administracyjne

### Scenariusz główny

1. Administrator wybiera organizację z listy dostępnych organizacji
2. System wyświetla listę istniejących magazynów w wybranej organizacji
3. Administrator wybiera opcję "Dodaj nowy magazyn"
4. System wyświetla formularz tworzenia magazynu z polami:
   - Nazwa magazynu (wymagane)
   - Kod magazynu (wymagane, unikalny)
   - Adres magazynu (wymagane)
   - Opis magazynu (opcjonalne)
   - Status (aktywny/nieaktywny)
5. Administrator wypełnia wszystkie wymagane pola formularza
6. Administrator zatwierdza formularz przyciskiem "Zapisz"
7. System waliduje wprowadzone dane:
   - Sprawdza czy nazwa nie jest pusta
   - Weryfikuje unikalność kodu magazynu w ramach organizacji
   - Sprawdza poprawność formatu danych
8. System zapisuje nowy magazyn w bazie danych
9. System przypisuje magazyn do wybranej organizacji
10. System wyświetla komunikat o pomyślnym utworzeniu magazynu
11. System przekierowuje do listy magazynów z nowo utworzonym magazynem

### Scenariusze alternatywne

**7a. Dane formularza są niepoprawne:**

- 7a.1. System wyświetla komunikaty błędów przy odpowiednich polach
- 7a.2. System pozostaje na formularzu umożliwiając poprawę danych
- 7a.3. Powrót do kroku 5

**7b. Kod magazynu już istnieje w organizacji:**

- 7b.1. System wyświetla komunikat "Magazyn o podanym kodzie już istnieje w tej organizacji"
- 7b.2. System podświetla pole "Kod magazynu"
- 7b.3. Powrót do kroku 5

**8a. Błąd zapisu do bazy danych:**

- 8a.1. System wyświetla komunikat "Wystąpił błąd podczas zapisywania magazynu"
- 8a.2. System loguje błąd w systemie
- 8a.3. Powrót do kroku 5

**Rozszerzenie - Edycja istniejącego magazynu:**

- 3a.1. Administrator wybiera istniejący magazyn z listy
- 3a.2. Administrator wybiera opcję "Edytuj"
- 3a.3. System wyświetla formularz z wypełnionymi danymi magazynu
- 3a.4. Kontynuacja od kroku 5

**Rozszerzenie - Przypisywanie użytkowników:**

- 11a.1. Administrator wybiera opcję "Zarządzaj użytkownikami" dla magazynu
- 11a.2. System wyświetla listę użytkowników organizacji
- 11a.3. Administrator zaznacza użytkowników do przypisania
- 11a.4. System zapisuje przypisania użytkowników do magazynu

### Warunki końcowe

**Sukces:**

- Nowy magazyn został utworzony i zapisany w systemie
- Magazyn jest przypisany do wybranej organizacji
- Magazyn pojawia się na liście magazynów organizacji
- Administrator otrzymał potwierdzenie operacji

**Niepowodzenie:**

- Magazyn nie został utworzony
- Dane pozostają w formularzu do poprawy
- System wyświetla odpowiednie komunikaty błędów

---

## 2. UC07 - Przyjęcie Towaru (PZ)

### Nazwa przypadku użycia

**Przyjęcie towaru do magazynu (PZ)**

### Aktor główny

**Użytkownik**

### Cel

Zarejestrowanie przyjęcia towaru do magazynu poprzez utworzenie dokumentu PZ i aktualizację stanów magazynowych.

### Warunki wstępne

- Użytkownik jest zalogowany w systemie
- Użytkownik ma przypisany dostęp do co najmniej jednego magazynu
- W systemie istnieją produkty do przyjęcia
- W magazynie istnieją lokacje do składowania towaru

### Scenariusz główny

1. Użytkownik wybiera magazyn z listy dostępnych magazynów
2. System wyświetla główny panel magazynu
3. Użytkownik wybiera opcję "Przyjęcie towaru (PZ)"
4. System wyświetla formularz przyjęcia towaru z polami:
   - Numer dokumentu PZ (wymagane)
   - Data przyjęcia (automatycznie ustawiona na dzisiaj)
   - Wyszukiwanie produktu (wymagane)
   - Ilość (wymagane)
   - Lokacja docelowa (wymagane)
   - Uwagi (opcjonalne)
5. Użytkownik wprowadza unikalny numer dokumentu PZ
6. Użytkownik wyszukuje produkt wpisując nazwę lub kod produktu
7. System wyświetla listę pasujących produktów
8. Użytkownik wybiera właściwy produkt z listy
9. System wyświetla szczegóły wybranego produktu (nazwa, kod, jednostka miary)
10. Użytkownik wprowadza ilość przyjmowanego towaru
11. System wyświetla listę dostępnych lokacji w magazynie
12. Użytkownik wybiera lokację docelową dla towaru
13. System sprawdza pojemność wybranej lokacji
14. Użytkownik opcjonalnie dodaje uwagi do operacji
15. Użytkownik zatwierdza operację przyciskiem "Przyjmij towar"
16. System waliduje wszystkie wprowadzone dane
17. System sprawdza czy lokacja nie jest zablokowana
18. System tworzy rekord operacji przyjęcia w bazie danych
19. System aktualizuje stan magazynowy produktu w wybranej lokacji
20. System zapisuje operację w historii operacji magazynowych
21. System wyświetla komunikat potwierdzający przyjęcie towaru
22. System wyświetla podsumowanie operacji z nowym stanem magazynowym

### Scenariusze alternatywne

**6a. Nie znaleziono produktu:**

- 6a.1. System wyświetla komunikat "Nie znaleziono produktu o podanych kryteriach"
- 6a.2. System oferuje opcję "Poproś administratora o dodanie produktu"
- 6a.3. Powrót do kroku 6

**5a. Numer dokumentu PZ już istnieje:**

- 5a.1. System wyświetla komunikat "Dokument PZ o podanym numerze już istnieje"
- 5a.2. System podświetla pole numeru dokumentu
- 5a.3. Powrót do kroku 5

**13a. Lokacja przekroczy pojemność:**

- 13a.1. System wyświetla ostrzeżenie "Przyjęcie spowoduje przekroczenie pojemności lokacji"
- 13a.2. System wyświetla aktualny stan i maksymalną pojemność
- 13a.3. Użytkownik może kontynuować lub wybrać inną lokację
- 13a.4. Jeśli kontynuuje - przejście do kroku 14
- 13a.5. Jeśli wybiera inną lokację - powrót do kroku 12

**17a. Lokacja jest zablokowana:**

- 17a.1. System wyświetla komunikat "Wybrana lokacja jest zablokowana (prawdopodobnie trwa inwentaryzacja)"
- 17a.2. System nie pozwala na kontynuację operacji
- 17a.3. Powrót do kroku 12

**10a. Wprowadzono nieprawidłową ilość:**

- 10a.1. System sprawdza czy ilość jest liczbą dodatnią
- 10a.2. System wyświetla komunikat "Ilość musi być liczbą dodatnią"
- 10a.3. Powrót do kroku 10

**18a. Błąd zapisu operacji:**

- 18a.1. System wyświetla komunikat "Wystąpił błąd podczas zapisywania operacji"
- 18a.2. System loguje błąd
- 18a.3. Operacja nie zostaje wykonana
- 18a.4. Powrót do kroku 15

### Warunki końcowe

**Sukces:**

- Dokument PZ został utworzony i zapisany w systemie
- Stan magazynowy produktu został zwiększony o przyjętą ilość
- Operacja została zarejestrowana w historii operacji
- Użytkownik otrzymał potwierdzenie operacji z aktualnym stanem

**Niepowodzenie:**

- Dokument PZ nie został utworzony
- Stany magazynowe pozostały bez zmian
- System wyświetla odpowiednie komunikaty błędów
- Użytkownik może poprawić dane i spróbować ponownie

---

## 3. UC08 - Wydanie Towaru (WZ)

### Nazwa przypadku użycia

**Wydanie towaru z magazynu (WZ)**

### Aktor główny

**Użytkownik**

### Cel

Zarejestrowanie wydania towaru z magazynu poprzez utworzenie dokumentu WZ i aktualizację stanów magazynowych z kontrolą dostępności towaru.

### Warunki wstępne

- Użytkownik jest zalogowany w systemie
- Użytkownik ma dostęp do magazynu
- W magazynie znajdują się produkty na stanie (ilość > 0)
- Produkty są dostępne w konkretnych lokacjach

### Scenariusz główny

1. Użytkownik wybiera magazyn z listy dostępnych magazynów
2. System wyświetla główny panel magazynu
3. Użytkownik wybiera opcję "Wydanie towaru (WZ)"
4. System wyświetla formularz wydania towaru z polami:
   - Numer dokumentu WZ (wymagane)
   - Data wydania (automatycznie ustawiona na dzisiaj)
   - Wyszukiwanie produktu (wymagane)
   - Lokacja źródłowa (wymagane)
   - Ilość do wydania (wymagane)
   - Odbiorca (opcjonalne)
   - Uwagi (opcjonalne)
5. Użytkownik wprowadza unikalny numer dokumentu WZ
6. Użytkownik wyszukuje produkt wpisując nazwę lub kod
7. System wyświetla listę pasujących produktów
8. Użytkownik wybiera produkt z listy
9. System wyszukuje wszystkie lokacje zawierające wybrany produkt
10. System wyświetla listę lokacji z aktualnym stanem produktu w każdej lokacji
11. Użytkownik wybiera lokację źródłową z listy
12. System wyświetla maksymalną dostępną ilość w wybranej lokacji
13. Użytkownik wprowadza ilość do wydania
14. System sprawdza czy wprowadzona ilość nie przekracza dostępnej ilości
15. Użytkownik opcjonalnie wprowadza dane odbiorcy towaru
16. Użytkownik opcjonalnie dodaje uwagi do operacji
17. Użytkownik zatwierdza operację przyciskiem "Wydaj towar"
18. System ponownie sprawdza dostępność towaru (zabezpieczenie przed równoczesnym wydaniem)
19. System waliduje wszystkie wprowadzone dane
20. System tworzy rekord operacji wydania w bazie danych
21. System zmniejsza stan magazynowy produktu w wybranej lokacji
22. System zapisuje operację w historii operacji magazynowych
23. System wyświetla komunikat potwierdzający wydanie towaru
24. System wyświetla podsumowanie operacji z nowym stanem magazynowym

### Scenariusze alternatywne

**9a. Produkt nie znajduje się w żadnej lokacji:**

- 9a.1. System wyświetla komunikat "Wybrany produkt nie jest dostępny w magazynie"
- 9a.2. System wyświetla aktualny stan produktu (0)
- 9a.3. Powrót do kroku 6

**5a. Numer dokumentu WZ już istnieje:**

- 5a.1. System wyświetla komunikat "Dokument WZ o podanym numerze już istnieje"
- 5a.2. System podświetla pole numeru dokumentu
- 5a.3. Powrót do kroku 5

**14a. Ilość do wydania przekracza dostępną ilość:**

- 14a.1. System wyświetla komunikat "Ilość do wydania przekracza dostępną ilość w lokacji"
- 14a.2. System wyświetla dostępną ilość: "Dostępne: X jednostek"
- 14a.3. System podświetla pole ilości
- 14a.4. Powrót do kroku 13

**13a. Wprowadzono nieprawidłową ilość:**

- 13a.1. System sprawdza czy ilość jest liczbą dodatnią większą od 0
- 13a.2. System wyświetla komunikat "Ilość musi być liczbą dodatnią większą od 0"
- 13a.3. Powrót do kroku 13

**18a. Stan towaru zmienił się podczas operacji:**

- 18a.1. System wykrywa że stan towaru został zmieniony przez innego użytkownika
- 18a.2. System wyświetla komunikat "Stan towaru został zmieniony. Sprawdź aktualną dostępność"
- 18a.3. System odświeża dane o dostępności
- 18a.4. Powrót do kroku 12

**20a. Błąd zapisu operacji:**

- 20a.1. System wyświetla komunikat "Wystąpił błąd podczas zapisywania operacji"
- 20a.2. System loguje błąd w systemie
- 20a.3. Operacja nie zostaje wykonana
- 20a.4. Stany magazynowe pozostają bez zmian
- 20a.5. Powrót do kroku 17

**Rozszerzenie - Wydanie z wielu lokacji:**

- 11a.1. Użytkownik chce wydać więcej niż dostępne w jednej lokacji
- 11a.2. System oferuje opcję "Wydaj z wielu lokacji"
- 11a.3. Użytkownik wybiera kolejne lokacje i ilości
- 11a.4. System sprawdza łączną dostępność
- 11a.5. Kontynuacja od kroku 17

### Warunki końcowe

**Sukces:**

- Dokument WZ został utworzony i zapisany w systemie
- Stan magazynowy produktu został zmniejszony o wydaną ilość
- Operacja została zarejestrowana w historii operacji
- Użytkownik otrzymał potwierdzenie operacji z aktualnym stanem
- Jeśli podano odbiorcę, informacja została zapisana

**Niepowodzenie:**

- Dokument WZ nie został utworzony
- Stany magazynowe pozostały bez zmian
- System wyświetla odpowiednie komunikaty błędów
- Użytkownik może poprawić dane i spróbować ponownie

---

## 4. UC12 - Wyszukiwanie Produktów

### Nazwa przypadku użycia

**Wyszukiwanie produktów w systemie**

### Aktor główny

**Użytkownik** lub **Administrator**

### Cel

Umożliwienie szybkiego odnalezienia produktów w systemie na podstawie różnych kryteriów wyszukiwania oraz wyświetlenie szczegółowych informacji o produkcie wraz z jego stanami magazynowymi.

### Warunki wstępne

- Użytkownik jest zalogowany w systemie
- W systemie istnieją produkty do wyszukiwania
- Użytkownik ma dostęp do co najmniej jednego magazynu (dla wyświetlania stanów)

### Scenariusz główny

1. Użytkownik wybiera opcję "Wyszukaj produkty" z menu głównego
2. System wyświetla interfejs wyszukiwania z polami:
   - Pole tekstowe wyszukiwania (nazwa lub kod produktu)
   - Filtr kategorii (lista rozwijana)
   - Filtr magazynu (lista rozwijana - tylko magazyny dostępne dla użytkownika)
   - Opcja "Pokaż tylko produkty na stanie"
3. Użytkownik wprowadza frazę wyszukiwania w pole tekstowe
4. System automatycznie rozpoczyna wyszukiwanie po wprowadzeniu minimum 3 znaków
5. System przeszukuje bazę danych produktów według kryteriów:
   - Nazwa produktu (wyszukiwanie częściowe, ignorowanie wielkości liter)
   - Kod produktu (wyszukiwanie dokładne i częściowe)
   - Opis produktu (wyszukiwanie częściowe)
6. System wyświetla listę znalezionych produktów w tabeli zawierającej:
   - Nazwa produktu
   - Kod produktu
   - Kategoria
   - Jednostka miary
   - Łączny stan we wszystkich dostępnych magazynach
   - Status produktu (aktywny/nieaktywny)
7. System wyświetla liczbę znalezionych produktów
8. Użytkownik przegląda listę wyników
9. Użytkownik klika na wybrany produkt z listy
10. System wyświetla szczegółowy widok produktu zawierający:
    - Pełne dane produktu (nazwa, kod, opis, kategoria, jednostka miary)
    - Tabela stanów magazynowych z podziałem na magazyny i lokacje
    - Historia ostatnich operacji na produkcie (ostatnie 10 operacji)
    - Opcje akcji (w zależności od uprawnień użytkownika)

### Scenariusze alternatywne

**4a. Użytkownik wprowadził mniej niż 3 znaki:**

- 4a.1. System wyświetla podpowiedź "Wprowadź co najmniej 3 znaki aby rozpocząć wyszukiwanie"
- 4a.2. System nie wykonuje wyszukiwania
- 4a.3. Oczekiwanie na wprowadzenie kolejnych znaków

**5a. Nie znaleziono żadnych produktów:**

- 5a.1. System wyświetla komunikat "Nie znaleziono produktów spełniających kryteria wyszukiwania"
- 5a.2. System wyświetla sugestie:
  - "Sprawdź pisownię"
  - "Użyj ogólniejszych kryteriów wyszukiwania"
  - "Skontaktuj się z administratorem w sprawie dodania produktu"
- 5a.3. Powrót do kroku 3

**Rozszerzenie - Filtrowanie wyników:**

- 8a.1. Użytkownik wybiera kategorię z listy filtrów
- 8a.2. System filtruje wyniki według wybranej kategorii
- 8a.3. System aktualizuje listę wyników
- 8a.4. Kontynuacja od kroku 8

**Rozszerzenie - Filtrowanie według magazynu:**

- 8b.1. Użytkownik wybiera konkretny magazyn z listy
- 8b.2. System wyświetla tylko produkty dostępne w wybranym magazynie
- 8b.3. System aktualizuje kolumnę stanów pokazując tylko stan z wybranego magazynu
- 8b.4. Kontynuacja od kroku 8

**Rozszerzenie - Pokazywanie tylko produktów na stanie:**

- 8c.1. Użytkownik zaznacza opcję "Pokaż tylko produkty na stanie"
- 8c.2. System filtruje wyniki pokazując tylko produkty z ilością > 0
- 8c.3. System aktualizuje listę wyników
- 8c.4. Kontynuacja od kroku 8

**3a. Wyszukiwanie po kodzie kreskowym (rozszerzenie):**

- 3a.1. Użytkownik wprowadza kod kreskowy w pole wyszukiwania
- 3a.2. System rozpoznaje format kodu kreskowego
- 3a.3. System wyszukuje produkt po kodzie EAN/UPC
- 3a.4. Jeśli znaleziono dokładnie jeden produkt - przejście do kroku 10
- 3a.5. Jeśli nie znaleziono - przejście do scenariusza 5a

**10a. Użytkownik nie ma uprawnień do wyświetlenia szczegółów:**

- 10a.1. System sprawdza uprawnienia użytkownika
- 10a.2. System wyświetla ograniczone informacje o produkcie
- 10a.3. System ukrywa szczegółowe stany magazynowe z lokacji niedostępnych dla użytkownika
- 10a.4. System wyświetla komunikat "Ograniczone uprawnienia - niektóre informacje są ukryte"

### Warunki końcowe

**Sukces:**

- Użytkownik znalazł poszukiwany produkt
- System wyświetlił szczegółowe informacje o produkcie
- Użytkownik ma dostęp do aktualnych stanów magazynowych
- System zalogował operację wyszukiwania (opcjonalnie)

**Sukces częściowy:**

- System wyświetlił listę produktów pasujących do kryteriów
- Użytkownik może dalej przeglądać wyniki lub doprecyzować wyszukiwanie
- Nie wszystkie produkty mogą być dostępne dla użytkownika

**Niepowodzenie:**

- Nie znaleziono żadnych produktów spełniających kryteria
- System wyświetlił odpowiednie komunikaty i sugestie
- Użytkownik może zmodyfikować kryteria wyszukiwania

---

## Podsumowanie

Przedstawione przypadki użycia obejmują kluczowe funkcjonalności systemu zarządzania magazynami:

1. **UC03 - Zarządzanie magazynami** - reprezentuje funkcjonalności administracyjne
2. **UC07 - Przyjęcie towaru (PZ)** - podstawowa operacja magazynowa zwiększająca stany
3. **UC08 - Wydanie towaru (WZ)** - podstawowa operacja magazynowa zmniejszająca stany
4. **UC12 - Wyszukiwanie produktów** - funkcjonalność wspólna dla wszystkich użytkowników

Każdy przypadek użycia został opisany zgodnie ze standardowym formatem zawierającym:

- Nazwę i aktora głównego
- Cel i warunki wstępne
- Szczegółowy scenariusz główny
- Scenariusze alternatywne i rozszerzenia
- Warunki końcowe

Opisy są dostosowane do poziomu studenckiego - szczegółowe ale nie nadmiernie skomplikowane, praktyczne i realistyczne dla systemu edukacyjnego.
