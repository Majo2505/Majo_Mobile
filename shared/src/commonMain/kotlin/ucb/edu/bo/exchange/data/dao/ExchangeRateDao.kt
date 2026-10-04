package ucb.edu.bo.exchange.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import ucb.edu.bo.exchange.data.entity.ExchangeRateEntity
@Dao
interface ExchangeRateDao {
    @Query("SELECT * FROM exchangeRate")
    suspend fun getList(): List<ExchangeRateEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: ExchangeRateEntity)

    @Query("DELETE FROM exchangeRate")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(entity: ExchangeRateEntity)


    @Query("SELECT * FROM exchangeRate WHERE id = :id")
    suspend fun getById(id:String): ExchangeRateEntity?
}