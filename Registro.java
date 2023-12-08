package com.example.animaltfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animaltfg.controlador.Controlador;
import com.example.animaltfg.modelo.RegistrosUsuarios;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registro extends  AppCompatActivity  {

    //llamamos a los datos que tenemos en nuestro xml
    private Button btnGuardar;
    private EditText edittextNombreUsuario;
    private EditText edittextNombreApellido;
    private EditText edittexTelefono;
    private EditText ediCorreoElectronico;
    private EditText claveEdt;
    private EditText repetirClaveEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //almacenan y se asigna una variable de la parte del diseño xml
        edittextNombreUsuario = findViewById(R.id.editusuario);
        edittextNombreApellido=findViewById(R.id.editNombre);
        edittexTelefono=findViewById(R.id.edittelefono);
        ediCorreoElectronico=findViewById(R.id.editCorreo);
        claveEdt=findViewById(R.id.editclave);
        repetirClaveEdt=findViewById(R.id.editrepetir);

        //iniciamos cuando le damos al boton de registrarse
        btnGuardar = findViewById(R.id.btnregistrarseusu);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cambiamos los datos que se ingresadon en los campos de texto a stirng
                String usuarioId =edittextNombreUsuario.getText().toString();
                String nombreApellido = edittextNombreApellido.getText().toString();
                String telefono=edittexTelefono.getText().toString();
                String correo=ediCorreoElectronico.getText().toString();
                String clave=claveEdt.getText().toString();
                String repetirClave=repetirClaveEdt.getText().toString();
                //validamos los datos que se ingresaron en los campos de texto para que no esten vacios y que sean correctos
                boolean registroValido= Controlador.registroVerficiar(getApplicationContext(),usuarioId,nombreApellido,telefono,correo,clave,repetirClave);
                //preguntamos si estan vacios los campos
                if(registroValido){
                    //conectamos a la base de datos
                    FirebaseDatabase db=FirebaseDatabase.getInstance();
                    //referencia al elemento registrarse
                    DatabaseReference drebf=db.getReference().child("RegistrosUsuarios");
                    //ahora el evento principal para registrarme
                    drebf.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           /* //validamos datos para que entre el 1ro usuario
                            RegistrosUsuarios usuario=new RegistrosUsuarios(usuarioId,nombreApellido,telefono,correo,clave,repetirClave,"");
                            //ingresmos datos firebase
                            drebf.push().setValue(usuario);
                            //mensaje de registro exitoso
                            Toast.makeText(Registro.this, "Successful registration", Toast.LENGTH_SHORT).show();
*/
                            //validaremos que el usuario sea unico
                            boolean resp=false;
                            //VALIDANDO QUE NOSE REPITA EL NOMBRE del usuario y que sea unico
                            for(DataSnapshot x:snapshot.getChildren()){
                                //si el usuario ya existe no se registrara
                                if(x.child("nombreUsuario").getValue().toString().equalsIgnoreCase(usuarioId)){
                                    Toast.makeText(Registro.this, "User already exists", Toast.LENGTH_SHORT).show();
                                    resp =true;
                                    break;
                                }
                            }

                            if(resp==false){
                                //si no existe el usuario entrara direcatmente aqui y se registrara
                                RegistrosUsuarios usuario=new RegistrosUsuarios(usuarioId,nombreApellido,telefono,correo,clave,repetirClave,"");
                                //ingresamos los datos en la bse de datos
                                drebf.push().setValue(usuario);
                                Toast.makeText(Registro.this, "Successful registration", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            //si no se puede conectar a la base de datos
                            Toast.makeText(Registro.this, "connection error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //NO TOCAR PERFECTO EXEPTO SI SE BORRARN TOODS LOS USUARIOS y hay que agregar desde 0
                }
            }
        });
    }
}