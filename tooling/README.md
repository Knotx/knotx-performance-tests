# Knot.x Monitoring toolset

> Work in progress...

## Stack
Monitoring stack runs:
- [InfluxDB](https://www.influxdata.com/products/influxdb-overview/)
- [Telegraf](https://www.influxdata.com/time-series-platform/telegraf/) with [StatsD](https://github.com/statsd/statsd)
- [Grafana](https://grafana.com/)

Target stack runs:
- [Knot.x instance](https://github.com/Knotx/knotx-performance-tests/tree/master/knot.x)
- [cAdvisor](https://github.com/google/cadvisor)
- [jmxtrans](http://jmxtrans.org)

Mocks stack runs
- [httpd](https://hub.docker.com/_/httpd)

### How to run
Make sure your Docker instance has [swarm created](https://docs.docker.com/engine/swarm/swarm-tutorial/create-swarm/).

Run monitoring stack from this directory:
```
docker stack deploy -c monitoring.yml monitoring
```

Run mocks stack from this directory:
```
docker stack deploy -c mocks.yml mocks
```

Next, build the Knot.x image first with `./gradlew clean build` from `knot.x` directory
and then run from this directory:
```
docker stack deploy -c target.yml target
```

*NOTE*. In case your mocks are deployed on different machine you need set environment variable 
TARGET_MOCK_DOMAIN for host ip, for example:

```
export TARGET_MOCK_DOMAIN=192.168.1.1
```

and then run from this directory:
```
docker stack deploy -c target.yml target
```

### Grafana
Grafana is available at http://localhost:3000. Use `knotx/knotx` credentials when logging in for the 
first time.

#### Dashboards
This Grafana comes with 2 Knot.x Metrics dashboards embedded:
- Knot.x Healthcheck
- Knot.x Server

And two resource monitoring dashboards:
- Knot.x Resources
- Knot.x JMX monitoring

## Troubleshooting
Some of the connections use [`host.docker.internal`](https://docs.docker.com/docker-for-mac/networking/)
 as the reference to the host address. This currently works only for Docker for Mac/Windows.
To be able to run, uncomment `extra_hosts` sections in the `target.yml` and set your host ip there.