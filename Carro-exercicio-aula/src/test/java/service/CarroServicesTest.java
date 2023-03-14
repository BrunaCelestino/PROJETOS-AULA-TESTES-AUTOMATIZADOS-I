package service;

import br.com.ada.bdd.model.Carro;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarroServicesTest {

    @Test
    public void acelerarAteVelocidadeMaxima() {
        CarroService service = new CarroService();
        Carro carro = new Carro();
        // Given: um carro ligado em velocidade maxima
        service.ligar(carro);
        carro.setVelocidadeAtual(100);

        // When: acelerar
        service.acelerar(carro, 100);

        //Then: a velocidade atual tem que ser igual a maxima
        assertEquals(100, carro.getVelocidadeAtual());
    }

    @Test
    public void acelerarOCarroDesligado() {
        CarroService service = new CarroService();

        // Given: um carro desligado
        Carro carro = new Carro();

        // When: acelerar

        service.acelerar(carro, 100);
        //Then: velocidade continua zero

        assertTrue(carro.getVelocidadeAtual() == 0);
    }

    @Test
    public void mostrarEstadoAtual() {
        // Given: um carro
        CarroService service = new CarroService();
        Carro carro = new Carro("vermelho", "ford", "sedan", 1995, 100);


        // When: mostrar estado
        String estadoAtual = service.estadoAtual(carro);
        String carroString = "Carro{cor='vermelho', marca='ford', modelo='sedan', ano='1995', ligado=false, " +
                "velocidadeAtual=0, velocidadeMaxima=100}";


        //Then: imprime o carro
        assertEquals(carroString, estadoAtual);

    }

    @Test
    public void frearCarroParado(){
        CarroService service = new CarroService();
        Carro carro = new Carro();
        // Given: um carro ligado e parado
        service.ligar(carro);

        // When: frear
        service.frear(carro);

        // Then: a velocidade não diminui
        assertFalse(carro.getVelocidadeAtual() < 0);

    }


    @Test
    public void acelerarCarro(){
        CarroService service = new CarroService();
        Carro carro = new Carro();
        // Given: um carro ligado e a 20km por hora
        service.ligar(carro);
        carro.setVelocidadeAtual(20);

        // When: acelerar
        service.acelerar(carro, 100);

        // Then: velocidade igual a 30km/h
        assertEquals(30, carro.getVelocidadeAtual());

    }

}
