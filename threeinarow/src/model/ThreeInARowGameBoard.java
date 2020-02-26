package model;

/**
 * Created by chungyang on 2/26/20.
 */
public class ThreeInARowGameBoard extends AbstractGameBoard {

    private RowBlock[][] blocksData;
    private final int rowSize;
    private final int colSize;
    private int movesLeft;



    public ThreeInARowGameBoard(int rowSize, int colSize){

        this.rowSize = rowSize;
        this.colSize = colSize;
        this.movesLeft = rowSize * colSize;
        this.blocksData = new RowBlock[rowSize][colSize];

        for(int row = 0; row < rowSize; row++){
            for(int col = 0; col < colSize; col++){
                blocksData[row][col] = new RowBlock(row, col);
            }
        }
        reset();

    }

    @Override
    public void reset() {

        movesLeft = this.rowSize * this.colSize;

        for(int row = 0; row < rowSize; row++){
            for(int col = 0; col < colSize; col++){
                blocksData[row][col].setContents("");
                blocksData[row][col].setIsLegalMove(true);
            }
        }

    }

    @Override
    public boolean placeMarker(int row, int col, String marker) {

        if(!blocksData[row][col].getIsLegalMove()){
            throw new IllegalArgumentException("Specified location is not a legal move");
        }

        blocksData[row][col].setContents(marker);
        blocksData[row][col].setIsLegalMove(false);

        return checkAntiDiagonal(row, col, marker) || checkMainDiagonal(row, col, marker) ||
                checkHorizontalRow(row, marker) || checkVerticalRow(col, marker);
    }


    @Override
    public int getMovesLeft(){
        return this.movesLeft;
    }

    @Override
    public boolean isLegalMove(int row, int col){
        return this.blocksData[row][col].getIsLegalMove();
    }

    @Override
    public String getBlockContent(int row, int col){
        return this.blocksData[row][col].getContents();
    }

    /**
     * Main diagonal goes from top left to bottom right
     */
    private boolean checkMainDiagonal(int row, int col, String marker) {


        int startingRow = Math.max(0, row - col);
        int startingCol = Math.max(0, col - row);

        int count = 0;

        while (startingRow < this.rowSize && startingCol < this.colSize && count != 3) {
            if (blocksData[startingRow][startingCol].getContents().equals(marker)) {
                count++;
            } else {
                count = 0;
            }

            startingRow++;
            startingCol++;
        }

        return count == 3;
    }


    /**
     * Anti-diagonal goes from bottom left to top right
     */
    private boolean checkAntiDiagonal(int row, int col, String marker) {

        final int sum = row + col;

        int startingRow = sum > (rowSize - 1) ? rowSize - 1 : sum;
        int startingCol = sum - startingRow;

        int count = 0;

        while (startingRow >= 0 && startingCol < this.colSize && count != 3) {

            if (blocksData[startingRow][startingCol].getContents().equals(marker)) {
                count++;
            } else {
                count = 0;
            }

            startingRow--;
            startingCol++;
        }

        return count == 3;
    }

    private boolean checkHorizontalRow(int row, String marker) {

        int count = 0;

        for (int col = 0; col < colSize && count != 3; col++) {
            if (blocksData[row][col].getContents().equals(marker)) {
                count++;
            } else {
                count = 0;
            }
        }

        return count == 3;
    }


    private boolean checkVerticalRow(int col, String marker) {

        int count = 0;

        for (int row = 0; row < rowSize && count != 3; row++) {
            if (blocksData[row][col].getContents().equals(marker)) {
                count++;
            } else {
                count = 0;
            }
        }

        return count == 3;
    }
}
