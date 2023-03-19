package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triangulo extends FormaGeometrica {
    private double altura;
    private double base;
}
