import java.awt.*;
import java.awt.event.*;
import java.awt.Image.*;
import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.ImageIcon.*;
import javax.swing.Icon;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class ChessGame extends JFrame implements ActionListener
{

    JFrame window = new JFrame("NBA Chess Game");

    JMenuBar mnuMain = new JMenuBar();
    JMenuItem   mnuNewGame = new JMenuItem("  New Game"), 
    mnuGameTitle = new JMenuItem("|Tic Tac Toe|   "),
    mnuStartingPlayer = new JMenuItem("Starting Player  "),
    mnuRules = new JMenuItem("  Rules "),
    mnuExit = new JMenuItem("    Quit");

    static JButton btnEmpty[] = new JButton[65];
    JPanel  pnlNewGame = new JPanel(),
    pnlNorth = new JPanel(),
    pnlSouth = new JPanel(),
    pnlTop = new JPanel(),
    pnlBottom = new JPanel(),
    pnlPlayingField = new JPanel();
    JPanel radioPanel = new JPanel();
    static String playerOne = "";
    static String playerTwo = "";

    private JRadioButton SelectTeam1 = new JRadioButton("Team Steph", false);
    private  JRadioButton SelectTeam2 = new JRadioButton("Team LeBron", false);

    private ButtonGroup radioGroup;
    private  String startingPlayer= "";
    final int X = 500, Y = 500, color = 190; // size of the game window
    private boolean inGame = false;
    private boolean win = false;
    private boolean btnEmptyClicked = false;
    private boolean setTableEnabled = false;
    private String message;
    private Font font = new Font("Rufscript", Font.BOLD, 24);
    private int remainingMoves = 1;
    int size =(int)(Math.sqrt(btnEmpty.length-1));
    private String replaceText = "";
    Image image;
    boolean firstClick = true; 
    boolean secondClick = false; 
    String [] teamSteph = {"DemDeRozan","KAT","JoelEmbiid","JHarden13","StephCurry30","Giannis","DamianLillard","KThompson11"};
    String [] teamLeBron = {"Kyrie Irving","Paul George","Andre Drummond","Kevin Durant","The King","Anthony Davis","2017 MVP Westbrook","Bradley Beal"};
    String [] teamStephImg = {"derozan.png","kat.png","joel.png","harden.jpg","steph.jpg","giannis.png","damian.png","thompson.png"};
    String [] teamLeBronImg = {"kyrie.png","george.png","andre.png","kd.jpg","KingJames.png","davis.png","westbrook.png","beal.jpg"};
    ImageIcon iconic = new ImageIcon("nba_logo.png");
    ImageIcon luck = new ImageIcon("goodLuck.jpg");
    ImageIcon crying = new ImageIcon("crying.jpg");
    int previousPos; 
    int futurePos; 
    int moves;
    String ownPlayer = "";

    public ChessGame()
    {
        JLabel label = new JLabel ("<html> Welcome to the first ever NBA All-Star NBA Chess game! <br> This game comprises of your favorite players" + 
                " fighting each other on the chess board. <br> For the rules, click on the Rules tab! Enjoy!! </html>");
        label.setFont(new Font("Ariel",Font.BOLD,24));
        JOptionPane.showMessageDialog(null,label,"WELCOME",JOptionPane.INFORMATION_MESSAGE,iconic);

        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(300, 180);
        window.setResizable(true);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        //------------  Sets up Panels and text fields  ------------------------//
        // setting Panel layouts and properties
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNorth.setBackground(new Color(0, 75, 255));
        pnlSouth.setBackground(new Color(0, 75, 255));

        pnlTop.setBackground(new Color(255,150,0));
        pnlBottom.setBackground(new Color(255,150,0));

        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        radioPanel.setBackground(new Color(0,75,255));
        pnlBottom.setBackground(new Color(0,75,255));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Who Goes First?"));
        radioPanel.setFont(new Font("Comic Sans MS",Font.BOLD,36));

        // adding menu items to menu bar
        mnuMain.add(mnuGameTitle);
        mnuGameTitle.setEnabled(false);
        mnuGameTitle.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        mnuMain.add(mnuNewGame);
        mnuNewGame.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        mnuMain.add(mnuStartingPlayer);
        mnuStartingPlayer.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        mnuMain.add(mnuRules);
        mnuRules.setFont(new Font("Comic Sans MS",Font.BOLD,18));
        mnuMain.add(mnuExit);
        mnuExit.setFont(new Font("Comic Sans MS",Font.BOLD,18)); //---->Menu Bar Complete

        // adding X & O options to menu
        SelectTeam1.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        SelectTeam1.setFont(new Font("Comic Sans MS",Font.BOLD,24));
        radioGroup = new ButtonGroup(); // create ButtonGroup
        radioGroup.add(SelectTeam1); // add plain to group
        radioGroup.add(SelectTeam2);
        radioPanel.add(SelectTeam1);
        radioPanel.add(SelectTeam2);
        SelectTeam1.setFont(new Font ("Comic Sans MS",Font.BOLD,36));
        SelectTeam2.setFont( new Font ("Comic Sans MS",Font.BOLD,36));

        // adding Action Listener to all the Buttons and Menu Items
        mnuNewGame.addActionListener(this);
        mnuExit.addActionListener(this);
        mnuStartingPlayer.addActionListener(this);
        mnuRules.addActionListener(this);

        // setting up the playing field
        pnlPlayingField.setLayout(new GridLayout(size, size, size-1, size-1));
        pnlPlayingField.setBackground(Color.black);
        for(int x=1; x <= btnEmpty.length-1; ++x)   
        {
            btnEmpty[x] = new JButton();
            if(x >= 9 && x <= 16 || x >= 25 && x <= 32 || x >= 41 && x <= 48 || x >= 57 && x <= 64)
            {
                if(x % 2 == 0)
                {
                    btnEmpty[x].setIcon(new ImageIcon("black.jpg","black"));
                }

                else 
                { 
                    btnEmpty[x].setIcon(new ImageIcon("white.jpg","white"));
                }
            }
            else 
            {
                if(x % 2 == 0)
                    btnEmpty[x].setIcon(new ImageIcon("white.jpg","white"));
                else 
                    btnEmpty[x].setIcon(new ImageIcon("black.jpg","black"));
            }  
            btnEmpty[x].addActionListener(this);
            pnlPlayingField.add(btnEmpty[x]);
            btnEmpty[x].setEnabled(setTableEnabled);
        }

        // adding everything needed to pnlNorth and pnlSouth
        pnlNorth.add(mnuMain);
        NBALogic.ShowGame(pnlSouth,pnlPlayingField);

        // adding to window and Showing window
        window.add(pnlNorth, BorderLayout.NORTH);
        window.add(pnlSouth, BorderLayout.CENTER);
        window.setVisible(true);

        setPlayers();

    }// End GUI

    public void actionPerformed(ActionEvent click)  
    {
        // get the mouse click from the user

        Object source = click.getSource();
        int lastMove = 0; 
        // check if a button was clicked on the gameboard
        for(int currentMove=1; currentMove <= btnEmpty.length-1 ; ++currentMove) 
        {
            if(firstClick && source == btnEmpty[currentMove] && remainingMoves >= 0)  
            {
                firstClick = false; 
                secondClick = true;
                btnEmptyClicked = true;
                replaceText = btnEmpty[currentMove].getText();

                if(checkPlayer() != checkArray(replaceText))
                {
                    JOptionPane.showMessageDialog(null,"Wrong player! Don't cheat!","Warning!",JOptionPane.ERROR_MESSAGE);
                    firstClick = true; 
                    secondClick = false;
                    break;
                }
                else if(replaceText.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Select a player!");
                    firstClick = true;
                    secondClick = false;
                    break;
                }
                else
                {
                    btnEmpty[currentMove].setText(""); 
                    if(currentMove >= 9 && currentMove <= 16 || currentMove >= 25 && currentMove <= 32 || currentMove >= 41 && currentMove <= 48 ||
                    currentMove >= 57 && currentMove <= 64)
                    {
                        if(currentMove % 2 == 0)
                        {
                            btnEmpty[currentMove].setIcon(new ImageIcon("black.jpg"));
                        }

                        else 
                        { 
                            btnEmpty[currentMove].setIcon(new ImageIcon("white.jpg"));
                        }
                    }
                    else 
                    {
                        if(currentMove % 2 == 0)
                            btnEmpty[currentMove].setIcon(new ImageIcon("white.jpg"));
                        else 
                            btnEmpty[currentMove].setIcon(new ImageIcon("black.jpg"));
                    }  
                    lastMove = currentMove;
                }
                previousPos = currentMove;
            }
            else if(secondClick && source == btnEmpty[currentMove] && remainingMoves < btnEmpty.length)
            {
                if(currentMove == lastMove)
                {
                    JOptionPane.showMessageDialog(null, "Choose another spot or change player.");
                    secondClick = true; 
                    firstClick = false;

                }

                else 
                {
                    futurePos = currentMove;
                    String ownPlayer = btnEmpty[futurePos].getText();
                    if(ownPlayer != "" && suicide(ownPlayer))
                    {

                        ImageIcon icon = new ImageIcon("laughingguy.jpg");
                        JLabel leaving = new JLabel("Why you beating your own players? Replay!");
                        leaving.setFont(new Font("Comic Sans MS",Font.BOLD,28));
                        JOptionPane.showMessageDialog(null,leaving,"WHY",JOptionPane.ERROR_MESSAGE,icon);
                        secondClick = false; 
                        firstClick = true;
                        btnEmpty[previousPos].setText(replaceText);
                        btnEmpty[previousPos].setIcon(getIcon(replaceText));
                        btnEmpty[futurePos].setText(ownPlayer);
                        btnEmpty[futurePos].setIcon(getIcon(ownPlayer));

                    }
                    else {

                        players obj = new players(replaceText,previousPos, futurePos);
                        if(obj.checkMoves())
                        {
                            btnEmpty[currentMove].setText(replaceText);
                            btnEmpty[currentMove].setIcon(getIcon(replaceText));
                            pnlPlayingField.requestFocus();
                            ++remainingMoves;
                            secondClick = false; 
                            firstClick = true;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Choose another spot!","Error!",JOptionPane.ERROR_MESSAGE); 
                            pnlPlayingField.requestFocus();
                            btnEmpty[previousPos].setText(replaceText);
                            btnEmpty[previousPos].setIcon(getIcon(replaceText));
                            secondClick = false; 
                            firstClick = true; 
                        }
                    }

                } 

                if(checkLeBron() && checkCurry() == false)
                {
                    JOptionPane.showMessageDialog(null,"Team LeBron Wins!","Game Over!",JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null,"Resetting game......","Over", JOptionPane.PLAIN_MESSAGE);
                    RedrawGameBoard();
                }
                else if(checkCurry() && checkLeBron() == false)
                {
                    JOptionPane.showMessageDialog(null,"Team Steph Wins!","Game Over!",JOptionPane.PLAIN_MESSAGE);
                    JOptionPane.showMessageDialog(null,"Resetting game......","Over", JOptionPane.PLAIN_MESSAGE);
                    RedrawGameBoard();
                }
            }

        }

        if(source == mnuNewGame)    
        {
            System.out.println(startingPlayer);
            NBALogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                pnlPlayingField,pnlBottom,radioPanel);
            if(startingPlayer.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Select a Starting Player", 
                    "Oops..", JOptionPane.ERROR_MESSAGE);
                NBALogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                if(inGame)  
                {
                    int option = JOptionPane.showConfirmDialog(null, "If you start a new game," +
                            " you'll lose your MVP points" + "n" +"Are you sure you want to continue?"
                        , "New Game?" ,JOptionPane.YES_NO_OPTION);
                    if(option == JOptionPane.YES_OPTION)    
                    {
                        inGame = false;
                        startingPlayer = "";
                        setTableEnabled = false;
                    }
                    else
                    {
                        NBALogic.ShowGame(pnlSouth,pnlPlayingField);
                    }
                }
                // redraw the gameboard to its initial state
                if(!inGame) 
                {
                    RedrawGameBoard();
                }
            }       
        }       
        // exit button
        else if(source == mnuExit)  
        {

            int option = JOptionPane.showConfirmDialog(null, "Do you wanna leave us so soon?", 
                    "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null,crying);
                System.exit(0);
            }
        }

        else if(source == mnuStartingPlayer)  
        {
            if(inGame)  
            {
                JOptionPane.showMessageDialog(null, "Cannot select a new Starting "+
                    "Player at this time.nFinish the current game, or select a New Game "+
                    "to continue", "Game In Session..", JOptionPane.INFORMATION_MESSAGE);
                NBALogic.ShowGame(pnlSouth,pnlPlayingField);
            }
            else
            {
                setTableEnabled = true;
                pnlSouth.setVisible(false);
                pnlSouth.setVisible(true);
                NBALogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
                    pnlPlayingField,pnlBottom,radioPanel);

                SelectTeam1.addActionListener(new RadioListener());
                SelectTeam2.addActionListener(new RadioListener());
                radioPanel.setLayout(new GridLayout(2,1));

                radioPanel.add(SelectTeam1);
                radioPanel.add(SelectTeam2);
                pnlSouth.setLayout(new GridLayout(2,1, 2, 1));
                pnlSouth.add(radioPanel);
                pnlSouth.add(pnlBottom);
            }
        }

        else if(source == mnuRules)  
        {
            JLabel rules = new JLabel("<html>Welcome to NBA All Star Chess! Rules: <br><br>    Players: <br>" +
                    "       Captains: Steph Curry & Lebron James <br>    Can move 1 space in any direction AND can capture any player 1 space ahead of them in the same direction after the move<br><br>" +
                    "       Co-Captains: Kevin Durant & James Harden<br>    Can jump 2 spaces in any direction and land on the third space, capturing an enemy if he lands on them (only the third spot)<br><br>" +
                    "       Player Pair 1: Demar DeRozan & Kyrie Irving <br>    Can jump 1 space forward and land on the second space, capturing enemy piece if landed on<br><br>"+
                    "       Player Pair 2: Russell Westbrook & Damian Lillard <br>  Can jump 1 space diagonally and land on the second space, capturing an enemy piece if landed on<br><br>"+ 
                    "       Player Pair 3: Joel Embiid & Andre Drummond <br>  Can move 2 spaces forward and one space additionally left or right to capture an enemy piece<br><br>"+
                    "       Player Pair 4: Giannis Antetokounmpo & Anthony Davis <br>  Can move 3 spaces diagonally<br><br>"+
                    "       Player Pair 5: Karl-Anthony Towns & Paul George <br>  Can move 2 spaces forward and can capture diagonally once<br><br>"+
                    "       Player Pair 6: Klay Thompson & Bradley Beal <br>  Can move 2 spaces forward<br><br>"+
                    "To Win: Capture the Captain of the Opposing Team<br><br><br>"+
                    "Good Luck!");
            rules.setFont(new Font("Comic Sans MS",Font.BOLD,18));
            JOptionPane.showMessageDialog(null,rules,
                "Rules", JOptionPane.INFORMATION_MESSAGE,luck);

            pnlSouth.setVisible(false); 
            pnlSouth.setVisible(true);

            // End Action Performed
        }
    }

    private void RedrawGameBoard()  
    {
        NBALogic.ClearPanelSouth(pnlSouth,pnlTop,pnlNewGame,
            pnlPlayingField,pnlBottom,radioPanel);
        NBALogic.ShowGame(pnlSouth,pnlPlayingField);       

        remainingMoves = 1;

        for(int x=1; x <= btnEmpty.length-1; ++x)   
        {
            btnEmpty[x].setText("");
            if(x >= 9 && x <= 16 || x >= 25 && x <= 32 || x >= 41 && x <= 48 || x >= 57 && x <= 64)
            {
                if(x % 2 == 0)
                {
                    btnEmpty[x].setIcon(new ImageIcon("black.jpg","black"));
                }

                else 
                { 
                    btnEmpty[x].setIcon(new ImageIcon("white.jpg","white"));
                }
            }
            else 
            {
                if(x % 2 == 0)
                    btnEmpty[x].setIcon(new ImageIcon("white.jpg","white"));
                else 
                    btnEmpty[x].setIcon(new ImageIcon("black.jpg","black"));
            }  
            btnEmpty[x].setEnabled(setTableEnabled);

            win = false;        
            setPlayers();
        }
    }

    private class RadioListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            JRadioButton theButton = (JRadioButton)event.getSource();
            if(theButton.getText().equals("Team Steph")) 
            {
                playerOne = "Team Steph";
                playerTwo = "Team LeBron";
            }
            else 
            {
                playerOne = "Team LeBron";
                playerTwo = "Team Steph";
            }

            // redisplay the gameboard to the screen
            pnlSouth.setVisible(false); 
            pnlSouth.setVisible(true);          
            RedrawGameBoard();
        }
    }// End RadioListener

    public void setPlayers()
    {
        int count = 0; 
        for(int i = 1; i <= 8; i++)
        {
            btnEmpty[i].setIcon(new ImageIcon(teamStephImg[count]));
            count++;
        }
        count = 0;
        for(int i = 57; i <= 64; i++)
        {
            btnEmpty[i].setIcon(new ImageIcon(teamLeBronImg[count])); 
            count++;
        }
        count = 0;
        for(int i = 1 ; i <= 8; i++)
        {
            btnEmpty[i].setText(teamSteph[count]);
            count++;
        }
        count = 0; 
        for(int i = 57; i <= 64; i++)
        {
            btnEmpty[i].setText(teamLeBron[count]);
            count++;
        }

    }

    public ImageIcon getIcon(String name)
    {
        ImageIcon im = new ImageIcon("black.png");
        for(int i = 0; i < teamLeBron.length; i++)
        {
            if(teamLeBron[i].equals(name))
            {
                im = new ImageIcon(teamLeBronImg[i]);

            }
        }
        for(int i = 0; i < teamSteph.length; i++)
        {
            if(teamSteph[i].equals(name))
            {
                im = new ImageIcon(teamStephImg[i]);
            }
        }
        return im;
    }

    public boolean checkLeBron()
    {
        int cnt = 0;
        boolean check = false;
        for(int x=1; x <= btnEmpty.length-1; x++)
        {
            if(btnEmpty[x].getText().equals("The King"))
            {
                return true;
            }
        }
        return check;
    }

    public boolean checkCurry()
    {
        int cnt = 0; 
        boolean check = false;
        for(int x = 1; x <= btnEmpty.length-1; x++)
        {
            if(btnEmpty[x].getText().equals("StephCurry30"))
            {
                return true;
            }
        }

        return check;
    }

    public String checkPlayer()
    {
        if(remainingMoves % 2 == 0)
        {
            return playerTwo;
        }
        else 
        {
            return playerOne;
        }
    }

    public String checkArray(String text)
    {
        String output = "";
        for(int x=0; x < teamSteph.length; x++)
        {
            if(teamSteph[x].equals(text))
            {
                output ="Team Steph";
            }
        }
        for(int x = 0; x < teamLeBron.length; x++)
        {
            if(teamLeBron[x].equals(text))
            {
                output = "Team LeBron";
            }
        }
        return output;

    }

    public boolean suicide (String ownPlayer)
    {
        if(checkArray(replaceText).equals("Team Steph"))
        {
            for(int x=0; x < teamSteph.length; x++)
            {
                if(teamSteph[x].equals(ownPlayer))
                {
                    return true;
                }
            }
        }
        else if(checkArray(replaceText).equals("Team LeBron"))
        {
            for(int x = 0; x < teamLeBron.length; x++)
            {
                if(teamLeBron[x].equals(ownPlayer))
                {
                    return true;
                }
            }

        }

        return false;
    }
}