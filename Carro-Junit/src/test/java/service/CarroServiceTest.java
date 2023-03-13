package service;

import br.com.ada.bdd.model.Carro;
import org.junit.Test;


import static org.junit.Assert.*;

public class CarroServiceTest {

    @Test
    public void acelerar() {
        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Given: Um carro ligado
        service.ligar(carro);

        //When: Acelerar o carro
        service.acelerar(carro, 100);

        //Then: velocidade aumenta +10
        assertEquals(10, carro.getVelocidadeAtual());
    }

    @Test
    public void acelerarAteMaxima() {
        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Given: Um carro ligado e em velocidade máxima
        service.ligar(carro);
        carro.setVelocidadeAtual(100);

        //When: Acelerar o carro
        service.acelerar(carro, 100);

        //Then: Mantém a velocidade máxima
        assertEquals(100, carro.getVelocidadeAtual());

    }

    @Test
    public void frear() {
        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Given: Um carro ligado
        service.ligar(carro);

        //When: Frear o carro
        service.frear(carro);

        //Then: mantém a velocidade 0
        assertEquals(0, carro.getVelocidadeAtual());

    }


    @Test
    public void desligar() {
        CarroService service = new CarroService();

        //Given: Um carro desligado
        Carro carro = new Carro();

        //When: Desligar o carro
        service.desligar(carro);

        //Then: Ocorre um erro
        assertEquals("O carro já está desligado", service.desligar(carro));

    }

    @Test
    public void desligarSemFrear() {
        CarroService service = new CarroService();
        Carro carro = new Carro();

        //Given: Um carro ligado
        service.ligar(carro);
        service.acelerar(carro, 100);

        //When: Em velocidade maior que 0

        //Then: Não pode desligar
        service.desligar(carro);
        assertTrue(carro.isLigado());

    }

}