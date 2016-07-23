
# Demo of java - R integration using Rserve

## Installation

Required :

* R
* Rserve (can be installed via R as a package)
* Chrome or Firefox with correct drivers for the installed version (currently using Chrome, waiting for FireFox47 driver to stabilize)

## Configuration

Configuration is done in RserveManager, where you need to specify the Rserve binary and the R_HOME location (R Home Directory).

## Features implemented

* Analyse a web page, extracting features of the webelements
* Launch Rserve from java if not running
* Close Rserve from java (the server, not only the connection)

## Next steps

* Format web page features extracted for ML processing in R