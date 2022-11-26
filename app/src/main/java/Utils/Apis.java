package Utils;


public class Apis {

    public static final String URL_001="http://104.248.201.63:8000/";

    public static RecordatorioService getRecordatorioService(){

        return Recordatorio.getRecordatorio(URL_001).create(RecordatorioService.class);
    }

    public static UsuarioService getUsuarioService(){

        return Usuario.getUsuario(URL_001).create(UsuarioService.class);
    }

    public static NotasServices getNotasService(){
        return Notas.getNotas(URL_001).create(NotasServices.class);
    }
}
