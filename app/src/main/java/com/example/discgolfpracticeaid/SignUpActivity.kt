package com.example.discgolfpracticeaid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.discgolfpracticeaid.databinding.SignUpActivityBinding
import com.example.discgolfpracticeaid.datamodels.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private lateinit var binding: SignUpActivityBinding
private lateinit var auth: FirebaseAuth
private lateinit var db: FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser != null) {
            goToHomePage()
        }

        binding.haveAnAccountLink.setOnClickListener {
            goToLogin()
        }

        binding.signUp.setOnClickListener {
            val Username = binding.Username.text.toString().trim()
            val Email = binding.Email.text.toString().trim()
            val Password = binding.Password.text.toString().trim()

            if (Username.isEmpty()) {
                binding.Username.error = "Username cannot be empty!"
                return@setOnClickListener
            }

            if (Email.isEmpty()) {
                binding.Email.error = "Email cannot be empty!"
                return@setOnClickListener
            }

            if (Password.isEmpty()) {
                binding.Password.error = "Password cannot be empty!"
                return@setOnClickListener
            }

            val newUser = UserModel(Username, Email, Password)

            auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(ACTIVITY_NAME, "createUserWithEmail:success")
                        val user = auth.currentUser?.uid
                        createUser(USER_COLLECTION, user.toString(), newUser)
                        goToHomePage()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(ACTIVITY_NAME, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }


        }
    }

    fun goToLogin() {
        val LogInActivity = Intent(this, LogInActivity::class.java)
        startActivity(LogInActivity)
        finish()
    }

    fun goToHomePage() {
        val HomePageActivity = Intent(this, HomePageActivity::class.java)
        startActivity(HomePageActivity)
        finish()
    }

    fun createUser(collection : String, document : String, information : UserModel) {
        db.collection(collection)
            .document(document)
            .set(information)
            .addOnSuccessListener {
                Toast.makeText(this@SignUpActivity, "Account created for ${information.Email}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@SignUpActivity, "Account creation failed.", Toast.LENGTH_SHORT).show()
                Log.e(ACTIVITY_NAME, "Failure Creating New Account : Err : " + it.message)
            }
    }

    companion object {
        private const val ACTIVITY_NAME = "SignUpActivity"
        private const val USER_COLLECTION = "users"
    }
}