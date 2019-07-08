# Artillery for performance testing
This project uses [artillery](https://github.com/artilleryio/artillery) and a couple of it's plugins for performance testing.

Install the required node modules with
```
sudo npm install --unsafe-perm
```

To gather metrics, make sure you have running [monitoring stack](https://github.com/Knotx/knotx-performance-tests/tree/master/tooling#stack).

Run tests with
```
artillery run <path-to-scenario> -o results/test.json
```

Create a test report from the results .json with
```
artillery report results/test.json -o reports/report.html
```