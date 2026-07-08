/*
 * JUnit test cases for ZigguratGaussian.
 *
 * Copyright 2019-2026 Vincent A. Cicirello, <https://www.cicirello.org/>.
 *
 * This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.	See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.cicirello.math.rand;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/** Validation code needed by the JUnit test cases. */
public class Validator {

  // Change to true to see extra statistical output not otherwise used
  // by automated tests (e.g., to see the specific chi square statistic value).
  // Extra output sent to standard out.
  private static final boolean VERBOSE_OUTPUT = false;

  public static void validateThreadLocal(GaussianGenerator r) {
    // Since we cannot set the seed for the random number generator
    // in this case (ThreadLocalRandom does not allow setting seeds),
    // we do not do any goodness of fit testing here.  Without the ability
    // to set a seed, the chi square test statistic would be different
    // each test run, and tests at the 95% level could fail on average 1
    // out of every 20 runs and still be statistically valid.

    // Also note that ThreadLocalRandom implements the same pseudorandom
    // number generator algorithm as SplittableRandom, without the split
    // functionality.  And our implementation of ZigguratGaussian.nextGaussian()
    // delegates computation to ZigguratGaussian.nextGaussian(Random) by passing
    // ThreadLocalRandom.current() as the param since ThreadLocalRandom extends
    // Random.  So if the other test cases pass the goodness of fit tests, we
    // should be fine here as well.

    // We simply test instead that ZigguratGaussian.nextGaussian()
    // gives both negative and positive values over a large number of trials.
    boolean positive = false;
    boolean negative = false;
    for (int i = 0; i < 1000; i++) {
      double x = r.nextGaussian();
      if (x < 0) negative = true;
      else if (x > 0) positive = true;
      if (positive && negative) break;
    }
    assertTrue(positive && negative);
  }

  @FunctionalInterface
  public static interface GaussianGenerator {
    public double nextGaussian();
  }

  public static int[] generateBuckets(GaussianGenerator r, double sigma, int samplesPerBucket) {
    int[] buckets = new int[20];
    final int N = buckets.length * samplesPerBucket;
    for (int i = 0; i < N; i++) {
      int j = whichBucket(r.nextGaussian(), sigma);
      buckets[j]++;
    }
    return buckets;
  }

  public static void validate(int[] buckets, String msg, int samplesPerBucket) {
    double chi = chiSquare(buckets, samplesPerBucket);
    if (VERBOSE_OUTPUT) {
      System.out.printf(msg + ", chi=%5.4f\n", chi);
    }
    // 19 degrees of freedom, 95% percentage point of chi square distribution: 30.144
    assertTrue(chi <= 30.144);
  }

  public static double chiSquare(int[] buckets, int samplesPerBucket) {
    int x = 0;
    for (int e : buckets) {
      x = x + e * e;
    }
    return 1.0 * x / samplesPerBucket - buckets.length * samplesPerBucket;
  }

  public static int whichBucket(double x) {
    return whichBucket(x, 1.0);
  }

  public static int whichBucket(double x, double sigma) {
    final double[] upperBoundaries = {
      -1.644853627,
      -1.281551566,
      -1.036433389,
      -0.841621234,
      -0.67448975,
      -0.524400513,
      -0.385320466,
      -0.253347103,
      -0.125661347,
      0,
      0.125661347,
      0.253347103,
      0.385320466,
      0.524400513,
      0.67448975,
      0.841621234,
      1.036433389,
      1.281551566,
      1.644853627
    };
    if (sigma != 1.0) {
      for (int i = 0; i < upperBoundaries.length; i++) {
        upperBoundaries[i] = upperBoundaries[i] * sigma;
      }
    }
    for (int i = 0; i < upperBoundaries.length; i++) {
      if (x <= upperBoundaries[i]) return i;
    }
    return upperBoundaries.length;
  }
}
