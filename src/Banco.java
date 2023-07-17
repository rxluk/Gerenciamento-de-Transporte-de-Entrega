import java.util.ArrayList;

public class Banco {

    private static Banco banco;
    private final ArrayList<Entrega> bdEntrega;
    private final ArrayList<Motorista> bdMotorista;

    private Banco() {
        bdEntrega = new ArrayList<>();
        bdMotorista = new ArrayList<>();
    }

    public static Banco getBanco() {
        if (banco == null) {
            banco = new Banco();
        }
        return banco;
    }

    public ArrayList<Motorista> getBdMotorista() { return bdMotorista; }
    public ArrayList<Entrega> getBdEntrega() { return bdEntrega; }
    public Motorista cadMotorista(Motorista motorista) {
        if (consMotorista(motorista) == null) {
            bdMotorista.add(motorista);
            return motorista;
        }
        return null;
    }

    public Motorista altMotorista(Motorista motorista) {
        if (consMotorista(motorista) != null) {
            bdMotorista.set(bdMotorista.indexOf(consMotorista(motorista)), motorista);
            return motorista;
        }
        return null;
    }

    public Motorista consMotorista(Motorista motorista) {
        if(motorista.getId() == 0) {
            for(Motorista m : getBdMotorista()) {
                if(m.getCpf().equals(motorista.getCpf())) return m;
            }
        }
        else {
            for (Motorista m : getBdMotorista()) {
                if (m.getId() == motorista.getId()) return m;
            }
        }
        return null;
    }
    public Motorista removeMotorista(Motorista motorista) {
        if (consMotorista(motorista) != null) {
            bdMotorista.remove(consMotorista(motorista));
            return null;
        }
        return motorista;
    }
    public void cadEntrega(Entrega entrega) { bdEntrega.add(entrega); }
    public Entrega altEntrega(Entrega entrega) {
        if(consEntrega(entrega) != null) {
            bdEntrega.set(bdEntrega.indexOf(consEntrega(entrega)), entrega);
            if(entrega.getStatus()) entrega.getMotorista().addHistEntrega(entrega);
            return entrega;
        }
        return null;
    }
    public Entrega consEntrega(Entrega entrega) {
        for(Entrega e : getBdEntrega()) {
            if(e.getId() == entrega.getId()) {
                return e;
            }
        }
        return null;
    }
}