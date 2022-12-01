# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased] - 2022-12-01

### Added

### Changed

### Deprecated

### Removed

### Fixed

### CI/CD

### Other


## [1.0.5] - 2022-12-01

### Changed
* Reformatted all sourcecode to [Google Java Style](https://google.github.io/styleguide/javaguide.html).

### CI/CD
* Configured [Spotify's fmt-maven-plugin](https://github.com/spotify/fmt-maven-plugin) to format to Google Java Style during builds.
* Configured the [refactor-first-maven-plugin](https://github.com/jimbethancourt/RefactorFirst) within a profile in the pom.xml.

### Other
* Adopted [Google Java Style](https://google.github.io/styleguide/javaguide.html).


## [1.0.4] - 2022-07-15

### Other
* First release available via JitPack. No actual changes to the library.


## [1.0.3] - 2021-09-10

### Other
* Edited Zenodo metadata. Only way to deploy changes to Zenodo is through a release. No actual changes to the library.


## [1.0.2] - 2020-10-19

### Other
* Fixed bug in Zenodo metadata. Only way to deploy changes to Zenodo is through a release. No actual changes to the library.


## [1.0.1] - 2020-10-19

### Other
* Fixed bug in Zenodo metadata. Only way to deploy changes to Zenodo is through a release. No actual changes to the library.


## [1.0.0] - 2020-10-19

### Initial Release

Java implementation of the Ziggurat algorithm for generating Gaussian distributed 
pseudorandom numbers. The Ziggurat algorithm is significantly faster than the more 
commonly encountered Polar method, and has some other desirable statistical 
properties. The ZigguratGaussian class is a Java port of the GNU Scientific Library's 
C implementation (Voss, 2005) of the Ziggurat method. In porting to Java, we have 
made several optimizations, the details of which can be found in the source code 
comments, which highlights any differences between this Java implementation and the 
C implementation on which it is based. This package also includes an implementation 
of the Polar Method, included to enable comparing speed advantage of the Ziggurat 
algorithm.