public class Cabelo extends Servico {
    private boolean corte;
    private boolean escova;
    private boolean selagem;

    public Cabelo(boolean corte, boolean escova, boolean selagem) {
        super(calculaValorObj(corte, escova, selagem), CodigoServico.CABELO);
        this.corte = corte;
        this.escova = escova;
        this.selagem = selagem;
    }

    public static double calculaValorObj(boolean corte, boolean escova, boolean selagem){
        if (corte && selagem) return 150.0;
        if (corte && escova) return 90.0;
        if (corte) return 50.0;
        if (selagem) return 100.0;
        if (escova) return 40.0;
        return 0.0;
    }

    public boolean isCorte() {
        return corte;
    }

    public void setCorte(boolean corte) {
        this.corte = corte;
    }

    public boolean isEscova() {
        return escova;
    }

    public void setEscova(boolean escova) {
        this.escova = escova;
    }

    public boolean isSelagem() {
        return selagem;
    }

    public void setSelagem(boolean selagem) {
        this.selagem = selagem;
    }

    @Override
    public String toString(){
        if (corte && selagem) return "Corte e Selagem de cabelo";
        if (corte && escova) return "Corte e Escova de cabelo";
        if (corte) return "Corte de cabelo";
        if (selagem) return "Selagem de cabelo";
        if (escova) return "Escova de cabelo";
        return "";
    }
}
