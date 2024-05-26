package ir.sajjadasadi.goldapp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import ir.sajjadasadi.goldapp.databinding.ActivityMainBinding
import ir.sajjadasadi.goldapp.remote.dataModel.ContentModel
import ir.sajjadasadi.goldapp.remote.dataModel.DateModel
import ir.sajjadasadi.goldapp.remote.dataModel.GoldModel
import ir.sajjadasadi.goldapp.remote.golds.GoldApiRepository
import ir.sajjadasadi.goldapp.remote.golds.GoldRequest
import ir.sajjadasadi.goldapp.remote.time.TimeApiRepository
import ir.sajjadasadi.goldapp.remote.time.TimeRequest

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val goldPrice = ArrayList<ContentModel>()
    private val currencyPrice = ArrayList<ContentModel>()
    private val cryptoPrice = ArrayList<ContentModel>()
    lateinit var topDate: ImageView
    lateinit var viewbuttoms: View
    lateinit var RecyclerV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        topDate = binding.imageView2
        viewbuttoms = binding.view
        RecyclerV = binding.RecView
        val topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val midleanimation = AnimationUtils.loadAnimation(this, R.anim.midle_animation)
        val bootomanimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        val recanim = AnimationUtils.loadAnimation(this, R.anim.recycleranim)
        topDate.startAnimation(topanimation)
        viewbuttoms.startAnimation(midleanimation)
        RecyclerV.startAnimation(recanim)
        TimeApiRepository.instance.getTime(
            object : TimeRequest {
                override fun OnSuccess(data: DateModel) {
                    val date =
                        "${data.date.l_value} ${data.date.j_value} ${data.date.F_value} ${data.date.Y_value}"
                    binding.txtTime.text = date
                }

                override fun OnNotSuccess(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

                override fun OnError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }

            }
        )
        getPrice()


        binding.RecView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        binding.txtTala.setOnClickListener {
            binding.txtTala.setTextColor(Color.parseColor("#E7C376"))
            binding.txtArz.setTextColor(Color.parseColor("#787879"))
            binding.txtCriptoPrice.setTextColor(Color.parseColor("#787879"))
            setData(goldPrice)
            RecyclerV.startAnimation(recanim)
            binding.txtCriptoPrice.isClickable = true
            binding.txtTala.isClickable = false
            binding.txtArz.isClickable = true

        }
        binding.txtArz.setOnClickListener {
            binding.txtTala.setTextColor(Color.parseColor("#787879"))
            binding.txtArz.setTextColor(Color.parseColor("#E7C376"))
            binding.txtCriptoPrice.setTextColor(Color.parseColor("#787879"))
            setData(currencyPrice)
            RecyclerV.startAnimation(recanim)
            binding.txtCriptoPrice.isClickable = true
            binding.txtTala.isClickable = true
            binding.txtArz.isClickable = false

        }
        binding.txtCriptoPrice.setOnClickListener {
            binding.txtCriptoPrice.setTextColor(Color.parseColor("#E7C376"))
            binding.txtTala.setTextColor(Color.parseColor("#787879"))
            binding.txtArz.setTextColor(Color.parseColor("#787879"))
            setData(cryptoPrice)
            RecyclerV.startAnimation(recanim)
            binding.txtCriptoPrice.isClickable = false
            binding.txtTala.isClickable = true
            binding.txtArz.isClickable = true


        }


    }

    private fun getPrice() {
        GoldApiRepository.instance.getGolds(
            object : GoldRequest {
                override fun OnSuccess(data: GoldModel) {
                    goldPrice.addAll(data.data.golds)
                    currencyPrice.addAll(data.data.currencies)
                    cryptoPrice.addAll(data.data.cryptocurrencies)

                    //نمایش قیمت طلا در ورود به برنامه
                    setData(goldPrice)
                }

                override fun OnNotSuccess(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

                override fun OnError(error: String) {
                    Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    private fun setData(data: ArrayList<ContentModel>) {
        binding.RecView.adapter = RecyclerMainAdapter(data)
    }
}