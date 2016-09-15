import java.util.Arrays;

//417369825632158947958724316825437169791586432346912758289643571573291684164875293

		// 417369825
		// 632158947
		// 958724316
		// 825437169791586432346912758289643571573291684164875293

public class SudokuVerifier {
	private final int GLOBAL_GRID_SIZE = 81;
	private final int SUBGRID_AND_ROW_SIZE = 9;

	public final int NO_ERROR = 0;
	public final int SUBGRID_ERROR = -2;
	public final int GLOBAL_ROW_ERROR = -3;
	
	public int verify(String candidateSolution) {
		// returns 0 if the candidate solution is correct
		int error = 0;
		
		
		//check whole string length?
		
		//error = verifySubGrid(candidateSolution);
		
		//error = verifyGlobalRow(candidateSolution);
		
		return error;
	}
	
	public int verifySubGrid(String candidateSolution) {
		return checkSubGrid(candidateSolution);
	}
	
	public int verifyGlobalRow(String candidateSolution) {
		return checkGlobalRow(candidateSolution);
	}
	
	private int checkGlobalRow(String candidateSolution) {
		int[] testSet = null;
		
		// 9 row amount
		for (int i = 0; i < 9; i++) {
			// next 9 numbers to test set
			testSet = getGlobalRow(candidateSolution, i);
			System.out.println("here, i == " + i);
			if (!membersUnique(testSet))
				return GLOBAL_ROW_ERROR;
		}

		return NO_ERROR;
	}
	
	private int checkSubGrid(String candidateSolution){
		int[] testSet = null;
		
		for (int i = 0; i < GLOBAL_GRID_SIZE; i++) {
			// next 9 numbers to test set
			testSet = getSubgrid(candidateSolution, i);

			if (!membersUnique(testSet))
				return SUBGRID_ERROR;
		}

		return NO_ERROR;
	}
	
	private boolean membersUnique(int[] testSet) {
		boolean debug = true;

		if (debug)
			subgridDebug(testSet); // change name for this function
		
		Arrays.sort(testSet);
		
		if (debug)
			subgridDebug(testSet);
		
		for (int i = 1; i <= testSet.length; i++) {
			if (i != testSet[i - 1]) {
				return false;
			}
		}
		
		return true;
	}
	
	/* subgrid nums
	 * 0 1 2
	 * 3 4 5
	 * 6 7 8
	 */
	private int[] getSubgrid(String candidateSolution, int subgridNum) {
		final int SUBGRID_AMOUNT_ON_ROW = 3;
		int[] subgridCellValues = new int[SUBGRID_AND_ROW_SIZE];
		int resultIndex = 0;
		
		// select subgrid starting global row
		int globalRow = subgridNum / 3; 
		int subgridColumn = subgridNum % SUBGRID_AMOUNT_ON_ROW;
		int globalRowIndex = globalRowIndex(globalRow);
		int subgridStartIndex = subgridStartIndex(globalRowIndex, subgridColumn);
		
		// select 3 subsequent cells from 3 subsequent global rows
		for (int i = 0; i < 3; i++) {
			int column_offset = 0;
			int row_offset = (SUBGRID_AND_ROW_SIZE * i);
			while (column_offset < 3) {
				int index = subgridStartIndex + row_offset + column_offset;
				System.out.println("start " + subgridStartIndex + ", row " + row_offset + ", column " + column_offset);
				System.out.println("index == " + index);
				subgridCellValues[resultIndex] = Integer.parseInt(Character.toString(candidateSolution.charAt(index)));
				column_offset++;
				resultIndex++;
				row_offset++;
			}
		}
		
		return subgridCellValues;
	}
	
	private int globalRowIndex(int globalRowNo) {
		return globalRowNo * SUBGRID_AND_ROW_SIZE;
	}
	
	private int subgridStartIndex(int globalRowIndex, int subgridColumnNo) {
		return globalRowIndex + (3 * subgridColumnNo); // 3 == cells in subgrid 
	}
	
	private int[] getGlobalRow(String candidateSolution, int rowNum) {
		int[] globalRowNums = new int[SUBGRID_AND_ROW_SIZE];

		/* first row is row 0 */
		for (int i = 0; i < SUBGRID_AND_ROW_SIZE; i++) {
			globalRowNums[i] = Integer.parseInt(Character.toString(candidateSolution.charAt(globalRowIndex(rowNum) + i)));
		}
		
		return globalRowNums;
	}
	
	private void subgridDebug(int[] testSet) {
		for (int k = 0; k < SUBGRID_AND_ROW_SIZE; k++) {
			System.out.print(testSet[k]);
		}
		
		System.out.print("\n");
	}
}
