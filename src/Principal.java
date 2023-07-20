import java.time.format.DateTimeFormatter;

class MenuOption {
    private final String title;
    private final Runnable action;

    public MenuOption(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void execute() {
        action.run();
    }
}
public class Principal {
    public static Banco bd = Banco.getBanco();

    public static void main(String[] args) {
        boolean process = true;

        enum MenuPrincipalOption {
            MOTORISTA,
            ENTREGA,
            SAIR
        }
        while(process) {
            try {
                exibirMenuPrincipal();
                int opc = Integer.parseInt(Leitura.entDados(""));

                switch(MenuPrincipalOption.values()[opc - 1]) {
                    case MOTORISTA -> menuMotorista();
                    case ENTREGA -> menuEntrega();
                    case SAIR -> process = false;
                    default -> Leitura.entDados("Opcao invalida!");
                }
            }
            catch(NumberFormatException nfe) {
                Leitura.entDados("Digite apenas numeros inteiros!");
            }
        }
    }
    public static int lerOpc(int tam) {
        while (true) {
            try {
                int opc = Integer.parseInt(Leitura.entDados(""));
                if(opc < 1 || opc > tam) Leitura.entDados("Opcao invalida!");
                else return opc;
            } catch (NumberFormatException nfe) {
                Leitura.entDados("Digite apenas numeros inteiros!");
            }
        }
    }
    public static void menuMotorista() {

        MenuOption[] motoristasOption =  criarOpcoesMenuMotorista();
        exibirMenuOpcoes("MOTORISTA", motoristasOption);
        int opc = lerOpc(motoristasOption.length);

        if(opc >= 1 && opc <= motoristasOption.length) {
            motoristasOption[opc - 1].execute();
        } else {
            Leitura.entDados("Opcao invalida!");
        }
    }
    public static void menuEntrega() {

        MenuOption[] entregaOption = criarOpcoesMenuEntrega();
        exibirMenuOpcoes("ENTREGA", entregaOption);

        int opc = lerOpc(entregaOption.length);

        if(opc >= 1 && opc <= entregaOption.length) {
            entregaOption[opc - 1].execute();
        } else {
            Leitura.entDados("Opcao invalida!");
        }

    }
    public static void exibirMenuPrincipal() {
        System.out.println("-------------------------");
        System.out.println("--------- Menu ----------");
        System.out.println("-------------------------");
        System.out.println("1- Motorista.");
        System.out.println("2- Entrega.");
        System.out.println("3- Sair.");
    }
    public static MenuOption[] criarOpcoesMenuMotorista() {
        return new MenuOption[] {
                new MenuOption("MOTORISTA - Cadastrar", Principal::cadMotorista),
                new MenuOption("MOTORISTA - Alterar", Principal::altMotorista),
                new MenuOption("MOTORISTA - Consultar", Principal::consMotorista),
                new MenuOption("MOTORISTA - Todos", Principal::todosMotoristas),
                new MenuOption("MOTORISTA - Recuperar ID", Principal::recupIdMotorista),
                new MenuOption("MOTORISTA - Remover", Principal::removeMotorista),
                new MenuOption("Voltar", () -> {})
        };
    }
    public static MenuOption[] criarOpcoesMenuEntrega() {
        return new MenuOption[] {
                new MenuOption("ENTREGA - Realizar", Principal::realizarEntrega),
                new MenuOption("ENTREGA - Alterar", Principal::attEntrega),
                new MenuOption("ENTREGA - Consultar", Principal::consEntrega),
                new MenuOption("ENTREGA - Lancar atraso", Principal::lancarAtraso),
                new MenuOption("ENTREGA - Confirmar entrega", Principal::confirmarEntrega),
                new MenuOption("ENTREGA - Verificar pendentes", Principal::todasEntregas),
                new MenuOption("Voltar", () -> {})
        };
    }
    public static void exibirMenuOpcoes(String titulo, MenuOption[] opcoes) {
        System.out.println("-------------------------");
        System.out.println("--------- Menu ----------");
        System.out.println("-------------------------");
        System.out.println(titulo);

        int index = 1;
        for (MenuOption opcao : opcoes) {
            System.out.println(index++ +"- " +opcao.getTitle());
        }
    }
    public static Motorista exibirDadosMotorista(Motorista motorista) {
        if (motorista != null) {
            System.out.println("MOTORISTA - Id: " + motorista.getId());
            System.out.println("MOTORISTA - Nome: " + motorista.getNome());
            System.out.println("MOTORISTA - CPF: " + motorista.getCpf());
            System.out.println("MOTORISTA - Data de Nascimento: " + motorista.getDtaNasc());
            Leitura.entDados("AJUDANTE - " + (motorista.getAjudante()!= null ?
                    "Nome: " + motorista.getAjudante().getNome()
                            + "\nAJUDANTE - CPF: " + motorista.getAjudante().getCpf() : "Nenhum"
            ));
            if(!motorista.getHistEntrega().isEmpty()) {
                System.out.println("Historico entrega:");
                for(Entrega entrega : motorista.getHistEntrega()) System.out.println("Id: " +entrega.getId());
            }
            return motorista;
        }
        Leitura.entDados("MOTORISTA - nao encontrado!");
        return null;
    }
    public static Entrega exibirDadosEntrega(Entrega entrega) {
        if (entrega != null) {
            System.out.println("ENTREGA - Id: " + entrega.getId());
            System.out.println("ENTREGA - Motorista: " + entrega.getMotorista().getNome());
            System.out.println("ENTREGA - Rota: " + entrega.getRota().getOrigem() + " - " + entrega.getRota()
                    .getDestino());
            System.out.println("ENTREGA - Data do Pedido: " + entrega.getDataPedido()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("ENTREGA - Data de Entrega: " + entrega.getDataEntrega()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Leitura.entDados("ENTREGA - Status: " + (entrega.getStatus() ? "Concluida" : "Pendente"));
            return entrega;
        }
        Leitura.entDados("Entrega nao encontrada!");
        return null;
    }
    public static void cadMotorista() {
        Motorista motorista = new Motorista();

        motorista.setNome(Leitura.entDados("Nome: "));
        motorista.setCpf(Leitura.entDados("Cpf: "));
        motorista.setDtaNasc(Leitura.entDados("Data de nascimento: "));

        System.out.println("Possui ajudante? (1- Sim / 2- Nao): ");
        int opc = lerOpc(2);

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
        } else {
            Leitura.entDados("Motorista ja cadastrado!");
        }
    }
    public static void altMotorista() {
        Motorista motorista = bd.consMotorista(new Motorista(
                Integer.parseInt(Leitura.entDados("Digite o ID do motorista que deseja alterar: ")))
        );
        if (motorista != null) {
            System.out.println("------------------------");
            System.out.println("--------- Menu ----------");
            System.out.println("-------------------------");
            System.out.println("-- MOTORISTA - Alterar --");
            System.out.println("1- MOTORISTA - Nome.");
            System.out.println("2- MOTORISTA - CPF.");
            System.out.println("3- MOTORISTA - Data de nascimento.");
            System.out.println("4- MOTORISTA - Ajudante.");
            System.out.println("5- Voltar");
            int opc = lerOpc(5);
            switch (opc) {
                case 1 -> motorista.setNome(Leitura.entDados("MOTORISTA - novo nome: "));
                case 2 -> motorista.setCpf(Leitura.entDados("MOTORISTA - novo CPF: "));
                case 3 -> motorista.setDtaNasc(Leitura.entDados("MOTORISTA - nova data de nascimento: "));
                case 4 -> {
                    System.out.println("Deseja adicionar ou remover ajudante?\" + \" (1- Adicionar/Alterar\\n2- Remover):");
                    int ajudante = lerOpc(2);
                    if (ajudante == 1) {
                        motorista.setAjudante(
                                Leitura.entDados("Nome do novo ajudante: "),
                                Leitura.entDados("CPF do novo ajudante: ")
                        );
                    } else {
                        motorista.setAjudante(null);
                    }
                }
            }
            bd.altMotorista(motorista);
            Leitura.entDados("Motorista alterado com sucesso!");
        } else {
            Leitura.entDados("Motorista não encontrado!");
        }
    }
    public static Motorista consMotorista() {
        Motorista motorista = bd.consMotorista(new Motorista(
                Integer.parseInt(Leitura.entDados("Digite o ID do motorista que deseja alterar: ")))
        );
        return exibirDadosMotorista(motorista);
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
            return exibirDadosEntrega(entrega);
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
            bd.getBdEntrega().values().stream()
                    .filter(entrega -> !entrega.getStatus())
                    .forEach(entrega ->  {
                        System.out.println("ENTREGA - Id: " + entrega.getId());
                        System.out.println("ENTREGA - Motorista: " + entrega.getMotorista().getNome());
                        System.out.println("ENTREGA - Rota: " + entrega.getRota().getOrigem() + " - " + entrega.getRota().getDestino());
                        System.out.println("ENTREGA - Status: " + (entrega.getStatus() ? "Concluída" : "Pendente"));
                        System.out.println("-----------------------------------------");
            });
        }
        else System.out.println("Nao ha entregas pendentes.");
    }
    public static void todosMotoristas() {

        if(!bd.getBdMotorista().isEmpty()) {
            System.out.println("Listagem de motoristas:");
            bd.getBdMotorista().values().forEach(motorista -> {
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
                    motorista.getHistEntrega().forEach(entrega -> System.out.println("Id: " +entrega.getId()));
                }
                System.out.println("-----------------------------------------");
            });
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
