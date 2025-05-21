# Wartość eps można znaleźć również interakcyjnie w sposób przybliżony, korzystając z definicji -
# startujemy od pewnej wartości poszukiwanej zmiennej, np. epsilon = 1, a następnie dzielimy ja
# na pół tak długo, aż po dodaniu do jedności wynik nie zmieni się (tzn. 1+epsilon = 1). Należy napisać skrypt
# w Pythonie wyznaczający precyzję maszynowa wykonywanych obliczeń.

# Wyznaczenie precyzji maszynowej — szukamy najmniejszego epsilon,
# dla którego 1 + epsilon != 1

epsilon = 1.0
while 1.0 + epsilon != 1.0:
    epsilon /= 2

# Ostatnia wartość epsilon, dla której warunek był jeszcze spełniony, to 2 * epsilon
epsilon *= 2
print(f"Przybliżona precyzja maszynowa: {epsilon}")