package org.school.demoapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import org.school.demoapp.data.network.model.FavImageList
import org.school.demoapp.data.network.model.WallPaperList

val DATABASENAME = "MY DATABASE"
val TABLENAME = "Wallpapers"
val COL_NAME = "name"
val COL_PREVIIEWLINK = "previewLink"
val COL_LARGELINK = "largeLink"
val COL_ID = "id"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null, 1)
{
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_NAME + " VARCHAR(256)," + COL_LARGELINK + " VARCHAR(2000)," + COL_PREVIIEWLINK + " VARCHAR(2000))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData( wallpaper: WallPaperList)
    {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, wallpaper.userId)
        contentValues.put(COL_PREVIIEWLINK, wallpaper.previewURL)
        contentValues.put(COL_LARGELINK, wallpaper.largeImageURL)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Added as favorite", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData( userId: String )
    {
        val database = this.writableDatabase

        val result = database.delete(TABLENAME, COL_NAME+"= ${ userId }", null )

        if (result == 0 ) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "removed from favorite", Toast.LENGTH_SHORT).show()
        }
    }

    fun readData(): MutableList<FavImageList> {
        val list: MutableList<FavImageList> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val favImage = FavImageList(

                    result.getString(result.getColumnIndex(COL_ID)),
                    result.getString(result.getColumnIndex(COL_NAME)),
                    result.getString(result.getColumnIndex(COL_PREVIIEWLINK)),
                    result.getString(result.getColumnIndex(COL_LARGELINK))
                )
                list.add(favImage)
            }
            while (result.moveToNext())
        }
        return list
    }
}