package com.example.animaltfg.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class RegistrosUsuarios implements Parcelable {
    //declaramos que vamios a utilizar tambien imagen
    String nombreUsuario;
    String nombreApellido;
    String telefono;
    String correoElectronico;
    String clave;
    String repetirClave;
    String imagen;

    //constructor
    public RegistrosUsuarios(String nombreUsuario, String nombreApellido, String telefono, String correoElectronico, String clave, String repetirClave, String imagen) {
        this.nombreUsuario = nombreUsuario;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.repetirClave = repetirClave;
        this.imagen = imagen;
    }

    //getter
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public String getRepetirClave() {
        return repetirClave;
    }

    public String getImagen() {
        return imagen;
    }

    //setter
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setRepetirClave(String repetirClave) {
        this.repetirClave = repetirClave;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    //ToString
    @Override
    public String toString() {
        return "RegistrosUsuarios{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", nombreApellido='" + nombreApellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", clave='" + clave + '\'' +
                ", repetirClave='" + repetirClave + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }

    //constuctor sin argumentos
    public RegistrosUsuarios() {
        // Constructor sin argumentos requerido por Firebase Realtime Database
    }


    //datos parcelables
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombreUsuario);
        dest.writeString(this.nombreApellido);
        dest.writeString(this.telefono);
        dest.writeString(this.correoElectronico);
        dest.writeString(this.clave);
        dest.writeString(this.repetirClave);
        dest.writeString(this.imagen);
    }

    public void readFromParcel(Parcel source) {
        this.nombreUsuario = source.readString();
        this.nombreApellido = source.readString();
        this.telefono = source.readString();
        this.correoElectronico = source.readString();
        this.clave = source.readString();
        this.repetirClave = source.readString();
        this.imagen = source.readString();
    }

    protected RegistrosUsuarios(Parcel in) {
        this.nombreUsuario = in.readString();
        this.nombreApellido = in.readString();
        this.telefono = in.readString();
        this.correoElectronico = in.readString();
        this.clave = in.readString();
        this.repetirClave = in.readString();
        this.imagen = in.readString();
    }

    public static final Parcelable.Creator<RegistrosUsuarios> CREATOR = new Parcelable.Creator<RegistrosUsuarios>() {
        @Override
        public RegistrosUsuarios createFromParcel(Parcel source) {
            return new RegistrosUsuarios(source);
        }

        @Override
        public RegistrosUsuarios[] newArray(int size) {
            return new RegistrosUsuarios[size];
        }
    };
}
