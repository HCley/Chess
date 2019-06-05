/**
 * Classe de ordem, organiza e chama demais classes 
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v06 d14-11-18
 */
import java.lang.Object;
import java.util.ArrayList;
public class Order{
    /**
     * Método que procura pocura poPiecePiecesição X.
     * retorna inteiro com a posição X.
     * Parâmetros: (ignoreCase)
     * String i (String no formato FunçãoLetraNúmero (Function,y,x))
     */
    public static int positionX(String i){
        i = i.toUpperCase();
    return Game.positionX(i.charAt(2));
    }

    /**
     * Método que procura pocura posição Y.
     * retorna inteiro com a posição Y.
     * Parâmetros: (ignoreCase)
     * String i (String no formato FunçãoLetraNúmero (Function,y,x))
     */
    public static int positionY(String i){
        i = i.toUpperCase();
    return Game.positionY(i.charAt(1));
    }

    /*
    /**
     * Método de retorno com as funções da classe
     * (APENAS PARA VALIDAÇÃO DE TESTES)
     /
    public static String toString(String var){
    return "\n\n"+"Função: "+var.charAt(0)+
           "\n"+"Position X: "+Order.positionX(var)+
           "\n"+"Position Y: "+Order.positionY(var)+"\n";
    }
    */

    /**
     * Método que verifica se a posição está vazia.
     * retorna true se a posição estiver vazia.
     * Parâmetros: (ignoreCase)
     * Classe Board (tabuleiro para validação da posição),
     * String i (String no formato FunçãoLetraNúmero (Function,y,x))
     */
    public static boolean isEmpty(Board board, String i){
        if(board.isEmpty(positionX(i),positionY(i))==false) return false;
    return true;
    }

    /**
     * Método que compara a classe peça com posição fx
     * retorna true se a peça for da posição indicada.
     */
    public static boolean isPiece(Piece chessman, String fx){
        if(chessman.isAlive())
            if(chessman.getPosX() == positionX(fx) && chessman.getPosY() == positionY(fx)) return true;
    return false;
    }

    /**
     * Método que compara a posição com peças do time
     * retorna true se a peça for do time indicado.
     */
    public static boolean isTeam(ArrayList<Piece> chessman, String fx){
        for(int i = 0; i < chessman.size(); i++){
            if(isPiece(chessman.get(i), fx))return true;
        }
    return false;
    }

    /**
     * Método que procura a posição da peça dentro do ArrayList
     * retorna valor inteiro da posição correspondente.
     */
    public static int findPiece(ArrayList<Piece> chessman, String fx){
        for(int i = 0; i < chessman.size(); i++){
            if(isPiece(chessman.get(i), fx))return i;
        }
    return 16;
    }

    /**
     * Método que move altera a classe peça e atualiza o tabuleiro;
     * Parâmetros:
     * Classe Piece (objeto que recebe nova posição),
     * Classe Board (tabuleiro que receberá nova posição e limpará anterior),
     * String i (String contendo a nova cordenada. String no formato FunçãoLetraNúmero (Function,y,x))
     *
     */
    public static void move(Board board, Piece chessman, String i, int compatibility){
        board.clearPos(chessman.getPosX(), chessman.getPosY());
        chessman.move(i);
        board.getObject(chessman.toString(compatibility), compatibility);
        System.out.println(board.refresh(compatibility));
    }

    /**
     * Método que busca posição com parâmetro de classe e texto pré definido
     * "Qual a posição da peça? "
     */
    public static String getMove(Board board){
        return getMove(board, null, "Qual a posiçao da peça? ", false);
    }

    /**
     * Método que solicita nova posição
     * Parâmetros:
     * Classe Board (tabuleiro para validação da posição),
     * String msg (mensagem a ser exibida durante a solicitação),
     * Boolean empty (apenas retorna posição vazia se empty = true, do contrário apenas retorna posição ocupada)
     */
    public static String getMove(Board board, ArrayList<Piece> chessman, String msg, boolean empty){
        boolean enemy = false;
        String fx = "";
        fx = Interface.newRequest(msg, 'M');{
        if(empty == false && fx.charAt(0) != 'Q'){
            while(isEmpty(board, fx)){
                fx = Interface.newRequest("Quadro vazio. Digite um campo válido.", 'M');
            }
        }
        if(empty == true && fx.charAt(0) != 'Q'){
            while(isEmpty(board, fx) != true){
                if(isTeam(chessman,fx)) fx = Interface.newRequest("Quadro ocupado. Digite um campo válido.", 'M');
                else return fx;
            }
	}
	return fx;
	}
    }
}