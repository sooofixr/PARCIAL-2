package model;

import java.sql.Timestamp;
import java.util.Date;

public class Cantante {
    private String Nombre;
    private String Nacionalidad;
    private String generoMusical;
    private int Edad;
    private String CancionReconocida;
    private int id;
    private Timestamp Registrado;

    public Cantante (String Nombre, String Nacionalidad, String generoMusical, int Edad, String CancionReconocida, int id, Timestamp Registrado) {
        this.Nombre = Nombre;
        this.Nacionalidad = Nacionalidad;
        this.generoMusical = generoMusical;
        this.Edad = Edad;
        this.CancionReconocida = CancionReconocida;
        this.id = id;
        this.Registrado = Registrado;

}
