This service is runing  on 2024 port 
just run this service 
url: http://localhost:2024/products
--------------------------
to get all prodcut info 
 GET / http://localhost:2024/products </br>
delet one product 
  DELETE / http://localhost:2024/products/1   </br>
get Categories list
 GET / http://localhost:2024/products/categories  </br>
GetOne Product
 GET / http://localhost:2024/products/2  </br>
find category based products
 GET / http://localhost:2024/products/category/jewelery  </br>
Add New Product
  POST / https://localhost:2024/products   </br>

body  </br>
```
{
    "title": "Iphone 6",
    "description": "IOS based os with 2 gb of ram and 32 gb of storege Space",
    "price": 14000.00, 
    "category": {
        "id": 0.0,
        "name": "mobile"
    },
    "image": "https://images.app.goo.gl/ejD138nbjovH6n8bA"
}
```

PUT / http://localhost:2024/products/1  </br>

body :
```
{
    "title": "Iphone 6",
    "description": "IOS based os with 2 gb of ram and 32 gb of storege Space",
    "price": 14000.00, 
    "category": {
        "id": 0.0,
        "name": "mobile"
    },
    "image": "https://images.app.goo.gl/ejD138nbjovH6n8bA"
}

```
