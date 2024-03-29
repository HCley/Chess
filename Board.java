/**
 * Classe de métodos de Objeto para manipulação do tabuleiro
 *
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v05 d11-11-18
 */
public class Board{
    private static final String EMPTY = "       ";
    private String[][] board, team;

    /**
     * Método construtor de classe
     */
    public Board(){
        board = new String[8][8];
        team = new String[8][8];
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++){
                board [x][y] = EMPTY;
                team [x][y] = EMPTY;
            }
    }

    /**
     * Método que limpa a posição XY indicada
     */
    public void clearPos(int x, int y){
        board [x][y] = EMPTY;
        team [x][y] = EMPTY;
    }
    
    /**
     * Método que limpa todo o tabuleiro
     */
    public void clearBoard(){
        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++){
                board [x][y] = EMPTY;
                team [x][y] = EMPTY;
            }
    }

    /**
     * Método que verifica se a posição está vazia.
     * retorna true se a posição estiver vazia.
     * Parâmetros: (ignoreCase)
     * int x posição respectiva
     * int y posição respectiva
     */
    public Boolean isEmpty(int x, int y){
        if(board[x][y]=="       ")return true;
        return false;
    }
    
    /**
     * Método que recebe nova posição da Piece e escreve a mesma no tabuleiro conjunto seu time
     * Parâmetros: (ignoreCase)
     * String info (informações da peça no formato x, y, t,chessman (posição x, posição y, time, tipo))
     */
    public void getObject(String info, int compatibility){
        if(info.charAt(6) != '0'){
            String convert = Character.toString(info.charAt(0));
            int objx = Integer.parseInt(convert);
            convert = Character.toString(info.charAt(3));
            int objy = Integer.parseInt(convert);
            convert = Character.toString(info.charAt(6));
            int team = Integer.parseInt(convert);

            if(compatibility > 0)  this.team[objx][objy] = " time" + team +" ";
            board[objx][objy] = info.substring(9);
        }
    }

    /**
     * Método que retorna o tabuleiro com suas posições preenchidas
     */
    public String refresh(int compatibility){
        if(compatibility == 0){
            return "\n"+           
                   "\n        ___________________________________________________________________ "+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | H |"+board[0][7]+"|"+board[1][7]+"|"+board[2][7]+"|"+board[3][7]+"|"+board[4][7]+"|"+board[5][7]+"|"+board[6][7]+"|"+board[7][7]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | G |"+board[0][6]+"|"+board[1][6]+"|"+board[2][6]+"|"+board[3][6]+"|"+board[4][6]+"|"+board[5][6]+"|"+board[6][6]+"|"+board[7][6]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | F |"+board[0][5]+"|"+board[1][5]+"|"+board[2][5]+"|"+board[3][5]+"|"+board[4][5]+"|"+board[5][5]+"|"+board[6][5]+"|"+board[7][5]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | E |"+board[0][4]+"|"+board[1][4]+"|"+board[2][4]+"|"+board[3][4]+"|"+board[4][4]+"|"+board[5][4]+"|"+board[6][4]+"|"+board[7][4]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | D |"+board[0][3]+"|"+board[1][3]+"|"+board[2][3]+"|"+board[3][3]+"|"+board[4][3]+"|"+board[5][3]+"|"+board[6][3]+"|"+board[7][3]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | C |"+board[0][2]+"|"+board[1][2]+"|"+board[2][2]+"|"+board[3][2]+"|"+board[4][2]+"|"+board[5][2]+"|"+board[6][2]+"|"+board[7][2]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | B |"+board[0][1]+"|"+board[1][1]+"|"+board[2][1]+"|"+board[3][1]+"|"+board[4][1]+"|"+board[5][1]+"|"+board[6][1]+"|"+board[7][1]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |       |       |       |       |       |       |       |       |"+
                   "\n       | A |"+board[0][0]+"|"+board[1][0]+"|"+board[2][0]+"|"+board[3][0]+"|"+board[4][0]+"|"+board[5][0]+"|"+board[6][0]+"|"+board[7][0]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n           |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |"+
                   "\n           |_______________________________________________________________|"+
                   "\n\n\n";
        }else{
            return "\n"+           
                   "\n        ___________________________________________________________________ "+
                   "\n       |   |"+team[0][7]+"|"+team[1][7]+"|"+team[2][7]+"|"+team[3][7]+"|"+team[4][7]+"|"+team[5][7]+"|"+team[6][7]+"|"+team[7][7]+"|"+
                   "\n       | H |"+board[0][7]+"|"+board[1][7]+"|"+board[2][7]+"|"+board[3][7]+"|"+board[4][7]+"|"+board[5][7]+"|"+board[6][7]+"|"+board[7][7]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][6]+"|"+team[1][6]+"|"+team[2][6]+"|"+team[3][6]+"|"+team[4][6]+"|"+team[5][6]+"|"+team[6][6]+"|"+team[7][6]+"|"+
                   "\n       | G |"+board[0][6]+"|"+board[1][6]+"|"+board[2][6]+"|"+board[3][6]+"|"+board[4][6]+"|"+board[5][6]+"|"+board[6][6]+"|"+board[7][6]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][5]+"|"+team[1][5]+"|"+team[2][5]+"|"+team[3][5]+"|"+team[4][5]+"|"+team[5][5]+"|"+team[6][5]+"|"+team[7][5]+"|"+
                   "\n       | F |"+board[0][5]+"|"+board[1][5]+"|"+board[2][5]+"|"+board[3][5]+"|"+board[4][5]+"|"+board[5][5]+"|"+board[6][5]+"|"+board[7][5]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][4]+"|"+team[1][4]+"|"+team[2][4]+"|"+team[3][4]+"|"+team[4][4]+"|"+team[5][4]+"|"+team[6][4]+"|"+team[7][4]+"|"+
                   "\n       | E |"+board[0][4]+"|"+board[1][4]+"|"+board[2][4]+"|"+board[3][4]+"|"+board[4][4]+"|"+board[5][4]+"|"+board[6][4]+"|"+board[7][4]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][3]+"|"+team[1][3]+"|"+team[2][3]+"|"+team[3][3]+"|"+team[4][3]+"|"+team[5][3]+"|"+team[6][3]+"|"+team[7][3]+"|"+
                   "\n       | D |"+board[0][3]+"|"+board[1][3]+"|"+board[2][3]+"|"+board[3][3]+"|"+board[4][3]+"|"+board[5][3]+"|"+board[6][3]+"|"+board[7][3]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][2]+"|"+team[1][2]+"|"+team[2][2]+"|"+team[3][2]+"|"+team[4][2]+"|"+team[5][2]+"|"+team[6][2]+"|"+team[7][2]+"|"+
                   "\n       | C |"+board[0][2]+"|"+board[1][2]+"|"+board[2][2]+"|"+board[3][2]+"|"+board[4][2]+"|"+board[5][2]+"|"+board[6][2]+"|"+board[7][2]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][1]+"|"+team[1][1]+"|"+team[2][1]+"|"+team[3][1]+"|"+team[4][1]+"|"+team[5][1]+"|"+team[6][1]+"|"+team[7][1]+"|"+
                   "\n       | B |"+board[0][1]+"|"+board[1][1]+"|"+board[2][1]+"|"+board[3][1]+"|"+board[4][1]+"|"+board[5][1]+"|"+board[6][1]+"|"+board[7][1]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n       |   |"+team[0][0]+"|"+team[1][0]+"|"+team[2][0]+"|"+team[3][0]+"|"+team[4][0]+"|"+team[5][0]+"|"+team[6][0]+"|"+team[7][0]+"|"+
                   "\n       | A |"+board[0][0]+"|"+board[1][0]+"|"+board[2][0]+"|"+board[3][0]+"|"+board[4][0]+"|"+board[5][0]+"|"+board[6][0]+"|"+board[7][0]+"|"+
                   "\n       |___|_______|_______|_______|_______|_______|_______|_______|_______|"+
                   "\n           |   1   |   2   |   3   |   4   |   5   |   6   |   7   |   8   |"+
                   "\n           |_______________________________________________________________|"+
                   "\n\n\n";
        }
    }

    /**
     * Método que retorna apenas o desenho do tabuleiro
     */
    public String printBoard(){
        return "\n"+           
               "\n        ___________________________________________________________________________ "+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | H |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | G |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+               
               "\n       | F |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | E |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | D |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | C |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+               
               "\n       | B |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n       |   |        |        |        |        |        |        |        |        |"+
               "\n       | A |        |        |        |        |        |        |        |        |"+
               "\n       |___|________|________|________|________|________|________|________|________|"+
               "\n           |   1    |    2   |    3   |    4   |    5   |    6   |    7   |    8   |"+
               "\n           |_______________________________________________________________________|"+
               "\n";
    }
}