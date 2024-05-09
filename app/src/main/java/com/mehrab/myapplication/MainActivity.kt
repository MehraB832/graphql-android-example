package com.mehrab.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Error
import com.apollographql.apollo3.api.http.HttpMethod
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            try{
                Log.d("OnResume", "START")

//                GetLoginWithKeyQuery().performWork("OG16am52dHdtM3JlYzdyMTphZ04zeVRCR2hRVERSTjk4",
//                    object : GetLoginWithKeyQuery.GetLoginCallback {
//
//                    override fun onSuccess(data: GetLoginQuery.Data?) {
//                        // برخورد با موفقیت
//                        Log.d("Success", data.toString())
//                        Log.d("Queryyyyyy suc","Hero.name=${data?.service?.name}")
//                    }
//
//                    override fun onFailure(errors: List<Error>?) {
//                        // برخورد با خطا
//                        Log.d("Failure", errors.toString())
//                    }
//
//                })

                GetServersWithKeyQuery().performWork("OG16am52dHdtM3JlYzdyMTphZ04zeVRCR2hRVERSTjk4",
                    object : GetServersWithKeyQuery.GetServersCallback {

                        override fun onSuccess(data: GetServersQuery.Data?) {
                            // برخورد با موفقیت
                            Log.d("Success", data.toString())
                            Log.d("Queryyyyyy suc","Hero.name=${data?.servers?.get(0)?.name}")
                            Log.d("Queryyyyyy suc","Hero.url=${data?.servers?.get(0)?.url}")
                        }

                        override fun onFailure(errors: List<Error>?) {
                            // برخورد با خطا
                            Log.d("Failure", errors.toString())
                        }

                    })

            }catch (e: Exception){
                Log.d("An error", e.toString())
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}