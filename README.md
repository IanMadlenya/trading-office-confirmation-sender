# Trading Office - Confirmation Sender
[![Build Status](https://travis-ci.org/spolnik/trading-office-confirmation-sender.svg?branch=master)](https://travis-ci.org/spolnik/trading-office-confirmation-sender) [![codecov.io](https://codecov.io/github/spolnik/trading-office-confirmation-sender/coverage.svg?branch=master)](https://codecov.io/github/spolnik/trading-office-confirmation-sender?branch=master) [![Sonar Coverage](https://img.shields.io/sonar/https/sonar-nprogramming.rhcloud.com/trading-office-confirmation-sender/coverage.svg)](https://sonar-nprogramming.rhcloud.com/dashboard/index/1) [![Sonar Tech Debt](https://img.shields.io/sonar/https/sonar-nprogramming.rhcloud.com/trading-office-confirmation-sender/tech_debt.svg)](https://sonar-nprogramming.rhcloud.com/dashboard/index/1) [![Coverity Scan Build Status](https://scan.coverity.com/projects/7604/badge.svg)](https://scan.coverity.com/projects/spolnik-trading-office-confirmation-sender)

Trading Office is reference implementation of microservices architecture, based on Spring Boot. It's modeling part of post trade processing, mainly focused on receiving Fixml message and preparing confirmation for it.

- [Trading Office](#trading-office)
- [Confirmation Sender](#confirmation-sender)
- [Notes](#notes)

## Trading Office

- [Trading Office](https://github.com/spolnik/trading-office)

## Confirmation Sender
- spring boot application
- subscribes to messaging queue looking for enriched allocation messages (json)
- after receiving message, it generates PDF confirmation using JasperReports template or SWIFT confirmation
- finally, it sends the Confirmation with attached PDF or SWIFT (as byte[]) to confirmation service

Heroku: http://confirmation-sender.herokuapp.com/health

![Component Diagram](https://raw.githubusercontent.com/spolnik/trading-office-confirmation-sender/master/design/confirmation_sender.png)

## Notes
- checking if [dependencies are up to date](https://www.versioneye.com/user/projects/56ad39427e03c7003ba41427)
- installing RabbitMQ locally (to run end to end test locally) - [instructions](https://www.rabbitmq.com/download.html)
- to run on Mac OS X - /usr/local/sbin/rabbitmq-server 
