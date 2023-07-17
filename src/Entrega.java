import java.time.LocalDate;

public class Entrega {

    private Motorista motorista;
    private Rota rota;
    private final LocalDate dataPedido;
    private LocalDate dataEntrega;
    private Boolean status;
    private static int proximoId = 1;
    private final int id;

    public Entrega(int id) {
        this.id = id;
        this.motorista = null;
        this.rota = null;
        dataEntrega = null;
        dataPedido = null;
        status = null;
    }

    public Entrega(Motorista motorista, Rota rota) {
        this.motorista = motorista;
        this.rota = rota;
        dataEntrega = calcDataEntrega(rota);
        dataPedido = LocalDate.now();
        this.id = proximoId;
        status = false;
        proximoId++;
    }

    public LocalDate calcDataEntrega(Rota rota) {

        //88 = velocidade média de um caminhão na pista.
        double tempoGasto = rota.getDistancia()/88;
        if(tempoGasto < 24) {
            dataEntrega = dataPedido;
        }
        else {
            int dias = (int) Math.ceil(tempoGasto/24);
            dataEntrega = dataPedido.plusDays(dias);
        }
        return dataEntrega;
    }
    public boolean getStatus() {
        return status;
    }
    public Motorista getMotorista() {
        return motorista;
    }
    public Rota getRota() {
        return rota;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public LocalDate getDataEntrega() {
        return dataEntrega;
    }
    public int getId() {
        return id;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }
    public void setRota(Rota rota) {
        this.rota = rota;
    }
    public void atrasoEntrega(int diasAtraso) {
        dataEntrega = dataEntrega.plusDays(diasAtraso);
    }
}
