package dds.domain.persona.transaccion;

public class DarEnAdopcion implements Transaccion {
    final  int idTransaccion = 3;

    @Override
    public void ejecutar(){

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
