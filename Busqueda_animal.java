package com.example.animaltfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animaltfg.controlador.Controlador;
import com.example.animaltfg.modelo.AnimalDatos;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Busqueda_animal extends AppCompatActivity {
    //datos a llamar
    EditText editTextAnimalBusqueda;
    ListView listViewAnimales;
    Button btnBusqueda;
    Button btnRegresar;
    String apiKey = "sScXAfwRYvUPYQ4NRfrtXA==YFDnGH9NvVcFGQg2";//API KEY de mi cuenta xd
    ArrayAdapter<AnimalDatos> adapter;
    ArrayList<AnimalDatos> listaAnimales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_animal);

        //datos a llamar por id en el xml
        btnRegresar = findViewById(R.id.btnVolverPerfil);
        editTextAnimalBusqueda = findViewById(R.id.editextAnimal);
        listViewAnimales = findViewById(R.id.listviewAnimal);
        btnBusqueda = findViewById(R.id.btnBusq);
        listaAnimales = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAnimales);
        listViewAnimales.setAdapter(adapter);

        //boton para volver al perfil
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Busqueda_animal.this, Perfil_usa.class);
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


        //boton para buscar el animal
        btnBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String animal = editTextAnimalBusqueda.getText().toString().trim();
                //llamar al controlador para que el campo no este vacio
                boolean busquedaContr = Controlador.comprobacionBuscador(getApplicationContext(),animal);
                if(busquedaContr){
                    String apiUrl = "https://api.api-ninjas.com/v1/animals?name=" + animal;
                    new ConsultarAPI().execute(apiUrl);

                }
            }
        });
        //aqui iria la parte de listaview para que cuando clikeee esa lista cambie de vista o de ventana
        listViewAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posi, long lang) {
                //obtener los datos del animal seleccionado de la lista
                AnimalDatos animalSeleccionado = listaAnimales.get(posi);

                //cambiamos de ventnan
                Intent intent = new Intent(Busqueda_animal.this, Animal_dat.class);
                //enviamos los datos del animal a la otra ventana
                intent.putExtra("animal", animalSeleccionado.getNombre());

                startActivity(intent);//iniciamos la actividad

            }
        });
    }

    private class ConsultarAPI extends AsyncTask<String, Void, ArrayList<AnimalDatos>> {

        @Override
        protected ArrayList<AnimalDatos> doInBackground(String... urls) {
            ArrayList<AnimalDatos> resultados = new ArrayList<>();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("X-Api-Key", apiKey);
                connection.setRequestProperty("accept", "application/json");

                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);

                // Procesar la respuesta y obtener los datos relevantes
                resultados = procesarDatos(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultados;
        }

        @Override
        protected void onPostExecute(ArrayList<AnimalDatos> result) {
            if (result.isEmpty()) {
                // Si no se encontraron resultados, muestra un mensaje
                Toast.makeText(Busqueda_animal.this, "That animal does not exist or you have written it wrong", Toast.LENGTH_SHORT).show();
            } else {
                // Si se encontraron resultados, actualiza la lista y muestra los datos en el ListView
                listaAnimales.clear();
                listaAnimales.addAll(result);
                adapter.notifyDataSetChanged();

            }
        }

        private ArrayList<AnimalDatos> procesarDatos(JsonNode root) {
            ArrayList<AnimalDatos> animales = new ArrayList<>();

            // Verificar si la respuesta tiene datos
            if (root.isArray() && root.size() > 0) {
                for (JsonNode animalNode : root) {
                    String nombre = animalNode.path("name").asText();
                    String ubicacion = animalNode.path("locations").get(0).asText();
                    String presas = animalNode.path("characteristics").path("prey").asText();
                    String habitat = animalNode.path("characteristics").path("habitat").asText();
                    String dieta = animalNode.path("characteristics").path("diet").asText();

                    AnimalDatos animal = new AnimalDatos(nombre, ubicacion, presas, habitat, dieta);
                    animales.add(animal);
                }
            }

            return animales;
        }
    }
}