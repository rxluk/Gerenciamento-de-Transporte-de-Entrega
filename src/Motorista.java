import java.util.HashSet;

public class Motorista {
    private String nome;
    private static int proxId = 1;
    private final int id;
    private String dtaNasc;
    private String cpf;
    private Ajudante ajudante;
    private final HashSet<Entrega> histEntrega;

    public Motorista() {
        id = proxId;
        nome = "";
        dtaNasc = "";
        ajudante = new Ajudante();
        cpf = "";
        proxId++;
        histEntrega = new HashSet<>();
    }

    public Motorista(int id) {
        this.id = id;
        nome = null;
        dtaNasc = null;
        ajudante = null;
        cpf = null;
        histEntrega = null;
    }
    public Motorista(String cpf) {
        this.id = 0;
        this.nome = null;
        this.dtaNasc = null;
        this.cpf = cpf;
        this.ajudante = null;
        histEntrega = null;
    }
    public String getNome() {
        return nome;
    }
    public int getId() {
        return id;
    }
    public String getDtaNasc() {
        return dtaNasc;
    }
    public String getCpf() {
        return cpf;
    }
    public HashSet<Entrega> getHistEntrega() {
        return histEntrega;
    }
    public Ajudante getAjudante() {
        return ajudante;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setDtaNasc(String dtaNasc) {
        this.dtaNasc = dtaNasc;
    }
    public void setAjudante(Ajudante ajudante) {
        this.ajudante = ajudante;
    }
    public void setAjudante(String nome, String cpf) {
        ajudante = new Ajudante(nome, cpf);
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void addHistEntrega(Entrega entrega) {
        histEntrega.add(entrega);
    }
    public void removerHistEntrega(Entrega entrega) {
        histEntrega.remove(entrega);
    }
    public class Ajudante {

        String nome;
        String cpf;

        public Ajudante() {
            nome = "";
            cpf = "";
        }
        public Ajudante(String nome, String cpf) {
            this.nome = nome;
            this.cpf = cpf;
        }
        public String getNome() {
            return nome;
        }
        public String getCpf() {
            return cpf;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
        public void setCpf(String cpf) {
            this.cpf = cpf;
        }
    }
}













