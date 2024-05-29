# Ziggurat Gaussian

[<img alt="Ziggurat Gaussian - Fast Gaussian distributed pseudorandom number generation in Java via the Ziggurat algorithm" 
     src="https://raw.githubusercontent.com/cicirello/ZigguratGaussian/master/images/ziggurat-gaussian.png" width="640">](#ziggurat-gaussian)

Copyright (C) 2015, 2017-2024 [Vincent A. Cicirello](https://www.cicirello.org/).

| __Packages and Releases__ | [![Maven Central](https://img.shields.io/maven-central/v/org.cicirello/ziggurat.svg?label=Maven%20Central&logo=apachemaven)](https://central.sonatype.com/artifact/org.cicirello/ziggurat/) [![GitHub release (latest by date)](https://img.shields.io/github/v/release/cicirello/ZigguratGaussian?logo=GitHub)](https://github.com/cicirello/ZigguratGaussian/releases) |
| :--- | :--- |
| __Build Status__ | [![build](https://github.com/cicirello/ZigguratGaussian/workflows/build/badge.svg)](https://github.com/cicirello/ZigguratGaussian/actions/workflows/build.yml) [![CodeQL](https://github.com/cicirello/ZigguratGaussian/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/cicirello/ZigguratGaussian/actions/workflows/codeql-analysis.yml) |
| __JaCoCo Test Coverage__ | [![coverage](https://raw.githubusercontent.com/cicirello/ZigguratGaussian/badges/jacoco.svg)](https://github.com/cicirello/ZigguratGaussian/actions/workflows/build.yml) [![branches coverage](https://raw.githubusercontent.com/cicirello/ZigguratGaussian/badges/branches.svg)](https://github.com/cicirello/ZigguratGaussian/actions/workflows/build.yml)  |
| __Security__ | [![Snyk security score](https://snyk-widget.herokuapp.com/badge/mvn/org.cicirello/ziggurat/badge.svg)](https://snyk.io/vuln/maven%3Aorg.cicirello%3Aziggurat) [![Snyk Known Vulnerabilities](https://snyk.io/test/github/cicirello/ZigguratGaussian/badge.svg)](https://snyk.io/test/github/cicirello/ZigguratGaussian) |
| __DOI__ | [![DOI](https://zenodo.org/badge/201526811.svg)](https://zenodo.org/badge/latestdoi/201526811) |
| __Other Information__ | [![GitHub](https://img.shields.io/github/license/cicirello/ZigguratGaussian)](https://github.com/cicirello/ZigguratGaussian/blob/master/LICENSE) [![style](https://img.shields.io/badge/style-Google%20Java%20Style-informational)](https://google.github.io/styleguide/javaguide.html) |
| __Support__ | [![GitHub Sponsors](https://img.shields.io/badge/sponsor-30363D?logo=GitHub-Sponsors&logoColor=#EA4AAA)](https://github.com/sponsors/cicirello) [![Liberapay](https://img.shields.io/badge/Liberapay-F6C915?logo=liberapay&logoColor=black)](https://liberapay.com/cicirello) [![Ko-Fi](https://img.shields.io/badge/Ko--fi-F16061?logo=ko-fi&logoColor=white)](https://ko-fi.com/cicirello) |

## Fast Gaussian distributed pseudorandom number generation in Java via the Ziggurat algorithm

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

## When to Use This Library

The following table summarizes when this library will speed up Gaussian random
number generation relative to Java's builtin functionality:

| Java Version | Significantly Faster For  | 
| --- | --- |
| Java 11 &le; version &lt; Java 17 | `Random`, `SecureRandom`, `SplittableRandom`,`ThreadLocalRandom` |
| Java version &ge; Java 17 | `Random`, `SecureRandom`, `ThreadLocalRandom` |

## More Detailed Information Including Experiments

Some experiment source code and data can be found in the [experiment](experiment) directory.

The following paper discusses experiments using the library with Java 17. In
Java 17, several enhancements to random number generation were introduced, including
replacing the polar method with a modified ziggurat for many of the random number
generators. Some of the legacy generators still use the slow polar method, however. This
report explores how and where our ziggurat library is still relevant in Java 17.

> Vincent A. Cicirello. 2024. [Fast Gaussian Distributed Pseudorandom Number Generation in Java via the Ziggurat Algorithm](https://reports.cicirello.org/24/009/). Technical Report ALG-24-009, Cicirello.org, May 2024. [[PDF]](https://reports.cicirello.org/24/009/ALG-24-009.pdf)

You can find some experimental data comparing the performance of a sequential
genetic algorithm (GA) using this implementation of the Ziggurat method for
Gaussian mutation vs using the more common polar method, as well as experimental data
for the same comparison but with a PGA, in the following paper:

> V. A. Cicirello. [Impact of Random Number Generation on Parallel Genetic Algorithms](https://www.cicirello.org/publications/cicirello2018flairs.html). *Proceedings of the Thirty-First International Florida Artificial Intelligence Research Society Conference*, pages 2-7. AAAI Press, May 2018. [[PDF]](https://www.cicirello.org/publications/cicirello-flairs2018.pdf).

See the following articles for detailed description of the Ziggurat algorithm itself, as well as
additional experimental data:
* G. Marsaglia and W. W. Tsang. [The ziggurat method for generating random variables](http://www.jstatsoft.org/v05/i08/).  *Journal of Statistical Software*. 5(1):1–7, 2000. 
* P. H. W. Leong, G. Zhang, D. Lee, W. Luk, and J. Villasenor. [A Comment on the Implementation of the Ziggurat Method](https://www.jstatsoft.org/article/view/v012i07). *Journal of Statistical Software*. 12(7):1–4, 2005. 
* J. Voss. [The Ziggurat Method for Generating Gaussian Random Numbers](http://www.seehuhn.de/pages/ziggurat). GSL: GNU Scientific Library. 2005. 

## See [&rho;&mu;](https://github.com/cicirello/rho-mu) for Expanded Functionality

The entirety of this library has been absorbed 
by [&rho;&mu;, a Java library of Randomization enHancements and Other Math Utilities](https://github.com/cicirello/rho-mu), which
includes additional enhanced random number generation, among other related functionality. However, [&rho;&mu;](https://rho-mu.cicirello.org/)
requires Java 17+, whereas this Ziggurat implementation supports Java 11+.

## Versioning Scheme

The library uses [Semantic Versioning](https://semver.org/) with 
version numbers of the form: MAJOR.MINOR.PATCH, where differences 
in MAJOR correspond to incompatible API changes, differences in MINOR 
correspond to introduction of backwards compatible new functionality, 
and PATCH corresponds to backwards compatible bug fixes.

## Java 11+

The jars of the library, distributed via Maven Central, GitHub Packages, 
and GitHub Releases, are built with OpenJDK 17 but for a target of Java 11.   

## Importing from Package Repositories

Prebuilt artifacts are regularly published to Maven Central and GitHub Packages. In 
most cases, you'll want to use Maven Central. Releases are published to GitHub 
Packages mainly as a fall-back in the unlikely scenario that Maven Central is unavailable.

### Importing from Maven Central

Add this to the dependencies section of your pom.xml, replacing 
the version number with the version that you want to use.

```XML
<dependency>
  <groupId>org.cicirello</groupId>
  <artifactId>ziggurat</artifactId>
  <version>1.0.5</version>
</dependency>
```

### Importing from GitHub Packages

If you'd prefer to import from GitHub Packages, rather than Maven Central, 
then: (1) add the dependency as indicated in previous section above, and (2) add 
the following to the repositories section of your pom.xml:

```XML
<repository>
  <id>github</id>
  <name>GitHub cicirello Apache Maven Packages</name>
  <url>https://maven.pkg.github.com/cicirello/ZigguratGaussian</url>
</repository>
```

Note that GitHub Packages requires authenticating to GitHub.

### Downloading Jar Files

If you don't use a dependency manager that supports importing from Maven Central,
or if you simply prefer to download manually, prebuilt jars are also attached to 
each [GitHub Release](https://github.com/cicirello/ZigguratGaussian/releases).

## Build with Maven

If you want to build from the source, then execute `mvn package` at the root
of the repository. The library should build with Java 11+.

To include generation of a code coverage report during the build,
execute `mvn package -Pcoverage` at the root of the repository to 
enable a Maven profile that executes JaCoCo during the test phase.

To run all static analysis tools (i.e., SpotBugs, Find Security Bugs,
refactor-first), execute `mvn package -Panalysis` to enable a Maven 
profile that executes the various static analysis tools that we are 
using. The SpotBugs html report will be found in the `target` directory, 
or you can use the SpotBugs GUI with: `mvn spotbugs:gui -Panalysis`. The 
refactor-first report will be found in the `target/site` directory.

To run all of the above: `mvn package -P "analysis,coverage"`.

## License

The example programs in this repository are licensed under 
the [GNU General Public License 3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
