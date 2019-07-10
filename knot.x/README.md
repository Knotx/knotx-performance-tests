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