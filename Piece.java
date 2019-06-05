/**
 * Classe de objetos para posicionamento e manipulação das peças
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v07 d14-11-18
 */
public class Piece{
    private boolean alive;
    private String type;
    private char coordinateY;
    private int x;
    private int y;
    private int t;

    /**
     * Método construtor de classe
     */
    public Piece(int x, int y, int t, String type){
        this.type = type;
        this.x = x;
        this.y = y;
        this.t = t;
        alive = true;
        coordinateY = 65;
        coordinateY += y;
    }



    /**
     * Seta nova posição para X
     */
    public void setPosX(int i){
        x = i;
    }
    /**
     * Seta nova posição para Y
     */
    public void setPosY(int i){
        y = i;
    }

    /**
     * Invalida peça abatida
     * *Há também a opção de torna-lá viva para casos de LOAD
     */
    public void setDefeat(boolean i){
        if(i == true) alive = false;
            else alive = true;
    }

    /**
     * Altera o formato da peça
     * *Utilizado quando o peão chega ao último quadro do tabuleiro
     */
    public void setType(String i){
        type = i;
    }

    /**
    * Método toString retorna informações necessárias para a classe Board
    */
    public String toString(int plataforma){ //x(int:0~7), y(int:0~7), t(int:1~2), chessman("   "unicode"   ") (x = 0, y = 3, t = 6, chessman = 9)
        if(plataforma == 1)return x + ", " + y + ", "+ t +", "+ chessmanBlueJ();
		if(plataforma == 2)return x + ", " + y + ", "+ t +", "+ chessmanWindows();
        return x + ", " + y + ", "+ t +", "+ chessmanLinux();
    }

    /**
    * Método toSave retorna informações necessárias para guardar o jogo em .txt
    */
    public String toSave(){ //x(int:0~7), y(int:0~7), t(int:1~2), type(String) (x = 0, y = 3, t = 6, a = 9, type = 12)
        return x + ", " + y + ", "+ t +", " + aliveInt() + ", "+ type;
    }

    /**
     * Move a peça alterando a posição X e Y;
     * Parâmetros: (ignoreCase)
     * String i String contendo a nova cordenada. String no formato FunçãoLetraNúmero (Function,y,x)
     */
    public void move(String i){
        x = Order.positionX(i);
        y = Order.positionY(i);
    }

    /**
     * Set type para compatibilidade com o tabuleiro.
     * Compatibilidade Linux
     * - peça desenhada em unicode -
     */
    public String chessmanLinux(){
        if(t == 2){ 
            if(type.equals("Peão")) return "   "+"\u265F"+"   ";
            if(type.equals("Torre"))    return "   "+"\u265C"+"   ";
            if(type.equals("Cavalo"))   return "   "+"\u265E"+"   ";
            if(type.equals("Bispo"))    return "   "+"\u265D"+"   ";
            if(type.equals("Rainha"))   return "   "+"\u265B"+"   ";
            if(type.equals("Rei"))  return "   "+"\u265A"+"   ";            
        }else{
            if(type.equals("Peão")) return "   "+"\u2659"+"   ";
            if(type.equals("Torre"))    return "   "+"\u2656"+"   ";
            if(type.equals("Cavalo"))   return "   "+"\u2658"+"   ";
            if(type.equals("Bispo"))    return "   "+"\u2657"+"   ";
            if(type.equals("Rainha"))   return "   "+"\u2655"+"   ";
            if(type.equals("Rei"))  return "   "+"\u2654"+"   ";    
        }
        return "Piece not found.";
    }
    
    /**
     * Set type para compatibilidade com o tabuleiro.
     * Compatibilidade Windows e BlueJ
     * - peça desenhada em unicode -
     */
    public String chessmanBlueJ(){ 
        if(type.equals("Peão")) return " Peão  ";
        if(type.equals("Torre"))    return " Torre ";
        if(type.equals("Cavalo"))   return "Cavalo ";
        if(type.equals("Bispo"))    return " Bispo ";
        if(type.equals("Rainha"))   return "Rainha ";
        if(type.equals("Rei"))  return "  Rei  ";            
        return "Piece not found.";
    }
	
    public String chessmanWindows(){ 
        if(type.equals("Peão")) return " Peao  ";
        if(type.equals("Torre"))    return " Torre ";
        if(type.equals("Cavalo"))   return "Cavalo ";
        if(type.equals("Bispo"))    return " Bispo ";
        if(type.equals("Rainha"))   return "Rainha ";
        if(type.equals("Rei"))  return "  Rei  ";            
        return "Piece not found.";
    }
	
    /**
     * Método que retorna a variável alive em inteiro para salvar o jogo
     */
    public int aliveInt(){
        int a = 0;
        if(alive == true) a = 1;
            else a = 0;
        return a;
    }

    /**Retorna boolean isAlive(true se estiver em tabuleiro)*/public boolean isAlive(){ return alive; }
    /**Retorna String Type*/public String getType(){ return type; }
    /**Retorna int posX (0-7)*/public int getPosX(){ return x; }
    /**Retorna int posY (0-7)*/public int getPosY(){ return y; }
    /**Retorna int Team (número do time, respectivamente.)*/public int getTeam(){ return t; }
    /**Retorna char CoordinateY(Coordenada Y em letra (Respectiva ao tabuleiro))*/public char getCoordinateY(){ coordinateY = 65; return coordinateY += y; }
}