package service;

import br.com.ada.bdd.model.Carro;

import java.util.Objects;

class CarroServiceTest {


    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");
        acelerar();
        System.out.println("----------------------------------------------------");
        acelerarMaxima();
        System.out.println("----------------------------------------------------");
        frear();
        System.out.println("----------------------------------------------------");
        desligar();
        System.out.println("----------------------------------------------------");
        desligarSemFrear();
        System.out.println("----------------------------------------------------");
    }

    public static void acelerar() {
        System.out.println("Teste: acelerar");

        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Dado: Um carro ligado
        service.ligar(carro);
        int velocidadeInicial = carro.getVelocidadeAtual();
        System.out.println("Velocidade atual do carro: " + velocidadeInicial);

        //Quando: Acelerar o carro
        service.acelerar(carro, 100);


        //Então: velocidade aumenta +10
        int velocidadeAcelerar = carro.getVelocidadeAtual();
        System.out.println("Velocidade atual do carro: " + velocidadeAcelerar);

        if(carro.getVelocidadeAtual() == 10) {
            System.out.println("Passou!");
        } else {
            System.out.println("Falhou!");
        }
    }

    public static void acelerarMaxima() {
        System.out.println("Teste: acelerarMaxima");

        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Dado: Um carro ligado e em velocidade máxima
        service.ligar(carro);
        carro.setVelocidadeAtual(100);

        //Quando: Acelerar o carro
        service.acelerar(carro, 100);

        //Então: Mantém a velocidade máxima
        System.out.println("Velocidade atual do carro: " + carro.getVelocidadeAtual());

        if(carro.getVelocidadeAtual() == 100) {
            System.out.println("Passou!");
        } else {
            System.out.println("Falhou!");
        }
    }

    public static void frear() {
        System.out.println("Teste: frear");

        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Dado: Um carro ligado
        service.ligar(carro);

        //Quando: Frear o carro
        service.frear(carro);

        //Então: mantém a velocidade 0
        System.out.println("Velocidade atual do carro: " + carro.getVelocidadeAtual());

        if(carro.getVelocidadeAtual() == 0) {
            System.out.println("Passou!");
        } else {
            System.out.println("Falhou!");
        }
    }


    public static void desligar() {
        System.out.println("Teste: desligar");

        CarroService service = new CarroService();


        //Dado: Um carro desligado
        Carro carro = new Carro();

        //Quando: Desligar o carro
        service.desligar(carro);

        //Então: Ocorre um erro
        System.out.println(service.desligar(carro));

        if(Objects.equals(service.desligar(carro), "O carro já está desligado")) {
            System.out.println("Passou!");
        } else {
            System.out.println("Falhou!");
        }

    }

    public static void desligarSemFrear() {
        System.out.println("Teste: desligarSemFrear");

        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Dado: Um carro ligado
        service.ligar(carro);
        service.acelerar(carro, 100);

        //Quando: Em velocidade maior que 0
        System.out.println("Velocidade atual do carro: " + carro.getVelocidadeAtual());

        //Então: Não pode desligar
        service.desligar(carro);
        System.out.println(service.desligar(carro));
        if(carro.isLigado()) {
            System.out.println("Passou!");
        } else {
            System.out.println("Falhou!");
        }

    }

}