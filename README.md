# orderservice
OrderService:
Git URL: https://github.com/kalpanagadde/orderservice.git<br>
OrderItemService:
Git URL:https://github.com/kalpanagadde/orderitem.git

Buid Instructions:<br>
Step1: Clone above repositories using git clone.<br>
Step2: Navigate to the root project folder in command prompt.<br>
Step4: Execute mvn install command.

How To Run & Use:<br>
Step1: use java -jar <jar-file-name><br>
Step2: Use below APIs for OrderItem Service<br>
	GET: http://localhost:8080/orderitem/{id}<br>
	Parameters: Replace {id} with productCode.<br>
	POST: http://localhost:8080/orderitem/<br>
	Request Body:<br>
  {
    "quantity": 2,
    "productName": "bread",
    "price": 40
  }
	
Step3: Use below APIs for Order Service<br>
	GET: http://localhost:8002/order/{id}<br>
	Parameters: Replace {id} with orderId<br>
	POST: http://localhost:8002/order/<br>
	Request Body:<br>
  {
    "customerId" : 1,
    "customerName": "someName",
    "shippingAddress": "someAddress",
    "quantity": 110,
    "productCode" : 2
}
