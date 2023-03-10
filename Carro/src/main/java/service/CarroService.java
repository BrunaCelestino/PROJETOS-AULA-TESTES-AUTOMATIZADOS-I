package service;

import br.com.ada.bdd.model.Carro;

public class CarroService {

    public void acelerar(Carro carro, int velocidadeMaxima) {
        if(carro.getVelocidadeAtual() < velocidadeMaxima)
        carro.setVelocidadeAtual(carro.getVelocidadeAtual() + 10);
    }

    public void frear(Carro carro) {
            carro.setVelocidadeAtual(0);

    }

    public void ligar(Carro carro) {
        carro.setLigado(true);
    }

    public String desligar(Carro carro) {
        if(!carro.isLigado()) {
            return ("O carro já está desligado");
        } else if(carro.getVelocidadeAtual() > 0){
            return ("O carro precisa estar parado para desligar!");
        } else {
            carro.setLigado(false);
            return("Desligando o carro!");

        }
    }

    public String estadoAtual(Carro carro) {
        return carro.toString();
    }
}
