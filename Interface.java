/**
 * Classe de manipulação -Interface usuário-
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v03 d13-11-18
 */
import java.util.Scanner;
public class Interface{
    /**
     * Método de chamada do menu inicial
     */
    public static String menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("\n\n----------------Menu----------------");
        System.out.println("1 - Continuar jogo salvo");
        System.out.println("2 - Novo Jogo");
        System.out.println("3 - Opções");
        System.out.println("4 - Sair");
        System.out.println("Escolhar a opção: (Recomendável setar configurações primeiro)");   
        
            String fct = in.nextLine();
            fct = fct.toUpperCase();

        if(fct.equals("4") || fct.equals("SAIR")) return"Q";
        if(fct.equals("1") || fct.equals("CONTINUAR JOGO SALVO")) return"L";
        if(fct.equals("2") || fct.equals("NOVO JOGO") || fct.equals("JOGAR")){
            do{
                 System.out.println("\n\n-----------Modo de jogo-----------");
                System.out.println("1 - Jogar sozinho");
                System.out.println("2 - Jogar acompanhado");
                System.out.println("3 - Voltar");
                System.out.println("4 - Sair");             
                System.out.println("Escolhar a opção: ");   
    
                    fct = in.nextLine();
                    fct = fct.toUpperCase();
            
                if(fct.equals("1") || fct.equals("SOZINHO") || fct.equals("JOGAR SOZINHO")) return"S";
                if(fct.equals("2") || fct.equals("ACOMPANHADO") || fct.equals("JOGAR ACOMPANHADO")) return"M";
                if(fct.equals("3") || fct.equals("VOLTAR")) return"B";
                if(fct.equals("4") || fct.equals("SAIR")) return"Q";
            }while(true);
        }
        if(fct.equals("3") || fct.equals("OPÇÕES") || fct.equals("OPÇOES") ){
            System.out.println("\n\n--------------Opções--------------");
            System.out.println("Para uma melhor experiência jogue em linux terminal");
            System.out.println("1 - Compatibilidade com BlueJ");
            System.out.println("2 - Compatibilidade com Windows");
            System.out.println("3 - Compatibilidade com Linux (Default)");
            System.out.println("\n4 - Voltar");
            System.out.println("5 - Sair");
                fct = in.nextLine();
                fct = fct.toUpperCase();
            do{
                if(fct.equals("1") || fct.equals("BLUEJ")) return"C1";
                if(fct.equals("2") || fct.equals("WINDOWS")) return"C2";
                if(fct.equals("3") || fct.equals("LINUX")) return"C0";
                if(fct.equals("4") || fct.equals("VOLTAR")) return"B";
                if(fct.equals("5") || fct.equals("SAIR")) return"Q";
                }while(true);
        }
        return "Statement not found";
    }

    /**
     * Método que faz pedido de posição ao usuário
     * retorna a posição em no formato FunçãoLetraNúmero (Function,y,x)
     */
    public static String newRequest(String msg, char returnMsg){
        Scanner in = new Scanner(System.in);
    
        System.out.println(msg);
        String boardPos = in.nextLine();
        boardPos = boardPos.toUpperCase();
        if(boardPos.equals("SAIR")) return "Q";
        if(boardPos.equals("VOLTAR")) return "B";
        if(boardPos.equals("SALVAR")) return "R";
        if(boardPos.equals("PAUSAR")) return "P";
        
        while(Game.isBoard(boardPos)==false){
            System.out.println("Posição não representada no tabuleiro. Fx: "+boardPos);
            boardPos = in.nextLine();
            boardPos = boardPos.toUpperCase();
            if(boardPos.equals("SAIR")) return "Q";
        }
        if(Game.isBoard(boardPos)) return returnMsg + boardPos;
        return "Statement not found.";
    }

    /**
     * Método que analisa resposta de interface
     * retorna true se e somente se a interface mudar de função.
     */
    public static boolean verify(String pos){
        if(pos.charAt(0) == 'Q' || pos.charAt(0) == 'S' || pos.charAt(0) == 'B' || pos.charAt(0) == 'R'|| pos.charAt(0) == 'P') return true;
        return false;
    }

    /**
     * Método que faz pedido de posição ao usuário
     * retorna a posição em no formato FunçãoLetraNúmero (Function,y,x)
     */
    public static boolean verifyPiece(String i){
        if(i.charAt(0) == '1' || i.charAt(0) == '2' || i.charAt(0) == '3') return true;
        if(i.equals("TORRE") || i.equals("CAVALO") || i.equals("BISPO")) return true;
        return false;
    }

    /**
     * Método que faz pedido de Slot [Save/Load] ao usuário
     * Parâmetros:
     * String TXT (Texto a ser impresso com a solicitação descrita)
     * Retorna String com o valor dentro do range de slot.
     */
    public static String inSlot(String txt){
        Scanner in = new Scanner(System.in);
        
        String valueIn = "";
        int range = 0;

        do{
            System.out.println(txt); //Get valueIn
            valueIn = in.nextLine();
            
            valueIn = valueIn.toUpperCase(); //Define valueIn Upper case
            int length = valueIn.length();   //Get value length
            boolean noLetter = true;
            
            
            for(int i = 0; i < length; i++){
                if(Character.isLetter(valueIn.charAt(i)) == true) noLetter = false; break;
            }
            if(valueIn.isEmpty()==false){
                if(valueIn.equals("SAIR")) return "Q";
                    else if(valueIn.equals("VOLTAR")) return "B";
                        else if(noLetter == true){
                            range = Integer.parseInt(valueIn);
                                if(range >= 0  && range < 8) return valueIn;
                                    else System.out.println("Valor invalido.");
                        }
                
            }
        }while(true);
    }
}