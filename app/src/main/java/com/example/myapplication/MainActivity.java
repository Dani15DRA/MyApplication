package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean isTextChanged = false;
    private int currentColorIndex = 0;
    private int currentStyleIndex = 0;
    private final int[] colors = {
            Color.BLACK,
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            Color.YELLOW
    };
    private final int[] styles = {
            Typeface.NORMAL,
            Typeface.BOLD,
            Typeface.ITALIC,
            Typeface.BOLD_ITALIC
    };
    private final int[] backgroundColors = {
            Color.WHITE,
            Color.LTGRAY,
            Color.CYAN,
            Color.MAGENTA,
            Color.GRAY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a las vistas
        TextView textView = findViewById(R.id.texto);
        EditText inputNombre = findViewById(R.id.inputNombre);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        Button textButton = findViewById(R.id.boton);
        Button colorButton = findViewById(R.id.botonColor);
        Button styleButton = findViewById(R.id.botonEstilo);
        Button randomButton = findViewById(R.id.botonRandom);
        Button backgroundButton = findViewById(R.id.botonFondo);
        Button toastButton = findViewById(R.id.botonToast);

        // Animación de Bienvenida
        // Configuramos inicialmente el TextView para la animación:
        textView.setAlpha(0f);             // Comienza invisible
        textView.setTranslationY(-100f);    // Posicionado arriba (fuera de pantalla)
        textView.setText("¡Bienvenido a mi app!"); // Mensaje de bienvenida

        // Ejecutamos la animación: desvanecimiento y movimiento hacia su posición original.
        textView.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(1200)       // Duración de 1.2 segundos
                .setStartDelay(300)      // Retraso de 0.3 segundos para iniciar
                .start();

        // Funcionalidades de la aplicación

        textButton.setOnClickListener(v -> {
            if (isTextChanged) {
                textView.setText("¡Hola, Android!");
            } else {
                textView.setText("¡Bienvenido a mi app!");
            }
            isTextChanged = !isTextChanged;
        });

        colorButton.setOnClickListener(v -> {
            currentColorIndex = (currentColorIndex + 1) % colors.length;
            textView.setTextColor(colors[currentColorIndex]);
        });

        styleButton.setOnClickListener(v -> {
            currentStyleIndex = (currentStyleIndex + 1) % styles.length;
            textView.setTypeface(null, styles[currentStyleIndex]);
        });

        randomButton.setOnClickListener(v -> {
            Random random = new Random();
            textView.setTextColor(colors[random.nextInt(colors.length)]);
            textView.setTypeface(null, styles[random.nextInt(styles.length)]);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, random.nextInt(20) + 20);
        });

        backgroundButton.setOnClickListener(v -> {
            Random random = new Random();
            mainLayout.setBackgroundColor(backgroundColors[random.nextInt(backgroundColors.length)]);
        });

        toastButton.setOnClickListener(v -> {
            String nombre = inputNombre.getText().toString().trim();
            if (!nombre.isEmpty()) {
                Snackbar.make(mainLayout, "Hola, " + nombre + "!", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(mainLayout, "Por favor, ingrese su nombre.", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
