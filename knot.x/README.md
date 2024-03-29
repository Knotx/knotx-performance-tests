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

The page is quite small, it contains only one dynamic Knot.x snippet that requires 1 data source integration.

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario1/simple-1snippet-1service.html
 - Mountains service - http://TARGET_MOCKS_DOMAIN:3080/scenario1/seven-mountains.json

#### One snippet and five services

The page is still small, it contains only one dynamic Knot.x snippet but it requires 5 different data source integration.

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario2/simple-1snippet-5services.html
 - Mountains service - http://TARGET_MOCKS_DOMAIN:3080/scenario2/mountains.json
 - Continents service - http://TARGET_MOCKS_DOMAIN:3080/scenario2/continents.json
 - Countries service - http://TARGET_MOCKS_DOMAIN:3080/scenario2/countries.json
 - Names service - http://TARGET_MOCKS_DOMAIN:3080/scenario2/names.json
 - Dates service - http://TARGET_MOCKS_DOMAIN:3080/scenario2/dates.json
 

#### Five snippets one service each

The page is variation of a previous one. It is still small, it contains five dynamic Knot.x snippets and each of those snippets uses separate data source integration.

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario3/simple-5snippets.html
 - Mountains service - http://TARGET_MOCKS_DOMAIN:3080/scenario3/mountains.json
 - Continents service - http://TARGET_MOCKS_DOMAIN:3080/scenario3/continents.json
 - Countries service - http://TARGET_MOCKS_DOMAIN:3080/scenario3/countries.json
 - Names service - http://TARGET_MOCKS_DOMAIN:3080/scenario3/names.json
 - Dates service - http://TARGET_MOCKS_DOMAIN:3080/scenario3/dates.json
 
#### Heavy template with one snippet one services     

The page contains big blocks of content and is heavy. There is no much work for integration here (only one snippet with single data soruce), however the challenge here is to process (split and then assemble) a big chunk of html code.

 - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario4/simple-big-data.html
 - First service - http://TARGET_MOCKS_DOMAIN:3080/scenario4/first.json 
 
#### Heavy template with 100 snippets and one heavy service     
 
 The page contains 100 snippets, each of them uses the same service. However, the service response is heavy. The challenge here is to process (split and then assemble) a big chunk of html code with multiple snippets and additionaly pass the payload of service response (heavy json) to templating engine Knot.
 
  - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario5/100-small-snippets-1-service-wtih-big-json.html
  - Peoples service - http://TARGET_MOCKS_DOMAIN:3080/scenario5/people-50.json 
  
#### Heavy template with one big snippet and one heavy service
 
 This test is variation of previous one. All 100 snippets were merged into one big snippet. The service response is heavy. The challenge here is to process (split and then assemble) a big chunk of html code with one heavy snippet and additionaly pass the payload of service response (heavy json) to templating engine Knot. 
 
   - Template from http://TARGET_MOCKS_DOMAIN:4503/scenario6/1-big-snippet-1-service-wtih-big-json.html
   - Peoples service - http://TARGET_MOCKS_DOMAIN:3080/scenario6/people-50.json 