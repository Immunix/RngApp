package com.example.rngclone

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_lotto.*

class LottoFragment : Fragment() {

    private val lottoArray = ArrayList<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lotto, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var pos = 0

        //  probably can do without the variable
        val spinner = lotto_spinner

        val adapter = ArrayAdapter.createFromResource(
            context!!, // need to learn more about how this works
            R.array.lotto_choices,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                pos = spinner.selectedItemPosition
            }
        }

        lotto_button.setOnClickListener {

            if (lotto_results != null) {
                lottoArray.clear()
            }

            when (pos) {
                0 -> {
                    lottoRoFun()
                    lotto_results.text = lottoArray.asIterable().joinToString(", ")
                }
                1 -> {
                    lotto5Fun()
                    lotto_results.text = lottoArray.asIterable().joinToString(", ")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun lottoRoFun () {
        var num: Int

         while (lottoArray.size <= 5) {
             num = randomNumber(1, 49)

             if (num !in lottoArray) {
                 lottoArray.add(num)
             } else {
                 continue
             }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun lotto5Fun () { // can be changed to something else. used just as a placeholder
        var num: Int

        while (lottoArray.size <= 4) {
            num = randomNumber(1, 40)

            if (num !in lottoArray) {
                lottoArray.add(num)
            } else {
                continue
            }
        }
    }
}