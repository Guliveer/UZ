# Napisać program kopier, kopiujący zawartość jednego katalogu do innego. Skrypt powinien pytać się o katalog źródłowy, następnie o katalog docelowy i skopiować wszystkie pliki. Następnie należy zmodyfikować skrypt tak, aby kopiował wraz z podkatalogami.

#!/bin/bash

echo "Podaj katalog źródłowy:"
read src

echo "Podaj katalog docelowy:"
read dest

cp -r "$src" "$dest"

echo "Skopiowano katalog $src do $dest."