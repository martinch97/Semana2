package com.diplomado2.semana2.api.response;

public class ResponseLogin {
    private boolean success;
    private String mensaje;
    private String id_usuario;
    private String nombres;
    private String apellidos;
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

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTokenSesion() {
        return tokenSesion;
    }
}
