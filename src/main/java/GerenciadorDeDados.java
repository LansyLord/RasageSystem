import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeDados {

    private String nomeArquivo;

    public GerenciadorDeDados(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void gravarDados(List<String> texto) throws IOException {
        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new FileWriter(this.nomeArquivo));
            for (String s : texto) {
                escritor.write(s + "\n");
            }
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }

    public List<String> lerDados() throws IOException {
        List<String> lista = new ArrayList<>();
        BufferedReader leitor = null;
        try {
            leitor = new BufferedReader(new FileReader(this.nomeArquivo));
            String linhaLida = leitor.readLine();
            while (linhaLida != null) {
                lista.add(linhaLida);
                linhaLida = leitor.readLine();
            }
            return lista;
        } finally {
            if(leitor != null){
                leitor.close();
            }
        }
    }
}
