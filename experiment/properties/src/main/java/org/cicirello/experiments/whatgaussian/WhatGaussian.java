/*
 * Check which RandomGenerators use default Gaussian algorithm.
 * Copyright (C) 2024 Vincent A. Cicirello
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

package org.cicirello.experiments.whatgaussian;

import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Determines which of Java's builtin pseudorandom number generators use the RandomGenerator
 * interface's default algorithm for nextGaussian, which override it.
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, <a
 *     href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
public final class WhatGaussian {

  /**
   * Entrypoint for program.
   *
   * @param args unused
   */
  public static void main(String[] args) {

    System.out.printf("%10s %21s %s\n", "Group", "RandomGenerator", "Where nextGaussian declared");
    RandomGeneratorFactory.all()
        .sorted((f1, f2) -> f1.group().compareTo(f2.group()))
        .forEach(
            generatorFactory -> {
              String name = generatorFactory.name();
              String group = generatorFactory.group();
              RandomGenerator r = generatorFactory.create();
              try {
                String classThatDeclaresNextGaussian =
                    r.getClass().getMethod("nextGaussian").getDeclaringClass().getName();
                System.out.printf("%10s %21s %s\n", group, name, classThatDeclaresNextGaussian);
              } catch (NoSuchMethodException exception) {
                System.out.println(
                    "Something highly unexpexpected occured. A method with a default not found.");
              }
            });
    try {
      String classThatDeclaresNextGaussian =
          ThreadLocalRandom.current()
              .getClass()
              .getMethod("nextGaussian")
              .getDeclaringClass()
              .getName();
      System.out.printf(
          "%10s %21s %s\n", "Legacy", "ThreadLocalRandom", classThatDeclaresNextGaussian);
    } catch (NoSuchMethodException exception) {
      System.out.println(
          "Something highly unexpexpected occured. A method with a default not found.");
    }
  }
}
