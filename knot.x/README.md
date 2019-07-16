# Knot.x Performance Target Instance
This project is forked from the [Knot.x Starter Kit](https://github.com/Knotx/knotx-starter-kit).

## How to build Docker image
Simply run the command
```
./gradlew build
```

## How can I run my Docker image?
You may run single Knot.x container with
```
docker run --rm -p8092:8092 --name knotx_test knotx/knotx-performance-target
```

To run whole `target` stack with monitoring, please build the image first with `./gradlew clean build`
and then run from the `tooling` directory
```
docker stack deploy -c target.yml target
```

### Simple endpoint:
- http://localhost:8092/api/v1/example

### Scenarios

#### Template processing 

```
http://localhost:8092/scenario0/payment.html
```
 
Scenario with template processing. It calls 4 services. First gather information from `user` service and then in parallel from 3 payments services: `creditcard`, `paypal` and `payU`.

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario0/payment.html
 - User service - http://TARGET_MOCKS_DOMAIN:3080/scenario0/user
 - 3 services called in parallel:
    - credit card - http://TARGET_MOCKS_DOMAIN:3080/scenario0/creditcard/{user._id}/allowed
    - paypal - http://TARGET_MOCKS_DOMAIN:3080/scenario0/paypal/{user._id}/verify
    - payu - http://TARGET_MOCKS_DOMAIN:3080/scenario0/payu/{user._id}/active
    
#### One snippet and one service     

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario1/simple-1snippet-1service.html
 - User service - http://TARGET_MOCKS_DOMAIN:3080/scenario0/user
