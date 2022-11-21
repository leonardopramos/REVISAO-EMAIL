public class EmailControlado extends Email{

    private boolean lido;

    public EmailControlado(String r, String d, String a, String c, boolean lido) {
        super(r, d, a, c);
        this.lido = lido;
    }
    public boolean isLido() {
        return lido;
    }
    public void setLido(boolean lido) {
        this.lido = lido;
    }
    
    
}
