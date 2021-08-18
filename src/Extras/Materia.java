/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extras;

/**
 *
 * @author Matheus Markies
 */
public class Materia {

    public String Prova;
    public String Conteudo;
    public String Nome;
    public int Acertos;
    public int Erros;
    
    public String toString(){
        String a = "Prova: "+Prova+" | Conteudo: "+Conteudo+" | Nome: "+Nome+" | Acertos: "+Acertos+" | Erros: "+Erros;
        return a;
    }
    
}

