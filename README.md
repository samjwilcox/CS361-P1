# Project 1: Deterministic Finite Automata

* Author: Tyler Fernandez, Sam Wilcox
* Class: CS361 Section #1
* Semester: Fall 2025

## Overview

This project implements a Java program that models a deterministic finite automata.

## Reflection

This project mostly went smoothly, the only issues being a minor bug in the addTransition 
method, as well as, obtaining a partner in the beginning. By obtaining a partner late, it
gave us less time to finish the project and communicate with each other. I think I have
a good understanding of DFAs after this project. Some techniques that I used to make
our code easy to debug and modify include using even spacing and comments.

If I could change something about my design process, I would give more time to identify
edge cases and testing. We weren't able to test our project until the last day because
the onyx commands weren't working, so we didn't know whether our project worked or not.
This made it hard to proceed when working on the project. If I could go back in time,
I would tell myself to start earlier on this project, as it would've allowed more time
for testing. 

## Compiling and Using

To compile this code, upload the files to onyx and run the following commands:
```
javac -cp .:/usr/share/java/junit.jar ./test/dfa/DFATest.java
```
Then to run the tests:
```
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/hamcrest.jar org.junit.runner.JUnitCore test.dfa.DFATest
```

## Sources used

Used class files.