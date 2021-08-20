package com.stackroute.oops.league.dao;

import com.stackroute.oops.league.exception.PlayerNotFoundException;
import com.stackroute.oops.league.model.Player;
import com.stackroute.oops.league.model.PlayerTeam;

import java.util.Set;

/**
 * This class implements the PlayerTeamDao interface
 * This class has two fields playerTeamSet,playerDao and a String constant for storing file name.
 */
public class PlayerTeamDaoImpl implements PlayerTeamDao {
    private static final String TEAM_FILE_NAME = "src/main/resources/finalteam.csv";
    private PlayerDao playerDao;
    private Set<PlayerTeam> players;

    /**
     * Constructor to initialize an empty TreeSet and PlayerDao object
     */
    public PlayerTeamDaoImpl() {
        playerDao=new PlayerDaoImpl();
        players=new TreeSet<>();
    }

    /*
    Returns the list of players belonging to a particular teamTitle by reading
    from the file finalteam.csv
     */
    @Override
    public Set<PlayerTeam> getPlayerSetByTeamTitle(String teamTitle) throws IOException {
        Set<PlayerTeam> playerTeamSet=getAllPlayerTeams();
        return playerTeamSet.stream().filter(play->play.getTeamTitle().equalsIgnoreCase(teamTitle)).collect(Collectors.toSet());
    }

    //Add he given PlayerTeam Object to finalteam.csv file
    @Override
    public boolean addPlayerToTeam(Player player) throws PlayerNotFoundException,IOException {
        writeIntoFile(player);
        return true;
    }

    //Return the set of all PlayerTeam by reading the file content from finalteam.csv file
    @Override
    public Set<PlayerTeam> getAllPlayerTeams() throws IOException {
        BufferedReader bufferedReader=fileReader();
        String line;
        while((line=bufferedReader.readLine())!=null ){
            addPlayerToList(line);
        }
        return players;
    }

    public void writeIntoFile(Player player) throws IOException {
        BufferedWriter bufferedWriter=getWriterForTeam();
        bufferedWriter.write(player.toString());
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public BufferedWriter getWriterForTeam() throws IOException {
        return new BufferedWriter(new FileWriter(TEAM_FILE_NAME,true));
    }

    public void addPlayerToList(String line) {
        if (!(line.equals(""))) {
            String field[] = line.split(",");
            players.add(new PlayerTeam(field[0], field[3]));
        }
    }

    public BufferedReader fileReader() throws FileNotFoundException {
         return new BufferedReader(new FileReader(TEAM_FILE_NAME));
        }
    }

