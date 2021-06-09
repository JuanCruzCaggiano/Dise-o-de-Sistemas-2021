package dds.domain.persona.transaccion;


public class EncontreMiMascota implements Transaccion {
    final  int idTransaccion = 5;

    @Override
    public void ejecutar(){

    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
