# Define the customer name and order details
customer_name = "Jan Kowalski"
order_items = [
    {"name": "książka", "quantity": 1, "price": 199.99},
    {"name": "zeszyt", "quantity": 2, "price": 7.89}
]
store_name = "TwojFantastycznySklep"

# Calculate the total cost
order_lines = []
total_sum = 0
for item in order_items:
    item_total = item["quantity"] * item["price"]
    total_sum += item_total
    order_lines.append(
        "- {name}, szt {quantity}, cena {price:.2f}zł, razem {item_total:.2f}zł".format(
            name=item["name"],
            quantity=item["quantity"],
            price=item["price"],
            item_total=item_total
        )
    )

# Create the email template
email_template = """\
Witaj {customer_name},

Dziękujemy za złożenie zamówienia w naszym sklepie. Oto szczegóły Twojego zamówienia:

{order_details}

SUMA: {total_sum:.2f}zł

Pozdrawiamy,
zespół {store_name}
"""

# Format the email with the provided data
email_content = email_template.format(
    customer_name=customer_name,
    order_details="\n".join(order_lines),
    total_sum=total_sum,
    store_name=store_name
)

# Display the email content
print(email_content)