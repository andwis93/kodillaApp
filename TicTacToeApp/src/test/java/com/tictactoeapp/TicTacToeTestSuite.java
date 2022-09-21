package com.tictactoeapp;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

@DisplayName("TDD: TicTacToe Test Suite")
public class TicTacToeTestSuite {

    private static int testCounter = 0;
    private static final Player player1 = new Player("Jon",true,'O');
    private static final Player player2 = new Player("Computer",false,'X');
    private static final GameMechanics gameMechanics = new GameMechanics();
    private static final Board board = new Board();
    private static final PlayerComputer playerComputer = new PlayerComputer();



    @BeforeAll
    public static void beforeAllTests(){
        board.setBoardSize(87);
        gameMechanics.winningNumber=5;
        board.preparingBoard(board.getBoardSize());

    }

    @AfterAll
    public static void afterAllTests()
    {
        System.out.println("All tests are finished.");
    }

    @BeforeEach
    public void beforeEachTest()
    {
        testCounter++;
        System.out.println("Preparing to execute test #: " + testCounter);
    }


    @AfterEach
    public void afterEachTest()
    {
        System.out.println("Test#: " + testCounter + " was executed.");
    }

    @Nested
    @DisplayName("Move logic")
    class moveTests {

        @Test
        void playerMoveTestValid() {
            //Give
            Board theBoard = new Board();
            theBoard.setBoardSize(87);
            gameMechanics.winningNumber = 5;
            theBoard.preparingBoard(theBoard.getBoardSize());
            List<Integer> listWithSelection = new ArrayList<>();
            listWithSelection.add(4);
            listWithSelection.add(9);

            //When
            boolean checkMove = gameMechanics.playerMove(theBoard.board, player1, listWithSelection, theBoard.getBoardSize()
                    , playerComputer.getEmptySquares());

            //Then
            Assertions.assertTrue(checkMove);
        }

        @Test
        void playerMoveTestInvalid() {
            //Give
            Board theBoard = new Board();
            theBoard.setBoardSize(87);
            theBoard.preparingBoard(theBoard.getBoardSize());
            List<Integer> listWithSelection = new ArrayList<>();
            listWithSelection.add(4);
            listWithSelection.add(10);
            theBoard.board.set(270, 'O');

            //When
            boolean checkMove = gameMechanics.playerMove(theBoard.board, player1, listWithSelection, theBoard.getBoardSize()
                    , playerComputer.getEmptySquares());

            //Then
            Assertions.assertFalse(checkMove);
        }


        @Test
        void computerMoveTestValid() {
            //Give
            Board theBoard = new Board();
            theBoard.setBoardSize(87);
            gameMechanics.winningNumber = 5;
            theBoard.preparingBoard(theBoard.getBoardSize());
            playerComputer.creatingComputerMoves(theBoard.board.size());


            //When
            boolean checkMove = playerComputer.computerMove(theBoard.board, player2
                    ,playerComputer.getEmptySquares());

            //Then
            Assertions.assertTrue(checkMove);
        }

        @Test
        void computerMoveTestTie() {
            //Give
            Board theBoard = new Board();
            theBoard.setBoardSize(87);
            gameMechanics.winningNumber = 5;
            theBoard.preparingBoard(theBoard.getBoardSize());

            //When
            boolean checkMove = playerComputer.computerMove(theBoard.board, player2
                    ,playerComputer.getEmptySquares());

            //Then
            Assertions.assertFalse(checkMove);
        }
    }



    @Nested
    @DisplayName("Win logic")
    class winTest {

        @Test
        void checkIfWinRowTestWin() {
            //Give
            board.resetBoard();
            for (int i = 81; i < 86; i++) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinRow = gameMechanics.checkIfWinRows(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertTrue(ifWinRow);
        }

        @Test
        void checkIfWinRowTestNoWin() {
            //Give
            board.resetBoard();
            for (int i = 83; i < 89; i++) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinRow = gameMechanics.checkIfWinRows(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertFalse(ifWinRow);
        }

        @Test
        void checkIfWinColumnTestWin() {
            //Give
            board.resetBoard();
            for (int i = 43; i < 479; i += board.getBoardSize()) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinColumn = gameMechanics.checkIfWinColumns(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertTrue(ifWinColumn);
        }

        @Test
        void checkIfWinColumnTestNoWin() {
            //Give
            board.resetBoard();
            for (int i = 43; i < 391; i += board.getBoardSize()) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinColumn = gameMechanics.checkIfWinColumns(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertFalse(ifWinColumn);
        }

        @Test
        void checkIfWinPositiveDiagonallyTestWin() {
            //Give
            board.resetBoard();
            for (int i = 91; i < 450; i += board.getBoardSize() + 1) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinDiagonallyPositive = gameMechanics.checkIfWinDiagonallyPositive(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertTrue(ifWinDiagonallyPositive);
        }

        @Test
        void checkIfWinPositiveDiagonallyTestNoWin() {
            //Give
            board.resetBoard();
            for (int i = 85; i < 450; i += board.getBoardSize() + 1) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinDiagonallyPositive = gameMechanics.checkIfWinDiagonallyPositive(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertFalse(ifWinDiagonallyPositive);
        }

        @Test
        void checkIfWinNegativeDiagonallyTestWin() {
            //Give
            board.resetBoard();
            for (int i = 100; i < 450; i += board.getBoardSize() - 1) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinDiagonallyNegative = gameMechanics.checkIfWinDiagonallyNegative(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertTrue(ifWinDiagonallyNegative);
        }

        @Test
        void checkIfWinNegativeDiagonallyTestNoWin() {
            //Give
            board.resetBoard();
            for (int i = 90; i < 450; i += board.getBoardSize() - 1) {
                board.board.set(i, 'O');
            }

            //When
            boolean ifWinDiagonallyNegative = gameMechanics.checkIfWinDiagonallyNegative(board.board, player1, board.getBoardSize());

            //Then
            Assertions.assertFalse(ifWinDiagonallyNegative);
        }
    }
}
