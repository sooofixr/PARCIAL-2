package model;

import java.sql.Timestamp;

public class Cantante {
    private String nombre;
    private String nacionalidad;
    private String generomusical;
    private int edad;
    private String cancionreconocida;
    private int id;
    private Timestamp registrado;

    public Cantante(String nombre, String nacionalidad, String generomusical, int edad, String cancionreconocida, int id, Timestamp registrado) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.generomusical = generomusical;
        this.edad = edad;
        this.cancionreconocida = cancionreconocida;
        this.id = id;
        this.registrado = registrado;

    }

    public Cantante(String nombre, String nacionalidad, String generomusical, int edad, String cancionreconocida) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.generomusical = generomusical;
        this.edad = edad;
        this.cancionreconocida = cancionreconocida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGeneromusical() {
        return generomusical;
    }

    public void setGeneromusical(String generomusical) {
        this.generomusical = generomusical;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCancionreconocida() {
        return cancionreconocida;
    }

    public void setCancionreconocida(String cancionreconocida) {
        this.cancionreconocida = cancionreconocida;
    }

    public Timestamp getRegistrado() {
        return registrado;
    }

    public void setRegistrado(Timestamp registrado) {
        this.registrado = registrado;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "Nombre=" + nombre +
                ", Nacionalidad='" + nacionalidad + '\'' +
                ", Genero musical=" + generomusical +
                ", Edad='" + edad + '\'' +
                ", Cancion reconocida=" + cancionreconocida +
                ", id=" + id +
                ", Registrado=" + registrado +
                '}';
    }
}


