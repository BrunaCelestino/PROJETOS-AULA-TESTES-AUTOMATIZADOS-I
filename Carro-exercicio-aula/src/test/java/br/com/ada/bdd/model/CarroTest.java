package br.com.ada.bdd.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarroTest {

    @Test
    public void novoCarroIniciaDesligado() {
        Carro carro = new Carro();
        assertFalse(carro.isLigado());
    }


}