# Lab03

1.  Użyć polecenia `Get-Help`, aby otrzymać pomoc polecenia `Set-Location`.

    > `Get-Help Set-Location`

2.  Wyświetlić listę usług systemowych.

    > `Get-Service`

3.  Wyświetlić listę procesów.

    > `Get-Process`

4.  Wyświetlić listę procesów, których ID jest większe od 716.

    > `Get-Process | Where-Object {$_.Id -gt 716}`

5.  Wyświetlić listę procesów, których PM jest mniejsze od 5916.

    > `Get-Process | Where-Object {$_.PM -lt 5916}`

6.  Wyświetlić listę procesów, których nazwa zaczyna się od litery „c”.

    > `Get-Process | Where-Object {$_.Name -like 'c*'}`

7.  Wyświetlić aktywne usługi systemowe w formie listy.

    > `Get-Service | Where-Object {$_.Status -eq 'Running'} | Format-List`

8.  Pogrupować usługi systemowe wg statusu oraz wyświetlić alfabetycznie wg nazwy.

    > `Get-Service | Sort-Object Name | Group-Object Status`

9.  Napisać skrypt, który utworzy katalog `C:\PS` i umieści w nim plik z 10 najbardziej zużywającymi pamięć procesami.

    > [09.ps1](./09.ps1)

10. Posortować procesy wg ID malejąco.

    > `Get-Process | Sort-Object Id -Descending`

11. Wyświetlić właściwości obiektu `Get-Process`.

    > `Get-Process | Get-Member -MemberType Property`

12. Wyświetlić listę aliasów obiektów (`Get-Alias`).

    > `Get-Alias`

13. Zmienić alias dla obiektu `Get-Process`.

    > `New-Alias gps Get-Process`

14. Zmienić tło konsoli na czarne.

    > ```
    > $Host.UI.RawUI.BackgroundColor = 'Black'
    > $Host.UI.RawUI.ForegroundColor = 'White'
    > Clear-Host
    > ```

15. Pobrać dane z klawiatury i wyświetlić je w konsoli.

    > ```
    > $userInput = Read-Host "Wprowadź dane"
    > Write-Host "Wprowadziłeś: $userInput"
    > ```

16. Utworzyć skrypt, który wyświetli 10 losowych liczb w losowych kolorach.

    > [16.ps1](./16.ps1)

17. Wyświetlić listę dostępnych poleceń (`Get-Command`) typu alias w formie listy.

    > `Get-Command -CommandType Alias | Format-List`

18. Wyświetlić wszystkie cmdlety, które działają jako proces.

    > `Get-Command -Name *Process* -CommandType Cmdlet`

19. Zapoznać się z apletem `Get-Acl` (wyświetlić pomoc dla apletu)

    > `Get-Help Get-Acl`

20. Wyświetlić zawartość pliku z zapisanymi wcześniej usługami.
    > `Get-Content "C:\PS\PLIK"`
