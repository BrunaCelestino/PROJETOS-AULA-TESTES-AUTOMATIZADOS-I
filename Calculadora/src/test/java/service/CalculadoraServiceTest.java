package service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculadoraServiceTest  {

    CalculadoraService service;
    @Before
    public void setUp() {
        service = new CalculadoraService();
    }

    @Test
    public void testSomar() {
        // Given: dois inteiros
        int a = 2;
        int b = 2;

        // When: somar
        int soma =  service.somar(a, b);

        // Then: resultado deve ser igual 4
        assertEquals(4, soma);
    }

    @Test
    public void testSubtrair() {
        // Given: dois inteiros
        int a = 2;
        int b = 2;

        // When: subtrair
        int subtracao =  service.subtrair(a, b);

        // Then: resultado deve ser igual 0
        assertEquals(0, subtracao);
    }

    @Test
    public void testDividir() {
        // Given: dois inteiros
        int a = 2;
        int b = 2;

        // When: dividir
        int divisao =  service.dividir(a, b);

        // Then: resultado deve ser igual 1
        assertEquals(1, divisao);
        assertNotEquals(2, divisao);
    }

    @Test
    public void testMultiplicar() {
        // Given: dois inteiros
        int a = 2;
        int b = 2;

        // When: multiplicar
        int multiplicacao =  service.multiplicar(a, b);

        // Then: resultado deve ser igual 4
        assertEquals(4, multiplicacao);
        assertFalse(4 != multiplicacao);
    }

}