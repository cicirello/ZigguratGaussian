# Ziggurat Gaussian

## Java implementation of the Ziggurat algorithm for generating Gaussian distributed random numbers

Copyright (C) 2015, 2017-2020 Vincent A. Cicirello.

https://www.cicirello.org/

| __Packages and Releases__ |  |
| :--- | :--- |
| __Source and Build Info__ | [![build](https://github.com/cicirello/ZigguratGaussian/workflows/build/badge.svg)](https://github.com/cicirello/ZigguratGaussian/actions?query=workflow%3Abuild) [![GitHub](https://img.shields.io/github/license/cicirello/ZigguratGaussian)](https://github.com/cicirello/ZigguratGaussian/blob/master/LICENSE) |

## Overview

This repository contains a Java implementation of the Ziggurat 
algorithm for generating Gaussian distributed pseudorandom numbers.
The Ziggurat algorithm is significantly faster than the more commonly encountered
Polar method, and has some other desirable statistical properties.
The ZigguratGaussian class is a Java port of the GNU Scientific 
Library's C implementation (Voss, 2005) of the Ziggurat method.
In porting to Java, we have made several optimizations, the details of
which can be found in the source code comments, which highlights any
differences between this Java implementation and the 
C implementation on which it is based.

This Java implementation originated as part of an effort to speed
up the runtime of a parallel genetic algorithm (PGA).  The PGA in
question evolved its control parameters (i.e., crossover and mutation rates,
etc) using Gaussian mutation.  The only Gaussian implementation within the
Java API is the polar method (nextGaussian method of the Random and
ThreadLocalRandom classes, however the polar method is quite slow
relative to other newer available alternatives, such as the Ziggurat method.

You can find some experimental data comparing the performance of a sequential
genetic algorithm (GA) using this implementation of the Ziggurat method for
Gaussian mutation vs using the more common polar method, as well as experimental data
for the same comparison but with a PGA, in the following paper:

> V. A. Cicirello. [Impact of Random Number Generation on Parallel Genetic Algorithms](https://www.cicirello.org/publications/cicirello2018flairs.html). *Proceedings of the Thirty-First International Florida Artificial Intelligence Research Society Conference*, pages 2-7. AAAI Press, May 2018.  

See the following articles for detailed description of the Ziggurat algorithm itself, as well as
additional experimental data:
* G. Marsaglia and W. W. Tsang. [The ziggurat method for generating random variables](http://www.jstatsoft.org/v05/i08/).  *Journal of Statistical Software*. 5(1):1–7, 2000. 
* P. H. W. Leong, G. Zhang, D. Lee, W. Luk, and J. Villasenor. [A Comment on the Implementation of the Ziggurat Method](https://www.jstatsoft.org/article/view/v012i07). *Journal of Statistical Software*. 12(7):1–4, 2005. 
* J. Voss. [The Ziggurat Method for Generating Gaussian Random Numbers](http://www.seehuhn.de/pages/ziggurat). GSL: GNU Scientific Library. 2005. 

## Build with Maven

If you want to build from the source, then execute `mvn package` at the root
of the repository.

## License

The example programs in this repository are licensed under 
the [GNU General Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
