package service;

import model.Quadrado;
import model.Triangulo;
import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;


public class FormaGeometricaServiceTest {

    public static FormaGeometricaService service;
    private static final double DELTA = 1e-15;


    @Before
    public void setUp() {
        service = new FormaGeometricaService();
    }

    @Test
    public void testCalcularAreaQuadrado() {
        //Given: um quadrado
        Quadrado quadrado = new Quadrado(10);

        //When: calcular a área do quadrado
        Double area = service.calcularArea(quadrado);

        //Then: retorna sua área
        assertNotNull(area);
        assertTrue(100 == area);
        assertEquals(100, area, DELTA);
    }

    @Test
    public void testCalcularAreaTriangulo() {
        //Given: um triângulo
        Triangulo triangulo = new Triangulo(20, 15);

        //When: calcular a área do triângulo
        Double area = service.calcularArea(triangulo);

        //Then: retorna sua área
        assertNotNull(area);
        assertEquals(150, area, DELTA);
    }

    @Test
    public void testCalcularTrianguloDeMenorArea() {
        //Given: dois triângulos diferentes
        Triangulo trianguloA = new Triangulo(20, 10);
        Triangulo trianguloB = new Triangulo(20, 15);

        //When: calcular as areas dos triângulos
        Triangulo trianguloDeMenorArea = service.calcularTrianguloDeMenorArea(trianguloA,trianguloB);

        //Then: retornar o triângulo de menor área
        assertNotNull(trianguloDeMenorArea);
        assertNotSame(trianguloB, trianguloDeMenorArea);
        assertSame(trianguloA, trianguloDeMenorArea);
    }

    @Test
    public void testCalcularQuadradoDeMenorArea() {
        //Given: dois quadrados diferentes
        Quadrado quadradoA = new Quadrado(10);
        Quadrado quadradoB = new Quadrado(15);

        //When: calcular as areas dos quadrados
        Quadrado quadradoDeMenorArea = service.calcularQuadradoDeMenorArea(quadradoA, quadradoB);

        //Then: retornar o quadrado de menor área
        assertNotNull(quadradoDeMenorArea);
        assertNotSame(quadradoB, quadradoDeMenorArea);
        assertSame(quadradoA, quadradoDeMenorArea);
    }

    @Test
    public void testCalcularTrianguloDeMenorAreaDadoDoisTriangulosIguais() {
        //Given: dois triângulos iguais
        Triangulo trianguloA = new Triangulo(20, 10);
        Triangulo trianguloB = new Triangulo(20, 10);

        //When: calcular as areas dos triângulos
        Triangulo trianguloDeMenorArea = service.calcularTrianguloDeMenorArea(trianguloA,trianguloB);

        //Then: retornar nulo
        assertNull(trianguloDeMenorArea);
    }

    @Test
    public void testCalcularQuadradoDeMenorAreaDadoDoisQuadradosIguais() {
        //Given: dois quadrados iguais
        Quadrado quadradoA = new Quadrado(10);
        Quadrado quadradoB = new Quadrado(10);

        //When: calcular as areas dos quadrados
        Quadrado quadradoDeMenorArea = service.calcularQuadradoDeMenorArea(quadradoA, quadradoB);

        //Then: retornar nulo
        assertNull(quadradoDeMenorArea);

    }
}