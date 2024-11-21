# Bottle machine front end

## Prerequisites

Java 21

## How to start

Run with Intellij
![img.png](img.png)

## Task

> #### RS-001
> **As** a drinks' manufacturer,  
> **I'd like** the system to accept empty bottles  
> **So that** we can reacquire used bottles for recycling

> #### RS-002
> **As** a drinks' manufacturer,  
> **I'd like** the system to accept empty cans,  
> **So that** we can reacquire used cans for recycling.

> #### RS-003
> **As** a customer,  
> **I'd like** the system to give me a voucher as a reward for turning in bottles,  
> **So that** I can buy stuff

> #### RS-004
> **As** a drinks' manufacturer,  
> **I'd like** a backend system to log whenever a bottle/can is turned in or a voucher is printed,  
> **So that** we can report the efficiency of each recycling station.

#### Specifications and Clarifications

* *Cans* are valued at 2 NOK each.
* *Bottles* are valued at 3 NOK each.
* Inputting a bottle/can should be represented through a UI element, e.g. a button or a console prompt.
* For the purposes of this application, it is enough to present the printed voucher on screen next to the bottle inputs.
* You can assume that all containers have a valid sticker on it for valid identification.
* The hardware running on the recycling machine is able to process a can at a rate of 0,5 per second and for the plastic bottles it's 1 per second.

## Assumptions
* RS-004, 'log' is assumed to mean store an entry in a db and provide the total result as an output when a voucher is requested