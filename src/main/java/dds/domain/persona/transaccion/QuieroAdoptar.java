package dds.domain.persona.transaccion;

public class QuieroAdoptar implements Transaccion {
    final  int idTransaccion = 1;
    //CONSTRUCTOR PARA LISTA DE PERMISOS
    public QuieroAdoptar() {
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
