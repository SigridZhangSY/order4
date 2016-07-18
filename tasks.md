# Tasks

/products

- post()

  1. return 201

     - return 201 when create product — 5 7
     - return uri when create product — 10
     - save and find product in product repository (ProductRepository.createProduct) — 15
     - return 201 when create product with specified parameter — 5

  2. return 400

     - return 400 when name, description or price is null — 5

     ​

- get()

  1. return 200

     - return 200 when get products — 5

     - get products in product repository (ProductRepository.getAllProducts) — 10

     - return detail when get products — 7

       ​

/products/{productId}

- get()

  1. return 200
     - return 200 when get product — 5
     - find product in product repository (ProductRepository.findById) — 10
     - return detail when get product — 7
  2. return 404
     - return 404 when no product exists — 7

  ​

/users

- post()

  1. return 201

     - return 201 when create user — 5
     - return uri when create user — 10
     - save and find user in user repository (UserRepository.createUser) — 15
     - return 201 when create user with specified parameter — 5

  2. return 400

     - find user by name in user repository (UserRepository.findUserByName) — 10

     - return 400 when user exists — 5

     - return 400 when name is null — 5

       ​

/users/{id}/orders

- post()

  1. return 201

     - return 201 when create order — 5
     - return uri when create order — 15
     - get product price in  repository(UserRepository.getProductPrice) — 10
     - save and find order in user repository (UserRepository.createOrder) — 20
     - return 201 when create oder with specified parameter — 7

  2. return 400

     - find user by id in user repository — 5
     - return 400 when create order with no exist user — 10
     - return 400 when name, address, phone, order_items, product_id or quantity is null — 5
     - return 400 when product_id is not found — 10

     ​

- get()

  1. return 200

     - return 200 when get orders — 5
     - get orders in user repository in order repository(UserRepository.getOrders) — 10
     - return detail when get orders — 10

  2. return 400 when get order with no exist user — 5

     ​

/users/{id}/orders/{orderId}

- get()

  1. return 200

     - return 200 when get order — 5
     - find order by order_id in user repository (UserRepository.findOrderById) — 10
     - return detail when get order — 10

  2. return 404 when no order exists — 5

     ​

/users/{id}/orders/{orderId}/payments

- post()

  1. return 201

     - return 201 when create payment — 5
     - return uri when create payment — 10
     - save payment in payment repository (UserRepository.creatPayment) — 15
     - return 201 when create payment with specified parameter — 7

  2. return 400

     - find whether payment exist in payment repository (UserRepository.findPaymentById)  —10
     - return 400 when payment exists — 5
     - return 400 when pay_type or amount is null — 5

     ​

- get()

  1. return 200
     - return 200 when get payment — 5
     - return details when get payment — 10
  2. return 404 when no payment exists — 5