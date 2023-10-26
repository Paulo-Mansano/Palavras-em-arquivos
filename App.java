//Paulo Eduardo Carvalho Mansano e Roberto Braga.

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        int opcao = 100;
        Scanner input = new Scanner(System.in);
        String[] files = {
            "texto01.txt", "texto02.txt", "texto03.txt",
            "texto04.txt", "texto05.txt", "texto06.txt", "texto07.txt",
            "texto08.txt", "texto09.txt", "texto10.txt"
        };

        TextAnalysis dicionario = new TextAnalysis(files);

        do {
            System.out.println("------Selecione a opção desejada------");
            System.out.println("1 - Descobrir em quais arquivos uma palavra aparece.");
            System.out.println("2 - Descobrir quais textos possuem certas palavras.");
            System.out.println("3 - Mostrar quais e com que frequencia as palavras aparecem nesse arquivo.");
            System.out.println("4 - Detectar palavras comuns em 2 arquivos.");
            System.out.println("0 - Sair");
            opcao = input.nextInt();
            input.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Digite a palavra a ser procurada.");
                    String palavraAProcurar = input.nextLine();
                    List<String> arquivosComPalavra = dicionario.arquivosComPalavra(palavraAProcurar);

                    if (arquivosComPalavra.isEmpty()) {
                        System.out.println("A palavra '" + palavraAProcurar + "' não foi encontrada em nenhum arquivo.");
                    } else {
                        System.out.println("A palavra '" + palavraAProcurar + "' foi encontrada nos seguintes arquivos:");
                        for (String arquivo : arquivosComPalavra) {
                            System.out.println(arquivo);
                        }
                    }
                    break;

                case 2:
                    int noperacao;    
                System.out.println("Quantas palavras serão procuradas?");
                noperacao = input.nextInt();

                if(noperacao < 2){
                    System.out.println("Este código precisa de mais de uma palavra!");
                } else if(noperacao == 2){
                   
                   System.out.println("Digite as duas palavras: ");
                   String palavra1 = input.nextLine();
                    input.nextLine(); 
                   String palavra2 = input.nextLine();
                    input.nextLine();                 
                   List<String> arquivosComDuasPalavras = dicionario.arquivosComDuasPalavras(palavra1, palavra2);

        System.out.println("Arquivos que contêm as palavras '" + palavra1 + "' e '" + palavra2 + "':");
        for (String arquivo : arquivosComDuasPalavras) {
            System.out.println(arquivo);
        } 

                } else if(noperacao > 2){
                     System.out.println("Digite as duas palavras: (Dê um enter vazio antes de tudo para limpar o buffer.)");
                   String palavra1 = input.nextLine();
                    input.nextLine(); 
                   String palavra2 = input.nextLine(); 
                    input.nextLine(); 
                   String palavra3 = input.nextLine();
                    input.nextLine(); 
                   String palavra4 = input.nextLine();
                    input.nextLine(); 

                   List<String> arquivosComTresOuMaisPalavras = dicionario.arquivosComTresOuMaisPalavras(palavra1, palavra2, palavra3, palavra4);

        System.out.println("Arquivos que contêm as palavras '" + palavra1 + "', '" + palavra2 + "', '" + palavra3 + "', e '" + palavra4 + "':");
        for (String arquivo : arquivosComTresOuMaisPalavras) {
            System.out.println(arquivo);
        }

                }
                    break;

                case 3:
                    System.out.println("Digite o nome do arquivo que você quer ler: ");
					dicionario.FrequenciaDasPalavras(input.nextLine());
                    break;

                case 4:
                    System.out.println("Digite o nome do primeiro arquivo.");
                    String palavra1 = input.nextLine();
                    System.out.println("Digite o nome do segundo arquivo.");
                    String palavra2 = input.nextLine();
                    dicionario.listarPalavrasComuns(palavra1, palavra2);
                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
            }
        } while (opcao != 0);
    }
}