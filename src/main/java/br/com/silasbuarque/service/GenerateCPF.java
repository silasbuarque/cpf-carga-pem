package br.com.silasbuarque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateCPF {

    public String generate() {
        int[] digits = new int[11];
        Random random = new Random();

        digits[0] = random.nextInt(9) + 1;

        for (int i = 1; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        digits[9] = calculateVerifierDigit(digits, 10);

        digits[10] = calculateVerifierDigit(digits, 11);

        StringBuilder cpf = new StringBuilder();

        for (int i = 0; i < 11; i++) {
            cpf.append("");
            cpf.append(digits[i]);
        }

        return cpf.toString();

    }

    public List<String> getCpfsList(int quantidadeCPF) {
        List<String> cpfs = new ArrayList<>();

        System.out.println("Gerando CPF's...");

        for (int i = 0; i < quantidadeCPF; i++) {
            cpfs.add(generate());
        }

        System.out.println("CPF's gerados com sucesso!");

        return cpfs;
    }

    private int calculateVerifierDigit(int[] digits, int length) {
        int sum = 0;
        for (int i = 0; i < length - 1; i++) {
            sum += digits[i] * (length - i);
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

}
