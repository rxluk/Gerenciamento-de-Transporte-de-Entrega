public class Principal {

    public static Banco bd = Banco.getBanco();

    public static void main(String[] args) {

        boolean process = true;
        while(process) {

            int opc;
            try {
                System.out.println("-------------------------");
                System.out.println("--------- Menu ----------");
                System.out.println("-------------------------");
                System.out.println("1- Motorista.");
                System.out.println("2- Entrega.");
                System.out.println("3- Sair.");
                opc = Integer.parseInt(Leitura.entDados(""));
                switch(opc) {
                    case 1 -> menuMotorista();
                    case 2 -> menuEntrega();
                    case 3 -> process = false;
                    default -> Leitura.entDados("Opcao invalida!");
                }
            }
            catch(NumberFormatException nfe) {
                Leitura.entDados("Digite apenas numeros inteiros!");
            }
        }
    }
    public static void menuMotorista() {

        int opc;
        try {
            System.out.println("-------------------------");
            System.out.println("--------- Menu ----------");
            System.out.println("-------------------------");
            System.out.println("1- MOTORISTA - Cadastrar.");
            System.out.println("2- MOTORISTA - Alterar.");
            System.out.println("3- MOTORISTA - Consultar.");
            System.out.println("4- MOTORISTA - Todos.");
            System.out.println("5- MOTORISTA - Recuperar ID.");
            System.out.println("6- MOTORISTA - Remover.");
            System.out.println("7- Voltar");
            opc = Integer.parseInt(Leitura.entDados(""));
            switch (opc) {
                case 1 -> cadMotorista();
                case 2 -> altMotorista();
                case 3 -> consMotorista();
                case 4 -> todosMotoristas();
                case 5 -> recupIdMotorista();
                case 6 -> removeMotorista();
                case 7 -> {
                }
                default -> Leitura.entDados("Opcao invalida!");
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Digite apenas numeros inteiros!");
        }
    }
    public static void menuEntrega() {

        int opc;
        try {
            System.out.println("-------------------------");
            System.out.println("--------- Menu ----------");
            System.out.println("-------------------------");
            System.out.println("1- ENTREGA - Realizar.");
            System.out.println("2- ENTREGA - Alterar.");
            System.out.println("3- ENTREGA - Consultar.");
            System.out.println("4- ENTREGA - Lancar atraso.");
            System.out.println("5- ENTREGA - Confirmar entrega.");
            System.out.println("6- ENTREGA - Verificar pendentes.");
            System.out.println("7- Voltar");
            opc = Integer.parseInt(Leitura.entDados(""));
            switch (opc) {
                case 1 -> realizarEntrega();
                case 2 -> attEntrega();
                case 3 -> consEntrega();
                case 4 -> lancarAtraso();
                case 5 -> confirmarEntrega();
                case 6 -> todasEntregas();
                default -> {
                    if(opc == 7) {
                        return;
                    }
                    Leitura.entDados("Opcao invalida!");
                }
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Digite apenas numeros inteiros!");
        }
    }
    public static void cadMotorista() {
        int opc;
        try {
            Motorista motorista = new Motorista();
            motorista.setNome(Leitura.entDados("Nome: "));
            motorista.setCpf(Leitura.entDados("Cpf: "));
            motorista.setDtaNasc(Leitura.entDados("Data de nascimento: "));
            do {
                opc = Integer.parseInt(Leitura.entDados("Possui ajudante? (1- Sim / 2- Nao): "));
                if(opc > 2 || opc < 1) {
                    Leitura.entDados("Opcao invalida!");
                }
            }while(opc < 1 || opc > 2 );
            if(opc == 1) {
                motorista.getAjudante().setNome(Leitura.entDados("Nome ajudante: "));
                motorista.getAjudante().setCpf(Leitura.entDados("Cpf ajudante: "));
            }
            else {
                motorista.setAjudante(null);
            }
            motorista = bd.cadMotorista(motorista);
            if(motorista != null) {
                Leitura.entDados("ID Cadastrado - Motorista: " + motorista.getId());
                return;
            }
            Leitura.entDados("Erro ao cadastrar!");
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Digite apenas numeros inteiros!");
        }
    }
    public static void altMotorista() {
        try {
            Motorista motorista = bd.consMotorista(new Motorista(
                    Integer.parseInt(Leitura.entDados("Digite o ID do motorista que deseja alterar: ")))
            );
            if (motorista != null) {
                int opc;
                System.out.println("------------------------");
                System.out.println("--------- Menu ----------");
                System.out.println("-------------------------");
                System.out.println("-- MOTORISTA - Alterar --");
                System.out.println("1- MOTORISTA - Nome.");
                System.out.println("2- MOTORISTA - CPF.");
                System.out.println("3- MOTORISTA - Data de nascimento.");
                System.out.println("4- MOTORISTA - Ajudante.");
                System.out.println("5- Voltar");
                opc = Integer.parseInt(Leitura.entDados(""));
                switch (opc) {
                    case 1 -> motorista.setNome(Leitura.entDados("MOTORISTA - novo nome: "));
                    case 2 -> motorista.setCpf(Leitura.entDados("MOTORISTA - novo CPF: "));
                    case 3 -> motorista.setDtaNasc(Leitura.entDados("MOTORISTA - nova data de nascimento: "));
                    case 4 -> {
                        int ajudante;
                        try {
                            ajudante = Integer.parseInt(Leitura.entDados(
                                    "Deseja adicionar ou remover ajudante?" + " (1- Adicionar/Alterar\n2- Remover): "
                            ));
                        } catch (NumberFormatException nfe) {
                            Leitura.entDados("Digite apenas numeros inteiro!");
                            return;
                        }
                        if (ajudante == 1) {
                            motorista.setAjudante(
                                    Leitura.entDados("Nome do novo ajudante: "),
                                    Leitura.entDados("CPF do novo ajudante: ")
                            );
                        } else if (ajudante == 2) {
                            motorista.setAjudante(null);
                        } else {
                            Leitura.entDados("Opção inválida!");
                        }
                    }
                    default -> {
                        if (opc == 5) {
                            return;
                        }
                        Leitura.entDados("Opcao invalida!");
                        return;
                    }
                }
                bd.altMotorista(motorista);
                Leitura.entDados("Motorista alterado com sucesso!");
                return;
            } 
            Leitura.entDados("Motorista não encontrado!");
        }
        catch (NumberFormatException nfe) {
            Leitura.entDados("Digite apenas numeros inteiros!");
        }
            
    }
    public static void consMotorista() {
        try{
            Motorista motorista = bd.consMotorista(new Motorista(
                    Integer.parseInt(Leitura.entDados("Digite o ID do motorista que deseja alterar: ")))
            );
            if (motorista != null) {
                System.out.println("MOTORISTA - Id: " + motorista.getId());
                System.out.println("MOTORISTA - Nome: " + motorista.getNome());
                System.out.println("MOTORISTA - CPF: " + motorista.getCpf());
                System.out.println("MOTORISTA - Data de Nascimento: " + motorista.getDtaNasc());
                if(motorista.getAjudante() != null) {
                    System.out.println("AJUDANTE  - Nome: " + motorista.getAjudante().getNome());
                    Leitura.entDados("AJUDANTE  - CPF: " + motorista.getAjudante().getCpf());
                } 
                else {
                    Leitura.entDados("AJUDANTE  - Nenhum");
                }
                if(!motorista.getHistEntrega().isEmpty()) {
                    System.out.println("Historico entrega:");
                    for(Entrega entrega : motorista.getHistEntrega()) System.out.println("Id: " +entrega.getId());
                }
            }
            else {
                Leitura.entDados("MOTORISTA - nao encontrado!");
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Entrada invalida. Digite apenas numeros inteiros!");
        }
        
    }
    public static void removeMotorista() {
        try {
            Motorista motorista = bd.removeMotorista(new Motorista(
                    Integer.parseInt(Leitura.entDados("MOTORISTA - ID p/ remover: ")))
            );
            if (motorista == null) {
                Leitura.entDados("MOTORISTA - removido com sucesso!");
            } else {
                Leitura.entDados("MOTORISTA - nao encontrado!");
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Entrada invalida. Digite apenas numeros inteiros!");
        }
        
    }
    public static void realizarEntrega() {
        try{
            Motorista motorista = bd.consMotorista(new Motorista(
                    Integer.parseInt(Leitura.entDados("ENTREGA - ID Motorista: "))
            ));
            if (motorista != null) {
                Entrega entrega = new Entrega(
                        motorista,
                        new Rota(
                                Leitura.entDados("ENTREGA - origem da rota: "),
                                Leitura.entDados("ENTREGA - destino da rota: "),
                                Double.parseDouble(Leitura.entDados("ENTREGA - distancia da rota (km): "))
                        )
                );
                bd.cadEntrega(entrega);

                System.out.println("ENTREGA - realizada com sucesso!");
                System.out.println("ENTREGA - ID: " + entrega.getId());
                System.out.println("ENTREGA - Prazo: " + entrega.getDataEntrega());
                Leitura.entDados("ENTREGA - Status: " + (entrega.getStatus() ? "Concluída" : "Pendente"));
            } else {
                Leitura.entDados("MOTORISTA - nao encontrado!");
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Entrada invalida. Digite apenas numero no campo distancia!");
        }
    }
    public static Entrega consEntrega() {
        try {
            Entrega entrega = bd.consEntrega(new Entrega(
                    Integer.parseInt(Leitura.entDados("ID da entrega p/ consulta:  "))
            ));
            if (entrega != null) {
                System.out.println("ENTREGA - Id: " + entrega.getId());
                System.out.println("ENTREGA - Motorista: " + entrega.getMotorista().getNome());
                System.out.println("ENTREGA - Rota: " + entrega.getRota().getOrigem() + " - " + entrega.getRota().getDestino());
                System.out.println("ENTREGA - Data do Pedido: " + entrega.getDataPedido());
                System.out.println("ENTREGA - Data de Entrega: " + entrega.getDataEntrega());
                Leitura.entDados("ENTREGA - Status: " + (entrega.getStatus() ? "Concluida" : "Pendente"));
                return entrega;
            }
            else {
                Leitura.entDados("Entrega nao encontrada!");
            }
        }
        catch(NumberFormatException nfe) {
            Leitura.entDados("Entrada invalida. Digite apenas numeros inteiros!");
        }
        return null;
    }
    public static void attEntrega() {
        try {
            Entrega entrega = bd.consEntrega(new Entrega(
                    Integer.parseInt(Leitura.entDados("ID da entrega p/ atualizar: "))
            ));
            if (entrega != null && !entrega.getStatus()) {
                System.out.println("Escolha uma opção de alteração:");
                System.out.println("1- Motorista");
                System.out.println("2- Rota");
                System.out.println("3- Voltar.");
                int opcao = Integer.parseInt(Leitura.entDados(""));

                switch (opcao) {
                    case 1 -> {
                        try {
                            Motorista motorista = bd.consMotorista(new Motorista(
                                    Integer.parseInt(Leitura.entDados("Digite o ID do novo motorista: ")))
                            );
                            if (motorista != null) {
                                entrega.getMotorista().removerHistEntrega(entrega);
                                entrega.setMotorista(motorista);
                                motorista.addHistEntrega(entrega);
                                System.out.println("Motorista atualizado com sucesso!");
                            } else {
                                Leitura.entDados("Motorista não encontrado!");
                                return;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Digite apenas numeros inteiros!");
                            return;
                        }
                    }
                    case 2 -> {
                        try {
                            System.out.println("Criando uma nova rota...");
                            entrega.setRota(
                                    new Rota(
                                            Leitura.entDados("Digite a origem da rota: "),
                                            Leitura.entDados("Digite o destino da rota: "),
                                            Double.parseDouble(Leitura.entDados("Digite a distância da rota: "))
                                    )
                            );
                            System.out.println("Rota atualizada com sucesso!");
                        } catch (NumberFormatException e) {
                            System.out.println("Digite apenas numeros inteiros!");
                            return;
                        }
                    }
                    default -> {
                        if(opcao == 3) {
                            return;
                        }
                        System.out.println("Opção inválida!");
                        return;
                    }
                }
                bd.altEntrega(entrega);
            }
            else if(entrega != null && entrega.getStatus()) {
                System.out.println("Entrega ja concluida!");
            }
            else {
                System.out.println("Entrega não encontrada!");
            }
        }
        catch(NumberFormatException nfe) {
            System.out.println("Entrada invalida. Digite apenas numeros inteiros!");
        }
    }
    public static void todasEntregas() {

        if(!bd.getBdEntrega().isEmpty()) {
            System.out.println("Listagem de entregas:");
            for(Entrega entrega : bd.getBdEntrega()) {
                if(!entrega.getStatus()) {
                    System.out.println("ENTREGA - Id: " + entrega.getId());
                    System.out.println("ENTREGA - Motorista: " + entrega.getMotorista().getNome());
                    System.out.println("ENTREGA - Rota: " + entrega.getRota().getOrigem() + " - " + entrega.getRota().getDestino());
                    System.out.println("ENTREGA - Status: " + (entrega.getStatus() ? "Concluída" : "Pendente"));
                    System.out.println("-----------------------------------------");
                }
            }
        }
        else System.out.println("Nao ha entregas pendentes.");
    }
    public static void todosMotoristas() {

        if(!bd.getBdMotorista().isEmpty()) {
            System.out.println("Listagem de motoristas:");
            for(Motorista motorista : bd.getBdMotorista()) {
                System.out.println("MOTORISTA - Id: " +motorista.getId());
                System.out.println("MOTORISTA - Nome: " +motorista.getNome());
                System.out.println("MOTORISTA - CPF: " +motorista.getCpf());
                System.out.println("MOTORISTA - Data de nascimento: " +motorista.getDtaNasc());
                if(motorista.getAjudante() != null) {
                    System.out.println("AJUDANTE - Nome: " +motorista.getAjudante().getNome());
                    System.out.println("AJUDANTE - CPF: " +motorista.getAjudante().getCpf());
                }
                else {
                    System.out.println("AJUDANTE - Nenhum");
                }
                if(!motorista.getHistEntrega().isEmpty()) {
                    System.out.println("Historico entrega:");
                    for(Entrega entrega : motorista.getHistEntrega()) System.out.println("Id: " +entrega.getId());
                }
                System.out.println("-----------------------------------------");
            }
        }
        else {
            System.out.println("Nao  ha motoristas cadastrados");
        }
    }
    public static void recupIdMotorista() {
        Motorista motorista = bd.consMotorista(new Motorista(Leitura.entDados("MOTORISTA - CPF: ")));
        if(motorista != null) {
            System.out.println("MOTORISTA - Id: " +motorista.getId());
            System.out.println("MOTORISTA - Nome: " +motorista.getNome());
            System.out.println("MOTORISTA - CPF: " +motorista.getCpf());
            System.out.println("MOTORISTA - Data de nascimento: " +motorista.getDtaNasc());
        }
        else {
            System.out.println("CPF nao encontrado!");
        }
    }
    public static void lancarAtraso() {
        try {
            Entrega entrega = consEntrega();
            if(entrega != null) {
                entrega.atrasoEntrega(Integer.parseInt(Leitura.entDados("Entrega - Dias p/ atrasar: ")));
            }
        }
        catch(NumberFormatException e) {
            Leitura.entDados("Digite apenas numeros inteiros!");
        }
    }
    public static void confirmarEntrega() {
        try{
            Entrega entrega = consEntrega();
            if(entrega != null) {
                int novoStatus = Integer.parseInt(
                        Leitura.entDados("Digite o novo status (1- concluída / 2- pendente): ")
                );
                if(novoStatus < 3 && novoStatus > 0) {
                    entrega.setStatus(novoStatus != 2);
                    System.out.println("Status atualizado com sucesso!");
                }else {
                    Leitura.entDados("Erro, insira a opcao corretamente!");
                }
            }
        }
        catch(NumberFormatException nfe) {
            System.out.println("Entrada invalida. Digite apenas numeros inteiros!");
        }
    }
}
