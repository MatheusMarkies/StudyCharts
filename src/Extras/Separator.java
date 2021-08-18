/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extras;

import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class Separator {

    public static ArrayList<Materia> getGeralMateria(ArrayList<Materia> materias) {
        ArrayList<Materia> materiasGeral = new ArrayList<>();
        for (int i = 0; i < materias.size(); i++) {
            if (materiasGeral.size() == 0) {
                Materia mat = materias.get(i);
                mat.Prova = "Geral";
                materiasGeral.add(mat);
            } else {
                int k = 0;
                for (int e = 0; e < materiasGeral.size(); e++) {
                    if (compareString(materiasGeral.get(e).Nome, materias.get(i).Nome)) {
                        k += 1;

                        materiasGeral.get(e).Prova = "Geral";
                        materiasGeral.get(e).Acertos += materias.get(i).Acertos;
                        materiasGeral.get(e).Erros += materias.get(i).Erros;
                    }
                }
                if (k == 0) {
                    Materia mat = materias.get(i);
                    mat.Prova = "Geral";
                    materiasGeral.add(mat);
                }
            }
        }
        return materiasGeral;
    }

    public static ArrayList<Materia> getGeralMateria(ArrayList<Materia> materias, String prova) {
        ArrayList<Materia> materiasGeral = new ArrayList<>();
        for (int i = 0; i < materias.size(); i++) {

            if (!compareString(materias.get(i).Prova, prova)) {

            } else if (materiasGeral.size() == 0) {
                Materia mat = materias.get(i);
                mat.Prova = prova;
                materiasGeral.add(mat);
            } else {
                int k = 0;
                for (int e = 0; e < materiasGeral.size(); e++) {
                    if (compareString(materiasGeral.get(e).Nome, materias.get(i).Nome)) {
                        k += 1;

                        materiasGeral.get(e).Prova = prova;
                        materiasGeral.get(e).Acertos += materias.get(i).Acertos;
                        materiasGeral.get(e).Erros += materias.get(i).Erros;
                    }
                }
                if (k == 0) {
                    Materia mat = materias.get(i);
                    mat.Prova = prova;
                    materiasGeral.add(mat);
                }
            }
        }
        return materiasGeral;
    }

    public static ArrayList<Materia> getConteudo(ArrayList<Materia> materias) {
        ArrayList<Materia> materiasGeral = new ArrayList<>();

        for (int i = 0; i < materias.size(); i++) {
            if (materiasGeral.size() == 0) {
                Materia mat = materias.get(i);
                mat.Prova = "Geral";
                materiasGeral.add(mat);
            } else {
                int k = 0;
                for (int e = 0; e < materiasGeral.size(); e++) {
                    if (compareString(materiasGeral.get(e).Conteudo, materias.get(i).Conteudo)) {
                        k += 1;

                        materiasGeral.get(e).Prova = "Geral";
                        materiasGeral.get(e).Acertos += materias.get(i).Acertos;
                        materiasGeral.get(e).Erros += materias.get(i).Erros;
                    }
                }
                if (k == 0) {
                    Materia mat = materias.get(i);
                    mat.Prova = "Geral";
                    materiasGeral.add(mat);
                }
            }
        }

        return materiasGeral;
    }

    public static ArrayList<Materia> getConteudo(ArrayList<Materia> materias, String prova) {
        ArrayList<Materia> materiasGeral = new ArrayList<>();

        for (int i = 0; i < materias.size(); i++) {

            if (!compareString(materias.get(i).Prova, prova)) {

            } else if (materiasGeral.size() == 0) {
                Materia mat = materias.get(i);
                mat.Prova = prova;
                materiasGeral.add(mat);
            } else {
                int k = 0;
                for (int e = 0; e < materiasGeral.size(); e++) {
                    if (compareString(materiasGeral.get(e).Conteudo, materias.get(i).Conteudo)) {
                        k += 1;

                        materiasGeral.get(e).Prova = prova;
                        materiasGeral.get(e).Acertos += materias.get(i).Acertos;
                        materiasGeral.get(e).Erros += materias.get(i).Erros;
                    }
                }
                if (k == 0) {
                    Materia mat = materias.get(i);
                    mat.Prova = prova;
                    materiasGeral.add(mat);
                }
            }
        }

        return materiasGeral;
    }

    public static boolean compareString(String a, String b) {
        if (removeSpace(a.toLowerCase()).equals(removeSpace(b.toLowerCase()))) {
            return true;
        } else {
            return false;
        }
    }

    public static String removeSpace(String a) {
        String newS = "";
        boolean copy = true;

        int h = 0;
        
        int index = 0;
        if (a.charAt(a.length()-1) == ' ') {
            index = a.length() - 1;
        } else {
            index = a.length();
        }

        if(a.charAt(0) == ' ')
            h = 1;
        
        for (int i = h;i < index; i++) {
            copy = true;
            if (a.charAt(i) == ' ') {
                if ((i + 1) < index) {
                    if (a.charAt(i + 1) == ' ') {
                        copy = false;
                    }
                }
            }
            if (copy) {
                newS += a.charAt(i);
            }
        }

        return newS;
    }
}
