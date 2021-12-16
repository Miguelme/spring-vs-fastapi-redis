# Redis Spring/Fast API

## _Redis for Spring / Fast API Comparator_


This repo aims to deploy two services with the `docker-compose.yaml`

- Spring: Port `8001`
- Fast API: Port `8000`

which will only connect to a Redis Cluster (also deployed on the `docker-compose.yaml`)

## Spring

We would like to use Spring for comparing to FastAPI based on the Glovo Stack Tool as well as being one "faster" option according to WebFrameworks Benchmark. 

_Note: It's worth highlighting we developed two different controller for Spring, one using Redis Template and another one using Spring Data Redis but the first one it's much faster and the instructions will focus on that one_

### Key Value Generator

- GET `/template/generate/{amountOfValues}`

### Get All Keys

- GET `/template/keys`

### Key Value Batch Query

- POST `/template/` 
    - Body: `List<String>`

### Key Value Single Query

- GET `/template/{key}`

## FastAPI

We would like to use FastAPI for comparing to Spring based on it being one "faster" option according to WebFrameworks Benchmark for Python

### Key Value Generator

- GET `/generate/{amountOfValues}`

### Get All Keys

- GET `/template/keys`

### Key Value Batch Query

- POST `/keys/` 
    - Body: `List<String>`
    - 
### Key Value Single Query

- GET `/keys/{key}`

## Instructions
0. Run `./mvnw clean package` in the Spring folder
1. Run `docker-compose up --build`
2. Generate Values for the instance (wether it's Spring or FastAPI) according to the instructions from above (we reccomend to test with 20000 instances)
3. Get All keys following the instructions from above and take a subset  of them to query them usuing the Key Value Batch Query Described Above
4. Do the load test to the Key Value Batch Query with the responses from the previous step and the tool of your choice. (If you want you can also load test the generation for assesing the writing time)

## Extra

You can use locust after you install it following the locustfile for both spring and fastapi as follows:

`locust -f <locust_file>.py --headless -u 200 -r 10 --run-time 5m`

where you can use for `<locust_file>` one of the following:

- Spring: `locustfile_spring.py`
- FastAPI: `locustfile_fastapi.py`