/**
 * TicTacToeGame.java
 *
 * This class creates a simple Tic Tac Toe game.
 * In this version of the game, human player plays
 * against computer.
 * 
 */

 

// import necessary classes and interfaces
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

/**
 *
 * @author Masood Fallahpoor and Mohammad Zebardast
 */
public class TicTacToeGame  extends JFrame {

    private final static int ROWS = 3;
    private final static int COLUMNS = 3;
    private JButton button[];
    private char sign[][];
    private JPanel buttonsPanel;
    private JButton exitButton;
    
    
    public TicTacToeGame()
    {
        // set frame's title
        this.setTitle( "Tic Tac Toe" );

        /*
         * create an array to keep track of
         * human player and computer moves
         */
        sign = new char[ ROWS ][ COLUMNS ];

        // create a panel as a container for butons
        buttonsPanel = new JPanel();

        // set panel's layout manager
        buttonsPanel.setLayout( new GridLayout( ROWS , COLUMNS , 1 , 1 ) );

        // create a JButton object named exitButton
        exitButton = new JButton("Exit");

        //register an ActionListener for exitButton
        exitButton.addActionListener(
           new ActionListener()
           {
               public void actionPerformed( ActionEvent ae )
               {
                   // do a normal exit
                   System.exit( 0 );
               }
           }
        );

        // add exitButton to frame
        this.add( exitButton , BorderLayout.SOUTH );

        // create an array of JButtons
        button = new JButton[ ROWS * COLUMNS ];

        // initialize each button and add it to buttonsPanel
        for ( int i = 0 ; i < ROWS * COLUMNS ; i++ ) {
               button[ i ] = new JButton();
               button[ i ].setFocusPainted( false );
               button[ i ].setActionCommand( Integer.toString( i ) );
               button[ i ].setFont( new Font( "Tahoma" , Font.BOLD , 15 ) );
               button[ i ].setPreferredSize( new Dimension( 50 , 50 ) );
               button[ i ].setToolTipText( "Click to make your move" );
               button[ i ].addActionListener( new ButtonClickHandler() );
               buttonsPanel.add( button[ i ] );

        }

        // add buttonsPanel to frame
        this.add(buttonsPanel, BorderLayout.NORTH);

        // set some of JFrame's propeties
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // don't allow user to resize the Jframe
        this.setResizable( false );

        // resize frame to a suitable size
        this.pack();

        // don't allow user to resize the game window
        this.setVisible( true );

        resetGame();

    } // end of class constructor


    private class ButtonClickHandler implements ActionListener
    {
        private int i,j;
        
