package sg.edu.np.WhackAMole;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button buttonLeft;
    private Button buttonMiddle;
    private Button buttonRight;


    private Integer scoreCount;

    private static final String TAG="Whack-a-mole";
    TextView score;

    /* Hint
        - The function setNewMole() uses the Random class to generate a random value ranged from 0 to 2.
        - The function doCheck() takes in button selected and computes a hit or miss and adjust the score accordingly.
        - The function doCheck() also decides if the user qualifies for the advance level and triggers for a dialog box to ask for user to decide.
        - The function nextLevelQuery() builds the dialog box and shows. It also triggers the nextLevel() if user selects Yes or return to normal state if user select No.
        - The function nextLevel() launches the new advanced page.
        - Feel free to modify the function to suit your program.
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        score=(TextView)findViewById(R.id.text);
        buttonLeft=findViewById(R.id.Button1);
        buttonMiddle=findViewById(R.id.Button2);
        buttonRight=findViewById(R.id.Button3);
        scoreCount =0 ;




        Log.v(TAG, "Finished Pre-Initialisation!");


    }
    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 1 Clicked");

                doCheck(buttonLeft);

            }
        });

        buttonMiddle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 2 Clicked");

                doCheck(buttonMiddle);

            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v(TAG, "Button 3 Clicked");

                doCheck(buttonRight);

            }
        });
        Log.v(TAG, "Starting GUI!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "Paused Whack-A-Mole!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "Stopped Whack-A-Mole!");
        finish();
    }

    private void doCheck(Button checkButton) {
        if (checkButton.getText().toString() == "*") {
            scoreCount = scoreCount + 1;

        } else {
            scoreCount = scoreCount - 1;

        }
        score.setText(Integer.toString(scoreCount));

        if (scoreCount  % 10 == 0) {
            nextLevelQuery();
        }

        setNewMole();

        /* Checks for hit or miss and if user qualify for advanced page.
            Triggers nextLevelQuery().
         */
    }

    private void nextLevelQuery(){

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Warning! Insane Whack-A-Mole incoming!");
        alertbox.setMessage("Would you like to advance to advanced mode? ");
        alertbox.setCancelable(true);
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG, "User decline!");

            }
        });
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.v(TAG, "User accepts!");
                nextLevel();

            }
        });
        alertbox.create().show();
        Log.v(TAG, "Advance option given to user!");

        /*
        Builds dialog box here.
        Log.v(TAG, "User accepts!");
        Log.v(TAG, "User decline!");
        Log.v(TAG, "Advance option given to user!");
        belongs here*/
    }

    private void nextLevel(){
        Intent  launchAdvanced = new Intent(MainActivity.this,Main2Activity.class);
        launchAdvanced.putExtra("Score", Integer.toString(scoreCount));
        startActivity(launchAdvanced);
        /* Launch advanced page */
    }

    private void setNewMole() {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        if(randomLocation ==0)
        {
            buttonLeft.setText("*");
            buttonMiddle.setText("O");
            buttonRight.setText("O");
        }
        else if(randomLocation ==1)
        {
            buttonLeft.setText("O");
            buttonMiddle.setText("*");
            buttonRight.setText("O");
        }
        else
        {
            buttonLeft.setText("O");
            buttonMiddle.setText("O");
            buttonRight.setText("*");
        }

    }
}