package com.example.duan.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.duan.Model.Question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper";
    public static final String TABLE_NAME = "Question";
    private static String DB_PATH = "";
    private static String DB_NAME = "Question.sqlite";
    public static final String TABLE_ID = "_id";
    public static final String TABLE_QUESTION = "Question";
    public static final String TABLE_CASE_A = "CaseA";
    public static final String TABLE_CASE_B = "CaseB";
    public static final String TABLE_CASE_C = "CaseC";
    public static final String TABLE_CASE_D = "CaseD";
    public static final String TABLE_TRUE_CASE = "TrueCase";
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, 1);// 1? Its database Version
        if (android.os.Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.mContext = context;
    }

    public void createDataBase() {
        //If the database does not exist, copy it from the assets.

        boolean mDataBaseExist = checkDataBase();
        if (!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                //Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }


    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    public ArrayList<Question> getData(){
        createDataBase();
        openDataBase();
        ArrayList<Question> arrQuestions=new ArrayList<>();
        for (int i=1;i<16;i++) {
            String table = TABLE_NAME + i+"";
            String sql="SELECT * FROM "+table+" ORDER BY random() limit 1";
            Cursor cursor= mDataBase.rawQuery(sql,null);
            int indexId= cursor.getColumnIndex(TABLE_ID);
            int indexQuestion= cursor.getColumnIndex(TABLE_QUESTION);
            int indexCaseA=cursor.getColumnIndex(TABLE_CASE_A);
            int indexCaseB=cursor.getColumnIndex(TABLE_CASE_B);
            int indexCaseC=cursor.getColumnIndex(TABLE_CASE_C);
            int indexCaseD=cursor.getColumnIndex(TABLE_CASE_D);
            int indexTrueCase=cursor.getColumnIndex(TABLE_TRUE_CASE);
            cursor.moveToFirst();
            int id=cursor.getInt(indexId);
            String question= cursor.getString(indexQuestion);
            String caseA= cursor.getString(indexCaseA);
            String caseB= cursor.getString(indexCaseB);
            String caseC= cursor.getString(indexCaseC);
            String caseD= cursor.getString(indexCaseD);
            int trueCase= cursor.getInt(indexTrueCase);
            Question question1=new Question(id,question,caseA,caseB,caseC,caseD,trueCase);
            arrQuestions.add(question1);
        }

        return arrQuestions;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
