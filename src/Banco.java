import java.util.HashMap;
import java.util.Map;

public class Banco {

    private static Banco banco;
    private final Map<Integer, Entrega> bdEntrega;
    private final Map<Integer, Motorista> bdMotorista;

    private Banco() {
        bdEntrega = new HashMap<>();
        bdMotorista = new HashMap<>();
    }

    public static Banco getBanco() {
        if (banco == null) {
            banco = new Banco();
        }
        return banco;
    }

    public Map<Integer, Motorista> getBdMotorista() {
        return bdMotorista;
    }
    public Map<Integer, Entrega> getBdEntrega() {
        return bdEntrega;
    }
    public Motorista cadMotorista(Motorista motorista) {
        if (!bdMotorista.containsKey(motorista.getId())) {
            bdMotorista.put(motorista.getId(), motorista);
            return motorista;
        }
        return null;
    }

    public Motorista altMotorista(Motorista motorista) {
        if (bdMotorista.containsKey(motorista.getId())) {
           bdMotorista.put(motorista.getId(), motorista);
            return motorista;
        }
        return null;
    }

    public Motorista consMotorista(Motorista motorista) {
        if(motorista.getId() == 0) {
            for(Motorista m : getBdMotorista().values()) {
                if(m.getCpf().equals(motorista.getCpf())) return m;
            }
        }
        else if(getBdMotorista().containsKey(motorista.getId())) return getBdMotorista().get(motorista.getId());
        return null;
    }
    public Motorista removeMotorista(Motorista motorista) {
        if (bdMotorista.containsKey(motorista.getId())) {
            bdMotorista.remove(motorista.getId());
            return null;
        }
        return motorista;
    }
    public void cadEntrega(Entrega entrega) {
        bdEntrega.put(entrega.getId(), entrega); }
    public Entrega altEntrega(Entrega entrega) {
        if(bdEntrega.containsKey(entrega.getId())) {
            bdEntrega.put(entrega.getId(), entrega);
            if(entrega.getStatus()) entrega.getMotorista().addHistEntrega(entrega);
            return entrega;
        }
        return null;
    }
    public Entrega consEntrega(Entrega entrega) {
        if(bdEntrega.containsKey(entrega.getId())) return bdEntrega.get(entrega.getId());
        return null;
    }
}