package org.etu.psa.fuzzysearch.tools.editdistance;

public class LevensteinDistance implements IEditDistance {
	protected String first;
	protected String second;
	protected int[][] matrix;
	
	public int getDistance(String first, String second) {
		this.first = first;
		this.second = second;
				
		int n = first.length();
		int m = second.length();

		matrix = new int[m + 1][n + 1];
		
		for(int j = 1; j <= n; j++) {
			matrix[0][j] = j;
		}
		
		for(int i = 1; i <= m; i++) {
			matrix[i][0] = i;
		}
		
		for(int j = 1; j <= n; j++) {
			for(int i = 1; i <= m; i++) {
					int cost = second.charAt(i - 1) == first.charAt(j - 1) ? 0 : 1;
					matrix[i][j] = Math.min(Math.min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1), matrix[i - 1][j - 1] + cost);
					
					modification(i, j, cost);
			}
		}
		return matrix[m][n];
	}
	
	protected void modification(int i, int j, int cost) {
	}

}
