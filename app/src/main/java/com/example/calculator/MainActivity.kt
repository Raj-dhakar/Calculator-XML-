package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import java.lang.ArithmeticException

class MainActivity : ComponentActivity() {

    private var tvInput:TextView?=null
    var lastNumeric:Boolean=false;
    var lastDot:Boolean=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView(R.layout.activity_main)
            tvInput=findViewById(R.id.calc_Input)
        }
    }

    fun OnDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric=true


    }

    fun onClear(view :View){
        tvInput?.text=""
        lastNumeric=false
        lastDot=false
    }

    fun onDecimalPoint(view:View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onOperator(view:View){

        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                println("Enter")
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot=false
            }
        }
    }

    private fun isOperatorAdded(value: String):Boolean{

        print("Chceck")
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-")
        }

    }

    fun onEqual(view:View){

        if(lastNumeric){
            var tvValue=tvInput?.text.toString()
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    var splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()) one=prefix+one

                    var result=one.toDouble() -two.toDouble();
                    tvInput?.text=result.toString()
                }
                else if(tvValue.contains("+")){
                    var splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()) one=prefix+one

                    var result=one.toDouble() +two.toDouble();
                    tvInput?.text=result.toString()
                }
                else if(tvValue.contains("*")){
                    var splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()) one=prefix+one

                    var result=one.toDouble() *two.toDouble();
                    tvInput?.text=result.toString()
                }
                else if(tvValue.contains("/")){
                    var splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]

                    if(prefix.isNotEmpty()) one=prefix+one

                    var result=one.toDouble() /two.toDouble();
                    tvInput?.text=result.toString()
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
}
