package dds.domain.persona.transaccion;

public class SolicitarAdopcion implements Transaccion {
    final  int idTransaccion = 10;
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public SolicitarAdopcion() {
    }

    @Override
    public void ejecutar()  {
        //TODO ADOPTAR
    }

    @Override
    public int getIdTransaccion() {
        return idTransaccion;
    }
}