        public void actionPerformed( ActionEvent event )
        {

            JButton button = (JButton) event.getSource();
            int row = Integer.parseInt( button.getActionCommand() ) / ROWS;
            int col = Integer.parseInt( button.getActionCommand() ) % COLUMNS;

            // is the square that user has clicked is occupied?
            if ( isOccupied( row , col ) == true  )
            {
                JOptionPane.showMessageDialog( null , "This cell is already occupied." ,
                                               "Tic Tac Toe : Error", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                // the square is not occupied so put
                // an X on it.
                button.setText( "X" );

                // mark that square as occupied
                sign[ row ][ col ] = 'X';

                // check whether user has won the game
                if ( isWinner( 'X' ) == true )
                {
                    JOptionPane.showMessageDialog( null , "You won the game." ,"Tic Tac Toe:Winner",
                                                   JOptionPane.INFORMATION_MESSAGE );
                     resetGame();
                     return;
                }
                else
                {
                    // user has not won the game so it's computer
                    // turn to make a move.
                    computerMove();

                    // has compuer won the game?
                    if ( isWinner( 'O' ) == true )
                    {
                        JOptionPane.showMessageDialog( null , "Computer won the game." ,"Tic Tac Toe:Winner",
                                                       JOptionPane.INFORMATION_MESSAGE );
                        resetGame();
                        return;
                    }                      
                }
                
            }

            /*
             * check whether the game is a tie or not.
             * if it's a tie then reset the game
             */
            if ( isTie() == true )
            {
                JOptionPane.showMessageDialog( null , "It's a tie" ,"Tic Tac Toe:Tie",
                                               JOptionPane.INFORMATION_MESSAGE );
                resetGame();
            }
        }

        private boolean isTie()
        {
            boolean tie = true;

            /* loop through all array elements and check
             * whether there is an empty element or not.
             * if there is at least one empty element then
             * the game is not a tie.
             */
            for ( int r = 0 ; r < ROWS ; r++)
            {
                for ( int c = 0 ; c < COLUMNS ; c++)
                {
                    if ( sign[r][c] != 'O' && sign[r][c]!= 'X' )
                    {
                        tie = false;
                    }
                }
            }
            
            return tie;
        }
        
        private void computerMove()
        {
            // is there a possibility for user to win the game.
            if ( canWin('O','X', ROWS-1 ) == true )
            {
                // there is a possibility for user to win.
                // make a suitable move to prevent user
                // from winning the game.
                button[ this.i * ROWS + this.j ].setText("O");
                sign[this.i][this.j] = 'O';

            }
            else if ( canWin('X','O', ROWS-1 ) == true )
                {
                    // there is a possibility for computer to win.
                    // so make a suitable move to win the game.
                    button[this.i * ROWS + this.j ].setText("O");
                    sign[this.i][this.j] = 'O';
                }
            else
            {
                // neither user nor computer can win the game.
                // make a sutable move.
                for ( int r = 0 ; r < (ROWS*COLUMNS) ; r++ )
                        if ( !isOccupied( r / ROWS, r % COLUMNS ) == true )
                        {
                            button[ r ].setText("O");
                            sign[r/ROWS][r%COLUMNS]='O';
                            break;
                        }

            }

        } // end method computerMove
        
        private boolean canWin( char playerSign , char opponentSign , int count )
        {
            byte r,c;
            byte c1,c2,c3,c4;
            byte i1,i2,i3,i4;
            byte j1,j2,j3,j4;

            i1 = i2 = i3 = i4 = -1;
            j1 = j2 = j3 = j4 = -1;
            c1 = c2 = c3 = c4 = 0;
            
            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;
                i1 = i2 = i3 = i4 = -1;
                j1 = j2 = j3 = j4 = -1;
                for ( c = 0 ; c < COLUMNS ; c++ )
                {
                    // check horizontal possibility
                    if ( sign[r][c] == playerSign )
                        c1++;
                    else
                    {
                        if ( sign[r][c] != opponentSign )
                        {
                            i1 = r;
                            j1 = c;
                        }
                    }

                    // check vertical possibility
                    if ( sign[c][r] == playerSign )
                        c2++;
                    else
                    {
                        if ( sign[c][r] != opponentSign )
                        {
                            i2 = c;
                            j2 = r;
                        }
                    }

                    // check diagonal possibilities
                    if ( r == c )
                    {
                        if ( sign[r][c] == playerSign )
                            c3++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i3 = r;
                                j3 = c;
                            }
                        }
                    }
                    
                    if ( (r+c) == ROWS - 1 )
                    {
                        if ( sign[r][c] == playerSign )
                            c4++;
                        else
                        {
                            if ( sign[r][c] != opponentSign )
                            {
                                i4 = r;
                                j4 = c;
                            }
                        }
                    }

                    // check whether there is a possibility to win or not
                    if ( c1 == count && i1 != -1 && j1 != -1 )
                    {
                        this.i = i1;
                        this.j = j1;
                        return true;
                    }
                    else if ( c2 == count && i2 != -1 && j2 != -1 )
                    {
                        this.i = i2;
                        this.j = j2;
                        return true;
                    }
                    else if ( c3 == count && i3 != -1 && j3 != -1 )
                    {
                        this.i = i3;
                        this.j = j3;
                        return true;
                    }
                    else if ( c4 == count && i4 != -1 && j4 != -1 )
                    {
                        this.i = i4;
                        this.j = j4;
                        return true;
                    }
                }
            }

            // there is no possibility to win
            return false;

        } // end method canWin
        
        private boolean isOccupied( int row , int col )
        {
            if ( sign[row][col] == 'X' || sign[row][col] == 'O' )
                return true;
            else
                return false;
        }

        private boolean isWinner( char playerSign )
        {
            byte r,c;
            byte c1,c2,c3,c4;

            c1 = c2 = c3 =  c4 = 0;

            for ( r = 0 ; r < ROWS ; r++ )
            {
                c1 = c2 = 0;                
                for ( c = 0 ; c < COLUMNS ; c++ )
                {

                    // check horizontally
                    if ( sign[r][c] == playerSign )
                        c1++;

                    // check vertically
                    if ( sign[c][r] == playerSign )
                        c2++;

                    // check diagonally
                    if ( (r + c) == ROWS - 1 )
                    {
                       if ( sign[r][c] == playerSign )
                           c3++;
                    }

                    if ( r == c )
                    {
                       if ( sign[r][r] == playerSign )
                           c4++;
                    }
                }

                // check whether there is a winner or not
                if ( c1 == ROWS || c2 == COLUMNS || c3 == ROWS || c4 == ROWS )
                    return true;
            }

            // there is no winner so return false
            return false;

        } // end method isWinner

    } // end inner class ButtonClickHandler

    private void resetGame()
    {
        sign = new char[ROWS][COLUMNS];
        
        for ( int i = 0 ; i < ROWS * COLUMNS ; i++ )
            button[i].setText("");

        int answer = JOptionPane.showConfirmDialog( null , "Do you want to start with computer?",
                                                    "Tic Tac Toe:New Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if ( answer == JOptionPane.YES_OPTION )
        {
            Random rnd = new Random();
            int rndNo;
            rndNo =  rnd.nextInt(ROWS*COLUMNS);
            button[rndNo].setText( "O" );
            sign[ rndNo / ROWS ][ rndNo % COLUMNS ] = 'O';
        }
        
    } // end method resetGame


} // end of class TicTacToeGame
