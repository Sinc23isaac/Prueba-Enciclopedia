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
//importe base datos firebase
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login_reg extends AppCompatActivity {


    //datos a llamar del login reg
    Button btnIniciar, btnRegistrar;
    EditText text1;
    EditText text2;
    //lista de usuarios
    private List<String> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_reg);
        //inicializamos el usuario y la clave para ingresar a nuestra pagina principal
        text1=findViewById(R.id.editusuariotext);
        text2=findViewById(R.id.edittextclave);
        btnIniciar=findViewById(R.id.btnIniciaApp);


        //hago lo de los botones primero cambiar a la ventana registro
        btnRegistrar=(Button)findViewById(R.id.btnRegistarse);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enviar a la vista registrarse
                Intent cambiarRegistro=new Intent(Login_reg.this,Registro.class);
                //inciar
                startActivity(cambiarRegistro);
            }
        });
        //boton inciiar
        ButtonInicioSesion();
        // Obtener la lista de usuarios del Intent
        Intent intent = getIntent();
        if (intent != null) {
            //recuperar una lista de usuarios (cada uno representado como una cadena de texto)
            //en este caso recuperamos el usuario logueado y lo guardamos en la lista
            List<String> listaUsuarios = intent.getStringArrayListExtra("USUARIO_LOGUEADO");

        }
    }

    //funcion para el boton de iniciar sesion
    private void ButtonInicioSesion(){
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //antes preguntamos si esta el usuario en la bd es decir si existe y si la clave esta bien xd
                String usuario=text1.getText().toString();
                String clave=text2.getText().toString();
                //preguntamos  que no este vacio estos campos que esta en el controlador es decir llamar a ese metodo
                boolean registarLog = Controlador.loginVerficiar(getApplicationContext(), usuario, clave);
                if(registarLog){
                    //ahora ingresamos en la base de datos de firebase
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference("RegistrosUsuarios");

                    //creamos el objeto para leer la base de datos
                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //creamos una variable para saber si el usuario existe o no
                            boolean res=false;
                            //mirar los hijos y los lee
                            //para recorrer cada uno de los hijos y ejecutar el código dentro del bloque
                            // del bucle para cada hijo.
                            for (DataSnapshot x:snapshot.getChildren()){
                                //preguntamos si el usuario y la clave son iguales a los que estan en la bd y si existe
                                if(usuario.equalsIgnoreCase(x.child("nombreUsuario").getValue().toString())){
                                    //ahora preguntamos si la clave es la correcta que esta en la bd y si existe
                                    String contraseñaAlmacenada = x.child("clave").getValue().toString();
                                    if (clave.equals(contraseñaAlmacenada)) {
                                        res=true;
                                        //Toast.makeText(Login_reg.this, "welcome :"+usuario+"\n", Toast.LENGTH_SHORT).show();

                                        // Agregar el nombre de usuario a la lista
                                        listaUsuarios.add(usuario);

                                        //cambiamos de vista al inicio ya de mi pagina al a ver ingresado
                                        Intent cambioInicioPer=new Intent(Login_reg.this, Perfil_usa.class);

                                        cambioInicioPer.putExtra("USUARIO_LOGUEADO", usuario); // usuario es el nombre de usuario obtenido del login

                                        //inicar
                                        startActivity(cambioInicioPer);
                                        break;
                                    }else{
                                        //mensaje de error por que no es correcta la clave
                                        Toast.makeText(Login_reg.this, "The password is incorrect\n", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            if(res==false){
                                //mensaje de error por si no encontro el usuario en firebase
                                Toast.makeText(Login_reg.this, "This user does not exist\n", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}