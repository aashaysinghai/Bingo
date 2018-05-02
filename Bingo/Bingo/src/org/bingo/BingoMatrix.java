package org.bingo;

import java.io.Serializable;

public class BingoMatrix implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[][] matrix;
	private String message;

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
