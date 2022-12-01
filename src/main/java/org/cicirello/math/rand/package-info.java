/*
 * Ziggurat Gaussian and other randomization related algorithms.
 * Copyright 2015, 2017-2022 Vincent A. Cicirello, <https://www.cicirello.org/>.
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
 */

/**
 *
 *
 * <h2>org.cicirello.math.rand</h2>
 *
 * <p>Copyright &copy; 2015, 2017-2022 <a href="https://www.cicirello.org/" target=_top>Vincent A.
 * Cicirello</a>.
 *
 * <p><a href="https://search.maven.org/artifact/org.cicirello/ziggurat"><img
 * src="https://img.shields.io/maven-central/v/org.cicirello/ziggurat.svg?logo=apachemaven"
 * alt="Maven Central" height="20" width="151"></a> <a
 * href="https://github.com/cicirello/ZigguratGaussian/releases"><img
 * src="https://img.shields.io/github/v/release/cicirello/ZigguratGaussian?logo=GitHub" alt="GitHub
 * release (latest by date)" height="20" width="111"></a> <a
 * href="https://github.com/cicirello/ZigguratGaussian"><img
 * src="https://rho-mu.cicirello.org/images/GitHub.svg" alt="GitHub Repository" width="68"
 * height="20"></a> <a href="https://github.com/cicirello/ZigguratGaussian/blob/master/LICENSE"><img
 * src="https://img.shields.io/github/license/cicirello/ZigguratGaussian" alt="GNU General Public
 * License Version 3 (GPLv3)" height="20" width="102"></a>
 *
 * <h3>Support</h3>
 *
 * <p><a href="https://github.com/sponsors/cicirello"><img
 * src="https://rho-mu.cicirello.org/images/github-sponsors.svg" alt="GitHub Sponsors" width="107"
 * height="28"></a> <a href="https://liberapay.com/cicirello"><img
 * src="https://rho-mu.cicirello.org/images/Liberapay.svg" alt="Liberapay" width="119"
 * height="28"></a> <a href="https://ko-fi.com/cicirello"><img
 * src="https://rho-mu.cicirello.org/images/ko-fi.svg" alt="Ko-Fi" width="82" height="28"></a>
 *
 * <h3>See <a href="https://rho-mu.cicirello.org/">&rho;&mu;</a> for Expanded Functionality</h3>
 *
 * <p>If you find the functionality of this package useful, please see the <a
 * href="https://rho-mu.cicirello.org/">&rho;&mu; library</a>, which has <a
 * href="https://rho-mu.cicirello.org/api/">absorbed this entire package and has greatly expanded
 * upon it</a>.
 *
 * <h3>About</h3>
 *
 * <p>This package originated as part of an effort to speed up the runtime of a parallel genetic
 * algorithm (PGA). The PGA in question evolved its control parameters (i.e., crossover and mutation
 * rates, etc) using Gaussian mutation. The only Gaussian implementation within the Java API is the
 * polar method (nextGaussian method of the {@link java.util.Random Random} and {@link
 * java.util.concurrent.ThreadLocalRandom ThreadLocalRandom} classes, however the polar method is
 * quite slow relative to other newer available alternatives, such as the Ziggurat method.
 *
 * <p>You can find some experimental data comparing the performance of a sequential genetic
 * algorithm (GA) using the implementation of the Ziggurat method for Gaussian mutation vs using the
 * more common polar method, as well as experimental data for the same comparison but with a PGA, in
 * the following paper:
 *
 * <ul>
 *   <li>V. A. Cicirello. <a href=https://www.cicirello.org/publications/cicirello2018flairs.html
 *       target=_top>Impact of Random Number Generation on Parallel Genetic Algorithms</a>.
 *       Proceedings of the Thirty-First International Florida Artificial Intelligence Research
 *       Society Conference, pages 2-7. AAAI Press, May 2018.
 * </ul>
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, <a
 *     href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
package org.cicirello.math.rand;
