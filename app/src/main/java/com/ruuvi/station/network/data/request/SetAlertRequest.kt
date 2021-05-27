package com.ruuvi.station.network.data.request

import com.ruuvi.station.alarm.domain.AlarmElement
import com.ruuvi.station.alarm.domain.AlarmType

data class SetAlertRequest(
    val sensor: String,
    val type: String,
    val min: Int,
    val max: Int,
    val enabled: Boolean
) {
    companion object {
        fun getAlarmRequest(alarm: AlarmElement): SetAlertRequest {
            val (low, high) =
                if (alarm.type == AlarmType.PRESSURE) {
                    Pair((alarm.low / 100), (alarm.high / 100))
                } else {
                    Pair(alarm.low, alarm.high)
                }
            return SetAlertRequest(alarm.sensorId, alarm.type.networkCode ?: throw IllegalArgumentException(), low, high, alarm.isEnabled)
        }
    }
}