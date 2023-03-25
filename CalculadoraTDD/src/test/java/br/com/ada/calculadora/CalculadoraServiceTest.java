package br.com.ada.calculadora;

import br.com.ada.calculadora.exception.NegativeNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraServiceTest {

    @Test
    void somarTest() {
        // GIVEN
        CalculadoraService calculadoraService = new CalculadoraService();
        Integer a = 10;
        Integer b = 10;

        // WHEN
        Integer expectativa = a + b;
        Integer atual = calculadoraService.somar(a, b);

        // THEN
        assertEquals(expectativa, atual);
    }

    @Test
    void subtrairTest() throws NegativeNumberException {
        // GIVEN
        CalculadoraService calculadoraService = new CalculadoraService();
        Integer a = 10;
        Integer b = 1;

        // WHEN
        Integer expectativa = a - b;
        Integer atual = calculadoraService.subtrair(a, b);

        // THEN
        assertEquals(expectativa, atual);
    }

    @Test
    void dividirTest() {
        // GIVEN
        CalculadoraService calculadoraService = new CalculadoraService();
        Integer a = 10;
        Integer b = 1;

        // WHEN
        Integer expectativa = a / b;
        Integer atual = calculadoraService.dividir(a, b);

        // THEN
        assertEquals(expectativa, atual);
    }

    @Test
    void multiplicarTest() {
        // GIVEN
        CalculadoraService calculadoraService = new CalculadoraService();
        Integer a = 10;
        Integer b = 2;

        // WHEN
        Integer expectativa = a * b;
        Integer atual = calculadoraService.multiplicar(a, b);

        // THEN
        assertEquals(expectativa, atual);
    }

    @Test
    void subtrairComResultadoNegativoTest() {
        // GIVEN
        CalculadoraService calculadoraService = new CalculadoraService();


        // WHEN
        Integer a = 1;
        Integer b = 10;

        // THEN
        assertThrows(NegativeNumberException.class, () -> calculadoraService.subtrair(a, b));
    }
}
