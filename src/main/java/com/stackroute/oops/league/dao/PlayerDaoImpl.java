package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerAlreadyExistsException;
import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;

import java.util.List;

/**
 * This class is implementing the PlayerDao interface
 * This class has one field playerList and a String constant for storing file name
 */
public class PlayerDaoImpl implements PlayerDao {
    private static final String PLAYER_FILE_NAME = "src/main/resources/player.csv";
    private List<Player> playerList;

    /**
     * Constructor to initialize an empty ArrayList for playerList
     */
    public PlayerDaoImpl() {
        playerList=new ArrayList<>();
    }

    /**
     * Return true if  player object is stored in "player.csv" as comma separated fields successfully
     * when password length is greater than six and yearExpr is greater than zero
     */
    @Override
    public boolean addPlayer(Player player) throws PlayerAlreadyExistsException, IOException {
        if(player.getPassword().length()>6&&player.getYearExpr()>0) {
            writeToFile(player);
            return true;
        }
        return false;
    }

    //Return the list of player objects by reading data from the file "player.csv"
    @Override
    public List<Player> getAllPlayers() throws FileNotFoundException,IOException {
        playerList.clear();

        BufferedReader bufferedReader=getReader();
        String line;
        while ((line=bufferedReader.readLine())!=null){
            String[] fields=line.split(",");
            playerList.add(new Player(fields[0],fields[1],fields[2],Integer.parseInt(fields[3])));
        }
        return playerList;
    }

    /**
     * Return Player object given playerId to search
     */
    @Override
    public Player findPlayer(String playerId) throws PlayerNotFoundException,FileNotFoundException,IOException {
        List<Player> players= getAllPlayers();
        for(Player player:players)
        {
            if(player.getPlayerId().equals(playerId))
                return player;
        }
        throw new PlayerNotFoundException();
    }

    public void writeToFile(Player player) throws IOException{
        BufferedWriter bufferedWriter=getWriter();
        bufferedWriter.write(getPlayerDetail(player));
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public void readFile(Player player) throws IOException, PlayersAlreadyExistException {
        BufferedReader bufferedReader=null;
        bufferedReader=getReader();
        String line;
        while((line=bufferedReader.readLine())!=null){
            if(getPlayerDetail(player).equals(line)){
                throw new PlayerAlreadyExistsException();

            }
        }

        bufferedReader.close();

    }

    public BufferedWriter getWriter() throws IOException {
        return new BufferedWriter(new FileWriter(PLAYER_FILE_NAME,true));
    }

    public String getPlayerDetail(Player player){
        return player.getPlayerId() + "," + player.getPlayerName() + "," + player.getPassword() + "," + player.getYearExpr();
    }

    public BufferedReader getReader() throws FileNotFoundException {
        return new BufferedReader(new FileReader(PLAYER_FILE_NAME));
    }

}
