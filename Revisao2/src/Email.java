import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Email {
    private static final Set<String> classificacoes = new HashSet<>(Arrays.asList("NORMAL","URGENTE","SPAN"));
    private String remetente;
    private String destinatario;
    private String assunto;
    private String classificacao;
    private double idadeDestinatario;

    public Email(String r, String d, String a, String c) {
        if(classificacoes.contains(c)){
            this.classificacao = c;
        } else {
            throw new IllegalArgumentException();
        }
        this.remetente = r;
        this.destinatario = d;
        this.assunto = a;
        
    }
    public double getIdadeDestinatario() {
        return idadeDestinatario;
    }

    public List<Email> emailsUrgentesEGerencia(List<Email> lst){
        List<Email> aux = lst.stream().filter(e -> e.getClassificacao().equals("urgente"))
                                    .filter(e -> e.getRemetente().equals("gerencia"))
                                    .collect(Collectors.toList());
        return aux;
    }
    public void emailMarcaSpam(List<Email> lst){
        lst.stream().filter(e -> e.getRemetente().equals("SOOFERTAS"))
                    .forEach(e -> e.setClassificacao("Spam"));
    }
    public long mensagensLidas(List<EmailControlado> lst){
        return lst.stream().filter(e -> e.isLido())
                            .count();
    }

    public double idadeMediaDestinatarios(List<Email> lst){
        return lst.stream().filter(e -> e.getClassificacao().equals("urgente"))
                            .mapToDouble(e -> e.getIdadeDestinatario())
                            .summaryStatistics().getAverage();
    }
    public Email retornaPrimeiroEmailDest(List<Email> lst, String destinatario){
        return lst.stream().filter(e -> e.getDestinatario().equals(destinatario))
                           .findFirst()
                           .orElse(null);
    }

    public void criaEstanciaEmailControlado(){
        try{
            EmailControlado ec = new EmailControlado("remetente", "destinatario", "assunto", classificacao, false);
        }catch(IllegalArgumentException e){
            System.out.println("Ilegal");
        }
        
    }

    public String getRemetente(){
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setClassificacao(String c) {
        classificacao = c;
    }
}