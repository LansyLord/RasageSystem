import java.io.Serializable;
import java.util.Objects;

public class Cliente implements Serializable {
    private String nome;
    private String cpf;
    private String numCelular;

    public Cliente(String nome, String cpf, String numCelular) {
        this.nome = nome;
        this.cpf = cpf;
        this.numCelular = numCelular;
    }
    public Cliente(){
        this("", "", "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nome, cliente.nome) && Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cpf);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumCelular() {
        return numCelular;
    }

    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }

    @Override
    public String toString() {
        return this.nome + " CPF: " + this.cpf;
    }
}