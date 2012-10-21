/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

package ultraextreme.view;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Highscore implements Comparable<Highscore> {

	public static final Highscore EMPTY_HIGHSCORE = new Highscore("Empty", 0);

	private final String name;
	private final int score;

	public Highscore(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Highscore another) {
		return another.getScore() - this.getScore();
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Highscore[name:" + name + ", score:" + score + "]";
	}
}
