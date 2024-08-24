import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timemanager.R

class CalendarThreeDayAdapter(private val context: Context) : RecyclerView.Adapter<CalendarThreeDayAdapter.ScheduleCellViewHolder>() {

    private val numColumns = 3

    class ScheduleCellViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleCellViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.calendar_three_day_cell, parent, false)
        return ScheduleCellViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleCellViewHolder, position: Int) {
        if (position < numColumns) {
            // Setup the date row
            holder.view.findViewById<TextView>(R.id.timeText).text = "Date " + (position + 1).toString()
        } else {
            val adjustedPosition = position - numColumns
            val hour = adjustedPosition / (4 * numColumns)
            val minute = (adjustedPosition / numColumns % 4) * 15
            val time = String.format("%02d:%02d", hour, minute)
            holder.view.findViewById<TextView>(R.id.timeText).text = time
        }
    }

    override fun getItemCount(): Int {
        return numColumns + 24 * 4 * numColumns // 3 columns plus 24 hours * 4 segments per hour * 3 columns
    }
}
