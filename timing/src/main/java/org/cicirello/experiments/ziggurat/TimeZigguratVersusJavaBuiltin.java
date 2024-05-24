/*
 * Experiment comparing org.cicirello.ziggurat versus Java builtin implementation.
 * Copyright (C) 2023-2024 Vincent A. Cicirello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.cicirello.experiments.ziggurat;

import java.io.IOException;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.cicirello.math.rand.ZigguratGaussian;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

/**
 * Compares Java's builtin Gaussian implementation with this Ziggurat impementation. Beginning with
 * Java 17, the Java API's default Gaussian implementation is based on the Ziggurat algorithm
 * (except for the Random class, which still uses the Polar Method). Thus, for Java 17+, it doesn't
 * seem like there should still be any benefit from my implementation of the Ziggurat algorithm.
 *
 * <p>The aim of this program is to empirically verify that Java's builtin Gaussian method is at
 * least as good as my implementation for Java 17+. If you run this on earlier versions of Java, the
 * Java builtin implementation should be slower. But on newer, it will probably be faster.
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, <a
 *     href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
public class TimeZigguratVersusJavaBuiltin {

  private static final Random random = new Random(42);
  private static final SplittableRandom splittable = new SplittableRandom(42);

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double builtinThreadLocalRandom() {
    return ThreadLocalRandom.current().nextGaussian();
  }

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double builtinSplittableRandom() {
    return splittable.nextGaussian();
  }

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double builtinRandom() {
    return random.nextGaussian();
  }

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double zigguratThreadLocalRandom() {
    return ZigguratGaussian.nextGaussian();
  }

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double zigguratSplittableRandom() {
    return ZigguratGaussian.nextGaussian(splittable);
  }

  @Benchmark
  @Fork(value = 1)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @BenchmarkMode(Mode.All)
  public double zigguratRandom() {
    return ZigguratGaussian.nextGaussian(random);
  }

  /**
   * Runs the experiments.
   *
   * @param args Ignored, no command line arguments.
   */
  public static void main(String[] args) throws IOException {
    printCopyrightAndLicense();
    org.openjdk.jmh.Main.main(args);
  }

  /** Prints copyright and license notices. */
  private static void printCopyrightAndLicense() {
    System.out.println();
    System.out.println(
        "Experiment comparing org.cicirello.ziggurat versus Java builtin implementation.");
    System.out.println("Copyright (C) 2023-2024 Vincent A. Cicirello");
    System.out.println("This program comes with ABSOLUTELY NO WARRANTY.  This is free");
    System.out.println("software, and you are welcome to redistribute it under certain");
    System.out.println("conditions.  See the GNU General Public License for more");
    System.out.println("details: https://www.gnu.org/licenses/gpl-3.0.html");
    System.out.println();
  }
}
