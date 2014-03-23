package org.etu.psa.fuzzysearch.tools.editdistance;

public class DamerauLevensteinDistance extends LevensteinDistance {
	
	@Override
	protected void modification(int i, int j, int cost) {
		if (i > 1 && j > 1
				&& second.charAt(i - 1) == first.charAt(j - 2)
				&& second.charAt(i - 2) == first.charAt(j - 1)) {
			matrix[i][j] = Math.min(matrix[i][j], matrix[i - 2][j - 2] + cost);
		}
	}	
}
