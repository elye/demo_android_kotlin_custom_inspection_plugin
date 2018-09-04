package com.plugin.inspection

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.util.NotNullLazyValue

class CustomNotificationComponent : ProjectComponent {

    override fun projectOpened() {}

    override fun projectClosed() {}

    override fun initComponent() {
        ApplicationManager.getApplication()
                .invokeLater(
                        {
                            Notifications.Bus.notify(NOTIFICATION_GROUP.value
                                    .createNotification(
                                            "Testing Personal Plugin",
                                            "Check for Kotlin non CamelCase usage",
                                            NotificationType.INFORMATION,
                                            null))
                        },
                        ModalityState.NON_MODAL)
    }

    override fun disposeComponent() {}

    override fun getComponentName(): String {
        return CUSTOM_NOTIFICATION_COMPONENT
    }

    companion object {
        private const val CUSTOM_NOTIFICATION_COMPONENT = "CustomNotificationComponent"
        private val NOTIFICATION_GROUP = object : NotNullLazyValue<NotificationGroup>() {
            override fun compute(): NotificationGroup {
                return NotificationGroup(
                        "Motivational message",
                        NotificationDisplayType.STICKY_BALLOON,
                        true)
            }
        }

    }
}
