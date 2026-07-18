package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.infra.exeption.exeptions;

public class EmailAlreadyExistExeption extends RuntimeException {
    public EmailAlreadyExistExeption(String mensaje) {
        super(mensaje);
    }
}
