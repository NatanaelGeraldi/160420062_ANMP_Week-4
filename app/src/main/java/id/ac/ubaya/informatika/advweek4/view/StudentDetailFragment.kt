package id.ac.ubaya.informatika.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.advweek4.R
import id.ac.ubaya.informatika.advweek4.databinding.FragmentStudentDetailBinding

import id.ac.ubaya.informatika.advweek4.databinding.StudentListItemBinding
import id.ac.ubaya.informatika.advweek4.model.Student
import id.ac.ubaya.informatika.advweek4.viewModel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
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
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater,
            R.layout.fragment_student_detail, container,false)
        return dataBinding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

       /*var idStudent =view.findViewById<TextView>(R.id.txtDetailId)
        idStudent.text = id
        var nameStudent =view.findViewById<TextView>(R.id.txtDetailName)
        nameStudent.text = name
        var dobStudent =view.findViewById<TextView>(R.id.txtDetailBod)
        dobStudent.text = bod
        var phoneStudent =view.findViewById<TextView>(R.id.txtDetailPhone)
        phoneStudent.text = phone*/
        if (arguments != null){
            id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id,name,bod,phone,url)
        observeViewModel()

    }
    fun observeViewModel() {
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
            /*view?.findViewById<TextView>(R.id.txtDetailId1)?.setText(it.id)
            view?.findViewById<TextView>(R.id.txtDetailName1)?.setText(it.name)
            view?.findViewById<TextView>(R.id.txtDetailBod1)?.setText(it.dob)
            view?.findViewById<TextView>(R.id.txtDetailPhone1)?.setText(it.phone)
            val btnNotif = view?.findViewById<Button>(R.id.btnNotif)
            var student = it
            btnNotif?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_error_24
                        )
                    }
            }*/

        })
    }
}