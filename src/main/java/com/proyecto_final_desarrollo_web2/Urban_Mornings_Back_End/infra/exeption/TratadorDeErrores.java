package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption.exeptions.EmailAlreadyExistExeption;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> handleValidationException(MethodArgumentNotValidException ex) {

        List<DatosErrorValidacion> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DatosErrorValidacion::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(EmailAlreadyExistExeption.class)
    public ResponseEntity<HashMap<String, String>> emailAlreadyExistExeption(EmailAlreadyExistExeption ex) {
        HashMap<String, String> error = new HashMap<>();

        error.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }





    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion (FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    };
}
