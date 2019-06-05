/**
 * Classe para verificação de regras e correção de valores (intermédio tabuleiro e matriz)
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v17 d13-11-18
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
public class Game{
    /**
     * Método de validação que retorna se a posição (Letra,Número) faz parte do tabuleiro
     * retorna true se for parte do tabuleiro.
     */
    public static boolean isBoard(String boardPos){
        boardPos = boardPos.toUpperCase();
    return isBoardAux(boardPos, 'A', 1);
    }

    /**
     * Método de auxiliar de validação que indica se a posição faz parte das instâncias do tabuleiro
     */
    public static boolean isBoardAux(String boardPos, char l, int i){
    if(i > 8) return isBoardAux(boardPos, l += 1, 1);
    if(l == 'I') return false;

        String pos = "";
        pos = l + pos + i;
            if(pos.equals(boardPos))return true;
        return isBoardAux(boardPos, l, i + 1);
    }

    /**
     * Método que converte o char posição Y em inteiro
     * Parâmetros:
     * char y cordenada no formato de letra;
     */
    public static int positionY(char y){
        return Character.getNumericValue(y)-10;
    }
    /**
     * Método que converte o char posição X em inteiro
     * Parâmetros:
     * char x cordenada no formato de número;
     */
    public static int positionX(char x){
        return Character.getNumericValue(x)-1;
    }

    /**
     * Método que converte o char posição X em inteiro
     * Parâmetros:
     * char x cordenada no formato de número;
     */
    public static void newGame(Board board, ArrayList<Piece> chessman1, ArrayList<Piece> chessman2, int compatibility){
            //Clear previous game
        chessman1.removeAll(chessman1);
        chessman2.removeAll(chessman2);

            //Create objects Pieces
        for(int i = 0; i < 8; i++) chessman1.add(new Piece(i, 1, 1, "Peão"));
        for(int i = 0; i < 8; i += 7) chessman1.add(new Piece(i, 0, 1, "Torre"));
        for(int i = 1; i < 7; i += 5) chessman1.add(new Piece(i, 0, 1, "Cavalo"));
        for(int i = 2; i < 6; i += 3) chessman1.add(new Piece(i, 0, 1, "Bispo"));
        chessman1.add(new Piece(3, 0, 1,"Rainha"));
        chessman1.add(new Piece(4, 0, 1,"Rei"));
        chessman1.add(new Piece(0, 0, 0," ")); //trashcan
        chessman1.get(16).setDefeat(true);

        for(int i = 0; i < 8; i++) chessman2.add(new Piece(i, 6, 2, "Peão"));
        for(int i = 0; i < 8; i += 7) chessman2.add(new Piece(i, 7, 2, "Torre"));
        for(int i = 1; i < 7; i += 5) chessman2.add(new Piece(i, 7, 2, "Cavalo"));
        for(int i = 2; i < 6; i += 3) chessman2.add(new Piece(i, 7, 2, "Bispo"));
        chessman2.add(new Piece(3, 7, 2, "Rainha"));
        chessman2.add(new Piece(4, 7, 2, "Rei"));
        chessman2.add(new Piece(0, 0, 0," ")); // trashcan
        chessman2.get(16).setDefeat(true);
            //Objects end

            //Clear previous board
        board.clearBoard();

            //Object to board
        for(int i = 0; i < chessman1.size(); i++) board.getObject(chessman1.get(i).toString(compatibility), compatibility); //Team 1 to Board               
        for(int i = 0; i < chessman2.size(); i++) board.getObject(chessman2.get(i).toString(compatibility), compatibility); //Team 2 to Board

            System.out.println(board.refresh(compatibility));
    }
    
    /**
     * Método que converte o char posição X em inteiro
     * Parâmetros:
     * char x cordenada no formato de número;
     */
    public static String loadGame(Board board, ArrayList<Piece> chessman1, ArrayList<Piece> chessman2, String slot, int compatibility)throws FileNotFoundException{
        String fx = "";
		
            //Clear previous game
        chessman1.removeAll(chessman1);
        chessman2.removeAll(chessman2);
            //Clear previous board
        board.clearBoard();
        
            //Load game        
        fx = FileManager.load(chessman1, chessman2, slot);

            //Object to board
        for(int i = 0; i < 16; i++) board.getObject(chessman1.get(i).toString(compatibility), compatibility);   //Team 1 to Board               
        for(int i = 0; i < 16; i++) board.getObject(chessman2.get(i).toString(compatibility), compatibility);   //Team 2 to Board

        System.out.println(board.refresh(compatibility));
        return fx;
    }

    /**
     * Método que valida o moviento das peças de acordo com seu tipo.
     *
     *  Parâmetros:
     * Board board (Faz verificação de posição em relação ao tabuleiro)
     * ArrayList<Piece> chessmanList (Utilizada para getType da peça movida e para verificar se está se movendo para a posição de um aliado ou inimigo (caso board.isEmpty==false))
     * ArrayList<Piece> chessmanListEnemy (Utilizado para validar os movimentos do rei, tendo em vista que o mesmo não pode se por em xeque)
     * int arrPos (Posição da peça que será movida em relação ao seu ArrayList)
     * String fx (Posição destino para o qual se valida o movimento)
     *
     *  Retorno composto de comando e identificação de proibição;
     * retorna String charAt(0) == T se for possível se mover para a posição
     * retorna String charAt(0) == F se não for possível se mover para a posição
     * retorna String charAt(1) == C caso a peça possa se mover e esteja (ou não) apenas seja impedida por ser a posição de um aliado
     * retorna String charAt(1) == N caso a peça não possa se mover mesmo se a posição estiver vazia
     * retorna String charAt(1) == K caso a peça seja movida para a mesma posição de um inimigo (Prever remoção da pesa que foi eliminada). 
     * retorna String substring(2) == identificação da proibição.
     */
    public static String canMove(Board board, ArrayList<Piece> chessmanList, ArrayList<Piece> chessmanListEnemy, int arrPos, String fx){
        Piece chessman, chessmanEnemy;
        char coordinateY = 65; //Tower User - Queen User
        char coordinateX = 49; //Tower User - Queen User
        chessman = chessmanList.get(arrPos);    // Piece handle
        int distX = chessman.getPosX() - Order.positionX(fx); // Pawn User
        int distY = chessman.getPosY() - Order.positionY(fx); // Pawn User
        int dist = 0;   //Tower User - Knight User - Queen User
        int distJ = 0;  //Knight userBishop User - Queen User
        int distK = 0;  //Bishop User - Queen User

        if(distX != 0 || distY != 0){
            
            if(chessman.getType().equals("Peão")){  //Set move Pawn
                if(chessman.isAlive()){

                if(chessman.getTeam() == 1 && distY > 0) return "FNMovimento incompatível com "+chessman.getType()+". err -1.";
                if(chessman.getTeam() == 2 && distY < 0) return "FNMovimento incompatível com "+chessman.getType()+". err 0.";
                if(distX < 0) distX *= -1;
                if(distY < 0) distY *= -1;
                    if(chessman.getPosY() == 1 || chessman.getPosY() == 6){ //Origin can move twice
                        if(distX == 0){
                            if(distY > 0 && distY < 3) 
                                if(Order.isEmpty(board, fx))return "TC1" + pawnChange(board, chessman, fx);
                            else return "FNNa origem o peão anda uma a duas posições ou ataca na diagonal próxima.";
                        }
                        if(distX == 1){
                            if(Order.isEmpty(board, fx) == false){
                                if(distY == 1){
                                    if(Order.isTeam(chessmanList, fx)){
                                            chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                            return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                        }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                            else return "TK" + pawnChange(board, chessman, fx);
                                } return "FNMovimento incompatível com "+chessman.getType()+". err 1.";
                            } else return "FNMovimento incompatível com "+chessman.getType()+". err 2.";
                        }else return "FNMovimento incompatível com "+chessman.getType()+". err 3.";
                    }   //End origin moves
                    if(distY == 1){
                        if(distX == 1){
                            if(Order.isEmpty(board, fx))return "FCNão há oponentes nessa posição.";
                                else if(Order.isTeam(chessmanList, fx)){
                                            chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                            return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                        }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                            else return "TK" + pawnChange(board, chessman, fx);
                        }else if(distX == 0)return "TC2" + pawnChange(board, chessman, fx);
                    }else return "FNO peão apenas anda uma posição caso não esteja na sua origem e na diagonal apenas caso haja oponente.";
                }
            }

            if(chessman.getType().equals("Torre")){ // Set move Rook
                if(chessman.isAlive()){ 
                    coordinateY = chessman.getCoordinateY();
                    coordinateX += chessman.getPosX();
                    if(distX != 0){
                        if(distY != 0) return "FNMovimento incompatível com "+chessman.getType()+".";
                            else dist = distX;
                    }else dist = distY; 
                        if(dist < 0) dist *= -1;

                    for(; dist > 0; dist--){
                        if(distX > 0)coordinateX -= 1;
                        else if(distX < 0)coordinateX += 1;
                        else if(distY > 0)coordinateY -= 1;
                        else if(distY < 0)coordinateY += 1;

                        if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && dist != 1) return "FNHá uma peça no caminho impedindo a passagem.";
                            else if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && dist == 1){
                                if(Order.isTeam(chessmanList, fx)){
                                    chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                    return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                    else return "TK";
                            }
                        }
                     return "TC3";
                }
            }

            if(chessman.getType().equals("Cavalo")){    //Set move Knight
                if(chessman.isAlive()){ 
                if(distX == 2 || distX == -2){
                    if(distY != 1 && distY != -1) return "FNMovimento incompatível com "+chessman.getType()+".";
                        else if(Order.isEmpty(board, "M"+coordinateY+coordinateX)) return "TC4";
                            else if(Order.isTeam(chessmanList, fx)){
                                chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                            }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                else return "TK";
                }else if (distY == 2 || distY == -2)
                    if(distX != 1 && distX != -1) return "FNMovimento incompatível com "+chessman.getType()+".";
                        else if(Order.isEmpty(board, "M"+coordinateY+coordinateX)) return "TC5";
                            else if(Order.isTeam(chessmanList, fx)){
                                chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                            }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                else return "TK";
                }
            }

            if(chessman.getType().equals("Bispo")){     //Set move Bishop
                if(chessman.isAlive()){ 
                    coordinateY = chessman.getCoordinateY();
                    coordinateX += chessman.getPosX();

                    if(distX < 0) distJ = distX * -1;
                        else distJ = distX;
                    if(distY < 0) distK = distY * -1;
                        else distK = distY;
                        if(distJ != distK) return "FNMovimento incompatível com "+chessman.getType()+".";
                            else{
                                for(; distJ > 0; distJ--){
                                         if(distX > 0 && distY > 0){coordinateX -= 1; coordinateY -= 1;}
                                    else if(distX > 0 && distY < 0){coordinateX -= 1; coordinateY += 1;}
                                    else if(distX < 0 && distY > 0){coordinateX += 1; coordinateY -= 1;}
                                    else if(distX < 0 && distY < 0){coordinateX += 1; coordinateY += 1;}

                                    if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && distJ != 1) return "FNHá uma peça no caminho impedindo a passagem.";
                                        else if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && distJ == 1){
                                            if(Order.isTeam(chessmanList, fx)){
                                                chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                                return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                            }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                                else return "TK";
                                        }
                                }
                                if(Order.isEmpty(board, "M"+coordinateY+coordinateX)) return "TC6";
                                else if(Order.isTeam(chessmanList, fx)){
                                    chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                    return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                    else return "TK";
                            }
                }
            }

            if(chessman.getType().equals("Rainha")){    //Set move Queen
                if(chessman.isAlive()){ 
                    coordinateY = chessman.getCoordinateY();
                    coordinateX += chessman.getPosX();

                    if(distX < 0) distJ = distX * -1;
                        else distJ = distX;
                    if(distY < 0) distK = distY * -1;
                        else distK = distY;

                        if(distJ == distK){
                            for(; distJ > 0; distJ--){
                                if(distX > 0 && distY > 0){coordinateX -= 1; coordinateY -= 1;}
                                else if(distX > 0 && distY < 0){coordinateX -= 1; coordinateY += 1;}
                                else if(distX < 0 && distY > 0){coordinateX += 1; coordinateY -= 1;}
                                else if(distX < 0 && distY < 0){coordinateX += 1; coordinateY += 1;}

                                if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && distJ != 1) return "FNHá uma peça no caminho impedindo a passagem.";
                                    else if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && distJ == 1){
                                        if(Order.isTeam(chessmanList, fx)){
                                            chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                            return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                        }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                            else return "TK";
                                    }
                            }
                            if(Order.isEmpty(board, "M"+coordinateY+coordinateX)) return "TC7";
                            else if(Order.isTeam(chessmanList, fx)){
                                chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                            }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                else return "TK";
                        
                        }
                    if(distX != 0){
                        if(distY != 0) return "FNMovimento incompatível com "+chessman.getType()+".";
                            else dist = distX;
                    }else dist = distY; 
                        
                        if(dist < 0) dist *= -1;

                    for(; dist > 0; dist--){
                        if(distX > 0)coordinateX -= 1;
                        if(distX < 0)coordinateX += 1;
                        if(distY > 0)coordinateY -= 1;
                        if(distY < 0)coordinateY += 1;

                        if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && dist != 1) return "FNHá uma peça no caminho impedindo a passagem.";
                            else if(Order.isEmpty(board, "M"+coordinateY+coordinateX) == false && dist == 1){
                                if(Order.isTeam(chessmanList, fx)){
                                    chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                                    return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                                }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                                    else return "TK";
                            }
                    }
                     return "TC8";
                }
            }
            if(chessman.getType().equals("Rei")){   
                if(distX < -1 || distX > 1 || distY < -1 || distY > 1) return "FNMovimento incompatível com "+chessman.getType()+".";
                    else for(int i = 0; i < chessmanList.size(); i++)
                            if(Game.canMove(board, chessmanListEnemy, chessmanList, i, fx).charAt(1)=='C') return"FCO rei não pode se colocar em xeque.";
                if(Order.isEmpty(board, "M"+coordinateY+coordinateX)) return "TC9";
                    else if(Order.isTeam(chessmanList, fx)){
                        chessman = chessmanList.get(Order.findPiece(chessmanList, fx));
                        return"FCImpossível se mover para a mesma posição do(a) "+ chessman.getType() +" aliado(a).";
                    }else if(chessmanListEnemy.get(Order.findPiece(chessmanListEnemy, fx)).getType().equals("Rei"))return "WK";
                        else return "TK";
            }
        }
        if (distX == 0 && distY == 0)return "FNA peça já está nessa posição.";
        return "FNMovimento incompatível com "+chessman.getType()+". Coord fx: "+fx;
    }


    public static boolean pawnChange(Board board, Piece chessman, String fx){
        Scanner in = new Scanner (System.in);
        String newPiece = "Peão";
        if(chessman.getTeam() == 1){
            if(fx.charAt(1)=='H'){
                while(Interface.verifyPiece(newPiece)==false){
                    System.out.println("Digite a peça que tomará lugar do peão: ");
                    System.out.println("1 - Torre \n2 - Cavalo\n3 - Bispo");
                        newPiece = in.nextLine();
                        newPiece = newPiece.toUpperCase();
                        if(Interface.verifyPiece(newPiece)){
                            if(newPiece.charAt(0) == '1' || newPiece.charAt(0) == 'T') chessman.setType("Torre");
                            if(newPiece.charAt(0) == '2' || newPiece.charAt(0) == 'T') chessman.setType("Cavalo");
                            if(newPiece.charAt(0) == '3' || newPiece.charAt(0) == 'T') chessman.setType("Bispo");
                        }
                }
            }
        }else{
            if(fx.charAt(1)=='A'){
                while(Interface.verifyPiece(newPiece)==false){
                    System.out.println("Digite a peça que tomará lugar do peão: ");
                    System.out.println("1 - Torre \n2 - Cavalo\n3 - Bispo");
                        newPiece = in.nextLine();
                        newPiece = newPiece.toUpperCase();
                        if(Interface.verifyPiece(newPiece)){
                            if(newPiece.charAt(0) == '1' || newPiece.charAt(0) == 'T') chessman.setType("Torre");
                            if(newPiece.charAt(0) == '2' || newPiece.charAt(0) == 'C') chessman.setType("Cavalo");
                            if(newPiece.charAt(0) == '3' || newPiece.charAt(0) == 'B') chessman.setType("Bispo");
                        }
                }
            }
        }
        return false;
    }
}