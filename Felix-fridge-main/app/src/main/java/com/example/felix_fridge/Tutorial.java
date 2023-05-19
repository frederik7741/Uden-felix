package com.example.felix_fridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {
    private TextView textField;

    private ImageView imageView;

    private final String[] textArray = {" 1/6 Hej alle sammen, mit navn er Felix, og velkommen til mit køkken! Jeg vil gerne vise jer rundt og fortælle jer, hvordan alt fungerer. Lad os starte med at kigge på køleskabet! Det er en maskine, der holder maden frisk og kold. Hvis du vil åbne det, skal du bare trykke på det. Klik på skærmen for at fortsætte",
            "2/6 Nu kan du se alle de lækre ting, der er i køleskabet. De Røde genstande bliver snart for gammel, og De grønne er nyt mad du har tilføjet. Du kan klikke på mad og vælge at spise det eller smide det ud. man kan klikke på den hvide pil i venstre hjørne for at komme tilbage til køleskabet.",
            "3/6 Nu er du tilbage ved selve køleskabet. Hvis du skal med ud og handle med dine forældre. Kan du klikke på den gule og åbne butikken. Klik på det gule papir.",
            "4/6 Her kan du vælge flere ting at handle ind. Men først skal du vælge hvilken slag mad, du vil have. Du kan vælge forskellige grupper af madvarer ved at trykke på de små ikoner øverst på skærmen. Hvis du vil vælge mælkeprodukter, så prøv at trykke på ko-ikonet.",
            "5/6 Nu kan du vælge mejeriprodukter og sætte dem ind i checklisten. Det er en god ide at have massere af sunde ting i køleskabet, som frugt og grøntsager og mejeriprodukter. For at komme videre til checklisten skal du trykke på indkøbskurven." ,
            "6/6 Her kan du se alt den mad, du har valgt. Hvis du vil have mere af en bestemt madvare, kan du trykke på plus-tegnet. Hvis du vil have mindre af noget, kan du trykke på minus-tegnet. Når du er færdig med at handle, skal du trykke på knappen, der hedder 'Afslut Handel'. Så er du færdig med at shoppe!. Nu når du er færdig skal du klikke på den hvide pil i venstre hjørne"};

    //put images in i selve project mappen som sus_kids... så kan man bare reffere til dem når vi skal bruge dem her. //sfa
    private final int[] images = {R.drawable.tut13, R.drawable.tut2, R.drawable.tut13, R.drawable.tut4, R.drawable.tut5, R.drawable.tut6};

    private int currentIndex = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        // Get references to the UI elements
        textField = findViewById(R.id.tutorialTxt);
        View button = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);

        // Reset the currentIndex variable
        currentIndex = 0;

        // Set the initial text in the text field
        textField.setText(textArray[currentIndex]);
        imageView.setImageResource(images[currentIndex]);


        // Set a click listener for the button
        button.setOnClickListener(view -> {
            // Increment the index to get the next text in the array
            currentIndex++;

            // Check if we have reached the end of the array
            if (currentIndex >= textArray.length) {

                Intent intent = new Intent(Tutorial.this, MainScreen.class);
                startActivity(intent);
            }
            else {
                // Set the new text in the text field
                textField.setText(textArray[currentIndex]);

                //set the new image in the image
                imageView.setImageResource(images[currentIndex]);

            }
        });

    }
}