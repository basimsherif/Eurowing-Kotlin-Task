package com.basim.kotlinapp.injection

import com.basim.kotlinapp.utils.Constants.Companion.BASE_URL
import com.basim.kotlinapp.utils.Constants.Companion.CLIENT_ID
import com.basim.mercari.data.model.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Module which provides all required dependencies about network
 */
@Module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the Gallery service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Gallery service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideAPIInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        var clientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(clientBuilder.addInterceptor(headerAuthorizationInterceptor).build())
            .build()
    }

    private var headerAuthorizationInterceptor: Interceptor = Interceptor { chain ->
        var request: Request = chain.request()
        request = request?.newBuilder()
            ?.addHeader("Authorization", "Client-ID $CLIENT_ID")
            ?.build()!!
        chain.proceed(request)
    }
}