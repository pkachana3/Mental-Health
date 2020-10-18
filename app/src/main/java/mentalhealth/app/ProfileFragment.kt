package mentalhealth.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    lateinit var navController: NavController
    private val RC_SIGN_IN = 123

    var totalMood = 0.0
    var totalClicks = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.signout_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.m1).setOnClickListener(this)
        view.findViewById<Button>(R.id.m2).setOnClickListener(this)
        view.findViewById<Button>(R.id.m3).setOnClickListener(this)
        view.findViewById<Button>(R.id.m4).setOnClickListener(this)
        view.findViewById<Button>(R.id.m5).setOnClickListener(this)

        profileName.text=Firebase.auth.currentUser!!.displayName
    }

    override fun onClick(v: View?) {
        if (v!!.getId() == R.id.signout_btn) {
            FirebaseAuth.getInstance().signOut()
            val providers = arrayListOf(
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.EmailBuilder().build()
            )
            startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
                RC_SIGN_IN)
        } else {
            var moodRating = 0.0

            if (v!!.getId() == R.id.m1) {
                totalMood += 1
                totalClicks++
                moodRating = 1.0
            }
            if (v!!.getId() == R.id.m2) {
                totalMood += 2
                totalClicks++
                moodRating = 2.0
            }
            if (v!!.getId() == R.id.m3) {
                totalMood += 3
                totalClicks++
                moodRating = 3.0
            }
            if (v!!.getId() == R.id.m4) {
                totalMood += 4
                totalClicks++
                moodRating = 4.0
            }
            if (v!!.getId() == R.id.m5) {
                totalMood += 5
                totalClicks++
                moodRating = 5.0
            }
            var avg = Math.round((totalMood/totalClicks) * 100.0) / 100.0
            if (moodRating < 3) {
                feedback.text = "\nThat's concerning! You should consider talking to me through the chat"
            } else if(moodRating < 5) {
                feedback.text = "\nThat's good! Keep improving!"
            } else {
                feedback.text = "\nThat's great! Keep it up!!"
            }
            if ((moodRating < avg) && (totalClicks > 1)) {
                feedback2.text = "\nOh no, that lower than usual! Your average is $avg"
            } else if ((moodRating == avg) && (totalClicks > 1)){
                feedback2.text = "\nThat's around your average mood! Your average is $avg"
            } else if ((moodRating >= avg) && (totalClicks > 1)){
                feedback2.text = "\nThat's better than usual! Your average is $avg"
            }

        }
    }
}