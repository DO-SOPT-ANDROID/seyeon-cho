package org.sopt.dosopttemplate

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponceUserSupport(
    @SerialName("url")
    val url: String = "",
    @SerialName("text")
    val text: String = "",
)