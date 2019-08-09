/*
 * JUnit test cases for ZigguratGaussian.
 *
 * Copyright 2019 Vincent A. Cicirello, <https://www.cicirello.org/>.
 *
 * The JUnit test cases for ZigguratGaussian is free software: you can 
 * redistribute it and/or modify it under the terms of the GNU 
 * General Public License as published by the Free Software 
 * Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 *
 * The JUnit test cases for ZigguratGaussian are distributed in the hope 
 * that it will be useful, but WITHOUT ANY WARRANTY; without even 
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.	See the GNU General Public License for more 
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ZigguratGaussianTests.java.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.cicirello.math.rand;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.Arrays;

/**
 * JUnit 4 test cases for the methods of the ZigguratGaussian class.
 */
public class ZigguratGaussianTests {
	
	// Test cases use chi square goodness of fit.  This constant 
	// can be used to adjust the number of samples used for this test.
	private static final int EXPECTED_SAMPLES_PER_BUCKET = 20;
	
	// Change to true to see extra statistical output not otherwise used
	// by automated tests (e.g., to see the specific chi square statistic value,
	// with 95% confidence intervals).  Extra output sent to standard out.
	private static final boolean VERBOSE_OUTPUT = false;
	
	@Test
	public void testRandom1() {
		Random r = new Random(42);
		
		int[] buckets = new int[4];
		final int N = buckets.length * EXPECTED_SAMPLES_PER_BUCKET;
		double[] chiVals = new double[40];
		for (int k = 0; k < chiVals.length; k++) {
			buckets = new int[4];
			for (int i = 0; i < N; i++) {
				int j = whichPercentile(ZigguratGaussian.nextGaussian(r));
				buckets[j]++;
			}
			chiVals[k] = chiSquare(buckets);
		}
		double[] ci = confidenceInterval(chiVals, 0.95); 
		double chi = ci[2];
		assertTrue(chi <= 7.815); // 3 degrees of freedom, 95% percentage point of chi square distribution: 7.815
		if (VERBOSE_OUTPUT) {
			System.out.printf("Random, sigma=1, chi=%5.4f", chi);
			System.out.printf(" conf. int.=[%5.4f, %5.4f]\n", ci[0], ci[1]);
		}
	}
	
	@Test
	public void testRandom10() {
		Random r = new Random(42);
		
		int[] buckets = new int[4];
		final int N = buckets.length * EXPECTED_SAMPLES_PER_BUCKET;
		double[] chiVals = new double[40];
		for (int k = 0; k < chiVals.length; k++) {
			buckets = new int[4];
			for (int i = 0; i < N; i++) {
				int j = whichPercentile(ZigguratGaussian.nextGaussian(10, r), 10);
				buckets[j]++;
			}
			chiVals[k] = chiSquare(buckets);
		}
		double[] ci = confidenceInterval(chiVals, 0.95); 
		double chi = ci[2];
		assertTrue(chi <= 7.815); // 3 degrees of freedom, 95% percentage point of chi square distribution: 7.815
		if (VERBOSE_OUTPUT) {
			System.out.printf("Random, sigma=10, chi=%5.4f", chi); 
			System.out.printf(" conf. int.=[%5.4f, %5.4f]\n", ci[0], ci[1]);
		}
	}
	
	@Test
	public void testSplittableRandom1() {
		SplittableRandom r = new SplittableRandom(42);
		
		int[] buckets = new int[4];
		final int N = buckets.length * EXPECTED_SAMPLES_PER_BUCKET;
		double[] chiVals = new double[40];
		for (int k = 0; k < chiVals.length; k++) {
			buckets = new int[4];
			for (int i = 0; i < N; i++) {
				int j = whichPercentile(ZigguratGaussian.nextGaussian(r));
				buckets[j]++;
			}
			chiVals[k] = chiSquare(buckets);
		}
		double[] ci = confidenceInterval(chiVals, 0.95); 
		double chi = ci[2];
		assertTrue(chi <= 7.815); // 3 degrees of freedom, 95% percentage point of chi square distribution: 7.815
		if (VERBOSE_OUTPUT) {
			System.out.printf("SplittableRandom, sigma=1, chi=%5.4f", chi); 
			System.out.printf(" conf. int.=[%5.4f, %5.4f]\n", ci[0], ci[1]);
		}
	}
	
	@Test
	public void testSplittableRandom10() {
		SplittableRandom r = new SplittableRandom(42);
		
		int[] buckets = new int[4];
		final int N = buckets.length * EXPECTED_SAMPLES_PER_BUCKET;
		double[] chiVals = new double[40];
		for (int k = 0; k < chiVals.length; k++) {
			buckets = new int[4];
			for (int i = 0; i < N; i++) {
				int j = whichPercentile(ZigguratGaussian.nextGaussian(10, r), 10);
				buckets[j]++;
			}
			chiVals[k] = chiSquare(buckets);
		}
		double[] ci = confidenceInterval(chiVals, 0.95); 
		double chi = ci[2];
		assertTrue(chi <= 7.815); // 3 degrees of freedom, 95% percentage point of chi square distribution: 7.815
		if (VERBOSE_OUTPUT) {
			System.out.printf("SplittableRandom, sigma=10, chi=%5.4f", chi); 
			System.out.printf(" conf. int.=[%5.4f, %5.4f]\n", ci[0], ci[1]);
		}
	}
	
	
	private double chiSquare(int[] buckets) {
		int x = 0;
		for (int e : buckets) {
			x = x + e*e;
		}
		return 1.0 * x / EXPECTED_SAMPLES_PER_BUCKET - buckets.length * EXPECTED_SAMPLES_PER_BUCKET;
	}
	
	private int whichPercentile(double x) {
		final double[] upperBoundaries = { -0.67448975, 0, 0.67448975 };
		for (int i = 0; i < upperBoundaries.length; i++) {
			if (x <= upperBoundaries[i]) return i;
		}
		return upperBoundaries.length;
	}
	
	private int whichPercentile(double x, double sigma) {
		final double[] upperBoundaries = { -0.67448975, 0, 0.67448975 };
		for (int i = 0; i < upperBoundaries.length; i++) {
			upperBoundaries[i] = upperBoundaries[i] * sigma;
		}
		for (int i = 0; i < upperBoundaries.length; i++) {
			if (x <= upperBoundaries[i]) return i;
		}
		return upperBoundaries.length;
	}
	
	private double[] confidenceInterval(double[] chi, double p) {
		int keep = (int)Math.round(chi.length * p);
		int left = (chi.length - keep) / 2;
		int right = left + keep - 1;
		Arrays.sort(chi);
		double median = (chi.length & 1) == 0 ? 0.5*(chi[chi.length/2] + chi[chi.length/2-1]) : chi[chi.length/2]; 
		return new double[] {chi[left], chi[right], median};
	}
}