package io.ravecode.firebaseauthapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.ravecode.firebaseauthapp.databinding.ActivityMainBinding
import io.ravecode.firebaseauthapp.databinding.ActivitySignupBinding

class SIgnupActivity : AppCompatActivity() {
    //Initiating variable for binding and auth
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting up binding
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialising Firebase Auth
        auth = Firebase.auth

        //Adding onClickListener to the Continue button
        binding.continueBtn.setOnClickListener{
            //Code which will run when continue button is clicked
            auth.createUserWithEmailAndPassword(binding.email.getText().toString().trim(), binding.password.getText()
                .toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Code which will run when signin is successtion
                        Log.d(TAG, "createUserWithEmail:success")

                        //Current user variable
                        val user = auth.currentUser

                        //If login is successful, move to Mainactivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Code which will run when Login Fails

                        Log.w(TAG, "createUserWithEmail:failure", task.exception)

                        // If user registration fails, display a message to the user.
                        Toast.makeText(
                            baseContext,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        //Move to SignupActivity with the text link
        binding.move.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    //On start of the activity, check if the user is already loged in, if logged in, then move to MainActivity
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}