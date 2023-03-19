package service;

import model.FormaGeometrica;
import model.Quadrado;
import model.Triangulo;

public class FormaGeometricaService {

    public Double calcularArea(FormaGeometrica formaGeometrica)  {
            if (formaGeometrica instanceof Quadrado) {
                return calcularAreaQuadrado((Quadrado) formaGeometrica);

            } else if (formaGeometrica instanceof Triangulo) {
                return calcularAreaTriangulo((Triangulo) formaGeometrica);

            } else {
                return null;
            }

    }

    public Triangulo calcularTrianguloDeMenorArea(Triangulo trianguloA, Triangulo trianguloB) {
        double areaTrianguloA = calcularArea(trianguloA);
        double areaTrianguloB = calcularArea(trianguloB);

        if(areaTrianguloA < areaTrianguloB) {
            return trianguloA;
        } else  if(areaTrianguloA > areaTrianguloB) {
            return trianguloB;
        } else {
            return null;
        }
    }

    public Quadrado calcularQuadradoDeMenorArea(Quadrado quadradoA, Quadrado quadradoB) {
        double areaQuadradoA = calcularArea(quadradoA);
        double areaQuadradoB = calcularArea(quadradoB);

        if(areaQuadradoA < areaQuadradoB) {
            return quadradoA;
        } else  if(areaQuadradoA > areaQuadradoB) {
            return quadradoB;
        } else {
            return null;
        }
    }

    private Double calcularAreaQuadrado(Quadrado quadrado){
        return quadrado.getLado() * quadrado.getLado();
    }

    private Double calcularAreaTriangulo(Triangulo triangulo) {
        return triangulo.getBase() * triangulo.getAltura() / 2;
    }

}
