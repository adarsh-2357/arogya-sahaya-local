package com.arogyasahaya.local.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.arogyasahaya.local.data.local.entity.HealthCampEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HealthCampDao_Impl implements HealthCampDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HealthCampEntity> __insertionAdapterOfHealthCampEntity;

  private final EntityDeletionOrUpdateAdapter<HealthCampEntity> __deletionAdapterOfHealthCampEntity;

  private final EntityDeletionOrUpdateAdapter<HealthCampEntity> __updateAdapterOfHealthCampEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllHealthCamps;

  public HealthCampDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHealthCampEntity = new EntityInsertionAdapter<HealthCampEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `health_camps` (`id`,`camp_name`,`location`,`date`,`asha_worker_name`,`description`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthCampEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCampName());
        statement.bindString(3, entity.getLocation());
        statement.bindLong(4, entity.getDate());
        statement.bindString(5, entity.getAshaWorkerName());
        statement.bindString(6, entity.getDescription());
      }
    };
    this.__deletionAdapterOfHealthCampEntity = new EntityDeletionOrUpdateAdapter<HealthCampEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `health_camps` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthCampEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfHealthCampEntity = new EntityDeletionOrUpdateAdapter<HealthCampEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `health_camps` SET `id` = ?,`camp_name` = ?,`location` = ?,`date` = ?,`asha_worker_name` = ?,`description` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HealthCampEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getCampName());
        statement.bindString(3, entity.getLocation());
        statement.bindLong(4, entity.getDate());
        statement.bindString(5, entity.getAshaWorkerName());
        statement.bindString(6, entity.getDescription());
        statement.bindLong(7, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM health_camps WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllHealthCamps = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM health_camps";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final HealthCampEntity healthCamp,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfHealthCampEntity.insertAndReturnId(healthCamp);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<HealthCampEntity> healthCamps,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfHealthCampEntity.insertAndReturnIdsList(healthCamps);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final HealthCampEntity healthCamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHealthCampEntity.handle(healthCamp);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final HealthCampEntity healthCamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHealthCampEntity.handle(healthCamp);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllHealthCamps(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllHealthCamps.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllHealthCamps.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<HealthCampEntity>> getAllHealthCamps() {
    final String _sql = "SELECT * FROM health_camps ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getHealthCampById(final long id,
      final Continuation<? super HealthCampEntity> $completion) {
    final String _sql = "SELECT * FROM health_camps WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HealthCampEntity>() {
      @Override
      @Nullable
      public HealthCampEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final HealthCampEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _result = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<HealthCampEntity> getHealthCampByIdFlow(final long id) {
    final String _sql = "SELECT * FROM health_camps WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<HealthCampEntity>() {
      @Override
      @Nullable
      public HealthCampEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final HealthCampEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _result = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HealthCampEntity>> getUpcomingHealthCamps(final long currentDate) {
    final String _sql = "SELECT * FROM health_camps WHERE date >= ? ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HealthCampEntity>> getPastHealthCamps(final long currentDate) {
    final String _sql = "SELECT * FROM health_camps WHERE date < ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HealthCampEntity>> getHealthCampsBetweenDates(final long startDate,
      final long endDate) {
    final String _sql = "SELECT * FROM health_camps WHERE date BETWEEN ? AND ? ORDER BY date ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HealthCampEntity>> searchHealthCamps(final String query) {
    final String _sql = "SELECT * FROM health_camps WHERE location LIKE '%' || ? || '%' OR camp_name LIKE '%' || ? || '%' ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HealthCampEntity>> getHealthCampsByAshaWorker(final String ashaWorkerName) {
    final String _sql = "SELECT * FROM health_camps WHERE asha_worker_name = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, ashaWorkerName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<HealthCampEntity>>() {
      @Override
      @NonNull
      public List<HealthCampEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCampName = CursorUtil.getColumnIndexOrThrow(_cursor, "camp_name");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfAshaWorkerName = CursorUtil.getColumnIndexOrThrow(_cursor, "asha_worker_name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final List<HealthCampEntity> _result = new ArrayList<HealthCampEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HealthCampEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCampName;
            _tmpCampName = _cursor.getString(_cursorIndexOfCampName);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpAshaWorkerName;
            _tmpAshaWorkerName = _cursor.getString(_cursorIndexOfAshaWorkerName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            _item = new HealthCampEntity(_tmpId,_tmpCampName,_tmpLocation,_tmpDate,_tmpAshaWorkerName,_tmpDescription);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getAllAshaWorkerNames() {
    final String _sql = "SELECT DISTINCT asha_worker_name FROM health_camps ORDER BY asha_worker_name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<String>> getAllLocations() {
    final String _sql = "SELECT DISTINCT location FROM health_camps ORDER BY location ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getHealthCampCount() {
    final String _sql = "SELECT COUNT(*) FROM health_camps";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Integer> getUpcomingHealthCampCount(final long currentDate) {
    final String _sql = "SELECT COUNT(*) FROM health_camps WHERE date >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"health_camps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
