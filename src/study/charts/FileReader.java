/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package study.charts;

import Extras.Materia;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.omg.CORBA.SystemException;
import static study.charts.StudyCharts.path;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Matheus Markies
 */
public class FileReader {

    public String fileFolder = "";

    public FileReader() {
        fileFolder = study.charts.StudyCharts.getPath();
        System.out.println(fileFolder);
    }

    public ArrayList<String> getProvas() {
        ArrayList<String> provas = new ArrayList<>();

        File file = new File(fileFolder);

        ArrayList<String> lines = null;

        try {
            lines = getExcelLines(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Integer> indexs = getIndex('*', lines);

        for (int i : indexs) {
            provas.add(removeChar(lines.get(i), '*'));
        }

        return provas;
    }
    
    public ArrayList<String> getProvasByMaterias(ArrayList<Materia> materias){
     ArrayList<String> provas = new ArrayList<>();
     
     for(int i =0;i<materias.size();i++){
         if(provas.size() == 0){
             provas.add(materias.get(i).Prova);
         }else{
             int o = 0;
             for(String r : provas){
                 if(materias.get(i).Prova == r)
                     o++;
             }
             if(o == 0)
                 provas.add(materias.get(i).Prova);
         }
     }
     
     return provas;
    }

    ArrayList<Materia> getMaterias() {
        ArrayList<Materia> materias = new ArrayList<Materia>();

        File file = new File(fileFolder);

        ArrayList<String> lines = null;

        try {
            lines = getExcelLines(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArrayList<Integer> indexs = getIndex('*', lines);

        int index = 0;
        String prova = "";
        if (indexs.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {

                if (i >= indexs.get(index)) {

                    prova = removeChar(lines.get(indexs.get(index)), '*');
                    if ((index + 1) < indexs.size()) {
                        index++;
                    }
                } else {
                    Materia materia = new Materia();
                    ArrayList<Integer> in = findNumbers(lines.get(i));

                    materia.Prova = prova;

                    materia.Nome = formatString(lines.get(i), in.get(0));
                    try {
                        materia.Conteudo = formatString(lines.get(i), in.get(0)).split(":")[0];
                    } catch (Exception e) {
                        materia.Conteudo = formatString(lines.get(i), in.get(0));
                    }
                    materia.Acertos = getNumbers(lines.get(i)).get(0);
                    try {
                        materia.Erros = getNumbers(lines.get(i)).get(1) - getNumbers(lines.get(i)).get(0);
                    } catch (Exception e) {
                        materia.Erros = 0;
                    }

                    materias.add(materia);
                }

            }
        } else {
            prova = "Geral";
            for (int i = 0; i < lines.size(); i++) {
                Materia materia = new Materia();
                ArrayList<Integer> in = findNumbers(lines.get(i));
                //System.out.println(lines.get(i));
                materia.Prova = prova;
                if (in.size() > 0) {
                    materia.Nome = formatString(lines.get(i), in.get(0));
                    try {
                        materia.Conteudo = formatString(lines.get(i), in.get(0)).split(":")[0];
                    } catch (Exception e) {
                        materia.Conteudo = formatString(lines.get(i), in.get(0));
                    }
                    materia.Acertos = getNumbers(lines.get(i)).get(0);
                    try {
                        //materia.Erros = getNumbers(lines.get(i)).get(1) - getNumbers(lines.get(i)).get(0);
                        materia.Erros = getNumbers(lines.get(i)).get(1);
                    } catch (Exception e) {
                        materia.Erros = 0;
                    }

                    materias.add(materia);
                }
            }
//            for(Materia i : materias)
//                System.out.println(i.toString());
        }

        return materias;
    }

    public static ArrayList<String> readFile(File file) throws FileNotFoundException {

        ArrayList<String> content = new ArrayList<String>();

//        Scanner Reader = new Scanner(file);
//        while (Reader.hasNext()) {
//            String next = Reader.next();
//            content.add(next);
//        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }

    ArrayList<Integer> getIndex(char separator, ArrayList<String> text) {
        ArrayList<Integer> indexs = new ArrayList<Integer>();

        for (int i = 0; i < text.size(); i++) {

            for (int e = 0; e < text.get(i).length(); e++) {
                if (text.get(i).charAt(e) == separator) {
                    indexs.add(i);
                    break;
                }
            }

        }

        return indexs;
    }

    public ArrayList<Integer> findWord(String text, String word) {
        ArrayList<Integer> indexArray = new ArrayList<Integer>();
        boolean firstChar = false;
        int charPosition = 0;

        String textToLower = text.toLowerCase();
        String wordToLower = word.toLowerCase();

        for (int i = 0; i < text.length(); i++) {
            if (!firstChar) {
                if (textToLower.charAt(i) == wordToLower.charAt(charPosition)) {
                    firstChar = true;
                    charPosition += 1;
                }
            } else {
                if (textToLower.charAt(i) == wordToLower.charAt(charPosition)) {
                    charPosition += 1;
                    if (charPosition == word.length()) {
                        indexArray.add(i - word.length() + 1);
                        firstChar = false;
                        charPosition = 0;
                    }
                } else {
                    firstChar = false;
                    charPosition = 0;
                }
            }
        }
        return indexArray;
    }

    public ArrayList<Integer> findNumbers(String text) {
        ArrayList<Integer> indexs = new ArrayList<>();

        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < text.length(); i++) {

            for (char k : numbers) {
                if (text.charAt(i) == k) {
                    indexs.add(i);
                }
            }

        }

        return indexs;
    }

    public ArrayList<Integer> getNumbers(String text) {
        ArrayList<Integer> indexs = new ArrayList<>();

        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        for (int i = 0; i < text.length(); i++) {

            for (char k : numbers) {
                if (text.charAt(i) == k) {
                    indexs.add(Integer.parseInt("" + k));
                }
            }

        }

        return indexs;
    }

    public String formatString(String text, int maxIndex) {
        String newString = "";
        for (int i = 0; i < text.length(); i++) {
            if (i < maxIndex) {
                newString += text.charAt(i);
            }
        }
        return newString;
    }

    public String removeChar(String text, char removed) {
        String newString = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != removed) {
                newString += text.charAt(i);
            }
        }
        return newString;
    }

    public void setFileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
    }

    public String getFileFolder() {
        return fileFolder;
    }

    public String ReadCellData(int vRow, int vColumn) {
        String value = null;
        Workbook wb = null;
        try {

            FileInputStream fis = new FileInputStream(fileFolder);

            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(vRow);
        Cell cell = row.getCell(vColumn);
        value = cell.getStringCellValue();
        return value;
    }

    public ArrayList<String> getExcelLines(File file) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();

        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> itr = sheet.iterator();

        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            String lineContent = "";
            int stringNumber = 0;

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (stringNumber == 0) {
                            lineContent += cell.getStringCellValue() + ": ";
                        } else {
                            lineContent += cell.getStringCellValue() + " ";
                        }
                        stringNumber++;
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        lineContent += cell.getNumericCellValue() + " ";
                        break;
                    default:
                }
            }
            lines.add(lineContent);
        }
        return lines;
    }

}
