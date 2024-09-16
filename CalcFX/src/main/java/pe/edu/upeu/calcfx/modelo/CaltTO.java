package pe.edu.upeu.calcfx.modelo;

public class CaltTO {
    private String num1;
    private String num2;
    private char operador;
    private String resultado;

    @Override
    public String toString() {
        return "CaltTO{" +
                "num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", operador=" + operador +
                ", resultado='" + resultado + '\'' +
                '}';
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
