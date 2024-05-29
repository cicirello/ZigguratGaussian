# Which Gaussian Algorithm Does Java Use

The code in this directory extracts a list of all of the random number generators
for Java 17, and determines what implementation of `nextGaussian` each uses. Classes
that inherit the default in Java 17 use a modified ziggurat, which is faster than
the original ziggurat algorithm. The `Random` class, and the classes that extend it
override the default with the slow polar method. Those are cases where our implementation
of the original ziggurat algorithm is still relevant for Java 17+ applications.

## Building with Maven

To compile, execute the following within this directory:

```Shell
mvn clean package
```

## Running

To run, execute the following within this directory:

```Shell
java -cp target/what-gaussian-1.0.0.jar org.cicirello.experiments.whatgaussian.WhatGaussian
```

## Output

If you just want to inspect the output from my run, see the 
file [what-gaussian-implementation.txt](what-gaussian-implementation.txt).