package kr.puze.parkingpush

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity() {

    companion object {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        var pushRef = firebaseDatabase.reference.child("push")
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity

        pushRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val data = dataSnapshot.getValue<String>(String::class.java)
                Log.d("LOGTAG, onChildAdded:", data.toString())
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                val data = dataSnapshot.getValue<String>(String::class.java)
                Log.d("LOGTAG, onChildChanged:", data.toString())

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue<String>(String::class.java)
                Log.d("LOGTAG, onChildRemoved:", data.toString())
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                val data = dataSnapshot.getValue<String>(String::class.java)
                Log.d("LOGTAG, onChildMoved:", data.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        button.setOnClickListener {
            pushRef.setValue(Data(edit.text.toString()))
//            myRef.setValue(edit.text.toString())
            edit.setText("")
        }
    }

    @IgnoreExtraProperties
    data class Data(
        var car: String? = ""
    )
}
