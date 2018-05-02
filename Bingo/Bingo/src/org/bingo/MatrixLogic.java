package org.bingo;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MatrixLogic {

	public static void main(String[] args) {
		
		doConnection();
		int n=3;
		int players = 2;
		int[][][] matrices = makeThreeMatrix(players,n);
	}

	private static void doConnection() {
		
	}

	public static int[][][] makeThreeMatrix(int players,int n) {
		int[][][] matrices = new int[players][n][n];
		int min=1;
		int max = n*n;
		
		for(int i=0;i<players;i++) {
					int[] arr = new int[n*n];
					for(int a=0;a<n*n;a++) {
						arr[a]=a+1;
					}
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					while(true) {
						int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
						if(arr[randomNum-1] != 0) {
							matrices[i][j][k] = randomNum;
							arr[randomNum-1] = 0;
							break;
						}
					}
				}
			}
		}
		
		System.out.println("-----------------------");
		
		for(int i=0;i<players;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					System.out.print(matrices[i][j][k] +" ");
				}
				System.out.println();
			}
			
			System.out.println("====================================");
		}
		
		return matrices;
	}

}
