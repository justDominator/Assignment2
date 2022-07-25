package com.example.navigation.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Database(entities = [Property::class], version = 1, exportSchema = false)
abstract class PropertyDatabase : RoomDatabase(){
    abstract fun propertyDao(): PropertyDao

    private class PropertyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var propertyDao = database.propertyDao()

                    for ( i in 1..15){

                        if(i%2 == 0){
                            val p2 = Property(
                                i,
                                "Assotech Blith",
                                "Busy Street Consultancy PVT LTD",
                                "2, 3, 4 BHK",
                                "Sector 99, Dwarka Expressway",
                                "₹ 88.73L - 1.84 Cr",
                                false
                            )
                            propertyDao.addProperty(p2)
                        }
                        else{
                            val p1 = Property(
                                i,
                                "Signature Global Park 4 And 5 Phase II",
                                "ROOFNASSETS INFRA PVT LTD",
                                "2, 3 BHK",
                                "Sector 36 Sohna, Gurgaon",
                                "₹ 56.25L - 61.58 L",
                                false
                            )
                            propertyDao.addProperty(p1)
                        }


                    }
                }
            }
        }
    }


    companion object{
        @Volatile
        private var INSTANCE : PropertyDatabase ? = null

        fun getDatabase(context: Context, scope: CoroutineScope):PropertyDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PropertyDatabase::class.java,
                    "property_database"

                )
                    .addCallback(PropertyDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}