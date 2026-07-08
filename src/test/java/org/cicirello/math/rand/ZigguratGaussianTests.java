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

import java.util.Random;
import java.util.SplittableRandom;
import org.junit.jupiter.api.*;

/** JUnit test cases for the methods of the ZigguratGaussian class. */
public class ZigguratGaussianTests {

  // Test cases use chi square goodness of fit.  This constant
  // can be used to adjust the number of samples used for this test.
  private static final int EXPECTED_SAMPLES_PER_BUCKET = 60;

  @Test
  public void testRandom1() {
    Random r = new Random(42);
    int[] buckets =
        Validator.generateBuckets(
            () -> ZigguratGaussian.nextGaussian(r), 1, EXPECTED_SAMPLES_PER_BUCKET);
    Validator.validate(buckets, "Random, sigma=1", EXPECTED_SAMPLES_PER_BUCKET);
  }

  @Test
  public void testRandom10() {
    Random r = new Random(42);
    int[] buckets =
        Validator.generateBuckets(
            () -> ZigguratGaussian.nextGaussian(10, r), 10, EXPECTED_SAMPLES_PER_BUCKET);
    Validator.validate(buckets, "Random, sigma=10", EXPECTED_SAMPLES_PER_BUCKET);
  }

  @Test
  public void testSplittableRandom1() {
    SplittableRandom r = new SplittableRandom(42);
    int[] buckets =
        Validator.generateBuckets(
            () -> ZigguratGaussian.nextGaussian(r), 1, EXPECTED_SAMPLES_PER_BUCKET);
    Validator.validate(buckets, "SplittableRandom, sigma=1", EXPECTED_SAMPLES_PER_BUCKET);
  }

  @Test
  public void testSplittableRandom10() {
    SplittableRandom r = new SplittableRandom(42);
    int[] buckets =
        Validator.generateBuckets(
            () -> ZigguratGaussian.nextGaussian(10, r), 10, EXPECTED_SAMPLES_PER_BUCKET);
    Validator.validate(buckets, "SplittableRandom, sigma=10", EXPECTED_SAMPLES_PER_BUCKET);
  }

  @Test
  public void testNoParamNextGaussian1() {
    Validator.validateThreadLocal(() -> ZigguratGaussian.nextGaussian());
  }

  @Test
  public void testNoParamNextGaussian10() {
    Validator.validateThreadLocal(() -> ZigguratGaussian.nextGaussian(10));
  }
}
