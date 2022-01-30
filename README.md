# Market Simulation
Create historical stock market simulation.

THIS PROJECT IS MADE FOR SCHOOL!

## Overview
Material designed for Java.
Tested with openjdk-17 and IntelliJ IDEA 2021.3.1

The program contains:
* Standard simulation
* Animated simulation
* Calculating with different holdings
* Demo live trading with a 100.000 USD portfolio


![Image StockSimulation](screenshot/stockSimulation.png "Stock Chart at the end of Simulation (with 1000 USD)")

## Requirements
This tools are required for the installation:
* Openjdk-17
* javafx-sdk-17.0.2 (for windows x64 already implemented) [Download Package](https://gluonhq.com/products/javafx/)

## Installation
1. Clone project
2. Install the requirements
3. Run Main.java

## Troubleshooting
If you have the following error:
```
Error: JavaFx runtime components are missing, and are required to run this application
```

In IntelliJ go to: Run >> Edit Configuration >> Modify Options >> Add VM Option
```
--module-path /path/to/JavaFX/lib --add-modules=javafx.controls
```

## Getting help
For general questions regarding this package, please hop over to Stack Overflow.
If you think there is an issue with this package, check if the issue is already listed (either open or closed), and file an issue if it's not.

## Contribute
For Contribution please use the Github functions:
* Sumbit issues to the [issue tracker](https://www.github.com/seaic/MarketSimulation/issues) on Github
* Fork the [source code](https://github.com/seaic/MarketSimulation.git) at Github
* Send a [pull request](https://www.github.com/seaic/MarketSimulation/pulls) with your changes

## Releases
Need a stable release? You can find them at [releases](https://github.com/seaic/MarketSimulation.git)

## Links
The tutorial is composed of the following tutorials:
* [JavaFX Line Chart Oracle](https://docs.oracle.com/javafx/2/charts/line-chart.htm)

## Licensing
This project is licensed under the MIT license.