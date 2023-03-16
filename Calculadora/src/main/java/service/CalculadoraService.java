package service;


import exception.InvalidObjectException;
import model.Quadrado;
import model.Triangulo;

public class CalculadoraService {

    public int somar(int a, int b) {
        return a+b;
    }

    public int subtrair(int a, int b) {
        return a-b;
    }

    public int dividir(int a, int b) {
        return a/b;
    }

    public int multiplicar(int a, int b) {
        return a*b;
    }

    public double calcularArea(Object object) throws InvalidObjectException {
        if (object instanceof Quadrado) {
            Quadrado quadrado = (Quadrado) object;
            return quadrado.getLado() * quadrado.getLado();
        } else if (object instanceof Triangulo) {
            Triangulo triangulo = (Triangulo) object;
            return triangulo.getBase() * triangulo.getAltura() / 2;
        } else {
            throw new InvalidObjectException("Objeto informado é inválido");
        }
    }
}
