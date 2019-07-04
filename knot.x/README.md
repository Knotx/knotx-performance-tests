# Knot.x Performance Target Instance
This project is forked from the [Knot.x Starter Kit](https://github.com/Knotx/knotx-starter-kit).

## How to build Docker image?
Simply run the command
```
./gradlew build
```

## How can I run my Docker image?
```
docker run -p8092:8092 --name knotx_test knotx/knotx-perfromance-target
```

### Simple endpoint:
- http://localhost:8092/api/v1/example