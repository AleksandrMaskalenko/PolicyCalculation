README for the assessment project. If you look at this file in Gitlab/Eclipse/IDEA, the Markdown formatting will be applied.

## Introduction ##

This page describes the test task for Proof IT assessment project. 

## Task description ##

Insurance company wants to start issuing private property policies to their customers.
System analysts found out that there will be a policy which will have objects (e.g. a flat) and that objects will
have sub-objects (e.g. eletronic devices such as TV).
One policy can contain multiple objects. One object can contain multiple sub-objects.
In this iteration, customer needs a functionality that calculates premium (a price that will be paid by each
client that will buy this insurance) for the policy.

Premium calculation formula:

PREMIUM = PREMIUM_FIRE + PREMIUM_WATER

* PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE
SUM_INSURED_FIRE - total sum insured of all policy's sub-objects with type "Fire"
COEFFICIENT_FIRE - by default 0.013 but if SUM_INSURED_FIRE is over 100 then 0.023

* PREMIUM_WATER = SUM_INSURED_WATER * COEFFICIENT_WATER
SUM_INSURED_WATER - total sum insured of all policy's sub-objects with type "Water"
COEFFICIENT_WATER - by default 0.1 but if SUM_INSURED_WATER equal or greater than
10 then 0.05

## Libraries used ##

* Git
* Java 8
* Maven
* jUnit 5


## Installation: ##
```
git clone git@github.com:AleksandrMaskalenko/PolicyCalculation.git
```
or just download .zip file and unpack

## Build project: ##
```
mvn clean package
```

