package template.com.example.kan_p.prokedex.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImagePathDao {
    @Insert
    fun insert(imagePathEntity: ImagePathEntity)

    @Query("SELECT path FROM image_path WHERE image_path.id = :id")
    fun getPath(id: Int): LiveData<String>
}