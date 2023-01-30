package com.coppel.util;

/**
 * AppMessages
 */
public class AppMessages {

    private AppMessages() {
        throw new IllegalStateException("No existe un constructor para la clase AppMessages");
    }

    public static final String CLIENT_ERROR = "Ocurrió un error al consumir el servicio.";
    public static final String ERROR = "Ocurrió un error inesperado.";
    public static final String DB_ERROR = "Ocurrió un error con la conexión a la base de datos";
    public static final String UNAUTHORISED_MESSAGE = "Usted no está autorizado para acceder este recurso.";
    public static final String DATA_TYPE_BAD_REQUEST_MESSAGE = "Verifique los datos enviados.";
    public static final String INVALID_REQUEST = "Uno o más campos son inválidos.";

}
