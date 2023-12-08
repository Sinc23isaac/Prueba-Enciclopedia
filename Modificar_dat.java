package com.example.animaltfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.animaltfg.controlador.Controlador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Modificar_dat extends AppCompatActivity {

    Button btnmodificar;
    //llamamos a los editext
    EditText usuario;
    EditText nombreApellido;
    EditText correo;
    EditText telefono;
    EditText clave;
    EditText repetirClave;
    Button btnregresarModi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_dat);


        //boton modificar
        btnmodificar=(Button)findViewById(R.id.btnModi);
        //llamamos a esos campos y le damos el nombre de la variable
        usuario=findViewById(R.id.modifUsa);
        nombreApellido=findViewById(R.id.modiNombre);
        correo=findViewById(R.id.modiCorreo);
        telefono=findViewById(R.id.moditelefono);
        clave=findViewById(R.id.modiclave);
        repetirClave=findViewById(R.id.modirepetir);


        // Obtener el nombre de usuario pasado desde la actividad anterior
        Intent intent = getIntent();


        //primero colocaremos los datos que ya estan en la base de datos
        if (intent.hasExtra("USUARIO_LOGUEADO")) {
            String usuarioLogueado = intent.getStringExtra("USUARIO_LOGUEADO");

            //prueba
            //conectamos a la abvse de datos
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("RegistrosUsuarios");
            //a ver si esta dentro de la base de datos
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean res=false;
                    //recorreremos la base de datos
                    for(DataSnapshot x:snapshot.getChildren()){
                        //preguntar si existe ese usuario en nuestra base de datos
                        if(usuarioLogueado.equalsIgnoreCase(x.child("nombreUsuario").getValue().toString())){
                            res=true;
                            //si existe agregamos los datos que tenemos en la base de datos de ese usuario en todos nuestros editext
                            String usu=x.child("nombreUsuario").getValue().toString();
                            usuario.setText(usu);
                            String nom=x.child("nombreApellido").getValue().toString();
                            nombreApellido.setText(nom);
                            String tel=x.child("telefono").getValue().toString();
                            telefono.setText(tel);
                            String cor=x.child("correoElectronico").getValue().toString();
                            correo.setText(cor);
                            String cla=x.child("clave").getValue().toString();
                            clave.setText(cla);
                            String rep=x.child("repetirClave").getValue().toString();
                            repetirClave.setText(rep);
                            break;

                        }
                        if(res==false){
                            //si no existe el usuario
                            //mensaje
                            //Toast.makeText(Modificar_dat.this, "no hay ", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else{
            //si no existe el usuario
            //mensaje
            Toast.makeText(Modificar_dat.this, "no hay ", Toast.LENGTH_SHORT).show();
        }

        //aqui esta que es lo que va a pasar cuando le demos click al boton modificar
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtener el nombre de usuario pasado desde la actividad anterior
                if (intent.hasExtra("USUARIO_LOGUEADO")) {
                    //obtenemos el usuario logueado
                    String usuarioLogueado = intent.getStringExtra("USUARIO_LOGUEADO");
                    //cambiamos los datos a string para poderlos modificar
                    String usu = usuario.getText().toString();
                    String nom = nombreApellido.getText().toString();
                    String tel = telefono.getText().toString();
                    String cor = correo.getText().toString();
                    String cla = clave.getText().toString();
                    String rep = repetirClave.getText().toString();

                    //Pondremos el controlador de errores para que no haya errores  al momento de modificar
                    boolean ModiDate = Controlador.registroVerficiar(getApplicationContext(), usu, nom, tel, cor, cla, rep);
                    //si no hay errores
                    if (ModiDate) {



                    //primero conectamos a la base de datos
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("RegistrosUsuarios");

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //VALIDANDO QUE NOSE REPITA EL NOMBRE XD
                            boolean resu = false;
                            //revisando la bd de firebase
                            for (DataSnapshot x : snapshot.getChildren()) {
                                //preguntar si existe ese usuario en nuestra base de datos
                                if (usuarioLogueado.equalsIgnoreCase(x.child("nombreUsuario").getValue().toString())) {
                                    resu = true;
                                    //se hace los cambios en la base de datos
                                    x.getRef().child("nombreApellido").setValue(nom);
                                    x.getRef().child("telefono").setValue(tel);
                                    x.getRef().child("correoElectronico").setValue(cor);
                                    x.getRef().child("clave").setValue(cla);
                                    x.getRef().child("repetirClave").setValue(rep);
                                    //nos saldremos e iremos a la actividad de inicio puedo quitar esto xd
                                    Intent vistainicio = new Intent(Modificar_dat.this, Perfil_usa.class);
                                    vistainicio.putExtra("USUARIO_LOGUEADO", usuarioLogueado);

                                    startActivity(vistainicio);


                                    Toast.makeText(Modificar_dat.this, "the user(" + usuarioLogueado + ") It has already been MODIFIED", Toast.LENGTH_SHORT).show();
                                    //saldra del bucle xd
                                    break;

                                }
                            }
                            //pues si sigue falso no se podra modificar
                            if (resu == false) {
                                Toast.makeText(Modificar_dat.this, "the user(" + usuarioLogueado + ") NOTHING HAS BEEN MODIFIED", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
            }
        });

        //boton regresar
        btnregresarModi=(Button)findViewById(R.id.btnPerfilVuel);
        btnregresarModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Regresar a la actividad "Perfil_usa"
                Intent intent = new Intent(Modificar_dat.this, Perfil_usa.class);
                // Obtener el nombre de usuario pasado desde la actividad anterior
                Intent intentActual = getIntent();
                if (intentActual.hasExtra("USUARIO_LOGUEADO")) {
                    String usuarioLogueado = intentActual.getStringExtra("USUARIO_LOGUEADO");
                    // Poner el nombre de usuario en el intent para la actividad "Perfil_usa"
                    intent.putExtra("USUARIO_LOGUEADO", usuarioLogueado);
                    startActivity(intent);
                }
            }
        });

    }

}