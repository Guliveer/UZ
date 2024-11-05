### **a) Zapisz adres sieci 200.10.57.0 w postaci binarnej:**
Adres 200.10.57.0 zapisujemy w systemie binarnym, konwertując każdy oktet na postać binarną:

- **200** = `11001000`  
- **10**  = `00001010`  
- **57**  = `00111001`  
- **0**   = `00000000`

Adres IP 200.10.57.0 w postaci binarnej to:

```
11001000.00001010.00111001.00000000
```

### **b) Który(e) oktet(y) reprezentują część sieciową, a który(e) część hostów w adresie 200.10.57.0?**

Standardowy adres sieci klasy C ma maskę **255.255.255.0**, co oznacza, że:

- Pierwsze 3 oktety (`200.10.57`) są częścią **sieciową**.
- Ostatni oktet (`0`) jest częścią **hostów**.

Czyli:
- Część sieciowa: **200.10.57**
- Część hostów: **0**

### **c) Ile bitów należy pożyczyć z części hostów adresu sieci, aby uzyskać 3 podsieci zawierające co najmniej 20 hostów?**

Zacznijmy od obliczeń.

#### Wymagana liczba hostów w podsieci:
Każda podsieć musi mieć co najmniej 20 hostów. Aby wyliczyć, ile bitów musimy pożyczyć, użyjemy wzoru na liczbę dostępnych hostów w danej liczbie bitów:

Liczba hostów = 2^n - 2

Gdzie **n** to liczba bitów użytych na część hostów w masce.

Musimy zapewnić co najmniej 20 hostów, więc rozwiązujemy nierówność:

2^n - 2 >= 20
2^n >= 22

Najmniejsza liczba bitów, która spełnia tę nierówność to **5 bitów**. Dla 5 bitów na część hostów, otrzymujemy:

2^5 - 2 = 32 - 2 = 30 hostów (więc spełnia to wymagania).

#### Liczba potrzebnych bitów do podziału na 3 podsieci:
Ponieważ potrzebujemy **3 podsieci**, pożyczymy tyle bitów, żeby utworzyć co najmniej 3 podsieci. Liczba bitów potrzebnych na podsieci to:

2^n >= 3

Minimalna liczba bitów to **2 bity**, ponieważ:

2^2 = 4 podsieci (więc wystarczy 2 bity).

#### Podsumowanie:
- Pożyczamy **5 bitów** z części hostów, aby uzyskać wystarczającą liczbę hostów w podsieci (30 hostów).
- Zostawiamy **2 bity** w części hostów na identyfikację podsieci (3 podsieci).

### **d) Jaka jest binarna postać maski użytej do podziału na podsieci?**

Zatem zmieniając maskę sieciową, otrzymujemy:

- Pierwsze 24 bity (dla sieci) pozostają bez zmian.
- Pożyczamy **5 bitów** z części hostów, co daje łącznie **29 bitów** przeznaczone na część sieciową.

Binarna postać maski wygląda następująco:

```
11111111.11111111.11111111.11111000
```

### **e) Zapisz powyższą maskę w postaci dziesiętnej:**

Aby zapisać maskę w postaci dziesiętnej, zamieniamy każdy oktet binarny na liczbę dziesiętną:

- `11111111` = 255  
- `11111111` = 255  
- `11111111` = 255  
- `11111000` = 248  

Maska w zapisie dziesiętnym to:

```
255.255.255.248
```

---

### **f) Wypełnij następującą tabelkę:**

| **Zadanie**                                                                 | **Odpowiedź**                                                                 |
|----------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| **a) Zapisz adres sieci 200.10.57.0 w postaci binarnej**                   | `11001000.00001010.00111001.00000000`                                          |
| **b) Część sieciowa i hostów w adresie 200.10.57.0**                        | Część sieciowa: `200.10.57` <br> Część hostów: `0`                             |
| **c) Liczba bitów do pożyczenia na 3 podsieci z 20 hostami**                | Pożyczyć **5 bitów** z części hostów, aby uzyskać 3 podsieci i co najmniej 20 hostów w każdej. |
| **d) Binarna postać maski po podziale na podsieci**                         | `11111111.11111111.11111111.11111000`                                          |
| **e) Maska w postaci dziesiętnej**                                          | `255.255.255.248`                                                            |

Mam nadzieję, że to pomoże! Jeśli masz dodatkowe pytania, śmiało pytaj.

| **Nr** | **Maska podsieci**     | **Adres podsieci**  | **Zasięg dopuszczalnych adresów IP hostów** | **Adresy rozgłoszeń podsieci (Broadcast)** | **Użyteczna?** |
|--------|------------------------|---------------------|--------------------------------------------|--------------------------------------------|----------------|
| **0**  | `255.255.255.248`       | `200.10.57.0`       | `200.10.57.1` do `200.10.57.30`           | `200.10.57.31`                            | Tak            |
| **1**  | `255.255.255.248`       | `200.10.57.32`      | `200.10.57.33` do `200.10.57.62`          | `200.10.57.63`                            | Tak            |
| **2**  | `255.255.255.248`       | `200.10.57.64`      | `200.10.57.65` do `200.10.57.94`          | `200.10.57.95`                            | Tak            |
| **3**  | `255.255.255.248`       | `200.10.57.96`      | `200.10.57.97` do `200.10.57.126`         | `200.10.57.127`                           | Tak            |
| **4**  | `255.255.255.248`       | `200.10.57.128`     | `200.10.57.129` do `200.10.57.158`        | `200.10.57.159`                           | Tak            |
| **5**  | `255.255.255.248`       | `200.10.57.160`     | `200.10.57.161` do `200.10.57.190`        | `200.10.57.191`                           | Tak            |
| **6**  | `255.255.255.248`       | `200.10.57.192`     | `200.10.57.193` do `200.10.57.222`        | `200.10.57.223`                           | Tak            |
| **7**  | `255.255.255.248`       | `200.10.57.224`     | `200.10.57.225` do `200.10.57.254`        | `200.10.57.255`                           | Tak            |

### **h) Jaki jest wynik operatora AND na adresie hosta X i maski podsieci?**
| **Element**                   | **Wartość**                          |
|--------------------------------|--------------------------------------|
| **Adres IP hosta (dziesiętny)**  | `192.168.1.100`                     |
| **Adres IP hosta (binarne)**   | `11000000.10101000.00000001.01100100` |
| **Maska podsieci (binarna)**   | `11111111.11111111.11111111.00000000` |
| **Rezultat operacji AND (binarna)** | `11000000.10101000.00000001.00000000` |
| **Rezultat operacji AND (dziesiętny)** | `192.168.1.0`                   |
