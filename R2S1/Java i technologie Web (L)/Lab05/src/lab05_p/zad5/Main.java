// Oliwer Pawelski, 24INF-SP/A

/*
Korzystając z obiektowego modelu programowania należy zaprojektować oraz
wykonać implementację kodu realizującego symulację funkcjonalności ekspresu do
kawy. Należy wykonać API sterujące ekspresem oraz zrealizować poniższe założenia
projektowe:
    - ekspres do kawy składa się z komponentów takich jak:
    grzałka do mleka, grzałka do wody, pojemnika na kubki, zbiorników na wodę,
    elementów wykonawczych takich jak pompy do wody, pompy do mleka,
    odseparowanych pojemników na ziarna kawy i cukier, panelu przedniego
    posiadającego włącznik on/off oraz diodę informującą o stanie urządzenia,

    - ekspres posiada interfejs dotykowy odpowiedzialny za sterowanie urządzeniem,

    - użytkownik wybiera rodzaj kawy, wskazuje ilość poszczególnych składników takich
    jak typ i ilość mleka, kawy, cukru,

    - użytkownik wybiera kawę spośród dostępnych propozycji w ekspresie (5 gatunków)

    - jednymi z akcji jakie wykonuje ekspres są: mielenie kawy, przesypywanie do głowicy,
    włączanie grzałki, uruchamianie pomp wody i/lub mleka, które są przesyłane
    przewodami z pojemnika do głowicy,

    - możliwa jest regulacja ciśnienia oraz temperatury wody i/lub mleka
    przepływającego w celu zachowania optymalnych dla danego gatunku kawy
    parametrów tłoczonej wody i/lub mleka,

    - należy pamiętać o czujnikach występujących w urządzeniu: ciśnienia wody i mleka,
    poziomu wody, mleka, kawy, cukru oraz resztek zmielonej kawy stanowiącej odpad
    po zaparzeniu oraz temperatury wody i mleka.

    - sterowanie przez użytkownika ekspresem powinno przebiegać w prosty i przejrzysty
    sposób umożliwiając dodawanie własnych przepisów kawy.
    Aplikacja działa w sposób wielowątkowy. Należy wziąć pod uwagę zasady DRY, KISS,
    YAGNI oraz pamiętać o zachowaniu „czystego kodu”.
*/

package lab05_p.zad5;

public class Main {
    public static void main(String[] args) {
        EkspresDoKawy ekspres = new EkspresDoKawy();
        PanelSterujacy panel = new PanelSterujacy(ekspres);

        while(true) { // Nieskończona pętla
            panel.wybierzKawe();
        }
    }
}


