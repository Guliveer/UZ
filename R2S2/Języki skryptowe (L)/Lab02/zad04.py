# Napisać program sklejający listy a,b,c kolejnych 10 liczb całkowitych, zespolonych i rzeczywistych
# (dla liczb całkowitych i zespolonych i rzeczywistych przyjąć 0 za początek listy, dla
# 'kolejnych' liczb rzeczywistych przyjąć krok 0.1).

a = list(range(10))
b = [complex(i, i) for i in range(10)]
c = [i/10 for i in range(10)]

result = a + b + c
print(result)
