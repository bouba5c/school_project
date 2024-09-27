package g55870.atl.othello.model;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
public class Game  implements Model {
    private Board board;
    private int currentPlayerId;
    private List<Integer> ia;
    private Color currentPlayerColor;
    private Color otherPlayerColor;
    private State state;
    private List<PlayerStrategy> players;
    private  List<Position> possibleBlackMoves;
    private  List<Position> possibleWhiteMoves;
    private List<Position> puttedPionHistory;
    private List<Position> opponentPionHistory;
    private List<Position> saveOpponentPion;
    private List<List<Position>> savedOponentTurnList;
    private List<Position> savePuttedPawnForRedo;
    private boolean undoClicked;
    private boolean redoClicked;
    private int gameMode;
    private boolean isPassed =false;
    private final List<Observer> observers;
    public Game()  {
        observers = new ArrayList<>();
        setState(State.NOT_STARTED);
    }
    @Override
    public int getGameMode() {
        return gameMode;
    }
    @Override
    public void start(int gameMode, int h)  {
        if (!(this.state.equals(State.NOT_STARTED) || this.state.equals(State.GAME_OVER))) {
            throw new IllegalStateException("not a started or a game over state");
        }
        this.gameMode = gameMode;
        board = new Board(h);
        players = new ArrayList<>();
        ia = new ArrayList<>();
        players.add(new Player(1, Color.BLACK));
        switch (gameMode) {
            case 1:
                players.add(new Player(2, Color.WHITE));
                break;
            case 2:
                players.add(new PlayerRandom(2, Color.WHITE));
                break;
            case 3:
                players.add(new PlayerIA(2, Color.WHITE));
                break;
            default:
                throw new IllegalArgumentException("veuillez choisir un mode de jeu possible");
        }
        puttedPionHistory = new ArrayList<>();
        opponentPionHistory = new ArrayList<>();
        savedOponentTurnList = new ArrayList<>();
        saveOpponentPion = new ArrayList<>();
        savePuttedPawnForRedo = new ArrayList<>();
        currentPlayerId = players.get(0).id();
        currentPlayerColor = players.get(0).color();
        initBoard();
        for(PlayerStrategy player : players)
        {
            if(player instanceof PlayerIA || player instanceof PlayerRandom)
            {
                ia.add(player.id());
            }
        }
        if(!ia.isEmpty())
        {
            setState(State.TURN_END);
            computerPlay(gameMode);
        }
        setState(State.PUT);
    }
    private boolean pawnCanBePut(Pawn pawn) {
        if (!pawnIsInside(pawn)) {
            throw new IllegalArgumentException("Pion is not inside the board");
        }
        if (currentPlayerId == 1) {
            return checkIfPawnCanBePut(pawn, getPossibleBlackMoves(),getPossibleWhiteMoves());
        } else if (currentPlayerId == 2) {
            return checkIfPawnCanBePut(pawn, getPossibleWhiteMoves(),getPossibleBlackMoves());
        }
        return false;
    }
    private boolean checkIfPawnCanBePut(Pawn pawn, List<Position> possibleMoves,List<Position> EnemyPossibleMoves) {
        for (Position pos : possibleMoves) {
            Pawn checkPawn = new Pawn(pos, currentPlayerColor);
            if (checkPawn.getPosition().equals(pawn.getPosition()) && checkPawn.getColor().equals(pawn.getColor())) {
                EnemyPossibleMoves.clear();
                possibleMoves.remove(pawn.getPosition());
                return true;
            }
        }
        return false;
    }

    private boolean pawnIsInside(Pawn pion)
    {
        return board.isInside(pion.getPosition());
    }
    @Override
    public int getWinner() {
        if (!getState().equals(State.GAME_OVER)) {
            throw new IllegalStateException("not a game over state");
        }
        ia = new ArrayList<>() ;
        int id = -1;
        if(board.isFull() || (possibleWhiteMoves.isEmpty() && possibleBlackMoves.isEmpty()))
        {
            if(getBlackScore()>getWhiteScore())
            {
                id= players.getFirst().id();
            }
            else if(getWhiteScore()>getBlackScore()){
                id= players.getLast().id();
            }
        }
        else if(getCurrentPlayerId()==1 )
        {
            id = players.getLast().id();

        }
        else if(getCurrentPlayerId()==2 )
        {
            id= players.getFirst().id();
        }

        return id;
    }


    @Override
    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    @Override
    public Color getCurrentPlayerColor() {
        currentPlayerColor=this.players.get(currentPlayerId-1).color();
        return currentPlayerColor;
    }
    private Color getOtherPlayerColor() {
        if (currentPlayerId == 1) {
            return players.get(1).color();
        } else {
            return players.get(0).color();
        }
    }
    @Override
    public State getState() {
        return state;
    }

    @Override
    public Pawn getPawn(Position position) {

        return this.board.getTile(position);
    }

