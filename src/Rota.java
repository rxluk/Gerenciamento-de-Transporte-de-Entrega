public class Rota {
    
    private String origem;
    private String destino;
    private Double distancia;

    public Rota() {
        origem = "";
        destino = "";
        distancia = 0.0;
    }

    public Rota(String origem, String destino, Double distancia) {
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getOrigem() {
        return origem;
    }
    public String getDestino() {
        return destino;
    }
    public Double getDistancia() {
        return distancia;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }
}
