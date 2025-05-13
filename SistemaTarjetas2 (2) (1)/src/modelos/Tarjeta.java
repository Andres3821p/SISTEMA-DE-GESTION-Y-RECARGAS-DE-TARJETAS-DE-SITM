package modelos;

import java.io.Serializable;

public class Tarjeta implements Serializable {
    private String ip;
    private double saldo;

    public Tarjeta(String ip) {
        this.ip = ip;
        this.saldo = 0.0;
    }

    public String getIp() {
        return ip;
    }

    public double getSaldo() {
        return saldo;
    }

    // Método para recargar el saldo de la tarjeta, con validación
    public boolean recargar(double monto) {
        if (monto > 0) {
            saldo += monto;
            return true;
        }
        return false; // No permite recargar si el monto es negativo o cero
    }

    // Método para retirar dinero de la tarjeta, con validación
    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false; // No permite retirar si el saldo es insuficiente
    }

    @Override
    public String toString() {
        return "IP: " + ip + " | Saldo: $" + String.format("%.2f", saldo);
    }

    // Sobrescribir equals y hashCode para comparar tarjetas por IP
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarjeta tarjeta = (Tarjeta) obj;
        return ip.equals(tarjeta.ip);
    }

    @Override
    public int hashCode() {
        return ip.hashCode();
    }
}
