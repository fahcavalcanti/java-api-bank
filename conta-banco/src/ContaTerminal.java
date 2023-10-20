import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContaTerminal {
    private Map<Integer, ContaBancaria> contas;
    private int proximoNumeroConta;

    public ContaTerminal() {
        contas = new HashMap<>();
        proximoNumeroConta = 1;
    }

    public int criarConta(int numeroConta, String nomeTitular, double depositoInicial) {
        ContaBancaria conta = new ContaBancaria(numeroConta, nomeTitular);
        conta.depositar(depositoInicial);
        contas.put(numeroConta, conta);
        return numeroConta;
    }

    public double verificarSaldo(int numeroConta) {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            return conta.getSaldo();
        } else {
            return -1; // Número de conta inválido
        }
    }

    public double realizarDeposito(int numeroConta, double valor) {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
            return conta.getSaldo();
        }
        return -1; // Falha no depósito
    }

    public double realizarSaque(int numeroConta, double valor) {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null && conta.getSaldo() >= valor) {
            conta.sacar(valor);
            return conta.getSaldo();
        }
        return -1; // Falha no saque
    }

    public static void main(String[] args) {
        ContaTerminal terminal = new ContaTerminal();
        Scanner scanner = new Scanner(System.in);
        int numeroConta = 0;
        String nomeTitular = "";
        boolean contaCriada = false;

        while (true) {
            System.out.print("Escolha uma opção:\n");

            if (!contaCriada) {
                System.out.print("1 - Criar nova conta\n");
            } else {
                System.out.print("2 - Realizar depósito\n" +
                                 "3 - Realizar saque\n" +
                                 "4 - Verificar saldo\n");
            }

            System.out.print("5 - Sair\n" +
                             "Digite o número da opção desejada: ");

            int opcao = scanner.nextInt();

            if (!contaCriada && opcao == 1) {
                System.out.print("Digite o número da conta: ");
                numeroConta = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.print("Digite o nome do titular da conta: ");
                nomeTitular = scanner.nextLine();

                System.out.print("Digite o valor do depósito inicial: ");
                double depositoInicial = scanner.nextDouble();

                terminal.criarConta(numeroConta, nomeTitular, depositoInicial);
                contaCriada = true;

                System.out.println("Conta criada com sucesso!");
            } else if (contaCriada) {
                if (opcao == 2) {
                    System.out.print("Digite o valor do depósito: ");
                    double deposito = scanner.nextDouble();
                    double novoSaldo = terminal.realizarDeposito(numeroConta, deposito);
                    System.out.println("Saldo da conta: " + novoSaldo);
                } else if (opcao == 3) {
                    System.out.print("Digite o valor do saque: ");
                    double saque = scanner.nextDouble();
                    double novoSaldo = terminal.realizarSaque(numeroConta, saque);
                    if (novoSaldo == -1) {
                        System.out.println("Falha no saque: saldo insuficiente.");
                    } else {
                        System.out.println("Saldo da conta: " + novoSaldo);
                    }
                } else if (opcao == 4) {
                    double saldo = terminal.verificarSaldo(numeroConta);
                    if (saldo != -1) {
                        System.out.println("Saldo da conta: " + saldo);
                    } else {
                        System.out.println("Número de conta inválido.");
                    }
                } else if (opcao == 5) {
                    System.out.println("OPERAÇÃO ENCERRADA!");
                    break; // Encerrar o programa
                }
            } else if (opcao == 5) {
                System.out.println("OPERAÇÃO ENCERRADA!");
                break; // Encerrar o programa
            }
        }

        scanner.close();
    }
}

class ContaBancaria {
    private int numero;
    private String nomeTitular;
    private double saldo;

    public ContaBancaria(int numero, String nomeTitular) {
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.saldo = 0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }
}
