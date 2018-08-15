package com.unit.ft_bird.database;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.sql.Database;
import com.badlogic.gdx.sql.DatabaseCursor;
import com.badlogic.gdx.sql.DatabaseFactory;
import com.badlogic.gdx.sql.SQLiteGdxException;

import java.util.ArrayList;

public class BirdDB {
	private static final String		TABLE_SCORE =	"scores";
	private static final String		ID =			"id";
	private static final String		SCORE =			"amount";
	private static final String		CREATE_TABLE =	"CREATE TABLE IF NOT EXISTS " + TABLE_SCORE + " (" + ID +
													" INTEGER PRIMARY KEY, " + SCORE + " INTEGER)";
	private static final String		GET_5_MAX =		"SELECT * FROM " + TABLE_SCORE + " ORDER BY " + SCORE + " DESC LIMIT 5";
	private static final String		GET_MAX =		"SELECT * FROM " + TABLE_SCORE + " ORDER BY " + SCORE + " DESC LIMIT 1";
	private static final String 	GET_ALL =		"SELECT * FROM " + TABLE_SCORE;
	private static final String		DELETE_TABLE =	"DROP TABLE IF EXISTS " + TABLE_SCORE;

	private static Database			myDB;

	public static void createDB() {
		myDB = DatabaseFactory.getNewDatabase(TABLE_SCORE, 1, CREATE_TABLE, null);
		myDB.setupDatabase();
		try {
			myDB.openOrCreateDatabase();
			myDB.execSQL(CREATE_TABLE);
		} catch (SQLiteGdxException e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
	}

	private static boolean isUnique(int score) {
		try {
			DatabaseCursor c = myDB.rawQuery(GET_ALL);
			while (c.next()) {
				if (c.getInt(1) == score) {
					c.close();
					return false;
				}
			}
			c.close();
		} catch (SQLiteGdxException e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
		return true;
	}

	private static String getInsertStringScore(int score) {
		return "INSERT INTO " + TABLE_SCORE + " (" + SCORE + ") VALUES (" + score + ")";
	}

	public static boolean insertScore(int score) {
		try {
			if (!isUnique(score))
				return false;
			else {
				myDB.execSQL(getInsertStringScore(score));
				return true;
			}
		} catch (SQLiteGdxException e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
		return false;
	}

	public static ArrayList<Integer> getDB() {
		try {
			ArrayList<Integer> ret = new ArrayList<>();
			DatabaseCursor c = myDB.rawQuery(GET_5_MAX);
			while (c.next())
				ret.add(c.getInt(1));
			return ret;
		} catch (SQLiteGdxException | NoSuchMethodError e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
		return new ArrayList<>();
	}

	public static int getMax() {
		try {
			int ret = 0;
			DatabaseCursor c = myDB.rawQuery(GET_MAX);
			while (c.next())
				ret = c.getInt(1);
			return ret;
		} catch (SQLiteGdxException | NoSuchMethodError e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
		return 0;
	}

	public static void deleteTable() {
		try {
			myDB.execSQL(DELETE_TABLE);
		} catch (SQLiteGdxException e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
	}

	public static void closeDB() {
		try {
			myDB.closeDatabase();
		} catch (SQLiteGdxException e) {
			Gdx.app.log("SQLite error: ", e.getMessage());
		}
	}
}
