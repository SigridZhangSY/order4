# Tasks

/products

- post()

  1. return 201

     - return 201 when create product — 5 7
     - return uri when create product — 10 12
     - save and find product in product repository (ProductRepository.createProduct) — 15 40
     - return 201 when create product with specified parameter — 5 3

  2. return 400

     - return 400 when name, description or price is null — 5 4

     ​

- get()

  1. return 200

     - return 200 when get products — 5 2

     - get products in product repository (ProductRepository.getAllProducts) — 10 6

     - return detail when get products — 7 10

       ​

/products/{productId}

- get()

  1. return 200
     - return 200 when get product — 5 3
     - find product in product repository (ProductRepository.findById) — 10 4
     - return detail when get product — 7 6
  2. return 404
     - return 404 when no product exists — 7 3

  ​

/users

- post()
  1. return 201
     - return 201 when create user — 5 5
     - return uri when create user — 10 5
     - save and find user in user repository (UserRepository.createUser) — 15 16
     - return 201 when create user with specified parameter — 5 3
  2. return 400
     - find user by name in user repository (UserRepository.findUserByName) — 10 5
     - return 400 when user exists — 5 3
     - return 400 when name is null — 5 3

/users/{userId}

- get()
  1. return 200
     - return 201 when find user by id — 5 3
     - return details when find user by id — 7 20
  2. return 404
     - return 404 when user not exists — 5 3

- ​

  ​

/users/{id}/orders

- post()

  1. return 201

     - return 201 when create order — 5 3
     - return uri when create order — 15 27
     - save and find order in user — 20 30
     - return 201 when create oder with specified parameter — 7 10

  2. return 400

     - return 400 when name, address, phone, order_items, product_id or quantity is null — 5 7
     - return 400 when product_id is not found — 10 5

     ​

- get()

  1. return 200

     - return 200 when get orders — 5 7
     - get orders in user repository in user — 10 17
     - return detail when get orders — 10 15

     ​

/users/{id}/orders/{orderId}

- get()

  1. return 200

     - return 200 when get order — 5 3
     - find order by order_id in user (UserRepository.findOrderById) — 10 7
     - return detail when get order — 10 15

  2. return 404 when no order exists — 5 5

     ​

/users/{id}/orders/{orderId}/payments

- post()

  1. return 201

     - return 201 when create payment — 5 6
     - return uri when create payment — 10 8
     - save payment in order — 15 25
     - return 201 when create payment with specified parameter — 7 3

  2. return 400

     - find whether payment exist in order  —10 5
     - return 400 when payment exists — 5 3
     - return 400 when pay_type or amount is null — 5 4

     ​

- get()

  1. return 200
     - return 200 when get payment — 5 3
     - return details when get payment — 10  7
  2. return 404 when no payment exists — 5 3