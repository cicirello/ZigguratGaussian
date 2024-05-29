# Experiments with the Ziggurat Library and Java 17

The code in this directory can be used to reproduce the experiments of the following paper:

> Vincent A. Cicirello. 2024. [Fast Gaussian Distributed Pseudorandom Number Generation in Java via the Ziggurat Algorithm](https://reports.cicirello.org/24/009/). Technical Report ALG-24-009, Cicirello.org, May 2024. [[PDF]](https://reports.cicirello.org/24/009/ALG-24-009.pdf)

## Building with Maven

To compile the experiments, execute the following within this directory:

```Shell
mvn clean package
```

## Running

The above builds a `jar-with-dependencies` that is executable, and which will be found within the target directory, which is created if it doesn't already exist. You can then run it with the following:

```Shell
java -jar target/ziggurat-timing-1.0.0-jar-with-dependencies.jar
```

## Data

If you just want to inspect the results from my run, you can find the data in [results17.txt](results17.txt).