package com.coppel.exceptions;


import com.coppel.dto.ApiResponseDTO;
import com.coppel.dto.ResponseDTO;
import com.coppel.util.Meta;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.coppel.util.AppMessages.*;

/**
 * Clase para manejo de excepciones no controladas.
 */
@ControllerAdvice
public class AppExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(AppExceptionHandler.class.getName());

    private static final String SEPARADOR = "--------------------------------------------------";

    /*
     * Cualquier excepcion tipo NullPointer que ocurra
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> handleNullPointer(NullPointerException exception){
        ApiResponseDTO<ResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        ResponseDTO response = new ResponseDTO();

        logExcepcion(exception);

        response.setStatus((short)-3);
        response.setMensaje(ERROR);

        apiResponseDTO.setMeta(new Meta("NOT OK", 500));
        apiResponseDTO.setData(response);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * Cuando mandas un parametro con tipo de dato incorrecto
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> handleTypeMismatch(TypeMismatchException exception){
        ApiResponseDTO<ResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        ResponseDTO response = new ResponseDTO();

        response.setStatus((short)-1);
        response.setMensaje(DATA_TYPE_BAD_REQUEST_MESSAGE);

        apiResponseDTO.setMeta(new Meta("NOT OK", 400));
        apiResponseDTO.setData(response);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /*
     * Cuando una o mas regla de RequestBody no se cumple
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        ApiResponseDTO<ResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        ResponseDTO response = new ResponseDTO();
        List<String> listaErrores = new ArrayList<>();

        exception.getAllErrors().forEach(
                objectError -> listaErrores.add(
                        String.format("%s %s",
                                Objects.requireNonNull(objectError.getCodes())[1],
                                objectError.getDefaultMessage())
                ));

        response.setStatus((short)-1);
        response.setMensaje(INVALID_REQUEST);
        response.setData(listaErrores);

        apiResponseDTO.setMeta(new Meta("NOT OK", 400));
        apiResponseDTO.setData(response);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /*
     * Cuando uno o mas campos del body tienen mal formato
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        ApiResponseDTO<ResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        ResponseDTO response = new ResponseDTO();

        response.setStatus((short) -1);
        response.setMensaje(DATA_TYPE_BAD_REQUEST_MESSAGE);

        apiResponseDTO.setMeta(new Meta("NOT OK", 400));
        apiResponseDTO.setData(response);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.BAD_REQUEST);
    }

    /*
     * Cuando ocurre una excepcion que corresponde a la base de datos
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ApiResponseDTO<ResponseDTO>> handleSQL(SQLException exception){
        ApiResponseDTO<ResponseDTO> apiResponseDTO = new ApiResponseDTO<>();
        ResponseDTO response = new ResponseDTO();

        logExcepcion(exception);

        response.setStatus((short)-2);
        response.setMensaje(DB_ERROR);

        apiResponseDTO.setMeta(new Meta("NOT OK", 500));
        apiResponseDTO.setData(response);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void logExcepcion(Exception exception){
        String[] classSplit = exception.getClass().getName().split("\\.");
        String clase = classSplit[classSplit.length-1];

        LOGGER.error(SEPARADOR);
        LOGGER.error(String.format("%s: %s", clase,exception.getMessage()));
        // Obtener origen del error
        StackTraceElement[] st =
                Arrays.stream(exception.getStackTrace())
                        .filter(stackTraceElement -> stackTraceElement.getClassName().startsWith("com.coppel"))
                        .toArray(StackTraceElement[]::new);
        StackTraceElement ste;
        int stackTraceCount = Math.min(st.length, 3);
        LOGGER.error("Stack trace excepcion originada");
        for (int i = 0; i < stackTraceCount; i++) {
            ste = st[i];
            LOGGER.error(String.format("%d: [Archivo: %s | Metodo: %s | Linea: %d]", i+1, ste.getFileName(), ste.getMethodName(), ste.getLineNumber()));
        }
        LOGGER.error(SEPARADOR);
    }

    public static void logError(String error){
        LOGGER.error(error);
    }

    public static void logBadRequest(String clase, String metodo, String identificador, String mensaje) {
        LOGGER.warn(SEPARADOR);

        LOGGER.warn("Peticion no exitosa.");
        LOGGER.warn("Razon:");
        LOGGER.warn(String.format("Clase: %s | Metodo: %s | Id: %s", clase, metodo, identificador));
        LOGGER.warn(String.format("Detalle: %s", mensaje));

        LOGGER.warn(SEPARADOR);
    }
}
