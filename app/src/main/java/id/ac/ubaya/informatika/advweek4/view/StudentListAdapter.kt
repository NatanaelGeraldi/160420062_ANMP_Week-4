package id.ac.ubaya.informatika.advweek4.view

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.StudentListItemBinding
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.util.loadImage

class StudentListAdapter(val studenList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener
{
    class StudentViewHolder ( var view: StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
       // val view = inflater.inflate(R.layout.student_list_item, parent,false)
        val view  = DataBindingUtil.inflate<StudentListItemBinding>(inflater,R.layout.student_list_item, parent,false)
        return StudentViewHolder(view)
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studenList[position]
        holder.view.listener = this
       /* holder.view.findViewById<TextView>(R.id.txtId).text = studenList[position].id
        holder.view.findViewById<TextView>(R.id.txtName).text = studenList[position].name
        holder.view.findViewById<Button>(R.id.btnDetail).setOnClickListener{
            val action = StudentListFragmentDirections.actionStudentDetail(studenList[position].id.toString(),
                studenList[position].name.toString(),studenList[position].dob.toString(),studenList[position].phone.toString())
            Navigation.findNavController(it).navigate(action)
        }
        var imageView = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        imageView.loadImage(studenList[position].photoUrl,progressBar)*/
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studenList.clear()
        studenList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return studenList.size
    }
}