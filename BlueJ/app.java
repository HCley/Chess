/**
 * Classe de aplicação main
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v16 d16-11-18
 */
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
public class app{   
    public static void main(String args[])throws FileNotFoundException{
        System.out.print("\f");
        //Create Scanner and board object
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        Board board = new Board();

                //Ref to handle Class Piece
                String chessmanMove = "";
                Piece chessman;
                char coordinateY = 65;
                char coordinateX = 49;

                //Var to handle char
                String fx = "";
                String fs = "";
                String pos = "";
                int arrPos = 0;

                //Var to handle with game and load interface
                boolean start = true;
                boolean turn = true;
                String slot = "";
                String answare = "";
                int compatibility = 0;

                //Array bloc organizer
            ArrayList<Piece> chessman1 = new ArrayList<Piece>();
            ArrayList<Piece> chessman2 = new ArrayList<Piece>();


            //Running game
        do{ 


                //Menu set
            if(fx.equals(""))fx = Interface.menu();
            fs = fx;

            if(fx.equals("")==false && fx.charAt(0) == 'B') {fx = ""; start = true; pos = "";}
            if(fx.equals("")==false && fx.charAt(0) == 'C') {
                if(fx.charAt(1) == '0') compatibility = 0;
                    else if(fx.charAt(1) == '1')compatibility = 1;
						else compatibility = 2;
                fx = ""; start = true; pos = "";
            }
            
                    //Multiplayer set
            if(fx.equals("")==false) //Shield String Text
            while(fx.charAt(0) == 'M'){
                if(start){Game.newGame(board, chessman1, chessman2, compatibility); start = false;}
                    //Player 1 turn
                while(turn == true){
                    if(pos.equals("")){pos = Interface.newRequest("Jogador 1: Qual a posição da peça que será movida? [LetraNúmero]",'M');
                        if(Interface.verify(pos)){fx = pos; break;}
                            if(Order.isEmpty(board, pos)){System.out.println("Sem peças na posição indicada."); pos = "";
                            }else if(Order.isTeam(chessman1, pos)){
                                    arrPos = Order.findPiece(chessman1, pos);
                                    chessman = chessman1.get(arrPos);
                                }else pos = Interface.newRequest("Jogador 1: Peça do oponente, você apenas pode mover as suas peças.", 'M');
                    }else{
                        chessman = chessman1.get(arrPos);
                        pos = Interface.newRequest("Jogador 1: Mover a peça para onde? [LetraNúmero]",'M');
                            if(Interface.verify(pos)){fx = pos; break;}
                        if(pos.charAt(0) != 'M') pos = "";
                            chessmanMove = Game.canMove(board, chessman1, chessman2, arrPos, pos);
                        if(chessmanMove.charAt(0) == 'W'){System.out.println("Parabéns jogador 1, você ganhou!"); Order.move(board, chessman, pos, compatibility); fx = "Q"; break;}
                        if(chessmanMove.charAt(0) == 'T'){
                        if(chessmanMove.charAt(1) == 'K')
                            chessman2.set(Order.findPiece(chessman2, pos), chessman2.get(16));

                            Order.move(board, chessman, pos, compatibility);
                            pos = "";
                            turn = false;
                        }else System.out.println(chessmanMove.substring(2));
                    }
                }//End player 1 turn

                        //Player 2 turn
                    while(turn == false){
                        if(pos.equals("")){pos = Interface.newRequest("Jogador 2: Qual a posição da peça que será movida? [LetraNúmero]",'M');
                            if(Interface.verify(pos)){fx = pos; break;}
                                if(Order.isEmpty(board, pos)){System.out.println("Sem peças na posição indicada."); pos="";
                                    }else if(Order.isTeam(chessman2, pos)){
                                        arrPos = Order.findPiece(chessman2, pos);
                                        chessman = chessman2.get(arrPos);
                                    }else pos = Interface.newRequest("Jogador 2: Peça do oponente, você apenas pode mover as suas peças.", 'M');
                        }else{
                            chessman = chessman2.get(arrPos);
                            pos = Interface.newRequest("Jogador 2: Mover a peça para onde? [LetraNúmero]",'M');
                                if(Interface.verify(pos)){fx = pos; break;}
                            if(pos.charAt(0) != 'M') pos = "";
                                chessmanMove = Game.canMove(board, chessman2, chessman1, arrPos, pos);
                            if(chessmanMove.charAt(0) == 'W'){System.out.println("Parabéns jogador 2, você ganhou!"); Order.move(board, chessman, pos, compatibility); fx = "Q"; break;}
                            if(chessmanMove.charAt(0) == 'T'){
                            if(chessmanMove.charAt(1) == 'K')
                                chessman1.set(Order.findPiece(chessman1, pos), chessman2.get(16));

                                Order.move(board, chessman, pos, compatibility);
                                pos = "";
                                turn = true;
                            }else System.out.println(chessmanMove.substring(2));
                        }
                    }//End player 2 turn
                } //End multiplayer game
            
                //Singleplayer Set
            if(fx.equals("")==false) //Shield String Text
            while(fx.charAt(0) == 'S'){
                if(start){Game.newGame(board, chessman1, chessman2, compatibility); start = false;}
                //Player 1 turn
                while(turn == true){
                    if(pos.equals("")){pos = Interface.newRequest("Jogador 1: Qual a posição da peça que será movida? [LetraNúmero]",'M');
                        if(Interface.verify(pos)){fx = pos; break;}
                            if(Order.isEmpty(board, pos)){System.out.println("Sem peças na posição indicada."); pos="";
                                }else if(Order.isTeam(chessman1, pos)){
                                    arrPos = Order.findPiece(chessman1, pos);
                                    chessman = chessman1.get(arrPos);
                                }else pos = Interface.newRequest("Jogador 1: Peça do oponente, você apenas pode mover as suas peças.", 'M');
                    }else{
                        chessman = chessman1.get(arrPos);
                        pos = Interface.newRequest("Jogador 1: Mover a peça para onde? [LetraNúmero]",'M');
                            if(Interface.verify(pos)){fx = pos; break;}
                        if(pos.charAt(0) != 'M') pos = "";
                            chessmanMove = Game.canMove(board, chessman1, chessman2, arrPos, pos);
                        if(chessmanMove.charAt(0) == 'W'){System.out.println("Parabéns jogador 1, você ganhou!"); Order.move(board, chessman, pos, compatibility); fx = "Q"; break;}
                        if(chessmanMove.charAt(0) == 'T'){
                        if(chessmanMove.charAt(1) == 'K')
                            chessman2.set(Order.findPiece(chessman2, pos), chessman2.get(16));

                            Order.move(board, chessman, pos, compatibility);
                            pos = "";
                            turn = false;
                        }else System.out.println(chessmanMove.substring(2));
                    }
                }//End player 1 turn

                    //Machine turn
                while(turn == false){
                    arrPos = random.nextInt(chessman2.size()-1);
                    chessman = chessman2.get(arrPos);
                        for(int i = 0; i < 10; i++){
                            coordinateX = 49;
                            coordinateY = 65;

                            coordinateX += random.nextInt(7);
                            coordinateY += random.nextInt(7);
                            pos = "M"+coordinateY+coordinateX;
                                chessmanMove = Game.canMove(board, chessman2, chessman1, arrPos, pos);
                            if(chessmanMove.charAt(0) == 'W'){System.out.println("Infelizmente você perdeu dessa vez."); Order.move(board, chessman, pos, compatibility); fx = "Q"; break;}
                            if(chessmanMove.charAt(0) == 'T'){
                            if(chessmanMove.charAt(1) == 'K'){
                                chessman1.set(Order.findPiece(chessman1, pos), chessman1.get(16));}
                            
                            Order.move(board, chessman, pos, compatibility);
                            pos = "";
                            turn = true;
                            break;
                        }
                    }
                }//End machine turn
            }//End multiplayer game

                //Save set
            if(fx.equals("")==false) //Shield String Text
            while(fx.charAt(0) == 'R'){
                    slot = Interface.inSlot("Salvar o jogo em qual slot? [Número do slot(de 0 a 7)/Voltar/Sair]");
                    slot = "Slot." + slot;
                    if(slot.equals("Slot.B")){fx = fs; pos = ""; break;}
                    if(slot.equals("Slot.Q")){fx = "Q"; break;}
                System.out.println("Tem certeza que quer salvar no "+slot+"? [S/N]");
                    answare = in.nextLine();
                    answare = answare.toUpperCase();
        
                if(answare.equals("SIM") || answare.equals("S")){
                    boolean saved = FileManager.save(chessman1, chessman2, fs, turn, slot);
                    if(saved){
                        System.out.println("\n\nJogo salvo com sucesso.");
                        System.exit(0);
                    }
                }
            }

                //Loard set
            if(fx.equals("")==false) //Shield String Text
            while(fx.charAt(0) == 'L'){
                slot = Interface.inSlot("Retomar jogo de qual slot? [Número do slot(de 0 a 7)/Voltar/Sair]");
                slot = "Slot." + slot;
                if(slot.equals("Slot.B")){fx = ""; start = false; pos = ""; break;}
                if(slot.equals("Slot.Q")){fx = "Q"; break;}
                fs = Game.loadGame(board, chessman1, chessman2, slot, compatibility);
                    if(fs.charAt(1) == 'T') turn = true;
                        else turn = false;

                start = false;
                pos = "";
                fx = fs;
                System.out.println("\n\nJogo carregado com sucesso.");
            }
			
		//Pause set
            if(fx.equals("")==false) //Shield String Text
            while(fx.charAt(0) == 'P'){
                System.out.println("Jogo pausado.\n 1 - Continuar");
		answare = in.nextLine();
		answare = answare.toUpperCase();
		if(answare.equals("1") || answare.equals("CONTINUAR")){
		    System.out.println(board.refresh(compatibility));
		    pos = ""; fx = fs; break;
		}
            }

        }while(fx.equals("") || fx.charAt(0) != 'Q');
    }
}