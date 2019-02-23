/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author musta
 */
public class testClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease Enter the Expression in inflix form:\t");
        String infixExp = input.nextLine();
        Evaluation eva = new Evaluation();
        String postFix = eva.inToPost(infixExp);
        System.out.print("\n"+postFix+"\n");
        System.out.print("\nResult =\t"+eva.posEval(postFix)+"\n");
    }
}
