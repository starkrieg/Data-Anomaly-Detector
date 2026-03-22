## Chart structure

Simple Helm Chart structure for deploying the Data Anomaly Detector on a Kubernetes (K8S) cluster.

Current files only include simple setup of the app with health probe.

Before pushing new changes, execute the following commands:

1. `helm lint .` - this is meant as simple syntax test for the chart, and can be enough to prevent simple mistakes.
2. `helm-docs` - this will update the README for the chart, which saves up a lot of time and keeps the documentation standardized.

### Modules version

| Module | Version | Info |
|---|---|---|
| go | go1.26.1 | | Go language (https://go.dev/) |
| helm | v4.1.3 | | Helm Charts (https://helm.sh/)
| helm-docs | v1.14.2 (latest on 22/March/2026) | "A tool for automatically generating markdown documentation for helm charts" https://github.com/norwoodj/helm-docs |