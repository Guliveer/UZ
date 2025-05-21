# W wyniku wystąpienia zjawiska zaokrąglenia liczba 0,2 została zapisana w pamięci maszynowej
# jako 0,1875. Wyznaczyć popełniony błąd bezwzględny i względny. Czy można oszacować na
# podstawie policzonych błędów dokładność reprezentacji zmiennoprzecinkowej użytej maszyny
# cyfrowej (ilość bitów mantysy)?

real = 0.2
stored = 0.1875

abs_error = abs(real - stored)
rel_error = abs_error / abs(real)

print(f"Błąd bezwzględny: {abs_error}")
print(f"Błąd względny: {rel_error:.4%}")

# Błąd względny można powiązać z dokładnością mantysy — 0.1875
# to 3 bity po przecinku (0.0011), więc mantysa ma tylko 4 bity.