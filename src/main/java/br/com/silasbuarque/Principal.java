package br.com.silasbuarque;

import br.com.silasbuarque.service.GenerateCPF;
import br.com.silasbuarque.service.GenerateExcel;

import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GenerateCPF gerador = new GenerateCPF();
        GenerateExcel exportarExcel = new GenerateExcel();

        System.out.print("Quantos CPF's deseja gerar? ");
        int quantidadeCPF = sc.nextInt();


        List<String> cpfs = gerador.getCpfsList(quantidadeCPF);

        exportarExcel.export(cpfs);
        sc.close();

    }

}