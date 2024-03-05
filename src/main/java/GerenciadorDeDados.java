import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorDeDados {

    private static final String ARQUIVO_CLIENTES = "clientes.dat";
    private static final String ARQUIVO_COMANDAS = "comandas.dat";

    public HashMap<String, Cliente> recuperarClientes() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_CLIENTES));
            return (HashMap<String, Cliente>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar clientes!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_CLIENTES);
        } finally {
            if (in != null) {
                in.close();
            }
        }

    }

    public HashMap<Integer, Comanda> recuperarComandas() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(ARQUIVO_COMANDAS));
            return (HashMap<Integer, Comanda>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Não foi possível recuperar comandas!");
            throw new IOException("Não foi possível recuperar os dados do arquivo " + ARQUIVO_COMANDAS);
        }finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public void salvarClientes(Map<String, Cliente> clientes) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_CLIENTES));
            out.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar contatos no arquivo " + ARQUIVO_CLIENTES);
        }
    }

    public void salvarComandas(Map<Integer, Comanda> comandas) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_COMANDAS));
            out.writeObject(comandas);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Erro ao salvar contatos no arquivo " + ARQUIVO_COMANDAS);
        }
    }
}
