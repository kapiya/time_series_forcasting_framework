package com.edu.jnu.atm.core.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixUtlis {

	private List<Data> elemts;

	public MatrixUtlis(double[][] arry) {
		int row = arry.length;
		int col = arry[0].length;
		elemts = new ArrayList<>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j <col; j++) {
				elemts.add(new Data(i, j, arry[i][j]));
			}
		}
	}

	public int Size() {
		return this.elemts.size();
	}

	public void sort() {
		Collections.sort(elemts);
	}

	public int getRow(int index) {
		return elemts.get(index).getX();
	}

	public int getCol(int index) {
		return elemts.get(index).getY();
	}

	public double getVal(int index) {
		return elemts.get(index).getVal();
	}

	private class Data implements Comparable<Data> {
		private int x;
		private int y;
		private double val;

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public double getVal() {
			return val;
		}

		public Data(int row, int col, double val) {
			this.x = row;
			this.y = col;
			this.val = val;
		}

		@Override
		public int compareTo(Data o) {
			double d = this.val - o.val;
			if (d > 0)
				return 1;
			else if (d < 0)
				return -1;
			else
				return 0;
		}

	}
}
