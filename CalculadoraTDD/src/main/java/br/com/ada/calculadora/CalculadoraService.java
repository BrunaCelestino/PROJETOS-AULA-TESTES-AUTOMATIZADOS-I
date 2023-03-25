package br.com.ada.calculadora;

import br.com.ada.calculadora.exception.NegativeNumberException;

public class CalculadoraService {
    public Integer somar(Integer a, Integer b) {
        return a + b;
    }

    public Integer subtrair(Integer a, Integer b) throws NegativeNumberException {
        if(a < b) {
            throw new NegativeNumberException("Resultado não pode ser negativo.");
        }
        return a - b;
    }

    public Integer dividir(Integer a, Integer b) {
        return a/b;
    }

    public Integer multiplicar(Integer a, Integer b) {
        return a * b;
    }
}
