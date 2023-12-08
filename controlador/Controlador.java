package com.example.animaltfg.controlador;

import android.content.Context;
import android.widget.Toast;

public class Controlador {
    //validar campos vacios
    public static boolean registroVerficiar(Context context, String usuario, String nombreApellido, String telefono, String correoElectronico, String clave, String repetirClave) {
        boolean comprobar;
        //comprobaciones del registro xd
        //comprobar que todos los campos no esten vacios6
        if(!usuario.isEmpty()){
            if(!nombreApellido.isEmpty()){
                if(!telefono.isEmpty()){
                    if(!correoElectronico.isEmpty()){
                        if(!clave.isEmpty()){
                            if(!repetirClave.isEmpty()){
                                //AHORA PREGUNTAR COSAS COMO LA REPETIRCLAVE TIENE QUE SER COMO CLAVE YA SEA DE DISTANCIA ETC
                                if(clave.toString().equals(repetirClave.toString())){
                                    //que tenga un minimo de 8 letras si tiene menos pues error es x seguridad
                                    if(clave.length()>7 && clave.length()<16 ){
                                        //comprobar que el email sea valido
                                        if(correoElectronico.contains("@") && correoElectronico.contains(".")){
                                            //comprobar que el telefono sea valido
                                            if(telefono.length()==9){
                                                //comprobar que lleve una mayuscula y minuscula y un numero  el password
                                                if(clave.matches(".*[A-Z].*") && clave.matches(".*[a-z].*") && clave.matches(".*[0-9].*")){
                                                    //comprobar que el telefono sea solo numeros
                                                    if(telefono.matches("[0-9]*")){
                                                        comprobar=true;
                                                    }else{
                                                        Toast.makeText(context, "The PHONE must be only numbers", Toast.LENGTH_SHORT).show();
                                                        comprobar=false;
                                                    }
                                                }else {
                                                    Toast.makeText(context, "The KEY must contain a capital letter, a lowercase letter and a number", Toast.LENGTH_SHORT).show();
                                                    comprobar=false;
                                                }
                                            }else{
                                                Toast.makeText(context, "The PHONE must be 9 digits", Toast.LENGTH_SHORT).show();
                                                comprobar=false;
                                            }
                                        }else{
                                            Toast.makeText(context, "The EMAIL is not valid", Toast.LENGTH_SHORT).show();
                                            comprobar=false;
                                        }
                                    }else{
                                        Toast.makeText(context, "The KEY is very short or very long minino 8 to 16 letters", Toast.LENGTH_SHORT).show();
                                        comprobar=false;
                                    }

                                }else{
                                    Toast.makeText(context, "The REPEAT KEY must be the same as the key", Toast.LENGTH_SHORT).show();
                                    comprobar=false;
                                }
                            }else {
                                Toast.makeText(context, "The REPEAT KEY field must not be empty", Toast.LENGTH_SHORT).show();
                                comprobar=false;
                            }
                        }else {
                            Toast.makeText(context, "The KEY field must not be empty", Toast.LENGTH_SHORT).show();
                            comprobar=false;
                        }
                    }else {
                        Toast.makeText(context, "The EMAIL field must not be empty", Toast.LENGTH_SHORT).show();
                        comprobar=false;
                    }
                }else {
                    Toast.makeText(context, "The PHONE field must not be empty", Toast.LENGTH_SHORT).show();
                    comprobar=false;
                }
            }else {
                Toast.makeText(context, "The NAME AND LAST NAME field must not be empty", Toast.LENGTH_SHORT).show();
                comprobar=false;
            }
        }else{
            Toast.makeText(context, "The USER field must not be empty", Toast.LENGTH_SHORT).show();
            comprobar=false;
        }
        return  comprobar;
    }
    //verificar login campos vacios
    public static boolean loginVerficiar(Context context, String usuario, String clave) {
        boolean comprobar;
        //comprobar campos vacios
        if(!usuario.isEmpty()){
            if(!clave.isEmpty()){
                comprobar=true;
            }else{
                Toast.makeText(context, "The KEY field must not be empty", Toast.LENGTH_SHORT).show();
                comprobar=false;
            }
        }else{
            Toast.makeText(context, "The USER field must not be empty", Toast.LENGTH_SHORT).show();
            comprobar=false;
        }
        return  comprobar;
    }

