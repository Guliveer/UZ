| IP              | Klasa  | Użyteczny jako IP hosta? (T/N) | Uzasadnienie |
|-----------------|--------|-------------------------------|--------------|
| **7.2.1.2**     | A      | T                             | Adres w klasie A (1-127), nie jest adresem sieciowym ani rozgłoszeniowym. |
| **192.168.2.3** | C      | T                             | Adres w klasie C (192-223), jest to adres hosta w sieci prywatnej. |
| **208.9.7.2**   | C      | T                             | Adres w klasie C (192-223), jest to adres hosta w sieci publicznej. |
| **127.253.9.123** | A      | N                             | Adres w pętli lokalnej (127.0.0.0 - 127.255.255.255), służy do komunikacji wewnętrznej, nie może być użyty do adresowania hosta w normalnej sieci. |
| **226.189.12.132** | D      | N                             | Adres w klasie D (224-239), multicastowy, nie może być użyty do adresowania hostów. |
| **255.56.11.178** | specjalny | N                             | Adres zawierający 255 w pierwszym oktetu oznacza adres rozgłoszeniowy lub specjalny, nie jest to standardowy adres hosta. |
| **176.289.2.23** | błąd   | N                             | Adres nieprawidłowy (289 > 255), nie jest poprawnym adresem IP. |
| **123.0.12.223** | A      | T                             | Adres w klasie A (1-127), nie jest adresem sieciowym ani rozgłoszeniowym, można go użyć jako adres hosta. |
| **246.89.112.132** | E      | N                             | Adres w klasie E (240-255), zarezerwowany do użytku eksperymentalnego, nie może być użyty do zaadresowania hosta. |
| **255.255.11.178** | specjalny | N                             | Adres rozgłoszeniowy lub specjalny (zawiera 255 w dwóch pierwszych oktetach), nie może być użyty do zaadresowania hosta. |
| **6.255.255.23** | A      | T                             | Adres w klasie A (1-127), nie jest adresem sieciowym ani rozgłoszeniowym, można go użyć jako adres hosta. |
| **12.0.0.255**  | A      | N                             | Adres rozgłoszeniowy w klasie A (dla sieci 12.0.0.0). |

### Kluczowe wyjaśnienia:
1. **Adresy klasy A** (1.0.0.0 - 127.255.255.255) mogą być używane jako adresy hostów, o ile nie są to adresy sieciowe (np. `1.0.0.0`) ani adresy rozgłoszeniowe (np. `127.255.255.255`).
2. **Adresy klasy B** (128.0.0.0 - 191.255.255.255) i **klasa C** (192.0.0.0 - 223.255.255.255) mogą być używane jako adresy hostów, o ile nie są to adresy sieciowe ani rozgłoszeniowe.
3. **Adresy klasy D** (224.0.0.0 - 239.255.255.255) są przeznaczone do multicastu i nie mogą być używane do adresowania hostów.
4. **Adresy klasy E** (240.0.0.0 - 255.255.255.255) są zarezerwowane do eksperymentalnych zastosowań i nie mogą być używane do adresowania hostów.
5. **Adresy specjalne**, takie jak te zawierające `255` w jednym z oktetów (np. `255.255.11.178`) lub w całości (np. `255.255.255.255`), są adresami rozgłoszeniowymi lub przeznaczone do specjalnych celów i nie mogą być używane do adresowania hostów.
