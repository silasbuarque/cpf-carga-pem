package br.com.silasbuarque.service;

import com.opencsv.CSVWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GenerateExcel {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void export(List<String> cpfs) {

        System.out.println("Iniciando a geração da planilha...");

        String[] headers = {
                "id", "cpf", "flag_fraude_hist", "flag_pap", "flag_cupom",
                "pre_rating_sim_pem", "qtd_dias_atraso_contrato_sim", "flag_negado_ult_30_dias_sim",
                "flag_possui_ctr_sim", "flag_func", "flag_monoprodutista", "flag_correntista",
                "tipo_vinculo_cc", "publico", "cc_vlr_investimento", "cc_segmentacao_primaria",
                "cc_segmentacao_secundaria", "renda_balde", "nivel_confianca", "flag_possui_auto_quitado",
                "flag_possui_moto_quitado", "cadus_qtde_restr_ativ_cli", "cadus_vlr_total_restr_ativ_cli",
                "saldo_interno_cartao", "saldo_interno_cheque", "saldo_interno_cp", "saldo_interno_reneg",
                "pem_01", "pem_02", "pem_03", "pem_04", "pem_05", "pem_06", "pem_07", "pem_08", "pem_09",
                "pem_10", "pem_11", "pem_12", "pem_13", "pem_14", "pem_15", "pem_16", "pem_17", "pem_18",
                "pem_19", "pem_20", "pem_21"};

        List<String[]> data = new ArrayList<>();

        data.add(headers);

        for (String cpf : cpfs) {
            data.add(new String[]{cpf, cpf, "2", "0", "0", "619|202304", "0", "0", "0", "3", "2", "2", "0", "0", "0", "0", "0", "12411.00",
                    "0", "1", "0", "0", "0", "0", "0", "0", "0", "823", "|CLA010|2023-06-19|CLA028", "0", "0", "0",
                    "0", "0", " " + LocalDate.parse("2024-04-01").format(FORMATTER) + " ", "7", "0", "0", "587", "0", LocalDate.now().format(FORMATTER) + "|CLA108", "0", "0", "0", "0",
                    "F1VAN", "8", "2"});
        }

        JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o local para salvar a planilha");
        fileChooser.setSelectedFile(new File("planilha_cpfs.csv"));

        dialog.add(fileChooser);

        int userSelection = fileChooser.showSaveDialog(dialog);

        try {
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();

                FileWriter fw = new FileWriter(fileToSave.getAbsolutePath());
                CSVWriter cs = new CSVWriter(fw, ';');
                cs.writeAll(data);

                cs.flush();
                fw.close();
                System.out.println("Planilha gerada com sucesso!");
            } else {
                System.out.println("Geração de planilha cancelada.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar a planilha.");
        }

    }

}
