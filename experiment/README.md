# Experiments, Sample Code, and Other Misc

This directory contains source code of experiments with the
library, sample code, and other misc items, as follows.

## Experiments with the Ziggurat Library and Java 17

The code in the directory [timing17](timing17) can be used to reproduce the experiments of the following paper, which among other things explores the relevance of the library for Java 17+:

> Vincent A. Cicirello. 2024. [Fast Gaussian Distributed Pseudorandom Number Generation in Java via the Ziggurat Algorithm](https://reports.cicirello.org/24/009/). arXiv:[2405.19493](https://arxiv.org/abs/2405.19493), May 2024. doi:[10.48550/arXiv.2405.19493](https://doi.org/10.48550/arXiv.2405.19493). [[PDF]](https://reports.cicirello.org/24/009/ALG-24-009.pdf)

## Which Gaussian Algorithm Does Java Use

The code in the directory [properties](properties) extracts a list of all of the 
random number generators for Java 17, and determines what implementation of 
`nextGaussian` each uses. Classes that inherit the default in Java 17 use a modified 
ziggurat, which is faster than the original ziggurat algorithm. The `Random` class, 
and the classes that extend it override the default with the slow polar method. Those 
are cases where our implementation of the original ziggurat algorithm is still relevant 
for Java 17+ applications.