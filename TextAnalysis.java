import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAnalysis {
    private String[] files; // Campos para armazenar os nomes dos arquivos

    public TextAnalysis(String[] files) {
        this.files = files;
        for (String fname : files) {
            carregaDados(fname);
        }
    }

    public void listarArquivos(String palavra) {
        for (String fileName : files) {
            if (contemPalavraChave(fileName, palavra)) {
                System.out.println("Arquivo contém a palavra-chave '" + palavra + "': " + fileName);
            }
        }
    }

    public void listarArquivos(String[] palavras) {
        for (String palavra : palavras) {
            listarArquivos(palavra);
        }
    }

    public void listarPalavras(String fileName) {
		// Crie um conjunto para armazenar as palavras únicas do arquivo
		Set<String> palavrasUnicas = new HashSet<>();
	
		// Leitura do arquivo
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.forName("utf8"))) {
			String line;
	
			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçãõà-]", " ");
				String[] palavras = line.split("\\s+");
	
				// Adicione as palavras ao conjunto
				palavrasUnicas.addAll(Arrays.asList(palavras));
			}
	
			// Exiba as palavras únicas no arquivo
			System.out.println("Palavras únicas no arquivo '" + fileName + "':");
			for (String palavra : palavrasUnicas) {
				System.out.println(palavra);
			}
		} catch (IOException e) {
			System.out.println("Erro na leitura: " + e.getMessage());
		}
	}
	

    public void listarPalavrasComuns(String f1, String f2) {
    Set<String> palavrasArquivo1 = obterPalavrasArquivo(f1);
    Set<String> palavrasArquivo2 = obterPalavrasArquivo(f2);

    // Encontre e exiba as palavras em comum entre os dois arquivos
    palavrasArquivo1.retainAll(palavrasArquivo2);

    System.out.println("Palavras comuns entre '" + f1 + "' e '" + f2 + "':");
    for (String palavra : palavrasArquivo1) {
        System.out.println(palavra);
    }
}

private Set<String> obterPalavrasArquivo(String fileName) {
    Set<String> palavrasUnicas = new HashSet<>();

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.forName("utf8"))) {
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçãõà-]", " ");
            String[] palavras = line.split("\\s+");
            palavrasUnicas.addAll(Arrays.asList(palavras));
        }
    } catch (IOException e) {
        System.out.println("Erro na leitura: " + e.getMessage());
    }

    return palavrasUnicas;
}


    public boolean contemPalavraChave(String fileName, String palavra) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String linha = scanner.nextLine().toLowerCase();
                    if (linha.contains(palavra.toLowerCase())) {
                        System.out.println("A palavra "+ palavra + " existe no arquivo " + fileName);
                        return true;
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro na leitura do arquivo: " + e.getMessage());
            }
        }
        return false;
    }

    private void carregaDados(String fileName) {
        Path path1 = Paths.get(fileName);
        System.out.println("\nArquivo: " + fileName);

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.forName("utf8"))) {
            String line;
            int totalPalavras = 0; // Variável para contar o número total de palavras no arquivo

            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúçâãõà]", " ");

                // Dividir a linha em palavras
                String[] palavras = line.split("\\s+");

                // Contar as palavras nesta linha
                totalPalavras += palavras.length;
            }

            System.out.println("Total de palavras no arquivo: " + totalPalavras);
        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
    }

    public List<String> arquivosComPalavra(String palavra) {
        List<String> arquivosComPalavra = new ArrayList<>();

        for (String fileName : files) {
            if (contemPalavraChave(fileName, palavra)) {
                arquivosComPalavra.add(fileName);
            }
        }

        return arquivosComPalavra;
    }
    public void FrequenciaDasPalavras(String fileName) {  //Esse eu não entendi como se faz, tive que pegar da internet e adaptar pro trabalho.
        Map<String, Integer> frequenciaPalavras = new HashMap<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.forName("utf8"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase().replaceAll("[^a-zA-Záéíóúâçãõà-]", " ");
                String[] palavras = line.split("\\s+");

                for (String palavra : palavras) {
                    if (!palavra.isEmpty()) {
                        frequenciaPalavras.put(palavra, frequenciaPalavras.getOrDefault(palavra, 0) + 1);
                    }
                }
            }

            System.out.println("Frequência de palavras no arquivo '" + fileName + "':");
            for (Map.Entry<String, Integer> entry : frequenciaPalavras.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " vezes");
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura: " + e.getMessage());
        }
    }

    
    public List<String> arquivosComDuasPalavras(String palavra1, String palavra2) {
        List<String> arquivosComPalavras = new ArrayList<>();
    
        for (String fileName : files) {
            if (contemPalavraChave(fileName, palavra1) && contemPalavraChave(fileName, palavra2)) {
                arquivosComPalavras.add(fileName);
            }
        }
    
        return arquivosComPalavras;
    }

    public List<String> arquivosComTresOuMaisPalavras(String... palavras) {
        List<String> arquivosComPalavras = new ArrayList<>();
    
        for (String fileName : files) {
            boolean todasAsPalavrasEncontradas = true;
            for (String palavra : palavras) {
                if (!contemPalavraChave(fileName, palavra)) {
                    todasAsPalavrasEncontradas = false;
                    break;
                }
            }
            if (todasAsPalavrasEncontradas) {
                arquivosComPalavras.add(fileName);
            }
        }
    
        return arquivosComPalavras;
    }
    

}