    //controlaremos el buscador para que no este vacio
    public static boolean comprobacionBuscador(Context context, String nombBusq){
        boolean boolbusca;
        //comprobar campos vacios

        if(!nombBusq.isEmpty()){
            boolbusca=true;

        }else{
            Toast.makeText(context, "the NAME of the ANIMAL should NOT be empty in the search engine", Toast.LENGTH_SHORT).show();
            boolbusca=false;
        }
        return boolbusca;
    }

    //Controlador Modificar Usuario
    public static boolean comprobarUsuarioModi(Context context, String usuario, String nombreApellido, String telefono, String correoElectronico, String clave, String repetirClave){
        boolean boolModi;
        //comprobar campos vacios
        //comprobar que todos los campos no esten vacios6
        if(!usuario.isEmpty()){
            if(!nombreApellido.isEmpty()){
                if(!telefono.isEmpty()){
                    if(!correoElectronico.isEmpty()){
                        if(!clave.isEmpty()){
                            if(!repetirClave.isEmpty()){
                                //AHORA PREGUNTAR COSAS COMO LA REPETIRCLAVE TIENE QUE SER COMO CLAVE YA SEA DE DISTANCIA ETC
                                if(clave.toString().equals(repetirClave.toString())){
                                    //que tenga un minimo de 8 letras si tiene menos pues error es x seguridad
                                    if(clave.length()>7 && clave.length()<16 ){
                                        //comprobar que el email sea valido
                                        if(correoElectronico.contains("@") && correoElectronico.contains(".")){
                                            //comprobar que el telefono sea valido
                                            if(telefono.length()==9){
                                                //comprobar que lleve una mayuscula y minuscula y un numero  el password
                                                if(clave.matches(".*[A-Z].*") && clave.matches(".*[a-z].*") && clave.matches(".*[0-9].*")){
                                                    //comprobar que el telefono sea solo numeros
                                                    if(telefono.matches("[0-9]*")){
                                                        boolModi=true;
                                                    }else{
                                                        Toast.makeText(context, "The PHONE must be only numbers", Toast.LENGTH_SHORT).show();
                                                        boolModi=false;
                                                    }
                                                }else {
                                                    Toast.makeText(context, "The KEY must contain a capital letter, a lowercase letter and a number", Toast.LENGTH_SHORT).show();
                                                    boolModi=false;
                                                }
                                            }else{
                                                Toast.makeText(context, "The PHONE must be 9 digits", Toast.LENGTH_SHORT).show();
                                                boolModi=false;
                                            }
                                        }else{
                                            Toast.makeText(context, "The EMAIL is not valid", Toast.LENGTH_SHORT).show();
                                            boolModi=false;
                                        }
                                    }else{
                                        Toast.makeText(context, "The KEY is very short or very long minino 8 to 16 letters", Toast.LENGTH_SHORT).show();
                                        boolModi=false;
                                    }

                                }else{
                                    Toast.makeText(context, "The REPEAT KEY must be the same as the key", Toast.LENGTH_SHORT).show();
                                    boolModi=false;
                                }
                            }else {
                                Toast.makeText(context, "The REPEAT KEY field must not be empty", Toast.LENGTH_SHORT).show();
                                boolModi=false;
                            }
                        }else {
                            Toast.makeText(context, "The KEY field must not be empty", Toast.LENGTH_SHORT).show();
                            boolModi=false;
                        }
                    }else {
                        Toast.makeText(context, "The EMAIL field must not be empty", Toast.LENGTH_SHORT).show();
                        boolModi=false;
                    }
                }else {
                    Toast.makeText(context, "The PHONE field must not be empty", Toast.LENGTH_SHORT).show();
                    boolModi=false;
                }
            }else {
                Toast.makeText(context, "The NAME AND LAST NAME field must not be empty", Toast.LENGTH_SHORT).show();
                boolModi=false;
            }
        }else{
            Toast.makeText(context, "The USER field must not be empty", Toast.LENGTH_SHORT).show();
            boolModi=false;
        }
        return boolModi;
    }

}
