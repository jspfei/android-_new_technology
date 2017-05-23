package net.tt.androidkotlin
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val  ggg: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            ggg?.setText("切换");
        }
    }

    fun check(c:Char,str:String){
        if(c == '1'){

        }

        for (c in str){
            println(c)

            Log.i("HACK-TAG","SSSSSSS")
        }

        val i = 10
        val s = "i = $i"

        var s1 = "abc"

        val str = "$s.length is ${s.length}"


        val a:Int = 0
        val b:Int = 3
        val max = if(a > b){
            print("choose a")
            a
        }else{
            println("choose b ")
            b
        }

        when(a){
            1 -> print("1")
            2 -> print("2")
            else ->{
                print("else")
            }
        }

    }


}
