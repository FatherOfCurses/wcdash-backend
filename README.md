# WCDashDatasource

<!-- shields -->

[![Build Status](https://shields.lmig.com/bamboo/tests/CICRPA/WCDASHDA.svg?style=flat-square)](https://forge.lmig.com/builds/browse/CICRPA-WCDASHDA)<!-- /shields -->

---

## Project Structure

**[/src/main/deployment/](./src/main/deployment/)** - contains Cloud Foundry manifest files for each of your deployment environments

## Project goal

To provide a web-based reporting solution for Blue Prism transaction data.

## Structure details

Blue Prism transaction data is currently available from vmpid-c4ca0005, which is a MS SQL server.

This data is accessed via a CloudForge-hosted Java application.

Endpoints are available at https://wcdashdatasource-development.us-east-1.np.paas.lmig.com/swagger-ui.html.

The frontend is built on [Angular5](https://angular.io/) and data visualization provided by [Chart.js](http://www.chartjs.org/).

Tremendous assistance provided by Sam Machlin, Brett Hall, Mithun Tonse, Dan Barnes, and many others.

## Angular details

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.7.4.