    private List<Position> getPossibleBlackMoves() {
        possibleBlackMoves = this.board.getPossibleBlackMoves();
        removeDoublon(possibleBlackMoves);
        return possibleBlackMoves;
    }
    private List<Position> getPossibleWhiteMoves() {
        possibleWhiteMoves = this.board.getPossibleWhiteMoves();
        removeDoublon(possibleWhiteMoves);
        return possibleWhiteMoves;
    }

    private void removeDoublon(List<Position> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                if (list.get(i).equals(list.get(j))) {
                    list.remove(j);
                    size--;
                    j--;
                }
            }
        }
    }

    private void takePawn(Pawn pion) {
        Objects.requireNonNull(pion,"pion requis");
        board.removePawn(pion);
    }
    @Override
    public void putPawn(Pawn pion)  {
        if(!getState().equals(State.PUT))
        {
            throw new IllegalStateException("not put state");
        }
        if(!pawnCanBePut(pion))
        {
            throw new IllegalArgumentException("pion cannot be put");
        }
        board.put(pion);
        board.flipPawn(pion);
        puttedPionHistory.add(pion.getPosition());
        changeStateWithAction(undoClicked,State.UNDO);
        changeStateWithAction(redoClicked,State.REDO);
        changeStateWithAction(getState().equals(State.GAME_OVER),State.GAME_OVER);
        setState(State.TURN_END);
    }

    private void computerPlay(int gameMode)  {
        List<Position> list = new ArrayList<>();
        if(currentPlayerId==1)
        {
            list = getPossibleBlackMoves();
        }
        if(currentPlayerId==2)
        {
            list = getPossibleWhiteMoves();
        }
        if(this.gameMode==gameMode && gameMode!=1)
        {
            update();
            if (currentPlayerId == this.ia.getFirst() ||currentPlayerId == this.ia.getLast())
            {
                PlayerStrategy currentPlayer = players.get(currentPlayerId - 1);
                if(!list.isEmpty())
                {
                    Position randomPosition = currentPlayer.chooseMove(list,currentPlayerColor,otherPlayerColor,board);
                    Pawn pawnRandom = new Pawn(randomPosition, currentPlayerColor);
                    if (pawnCanBePut(pawnRandom)) {
                        board.put(pawnRandom);
                        board.flipPawn(pawnRandom);
                        puttedPionHistory.add(pawnRandom.getPosition());
                        nextPlayer();
                    }
                }
            }

        }
    }

    @Override
    public void nextPlayer()  {

        if (!(this.state.equals(State.TURN_END)  ||(this.state.equals(State.PUT)))) {
            throw new IllegalStateException("not a turn end or pass state");
        }
        this.currentPlayerId = currentPlayerId % players.size();
        currentPlayerId++;
        saveOpponentPion = new ArrayList<>(opponentPionHistory.size());
        saveOpponentPion.addAll(opponentPionHistory);
        savedOponentTurnList.add(saveOpponentPion);
        savedOponentTurnList.removeIf(List::isEmpty);
        opponentPionHistory.clear();
        computerPlay(gameMode);
        changeStateWithAction(getState().equals(State.GAME_OVER),State.GAME_OVER);
        setState(State.PUT);
    }
    private void changeStateWithAction(boolean action,State state)
    {
        if(action)
        {
            setState(state);
        }
    }
    @Override
    public int getBoardSize() {
        return board.getSize();
    }
    @Override

    public int getWhiteScore()
    {
        return this.board.getWhitePawns().size();
    }
    @Override
    public int getBlackScore()
    {
        return this.board.getBlackPawns().size();
    }

    private void initBoard()
    {
        Position pos = new Position((getBoardSize()/2)-1, (getBoardSize()/2)-1);
        Position pos2 = new Position(getBoardSize()/2, getBoardSize()/2);
        Position pos3 = new Position(getBoardSize()/2, (getBoardSize()/2)-1);
        Position pos4 = new Position((getBoardSize()/2)-1, getBoardSize()/2);
        Pawn pion1 = new Pawn(pos, Color.WHITE);
        Pawn pion2 = new Pawn(pos2, Color.WHITE);
        Pawn pion3 = new Pawn(pos3, Color.BLACK);
        Pawn pion4 = new Pawn(pos4, Color.BLACK);
        board.put(pion1);
        board.put(pion2);
        board.put(pion3);
        board.put(pion4);

    }
    @Override
    public void surrender()
    {
        setState(State.GAME_OVER);
    }


    private List<Position> getAllOpponentPawns()
    {
        opponentPionHistory = board.getOpponentPosition();
        removeDoublon(opponentPionHistory);
        return opponentPionHistory;
    }

    private void updatePlayersElement(List<Position> currentPossibleMoves ,List<Position> enemyPossiblesMoves)
    {
        for (Position enemyMoves:
                enemyPossiblesMoves ) {
            takePawn(new Pawn(enemyMoves,Color.NONE));
        }
        for (Position possibleMoves:
                currentPossibleMoves ) {
            board.put(new Pawn(possibleMoves,Color.NONE));
        }
        enemyPossiblesMoves.clear();
    }
    private List<Position> getSavePuttedPawnForRedo() {
        removeDoublon(savePuttedPawnForRedo);
        return savePuttedPawnForRedo;
    }

    @Override
    public void update()  {

        possibleBlackMoves = getPossibleBlackMoves();
        possibleWhiteMoves = getPossibleWhiteMoves();
        opponentPionHistory=getAllOpponentPawns() ;
        savePuttedPawnForRedo = getSavePuttedPawnForRedo();
        currentPlayerId = getCurrentPlayerId();
        otherPlayerColor = getOtherPlayerColor();
        this.currentPlayerColor = getCurrentPlayerColor();
        if (getCurrentPlayerId() == 1)
        {
            updatePlayersElement(possibleBlackMoves,possibleWhiteMoves);
        }
        else if (getCurrentPlayerId() == 2) {
            updatePlayersElement(possibleWhiteMoves,possibleBlackMoves);
        }
        if(getPossibleWhiteMoves().isEmpty() && getPossibleBlackMoves().isEmpty())
        {
            setState(State.GAME_OVER);
        }
        else if((currentPlayerId==1 && possibleBlackMoves.isEmpty()) || (currentPlayerId==2 && possibleWhiteMoves.isEmpty()))
        {
            isPassed=true;
            nextPlayer();
        }
        changeStateWithAction(undoClicked,State.UNDO);
        changeStateWithAction(redoClicked,State.REDO);
    }
    @Override
    public void  setUndo(boolean clicked)
    {
        undoClicked = clicked;
    }
    @Override
    public void  setRedo(boolean clicked)
    {
        redoClicked = clicked;
    }
    @Override
    public void undo(boolean isJavaFx)  {
        if(isJavaFx)
        {
            if(!(getState().equals(State.UNDO) ||getState().equals(State.TURN_END)||getState().equals(State.PUT)))
            {
                throw new IllegalStateException("not a undo/ turnEnd/ put state");
            }
        }
        else{
            if(!getState().equals(State.UNDO))
            {
                throw new IllegalStateException("not a undo state");
            }
        }
        undoClicked = false;
        if(gameMode==1)
        {
            savePuttedPawnForRedo.add(puttedPionHistory.getLast());
            undoManger();
        }
        else
        {
            savePuttedPawnForRedo.add(puttedPionHistory.getLast());
            savePuttedPawnForRedo.add(puttedPionHistory.get(puttedPionHistory.size()-2));
            undoManger();
            undoManger();
        }
    }
    private void undoManger()  {
        List<Position> lastLine = savedOponentTurnList.getLast();
        for(Position pos : lastLine)
        {
            getPawn(pos).flipPawn();
        }
        savedOponentTurnList.removeLast();
        takePawn(getPawn(puttedPionHistory.getLast()));
        puttedPionHistory.removeLast();
        update();

        if(isPassed)
        {
            currentPlayerId = (this.currentPlayerId == 2 ) ? 2 : 1;
        }
        currentPlayerId = (this.currentPlayerId == 1 ) ? 2 : 1;
        setState(State.PUT);

    }
    @Override
    public void redoJavaFx()  {
        if(!(getState().equals(State.REDO) ||getState().equals(State.TURN_END)||getState().equals(State.PUT)))
        {
            throw new IllegalStateException("not a redo state turned end put state ");
        }
        redoClicked = false;
        if(gameMode==1)
        {
            redoManagement();
        }
        else{
            redoManagement();
            redoManagement();
        }
    }

    private void redoManagement()
    {
        if(!savePuttedPawnForRedo.isEmpty())
        {
            Position lastPawnPosition = savePuttedPawnForRedo.getLast();
            currentPlayerColor = getCurrentPlayerColor();
            putPawn(new Pawn(lastPawnPosition,currentPlayerColor));
            savePuttedPawnForRedo.removeLast();
            update();
            if(isPassed)
            {
                currentPlayerId = (this.currentPlayerId == 2 ) ? 2 : 1;

            }
            currentPlayerId = (this.currentPlayerId == 1 ) ? 2 : 1;
        }
        setState(State.PUT);
    }
    @Override
    public void playAgain()  {
        if(!getState().equals(State.GAME_OVER))
        {
            throw new IllegalStateException("not a game over state");
        }
        setState(State.NOT_STARTED);
    }

    @Override
    public void setState(State state)  {
        this.state = state;
        notifyObservers();
    }
    @Override
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }
    @Override
    public void notifyObservers()  {
        for(Observer obs:  observers)
        {
            obs.update();
        }
    }

}
