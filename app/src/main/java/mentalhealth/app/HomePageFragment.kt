package mentalhealth.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home_page.*
import kotlinx.android.synthetic.main.fragment_profile.*


class HomePageFragment : Fragment(R.layout.fragment_home_page), View.OnClickListener {

    lateinit var navController: NavController
    val quotes = arrayOf("Your limitation—it's only your imagination.",
        "Push yourself, because no one else is going to do it for you.",
        "Great things never come from comfort zones.",
        "Dream it. Wish it. Do it.",
        "Success doesn’t just find you. You have to go out and get it.",
        "The harder you work for something, the greater you'll feel when you achieve it.",
        "Dream bigger.",
        "Even from a dark night, songs of beauty can be born. - Mary Anne Radmacher",
        "Every oak tree started out as a couple of nuts who stood their ground. - Anonymous",
        "And the day came when the risk it took to remain tight inside the bud was more painful than the risk it took to blossom. - Anais Nin",
        "We are not to blame for our illness, but we are responsible for our health.- Victoria Maxwell, BPP",
        "Every blade of grass has its angel that bends over it and whispers, 'Grow, grow.' - The Talmud",
        "Those who have a 'why' to live, can bear with almost any 'how'.  - Viktor E. Frankl",
        "My only advice: stay aware, listen carefully, and yell for help if you need it. -Judy Blume",
        "Even if you’re on the right track…if you just sit there, you’ll still get hit. - Will Rogers",
        "Action is the antidote to despair. - Joan Baez",
        "Life is the art of living with uncertainty, without being paralyzed by fear. – Dr. W. Dillon",
        "Never underestimate a person’s potential for recovery – Victoria Maxwell",
        "The Truth shall set you free…but first it’ll piss you off. - Gloria Steinem")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.weekly_stats_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.chatbot_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.profile_btn).setOnClickListener(this)

        val name = Firebase.auth.currentUser!!.displayName
        greet.text = "Hello $name,\ngood to see you!"
        val rands = (0..18).random()
        quote.text = quotes[rands]
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.weekly_stats_btn -> navController!!.navigate(R.id.action_homePageFragment_to_weeklyStatsFragment)
            R.id.chatbot_btn-> navController!!.navigate(R.id.action_homePageFragment_to_chatBotFragment)
            R.id.profile_btn -> navController!!.navigate(R.id.action_homePageFragment_to_profileFragment)
        }
    }



}