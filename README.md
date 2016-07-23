
# Demo of java - R integration using Rserve

## Installation

Required :

* R
* Rserve (can be installed via R as a package)
* Chrome or Firefox with correct webdrivers for the installed version (currently using Chrome 'marionette', waiting for FireFox47 driver to stabilize)

## General idea

The idea is to collect in a structured way "features" from a web page, and then, apply to them ML algorithms, possibly in R.

## What is working so far

* Analyse a web page, extracting features from the webelements
* Launch Rserve from java if not running, stop it from java, eval any R script from java.

