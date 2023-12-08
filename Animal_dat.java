package com.example.animaltfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Animal_dat extends AppCompatActivity {

    //declaramaos las variables de los textview que vamos a usar para la descripcion del animal
    TextView nombreTextView, ubicacionTextView, presasTextView, habitatTextView, dietaTextView,
            depredadoresTextView, tamanoPromedioCamadaTextView, estiloVidaTextView, comidaFavoritaTextView,
            tipoTextView, lemaTextView, colorTextView, tipoPielTextView, velocidadMaximaTextView, esperanzaVidaTextView, pesoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_dat);

        //declaramaos las variables de los textview que vamos a usar
        nombreTextView = findViewById(R.id.nombreTextView);
        ubicacionTextView = findViewById(R.id.ubicacionTextView);
        presasTextView = findViewById(R.id.presasTextView);
        habitatTextView = findViewById(R.id.habitatTextView);
        dietaTextView = findViewById(R.id.dietaTextView);
        depredadoresTextView = findViewById(R.id.depredadoresTextView);
        tamanoPromedioCamadaTextView = findViewById(R.id.tamanoPromedioCamadaTextView);
        estiloVidaTextView = findViewById(R.id.estiloVidaTextView);
        comidaFavoritaTextView = findViewById(R.id.comidaFavoritaTextView);
        tipoTextView = findViewById(R.id.tipoTextView);
        lemaTextView = findViewById(R.id.lemaTextView);
        colorTextView = findViewById(R.id.colorTextView);
        tipoPielTextView = findViewById(R.id.tipoPielTextView);
        velocidadMaximaTextView = findViewById(R.id.velocidadMaximaTextView);
        esperanzaVidaTextView = findViewById(R.id.esperanzaVidaTextView);
        pesoTextView = findViewById(R.id.pesoTextView);

        String nombreAnimal = getIntent().getStringExtra("animal");
        nombreTextView.setText(nombreAnimal);
        //llamamos a la funcion para obtener los detalles del animal
        obtenerDetallesDelAnimal(nombreAnimal);
    }

    //creamos el menu
    private void obtenerDetallesDelAnimal(String nombreAnimal) {
        new ConsultarAPIDetalles().execute(nombreAnimal);
    }

    //clase para obtener los detalles del animal
    private class ConsultarAPIDetalles extends AsyncTask<String, Void, JsonNode> {

        @Override
        protected JsonNode doInBackground(String... nombres) {
            JsonNode detalles = null;
            try {
                // Crear URL de la API
                String apiUrl = "https://api.api-ninjas.com/v1/animals?name=" + nombres[0];
                URL url = new URL(apiUrl);

                // Crear conexión HTTP
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("X-Api-Key", "sScXAfwRYvUPYQ4NRfrtXA==YFDnGH9NvVcFGQg2");
                connection.setRequestProperty("accept", "application/json");
                // Leer respuesta
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                // Convertir respuesta JSON a JsonNode
                detalles = mapper.readTree(responseStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return detalles;
        }

        @Override
        protected void onPostExecute(JsonNode result) {
            if (result != null && result.isArray() && result.size() > 0) {
                JsonNode animal = result.get(0); // Tomar el primer resultado
                // Mostrar detalles del animal
                ubicacionTextView.setText(obtenerTextoLocations(animal.path("locations")));
                presasTextView.setText(animal.path("characteristics").path("prey").asText());
                presasTextView.setText(animal.path("characteristics").path("main_prey").asText());
                habitatTextView.setText(animal.path("characteristics").path("habitat").asText());
                dietaTextView.setText(animal.path("characteristics").path("diet").asText());
                depredadoresTextView.setText(animal.path("characteristics").path("predators").asText());
                tamanoPromedioCamadaTextView.setText(animal.path("characteristics").path("average_litter_size").asText());
                estiloVidaTextView.setText(animal.path("characteristics").path("lifestyle").asText());
                comidaFavoritaTextView.setText(animal.path("characteristics").path("favorite_food").asText());
                tipoTextView.setText(animal.path("characteristics").path("type").asText());
                lemaTextView.setText(animal.path("characteristics").path("slogan").asText());
                colorTextView.setText(animal.path("characteristics").path("color").asText());
                tipoPielTextView.setText(animal.path("characteristics").path("skin_type").asText());
                velocidadMaximaTextView.setText(animal.path("characteristics").path("top_speed").asText());
                esperanzaVidaTextView.setText(animal.path("characteristics").path("lifespan").asText());
                pesoTextView.setText(animal.path("characteristics").path("weight").asText());
            }
        }

        //metodo para obtener el texto de la ubicacion
        private String obtenerTextoLocations(JsonNode locationsNode) {
            StringBuilder locationsText = new StringBuilder();
            // Recorrer nodos de locations
            for (JsonNode location : locationsNode) {
                locationsText.append(location.asText()).append(", ");
            }
            // Eliminar última coma
            return locationsText.length() > 2 ? locationsText.substring(0, locationsText.length() - 2) : "";
        }
    }
    //metodo para cerrar sesion
    private void irCerrarSesiom(){
        Intent intent = new Intent(this, Login_reg.class);
        startActivity(intent);
    }
    //metodo para dirigirme a la busqueda de animales
    private  void irBusquedaAnimal(){
        Intent intent = new Intent(this, Busqueda_animal.class);
        startActivity(intent);
    }
    // Configurar el menú de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_animal_res, menu);
        return true;
    }

    //menu para volver atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.busqueda:
                // busqueda
                irBusquedaAnimal();
                return true;
            case R.id.PaginaPrincipal:
                // Cerrar sesión
                irCerrarSesiom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}