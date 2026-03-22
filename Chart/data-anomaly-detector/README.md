# data-anomaly-detector

![Version: 1.0.0](https://img.shields.io/badge/Version-1.0.0-informational?style=flat-square) ![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square) ![AppVersion: 1.0.0](https://img.shields.io/badge/AppVersion-1.0.0-informational?style=flat-square)

Data Anomaly Detector

## Maintainers

| Name | Email | Url |
| ---- | ------ | --- |
| starkrieg |  |  |

## Source Code

* <https://github.com/starkrieg/Data-Anomaly-Detector>

## Values

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| anomaly_threshold | int | `3` |  |
| dataset_size | int | `50` |  |
| image | string | `"data-anomaly-detector:latest"` |  |
| inbound_queue | string | `"inbound"` |  |
| rabbitmq.host | string | `nil` |  |
| rabbitmq.pass | string | `"guest"` |  |
| rabbitmq.port | int | `5672` |  |
| rabbitmq.user | string | `"guest"` |  |
| resources.limits.memory | string | `"2048Mi"` |  |
| resources.requests.cpu | string | `"500m"` |  |
| resources.requests.memory | string | `"1024Mi"` |  |

