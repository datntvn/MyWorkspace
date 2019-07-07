# ShoppingCart

Below are commands curl that convinient for developing:

Add new product to cart

curl -X POST -d '{ "product": "Pears" }' -i -H "Content-Type: application/json" http://localhost:9000/api/add-to-cart/21

curl -X POST -d '{ "product": "Cherries" }' -i -H “Content-Type: application/json” http://localhost:9000/api/add-to-cart/23

curl -X POST -d '{ "product": "Cherries" }' -i -H “Content-Type: application/json” http://localhost:9000/api/add-to-cart/21

curl -X POST -d '{ "product": "Orange" }' -i -H “Content-Type: application/json” http://localhost:9000/api/add-to-cart/21

curl -X POST -d '{ "product": "Lemon" }' -i -H “Content-Type: application/json” http://localhost:9000/api/add-to-cart/21

Delete:

curl -X POST -d '{ "product": "abc" }' -i -H "Content-Type: application/json" http://localhost:9000/api/cart/21

curl -X POST -d '{ "product": "Pears" }' -i -H "Content-Type: application/json" http://localhost:9000/api/cart/21

curl -X POST -d '{ "product": "Orange" }' -i -H "Content-Type: application/json" http://localhost:9000/api/cart/21

curl -X POST -d '{ "product": "Apple" }' -i -H "Content-Type: application/json" http://localhost:9000/api/cart/21


