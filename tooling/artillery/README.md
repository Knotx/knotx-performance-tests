# Artillery for performance testing
This project uses [artillery](https://github.com/artilleryio/artillery) and a couple of it's plugins for performance testing.
The database and monitoring is provided by https://github.com/samuelebistoletti/docker-statsd-influxdb-grafana

Install the required node modules with
```
sudo npm install --unsafe-perm
```

Start the container with InfluxDB + Grafana
```
docker run --ulimit nofile=66000:66000 \
  -d \
  --name docker-statsd-influxdb-grafana \
  -p 3003:3003 \
  -p 3004:8888 \
  -p 8086:8086 \
  -p 8125:8125/udp \
  samuelebistoletti/docker-statsd-influxdb-grafana:latest
 ```

Run tests with
```
artillery run <path-to-scenario> -o results/test.json
```

Create a test report from the results .json with
```
artillery report results/test.json -o reports/report.html
```

Access Grafana via
```
localhost:3003
user: root
password :root
```