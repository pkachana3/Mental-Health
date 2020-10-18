package mentalhealth.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import kotlinx.android.synthetic.main.fragment_weekly_stats.*

class WeeklyStatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_stats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        WeeklyStatsPH.text = "test"
        Fitness.getHistoryClient(requireActivity(),
            GoogleSignIn.getLastSignedInAccount(requireActivity())!!
        )
            .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
            .addOnSuccessListener {
                WeeklyStatsPH.text = "Steps: ${it.dataPoints[0].getValue(Field.FIELD_STEPS)}"
            }
            .addOnFailureListener {
                WeeklyStatsPH.text = "Error"
            }
    }
}