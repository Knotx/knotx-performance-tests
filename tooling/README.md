# Knot.x Monitoring toolset

> Work in progress...

## Stack
Monitoring stack runs:
- [InfluxDB](https://www.influxdata.com/products/influxdb-overview/)
- [Telegraf](https://www.influxdata.com/time-series-platform/telegraf/) with [StatsD](https://github.com/statsd/statsd)
- [Grafana](https://grafana.com/)

### How to run
Make sure your Docker instance has [swarm created](https://docs.docker.com/engine/swarm/swarm-tutorial/create-swarm/).

Run
```
docker stack deploy -c monitoring.yml monitoring
```
from this directory.

### Grafana
Grafana is available at http://localhost:3000. Use `admin/admin` credentials when logging in for the 
first time.

#### Dashboards
This Grafana comes with 2 Knot.x Metrics dashboards embedded:
- Knot.x Healthcheck
- Knot.x Server