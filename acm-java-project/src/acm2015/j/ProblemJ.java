package acm2015.j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class ProblemJ {
	
	private static char[][] layout = {}; // the .'s and x's in the input file
	static int numRows;
	static int numCols;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String firstLine[] = in.readLine().split(" ");
		
		numRows = Integer.parseInt(firstLine[0]);
		numCols = Integer.parseInt(firstLine[1]);
		
		// intersections stores coordinates of intersections
		ArrayList<int[]> finalIntersections = new ArrayList<int[]>();
		
		
		int i=0;
		//
		while (i < numRows) {
			char[] input = in.readLine().toCharArray();
			layout[i] = (input);
			i++;
		}
		
		
		/* for each floor tile */
		for (i = 0; i <= numRows; i++) {
			for (int j = 0; i <= numCols; j++) {
				// if blank square
				if (layout[i][j] == '.') {
					int[] coords = {i,j};
					ArrayList<int[]> intersections = new ArrayList<int[]>();
					ArrayList<int[]> verticalIntersections = new ArrayList<int[]>();
					ArrayList<int[]> horizontalIntersections = new ArrayList<int[]>();
					
					// check if square is isolated (i.e. surrounded by x's), then add to finalIntersections
					if (isIsolated(coords)) {
						finalIntersections.add(coords); //TODO: add 1 to coords
					}
				
					// check if the square is an intersection (i.e. if it has a blank square both above/below and left/right of it 
					if (isIntersection(coords)) {	
						// add to temp list of intersections
						intersections.add(coords);
						
						/* follow vertical column */
						for (int a=i; a<numRows; a++) {
							int[] tempcoords = {a, j};
							if (layout[a][j] == 'x') {
									break;
							}
							if (isIntersection(tempcoords)) {
								// add to vertical intersections
								verticalIntersections.add(tempcoords);
								/* follow row */
								for (int b=j; b<=numCols; b++) {
									if (layout[a][b] == 'x') {
										break;
									}
									int[] temptempcoords = {a,b};
									if (isIntersection(temptempcoords)) {
										verticalIntersections.add(temptempcoords);
									}
								}
							}
						}
						
						/* follow horizontal row */
						for (int c=j; c<numCols; c++) {
							int[] tempcoords = {i, c};
							if (layout[i][c] == 'x') {
								break;
							}
							if (isIntersection(tempcoords)) {
								// add to vertical intersections
								horizontalIntersections.add(tempcoords);
								/* follow row */
								for (int d=i; d<=numRows; d++) {
									int[] temptempcoords = {c,d};
									if (layout[c][d] == 'x') {
										break;
									}
									if (isIntersection(temptempcoords)) {
										horizontalIntersections.add(temptempcoords);
									}
								}
							}
						}
					} // end intersection checking
							
					// if there is crossover between vertical and horizontal intersections
					for (int[] c : horizontalIntersections) {
						if (verticalIntersections.contains(c)) {
							int[] horizontalCoords = {c[0], j};
							int[] verticalCoords = {i, c[1]}; // TODO: Check this isn't backwards
							horizontalIntersections.remove(horizontalCoords);
							verticalIntersections.remove(verticalCoords);
						}
					}
					
					layout[i][j] = 'p';
				}
			}
			
		}
	}
	
	public static boolean isIntersection(int[] coords) {
		char squareAbove = 'x'; 
		char squareBelow = 'x';
		char squareLeft = 'x';
		char squareRight = 'x';
		int i = coords[0];
		int j = coords[1];
		if (layout[i][j] == 'x') {
			return false;
		}
		
		/* set values for surrounding squares */
		if (i!=0) {
			squareAbove = layout[i-1][j];
		}
		if (i!= numRows) {
			squareBelow = layout[i+1][j];
		}
		if (j!=0) {
			squareLeft = layout[i][j-1];
		}
		if (j!=numCols) {
			squareRight = layout[i-1][j+1];
		}
		
		if ((squareAbove != 'x' || squareBelow != 'x') && (squareLeft != 'x' || squareRight != 'x')) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isIsolated(int[] coords) {
		char squareAbove = 'x'; 
		char squareBelow = 'x';
		char squareLeft = 'x';
		char squareRight = 'x';
		int i = coords[0];
		int j = coords[1];
		if (layout[i][j] == 'x') {
			return false;
		}
		
		/* set values for surrounding squares */
		if (i!=0) {
			squareAbove = layout[i-1][j];
		}
		if (i!= numRows) {
			squareBelow = layout[i+1][j];
		}
		if (j!=0) {
			squareLeft = layout[i][j-1];
		}
		if (j!=numCols) {
			squareRight = layout[i-1][j+1];
		}
		
		if ((squareAbove == 'x' && squareBelow == 'x') && (squareLeft == 'x' && squareRight == 'x')) {
			return true;
		}
		
		return false;
	}

}