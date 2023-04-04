package id.ac.ubaya.informatika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.viewModel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    var studentList = ArrayList<Student>()
    var id = ""
    var name = ""
    var bod = ""
    var phone = ""
    var url = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            name = StudentDetailFragmentArgs.fromBundle(requireArguments()).name
            bod = StudentDetailFragmentArgs.fromBundle(requireArguments()).dob
            phone = StudentDetailFragmentArgs.fromBundle(requireArguments()).phone
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id,name,bod,phone,url)
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            view?.findViewById<TextView>(R.id.txtDetailId1)?.setText(it.id)
            view?.findViewById<TextView>(R.id.txtDetailName1)?.setText(it.name)
            view?.findViewById<TextView>(R.id.txtDetailBod1)?.setText(it.dob)
            view?.findViewById<TextView>(R.id.txtDetailPhone1)?.setText(it.phone)
        })
    }
}