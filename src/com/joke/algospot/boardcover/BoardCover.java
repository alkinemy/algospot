package com.joke.algospot.boardcover;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/BOARDCOVER
 */
public class BoardCover {
	private char[][] board;
	private int height;
	private int width;

	public BoardCover(char[][] board, int height, int width) {
		this.board = board;
		this.height = height;
		this.width = width;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int caseCount = scanner.nextInt();
		int[] result = new int[caseCount];

		for(int i = 0; i < caseCount; i++) {
			int height = scanner.nextInt();
			int width = scanner.nextInt();
			char[][] board = new char[height][width];
			for(int h = 0; h < height; h++) {
				String line = scanner.next();
				for(int w = 0; w < width; w++) {
					board[h][w] = line.charAt(w);
				}
			}

			result[i] = new BoardCover(board, height, width).solve();
		}
		for(int r : result) {
			System.out.println(r);
		}
	}

	private int solve() {
		boolean available = false;
		for(int h = 0; h < height; h++) {
			for(int w = 0; w < width; w++) {
				if (board[h][w] == '.') {
					available = true;
					break;
				}
				if (available) {
					break;
				}
			}
		}

		if (!available) {
			return 0;
		}
		return solve2(0);
	}

	private int solve2(int count) {
		int[] position = findAvailable();
		int h = position[0];
		int w = position[1];
		if (h == -1 && w == -1) {
			return count + 1;
		}

		int c = count;
		boolean[] available = checkCover(h, w);
		if (!(available[0] | available[1] | available[2] | available[3])) {
			return count;
		}

		if (available[0]) {
			board[h][w] = '#';
			board[h + 1][w] = '#';
			board[h + 1][w + 1] = '#';
			c = solve2(c);
			board[h][w] = '.';
			board[h + 1][w] = '.';
			board[h + 1][w + 1] = '.';
		}

		if (available[1]) {
			board[h][w] = '#';
			board[h][w + 1] = '#';
			board[h + 1][w] = '#';
			c = solve2(c);
			board[h][w] = '.';
			board[h][w + 1] = '.';
			board[h + 1][w] = '.';
		}

		if (available[2]) {
			board[h][w] = '#';
			board[h][w + 1] = '#';
			board[h + 1][w + 1] = '#';
			c = solve2(c);
			board[h][w] = '.';
			board[h][w + 1] = '.';
			board[h + 1][w + 1] = '.';
		}

		if (available[3]) {
			board[h][w] = '#';
			board[h + 1][w - 1] = '#';
			board[h + 1][w] = '#';
			c = solve2(c);
			board[h][w] = '.';
			board[h + 1][w - 1] = '.';
			board[h + 1][w] = '.';
		}
		return c;
	}

	private int[] findAvailable() {
		int h = -1;
		int w = -1;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if ('.' == board[i][j]) {
					h = i;
					w = j;
					break;
				}
			}
			if (h != -1 && w != -1) {
				break;
			}
		}
		return new int[] {h, w};
	}

	private boolean[] checkCover(int h, int w) {
		boolean[] available = new boolean[] {true, true, true, true};
		/**
		 * o
		 * x x 모양
		 */
		if (h + 1 >= height || w + 1 >= width || board[h + 1][w] == '#' || board[h + 1][w + 1] == '#') {
			available[0] = false;
		}

		/**
		 * o x
		 * x 모양
		 */
		if (h + 1 >= height || w + 1 >= width || board[h + 1][w] == '#' || board[h][w + 1] == '#') {
			available[1] = false;
		}

		/**
		 * o x
		 *   x 모양
		 */
		if (h + 1 >= height || w + 1 >= width || board[h][w + 1] == '#' || board[h + 1][w + 1] == '#') {
			available[2] = false;
		}

		/**
		 *   o
		 * x x 모양
		 */
		if (h + 1 >= height || w - 1 < 0 || board[h + 1][w] == '#' || board[h + 1][w - 1] == '#') {
			available[3] = false;
		}
		return available;
	}
}
