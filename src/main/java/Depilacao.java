public class Depilacao extends Servico {
    private String tipoDepilacao;
    private String areaDoCorpo;
    public static final String CERA = "Cera";
    public static final String FOTO_DEPILACAO = "Foto Depilação";
    public static final String LAMINA = "Lâmina";
    public static final String BARBA = "Barba";
    public static final String AXILA = "Axila";
    public static final String PERNAS = "Perna";

    public Depilacao(String tipoDepilacao, String areaDoCorpo){
        super(calcularValorObj(areaDoCorpo, tipoDepilacao), CodigoServico.DEPILACAO);
        this.tipoDepilacao = tipoDepilacao;
        this.areaDoCorpo = areaDoCorpo;
    }

    public static double calcularValorObj(String areaDoCorpo, String tipoDepilacao){
        if(areaDoCorpo.equals(BARBA)) {
            switch (tipoDepilacao) {
                case CERA -> {
                    return 25.0;
                }
                case FOTO_DEPILACAO -> {
                    return 30.0;
                }
                case LAMINA -> {
                    return 15.0;
                }
            }
        }
        if(areaDoCorpo.equals(AXILA)){
            switch (tipoDepilacao) {
                case CERA -> {
                    return 20.0;
                }
                case FOTO_DEPILACAO -> {
                    return 30.0;
                }
                case LAMINA -> {
                    return 10.0;
                }
            }
        }
        if(areaDoCorpo.equals(PERNAS)) {
            switch (tipoDepilacao) {
                case CERA -> {
                    return 30.0;
                }
                case FOTO_DEPILACAO -> {
                    return 45.0;
                }
                case LAMINA -> {
                    return 20.0;
                }
            }
        }
        return 0.0;
    }

    public String getTipoDepilacao() {
        return tipoDepilacao;
    }

    public void setTipoDepilacao(String tipoDepilacao) {
        this.tipoDepilacao = tipoDepilacao;
    }

    public String getAreaDoCorpo() {
        return areaDoCorpo;
    }

    public void setAreaDoCorpo(String areaDoCorpo) {
        this.areaDoCorpo = areaDoCorpo;
    }
}
