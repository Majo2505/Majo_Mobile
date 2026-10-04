package ucb.edu.bo.exchange.data.entity
import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
@Entity(tableName = "exchangeRates")
data class ExchangeRateEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,


    @ColumnInfo(name = "official_rate")
    var officialRate: String? = null,


    @ColumnInfo(name = "parallel_rate")
    var parallelRate: String? = null,


    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0)