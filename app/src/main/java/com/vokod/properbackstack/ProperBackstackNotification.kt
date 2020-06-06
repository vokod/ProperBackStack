package com.vokod.properbackstack

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder


class ProperBackstackNotification(private val context: Context) {

    companion object {
        private const val CHANNEL_PROPER_BACK_STACK_NOTIFICATION =
            "com.vokod.ProperBackstackNotification"
        const val ID_PROPER_BACK_STACK_NOTIFICATION = 25

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mNotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val name = context.getString(R.string.notification_channel_title)
                val description =
                    context.getString(R.string.notification_channel_desc)
                val importance = NotificationManager.IMPORTANCE_LOW
                val channel =
                    NotificationChannel(CHANNEL_PROPER_BACK_STACK_NOTIFICATION, name, importance)
                channel.description = description
                channel.enableLights(false)
                channel.enableVibration(false)

                mNotificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun getUploadPhotosNotification(
        backStackBundle: Bundle
    ): Notification = NotificationCompat.Builder(context, CHANNEL_PROPER_BACK_STACK_NOTIFICATION)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setColor(context.getColor(R.color.colorAccent))
        .setContentTitle(context.getString(R.string.notification_title))
        .setContentIntent(buildStackIntent(backStackBundle))
        .build()

    fun showUploadPhotosNotification(
        backStackBundle: Bundle
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            ID_PROPER_BACK_STACK_NOTIFICATION,
            getUploadPhotosNotification(backStackBundle)
        )
    }

    private fun buildStackIntent(bundle: Bundle) = NavDeepLinkBuilder(context)
        .setComponentName(MainActivity::class.java)
        .setGraph(R.navigation.main_navigation)
        .setDestination(R.id.level2Fragment)
        .setArguments(bundle)
        .createPendingIntent()
}