package com.diplomado2.semana2.api.response;

public class ResponseRegistro {
    private boolean success;
    private String mensaje;
    private String id_usuario;
    private String tokenSesion;

    public boolean isSuccess() {
        return success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getTokenSesion() {
        return tokenSesion;
    }
}
