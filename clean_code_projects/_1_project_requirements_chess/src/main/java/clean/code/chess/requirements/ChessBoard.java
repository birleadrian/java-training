package clean.code.chess.requirements;

public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;
    private BoardValidator boardValidator;
    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH+1][MAX_BOARD_HEIGHT+1];
        boardValidator = new BoardValidator();
    }

    public Pawn[][] getPieces() {
        return pieces;
    }


    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if(boardValidator.isPositionFree(xCoordinate,yCoordinate) && boardValidator.isValidCoordinates(xCoordinate,yCoordinate) && boardValidator.isValidPawnRow(xCoordinate,pieceColor)){
            pieces[xCoordinate][yCoordinate] = pawn;
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pawn.setChessBoard(this);
        }
        else{
            pawn.setXCoordinate(-1);
            pawn.setYCoordinate(-1);
        }
    }

    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return boardValidator.isValidCoordinates(xCoordinate,yCoordinate) && boardValidator.isPositionFree(xCoordinate,yCoordinate);
    }

    private class BoardValidator{
        public boolean isValidCoordinates(int xCoordinate, int yCoordinate){
            return 0 <= xCoordinate && xCoordinate <= MAX_BOARD_WIDTH && 0 <= yCoordinate && yCoordinate <= MAX_BOARD_HEIGHT;
        }

        public boolean isPositionFree(int xCoordinate, int yCoordinate){
            if(isValidCoordinates(xCoordinate,yCoordinate)){
                return getPieces()[xCoordinate][yCoordinate] == null;
            }
            return false;
        }

        public boolean isValidPawnRow(int xCoordinate,PieceColor color){
            if(color == PieceColor.WHITE){
                return xCoordinate == 1;
            }
            return xCoordinate == MAX_BOARD_HEIGHT - 1;
        }
    }
}
