public class Unha extends Servico {
    private boolean manicure;
    private boolean pedicure;
    private String corEsmalte;
    private String aditivos;
    private String tipoDeAlongamento;

    public Unha(boolean manicure, boolean pedicure, String corEsmalte, String aditivos, String tipoDeAlongamento) {

        super(calculaValorObj(manicure, pedicure), CodigoServico.UNHA);
        this.manicure = manicure;
        this.pedicure = pedicure;
        this.corEsmalte = corEsmalte;
        this.aditivos = aditivos;
        this.tipoDeAlongamento = tipoDeAlongamento;
    }

    public static double calculaValorObj(boolean manicure, boolean pedicure) {
        if (manicure && pedicure) return 40.0;
        if (manicure || pedicure) return 25.0;
        return 0.0;
    }

    public boolean ehManicure() {
        return manicure;
    }

    public void setManicure(boolean manicure) {
        this.manicure = manicure;
    }

    public boolean ehPedicure() {
        return pedicure;
    }

    public void setPedicure(boolean pedicure) {
        this.pedicure = pedicure;
    }

    public String getCorEsmalte() {
        return corEsmalte;
    }

    public void setCorEsmalte(String corEsmalte) {
        this.corEsmalte = corEsmalte;
    }

    public String getAditivos() {
        return aditivos;
    }

    public void setAditivos(String aditivos) {
        this.aditivos = aditivos;
    }

    public String getTipoDeAlongamento() {
        return tipoDeAlongamento;
    }

    public void setTipoDeAlongamento(String tipoDeAlongamento) {
        this.tipoDeAlongamento = tipoDeAlongamento;
    }
}
