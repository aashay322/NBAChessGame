
/**
 * Write a description of class players here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class players
{
    private String playerName; 
    private int previousPos,futurePos;

    public players(String name,int previousPos, int futurePos)
    {
        playerName = name; 
        this.previousPos = previousPos;
        this.futurePos = futurePos;
    }

    public boolean checkMoves()
    {    
        if(playerName.equals("DemDeRozan") || playerName.equals("Kyrie Irving"))
        {
            if(futurePos - previousPos == 16 || previousPos - futurePos == 16)
            {
                return true;
            }
        }
        if(playerName.equals("2017 MVP Westbrook") || playerName.equals("DamianLillard"))
        {
            if(futurePos - previousPos == 14 || previousPos - futurePos == 14 || futurePos - previousPos == 18 || previousPos - futurePos == 18)
            {
                return true;
            }
        }
        if(playerName.equals("JoelEmbiid") || playerName.equals("Andre Drummond"))
        {
            if((futurePos - previousPos >= 15 && futurePos - previousPos <= 17) || (previousPos - futurePos >= 15 && previousPos - futurePos <= 17) )
            {
                return true;
            }
        }
        if(playerName.equals("Giannis") || playerName.equals("Anthony Davis"))
        {
            if(futurePos - previousPos == 21 || previousPos - futurePos == 21 || futurePos - previousPos == 27||
            previousPos - futurePos == 27)
            {
                return true;
            }
        }
        if(playerName.equals("StephCurry30") || playerName.equals("The King"))
        {
            if(kingChecker())
            {
                return true;
            }
        }
        if(playerName.equals("KAT") || playerName.equals("Paul George"))
        {
            if(futurePos - previousPos == 16 || previousPos - futurePos == 16 )
            {
                return true;
            }
        }
        if(playerName.equals("Kevin Durant") || playerName.equals("JHarden13"))
        {
            if(coCaptains())
            {
                return true;
            }
        }
        if( playerName.equals("KThompson11"))
        {
            if(noThompObstacle() && (futurePos - previousPos == 16 || previousPos - futurePos == 16))
            {
                return true;
            }
        }
        if(playerName.equals("Bradley Beal"))
        {
            if(noBealObstacle() && (futurePos - previousPos == 16 || previousPos - futurePos == 16))
            {
                return true;
            }
        }

        return false; 
    }

    public boolean kingChecker()
    {
        if(previousPos - futurePos >= 7 && previousPos - futurePos <= 9 || previousPos - futurePos == 1 || futurePos - previousPos == 1|| 
        futurePos - previousPos >= 7 && futurePos - previousPos <=9)
        {
            return true;
        }
        else 
            return false;
    }

    public boolean coCaptains()
    {
        if(previousPos - futurePos >= 8 && previousPos - futurePos <= 24 || futurePos - previousPos <= 24 && futurePos - previousPos >= 8 || 
        previousPos - futurePos >= 1 && previousPos - futurePos <= 3 || futurePos - previousPos >= 1 && futurePos - previousPos <=3 || 
        previousPos - futurePos == 27 || futurePos - previousPos == 27 || previousPos - futurePos == 9 || futurePos - previousPos == 9 || 
        previousPos - futurePos == 18 || futurePos - previousPos == 18 || futurePos - previousPos == 21 || previousPos - futurePos == 21 || 
        previousPos - futurePos == 7 || futurePos - previousPos == 7 || previousPos - futurePos == 14 || futurePos - previousPos == 14)
        {
            return true;
        }
        else 
            return false;
    }

    public boolean noThompObstacle()
    {
        if(ChessGame.btnEmpty[previousPos + 8].getText().equals("") && ChessGame.btnEmpty[previousPos + 16].getText().equals(""))
            return true;
        else 
            return false;
    }
    
    public boolean noBealObstacle()
    {
        if(ChessGame.btnEmpty[previousPos - 8].getText().equals("") && ChessGame.btnEmpty[previousPos - 16].getText().equals(""))
            return true;
        else 
            return false;
        
    }
}
