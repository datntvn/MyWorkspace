# # DatNT notes about javascript / ES6

This repository store my notes on javascript

JAVA script promise:

Thuật ngữ

**callback hell**
    - Khi mà ta cần gọi asynchronous đến hàng loạt API/url
      mà mỗi một lời gọi API, khi có response (dù là success hay failure) thì nó sẽ kích hoạt 1 callback function
    - trong n async request API , sẽ có cái response sớm, có cái response trễ, khi đó ta không lường được trật tự của việc kích hoạt của các callback-function
      - Khi đó, ta rơi vào tình huống gọi là callback hell
