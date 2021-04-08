package com.ruuvi.station.database.tables

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import com.ruuvi.station.bluetooth.FoundRuuviTag
import com.ruuvi.station.database.LocalDatabase
import java.util.*

@Table(
    name = "RuuviTag",
    database = LocalDatabase::class,
    useBooleanGetterSetters = false
)
data class RuuviTagEntity(
    @Column
    @PrimaryKey
    var id: String? = null,
    @Column
    var url: String? = null,
    @Column
    var rssi: Int = 0,
    @Column
    var name: String? = null,
    @Column
    var temperature: Double = 0.0,
    @Column
    var temperatureOffset: Double = 0.0,
    @Column
    var humidity: Double? = null,
    @Column
    var pressure: Double? = null,
    @Column
    var favorite: Boolean = false,
    @Column
    var accelX: Double = 0.0,
    @Column
    var accelY: Double = 0.0,
    @Column
    var accelZ: Double = 0.0,
    @Column
    var voltage: Double = 0.0,
    @Column
    var updateAt: Date? = null,
    @Column
    var gatewayUrl: String? = null,
    @Column
    var defaultBackground: Int = 0,
    @Column
    var userBackground: String? = null,
    @Column
    var dataFormat: Int = 0,
    @Column
    var txPower: Double = 0.0,
    @Column
    var movementCounter: Int = 0,
    @Column
    var measurementSequenceNumber: Int = 0,
    @Column
    var createDate: Date? = null,
    @Column
    var humidityOffset: Double = 0.0,
    @Column
    var humidityOffsetDate: Date? = null,
    @Column
    var connectable: Boolean = false,
    @Column
    var lastSync: Date? = null,
    @Column
    var networkLastSync: Date? = null,
    @Column
    var networkBackground: String? = null
): BaseModel() {

    constructor(tag: FoundRuuviTag) :this(
        id = tag.id,
        url = tag.url,
        rssi = tag.rssi ?: 0,
        temperature = tag.temperature ?: 0.0,
        humidity = tag.humidity,
        pressure = tag.pressure,
        accelX = tag.accelX ?: 0.0,
        accelY = tag.accelY ?: 0.0,
        accelZ = tag.accelZ ?: 0.0,
        voltage = tag.voltage ?: 0.0,
        dataFormat = tag.dataFormat ?: 0,
        txPower = tag.txPower ?: 0.0,
        movementCounter = tag.movementCounter ?: 0,
        measurementSequenceNumber = tag.measurementSequenceNumber ?: 0,
        connectable = tag.connectable ?: false
    )

    val displayName get() = if (name.isNullOrEmpty()) id.toString() else name.toString()

    fun preserveData(tag: RuuviTagEntity): RuuviTagEntity {
        tag.name = name
        tag.createDate = createDate
        tag.favorite = favorite
        tag.defaultBackground = defaultBackground
        tag.userBackground = userBackground
        tag.updateAt = Date()
        tag.humidityOffset = humidityOffset
        tag.humidityOffsetDate = humidityOffsetDate
        tag.lastSync = lastSync
        tag.networkLastSync = networkLastSync
        tag.networkBackground = networkBackground
        return tag
    }

    fun updateData(reading: TagSensorReading) {
        rssi = reading.rssi
        temperature = reading.temperature
        humidity = reading.humidity
        pressure = reading.pressure
        accelX = reading.accelX
        accelY = reading.accelY
        accelZ = reading.accelZ
        voltage = reading.voltage
        dataFormat = reading.dataFormat
        txPower = reading.txPower
        movementCounter = reading.movementCounter
        measurementSequenceNumber = reading.measurementSequenceNumber
        updateAt = reading.createdAt
        networkLastSync = reading.createdAt
    }
}