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

    public int criarConta() {
        int numeroConta = proximoNumeroConta++;
        ContaBancaria conta = new ContaBancaria();
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

    public void realizarDeposito(int numeroConta, double valor) {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null) {
            conta.depositar(valor);
        }
    }

    public boolean realizarSaque(int numeroConta, double valor) {
        ContaBancaria conta = contas.get(numeroConta);
        if (conta != null && conta.getSaldo() >= valor) {
            conta.sacar(valor);
            return true;
        }
        return false; // Falha no saque
    }

    public static void main(String[] args) {
        ContaTerminal terminal = new ContaTerminal();
        Scanner scanner = new Scanner(System.in);

        int conta1 = terminal.criarConta();
        int conta2 = terminal.criarConta();

        System.out.print("Digite o valor do depósito para a conta 1: ");
        double depositoConta1 = scanner.nextDouble();
        terminal.realizarDeposito(conta1, depositoConta1);

        System.out.print("Digite o valor do depósito para a conta 2: ");
        double depositoConta2 = scanner.nextDouble();
        terminal.realizarDeposito(conta2, depositoConta2);

        System.out.print("Digite o valor do saque da conta 1: ");
        double saqueConta1 = scanner.nextDouble();
        terminal.realizarSaque(conta1, saqueConta1);

        System.out.print("Digite o valor do saque da conta 2: ");
        double saqueConta2 = scanner.nextDouble();
        terminal.realizarSaque(conta2, saqueConta2);

        System.out.println("Saldo da conta 1: " + terminal.verificarSaldo(conta1));
        System.out.println("Saldo da conta 2: " + terminal.verificarSaldo(conta2));

        scanner.close();
    }
}

class ContaBancaria {
    private double saldo;

    public ContaBancaria() {
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
