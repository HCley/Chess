/**
 * Classe para salvar e recordar o jogo a partir de arquivo.txt
 *
 * Autor: Cleyson B. de Olideira
 * Versão/data: v06 d16-11-18
 */
import java.io.File;
import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class FileManager{
    /**
     * Método salva o jogo em [Slot.(slot)].txt
     * Parâmetros:
     * ArraList<Piece> chessman1 (Salva todo o ArrayList com as peças do time 1)
     * ArraList<Piece> chessman2 (Salva todo o ArrayList com as peças do time 2)
     * String fs (Salva o modo de jogo)
     * boolean turn (Salva o turno)
     * String file (Procura o Slot a salvar) 
     * retorna true se for o jogo foi salvo em [Slot.(slot)].txt
     */
    public static boolean save(ArrayList<Piece> chessman1, ArrayList<Piece> chessman2, String fs, boolean turn, String file)throws FileNotFoundException {
        PrintWriter dataSaveChessman = new PrintWriter(file);
        dataSaveChessman.println(fs);
        dataSaveChessman.println(turn);
        boolean arrL = true;
    
        for(int i = 0; i < chessman1.size(); i++){
            if(arrL) dataSaveChessman.println(chessman1.get(i).toSave());
                else dataSaveChessman.println(chessman2.get(i).toSave());
            if(i == chessman1.size()-1){
                if(arrL != false){i = -1; arrL = false;}
                  else i++;
            }
        }
        dataSaveChessman.close();
        return true;
    }
   
    /**
     * Método recorda o jogo em [Slot.(slot)].txt
     * Parâmetros:
     * ArraList<Piece> chessman1 (Recorda todo o ArrayList com as peças do time 1)
     * ArraList<Piece> chessman2 (Recorda todo o ArrayList com as peças do time 2)
     * String file (Procura o Slot a recordar) 
     * retorna String charAt(0) == 'M' || 'S' (Modo de jogo)
     * retorna String charAt(1) == 'T' || 'F' (Turno respectivo 'T' = Player 1 && 'F' = player 2)
     */
    public static String load(ArrayList<Piece> chessman1, ArrayList<Piece> chessman2, String file)throws FileNotFoundException {
        File origenLoadChessman = new File(file);
        Scanner dataLoadChessman = new Scanner(origenLoadChessman);
        boolean arrL = true;
        Piece chessman;
        String lineGet = "";
        String fxLine = "";
        String type = "";
        char alive = 0;
        int team = 0;
        int posX = 0;
        int posY = 0;

        if(dataLoadChessman.hasNextLine())
            fxLine = dataLoadChessman.nextLine();
            else return "LOAD FAIL.";
        if(dataLoadChessman.hasNextLine())
            lineGet = dataLoadChessman.nextLine();
            else return "LOAD FAIL.";
        if(lineGet.equals("true")) fxLine += "T";
            else fxLine += "F";

        for(int i = 0; i < 17; i++){
            if(dataLoadChessman.hasNextLine()){
                lineGet = dataLoadChessman.nextLine();
                posX = Character.getNumericValue(lineGet.charAt(0));
                posY = Character.getNumericValue(lineGet.charAt(3));
                team = Character.getNumericValue(lineGet.charAt(6));
                alive = lineGet.charAt(9);
                type = lineGet.substring(12);
            
                if(arrL){
                    chessman1.add(new Piece(posX, posY, team, type));
                    if(alive != '1')chessman1.get(i).setDefeat(true);
                }else{
                chessman2.add(new Piece(posX, posY, team, type));
                if(alive != '1')chessman2.get(i).setDefeat(true);
                }
                if(i == 16){
                if(arrL != false){i = -1; arrL = false;}
                    else i++;
                }
            }else return "LOAD FAIL.";
        }
        dataLoadChessman.close();
        return fxLine;
    }
}